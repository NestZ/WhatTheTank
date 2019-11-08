package com.mystudio.wtt.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientStarter {
      private static boolean isReady = false;
      private ClientThread thread;
      private Socket clientSocket;
      private static BufferedWriter writer;

      public ClientStarter(String hostName, int serverPort, int x, int y, int dir){
            try{
                  this.clientSocket = new Socket(hostName, serverPort);
                  writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                  try{
                        Thread.sleep(100);
                        writer.write("Hello" + "x" + x + "y" + y + ":" + dir + "\n");
                        writer.flush();
                        Thread.sleep(500);
                  }
                  catch(InterruptedException e){
                        e.printStackTrace();
                  }
                  this.thread = new ClientThread(this.clientSocket);
                  this.thread.start();
                  this.thread.addToMap("c", x, y, dir, this.thread.getID());
            }
            catch(IOException e){
                  e.printStackTrace();
            }
      }

      public void sendToServer(String command){
            try{
                  writer.write(command + "\n");
                  writer.flush();
            }
            catch(IOException e){
                  System.out.println("Can not send to server");
            }
      }

      public ClientThread thread(){
            return this.thread;
      }

      public static void isReady(boolean b){
            ClientStarter.isReady = b;
      }

      public static boolean isReady(){
            return ClientStarter.isReady;
      }
}