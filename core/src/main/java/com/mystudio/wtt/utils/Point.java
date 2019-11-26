package com.mystudio.wtt.utils;

public class Point<T>{
      private T x;
      private T y;

      public Point(T x, T y){
            this.x = x;
            this.y = y;
      }

      public void set(T x, T y){
            this.x = x;
            this.y = y;
      }

      public T getX(){
            return this.x;
      }

      public T getY(){
            return this.y;
      }
}