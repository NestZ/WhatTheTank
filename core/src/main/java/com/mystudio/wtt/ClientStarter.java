package com.mystudio.wtt;

import java.io.IOException;
import java.net.Socket;

public class ClientStarter{
      private ClientThread client;
      private Socket clientSocket;
      // public String hostName = "127.0.0.1";
      // public int serverPort = 11111;
      private ClientRegister clientRegister;

      public ClientStarter(String hostName, int serverPort, int x, int y, int dir){
            try{
                  try{
                        Thread.sleep(100);
                  }
                  catch(InterruptedException e){
                        e.printStackTrace();
                  }
                  this.clientRegister = ClientRegister.getInstance();
                  this.clientRegister.register(hostName, serverPort, x, y, dir);
                  try{
                        Thread.sleep(500);
                  }
                  catch(InterruptedException e){
                        e.printStackTrace();
                  }
                  this.clientSocket = new Socket(hostName, serverPort);
                  this.client = new ClientThread(this.clientSocket);
                  this.client.start();
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }
}