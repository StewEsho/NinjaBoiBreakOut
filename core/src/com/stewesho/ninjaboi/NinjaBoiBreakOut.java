package com.stewesho.ninjaboi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class NinjaBoiBreakOut extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Map map;
	Player player;
	OrthographicCamera cam;

	@Override
	public void create () {
		batch = new SpriteBatch();
        map = new Map(); //level (just the one room)
		player = new Player(2, 3);
		cam = new OrthographicCamera(800, 450);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)0.1, (float)0.1, (float)0.1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		player.control();
		cam.position.set(player.getPixelX(), player.getPixelY(), 0);
		cam.update();

		//render graphics
		batch.setProjectionMatrix(cam.combined);
		batch.begin();

        for (int i = Math.max(0, player.getX() - 10 ); i < Math.min(player.getX() + 10, map.WIDTH); i++){
            for (int j = Math.max(0, player.getY() - 5 );j < Math.min(player.getY() + 5, map.HEIGHT); j++){
                batch.draw(map.getTexture(i, j), i*64, j*64);
            }
        }

		batch.draw(player.getSprite(), player.getPixelX(), player.getPixelY());

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
