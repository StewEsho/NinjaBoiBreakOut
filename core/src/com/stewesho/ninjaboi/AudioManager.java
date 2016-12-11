package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.utils.Array;

public class AudioManager{

    private Music song;
    private String songpath;
    private Array<Sound> sfxList;

    public AudioManager(String songpath){
        this.songpath = songpath;
        this.song = Gdx.audio.newMusic(Gdx.files.internal(this.songpath));
        this.song.setVolume(0.5f);
        this.song.setLooping(true);

        this.sfxList = new Array(8);
        this.sfxList.add(Gdx.audio.newSound(Gdx.files.internal("sfx/cool.wav"))); //0
        this.sfxList.add(Gdx.audio.newSound(Gdx.files.internal("sfx/shoot.wav"))); //1
        this.sfxList.add(Gdx.audio.newSound(Gdx.files.internal("sfx/hit.wav"))); //2
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

    /**
     * Methods to play sound effects
     */

    public void playSFX(int id){
        this.sfxList.get(id).play(1.0f);
    }

    public void playSFX(String path){
        Gdx.audio.newSound(Gdx.files.internal(path)).play(1.0f);
    }

    public void playSFX(int id, float vol){
        this.sfxList.get(id).play(vol);
    }

    public void playSFX(String path, float vol){
        Gdx.audio.newSound(Gdx.files.internal(path)).play(vol);
    }

}
