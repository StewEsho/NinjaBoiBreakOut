package com.stewesho.ninjaboi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NinjaBoiBreakOut extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Map map;

	@Override
	public void create () {
		batch = new SpriteBatch();
        map = new Map(); //level (just the one room)
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)0.1, (float)0.1, (float)0.1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 10; j++){
                batch.draw(map.getTexture(i, j), i*64, j*64);
            }
        }

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
