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
                        int ID = ParseString.parseID(command, 6);
                        this.clientID = ID;
                        ClientStarter.isReady(true);
                  }
                  else if(command.startsWith("GETS")){
                        int n = ParseString.parseID(command, 4);
                        for(int i = 0;i < n;i++){
                              try{
                                    command = this.reader.readLine();
                              }
                              catch(IOException e){
                                    e.printStackTrace();
                              }
                              if(command.startsWith("GET" + i)){
                                    int ID = ParseString.parseID(command, 4);
                                    int dir = ParseString.parseDir(command);
                                    float x = ParseString.parseX(command);
                                    float y = ParseString.parseY(command);
                                    this.addToMap("c", x, y, dir, ID);
                              }
                        }
                  }
                  else if(command.startsWith("REG")){
                        int ID = ParseString.parseID(command, 3);
                        int dir = ParseString.parseDir(command);
                        float x = ParseString.parseX(command);
                        float y = ParseString.parseY(command);
                        this.addToMap("c", x, y, dir, ID);
                  }
                  else if(command.startsWith("Update")){
                        int ID = ParseString.parseID(command, 6);
                        char moveDir = command.charAt(7);
                        int status = ParseString.parseID(command, 8);
                        float x = ParseString.parseX(command);
                        float y = ParseString.parseY(command);
                        Tank tank = this.tanks.get(ID);
                        if(status == 0)tank.setPos(x, y, tank.getDir());
                        tank.key().set(moveDir, status);
                  }
            }
            try{
                  this.reader.close();
            }
            catch(IOException e){
                  e.printStackTrace();
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

      public HashMap<Integer, Tank> getTanks(){
            return this.tanks;
      }

      public void addToMap(String color, float x, float y, int dir, int ID){
            if(!this.tanks.containsKey(ID))this.tanks.put(ID, new Tank(color, x, y, dir, ID));
      }
}