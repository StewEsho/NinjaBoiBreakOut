package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

enum Tile{
    ICE(0),
    WOOD(1),
    WALL(2),
    TREASURE(3);
    //fields
    private final int id;
    private final Texture spritesheet;
    private final TextureRegion texture;

    Tile (int id){
        this.id = id;
        this.spritesheet = new Texture("art/tiles.png");
        this.texture = new TextureRegion(this.spritesheet, this.id*64, 0, 64, 64);
    }

    public int getId(){ return this.id; }
    public TextureRegion getTexture(){ return this.texture; }
}

public class Map{
    private Tile[][] map;

    public static final int MAPWIDTH  = 523;
    public static final int MAPHEIGHT = 523;

    public Map(){
        map = new Tile[MAPWIDTH][MAPWIDTH];
        initMap();
    }

    protected void initMap(){
            for (int i = 0; i < MAPWIDTH; i++){
                for (int j = 0; j < MAPHEIGHT; j++){
                    try{
                        switch ((i+j) % 4){
                        case 0:
                            map[i][j] = Tile.ICE;
                            break;
                        case 1:
                            map[i][j] = Tile.WOOD;
                            break;
                        case 2:
                            map[i][j] = Tile.WALL;
                            break;
                        case 3:
                            map[i][j] = Tile.TREASURE;
                            break;
                        }
                    } catch (NullPointerException e){
                        Gdx.app.log("ERROR", "Map not initialized");
                    }
                }
            }
        }

    public TextureRegion getTexture(int x, int y) {
        try{
            return this.map[x][y].getTexture();
        } catch (NullPointerException e){
            Gdx.app.log("ERROR", "Map not initialized");
            return null;
        }
    }

}
