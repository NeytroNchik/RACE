import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
	
	public static final int MAX_SPEED = 75; 
	public static final int MAX_TOP = 85;       //������������ ������� ������
	public static final int MAX_BOTTOM = 615;  //������������ ������� �����

	
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Car.png")).getImage();
	Image img1 = new ImageIcon(getClass().getClassLoader().getResource("res/Red.png")).getImage();
	Image img2 = new ImageIcon(getClass().getClassLoader().getResource("res/Blue.png")).getImage();
	
	public Rectangle getRect() {
		 return new Rectangle (x, y, 0, 0);
	 }
	
	int speed = 0;         // �������� ������ (� ������ ����� ����)
	int boost = 0;        // ��������� ������������� ��������
	int distance = 0;    // ����
	
	int x = 30;            // ���������� ������ �� ������
	int y = 100;          //
	
	int dy = 0;         // ��������� ��������� ������� ����� � ����
	
	int layer1 = 0;      //���������� ������� ���� �� x
	int layer2 = 1800;  //���������� ������� ���� �� x
		
	public void move() {
		
		distance += speed;  //��������� ���������� ������������ ��������
		speed += boost;    //���������� ��������� 
		
		if (speed == 0) dy =0;                       //������ ��������� ����� � ���� ��� ������� ��������		
		if (speed <= 0) speed = 0;                  //������ ����� ����� 
		if (speed >= MAX_SPEED) speed = MAX_SPEED; //������ ��������� ������������ ��������
		y-=dy;
		
		if (y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
		if (layer2 - speed <= 0) { // ������������ ����������� ������. ��� ���������� ������� ����, ��������� �� ����������
			layer1 = 0;
			layer2 = 1800; 
		}
		else {
		layer1 -= speed;   // ���������� ���� ����������, ��� �����, �������� ������� ��������		
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
	
	if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A || key == KeyEvent.VK_D) boost = 0; //��� ���������� �������, ��������� ����������
		
	
	if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_W  || key == KeyEvent.VK_S) dy = 0; //��� ���������� �������, ���������� ��������� �� y
			
	}
}
