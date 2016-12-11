package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager{

    private Music song;
    private String songpath;

    public AudioManager(String songpath){
        this.songpath = songpath;
        this.song = Gdx.audio.newMusic(Gdx.files.internal(this.songpath));
        this.song.setVolume(0.5f);
        this.song.setLooping(true);
    }

    public void setNewSong(String songpath){
        this.song.stop();
        this.songpath = songpath;
        this.song = Gdx.audio.newMusic(Gdx.files.internal(this.songpath));
        this.song.play();
    }

    public void stopSong(){ this.song.stop(); }
    public void playSong(){ this.song.play(); }
    public void pauseSong(){ this.song.pause(); }

    public void disposeSong(){
        this.song.dispose();
    }

}
