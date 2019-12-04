package com.mystudio.wtt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * Singleton
 */
public class Singleton {

    public static final Singleton instance = new Singleton();
    public int count = 0;
    public float[] durations = new float[150];
    Texture[] image;

    private Singleton(){
    }

    public void loadImage() {
        if(image != null)return;
        image = new Texture[150];
            for (int i = 0; i < 150; i++) {
                image[count] = new Texture(Gdx.files.internal("Layer " + (count+1) + ".jpg"));
                count++;
                System.out.println("Load image " + count);
            }
            
    }

    public void setCount(int x) {
        this.count = x;
    }
    public Texture[] getImage(){
        if(image == null)loadImage();
        return image;
    }
    
    public void setDurations() {
        this.setDurations(0.04f);
    }
    public void setDurations(float a) {
        for (int i = 0; i < 150; i++) {
            this.durations[i] = a;
        }
    }
}

