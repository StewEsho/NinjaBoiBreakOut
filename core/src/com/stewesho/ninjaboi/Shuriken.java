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

    public Shuriken(float x, float y, float angle){
        this.spritesheet = new Texture("art/weapons.png");
        this.sprite = new Sprite(this.spritesheet, 0, 0, 16, 16);
        this.sprite.setPosition(x, y);
        this.angle = angle;
        this.speed = 1000;

        Gdx.app.log("shuriken", "spawned at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void move(){
        float deltaX = this.speed * MathUtils.cosDeg(this.angle) * Gdx.graphics.getDeltaTime();
        float deltaY = this.speed * MathUtils.sinDeg(this.angle) * Gdx.graphics.getDeltaTime();
        this.sprite.translate(deltaX, deltaY);
    }

}
