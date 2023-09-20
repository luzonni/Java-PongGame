package cene;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Map {

    private Rectangle bounds;

    public Player[] players;

    public Map(int width, int height) {
        bounds = new Rectangle(width, height);
        players = new Player[2];
        players[0] = new Player((int)(width*0.1), height/2);
        players[0].setKeys("W","S");
        players[1] = new Player((int)(width*0.9), height/2);
        players[1].setKeys("up","down");
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void tick() {
        for(int i = 0; i < players.length; i++) {
            players[i].tick();
        }
    }   
    
    public void render(Graphics g) {
        for(int i = 0; i < players.length; i++) {
            players[i].render(g);
        }
    }

}
