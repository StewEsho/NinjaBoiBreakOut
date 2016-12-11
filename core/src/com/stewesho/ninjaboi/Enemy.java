package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ENUMS
 */
enum State{
    IDLE, WALKING, DODGING, SHOOTING,
    TORCHING, RECOVERY, SLASHING, DEAD
}

////////////////////////////////////////////////////////////////////////////////

/**
 * class
 */
public class Enemy extends Character{

    private EnemyType type;
    private String spritesheetPath;

    private Texture spritesheet;
    private Sprite sprite;

    private boolean isDead;

    public Enemy(EnemyType id, int x, int y){
        super(x, y, "art/boi.png");
        this.type = id;
        this.spritesheetPath = "art/boi.png";
        this.spritesheet = new Texture(this.spritesheetPath);
        this.sprite = new Sprite(this.spritesheet, 0, this.type.getId()*64, 64, 64);
        this.sprite.setPosition(pixelCoords.x, pixelCoords.y);
        this.isDead = false;

        Gdx.app.log("enemy", this.type.getName() + " spawned at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public boolean isDead(){ return this.isDead; }


}
