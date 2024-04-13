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
	public static int points = 0;	                    //изначальное количество очков
	
	Timer mainTimer = new Timer(20, this);   //Таймер будет запускать функцию actionPreformed каждые 20 миллисекунд
	
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/Road.png")).getImage(); // изображение дороги 
	
	Player player = new Player();    // переменная игрока
	
	Thread obstaclesFactory = new Thread(this); //поток с врагами, выполняющийся параллельно с главной программой
	
	List<Obstacle> obstacles = new ArrayList<Obstacle>(); //массив с врагами
	
	public Road() {            // конструктор, нужный для выполнения основных методов
		
		mainTimer.start();                              // старт таймера
		obstaclesFactory.start();                      // старт потока с врагами
		addKeyListener(new AnotherKeyAdapter());      // метод, нужный для восприятия клавиатуры 
		setFocusable(true);		                     // фокусирование на текущем объекте

	}

		public void paint (Graphics g) {          // метод рисования дороги 
			
			g = (Graphics2D) g;                 // 2D графика рисования
			
			System.out.println(player.speed);
			
		if (Setting.YELLOW == true) {     // внешний вид машинки
			
			g.drawImage(img, player.layer1, 0, null); //координаты изображения дороги меняются в зависимости от скорости
			g.drawImage(img, player.layer2, 0, null); //
			g.drawImage(player.img, player.x, player.y, null); //изображение машины
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
			
			double screenSpeed = 2*player.speed; // скорость на экране	
			
			Font font = new Font("CaviarDreams-Italic", Font.ITALIC, 40);	 // шрифт количества очков							
			g.setColor(Color.ORANGE);	                                    // цвет количества очков	                          	
			g.setFont(font);                                               // установка шрифта
			g.drawString("СКОРОСТЬ: " + screenSpeed + " км/ч", 30, 40);   // вывод скорости на экран
			g.drawString("ОЧКИ: " + points, 1500, 800);                  // вывод очков на экран
			 
			Iterator<Obstacle> i = obstacles.iterator();               // создаём итератор, чтобы пробегать по всему массиву с врагами     			
			
			while (i.hasNext()) {                           // цикл для прохождения по всему массиву с врагами
			
				Obstacle o = i.next();			
				o.move();                                  // вызов метода, нужного для передвижения врагов
				g.drawImage(o.img, o.x, o.y, null);       // рисовка врага по заданным кординатам
			}		
		}
		
		public void actionPerformed (ActionEvent e) {  //движение дороги
			
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
					
					JOptionPane.showMessageDialog(null, "                            ПРОИГРЫШ!" +"\n" + "ВЫ НАБРАЛИ " + points + " очков  и проехали " + player.distance/10000 + " километров");
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

		public void run() {  //код параллельного потока			
			
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
					
					Thread.sleep(countOfObstacles);			//через случайный промежуток времени появляется враг				 
					Obstacle ob = new Obstacle(1800, r.nextInt(615)+80, 0 , this); //с такими параметрами (случайная скорость и случайное расположение на дороге)
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
			class AnotherKeyAdapter extends KeyAdapter {  //реализование класса, нужного для восприятия клавиатуры
		
		public void keyPressed (KeyEvent e) {    // при нажатии на клавишу 
			
			System.out.println("&"); 
			player.keyPressed(e);      // метод нажатия на клавишу игрока
		}
		
		public void keyReleased (KeyEvent e) {   // при отпускании клавиши
			
			player.keyReleased(e);              // метод отпускании клавиши игроком
		}
	}
			
	 }
		
			
//}

