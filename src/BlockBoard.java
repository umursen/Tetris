import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.*;
import java.io.*;
public class BlockBoard extends JPanel {
	
	private ApplicationWindow ownerFrame;
	private Block[][] blockArray;
	private Piece currentPiece;
	protected Piece nextPiece;
	private Timer timer;
	private int delay = Options.SPEED;
	private PieceLogic controller;
	private Board boardLogic;
	private RandomPieceGenerator randomPiece;
	ScoreCalculator score;

	public BlockBoard(ApplicationWindow ownerFrame, ScoreCalculator calc){
		super();
		this.ownerFrame = ownerFrame;
		int height = Options.BOARD_HEIGHT;
		int width = Options.BOARD_WIDTH;
		blockArray = new Block[height][width];
		randomPiece = new RandomPieceGenerator();
		score = calc;
		generateInitialNextPiece();
		boardLogic = new Board(score);
		controller = new PieceLogic(boardLogic, currentPiece.getPieceType());
		boardLogic.setCurrentShape(controller);
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				blockArray[i][j] = new Block((j) * Options.BLOCK_SIZE, (i) * Options.BLOCK_SIZE);
			}
		}

		timer = new Timer(Options.SPEED, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(boardLogic.isGameOver){
					timer.stop();
					soundEffect("sounds/gameOver.wav");
					ownerFrame.game.endGame();
				}else{
					if(controller.moveDown(boardLogic)){
						currentPiece.moveDown();
					}else{
						soundEffect("sounds/blockPlaced.wav");
						removePieces();
						generateNextPiece();
						controller = new PieceLogic(boardLogic, currentPiece.getPieceType());
						boardLogic.currentShape = controller;
					}
				}
			}
		});
		timer.start();
	}

	public boolean isFocusTraversable() { return true; }

	public Block[][] getBlockArray(){
		return blockArray;
	}

	private void removePieces(){
		ArrayList<Integer> linesToRemove = boardLogic.deleteLines(score);
		if(linesToRemove.size() > 0){
			soundEffect("sounds/lineDestroyed.wav");
		}
		for(int i = 0; i < linesToRemove.size(); i++){
			moveAllPiecesDown(linesToRemove.get(i));
		}
	}

	public void generateNextPiece(){

		randomPiece.generateRandomPiece();
		currentPiece = nextPiece.getPieceCopy();
		nextPiece = new Piece(randomPiece.getRandomPiece(), this);
		ownerFrame.game.nextPiecePanel.repaint();
		
	}
	public void generateInitialNextPiece(){

		randomPiece.generateRandomPiece();
		currentPiece = new Piece(randomPiece.getRandomPiece(), this);
		randomPiece.generateRandomPiece();
		nextPiece = new Piece(randomPiece.getRandomPiece(), this);
		

	}

	private void moveAllPiecesDown(int indexToMove){
		if (true){
			for(int i = 0; i < Options.BOARD_WIDTH; i++){
				for(int j = indexToMove; j > 0; j--){
					blockArray[j][i].setColor(blockArray[j - 1][i].getColor());
				}
			}
			repaint();
		}
	}

	public void movePieceRight(){
		if(controller.moveRight(boardLogic)){
			currentPiece.moveRight();
		}
	}

	public void movePieceLeft(){
		if(controller.moveLeft(boardLogic)){
			currentPiece.moveLeft();
		}
	}

	public void rotatePiece(){
		if(controller.rotate(boardLogic)){
			currentPiece.rotate();
		}
	}

	public void startPause(){
		timer.stop();
	}


	public void stopPause(){
		timer.setDelay(delay);
		timer.start();
	}


	public void moveFastStart(){
		timer.setDelay(delay/2);
	}


	public void moveFastStop(){
		timer.setDelay(delay);
	}

	public void paintComponent(Graphics g){
		for(int i = 0; i < Options.BOARD_HEIGHT; i++){
			for(int j = 0; j < Options.BOARD_WIDTH; j++){
				blockArray[i][j].paintComponent(g);
			}
		}
	}
	
	private void soundEffect(String soundToPlay){
		AudioPlayer audioPlayer = AudioPlayer.player;
		AudioStream audioStream;
		AudioData audioData;
		AudioDataStream audioDataStream;
		
		try {
			audioStream = new AudioStream(new FileInputStream(soundToPlay));
			audioData = audioStream.getData();
			audioDataStream = new AudioDataStream(audioData);
			audioPlayer.start(audioDataStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
