package GUI;

import java.awt.*;

public class drawPerson implements Draw{

    @Override
    public void draw(Graphics g,Object o) {
        Person p=(Person) o;
        int x=p.getX(),y=p.getY(),d=p.getD();
        Rectangle r = new Rectangle(x,y,d,d);//被传染范围
        Rectangle infect = new Rectangle(x-10,y-10,d+20,d+20);//传染范围
        g.fillOval(x, y, d, d);
        if(!p.isDead()&&((int) (Math.random()*100)<p.getMoveRate())) {
            p.move();
        }
    }
}
