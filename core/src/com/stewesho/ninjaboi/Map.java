package com.stewesho.ninjaboi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

enum Tile{
    ICE(0),
    FLOOR(1),
    WALL(2),
    TREASURE(3);
    //fields
    private final int id;
    private final Texture spritesheet;
    private final TextureRegion texture;
    private Body body;

    Tile (int id){
        this.id = id;
        this.spritesheet = new Texture("art/tiles.png");
        this.texture = new TextureRegion(this.spritesheet, this.id*64, 0, 64, 64);


    }

    public int getId(){ return this.id; }
    public TextureRegion getTexture(){ return this.texture; }
    public Body getBody() { return this.body; }
    public void setBody(Body b) {this.body = b;}
}

public class Map{
    private Tile[][] map;

    public static final int WIDTH  = 10;
    public static final int HEIGHT = 20;
    public static final int PIXELWIDTH  = WIDTH * 64;
    public static final int PIXELHEIGHT = HEIGHT * 64;

    public Map(){
        map = new Tile[WIDTH][HEIGHT];
        initMap();
    }

    protected void initMap(){
            for (int i = 0; i < WIDTH; i++){
                for (int j = 0; j < HEIGHT; j++){
                    try{
                        if ((i == WIDTH-1 || i == 0) || (j == HEIGHT-1 || j == 0) )
                            map[i][j] = Tile.WALL;
                        else{
                            map[i][j] = Tile.FLOOR;
                        }

                        if (map[i][j].getId() != 1)
                            map[i][j].setBody(NinjaBoiBreakOut.physicsManager.createWallBody(i , j));
                        else
                            map[i][j].setBody(null);
                    } catch (NullPointerException e){
                        Gdx.app.log("ERROR", "Map not initialized");
                    }
                }
            }

            map[3][8] = Tile.ICE;
            map[3][8].setBody(NinjaBoiBreakOut.physicsManager.createWallBody(3, 8));
        }

    public TextureRegion getTexture(int x, int y) {
        try{
            return this.map[x][y].getTexture();
        } catch (ArrayIndexOutOfBoundsException e){
            Gdx.app.log("ERROR", "Out of Bounds");
            throw e;
        }
    }

}
