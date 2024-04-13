import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
public static final JFrame mainFrame = new JFrame("ДОБРО ПОЖАЛОВАТЬ В ИГРУ");  // окно с главным меню
public static final JFrame gameFrame = new JFrame("НЕ ГОНЯЙТЕ ПАЦАНЫ");       // окно с самой игрой
public static boolean exit = false;                                          // переменная отвечающая за нажатие кнопки "Выход"
public static boolean RoadIsOpen = false;                                   // переменная отвечающая за открытие окна с игрой

	public static void main(String[] args) throws IOException {			
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// при нажатии на крестик окно закрывается
		mainFrame.setLayout(null);                                 // нормальное расположение элементов в окне
		mainFrame.setSize(600, 600);                              // размер окна                                   
		mainFrame.setLocationRelativeTo(null);		             // расположение окна по середине
		System.out.println("right");                            
		
		gameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gameFrame.setSize(1800, 900);			
		gameFrame.add(new Road());                              // вывод дороги на экран
		gameFrame.setLocationRelativeTo(null);	
		
		
		JButton buttonPlay = new JButton("Новая игра");    // размер кнопки с названием
		buttonPlay.setSize(150, 30);				      // размер кнопки
		buttonPlay.setLocation(225,150);                 // расположение кнопки
		buttonPlay.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));   // внешний вид
				
		mainFrame.add(buttonPlay); // добавление кнопки в окно
		
		JButton settingsButton = new JButton("Настройки");
		settingsButton.setSize(150, 30);
		settingsButton.setLocation(225,200);		
		settingsButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(settingsButton);
		
		JButton aboutButoon = new JButton("Об игре");
		aboutButoon.setSize(150, 30);
		aboutButoon.setLocation(225,250);		
		aboutButoon.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(aboutButoon);
		
		JButton exitButton = new JButton("Выйти");
		exitButton.setSize(150, 30);
		exitButton.setLocation(225,300);		
		exitButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(exitButton);	
				
		ImageIcon backIcon = createImageIcon("res/WOW.jpg");   // изображение заднего фона окна			
		JLabel JLBackgroundImage = new JLabel(backIcon);      // перевод изображения в JLabel
		JLBackgroundImage.setBounds(0, 0, 600, 600);         // границы JLabel`а
		mainFrame.add(JLBackgroundImage);                   // добавление заднего фона в окно
		
		mainFrame.setVisible(true);	                      // видимость окна
	
		final JFrame settingsFrame = new JFrame("НАСТРОЙКИ");
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.setLayout(null);
		settingsFrame.setSize(600, 600);	
		settingsFrame.setVisible(false);		
		settingsFrame.setLocationRelativeTo(null);
			
		JButton complexityButton = new JButton("Сложность");
		complexityButton.setSize(150, 30);
		complexityButton.setLocation(225,150);		
		complexityButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(complexityButton);
		
		JButton skinButton = new JButton("Внешний вид");
		skinButton.setSize(150, 30);
		skinButton.setLocation(225,200);		
		skinButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(skinButton);
		
		JFrame aboutFrame = new JFrame("ОБ ИГРЕ");
		aboutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aboutFrame.setSize(600, 600);	
		aboutFrame.setVisible(false);
		aboutFrame.setLocationRelativeTo(null);
			
		ImageIcon img = new ImageIcon("src/res/EBEY.jpg");    // создание иконки игры
		mainFrame.setIconImage(img.getImage());              // добавление иконки в окно
		gameFrame.setIconImage(img.getImage());		
		
		JButton exitSettingsButton = new JButton("Выйти");
		exitSettingsButton.setSize(150, 30);
		exitSettingsButton.setLocation(225,250);		
		exitSettingsButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(exitSettingsButton);
		
		buttonPlay.addActionListener(new ActionListener() {    // добавление функционала кнопке
			
	           public void actionPerformed(ActionEvent e) {  // метод, добавляющий функционал кнопке
	        	   
	        	   RoadIsOpen = true;
	            	gameFrame.setVisible(true);             // событие кнопки
	            	
	            }
	        });
			 
			 settingsButton.addActionListener(new ActionListener() {
				 
		            public void actionPerformed(ActionEvent e) {
		            	
		                settingsFrame.setVisible(true);		                
		            }
		        });
			 
			 exitButton.addActionListener(new ActionListener() {
				 
		            public void actionPerformed(ActionEvent e) {
		            	
		                mainFrame.setVisible(false);
		                exit = true;		               
		            }
		        });
			 
			 exitSettingsButton.addActionListener(new ActionListener() {
				 
		            public void actionPerformed(ActionEvent e) {
		            	
		                settingsFrame.setVisible(false);
		            }
		        });
			 
			 complexityButton.addActionListener(new ActionListener() {
				 
		            public void actionPerformed(ActionEvent e) {
		            	
		                javax.swing.SwingUtilities.invokeLater(new Runnable() {
		                	
		                	public void run() {
		                		
	                Complexity.createAndShowGUI2();
		        }
		    });
	       }
		});
			 
			 skinButton.addActionListener(new ActionListener() {
				 
		        public void actionPerformed(ActionEvent e) {
		            	
		            javax.swing.SwingUtilities.invokeLater(new Runnable() {
		                	
	            public void run() {                      // настройки показываются отдельным потоком
	            	
	                Setting.createAndShowGUI();
	                
	             }
		     });
		    }
		  });						 
    }
	
	protected static ImageIcon createImageIcon(String path) {                     // метод, создающий иконку приложения
		
        java.net.URL imgURL = Setting.class.getResource(path);                  // получение файла с помощью пути
        
        if (imgURL != null) {                                                 // если файл найден                                      
        	
            return new ImageIcon(imgURL);                                   // возвращение иконки изображения
            
        } else {                                                                            
        	
            System.err.println("Couldn't find file: " + path);           // если изображение не найдено
            return null;
        }
    }
}

