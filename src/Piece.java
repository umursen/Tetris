import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;



public class Piece {

	private Coordinates[] coordinatesArray;
	private BlockBoard blockBoard;
	Block[][] blockArray;
	Color pieceColor;
	Color defaultColor = new Color(255, 255, 255);
	Coordinates rotationCenter;
	private static int middlePoint = (int) (Options.BOARD_WIDTH / 2);
	private char pieceType;
	
	public Piece(char pieceType, BlockBoard board){
		blockBoard = board;
		blockArray = board.getBlockArray();
		this.pieceType = pieceType;
		
		if(pieceType == 'Z'){
			pieceColor = Color.BLUE;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 2, -2);
			coordinatesArray[1] = new Coordinates(middlePoint - 1, -2);
			coordinatesArray[2] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[3] = new Coordinates(middlePoint , -1);
			rotationCenter = new Coordinates(middlePoint , -1);
		}else if(pieceType == 'S'){
			pieceColor = Color.GREEN;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint -1 , -1);
			coordinatesArray[1] = new Coordinates(middlePoint , -1);
			coordinatesArray[2] = new Coordinates(middlePoint , -2);
			coordinatesArray[3] = new Coordinates(middlePoint + 1, -2);
			rotationCenter = new Coordinates(middlePoint, -1);
		}else if(pieceType == 'O'){
			pieceColor = Color.RED;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint -1 , -2);
			coordinatesArray[2] = new Coordinates(middlePoint , -2);
			coordinatesArray[3] = new Coordinates(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
		}else if(pieceType == 'T'){
			pieceColor = new Color (255, 102, 178);
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -2);
			coordinatesArray[1] = new Coordinates(middlePoint, -2);
			coordinatesArray[2] = new Coordinates(middlePoint, -1);
			coordinatesArray[3] = new Coordinates(middlePoint + 1, -2);
			rotationCenter = new Coordinates(middlePoint  + 0.5, -1.5);
		}else if(pieceType == 'J'){
			pieceColor = Color.MAGENTA;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint, -1);
			coordinatesArray[2] = new Coordinates(middlePoint, -2);
			coordinatesArray[3] = new Coordinates(middlePoint, -3);
			rotationCenter = new Coordinates(middlePoint, -1);
		}else if(pieceType == 'L'){
			pieceColor = Color.ORANGE;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint - 1, -2);
			coordinatesArray[2] = new Coordinates(middlePoint - 1 , -3);
			coordinatesArray[3] = new Coordinates(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
		}else if(pieceType == 'I'){
			pieceColor = Color.YELLOW;
			coordinatesArray = new Coordinates[4];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint - 1, -2);
			coordinatesArray[2] = new Coordinates(middlePoint - 1, -3);
			coordinatesArray[3] = new Coordinates(middlePoint -1 , -4);
			rotationCenter = new Coordinates(middlePoint - 0.5, -1.5);
		}else if(pieceType == 'i'){
			pieceColor = Color.CYAN;
			coordinatesArray = new Coordinates[3];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint, -1);
			coordinatesArray[2] = new Coordinates(middlePoint + 1, -1);
			rotationCenter = new Coordinates(middlePoint + 0.5, -0.5);
		}else if(pieceType == 'j'){
			pieceColor = Color.YELLOW;
			coordinatesArray = new Coordinates[3];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint, -1);
			coordinatesArray[2] = new Coordinates(middlePoint, -2);
			rotationCenter = new Coordinates(middlePoint, -1);
		}else if(pieceType == 'r'){
			pieceColor = Color.RED;
			coordinatesArray = new Coordinates[3];
			coordinatesArray[0] = new Coordinates(middlePoint - 1, -1);
			coordinatesArray[1] = new Coordinates(middlePoint - 1, -2);
			coordinatesArray[2] = new Coordinates(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
		}


	}
	public void moveDown(){
		
		removePiece();
		for(int i = 0; i < coordinatesArray.length; i++){
			coordinatesArray[i].y++;
			
		}
		rotationCenter.y++;
		paintPiece();
	}

	public void moveRight(){
		removePiece();
		for(int i = 0; i < coordinatesArray.length; i++){
			coordinatesArray[i].x++;
		}
		rotationCenter.x++;
		paintPiece();
	}

	public void moveLeft(){
		removePiece();
		for(int i = 0; i < coordinatesArray.length; i++){
			coordinatesArray[i].x--;
		}
		rotationCenter.x--;
		paintPiece();
	}
	
	public void rotate(){
		removePiece();
		for(int i = 0; i < coordinatesArray.length; i++){
			double centerX = coordinatesArray[i].getX() + 0.5;
			double centerY = coordinatesArray[i].getY() + 0.5;
			double rotationX = rotationCenter.getX();
			double rotationY = rotationCenter.getY();
			coordinatesArray[i].x = (int) (rotationX+rotationY-centerY-0.5);
			coordinatesArray[i].y = (int) (centerX-rotationX+rotationY-0.5);
		}

		paintPiece();
	}
	
	private void removePiece(){
		for(int i = 0; i < coordinatesArray.length; i++){

			if(coordinatesArray[i].getY()>=0)
				blockArray[(int) coordinatesArray[i].getY()][(int) coordinatesArray[i].getX()].setColor(defaultColor);
		}
		blockBoard.repaint();
	}
	
	public Piece getPieceCopy(){
		return new Piece(pieceType, blockBoard);
	}
	
	private void paintPiece(){
		for(int i = 0; i < coordinatesArray.length; i++){
			if(coordinatesArray[i].getY() >= 0){
				blockArray[(int) coordinatesArray[i].getY()][(int) coordinatesArray[i].getX()].setColor(pieceColor);
			}
		}
		blockBoard.repaint();
	}
	
	public char getPieceType(){
		return pieceType;
	}


}
