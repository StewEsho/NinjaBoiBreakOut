package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;


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

    private Body body; //physics body for collision

    //kinematics fields
    private float vel;
    private float acc;
    //rot is in Character
    private float rotVel;
    private float rotAcc;


    public Enemy(EnemyType id, int x, int y){
        super(x, y, "art/boi.png");
        this.type = id;
        this.spritesheetPath = "art/boi.png";
        this.spritesheet = new Texture(this.spritesheetPath);
        this.sprite = new Sprite(this.spritesheet, 0, this.type.getId()*64, 64, 64);
        this.sprite.setPosition(pixelCoords.x, pixelCoords.y);
        this.isDead = false;

        //inits kinematics fields
        this.vel = 0;
        this.acc = MathUtils.random(10, 25);
        this.rot = MathUtils.random(0, 360);
        this.rotVel = 2;
        this.rotAcc = 5;

        //inits body
        this.body = NinjaBoiBreakOut.physicsManager.createEnemyBody(pixelCoords.x+32, pixelCoords.y+32, this.type.getWidth(), this.type.getLength());

        Gdx.app.log("enemy", this.type.getName() + " spawned at (" + x + ", " + y + ")");
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public void runAI(){
        //run kinematics
        this.delta = Gdx.graphics.getDeltaTime(); //set delta time

        this.vel += this.acc * this.delta;
        float deltaY = vel * MathUtils.sinDeg(this.rot) * this.delta;
        float deltaX = vel * MathUtils.cosDeg(this.rot) * this.delta;
        this.move(deltaX, deltaY);

        this.rotVel += this.rotAcc*this.delta;
        this.rot += this.rotVel*this.delta;

        this.sprite.setRotation(this.rot - 90);
        this.sprite.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        this.sprite.setPosition(pixelCoords.x, pixelCoords.y);

        this.body.setTransform(pixelCoords.x+32, pixelCoords.y+32, 0);
    }

    public boolean isDead(){ return this.isDead; }


}
