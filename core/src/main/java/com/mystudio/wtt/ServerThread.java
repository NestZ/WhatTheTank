package com.mystudio.wtt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerThread extends Thread {
      private ArrayList<ClientInfo> clients;
      private boolean isRunning = true;
      private ServerSocket serverSocket;
      private int serverPort = 1234;

      public ServerThread() throws SocketException{
            this.clients = new ArrayList<>();
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

      class ServerSubThread extends Thread{
            private BufferedReader reader;
            private BufferedWriter writer;
            private Socket socket;
            private boolean isRunning = true;

            public ServerSubThread(Socket socket, BufferedReader reader, BufferedWriter writer){
                  this.reader = reader;
                  this.writer = writer;
                  this.socket = socket;
            }

            @Override
            public void run(){
                  String command;
                  while(this.isRunning){
                        try{
                              System.out.println("Sub - Thread : Waiting for command");
                              command = this.reader.readLine();
                              System.out.println(command);
                              if(command.startsWith("hello")){
                                    float x = Float.parseFloat(command.substring(5,command.indexOf(",")));
                                    float y = Float.parseFloat(command.substring(command.indexOf(",") + 1,command.indexOf(":")));
                                    int dir = Integer.parseInt(command.substring(command.indexOf(":") + 1,command.length()));
                                    clients.add(new ClientInfo(this.writer, x, y, dir));
                                    System.out.println("Server : rec hello");
                              }
                              else if(command.startsWith("Update"))System.out.println("kuy");
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
            private BufferedWriter writer;

            public ClientInfo(BufferedWriter writer, float x, float y, int dir){
                  this.x = x;
                  this.y = y;
                  this.dir = dir;
                  this.writer = writer;
            }

            public float getX(){
                  return this.x;
            }

            public float getY(){
                  return this.y;
            }

            public void setX(float x){
                  this.x = x;
            }

            public void setY(float y){
                  this.y = y;
            }

            public void setDir(int dir){
                  this.dir = dir;
            }

            public int getDir(){
                  return this.dir;
            }
      }
}