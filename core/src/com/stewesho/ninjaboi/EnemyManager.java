package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * ENUMS
 */

 enum EnemyType{
     FLAMEO(1, "Flameo", 48, 48),
     PARKA(2, "Parka Boi", 64, 64);

     //fields
     private final int id;
     private final String name;
     private final float width;
     private final float length;

     EnemyType (int id, String name, float width, float length){
         this.id = id;
         this.name = name;
         this.width = width;
         this.length = length;
     }

     public int getId(){ return this.id; }
     public String getName(){ return this.name; }
     public float getWidth(){ return this.width; }
     public float getLength(){ return this.length; }
 }

////////////////////////////////////////////////////////////////////////////////

/**
 * class
 */
public class EnemyManager{

    protected Array<Enemy> enemyList;

    public EnemyManager(){
        this.enemyList = new Array(true, 16);
    }

    public void spawn(EnemyType type, int x, int y){
        this.enemyList.add(new Enemy(type, x, y));
    }

    public Array<Enemy> getEnemyList() { return enemyList; }
    public Enemy getEnemyList(int index) { return enemyList.get(index); }

}