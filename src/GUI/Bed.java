package GUI;

import java.awt.Color;
import java.awt.Graphics;

public class Bed {
	private int x = 200;
	private int y = 200;
	public boolean IE = true;
	private int cureTime = 100;
	public Bed() {
		
	}
	public Bed(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public void busy() {
		IE = false;
	}
	public void draw(Graphics g) {
		if(!IE) {
			g.setColor(Color.RED);
			g.fillOval(x+3, y+3, 5, 5);
			cureTime--;
			if(cureTime <= 0) {
				IE = true;
				cureTime = 100;
			}
		}
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 10, 10);
	}
}
