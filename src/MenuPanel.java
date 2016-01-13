import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MenuPanel extends JPanel {
	private ApplicationWindow ownerFrame;
	private JButton start,options,exit,scores;
	private JLabel label;
	public MenuPanel(ApplicationWindow ownerFrame) {
		this.ownerFrame = ownerFrame;
		label = new JLabel("Tetris",JLabel.CENTER);
		start = new JButton("Start");
		scores = new JButton("High Scores");
		options = new JButton("Options");
		exit = new JButton("Exit");
		label.setFont(new Font("Tahoma", Font.BOLD, 50));
		label.setForeground(Constants.TITLE_COLOR);

		setLayout(new GridBagLayout());
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		GridBagConstraints c = new GridBagConstraints();

		c.gridx= 0;
		c.gridy= 0;
		c.insets = new Insets(10,10,10,10);		

		add(label, c);

		c.gridx= 0;
		c.gridy= 1;
		add(start,c);

		c.gridx= 0;
		c.gridy= 2;
		add(scores,c);

		c.gridx= 0;
		c.gridy= 3;
		add(options,c);


		c.gridx= 0;
		c.gridy= 4;
		add(exit,c);
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ownerFrame.game = new GamePanel(ownerFrame);
				
				
				ownerFrame.remove(ownerFrame.menu);
				ownerFrame.add(ownerFrame.game);
				ownerFrame.game.requestFocusInWindow();
				ownerFrame.game.startGame();
				ownerFrame.game.repaint();
			}
		});
		
		scores.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ownerFrame.highScores = new HighScoresPanel(ownerFrame);
				
				ownerFrame.remove(ownerFrame.menu);
				ownerFrame.add(ownerFrame.highScores);
				ownerFrame.highScores.repaint();
				
			}
			
		});
		
		options.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ownerFrame.remove(ownerFrame.menu);
				ownerFrame.add(ownerFrame.options);
				ownerFrame.options.repaint();
				
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});	
	}
}
