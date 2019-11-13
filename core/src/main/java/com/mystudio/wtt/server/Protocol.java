package com.mystudio.wtt.server;

/**
 * Server's package generator.
 * Used to generate dstring that server need to send to client.
 * 
 * @author NestZ
 */

public class Protocol{
      /**
       * Keep private (don't let anyone instantiate this class).
       */
      private Protocol(){}

      /**
       * Packaging client's new information into string. 
       * @param ID client's ID
       * @param moveDir client's moving direction
       * @param status client's key status
       * 0 : key up
       * 1 : key down
       * @param x client's current x position
       * @param y client's current y position
       * 
       * @return string that include client's new information
       */
      public static String updatePackage(int ID, char moveDir, int status, float x, float y){
            return "Update" + Integer.toString(ID) + moveDir + Integer.toString(status) + "x" + Float.toString(x) + "y" + Float.toString(y) + ":\n";
      }

      /**
       * Packaging new client's information into string.
       * @param ID new client's ID
       * 
       * @return string that include new client's information
       */
      public static String registerPackage(int ID, int team, String name){
            return "REG" + Integer.toString(ID) + Integer.toString(team) + name + ":\n";
      }

      /**
       * Packaging initial package before send other client's information to particular client.
       * @param size size of all other clients
       * 
       * @return string that include size of all other client
       */
      public static String getsPackage(int size){
            return "GETS" + Integer.toString(size) + "\n";
      }

      /**
       * Packaging each client package into string.
       * @param index index of current package
       * @param ID current packaging client's ID
       * 
       * @return string that include information of each client
       */
      public static String getPackage(int index, int ID, int team, String name){
            return "GET" + Integer.toString(index) + Integer.toString(ID) + Integer.toString(team) + name + ":\n";
      }

      /**
       * Packaging new client's ID into string. 
       * @param ID new client's ID
       * 
       * @return string that include new client's ID
       */
      public static String initPackage(int team, int ID, String name){
            return "Init" + Integer.toString(ID) + Integer.toString(team) + name + ":\n";
      }

      /**
       * Packaging client's id and face direction while shooting.
       * @param ID tank's id
       * @param dir tank's face direction
       * @param x tank's x position
       * @param y tank's y position
       * 
       * @return shooting package
       */
      public static String shootPackage(int ID, int dir, float x, float y){
            return "Shoot" + Integer.toString(ID) + "x" + x + "y" + y + ":" + Integer.toString(dir) + "\n";
      }
}