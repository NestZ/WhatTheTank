package com.mystudio.wtt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerThread extends Thread {
      private ArrayList<ClientInfo> clients;
      private boolean isRunning = true;
      private ServerSocket serverSocket;
      private int serverPort = 11111;
      private DataInputStream reader;
      private DataOutputStream writer;

      public ServerThread() throws SocketException{
            this.clients = new ArrayList<>();
            try{
                  this.serverSocket = new ServerSocket(this.serverPort);
            }
            catch(IOException e){
                  e.printStackTrace();
            }
            System.out.println("Server Created");
      }

      public void run(){
            Socket clientSocket = null;
            while(this.isRunning){
                  System.out.println("Server Started");
                  try{
                        clientSocket = serverSocket.accept();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  String command = "";
                  try{
                        this.reader = new DataInputStream(clientSocket.getInputStream());
                        this.writer = new DataOutputStream(clientSocket.getOutputStream());
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  try{
                        this.writer.writeUTF("Hello from server");
                        command = this.reader.readUTF();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  System.out.println(command);
                  if(command.startsWith("exit"))this.isRunning = false;
                  else if(command.startsWith("hello")){
                        float x = Float.parseFloat(command.substring(5,command.indexOf(",")));
                        float y = Float.parseFloat(command.substring(command.indexOf(",") + 1,command.indexOf(":")));
                        int dir = Integer.parseInt(command.substring(command.indexOf(":") + 1,command.length()));
                        clients.add(new ClientInfo(this.writer, x, y, dir));
                  }
            }
            try{
                  clientSocket.close();
                  this.serverSocket.close();
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      private class ClientInfo{
            private float x;
            private float y;
            private int dir;
            private DataOutputStream writer;

            private ClientInfo(DataOutputStream writer, float x, float y, int dir){
                  this.x = x;
                  this.y = y;
                  this.dir = dir;
                  this.writer = writer;
            }

            private float getX(){
                  return this.x;
            }

            private float getY(){
                  return this.y;
            }

            private void setX(float x){
                  this.x = x;
            }

            private void setY(float y){
                  this.y = y;
            }

            private void setDir(int dir){
                  this.dir = dir;
            }

            private int getDir(){
                  return this.dir;
            }
      }
}