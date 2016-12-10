package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

enum State{
    IDLE, WALKING, DODGING, SHOOTING,
    TORCHING, RECOVERY, SLASHING, DEAD
}

public class Player extends Character{

    private Vector2 mouseCoords;
    private float mouseRot;
    private Array<Shuriken> shuriken;
    private State state;

    public Player(int x, int y){
        super(x, y, "art/boi.png");
        state = State.IDLE;
        this.mouseCoords = new Vector2(0, 0);
        this.mouseRot = 0.0f;
        this.shuriken = new Array(true, 10);
    }

    /**
     * Runs in a loop
     * Used for player to control "Boi" himself
     */
    public void control(){
        state = state.IDLE;
        aim();
        move(); //wasd control

        //shoot (F key)
        if(Gdx.input.isButtonPressed(Buttons.LEFT))
            shoot();
    }

    public void shoot(){ //shoots out a shuriken
        state = state.SHOOTING;
        this.shuriken.add(new Shuriken(getPixelX(), getPixelY(), getRot() + 90));
    }

    public void move(){
        if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) //left
            move(Gdx.graphics.getDeltaTime() * -getSpeed(), 0);
            state = state.WALKING;
        if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) //right
            move(Gdx.graphics.getDeltaTime() * getSpeed(), 0);
            state = state.WALKING;
        if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.DPAD_UP)) //up
            move(0 , Gdx.graphics.getDeltaTime() * getSpeed());
            state = state.WALKING;
        if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) //down
            move(0 , Gdx.graphics.getDeltaTime() * -getSpeed());
            state = state.WALKING;
        getSprite().setPosition(getPixelX(), getPixelY());
    }

    public void aim(){
        //mouse control
        float mouseX = Gdx.input.getX() - ((float)Gdx.graphics.getWidth() / 2);
        float mouseY = -Gdx.input.getY() + ((float)Gdx.graphics.getHeight() / 2);
        this.mouseCoords.set(mouseX, mouseY);
        //get angle based on mouse pos; rotate boi
        this.mouseRot = MathUtils.atan2(mouseCoords.y, mouseCoords.x) * MathUtils.radiansToDegrees;
        this.setRot(this.mouseRot);
    }

    /**
     * GETTERS
     */
    public State getState() { return this.state; }
    public Array<Shuriken> getShurikens(){ return this.shuriken; }
}
