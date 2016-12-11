package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Shuriken{
    private Texture spritesheet;
    private Sprite sprite;
    private float angle;
    private float speed;
    private long lifespan; //seconds
    private long startTime;
    private long elapsedLifetime; //seconds
    private boolean isDead;

    public Shuriken(float x, float y, float angle){
        this.spritesheet = new Texture("art/weapons.png");
        this.sprite = new Sprite(this.spritesheet, 0, 0, 16, 16);
        this.sprite.setPosition(x, y);
        this.angle = angle;
        this.speed = 1000;
        this.lifespan = 3;
        this.startTime = System.currentTimeMillis();
        this.isDead = false;

        Gdx.app.log("shuriken", "spawned at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void move(){
        float deltaX = this.speed * MathUtils.cosDeg(this.angle) * Gdx.graphics.getDeltaTime();
        float deltaY = this.speed * MathUtils.sinDeg(this.angle) * Gdx.graphics.getDeltaTime();
        this.sprite.translate(deltaX, deltaY);

        this.elapsedLifetime = (System.currentTimeMillis() - this.startTime)/1000; //get time lasted
        if (this.elapsedLifetime > this.lifespan)
            this.isDead = true;
    }

    public boolean isDead(){ return isDead; }

}
