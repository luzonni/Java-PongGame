package cene;

import main.Main;

public class Ball extends Thing {

    private double radians;
    private double dx, dy;
    private double speed;

    public Ball(int x, int y) {
        super(x, y, (int)(Main.Width()*0.01), (int)(Main.Width()*0.01));
        this.radians = Math.toRadians(Main.rand.nextInt(360));
        this.speed = 10;
        this.dx = Math.cos(radians);
        this.dy = Math.sin(radians);
    }

    public void plusSpeed(double plus) {
        this.speed += plus;
    }

    public void tick(Player[] players) {
        double px = this.getBounds().getX() + speed*dx;
        double py = this.getBounds().getY() + speed*dy;
        this.getBounds().setLocation((int)px, (int)py);
        colliderBounds(players);
    }

    private void colliderBounds(Player[] players) {
        if(this.getBounds().getY() < 0) {
            this.dy*=-1;
        }
        if(this.getBounds().getY() + this.getBounds().getHeight() > Main.Height()) {
            this.dy*=-1;
        }

        if(this.getBounds().intersects(players[0].getBounds())) {
            this.dx*=-1;
            this.getBounds().setLocation(players[0].getBounds().x + players[0].getBounds().width, this.getBounds().y);
            plusSpeed(0.25);
        }
        if(this.getBounds().intersects(players[1].getBounds())) {
            this.dx*=-1;
            this.getBounds().setLocation(players[1].getBounds().x - this.getBounds().width, this.getBounds().y);
            plusSpeed(0.25);
        }
    }

}
