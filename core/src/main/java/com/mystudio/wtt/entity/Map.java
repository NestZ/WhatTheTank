package com.mystudio.wtt.entity;

import java.util.ArrayList;
import java.util.HashMap;
import com.mystudio.wtt.utils.Point;

public class Map{
      private ArrayList<Point<Float>> blueTeam;
      private ArrayList<Point<Float>> redTeam;
      private int [][] wall = new int[60][33];
      private HashMap<Point<Integer>, Wall> map;
      private int blueCount;
      private int redCount;

      public Map(){
            this.blueCount = 0;
            this.redCount = 0;
            this.blueTeam = new ArrayList<>();
            this.redTeam = new ArrayList<>();
            this.blueTeam.add(new Point<>(100f, 100f));
            this.blueTeam.add(new Point<>(100f, 500f));
            this.redTeam.add(new Point<>(500f, 0f));
            this.redTeam.add(new Point<>(500f, 500f));
            for(int i = 0;i < 30;i++){
                  for(int j = 0;j < 17;j++){
                        if(i == 0 || i == 29 || j == 0 || j == 16){
                              wall[i][j] = 1;
                              this.map.put(new Point<Integer>(i, j), new Brick(i * 64, j * 64));
                        }
                        else{
                              wall[i][j] = 0;
                        }
                  }
            }
      }

      public Point<Float> getPos(int team){
            if(team == 1)return this.blueTeam.get(this.blueCount++);
            else return this.redTeam.get(this.redCount++);
      }

      public HashMap<Point<Integer>, Wall> getWallMap(){
            return this.map;
      }
}