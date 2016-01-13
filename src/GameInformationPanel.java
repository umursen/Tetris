import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;


public class GameInformationPanel extends JPanel{;
	private JLabel level,lines,score,levelInput,linesInput,scoreInput;
	private ScoreCalculator scoreCalculator;
	
	public GameInformationPanel(ScoreCalculator scoreCalculator){
		super();
		this.scoreCalculator = scoreCalculator;
		
		level = new JLabel("Level");
		lines = new JLabel("Lines");
		score = new JLabel("Score");
		levelInput = new JLabel(""+Options.LEVEL);
		linesInput = new JLabel("0");
		scoreInput = new JLabel("0");
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		add(level);
		level.setBounds(0, 0,80,30);
		level.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(lines);
		lines.setBounds(0, 60, 80, 30);
		lines.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(score);
		score.setBounds(0, 120, 80, 30);
		score.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(levelInput);
		levelInput.setBounds(100, 0,80,30);
		levelInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(linesInput);
		linesInput.setBounds(100, 60, 80, 30);
		linesInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		add(scoreInput);
		scoreInput.setBounds(100, 120, 80, 30);
		scoreInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		level.setOpaque(true);
		level.setBackground(Constants.GAME_BACKGROUND_COLOR);
		level.setForeground(Constants.TITLE_COLOR);
		
		lines.setOpaque(true);
		lines.setBackground(Constants.GAME_BACKGROUND_COLOR);
		lines.setForeground(Constants.TITLE_COLOR);
		
		score.setOpaque(true);
		score.setBackground(Constants.GAME_BACKGROUND_COLOR);
		score.setForeground(Constants.TITLE_COLOR);
		
		levelInput.setOpaque(true);
		levelInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		levelInput.setForeground(Constants.TITLE_COLOR);
		
		linesInput.setOpaque(true);
		linesInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		linesInput.setForeground(Constants.TITLE_COLOR);
		
		scoreInput.setOpaque(true);
		scoreInput.setBackground(Constants.GAME_BACKGROUND_COLOR);
		scoreInput.setForeground(Constants.TITLE_COLOR);
		
	}
		public void paint(Graphics g){
			super.paintComponent(g);
			
			levelInput.setText(""+scoreCalculator.getLevel());
			linesInput.setText(""+scoreCalculator.getLinesDeleted());
			scoreInput.setText(""+scoreCalculator.getScore());
			
	    	level.repaint();
	    	lines.repaint();
	    	score.repaint();
	    	
	    	levelInput.repaint();
	    	linesInput.repaint();
	    	scoreInput.repaint();
	    	
	    	
	    	
			
	}
}
