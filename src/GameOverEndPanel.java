import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameOverEndPanel extends JPanel{
	private static int padding = 40;
	
	private ApplicationWindow ownerFrame;
	private ArrayList<JLabel> indexLabelList;
	private ArrayList<JLabel> nameLabelList;
	private ArrayList<JLabel> scoreLabelList;
	private HighScoreSerializer highScoreSerializer;
	private JLabel gameOver;
	private JButton replay, mainMenu;

	
	
	public GameOverEndPanel(ApplicationWindow ownerFrame){
		super();
		this.ownerFrame = ownerFrame;
		setLayout(null);
		setBackground(Color.RED);
		setSize(360,540);
		
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
		
	}
	
}
