package com.mystudio.wtt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientStarter {
      public static boolean isReady = false;
      public ClientThread clientThread;
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
                  this.clientThread = new ClientThread(this.clientSocket);
                  this.clientThread.start();
                  this.clientThread.addToMap("c", x, y, dir, this.clientThread.getID());
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
}