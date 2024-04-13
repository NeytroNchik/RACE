import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
	
	public static final int MAX_SPEED = 75; 
	public static final int MAX_TOP = 85;       //максимальная граница сверху
	public static final int MAX_BOTTOM = 615;  //максимальная граница снизу

	
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Car.png")).getImage();
	Image img1 = new ImageIcon(getClass().getClassLoader().getResource("res/Red.png")).getImage();
	Image img2 = new ImageIcon(getClass().getClassLoader().getResource("res/Blue.png")).getImage();
	
	public Rectangle getRect() {
		 return new Rectangle (x, y, 0, 0);
	 }
	
	int speed = 0;         // скорость машины (в начале равна нулю)
	int boost = 0;        // насколько увеличивается скорость
	int distance = 0;    // путь
	
	int x = 30;            // координаты машины на дороге
	int y = 100;          //
	
	int dy = 0;         // изменение положения машинки вверх и вниз
	
	int layer1 = 0;      //координата первого слоя по x
	int layer2 = 1800;  //координата второго слоя по x
		
	public void move() {
		
		distance += speed;  //изменение расстояния относительно скорости
		speed += boost;    //реализация ускорения 
		
		if (speed == 0) dy =0;                       //нельзя двигаться вверх и вниз при нулевой скорости		
		if (speed <= 0) speed = 0;                  //нельзя ехать назад 
		if (speed >= MAX_SPEED) speed = MAX_SPEED; //нельзя превышать максимальную скорость
		y-=dy;
		
		if (y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
		if (layer2 - speed <= 0) { // зацикливание изображения дороги. При достижении границы слоёв, обновляем их координаты
			layer1 = 0;
			layer2 = 1800; 
		}
		else {
		layer1 -= speed;   // координаты слоёв изменяются, тем самым, создавая иллюзию движения		
		layer2 -= speed;  //
		}
	}	
	
	public void keyPressed (KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) boost = 1;
		
		if (key == KeyEvent.VK_LEFT  || key == KeyEvent.VK_A) boost = -1;
		
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) dy = 10;
		
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) dy = -10;		
	}
	
public void keyReleased (KeyEvent e) {
	
	int key = e.getKeyCode();
	
	if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A || key == KeyEvent.VK_D) boost = 0; //при отпускании клавиши, ускорение обнуляется
		
	
	if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_W  || key == KeyEvent.VK_S) dy = 0; //при отпускании клавиши, обнуляется изменение по y
			
	}
}
