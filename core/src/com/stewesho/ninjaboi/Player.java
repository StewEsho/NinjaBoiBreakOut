package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Character{

    private Vector2 mouseCoords;
    private float mouseRot;

    public Player(int x, int y){
        super(x, y, "art/boi.png");
        this.mouseCoords = new Vector2(0, 0);
        this.mouseRot = 0.0f;
    }

    public void control(){
        //wasd control

        if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) //left
            move(Gdx.graphics.getDeltaTime() * -getSpeed(), 0);
        if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) //right
            move(Gdx.graphics.getDeltaTime() * getSpeed(), 0);
        if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.DPAD_UP)) //up
            move(0 , Gdx.graphics.getDeltaTime() * getSpeed());
        if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) //down
            move(0 , Gdx.graphics.getDeltaTime() * -getSpeed());
        getSprite().setPosition(getPixelX(), getPixelY());

        //mouse control
        float mouseX = Gdx.input.getX() - ((float)Gdx.graphics.getWidth() / 2);
        float mouseY = -Gdx.input.getY() + ((float)Gdx.graphics.getHeight() / 2);
        this.mouseCoords.set(mouseX, mouseY);
        //get angle based on mouse pos; rotate boi
        this.mouseRot = MathUtils.atan2(mouseCoords.y, mouseCoords.x) * MathUtils.radiansToDegrees;
        this.setRot(this.mouseRot);
    }
}
