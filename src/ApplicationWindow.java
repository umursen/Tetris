import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame {
	protected MenuPanel menu;
	protected OptionsPanel options;
	protected GamePanel game;
	protected HighScoresPanel highScores;

	public ApplicationWindow() {
		super("TeTriS");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(game!=null){
					game.running=false;
				}
				System.exit(0);
			}
		});
		
		setLayout(new GridLayout(1,1));
		setVisible(true);
		setResizable(false);
		
		
		menu = new MenuPanel(this);
		options = new OptionsPanel(this);
		
		
		add(menu);
		
		
	}
}