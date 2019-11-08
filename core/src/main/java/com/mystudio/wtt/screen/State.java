package com.mystudio.wtt.screen;

import java.util.Stack;

public class State{
      public states currState;
      public enum states{
            MENU,
            OPTION
      }
      private Stack<states> stack;

      public void push(states s){
            stack.push(s);
      }

      public void pop(){
            stack.pop();
      }

      public states top(){
            return stack.lastElement();
      }
}