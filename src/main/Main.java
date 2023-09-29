package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import cene.Map;

public class Main implements Runnable {
    
    private static Window window;
    public static Thread thread;
    public static boolean isRunning;

    public static final double HZ = 60;
	public static final double T = 1_000_000_000.0;
	public static int MaxFrames = 256;
    public static BufferStrategy BufferS;

	public static Random rand;
    public static final String ResPath = "/res";

	private Map map;

	public KeyBoard keys;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
		rand = new Random();
		keys = new KeyBoard();
        window = new Window("Pong", 1280, 720);
		window.addKeyListener(keys);
		map = new Map(Width(), Height());
        start();
    }

	public static int Width() {
		return Main.window.getWidth();
	}

	public static int Height() {
		return Main.window.getHeight();
	}

    public void tick() {
		map.tick();
    }

    public void render(Graphics g) {
        if(g == null) {
            return;
        }
        g.setColor(Color.black);
        g.fillRect(0, 0, Width(), Height());

		map.render(g);

        g.dispose();
		BufferS.show();
    }

    private Graphics getGraphics() {
        return BufferS.getDrawGraphics();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this, "Pong_Main_Thread");
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread = null;
    }

    @Override
    public void run() {
        //System values
		long lastTimeHZ = System.nanoTime();
		double amountOfHz = Main.HZ;
		double ns_HZ = Main.T / amountOfHz;
		double delta_HZ = 0;
		
		long lastTimeFPS = System.nanoTime();
		double amountOfFPS = Main.MaxFrames;
		double ns_FPS = Main.T / amountOfFPS;
		double delta_FPS = 0;
		
		//To Show
		int Hz = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			ns_FPS = Main.T / Main.MaxFrames;
			try {
				
				long nowHZ = System.nanoTime();
				delta_HZ += (nowHZ - lastTimeHZ) / ns_HZ;
				lastTimeHZ = nowHZ;
				if(delta_HZ >= 1) {
					tick();
					Hz++;
					delta_HZ--;
				}
				
				long nowFPS = System.nanoTime();
				delta_FPS += (nowFPS - lastTimeFPS) / ns_FPS;
				lastTimeFPS = nowFPS;
				if(delta_FPS >= 1) {
					Graphics g = getGraphics();
					render(g);
					frames++;
					delta_FPS--;
				}
				
				//Show fps
				if(System.currentTimeMillis() - timer >= 1000){
                    System.out.println(" - Hz: " + Hz + " / Frames: " + frames);
					frames = 0;
					Hz = 0;
					timer+=1000;
				}
				
			}catch(Exception e) {
				System.out.println("ERROR!");
				e.printStackTrace();
				System.exit(1);
			}
		}
		stop();
    }

}
