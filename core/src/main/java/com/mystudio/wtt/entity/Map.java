package com.mystudio.wtt.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import com.mystudio.wtt.utils.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map{
      private File file;
      private ArrayList<Point<Float>> blueTeam;
      private ArrayList<Point<Float>> redTeam;
      private HashMap<Point<Integer>, Tiles> land;
      private HashMap<Point<Integer>, Tiles> floating;
      private ConcurrentHashMap<Point<Integer>, Tiles> brick;
      private int blueCount;
      private int redCount;

      public Map(){
            this.blueCount = 0;
            this.redCount = 0;
            this.blueTeam = new ArrayList<>();
            this.redTeam = new ArrayList<>();
            this.blueTeam.add(new Point<>(90f, 256f));
            this.blueTeam.add(new Point<>(90f, 832f));
            this.redTeam.add(new Point<>(1788f, 256f));
            this.redTeam.add(new Point<>(1788f, 832f));
            this.brick = new ConcurrentHashMap<>();
            this.land = new HashMap<>();
            this.floating = new HashMap<>();
            this.readMapFile("map01.txt");
      }

      public void readMapFile(String name){
            this.file = new File(name);
            try{
                  Scanner sc = new Scanner(this.file);
                  for(int k = 0;k < 2;k++){
                        sc.next();
                        for(int i = 0;i < 17;i++){
                              for(int j = 0;j < 30;j++){
                                    switch(sc.next().charAt(0)){
                                          case '.' :
                                                this.land.put(new Point<Integer>(i , j), Tiles.getInstance('.', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '0' :
                                                this.brick.put(new Point<Integer>(i , j), Tiles.getInstance('0', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '*' :
                                                this.floating.put(new Point<Integer>(i, j), Tiles.getInstance('*', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '2' :
                                                this.brick.put(new Point<Integer>(i, j), Tiles.getInstance('2', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '1' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('1', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '3' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('3', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '4' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('4', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '5' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('5', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '6' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('6', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '7' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('7', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '8' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('8', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '9' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('9', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '/' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('/', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '\\' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('\\', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '_' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('_', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '#' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('#', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '$' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('$', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '@' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('@', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '+' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('+', new Point<>(j * 64, i * 64)));
                                                break;
                                          case '%' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('%', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'a' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('a', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'b' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('b', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'c' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('c', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'd' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('d', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'e' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('e', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'f' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('f', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'g' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('g', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'h' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('h', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'i' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('i', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'j' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('j', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'k' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('k', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'l' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('l', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'm' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('m', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'n' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('n', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'o' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('o', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'p' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('p', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'q' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('q', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 'r' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('r', new Point<>(j * 64, i * 64)));
                                                break;
                                          case 's' :
                                                this.land.put(new Point<Integer>(i, j), Tiles.getInstance('s', new Point<>(j * 64, i * 64)));
                                                break;
                                    };
                              }
                        }
                  }
                  sc.close();
            }
            catch(FileNotFoundException e){
                  e.printStackTrace();
            }
      }

      public Point<Float> getPos(int team){
            if(team == 1)return this.blueTeam.get(this.blueCount++);
            else return this.redTeam.get(this.redCount++);
      }

      public HashMap<Point<Integer>, Tiles> getLand(){
            return this.land;
      }

      public HashMap<Point<Integer>, Tiles> getFloating(){
            return this.floating;
      }

      public ConcurrentHashMap<Point<Integer>, Tiles> getBrick(){
            return this.brick;
      }
}