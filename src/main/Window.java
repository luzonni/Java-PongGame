package main;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import tools.SpriteSheet;

public class Window extends Canvas {

    private String Name;
    private int Width, Height;

    private JFrame frame;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public Window(String name, int width, int height) {
        this.Name = name;
        this.Width = width;
        this.Height = height;
        init();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public int getWidth() {
		Component c = frame.getComponent(0);
		return c.getWidth();
	}
	
	public int getHeight() {
		Component c = frame.getComponent(0);
		return c.getHeight();
	}

    private void init() {
        frame = new JFrame(Name);
        frame.add(this);
        frame.setUndecorated(false);
		frame.setResizable(false);
		frame.setAlwaysOnTop(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Width, Height));
        frame.setMinimumSize(new Dimension(Width, Height));
		frame.pack();
		try {
			SpriteSheet cursor = new SpriteSheet(Main.ResPath+"/ui/cursor.png");
			Image icone = ImageIO.read(getClass().getResource(Main.ResPath+"/ui/icon.png"));
			Cursor c = toolkit.createCustomCursor(cursor.getImage(), new Point(0,0), "cursor");
			frame.setCursor(c);
			frame.setIconImage(icone);
		}catch(Exception e) { e.printStackTrace(); }
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        createBufferStrategy(3);
        Main.BufferS = getBufferStrategy();
    }
    
}
