package com.stewesho.ninjaboi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class NinjaBoiBreakOut extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static PhysicsManager physicsManager;
	public static AudioManager audio;
	public static EnemyManager eMan;
    Map map;
	Player player;
	OrthographicCamera cam;

	@Override
	public void create () {
		audio = new AudioManager("music/maintheme.wav");
		eMan = new EnemyManager();
		batch = new SpriteBatch();
		physicsManager = new PhysicsManager();
        map = new Map(); //level (just the one room)
		player = new Player(2, 3);
		cam = new OrthographicCamera(800, 450);

		audio.playSong();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)0.1, (float)0.1, (float)0.1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		player.control();
		cam.position.set(player.getPixelX()+32, player.getPixelY()+32, 0);
		cam.update();

		//render graphics
		batch.setProjectionMatrix(cam.combined);

		batch.begin(); /////////////////////////////////

        for (int i = Math.max(0, player.getX() - 10 ); i < Math.min(player.getX() + 10, map.WIDTH); i++){
            for (int j = Math.max(0, player.getY() - 5 );j < Math.min(player.getY() + 5, map.HEIGHT); j++){
                batch.draw(map.getTexture(i, j), i*64, j*64);
            }
        }

		//draws the player
		player.getSprite().draw(batch);
		//draw shurikens
		int index = 0;
		for (Shuriken s : player.getShurikens()){
			s.move();
			s.draw(this.batch);
			if (s.isDead()){
				s.killBody();
				player.getShurikens().removeIndex(index);
			}
			index++;
		}
		//draw enemies
		index = 0;
		for (Enemy e : eMan.getEnemyList()){
			e.runAI();
			e.draw(this.batch);
			if (e.isDead()){
				//e.killBody();
				eMan.getEnemyList().removeIndex(index);
			}
			index++;
		}

		batch.end(); /////////////////////////////////

		//run physics world step
		physicsManager.run(cam);

	}

	@Override
	public void dispose () {
		batch.dispose();
		audio.disposeSong();
	}
}
