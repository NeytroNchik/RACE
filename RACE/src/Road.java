import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	public static int points = 0;	                    //����������� ���������� �����
	
	Timer mainTimer = new Timer(20, this);   //������ ����� ��������� ������� actionPreformed ������ 20 �����������
	
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Road.png")).getImage(); // ����������� ������ 
	
	Player player = new Player();    // ���������� ������
	
	Thread obstaclesFactory = new Thread(this); //����� � �������, ������������� ����������� � ������� ����������
	
	List<Obstacle> obstacles = new ArrayList<Obstacle>(); //������ � �������
	
	public Road() {            // �����������, ������ ��� ���������� �������� �������
		
		mainTimer.start();                              // ����� �������
		obstaclesFactory.start();                      // ����� ������ � �������
		addKeyListener(new AnotherKeyAdapter());      // �����, ������ ��� ���������� ���������� 
		setFocusable(true);		                     // ������������� �� ������� �������

	}

		public void paint (Graphics g) {          // ����� ��������� ������ 
			
			g = (Graphics2D) g;                 // 2D ������� ���������
			
			System.out.println(player.speed);
			
		if (Setting.YELLOW == true) {     // ������� ��� �������
			
			g.drawImage(img, player.layer1, 0, null); //���������� ����������� ������ �������� � ����������� �� ��������
			g.drawImage(img, player.layer2, 0, null); //
			g.drawImage(player.img, player.x, player.y, null); //����������� ������
		}
			
		if (Setting.RED == true) {
			
				g.drawImage(img, player.layer1, 0, null); 
				g.drawImage(img, player.layer2, 0, null); 
				g.drawImage(player.img1, player.x, player.y, null); 
		}
			
		if (Setting.BLUE == true) {
			
				g.drawImage(img, player.layer1, 0, null); 
				g.drawImage(img, player.layer2, 0, null); 
				g.drawImage(player.img2, player.x, player.y, null); 
		}
			
			double screenSpeed = 2*player.speed; // �������� �� ������	
			
			Font font = new Font("CaviarDreams-Italic", Font.ITALIC, 40);	 // ����� ���������� �����							
			g.setColor(Color.ORANGE);	                                    // ���� ���������� �����	                          	
			g.setFont(font);                                               // ��������� ������
			g.drawString("��������: " + screenSpeed + " ��/�", 30, 40);   // ����� �������� �� �����
			g.drawString("����: " + points, 1500, 800);                  // ����� ����� �� �����
			 
			Iterator<Obstacle> i = obstacles.iterator();               // ������ ��������, ����� ��������� �� ����� ������� � �������     			
			
			while (i.hasNext()) {                           // ���� ��� ����������� �� ����� ������� � �������
			
				Obstacle o = i.next();			
				o.move();                                  // ����� ������, ������� ��� ������������ ������
				g.drawImage(o.img, o.x, o.y, null);       // ������� ����� �� �������� ����������
			}		
		}
		
		public void actionPerformed (ActionEvent e) {  //�������� ������
			
			testCollissionWithObstacles();
			player.move();
			repaint();		
		}

		private void testCollissionWithObstacles() {
		
			System.out.println("***");
			Iterator<Obstacle> i = obstacles.iterator();
			int count = 0;	
			
			while (i.hasNext()) {
				
				if (Main.RoadIsOpen == true && count<5) {
				Obstacle o = i.next();
				count++;
				//if (count>20) i.remove();
				//System.out.println(count);
				
				if (player.getRect().intersects(o.getRect())) {
					
					JOptionPane.showMessageDialog(null, "                            ��������!" +"\n" + "�� ������� " + points + " �����  � �������� " + player.distance/10000 + " ����������");
					Main.gameFrame.setVisible(false);
					Main.gameFrame.getContentPane().removeAll();
					Main.gameFrame.getContentPane().repaint();
					Main.gameFrame.add(new Road());
					points=0;
				}
				
				if (player.x > o.x + 300) points++;
				
				//try (FileWriter fileWriter = new FileWriter("Record.txt")) {
				    //fileWriter.write(String.valueOf(5));
				  //  fileWriter.flush();
				}
			}
		}			
	//}

		public void run() {  //��� ������������� ������			
			
			int value = 0;
			
			try {
				
				Thread.sleep(5000);
				
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
			
			int countOfObstacles = 0;
			
			//if (Main.RoadIsOpen == true) {
				
			while (true) {
				
				if (Main.RoadIsOpen == true) {
				
				System.out.println(player.speed + " " + player.y);
				
				Random r = new Random();		

				value = player.speed + (r.nextInt(100)-50);
				
				if (value< 10) value = 10;									
				
				
				if (Complexity.EASY == true) {
					
				countOfObstacles = r.nextInt(2000) + 100;				 	
				}
				
				if (Complexity.MEDIUM == true) {
					countOfObstacles = r.nextInt(1000);				 	
					}
				
				if (Complexity.HARD == true) {
					countOfObstacles = r.nextInt(750);				 	
					}
				
				try {
					
					Thread.sleep(countOfObstacles);			//����� ��������� ���������� ������� ���������� ����				 
					Obstacle ob = new Obstacle(1800, r.nextInt(615)+80, 0 , this); //� ������ ����������� (��������� �������� � ��������� ������������ �� ������)
					obstacles.add(ob);  
					System.out.println(ob.y);
					//if ( ob.y<180) ((Iterator<Obstacle>) ob).remove();
				} 
				
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (Main.exit == true) break;
			 }
		 }
			
		}
			class AnotherKeyAdapter extends KeyAdapter {  //������������ ������, ������� ��� ���������� ����������
		
		public void keyPressed (KeyEvent e) {    // ��� ������� �� ������� 
			
			System.out.println("&"); 
			player.keyPressed(e);      // ����� ������� �� ������� ������
		}
		
		public void keyReleased (KeyEvent e) {   // ��� ���������� �������
			
			player.keyReleased(e);              // ����� ���������� ������� �������
		}
	}
			
	 }
		
			
//}

