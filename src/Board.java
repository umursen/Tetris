import java.util.ArrayList;


public class Board {
	int boardWidth;
	int boardHeight;
	boolean[][] blockPlaces; // first index represents rows and second represents columns. southwest is (0,0), one north southwest is (1,0), one east southwest is (0,1) etc.
	PieceLogic currentShape;
	boolean isGameOver;
	
	
	public Board(ScoreCalculator argScoreCalculator) {
		this.boardWidth = Options.BOARD_WIDTH;
		this.boardHeight = Options.BOARD_HEIGHT;
		this.blockPlaces = new boolean[boardWidth][boardHeight];
		this.isGameOver = false;
	}
	
	public Board(Board board) {
		this.boardWidth = board.getBoardWidth();
		this.boardHeight = board.getBoardHeight();
		this.blockPlaces = new boolean[boardWidth][boardHeight];
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public boolean getBlock(int x, int y) {
		return blockPlaces[x][y];
	}

	public void setBlock(int x, int y) {
		this.blockPlaces[x][y] = true;
	}
	
	public void removeBlock(int x, int y){
		this.blockPlaces[x][y] = false;
	}

	
	public PieceLogic getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(PieceLogic currentShape) {
		this.currentShape = currentShape;
	}
	
	public boolean[][] getBlockPlaces() {
		return blockPlaces;
	}

	public void setBlockPlaces(boolean[][] blockPlaces) {
		this.blockPlaces = blockPlaces;
	}

	public ArrayList<Integer> deleteLines(ScoreCalculator score){
		ArrayList<Integer> deleteIndices = new ArrayList<>();
		for(int i = boardHeight - 1; i > 0; i--){
			for(int j=0;j<boardWidth;j++){
				if(!blockPlaces[j][i]) //if a block is empty, break the inner for and start checking the line above.
				{
					break;
				}
				if(j == boardWidth - 1){
					deleteIndices.add(i);
					deleteLine(i);
					i++;//since the line at i is deleted, the line at i+1 is not at line i and hence line i needs to be checked again. Also when a line is deleted, the upper limit for lines to check may be decremented as well but it is not a must.  	
				}
			}
		}
		if(deleteIndices.size()>0)
			score.calculateScore(deleteIndices.size());
			if((score.getLevel()== (score.getLinesDeleted()/10)+Options.LEVEL-1) && score.getLevel()!= 5){
				Options.setLevel((score.getLinesDeleted()/10) +Options.LEVEL);
			}
			score.updated = true;
		return deleteIndices;
	}
	
	void deleteLine(int lineIndex){
		while(lineIndex > 0){
			for(int i=0;i<boardWidth;i++){
				blockPlaces[i][lineIndex] = blockPlaces[i][lineIndex - 1];
			}
			lineIndex--;
		}
	}
	
	boolean pointBelongsCurrentShape(Coordinates point){
		for(int i=0;i<currentShape.Blocks.length;i++)
			if(currentShape.Blocks[i].getCenter().equals(point))
				return true;
		return false;
	}
	
	void isGameOver(){
		for(int i=0;i<boardWidth;i++)
			if(blockPlaces[i][0])
				isGameOver = true;
		isGameOver = false;
	}
	
	void setCurrentShapePlacesTrue(){
		for(int i=0;i<currentShape.Blocks.length;i++){
			BlockLogic currentBlock = currentShape.Blocks[i];
			if(currentBlock.getCenter().getY() <= 0){
				// tell cem that game is over and return from the method.
				isGameOver = true;
				return;
			}
			else{
				blockPlaces[(int) currentBlock.getCenter().getX()][(int) currentBlock.getCenter().getY()] = true;
			}
		}
	}
}
