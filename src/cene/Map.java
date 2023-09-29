package cene;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Main;

public class Map {

    private Rectangle bounds;

    public Player[] players;
    private Rectangle[] playerAreas;
    public Ball ball;

    public Map(int width, int height) {
        float areaSizePlayer = 0.1f;
        bounds = new Rectangle(width, height);
        players = new Player[2];
        playerAreas = new Rectangle[2];
        players[0] = new Player((int)(width*areaSizePlayer), height/2);
        players[0].setKeys("W","S");
        playerAreas[0] = new Rectangle(0, 0, (int)(width*areaSizePlayer), height);
        players[1] = new Player((int)(width*(1-areaSizePlayer)), height/2);
        players[1].setKeys("up","down");
        playerAreas[1] = new Rectangle(width - (int)(width*areaSizePlayer), 0, (int)(width*areaSizePlayer), height);
        ball = new Ball(width/2, height/2);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void tick() {
        for(int i = 0; i < players.length; i++) {
            players[i].tick();
        }
        ball.tick(players);
        checkWin();
    }   

    private void checkWin() {
        if(ball.getBounds().getX() < players[0].getBounds().getX()) {
            players[0].addPoint(1);
            this.ball = new Ball(Main.Width()/2, Main.Height()/2);
        }
         if(ball.getBounds().getX() > players[1].getBounds().getX() + players[1].getBounds().getWidth()) {
            players[1].addPoint(1);
            this.ball = new Ball(Main.Width()/2, Main.Height()/2);
        }
    }
    
    public void render(Graphics g) {
        g.setColor(new Color(240, 70, 58));
        mapTexture(g);
        for(int i = 0; i < players.length; i++) {
            g.fillRect(playerAreas[i].x, playerAreas[i].y, playerAreas[i].width, playerAreas[i].height);
        } 
        for(int i = 0; i < players.length; i++) {
            players[i].render(g);
        }
        ball.render(g);
    }

    private void mapTexture(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(Main.Width()/2, 0, Main.Width()/2, Main.Height());
        int ovalSize = (int)(Main.Width()*0.1);
        g.drawOval(Main.Width()/2 - ovalSize/2, Main.Height()/2 - ovalSize/2, ovalSize, ovalSize);
        
        g.drawOval((int)(Main.Width()*0.1) - ovalSize, Main.Height()/2 - ovalSize, ovalSize*2, ovalSize*2);
        g.drawOval((int)(Main.Width()*0.9) - ovalSize, Main.Height()/2 - ovalSize, ovalSize*2, ovalSize*2);
    }

}
