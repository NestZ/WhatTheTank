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

      @Override
      public int hashCode(){
            int x = (Integer)(this.x);
            int y = (Integer)(this.y);
            if(x < 0)x = 100;
            if(y < 0)y = 100;
            String s = Integer.toString(x * 100) + Integer.toString(y);
            return Integer.parseInt(s);
      }

      @Override
      public boolean equals(Object o){
            if(this.hashCode() == o.hashCode())return true;
            return false;
      }
}