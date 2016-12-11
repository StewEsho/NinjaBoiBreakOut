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
     FLAMEO(1, "Flameo"),
     PARKA(2, "Parka Boi");

     //fields
     private final int id;
     private final String name;

     EnemyType (int id, String name){
         this.id = id;
         this.name = name;
     }

     public int getId(){ return this.id; }
     public String getName(){ return this.name; }
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
