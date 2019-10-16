package com.mystudio.wtt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ClientThread extends Thread {
      private boolean isRunning = true;
      private BufferedReader reader;
      private int clientID;
      private HashMap<Integer, Tank> tanks;
      
      public ClientThread(Socket clientSocket) throws SocketException{
            this.tanks = new HashMap<>();
            try{
                  this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }
            catch(IOException e){
                  e.printStackTrace();
            }
            System.out.println("Client Created");
      }

      public void run(){
            System.out.println("Client Started");
            while(this.isRunning){
                  String command = "";
                  try{
                        command = this.reader.readLine();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  if(command.startsWith("InitID")){
                        int ID = Integer.parseInt(command.substring(6, 7));
                        this.clientID = ID;
                        try{
                              command = this.reader.readLine();
                        }
                        catch(IOException e){
                              e.printStackTrace();
                        }
                        if(command.startsWith("GETS")){
                              int n = Integer.parseInt(command.substring(4, 5));
                              for(int i = 0;i < n;i++){
                                    try{
                                          command = this.reader.readLine();
                                    }
                                    catch(IOException e){
                                          e.printStackTrace();
                                    }
                                    if(command.startsWith("GET" + i)){
                                          int id = Integer.parseInt(command.substring(4, command.indexOf("x")));
                                          int dir = Integer.parseInt(command.substring(command.indexOf(":") + 1, command.indexOf(":") + 2));
                                          float x = Float.parseFloat(command.substring(command.indexOf("x") + 1, command.indexOf("y")));
                                          float y = Float.parseFloat(command.substring(command.indexOf("y") + 1, command.indexOf(":")));
                                          this.addToMap("color", x, y, dir, id);
                                    }
                              }
                        }
                  }
                  else if(command.startsWith("Update")){
                        int ID = Integer.parseInt(command.substring(6,7));
                        int dir = Integer.parseInt(command.substring(command.indexOf(":") + 1,command.indexOf(":") + 2));
                        float x = Float.parseFloat(command.substring(command.indexOf("x") + 1,command.indexOf("y")));
                        float y = Float.parseFloat(command.substring(command.indexOf("y") + 1,command.indexOf(":")));
                        tanks.get(ID).setPos(x, y, dir);
                  }
            }
      }

      public int getID(){
            return this.clientID;
      }

      public Tank getTank(int ID){
            return this.tanks.get(ID);
      }

      public int getClientSize(){
            return this.tanks.size();
      }

      public void addToMap(String color, float x, float y, int dir){
            this.tanks.put(this.getID(), new Tank(color, x, y, dir, this.getID()));
      }

      public void addToMap(String color, float x, float y, int dir, int ID){
            this.tanks.put(ID, new Tank(color, x, y, dir, ID));
      }
}