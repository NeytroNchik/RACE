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
	
public static final JFrame mainFrame = new JFrame("����� ���������� � ����");  // ���� � ������� ����
public static final JFrame gameFrame = new JFrame("�� ������� ������");       // ���� � ����� �����
public static boolean exit = false;                                          // ���������� ���������� �� ������� ������ "�����"
public static boolean RoadIsOpen = false;                                   // ���������� ���������� �� �������� ���� � �����

	public static void main(String[] args) throws IOException {			
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ��� ������� �� ������� ���� �����������
		mainFrame.setLayout(null);                                 // ���������� ������������ ��������� � ����
		mainFrame.setSize(600, 600);                              // ������ ����                                   
		mainFrame.setLocationRelativeTo(null);		             // ������������ ���� �� ��������
		System.out.println("right");                            
		
		gameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gameFrame.setSize(1800, 900);			
		gameFrame.add(new Road());                              // ����� ������ �� �����
		gameFrame.setLocationRelativeTo(null);	
		
		
		JButton buttonPlay = new JButton("����� ����");    // ������ ������ � ���������
		buttonPlay.setSize(150, 30);				      // ������ ������
		buttonPlay.setLocation(225,150);                 // ������������ ������
		buttonPlay.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));   // ������� ���
				
		mainFrame.add(buttonPlay); // ���������� ������ � ����
		
		JButton settingsButton = new JButton("���������");
		settingsButton.setSize(150, 30);
		settingsButton.setLocation(225,200);		
		settingsButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(settingsButton);
		
		JButton aboutButoon = new JButton("�� ����");
		aboutButoon.setSize(150, 30);
		aboutButoon.setLocation(225,250);		
		aboutButoon.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(aboutButoon);
		
		JButton exitButton = new JButton("�����");
		exitButton.setSize(150, 30);
		exitButton.setLocation(225,300);		
		exitButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		
		mainFrame.add(exitButton);	
				
		ImageIcon backIcon = createImageIcon("res/WOW.jpg");   // ����������� ������� ���� ����			
		JLabel JLBackgroundImage = new JLabel(backIcon);      // ������� ����������� � JLabel
		JLBackgroundImage.setBounds(0, 0, 600, 600);         // ������� JLabel`�
		mainFrame.add(JLBackgroundImage);                   // ���������� ������� ���� � ����
		
		mainFrame.setVisible(true);	                      // ��������� ����
	
		final JFrame settingsFrame = new JFrame("���������");
		settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settingsFrame.setLayout(null);
		settingsFrame.setSize(600, 600);	
		settingsFrame.setVisible(false);		
		settingsFrame.setLocationRelativeTo(null);
			
		JButton complexityButton = new JButton("���������");
		complexityButton.setSize(150, 30);
		complexityButton.setLocation(225,150);		
		complexityButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(complexityButton);
		
		JButton skinButton = new JButton("������� ���");
		skinButton.setSize(150, 30);
		skinButton.setLocation(225,200);		
		skinButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(skinButton);
		
		JFrame aboutFrame = new JFrame("�� ����");
		aboutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aboutFrame.setSize(600, 600);	
		aboutFrame.setVisible(false);
		aboutFrame.setLocationRelativeTo(null);
			
		ImageIcon img = new ImageIcon("src/res/EBEY.jpg");    // �������� ������ ����
		mainFrame.setIconImage(img.getImage());              // ���������� ������ � ����
		gameFrame.setIconImage(img.getImage());		
		
		JButton exitSettingsButton = new JButton("�����");
		exitSettingsButton.setSize(150, 30);
		exitSettingsButton.setLocation(225,250);		
		exitSettingsButton.setBorder(BorderFactory.createBevelBorder(0, Color.green, Color.orange));
		settingsFrame.add(exitSettingsButton);
		
		buttonPlay.addActionListener(new ActionListener() {    // ���������� ����������� ������
			
	           public void actionPerformed(ActionEvent e) {  // �����, ����������� ���������� ������
	        	   
	        	   RoadIsOpen = true;
	            	gameFrame.setVisible(true);             // ������� ������
	            	
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
		                	
	            public void run() {                      // ��������� ������������ ��������� �������
	            	
	                Setting.createAndShowGUI();
	                
	             }
		     });
		    }
		  });						 
    }
	
	protected static ImageIcon createImageIcon(String path) {                     // �����, ��������� ������ ����������
		
        java.net.URL imgURL = Setting.class.getResource(path);                  // ��������� ����� � ������� ����
        
        if (imgURL != null) {                                                 // ���� ���� ������                                      
        	
            return new ImageIcon(imgURL);                                   // ����������� ������ �����������
            
        } else {                                                                            
        	
            System.err.println("Couldn't find file: " + path);           // ���� ����������� �� �������
            return null;
        }
    }
}

