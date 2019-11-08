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
}