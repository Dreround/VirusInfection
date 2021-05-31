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
		//˫���巨������˸
		if(bi == null)
		bi = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
		Graphics big = bi.getGraphics();
		big.setColor(getBackground());
		big.fillRect(0, 0,this.getSize().width, this.getSize().height);//�����Ļ
		g.setColor(Color.RED);
		c.draw(big);
		h.draw(big);
		big.drawString("��������"+Hospital.max, 100, 60);
		big.drawString("������"+(day++)/10, 100, 80);
		big.drawString("��������"+c.getNum(), 100, 100);
		big.drawString("��������"+c.getH(), 100, 120);
		big.drawString("��Ⱦ����"+c.getI(), 100, 140);
		big.drawString("��������"+c.getD(), 100, 160);
		big.drawString("��������"+c.getC(), 100, 180);
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
	 * ����ͼ�ν����߳�
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
				JOptionPane.showMessageDialog(null, "Game Over!", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			else if(YouWin)
				JOptionPane.showMessageDialog(null, "You win!", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
}
