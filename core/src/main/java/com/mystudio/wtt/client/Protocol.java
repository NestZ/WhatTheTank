package com.mystudio.wtt.client;

/**
 * Client's package generator.
 * Used to generate string that client need to send to server.
 * 
 * @author NestZ
 */

public class Protocol{
      /**
       * Keep private (don't let anyone instantiate this class).
       */
      private Protocol(){}
      
      /**
       * Parse client's new information and send to server.
       * @param moveDir current tank's face direction
       * @param status current client's key status
       * (true if key down otherwise false)
       * 
       * @param ID client's id
       * @param x current tank's x position
       * @param y current tank's y position
       * 
       * @return package that ready to send to server
       */
      public static String updatePackage(char moveDir, int status, int ID, float x, float y){
            return "Update" + Integer.toString(ID) + moveDir + Integer.toString(status) + "x" + Float.toString(x) + "y" + Float.toString(y) + ":\n";
      }

      /**
       * Parse new client's information and handshaking with server.
       * @param dir tank's initial face direction 
       * @param x tank's initial x position
       * @param y tank's initial y position
       * 
       * @return package that ready to used to handshaking
       */
      public static String helloPackage(int dir, float x, float y){
            return "Hello" + "x" + Float.toString(x) + "y" + Float.toString(y) + ":" + Integer.toString(dir) + "\n";
      }

      /**
       * Parse client's id and face direction while shooting.
       * @param ID tank's id
       * @param dir tank's face direction
       * 
       * @return shooting package
       */
      public static String shootPackage(int ID, int dir, float x, float y){
            return "Shoot" + Integer.toString(ID) + "x" + Float.toString(x) + "y" + Float.toString(y) + ":" + Integer.toString(dir) + "\n";
      }
}