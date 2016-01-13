import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameOverPanel extends JPanel{
	private static int padding = 40;
	
	private ApplicationWindow ownerFrame;
	private ArrayList<JLabel> indexLabelList;
	private ArrayList<JLabel> nameLabelList;
	private ArrayList<JLabel> scoreLabelList;
	private HighScoreSerializer highScoreSerializer;
	private JLabel gameOver, usernameLabel;
	private JTextField enterNameField;
	private JButton replay, mainMenu;
	private int userScore;
	
	
	public GameOverPanel(ApplicationWindow ownerFrame, int userScore){
		super();
		this.ownerFrame = ownerFrame;
		setLayout(null);
		setBackground(Color.RED);
		setSize(360,540);
		
		this.userScore = userScore;
		highScoreSerializer = new HighScoreSerializer();
		
		indexLabelList = new ArrayList<JLabel>();
		nameLabelList = new ArrayList<JLabel>();
		scoreLabelList = new ArrayList<JLabel>();
		
		boolean userAdded = false;
		int counter = 0;
		for(HighScoreTuple person: highScoreSerializer.getHighScoreList()){
			if(counter >= 5) break;
			
			if(userScore>person.getScore() && !userAdded){
				JLabel indexLabel = new JLabel((counter+1)+".");
				JLabel scoreLabel = new JLabel(""+userScore);
				
				add(indexLabel);
				indexLabel.setBounds(30, 150 + counter*padding, 20, 20);;
				indexLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
				indexLabel.setForeground(Color.BLACK);
				indexLabel.setBackground(Color.RED);
				indexLabel.setOpaque(true);
				indexLabelList.add(indexLabel);
				
				enterNameField = new JTextField("Your Name Here");
				add(enterNameField);
				enterNameField.setRequestFocusEnabled(true);
				enterNameField.setFont(new Font("Tahoma", Font.BOLD, 15));
				enterNameField.setBounds(80, 150 + counter*padding, 150, 20);
				enterNameField.setForeground(Color.BLACK);
				enterNameField.setOpaque(true);
				
				enterNameField.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_ENTER){
							highScoreSerializer.newScore(enterNameField.getText(), userScore);
							remove(enterNameField);
							GameOverEndPanel gameOverEndPanel = new GameOverEndPanel(ownerFrame);
							ownerFrame.game.gameOverPanel.add(gameOverEndPanel);
							gameOverEndPanel.repaint();
							remove(replay);
							remove(mainMenu);
						};
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
				add(scoreLabel);
				scoreLabel.setForeground(Color.BLACK);
				scoreLabel.setBounds(230, 150 + counter*padding, 100, 20);
				scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));		
				scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				scoreLabel.setBackground(Color.RED);
				scoreLabel.setOpaque(true);
				scoreLabelList.add(scoreLabel);
				userAdded = true;
				
				counter++;
			}
			if(counter >= 5) break;
			JLabel indexLabel = new JLabel((counter+1)+".");
			JLabel nameLabel = new JLabel(person.getName());
			JLabel scoreLabel = new JLabel(""+person.getScore());
			
			add(indexLabel);
			indexLabel.setBounds(30, 150 + counter*padding, 20, 20);;
			indexLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			indexLabel.setForeground(Color.BLACK);
			indexLabel.setBackground(Color.RED);
			indexLabel.setOpaque(true);
			indexLabelList.add(indexLabel);
			
			add(nameLabel);
			nameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));	
			nameLabel.setForeground(Color.BLACK);
			nameLabel.setBounds(80, 150 + counter*padding, 150, 20);
			nameLabel.setBackground(Color.RED);
			nameLabel.setOpaque(true);
			nameLabelList.add(nameLabel);
			
			add(scoreLabel);
			scoreLabel.setForeground(Color.BLACK);
			scoreLabel.setBounds(230, 150 + counter*padding, 100, 20);
			scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));		
			scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreLabel.setBackground(Color.RED);
			scoreLabel.setOpaque(true);
			scoreLabelList.add(scoreLabel);
			
			counter++;
		}
		
		if(!userAdded && counter<5){
			JLabel indexLabel = new JLabel((counter+1)+".");
			JLabel scoreLabel = new JLabel(""+userScore);
			
			add(indexLabel);
			indexLabel.setBounds(30, 150 + counter*padding, 20, 20);;
			indexLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			indexLabel.setForeground(Color.BLACK);
			indexLabel.setBackground(Color.RED);
			indexLabel.setOpaque(true);
			indexLabelList.add(indexLabel);
			
			enterNameField = new JTextField("Your Name Here");
			add(enterNameField);
			enterNameField.setRequestFocusEnabled(true);
			enterNameField.setFont(new Font("Tahoma", Font.BOLD, 15));
			enterNameField.setBounds(80, 150 + counter*padding, 150, 20);
			enterNameField.setForeground(Color.BLACK);
			enterNameField.setOpaque(true);

			enterNameField.addKeyListener(new KeyListener(){

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						usernameLabel = new JLabel(enterNameField.getText());
						highScoreSerializer.newScore(enterNameField.getText(), userScore);
						remove(enterNameField);
						GameOverEndPanel gameOverEndPanel = new GameOverEndPanel(ownerFrame);
						ownerFrame.game.gameOverPanel.add(gameOverEndPanel);
						remove(replay);
						remove(mainMenu);
						
						gameOverEndPanel.repaint();		
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			add(scoreLabel);
			scoreLabel.setForeground(Color.BLACK);
			scoreLabel.setBounds(230, 150 + counter*padding, 100, 20);
			scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));		
			scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreLabel.setBackground(Color.RED);
			scoreLabel.setOpaque(true);
			scoreLabelList.add(scoreLabel);
			
			counter++;
		}

		gameOver = new JLabel("Game Over"); 
 		replay = new JButton("Replay");
 		mainMenu= new JButton("Main Menu");
		
 		add(gameOver);
 		gameOver.setBounds(0, 50, 360, 60);
 		gameOver.setForeground(Color.BLACK);
 		gameOver.setFont(new Font("Tahoma", Font.BOLD, 40));
 		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
 		
 		add(replay);
 		replay.setBounds(40, 400, 130, 50);
 		replay.setBorderPainted(false);
		replay.setFocusPainted(false);
		
		add(mainMenu);
 		mainMenu.setBounds(190, 400, 130, 50);
 		mainMenu.setBorderPainted(false);
		mainMenu.setFocusPainted(false);
		
		replay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(enterNameField!=null){
					String name = enterNameField.getText();
					if(name.equals("Your Name Here")){
						highScoreSerializer.newScore("No Name", userScore);
					}else{
						highScoreSerializer.newScore(name, userScore);
					}
				}
				
				ownerFrame.remove(ownerFrame.game);
				ownerFrame.game = new GamePanel(ownerFrame);
				ownerFrame.add(ownerFrame.game);
				ownerFrame.game.requestFocusInWindow();
				ownerFrame.game.startGame();
				ownerFrame.game.repaint();
			}
			
		});
		
		mainMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(enterNameField!=null){
					String name = enterNameField.getText();
					if(name.equals("Your Name Here")){
						highScoreSerializer.newScore("No Name", userScore);
					}else{
						highScoreSerializer.newScore(name, userScore);
					}
				}
				
				ownerFrame.remove(ownerFrame.game);
				ownerFrame.add(ownerFrame.menu);
				ownerFrame.menu.repaint();
			}
		});
		
		repaint();
	}
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 360, 540);
		
		gameOver.repaint();
		gameOver.setOpaque(true);
		gameOver.setBackground(Color.RED);
		
		replay.repaint();
		mainMenu.repaint();
		
		for(int i=0; i<indexLabelList.size(); i++)
			indexLabelList.get(i).repaint();
		for(int i=0; i<nameLabelList.size(); i++)
			nameLabelList.get(i).repaint();
		for(int i=0; i<scoreLabelList.size(); i++)
			scoreLabelList.get(i).repaint();
		
		if(enterNameField!=null){
			enterNameField.repaint();
		}
	}
	
}
