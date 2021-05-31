package GUI;

import java.awt.Graphics;
import java.awt.Rectangle;


public class Person{
	private int x = 200;
	private int y = 200;
	private int d = 5;
	private boolean isInfect = false;
	private boolean isDead = false;
	public boolean inH = false;
	private int MoveRate = 80;//�ƶ�����
	private Rectangle r;
	private Rectangle infect = null;
	public int HidTime = 50;//Ǳ��ʱ��
	public int SeekTime = 30;//ȥҽԺʱ��
	public int DeadTime = 100;//����ʱ��
	public int CureTime = 100;//����ʱ��
	int RN;
	public Person() {
		
	}
	public Person(int x,int y) {
		r = new Rectangle(x,y,d,d);//����Ⱦ��Χ
		infect = new Rectangle(x-10,y-10,d+20,d+20);//��Ⱦ��Χ
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g) {
		r = new Rectangle(x,y,d,d);//����Ⱦ��Χ
		infect = new Rectangle(x-10,y-10,d+20,d+20);//��Ⱦ��Χ
		g.fillOval(x, y, d, d);
		if(!isDead&&((int) (Math.random()*100)<MoveRate)) {
			move();
		}
		//g.drawRect(x-10,y-10,d+20,d+20);
	}
	public void move() {
		RN=(int) (Math.random()*100)%2;
		if(RN==0) {
			RN=(int) (Math.random()*100)%2;
			if(RN==0) {
				RN=(int) (Math.random()*100)%10;
				if(InCity(x+RN,y))
				  x += RN;
			}
			else {
				RN=(int) (Math.random()*100)%10;
				if(InCity(x-RN,y))
					x -= RN;
			}
		}
		else {
			RN=(int) (Math.random()*100)%2;
			if(RN==0) {
				RN=(int) (Math.random()*100)%10;
				if(InCity(x,y+RN))
					y += RN;
			}
			else {
				RN=(int) (Math.random()*100)%10;
				if(InCity(x,y-RN))
					y -= RN;
			}
		}
	}
	public boolean InCity(int x,int y) {
		if((x-320)*(x-320)+(y-380)*(y-380) < 62500)
			return true;
		else
			return false;
	}
	public Rectangle getR() {
		return r;
	}
	public Rectangle getInfectR() {
		return infect;
	}
	public void beInfect() {
		this.isInfect = true;
	}
	public void dead() {
		this.isDead = true;
		this.isInfect = false;
	}
	public boolean isInfect() {
		return isInfect;
	}
	public boolean isDead() {
		return isDead;
	}
}
