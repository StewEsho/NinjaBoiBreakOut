package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Character{
    protected Texture spritesheet; //all animations, etc
    protected TextureRegion sprite; //texture
    protected Vector2 coords; //stores x, y coordinates based on map grid
    protected Vector2 pixelCoords; //stores coords used for rendering on screen
    protected float rot; //rotation in degrees (0/360 is north, clockwise)
    protected float speed;
    protected int state; //stores the state of the charcter
    protected int animationFrame; //stores which frame of animation the charactrer is on

    public Character(int x, int y, String spritesheetPath){
        //clamp values based on map grid size
        x = Math.max(1, Math.min(Map.WIDTH-1, x));
        y = Math.max(1, Math.min(Map.HEIGHT-1, y));

        //set coordinates
        this.pixelCoords = new Vector2(x*64, y*64);
        this.coords = new Vector2(x, y);

        //set defaults
        this.rot = 0;
        this.speed = 250;
        Gdx.app.log("Character", "New entity spawned in at (" + this.coords.x + ", " + this.coords.y + ")");

        this.state = 0; //inital/idle
        this.animationFrame = 0;

        this.spritesheet = new Texture(spritesheetPath);
        this.sprite = new TextureRegion(this.spritesheet, this.animationFrame * 64, this.state * 64, 64, 64);
    }

    /**
     * Sets the coordinates of the character
     */

    public void setPixelCoords(float x, float y){
        x = Math.max(64, Math.min(Map.PIXELWIDTH-64, x));
        y = Math.max(64, Math.min(Map.PIXELHEIGHT-64, y));
        this.pixelCoords.set(x, y);
    }

    /**
     * Adds/subtracts character coordinates
     */

    protected void move(float deltaX, float deltaY){
        float x = this.pixelCoords.x + deltaX;
        float y = this.pixelCoords.y + deltaY;
        //clamp values
        x = Math.max(64, Math.min(Map.PIXELWIDTH-64, x));
        y = Math.max(64, Math.min(Map.PIXELHEIGHT-64, y));

        this.pixelCoords.set(x, y);
        this.coords.set(Math.round(x/64 - -0.5), Math.round(y/64 - -0.5));
    }

    /**
     * Returns the sprite of the
     * @return [description]
     */
    public TextureRegion getSprite(){ return this.sprite; }

    public float getPixelX() { return this.pixelCoords.x; }
    public float getPixelY() { return this.pixelCoords.y; }
    public Vector2 getPixelCoords() { return this.pixelCoords; }
}