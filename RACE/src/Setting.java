import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Setting extends JPanel implements ActionListener {
	
	public static boolean RED;
	public static boolean BLUE;
	public static boolean YELLOW = true;
	
	private static final long serialVersionUID = 1L;
	
	public static JButton b1;

	public static JButton b2;

	public static JButton b3;
	
	public static JButton b4;
 
    public Setting() {
    	
        ImageIcon leftButtonIcon = createImageIcon("res/BLUESKIN.png");
        ImageIcon middleButtonIcon = createImageIcon("res/REDSKIN.png");
        ImageIcon rightButtonIcon = createImageIcon("res/YELLOWSKIN.png");
 
        b1 = new JButton("—»Õﬂﬂ", leftButtonIcon);
        b1.setActionCommand("BLUE");
        b1.setSize(50, 30);
        b1.setLocation(100,100);
 
        b2 = new JButton("¡≈À¿ﬂ", middleButtonIcon);
        b2.setActionCommand("RED");
        b2.setSize(50, 30);
        b2.setLocation(100,150);
        
        b3 = new JButton("∆®À“¿ﬂ", rightButtonIcon);
        b3.setActionCommand("YELLOW");
        b3.setEnabled(false);
        b3.setSize(50, 30);
        b3.setLocation(100,200);
        
        b4 = new JButton("¬˚·‡Ú¸");
        b4.setSize(50, 30);
        b4.setLocation(200,200);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
    }
    
    public void actionPerformed(ActionEvent e) {
    	
        if ("BLUE".equals(e.getActionCommand())) {
        	
        	b1.setEnabled(false);
            b2.setEnabled(true);            
            b3.setEnabled(true);
            BLUE = true;
        } else if ("RED".equals(e.getActionCommand())) {
        	
        	b1.setEnabled(true);
            b2.setEnabled(false);            
            b3.setEnabled(true);
            RED = true;
        } else {
        	
        	b1.setEnabled(true);
        	b2.setEnabled(true);
        	b3.setEnabled(false);            
            YELLOW = true;
        }  
    }

    protected static ImageIcon createImageIcon(String path) {
    	
        java.net.URL imgURL = Setting.class.getResource(path);
        if (imgURL != null) {
        	
            return new ImageIcon(imgURL);
        } else {
        	
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    static void createAndShowGUI() {

        final JFrame frame = new JFrame("¬€¡–¿“‹ Ã¿ÿ»Õ ”");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(900, 140);       
        Setting newContentPane = new Setting();        
        frame.add(newContentPane);               
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
}


