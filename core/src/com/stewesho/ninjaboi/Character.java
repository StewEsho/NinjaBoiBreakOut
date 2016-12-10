package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Character{
    protected Texture spritesheet; //all animations, etc
    protected TextureRegion sprite; //texture
    protected Vector2 coords; //stores x, y coordinates
    protected float rot; //rotation in degrees (0/360 is north, clockwise)
    protected float speed;
    protected int state; //stores the state of the charcter
    protected int animationFrame; //stores which frame of animation the charactrer is on

    public Character(float x, float y, String spritesheetPath){
        x = Math.max(0, Math.min(1, Map.MAPWIDTH));
        y = Math.max(0, Math.min(1, Map.MAPHEIGHT));
        this.coords = new Vector2(x, y);
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

    public void setCoords(float x, float y){
        x = Math.max(0, Math.min(1, Map.MAPWIDTH));
        y = Math.max(0, Math.min(1, Map.MAPHEIGHT));
        this.coords.set(x, y);
    }

    /**
     * Adds/subtracts character coordinates
     */

    protected void move(float deltaX, float deltaY){
        float x = this.coords.x + deltaX;
        float y = this.coords.y + deltaY;
        //clamp values
        // x = Math.max(0, Math.min(1, Map.MAPWIDTH));
        // y = Math.max(0, Math.min(1, Map.MAPHEIGHT));

        this.coords.set(x, y);
    }

    /**
     * Returns the sprite of the
     * @return [description]
     */
    public TextureRegion getSprite(){ return this.sprite; }

    public float getX() { return this.coords.x; }
    public float getY() { return this.coords.y; }
    public Vector2 getCoords() { return this.coords; }
}
