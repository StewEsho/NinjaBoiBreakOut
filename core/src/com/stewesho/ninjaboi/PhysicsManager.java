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
import com.badlogic.gdx.utils.Array;


public class PhysicsManager {
    public World world;
    public Box2DDebugRenderer debugRenderer;

    public PhysicsManager(){
        Box2D.init();
        this.world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        createCollisionListener();
    }

    public void run(OrthographicCamera cam, Array<Shuriken> shurikenList, Array<Enemy> enemyList){
        this.world.step(1/45f, 6, 2);
        // this.debugRenderer.render(world, cam.combined);

    }

    public Body createShurikenBody(float x, float y){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DynamicBody;
        bd.position.set(x, y);

        Body shurikenBody = world.createBody(bd);
        shurikenBody.setUserData("shuriken");

        CircleShape shape = new CircleShape();
        shape.setRadius(8f);

        FixtureDef shuriken = new FixtureDef();
        shuriken.shape = shape;
        shuriken.isSensor = true;
        Fixture fixture = shurikenBody.createFixture(shuriken);

        shape.dispose();

        return shurikenBody;
    }

    public Body createPlayerBody(int x, int y){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DynamicBody;
        bd.position.set(((float) x * 64) + 32, ((float) y * 64) + 32);

        Body playerBody = world.createBody(bd);
        playerBody.setUserData("player");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 8);

        FixtureDef player = new FixtureDef();
        player.shape = shape;
        Fixture fixture = playerBody.createFixture(player);

        shape.dispose();

        return playerBody;
    }

    public Body createEnemyBody(float x, float y, float width, float length){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DynamicBody;
        bd.position.set(x, y);

        Body enemyBody = world.createBody(bd);
        enemyBody.setUserData("enemy");


        CircleShape shape = new CircleShape();
        shape.setRadius(width/2);

        FixtureDef enemy = new FixtureDef();
        enemy.shape = shape;
        Fixture fixture = enemyBody.createFixture(enemy);

        shape.dispose();

        return enemyBody;
    }

    public Body createWallBody(int x, int y){
        BodyDef bd = new BodyDef();
        bd.type = BodyType.StaticBody;
        bd.position.set(((float) x * 64) + 32, ((float) y * 64) + 32);

        Body wallBody = world.createBody(bd);
        wallBody.setUserData("wall");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32f, 32f);

        FixtureDef wall = new FixtureDef();
        wall.shape = shape;
        Fixture fixture = wallBody.createFixture(wall);

        shape.dispose();

        return wallBody;
    }

    private void createCollisionListener() {
        this.world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                if((contact.getFixtureA().getBody().getUserData()=="shuriken" && contact.getFixtureB().getBody().getUserData()=="enemy")
                || (contact.getFixtureA().getBody().getUserData()=="enemy" && contact.getFixtureB().getBody().getUserData()=="shuriken")){
                    Player.addPoints();
                    contact.getFixtureA().getBody().setUserData("dead");
                    contact.getFixtureB().getBody().setUserData("dead");
                }

            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}

        });
    }

    public World getWorld(){ return world; }

}
