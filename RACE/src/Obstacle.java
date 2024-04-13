import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Obstacle {

	 int x;  //кординаты препятствия
	 int y; //
	 int speed = 0; //скорость препятствия
	 Image img = new ImageIcon (getClass().getClassLoader().getResource("res/Obstacle.png")).getImage();
	 Road road;
	 
	 public Rectangle getRect() {
		 return new Rectangle (x, y, 200, 100);
	 }
	 
	 public Obstacle(int x, int y, int speed, Road road) {
		 this.x = x;
		 this.y = y;
		 this.speed = speed;
		 this.road = road;
	 }
	 
	 public void move () {
		 
		  Random r = new Random();
		  x=1800;
		  y=r.nextInt(615)+80;
		  
		  	if (x<-200 || x>2500) {		 
			
			 
					 int value = road.player.speed + (r.nextInt(100)-50);
				if (value< 1) value = 1;	;
				speed = value;
				
		 } 		 
		 
		x = x - speed;   
	 }
}
