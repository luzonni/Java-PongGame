package cene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Thing {

    private Rectangle bounds;

    public Thing(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

}
