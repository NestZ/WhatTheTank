package com.mystudio.wtt.utils;

/**
 * Used to parse any package in to value.
 * 
 * @author NestZ 
 */

public class ParseString{
      /**
       * Keep private (don't let anyone instantiate this class).
       */
      private ParseString(){}

      /**
       * Parse package into ID.
       * @param str package (String)
       * @param start starting index of ID's character
       * 
       * @return ID (int)
       */
      public static int parseID(String str, int start){
            return Integer.parseInt(str.substring(start, start + 1));
      }

      /**
       * Parse package into face direction.
       * @param str package (String)
       * 
       * @return face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       */
      public static int parseDir(String str){
            return Integer.parseInt(str.substring(str.indexOf(":") + 1,str.indexOf(":") + 2));
      }

      /**
       * Parse package into x position (float).
       * @param str package (String)
       * 
       * @return x position
       */
      public static float parseX(String str){
            return Float.parseFloat(str.substring(str.indexOf("x") + 1, str.indexOf("y")));
      }

      /**
       * Parse package into y position (float).
       * @param str package (String)
       * 
       * @return y position
       */
      public static float parseY(String str){
            return Float.parseFloat(str.substring(str.indexOf("y") + 1, str.indexOf(":")));
      }
}