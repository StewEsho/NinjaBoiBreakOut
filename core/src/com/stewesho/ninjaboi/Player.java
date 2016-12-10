package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;

public class Player extends Character{

    private Vector2 mouseCoords;

    public Player(int x, int y){
        super(x, y, "art/boi.png");
        this.mouseCoords = new Vector2(0, 0);
    }

    public void control(){
        //wasd control

        if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) //left
            move(Gdx.graphics.getDeltaTime() * -this.speed, 0);
        if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) //right
            move(Gdx.graphics.getDeltaTime() * this.speed, 0);
        if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.DPAD_UP)) //up
            move(0 , Gdx.graphics.getDeltaTime() * this.speed);
        if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) //down
            move(0 , Gdx.graphics.getDeltaTime() * -this.speed);

        //mouse control
        this.mouseCoords.set(Gdx.input.getX(), Gdx.input.getY());
    }
}
