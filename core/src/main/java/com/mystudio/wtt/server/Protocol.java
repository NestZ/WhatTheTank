package com.mystudio.wtt.server;

public class Protocol{
      private Protocol(){}

      public static String updatePackage(int ID, char moveDir, int status, float x, float y){
            return "Update" + Integer.toString(ID) + moveDir + Integer.toString(status) + "x" + Float.toString(x) + "y" + Float.toString(y) + ":\n";
      }

      public static String registerPackage(int ID, int dir, float x, float y){
            return "REG" + Integer.toString(ID) + "x" + Float.toString(x) + "y" + Float.toString(y) + ":" + Integer.toString(dir) + "\n";
      }

      public static String getsPackage(int size){
            return "GETS" + Integer.toString(size) + "\n";
      }

      public static String getPackage(int index, int ID, int dir, float x, float y){
            return "GET" + Integer.toString(index) + Integer.toString(ID) + "x" + Float.toString(x) +
                  "y" + Float.toString(y) + ":" + Integer.toString(dir) + "\n";
      }

      public static String initPackage(int ID){
            return "InitID" + Integer.toString(ID) + "\n";
      }

      /**
       * Parse client's id and face direction while shooting.
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