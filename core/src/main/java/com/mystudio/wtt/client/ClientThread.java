package com.mystudio.wtt.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.utils.ParseString;

/**
 * Client's main thread to communicate with server.
 * Should only created via ClientStarter.
 * 
 * @see com.com.mystudio.wtt.client.ClientStatrter
 * 
 * @author NestZ
 */

public class ClientThread extends Thread{
      /**
       * Field to store thread info (also client's cache data);
       */
      private boolean isRunning = true;
      private BufferedReader reader;
      public static int clientID = -1;
      private HashMap<Integer, Tank> tanks;
      public static int team = -1;
      
      /**
       * Constructor to set socket to connect.
       * @param clientSocket socket to connect
       * 
       * @throws SocketException error accesing given socket.
       */
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

      /**
       * Run this thread and paralelly recieve package from server.
       */
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
                        this.setInit(command);
                        //ClientStarter.isReady(true);
                  }
                  else if(command.startsWith("GETS")){
                        this.setOtherClient(command);
                  }
                  else if(command.startsWith("REG")){
                        this.registerClient(command);
                  }
                  else if(command.startsWith("Update")){
                        this.updateClient(command);
                  }
                  else if(command.startsWith("Shoot")){
                        this.clientShoot(command);
                  }
            }
            try{
                  this.reader.close();
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      /**
       * Parse package and call client's shoot method.
       * @param command package from server
       */
      public void clientShoot(String command){
            int ID = ParseString.parseID(command, 5);
            int dir = ParseString.parseDir(command);
            float x = ParseString.parseX(command);
            float y = ParseString.parseY(command);
            System.out.println("Client recieve shoot ID : " + ID);
            this.tanks.get(ID).shoot(dir, x, y);
      }

      /**
       * Parse package and update client's data (cache).
       * @param command package from server
       */
      public void updateClient(String command){
            int ID = ParseString.parseID(command, 6);
            char moveDir = command.charAt(7);
            int status = ParseString.parseID(command, 8);
            float x = ParseString.parseX(command);
            float y = ParseString.parseY(command);
            Tank tank = this.tanks.get(ID);
            if(status == 0)tank.setPos(x, y, tank.getDir());
            tank.key().set(moveDir, status);
      }

      /**
       * Parse package and add new client's data (cache).
       * @param command package from server
       */
      public void registerClient(String command){
            int ID = ParseString.parseID(command, 3);
            int dir = ParseString.parseDir(command);
            float x = ParseString.parseX(command);
            float y = ParseString.parseY(command);
            this.addToMap(x, y, dir, ID);
      }

      /**
       * Parse package and set all other client data.
       * @param command package from server
       */
      public void setOtherClient(String command){
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
                        // int dir = ParseString.parseDir(command);
                        // float x = ParseString.parseX(command);
                        // float y = ParseString.parseY(command);
                        //this.addToMap("c", x, y, dir, ID);
                  }
            }
      }

      /**
       * Parse package and set current client id.
       * @param command package from server
       */
      public void setInit(String command){
            clientID = ParseString.parseID(command, 4);
            team = ParseString.parseID(command, 5);
      }

      /**
       * Return tank that matched with id.
       * @param ID id of tank to return
       * 
       * @return tank that matched with id
       */
      public Tank getTank(int ID){
            return this.tanks.get(ID);
      }

      /**
       * Return current number of clients.
       * @return current client's size
       */
      public int getClientSize(){
            return this.tanks.size();
      }

      /**
       * Return tank's HashMap.
       * @return HashMap that store all client (tank) data
       */
      public HashMap<Integer, Tank> getTanks(){
            return this.tanks;
      }

      /**
       * Add tank to database (HashMap) if given id is exist.
       * @param color set tank's color
       * @param x tank's initial x position
       * @param y tank's initial y position
       * @param dir tank's initial face direction
       * @param ID set tank's ID
       */
      public void addToMap(float x, float y, int dir, int ID){
            //if(!this.tanks.containsKey(ID))this.tanks.put(ID, new Tank(x, y, dir, ID));
      }
}