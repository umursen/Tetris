import java.awt.*;

import javax.swing.*;

public class PausePanel extends JPanel {
	private JLabel paused;
	public PausePanel(){
		super();
		
		paused = new JLabel("PAUSED");
		setLayout(null);
		setBackground(Color.BLACK);
		
		add(paused);
		paused.setBounds(130,Constants.SCREEN_HEIGHT/2-110,Constants.SCREEN_WIDTH,30);
		paused.setFont(new Font("Tahoma", Font.BOLD, 30));
		paused.setForeground(Color.WHITE);
		
		
		
		repaint();
	}
	public void paint(Graphics g){
		super.paintComponent(g);
		paused.repaint();
		paused.setOpaque(true);
		paused.setBackground(Color.BLACK);
	}
}
