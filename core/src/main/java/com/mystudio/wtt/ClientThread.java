package com.mystudio.wtt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
      private boolean isRunning = true;
      private Socket clientSocket;
      private DataInputStream reader;
      private DataOutputStream writer;
      
      public ClientThread(Socket clientSocket) throws SocketException{
            this.clientSocket = clientSocket;
            try{
                  this.reader = new DataInputStream(clientSocket.getInputStream());
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
                        command = reader.readUTF();
                  }
                  catch(IOException e){
                        e.printStackTrace();
                  }
                  if(command != "")System.out.println(command);
            }
      }
}