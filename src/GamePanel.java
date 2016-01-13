import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.LinkedList;
import java.util.Iterator;

public class GamePanel extends JPanel {
	private static int LabelXPadding = Constants.SCREEN_WIDTH*11/16;  
	private static int LabelYPadding = Constants.SCREEN_HEIGHT*6/16;
	
	private ApplicationWindow ownerFrame;
	protected BlockBoard blockboard;
	private GameInformationPanel informationPanel;
	protected NextPiecePanel nextPiecePanel;
	private PausePanel pausePanel;
	protected GameOverPanel gameOverPanel;
	Controller controller;
	GameKeyEventList gameKeyEventList;
	protected boolean running=true;
	private ScoreCalculator scoreCalculator;
	
	public GamePanel(ApplicationWindow ownerFrame) {
		super();
		this.ownerFrame = ownerFrame;
		scoreCalculator = new ScoreCalculator();
		
		informationPanel = new GameInformationPanel(scoreCalculator);
		
		pausePanel = new PausePanel();
		
		add(informationPanel);
		informationPanel.setBounds(LabelXPadding, LabelYPadding, 
				Constants.INFORMATIONPANEL_SIZE,Constants.INFORMATIONPANEL_SIZE);
		
		
		blockboard = new BlockBoard(ownerFrame, scoreCalculator);
		
		
		nextPiecePanel = new NextPiecePanel(ownerFrame);
		
		gameKeyEventList = new GameKeyEventList();
		controller = new Controller(gameKeyEventList);
		addKeyListener(controller);
		requestFocusInWindow();
		
		new Thread(new Runnable(){

			public void run() {

				while(running){
					gameUpdate();
					scoreUpdate();
				}				
			}
			
		}).start();
		
		
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		add(pausePanel);
		pausePanel.setBounds(40, 60, 360, 540);

		add(informationPanel);
		informationPanel.setBounds(LabelXPadding, LabelYPadding, 
				Constants.INFORMATIONPANEL_SIZE,Constants.INFORMATIONPANEL_SIZE);
		
		add(nextPiecePanel);
		nextPiecePanel.setBounds(445, 65, 210, 140);
		
		
		add(blockboard);
		blockboard.setBounds(40, 60, 360, 540);
		
		
		
				
		
	}
	public void startGame(){
		running=true;
	}
	
	public void endGame(){
		running = false;
		gameOverPanel = new GameOverPanel(ownerFrame, scoreCalculator.getScore());
		remove(blockboard);
		
		gameOverPanel.setBounds(40, 60, 360, 540);
		add(gameOverPanel);
		gameOverPanel.repaint();
	}
	
	private void gameUpdate(){ 
		if(gameKeyEventList.size() != 0){
			doKeyEvents(gameKeyEventList.migrateList());
		}
	}
	
	private void scoreUpdate(){
		if(scoreCalculator.updated){
			informationPanel.repaint();
			scoreCalculator.updated = false;
		}
		
	}
	
	private void doKeyEvents(LinkedList<GameKeyEvent> list){

		for(Iterator<GameKeyEvent> i = list.iterator(); i.hasNext();){
			GameKeyEvent currentEvent = i.next();
			switch(currentEvent.getKey()){
			case GameEvents.KEY_EVENT_ROTATE: blockboard.rotatePiece(); break;
			case GameEvents.KEY_EVENT_MOVE_RIGHT: blockboard.movePieceRight(); break;
			case GameEvents.KEY_EVENT_MOVE_LEFT: blockboard.movePieceLeft(); break;
			case GameEvents.KEY_EVENT_MOVE_FAST_START: blockboard.moveFastStart(); break;
			case GameEvents.KEY_EVENT_MOVE_FAST_STOP: blockboard.moveFastStop(); break;
			case GameEvents.KEY_EVENT_PAUSE_START: 
				blockboard.startPause(); 
				pausePanel.repaint();
				break;
			case GameEvents.KEY_EVENT_PAUSE_STOP: 
				blockboard.repaint();
				blockboard.stopPause(); 
				break;
			}
		}
	}

	public void paint(Graphics g){
		/* For some reason background color won't change so a fillRect is put in to background */
		g.setColor(Constants.GAME_BACKGROUND_COLOR);
		g.fillRect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		g.setColor(new Color(0,0,0));
		g.fillRect(440, 60,220,150);
		g.setColor(new Color(0,0,0));
    	g.fillRect(30,50, 380, 560);
    	
    	informationPanel.repaint();
    	nextPiecePanel.repaint();
		blockboard.repaint();
	}

}
