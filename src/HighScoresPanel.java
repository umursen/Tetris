import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class HighScoresPanel extends JPanel{
	private static int RowLocation = Constants.SCREEN_WIDTH/3-50; 
	private static int padding = 50;
	
	private ApplicationWindow ownerFrame;
	private HighScoreSerializer highScoreSerializer;
	private ArrayList<JLabel> indexLabelList;
	private ArrayList<JLabel> nameLabelList;
	private ArrayList<JLabel> scoreLabelList;
	private JLabel highScores;
	private JLabel noHighScore;
	private JButton back;
	public HighScoresPanel(ApplicationWindow ownerFrame){
		this.ownerFrame = ownerFrame;
		highScoreSerializer = new HighScoreSerializer();
		
		indexLabelList = new ArrayList<JLabel>();
		nameLabelList = new ArrayList<JLabel>();
		scoreLabelList = new ArrayList<JLabel>();
		
		int counter = 0;
		for(HighScoreTuple person: highScoreSerializer.getHighScoreList()){
			JLabel indexLabel = new JLabel((counter+1)+".");
			JLabel nameLabel = new JLabel(person.getName());
			JLabel scoreLabel = new JLabel(""+person.getScore());
			
			add(indexLabel);
			indexLabel.setBounds(Constants.SCREEN_WIDTH/2 -180, RowLocation+padding*counter,50, 60);
			indexLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			indexLabel.setBackground(Constants.GAME_BACKGROUND_COLOR);
			indexLabel.setOpaque(true);
			
			add(scoreLabel);
			scoreLabel.setBounds(Constants.SCREEN_WIDTH/2 +40, RowLocation+padding*counter,200, 60);
			scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 20));		
			scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreLabel.setBackground(Constants.GAME_BACKGROUND_COLOR);
			scoreLabel.setOpaque(true);
			
			add(nameLabel);
			nameLabel.setBounds(Constants.SCREEN_WIDTH/2 - 130, RowLocation+padding*counter,170, 60);
			nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			nameLabel.setBackground(Constants.GAME_BACKGROUND_COLOR);
			nameLabel.setOpaque(true);
			
			indexLabelList.add(indexLabel);
			nameLabelList.add(nameLabel);
			scoreLabelList.add(scoreLabel);
			
			counter++;
		}
		
		highScores = new JLabel("High Scores");
		noHighScore = new JLabel("There is currently no records of a score.");
		back = new JButton("Back");

		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

		add(highScores);
		highScores.setBounds(0,50,Constants.SCREEN_WIDTH,120);
		highScores.setFont(new Font("Tahoma", Font.BOLD, 40));
		highScores.setForeground(Constants.TITLE_COLOR);
		highScores.setHorizontalAlignment(SwingConstants.CENTER);
		highScores.setOpaque(true);
		highScores.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		add(noHighScore);
		noHighScore.setBounds(0,150,Constants.SCREEN_WIDTH,120);
		noHighScore.setFont(new Font("Tahoma", Font.BOLD, 30));
		noHighScore.setForeground(Color.BLACK);
		noHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		noHighScore.setOpaque(true);
		noHighScore.setBackground(Constants.GAME_BACKGROUND_COLOR);
		if(highScoreSerializer.getHighScoreNumber()==0)
			noHighScore.setVisible(true);
		else
			noHighScore.setVisible(false);
		
		
		add(back);
		back.setBounds(50, 580, 100, 30);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {	
				ownerFrame.remove(ownerFrame.highScores);
				ownerFrame.add(ownerFrame.menu);
				ownerFrame.menu.repaint();
			}
			
		});
		
		repaint();

	}

	public void paint(Graphics g){
		super.paintComponent(g);
		highScores.repaint();
		noHighScore.repaint();
		back.repaint();
		for(int i=0; i<indexLabelList.size(); i++){
			indexLabelList.get(i).repaint();
			nameLabelList.get(i).repaint();
			scoreLabelList.get(i).repaint();
		}

	}
}
