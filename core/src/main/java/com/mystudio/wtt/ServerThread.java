package com.mystudio.wtt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ServerThread extends Thread {
      private HashMap<Integer, ClientInfo> clients;
      private boolean isRunning = true;
      private ServerSocket serverSocket;
      private int serverPort = 1234;

      public ServerThread() throws SocketException{
            this.clients = new HashMap<>();
            try{
                  this.serverSocket = new ServerSocket(this.serverPort);
            }
            catch(IOException e){
                  e.printStackTrace();
            }
            System.out.println("Server : Server Created");
      }

      @Override
      public void run(){
            Socket clientSocket = null;
            while(this.isRunning){
                  try{
                        clientSocket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        ServerSubThread subThread = new ServerSubThread(clientSocket, reader, writer);
                        subThread.start();
                        System.out.println("Server : Sub - Thread Started");
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
            try{
                  this.serverSocket.close();
                  clientSocket.close();
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      public void sendUpdate(int ID, char moveDir, int status, float x, float y){
            for(int i = 0;i < clients.size();i++){
                  BufferedWriter writer = clients.get(i).getWriter();
                  try{
                        writer.write("Update" + ID + moveDir + Integer.toString(status) + "x" + x + "y" + y + ":\n");
                        writer.flush();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }

      public void sendRegister(int ID, int dir, float x, float y){
            for(int i = 0;i < clients.size();i++){
                  BufferedWriter writer = clients.get(i).getWriter();
                  try{
                        writer.write("REG" + ID + "x" + x + "y" + y + ":" + dir + "\n");
                        writer.flush();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }

      public void sendGets(BufferedWriter writer){
            try{
                  writer.write("GETS" + clients.size() + "\n");
                  writer.flush();
                  for(int i = 0;i < clients.size();i++){
                        ClientInfo client = clients.get(i);
                        writer.write("GET" + i + client.getID() + "x" + client.getX() + "y" + client.getY() + ":" + client.getDir() + "\n");
                        writer.flush();
                  }
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      class ServerSubThread extends Thread{
            private BufferedReader reader;
            private BufferedWriter writer;
            private Socket socket;
            private boolean isRunning = true;
            private long threadID;

            public ServerSubThread(Socket socket, BufferedReader reader, BufferedWriter writer){
                  this.reader = reader;
                  this.writer = writer;
                  this.socket = socket;
                  this.threadID = Thread.currentThread().getId();
            }

            @Override
            public void run(){
                  String command;
                  while(this.isRunning){
                        try{
                              System.out.println("Sub - Thread " + this.threadID + " : Waiting for command");
                              command = this.reader.readLine();
                              if(command.startsWith("Hello")){
                                    int ID = clients.size();
                                    float x = ParseString.parseX(command);
                                    float y = ParseString.parseY(command);
                                    int dir = ParseString.parseDir(command);
                                    clients.put(ID, new ClientInfo(this.writer, x, y, dir, ID));
                                    this.writer.write("InitID" + ID + "\n");
                                    this.writer.flush();
                                    sendGets(this.writer);
                                    sendRegister(ID, dir, x, y);
                                    System.out.println("Sub - Thread " + this.threadID + " : Client requested register");
                                    System.out.println("Current Clients : " + clients.size());
                              }
                              else if(command.startsWith("Update")){
                                    int ID = ParseString.parseID(command, 6);
                                    char moveDir = command.charAt(7);
                                    int status = ParseString.parseID(command, 8);
                                    float x = ParseString.parseX(command);
                                    float y = ParseString.parseY(command);
                                    if(status == 0)clients.get(ID).setPos(x, y);
                                    sendUpdate(ID, moveDir, status, x, y);
                                    System.out.println("Sub - Thread " + this.threadID + " : Client requested Update");
                              }
                        }
                        catch(IOException e){
                              e.printStackTrace();
                        }
                  }
                  try{
                        this.writer.close();
                        this.reader.close();
                        socket.close();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }
      

      class ClientInfo{
            private float x;
            private float y;
            private int dir;
            private int ID;
            private BufferedWriter writer;

            public ClientInfo(BufferedWriter writer, float x, float y, int dir, int ID){
                  this.x = x;
                  this.y = y;
                  this.ID = ID;
                  this.dir = dir;
                  this.writer = writer;
            }

            public void setPos(float x, float y){
                  this.x = x;
                  this.y = y;
            }

            public float getX(){
                  return this.x;
            }

            public float getY(){
                  return this.y;
            }

            public int getID(){
                  return this.ID;
            }

            public int getDir(){
                  return this.dir;
            }

            public BufferedWriter getWriter(){
                  return this.writer;
            }
      }
}