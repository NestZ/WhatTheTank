package com.mystudio.wtt.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds{
    private static final String pewpew_sound_location = "pewpew.ogg";
    private static final String boom_sound_location = "boom.ogg";
    private static final String background_sound_location = "bgsound.ogg";

    Music backgroundMusic;
    {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(background_sound_location));
    }

    Sound boomsound;
    {
        boomsound = Gdx.audio.newSound(Gdx.files.internal(boom_sound_location));
    }
    Sound pewpewsound;
    {
        pewpewsound = Gdx.audio.newSound(Gdx.files.internal(pewpew_sound_location));
    }

    public void loopBackgroundMusic(){
        backgroundMusic.setVolume(0.25f);
        if(!backgroundMusic.isLooping()){
            backgroundMusic.play();
        }
        backgroundMusic.setLooping(true);
    }
    
    public void playshotsound (){
        pewpewsound.play(0.5f);
    }
    
    public void playboomsound (){
        boomsound.play(0.5f);
    }

    public void disposeBackgroundMusic(){
        backgroundMusic.dispose();
    }
}