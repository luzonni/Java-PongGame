package cene;

import main.KeyBoard;
import main.Main;

public class Player extends Thing {

    private String up, down;

    public Player(int x, int y) {
        super(0, 0, 0, 0);
        int width = (int)(Main.Width()*0.025);
        int height = (int)(Main.Height()*0.3);
        getBounds().setBounds(x - width/2, y - height/2, width, height);
    }

    public void setKeys(String... keys) {
        up = keys[0];
        down = keys[1];
    }

    public void tick() {
        super.tick();
        if(KeyBoard.KeyPressing(up)) {
            if(this.getBounds().y > 0)
                this.getBounds().setLocation(this.getBounds().x, this.getBounds().y - 10);
        }
        if(KeyBoard.KeyPressing(down)) {
            if(this.getBounds().y + this.getBounds().height < Main.Height())
                this.getBounds().setLocation(this.getBounds().x, this.getBounds().y + 10);
        }
    }
    
}
