package com.mystudio.wtt.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import com.mystudio.wtt.utils.ParseString;

public class ServerThread extends Thread{
      public static int clientCount = 0;
      private HashMap<Integer, ClientInfo> clients;
      private int redTeam;
      private int blueTeam;
      private boolean isRunning = true;
      private ServerSocket serverSocket;
      private int serverPort = 64740;

      public ServerThread() throws SocketException{
            this.redTeam = 0;
            this.blueTeam = 0;
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
            Iterator<Integer> it = clients.keySet().iterator();
            while(it.hasNext()){
                  BufferedWriter writer = clients.get(it.next()).getWriter();
                  try{
                        writer.write(Protocol.updatePackage(ID, moveDir, status, x, y));
                        writer.flush();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }

      public void sendRegister(int ID, int team, String name){
            Iterator<Integer> it = clients.keySet().iterator();
            while(it.hasNext()){
                  int key = it.next();
                  if(key != ID){
                        BufferedWriter writer = clients.get(key).getWriter();
                        try{
                              writer.write(Protocol.registerPackage(ID, team, name));
                              writer.flush();
                        }
                        catch(IOException e){
                              e.printStackTrace();
                        }
                  }
            }
      }

      public void sendGets(BufferedWriter writer, int ID){
            try{
                  writer.write(Protocol.getsPackage(clients.size() - 1));
                  writer.flush();
                  Iterator<Integer> it = clients.keySet().iterator();
                  while(it.hasNext()){
                        int key = it.next();
                        if(key != ID){
                              ClientInfo client = clients.get(key);
                              writer.write(Protocol.getPackage(key, client.getID(), client.team(), client.name()));
                              writer.flush();
                        }
                  }
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      public void sendShoot(int ID, int dir, float x, float y){
            Iterator<Integer> it = clients.keySet().iterator();
            while(it.hasNext()){
                  BufferedWriter writer = clients.get(it.next()).getWriter();
                  try{
                        writer.write(Protocol.shootPackage(ID, dir, x, y));
                        writer.flush();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }

      public void sendStart(){
            Iterator<Integer> it = clients.keySet().iterator();
            while(it.hasNext()){
                  BufferedWriter writer = clients.get(it.next()).getWriter();
                  try{
                        writer.write(Protocol.startPackage());
                        writer.flush();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
            }
      }

      private class ServerSubThread extends Thread{
            private BufferedReader reader;
            private BufferedWriter writer;
            private Socket socket;
            private boolean isRunning = true;
            private long threadID;

            private ServerSubThread(Socket socket, BufferedReader reader, BufferedWriter writer){
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
                                    String name = command.substring(5,command.indexOf(":"));
                                    int ID = ServerThread.clientCount++;
                                    int team;
                                    if(blueTeam < 2 && blueTeam <= redTeam){
                                          team = 1;
                                          blueTeam++;
                                    }
                                    else{
                                          team = 2;
                                          redTeam++;
                                    }
                                    clients.put(ID, new ClientInfo(this.writer, team, ID, name));
                                    this.writer.write(Protocol.initPackage(team, ID, name));
                                    this.writer.flush();
                                    sendGets(this.writer, ID);
                                    sendRegister(ID, team, name);
                                    System.out.println("Sub - Thread " + this.threadID + " : Client requested register");
                                    System.out.println("Current Clients : " + ServerThread.clientCount);
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
                              else if(command.startsWith("Shoot")){
                                    int ID = ParseString.parseID(command, 5);
                                    int dir = ParseString.parseDir(command);
                                    float x = ParseString.parseX(command);
                                    float y = ParseString.parseY(command);
                                    sendShoot(ID, dir, x, y);
                                    System.out.println("ID " + ID + " is shooting!");
                              }
                              else if(command.startsWith("Start")){
                                    sendStart();
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
      

      private class ClientInfo{
            private float x;
            private float y;
            private int dir;
            private int ID;
            private int team;
            private String name;
            private BufferedWriter writer;

            private ClientInfo(BufferedWriter writer, int team, int ID, String name){
                  this.ID = ID;
                  this.team = team;
                  this.name = name;
                  this.writer = writer;
            }

            private void setPos(float x, float y){
                  this.x = x;
                  this.y = y;
            }

            private int team(){
                  return this.team;
            }

            private String name(){
                  return this.name;
            }

            private float getX(){
                  return this.x;
            }

            private float getY(){
                  return this.y;
            }

            private int getID(){
                  return this.ID;
            }

            private int getDir(){
                  return this.dir;
            }

            private BufferedWriter getWriter(){
                  return this.writer;
            }
      }
}