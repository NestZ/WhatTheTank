package com.mystudio.wtt.utils;

public class ParseString{
      private ParseString(){}

      public static int parseID(String str, int start){
            return Integer.parseInt(str.substring(start, start + 1));
      }

      public static int parseDir(String str){
            return Integer.parseInt(str.substring(str.indexOf(":") + 1,str.indexOf(":") + 2));
      }

      public static float parseX(String str){
            return Float.parseFloat(str.substring(str.indexOf("x") + 1, str.indexOf("y")));
      }

      public static float parseY(String str){
            return Float.parseFloat(str.substring(str.indexOf("y") + 1, str.indexOf(":")));
      }
}