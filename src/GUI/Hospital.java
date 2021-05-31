package GUI;

import java.awt.Color;
import java.awt.Graphics;

public class Hospital {
	private int x = 570;
	private int y = 300;
	private static Bed[] b;
	public static int max = 30;
	public boolean isFull = false;
	public Hospital() {
		b = new Bed[max];
		for(int i = 0;i< max;i++) {
			b[i] = new Bed(x,y+10*(i%30));
			if((i+1)%30==0) {
				x+=10;
			}		
		}
	}
	public Hospital(int x,int y) {
		this();
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		for(int i=0;i < b.length;i++) {
			b[i].draw(g);
		}
	}
	public static boolean IsFull() {
		for(int i = 0 ; i<max;i++) {
			if(b[i].IE) {
				b[i].busy();
				return false;
			}	
		}
		return true;
	}
}
