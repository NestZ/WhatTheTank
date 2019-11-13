package com.mystudio.wtt.entity;

import java.util.ArrayList;

public class Map{
      private ArrayList<Point> blueTeam;
      private ArrayList<Point> redTeam;
      private int blueCount;
      private int redCount;

      public Map(){
            this.blueCount = 0;
            this.redCount = 0;
            this.blueTeam = new ArrayList<>();
            this.redTeam = new ArrayList<>();
            this.blueTeam.add(new Point(0f, 0f));
            this.blueTeam.add(new Point(0, 500f));
            this.redTeam.add(new Point(500f, 0f));
            this.redTeam.add(new Point(500f, 500f));
      }

      public Point getPos(int team){
            if(team == 1)return this.blueTeam.get(this.blueCount++);
            else return this.redTeam.get(this.redCount++);
      }

      public class Point{
            private float x;
            private float y;

            public Point(){
                  this(0, 0);
            }

            private Point(float x, float y){
                  this.x = x;
                  this.y = y;
            }

            public void set(float x, float y){
                  this.x = x;
                  this.y = y;
            }

            public float getX(){
                  return this.x;
            }

            public float getY(){
                  return this.y;
            }
      }
}