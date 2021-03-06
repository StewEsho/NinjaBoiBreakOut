package com.stewesho.ninjaboi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class NinjaBoiBreakOut extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static PhysicsManager physicsManager;
	public static AudioManager audio;
	public static EnemyManager eMan;
    Map map;
	Player player;
	OrthographicCamera cam;
	BitmapFont pointsText;
	BitmapFont hpText;

	Sprite logo;
	long currentTime;
	Pixmap cursorIcon;

	@Override
	public void create () {
		this.audio = new AudioManager("music/maintheme.wav");
		this.eMan = new EnemyManager();
		this.batch = new SpriteBatch();
		this.physicsManager = new PhysicsManager();
  		this.map = new Map(); //level (just the one room)
		this.player = new Player(Map.WIDTH/2, Map.HEIGHT/2);
		this.cam = new OrthographicCamera(800, 450);
		this.pointsText = new BitmapFont(Gdx.files.internal("font/KomikaAxis.fnt"), Gdx.files.internal("font/KomikaAxis.png"), false);
		this.hpText = new BitmapFont(Gdx.files.internal("font/KomikaAxis.fnt"), Gdx.files.internal("font/KomikaAxis.png"), false);

		this.logo = new Sprite(new Texture("art/logo.png"));
		this.logo.setSize(256, 256);
		this.logo.setPosition(Map.PIXELWIDTH/2 - this.logo.getWidth()/2, Map.PIXELHEIGHT/2 - this.logo.getHeight()/2);

		audio.stopSong();
		audio.playSong();
		this.currentTime = 0;

		cursorIcon = new Pixmap(Gdx.files.internal("art/cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorIcon, 0, 0));
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



		if(player.getHP() <= 0){

			//kill shurikens
			int index = 0;
			for (Shuriken s : player.getShurikens()){
				s.killBody();
				player.getShurikens().removeIndex(index);
				index++;
			}
			//kill enemies
			index = 0;
			for (Enemy e : eMan.getEnemyList()){
				e.killBody();
				eMan.getEnemyList().removeIndex(index);
				index++;
			}

			audio.stopSong();

			this.create();
			this.currentTime = System.currentTimeMillis();

		} else if (System.currentTimeMillis() - this.currentTime >= 200) {

			batch.begin(); /////////////////////////////////

			for (int i = Math.max(0, player.getX() - 10 ); i < Math.min(player.getX() + 10, map.WIDTH); i++){
				for (int j = Math.max(0, player.getY() - 5 );j < Math.min(player.getY() + 5, map.HEIGHT); j++){
					batch.draw(map.getTexture(i, j), i*64, j*64);
				}
			}

			//spawns enemies
			eMan.spawnCycle();
			this.logo.draw(this.batch);

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
					e.killBody();
					eMan.getEnemyList().removeIndex(index);
				}
				index++;
			}

			//draws the player
			player.getSprite().draw(batch);

			this.pointsText.setColor(0.631f, 0.780f, 0.807f, 1.0f);
			this.pointsText.draw(batch, "Targets Terminated: " + player.getPoints(), player.getPixelX() - 350, player.getPixelY() - 150);

			this.pointsText.setColor(0.631f, 0.780f, 0.807f, 1.0f);
			this.pointsText.draw(batch, "HP: " + player.getHP(), player.getPixelX(), player.getPixelY() - 150);
			batch.end(); /////////////////////////////////

			//run physics world step
			physicsManager.run(cam, player.getShurikens(), eMan.getEnemyList());
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		audio.disposeSong();
	}
}
