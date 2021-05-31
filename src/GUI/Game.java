package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class Game extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City c;
	private Hospital h;
	private boolean YouWin = false;
	private boolean GameOver = false;
	private BufferedImage bi;
	private int day = 0;
	public Game() {
		c = new City();
		h = new Hospital();
		this.setSize(800, 800);
		this.setVisible(true);
		this.setResizable(false);
		new Thread(new PaintThread()).start();
	}
	public void paint(Graphics g) {
		//双缓冲法避免闪烁
		if(bi == null)
		bi = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
		Graphics big = bi.getGraphics();
		big.setColor(getBackground());
		big.fillRect(0, 0,this.getSize().width, this.getSize().height);//清空屏幕
		g.setColor(Color.RED);
		c.draw(big);
		h.draw(big);
		big.drawString("病床数："+Hospital.max, 100, 60);
		big.drawString("天数："+(day++)/10, 100, 80);
		big.drawString("总人数："+c.getNum(), 100, 100);
		big.drawString("健康人数"+c.getH(), 100, 120);
		big.drawString("感染人数"+c.getI(), 100, 140);
		big.drawString("死亡人数"+c.getD(), 100, 160);
		big.drawString("治愈人数"+c.getC(), 100, 180);
		g.drawImage(bi,0,0,null);
		if(c.getI()==0)
			YouWin = true;
		if(c.getD()>c.getNum()/2)
			GameOver = true;
	}
	public static void main(String[] args) {
		new Game();
	}
	/*
	 * 绘制图形界面线程
	 */
	public class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (!GameOver&&!YouWin) {
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(GameOver)
				JOptionPane.showMessageDialog(null, "Game Over!", "消息提示", JOptionPane.WARNING_MESSAGE);
			else if(YouWin)
				JOptionPane.showMessageDialog(null, "You win!", "消息提示", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}
