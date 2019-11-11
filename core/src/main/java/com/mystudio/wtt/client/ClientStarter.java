package com.mystudio.wtt.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * This class is used to start client's thread then use this thread to connecting given ip and port.
 * 
 * @author NestZ
 */

public class ClientStarter {
      /**
       * Field to store current thread info.
       */
      private static boolean isReady = false;
      private static BufferedWriter writer;
      private ClientThread thread;
      private Socket clientSocket;

      /**
       * Constructor to initialize fields and make handshaking with server.
       * @param hostName set hostname (or IP)
       * @param serverPort set serverPort to connect
       * @param x client's initial x position
       * @param y client's initial y position
       * @param dir client's initial face direction
       * 
       * @throws IOException can not connect to server
       */
      public ClientStarter(String hostName)throws IOException{
            this.clientSocket = new Socket(hostName, 64740);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            try{
                  Thread.sleep(100);
                  writer.write(Protocol.helloPackage(0, 0, 0));
                  writer.flush();
                  Thread.sleep(500);
            }
            catch(InterruptedException e){
                  e.printStackTrace();
            }
            this.thread = new ClientThread(this.clientSocket);
            this.thread.start();
            this.thread.addToMap("c", 0, 0, 0, this.thread.getID());
      }

      /**
       * Method to send package to server.
       * @param command package (String).
       */
      public void sendToServer(String command){
            try{
                  writer.write(command);
                  writer.flush();
            }
            catch(IOException e){
                  System.out.println("Can not send to server");
            }
      }

      /**
       * Return current thread.
       * @return Current thread that communicating with server.
       */
      public ClientThread thread(){
            return this.thread;
      }

      /**
       * Set thread's status.
       * @param b true if connected to server otherwise false.
       */
      public static void isReady(boolean b){
            ClientStarter.isReady = b;
      }

      /**
       * Return thread's status.
       * @return true if connected to server otherwise return false.
       */
      public static boolean isReady(){
            return ClientStarter.isReady;
      }
}