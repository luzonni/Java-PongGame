package cene;

import main.KeyBoard;
import main.Main;

public class Player extends Thing {

    private String up, down;
    private double acceleration;
    private int points;

    public Player(int x, int y) {
        super(0, 0, 0, 0);
        int width = (int)(Main.Width()*0.01);
        int height = (int)(Main.Height()*0.3);
        getBounds().setBounds(x - width/2, y - height/2, width, height);
    }

    public void setKeys(String... keys) {
        up = keys[0];
        down = keys[1];
    }

    public void addPoint(int p) {
        this.points += p;
    }

    public int getPoints() {
        return this.points;
    }

    private void move() {
        if(this.getBounds().y >= 0 && (this.getBounds().y + this.getBounds().height) <= Main.Height())
            this.getBounds().setLocation(this.getBounds().x, (int)(this.getBounds().getY() + acceleration));
        else {
            if(this.getBounds().getY() < 0) {
                this.getBounds().setLocation(this.getBounds().x, (int)(acceleration));
            }else if((this.getBounds().y + this.getBounds().height) > Main.Height()) {
                this.getBounds().setLocation(this.getBounds().x, (int)(Main.Height() - this.getBounds().height + acceleration));
            }
        }
        
        if(KeyBoard.KeyPressing(up)) {
            if(this.acceleration > 0)
                this.acceleration = 0;
            if(this.acceleration > -10)
                this.acceleration-=2;
        }else if(KeyBoard.KeyPressing(down)) {
            if(this.acceleration < 0)
                this.acceleration = 0;
            if(this.acceleration < 10)
                this.acceleration+=2;
        }else {
            if(this.acceleration > 0) {
                this.acceleration--;
            }else if(this.acceleration < 0) {
                this.acceleration++;
            }
        }
    }

    public void tick() {
        this.points = 5;
        super.tick();
        move();
    }

}
