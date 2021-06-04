package Service.City;

import Person.Person;
import Service.Hospital.Hospital;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {
	private int x = 70;
	private int y = 130;
	private int d = 500;
	private Person[] p;
	private int num = 500;
	private int count = 0;
	private int SpreadRate = 80;//´«²¥ÂÊ
	private List<Person> dead = new ArrayList<Person>();
	private List<Person> infected = new ArrayList<Person>();
	private List<Person> healthy = new ArrayList<Person>();
	private List<Person> curing = new ArrayList<Person>();
	private List<Person> cured = new ArrayList<Person>();
	double ansX,ansY;
	public City() {
		p = new Person[num];
		Random r = new Random();
		int id= (int) (Math.random() * num);
		int R = d/2;
		for(int i = 0;i<num;i++) {
			ansX = r.nextGaussian();
			ansY = r.nextGaussian();
			while(Math.pow(R*ansX, 2)+Math.pow(R*ansY, 2)>Math.pow(R,2)){
				ansX = r.nextGaussian();
				ansY = r.nextGaussian();
				if(ansX>1){
					ansX=1;
				}
				if(ansX<-1){
					ansY=-1;
				}
				if(ansY>1){
					ansY=1;
				}
				if(ansY<-1){
					ansY=-1;
				}
			}
			p[i] = new Person((int) (R*ansX+320),(int) (R*ansY+380));
			if(i!=id)
			healthy.add(p[i]);
		}
		infected.add(p[id]);
	}
	public City(int x,int y) {
		this();
		this.x=x;
		this.y=y;
	}
	public int getNum() {
		return num;
	}
	public int getH() {
		return healthy.size();
	}
	public int getI() {
		return infected.size();
	}
	public int getD() {
		return dead.size();
	}
	public int getC() {
		return cured.size();
	}
	public int getCing() {
		return curing.size();
	}
	public void draw(Graphics g) {
		count++;
		g.setColor(Color.green);
		g.drawOval(x, y, d, d);
		for(int i = 0;i<healthy.size();i++) {
			//g.setColor(Color.GREEN);
			healthy.get(i).draw(g);
		}
		for(int i = 0;i<infected.size();i++) {
			g.setColor(Color.RED);
			if(!infected.get(i).inH) {
				if(infected.get(i).SeekTime <= 0) {		
					if(!Hospital.IsFull()) {
						infected.get(i).inH = true;
						curing.add(infected.get(i));
						infected.remove(i);
					}	
				}
				infected.get(i).draw(g);
				infected.get(i).HidTime--;
				if(infected.get(i).HidTime <=0&&count%10==0) {
					infected.get(i).SeekTime-=10;
					for(int j = 0;j<healthy.size();j++) {
						if(infected.get(i).getInfectR().intersects(healthy.get(j).getR())&&((int) (Math.random()*100)<SpreadRate)) {
							healthy.get(j).beInfect();
							infected.add(healthy.get(j));
							healthy.remove(j);
						}
					}
				}
				infected.get(i).DeadTime--;
				if(infected.get(i).DeadTime <=0) {
					infected.get(i).dead();
					dead.add(infected.get(i));
					infected.remove(i);
				}
			}
		}
		for(int i=0;i<curing.size();i++) {
			if(curing.get(i).CureTime <= 0) {
				curing.get(i).draw(g);
				cured.add(curing.get(i));
				curing.remove(i);
			}
			else
				curing.get(i).CureTime--;
		}
		for(int i=0;i<cured.size();i++) {
			g.setColor(Color.BLUE);
			cured.get(i).draw(g);
		}
		for(int i = 0;i<dead.size();i++) {
			g.setColor(Color.BLACK);
			dead.get(i).draw(g);
		}
	}
}
