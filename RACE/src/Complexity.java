import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Complexity extends JPanel implements ActionListener {
 
	private static final long serialVersionUID = 1L;
	
	public static JButton b1;

	public static JButton b2;

	public static JButton b3;
	
	public static JButton b4;
	
	public static boolean EASY = true;
	public static boolean MEDIUM;
	public static boolean HARD;
	
public Complexity() {
    	
        ImageIcon leftButtonIcon = createImageIcon("res/EASY.jpg");
        ImageIcon middleButtonIcon = createImageIcon("res/MEDIUM.jpg");
        ImageIcon rightButtonIcon = createImageIcon("res/HARD.jpg");
 
        b1 = new JButton("œ–Œ—“¿ﬂ", leftButtonIcon);
        b1.setActionCommand("EASY");
        b1.setEnabled(false);
        b1.setSize(50, 30);
        b1.setLocation(100,100);
 
        b2 = new JButton("—–≈ƒÕﬂﬂ", middleButtonIcon);
        b2.setActionCommand("MEDIUM");
        b2.setSize(50, 30);
        b2.setLocation(100,150);
        
        b3 = new JButton("—ÀŒ∆Õ¿ﬂ", rightButtonIcon);
        b3.setActionCommand("HARD");        
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
		
		if ("EASY".equals(e.getActionCommand())) {
        	b1.setEnabled(false);
            b2.setEnabled(true);            
            b3.setEnabled(true);
            EASY = true;
        } else if ("MEDIUM".equals(e.getActionCommand())) {
        	b1.setEnabled(true);
            b2.setEnabled(false);            
            b3.setEnabled(true);
            MEDIUM = true;
        } else {
        	b1.setEnabled(true);
        	b2.setEnabled(true);
        	b3.setEnabled(false);            
            HARD = true;
        }
		
	}
	
	protected static ImageIcon createImageIcon(String path) {
		
        java.net.URL imgURL = Complexity.class.getResource(path);
        
        if (imgURL != null) {
        	
            return new ImageIcon(imgURL);
        } else {
        	
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	static void createAndShowGUI2() {

        final JFrame frame = new JFrame("¬€¡–¿“‹ —ÀŒ∆ÕŒ—“‹");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(900, 140);       
        Complexity newContentPane = new Complexity();        
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
