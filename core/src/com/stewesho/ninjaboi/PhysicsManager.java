package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


public class PhysicsManager{
    public World world;
    public Box2DDebugRenderer debugRenderer;

    public PhysicsManager(){
        Box2D.init();
        this.world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void run(OrthographicCamera cam){
        this.world.step(1/45f, 6, 2);
        this.debugRenderer.render(world, cam.combined);
    }

    public Body createShurikenBody(float x, float y){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.KinematicBody;
        bd.position.set(x, y);

        Body shurikenBody = world.createBody(bd);

        CircleShape shape = new CircleShape();
        shape.setRadius(8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        Fixture fixture = shurikenBody.createFixture(fixtureDef);

        shape.dispose();

        return shurikenBody;
    }

    public Body createEnemyBody(float x, float y, float width, float length){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.KinematicBody;
        bd.position.set(x, y);

        Body enemyBody = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, length/2, new Vector2(width/2, length), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        Fixture fixture = enemyBody.createFixture(fixtureDef);

        shape.dispose();

        return enemyBody;
    }

    public World getWorld(){ return world; }

}
