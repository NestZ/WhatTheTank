package com.mystudio.wtt.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import com.mystudio.wtt.WhatTheTank;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.screen.Lobby;
import com.mystudio.wtt.utils.ParseString;

/**
 * This class is used to start client's thread then use this thread to connecting given ip and port.
 * 
 * @author NestZ
 */

public class ClientStarter extends Thread{
      /**
       * Field to store current thread info.
       */
      private static BufferedWriter writer;
      private Socket clientSocket;
      private BufferedReader reader;
      private static int clientID = -1;
      public static int team = -1;

      /**
       * Constructor to initialize fields and make handshaking with server.
       * @param hostName set hostname (or IP)
       * 
       * @throws IOException can not connect to server
       */
      public ClientStarter(String hostName)throws IOException{
            //this.tanks = new HashMap<>();
            this.clientSocket = new Socket(hostName, 64740);
            ClientStarter.writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Client Created");
      }

      /**
       * Method to send package to server.
       * @param command package (String).
       */
      public static void sendToServer(String command){
            try{
                  writer.write(command);
                  writer.flush();
            }
            catch(IOException e){
                  System.out.println("Can not send to server");
            }
      }

      @Override
      public void run(){
            System.out.println("Client Started");
            sendToServer(Protocol.helloPackage(Lobby.myName));
            while(true){
                  String command = "";
                  try{
                        command = this.reader.readLine();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  if(command.startsWith("Init")){
                        this.setInit(command);
                  }
                  else if(command.startsWith("GETS")){
                        this.addOtherClient(command);
                  }
                  else if(command.startsWith("REG")){
                        this.clientRegistered(command);
                  }
                  else if(command.startsWith("Update")){
                        this.updatePackage(command);
                  }
                  else if(command.startsWith("Start")){
                        Lobby.isStart = true;
                  }
                  else if(command.startsWith("Shoot")){
                        this.clientShoot(command);
                  }
            }
            // try{
            //       this.reader.close();
            // }
            // catch(IOException e){
            //       e.printStackTrace();
            // }
      }

      public void clientShoot(String command){
            int ID = ParseString.parseID(command, 5);
            int dir = ParseString.parseDir(command);
            float x = ParseString.parseX(command);
            float y = ParseString.parseY(command);
            Tank tank = WhatTheTank.tanks.get(ID);
            tank.shoot(dir, x, y);
            System.out.println("Client recieve shoot ID : " + ID);
      }

      public void updatePackage(String command){
            int ID = ParseString.parseID(command, 6);
            char moveDir = command.charAt(7);
            int status = ParseString.parseID(command, 8);
            float x = ParseString.parseX(command);
            float y = ParseString.parseY(command);
            Tank tank = WhatTheTank.tanks.get(ID);
            if(status == 0)tank.setPos(x, y, tank.getDir());
            tank.key().set(moveDir, status);
      }

      public void setInit(String command){
            ClientStarter.clientID = ParseString.parseID(command, 4);
            ClientStarter.team = ParseString.parseID(command, 5);
            Lobby.addMember(Lobby.myName, ClientStarter.team, ClientStarter.clientID);
      }

      public void addOtherClient(String command){
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
                        int team = ParseString.parseID(command, 5);
                        String name = command.substring(5, command.indexOf(":"));
                        Lobby.addMember(name, team, ID);
                  }
            }
      }

      public void clientRegistered(String command){
            int ID = ParseString.parseID(command, 3);
            int team = ParseString.parseID(command, 4);
            String name = command.substring(4, command.indexOf(":"));
            Lobby.addMember(name, team, ID);
      }

      public static int clientID()throws IOException{
            if(ClientStarter.clientID == -1){
                  throw new IOException("Not recieved client ID");
            }
            else return ClientStarter.clientID;
      }
}