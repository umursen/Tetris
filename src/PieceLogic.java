
public class PieceLogic {
	BlockLogic[] Blocks;
	Coordinates rotationCenter;
	Board board;
	private static int middlePoint = (int) (Options.BOARD_WIDTH / 2);
	ScoreCalculator score;

	public PieceLogic(Board argBoard, char shapeTypeNumber) {
		super();
		this.board = argBoard;
		this.board.currentShape = this;
		score = new ScoreCalculator();
		
		switch (shapeTypeNumber) {
		case 'I':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint - 1, -2);
			Blocks[2] = new BlockLogic(middlePoint - 1, -3);
			Blocks[3] = new BlockLogic(middlePoint -1 , -4);
			rotationCenter = new Coordinates(middlePoint - 0.5, -1.5);
			break;
		case 'J':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint, -1);
			Blocks[2] = new BlockLogic(middlePoint, -2);
			Blocks[3] = new BlockLogic(middlePoint, -3);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		case 'L':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint - 1, -2);
			Blocks[2] = new BlockLogic(middlePoint - 1 , -3);
			Blocks[3] = new BlockLogic(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		case 'O':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint -1 , -2);
			Blocks[2] = new BlockLogic(middlePoint , -2);
			Blocks[3] = new BlockLogic(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		case 'S':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint -1 , -1);
			Blocks[1] = new BlockLogic(middlePoint , -1);
			Blocks[2] = new BlockLogic(middlePoint , -2);
			Blocks[3] = new BlockLogic(middlePoint + 1, -2);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		case 'T':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 1, -2);
			Blocks[1] = new BlockLogic(middlePoint, -2);
			Blocks[2] = new BlockLogic(middlePoint, -1);
			Blocks[3] = new BlockLogic(middlePoint + 1, -2);
			rotationCenter = new Coordinates(middlePoint  + 0.5, -1.5);
			break;
		case 'Z':
			Blocks = new BlockLogic[4];
			Blocks[0] = new BlockLogic(middlePoint - 2, -2);
			Blocks[1] = new BlockLogic(middlePoint - 1, -2);
			Blocks[2] = new BlockLogic(middlePoint - 1, -1);
			Blocks[3] = new BlockLogic(middlePoint , -1);
			rotationCenter = new Coordinates(middlePoint , -1);
			break;
		case 'i':
			Blocks = new BlockLogic[3];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint, -1);
			Blocks[2] = new BlockLogic(middlePoint + 1, -1);
			rotationCenter = new Coordinates(middlePoint + 0.5, -0.5);
			break;
		case 'j':
			Blocks = new BlockLogic[3];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint, -1);
			Blocks[2] = new BlockLogic(middlePoint, -2);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		case 'r':
			Blocks = new BlockLogic[3];
			Blocks[0] = new BlockLogic(middlePoint - 1, -1);
			Blocks[1] = new BlockLogic(middlePoint - 1, -2);
			Blocks[2] = new BlockLogic(middlePoint, -1);
			rotationCenter = new Coordinates(middlePoint, -1);
			break;
		default:
			break;
		}
	}

	public boolean rotate(Board board) {
		//first check if every BlockLogic is rotatable.
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].updateBlockLogicInformation(rotationCenter, board);
		}
		//then return w/o rotation if a BlockLogic is not rotatable
		for(int i=0;i<Blocks.length;i++){
			if(!Blocks[i].isDestinationValid())
				return false;
		}
		
		//perform the rotation if all Blocks are rotatable
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].blockPosition = Blocks[i].destCoor;

		}

		this.board.setCurrentShape(this);
		return true;
	}

	public boolean moveRight(Board board) {
		
		//first check if every BlockLogic is movable.
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].updateBlockLogicInformation(2, board);
		}
		
		//then return w/o updating if destination is not valid
		for(int i=0;i<Blocks.length;i++){
			if(!Blocks[i].isDestinationValid())
				return false;
		}
		
		//perform the movement if all Blocks' destinations are valid
		this.rotationCenter.x++;
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].blockPosition = Blocks[i].destCoor;
		}
		
		this.board.setCurrentShape(this);
		return true;
	}

	public boolean moveDown(Board board) {
		
		//first check if every BlockLogic is movable.
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].updateBlockLogicInformation(3, board);
		}
		
		//then return w/o updating if destination is not valid. Also, set the current location of the
		//shape in the board as occupied. Also check and delete lines to be deleted.
		for(int i=0;i<Blocks.length;i++){
			if(!Blocks[i].isDestinationValid()){
				board.setCurrentShapePlacesTrue();
				return false;
			}
		}
		this.rotationCenter.y++;
		
		//perform the movement if all Blocks' destinations are valid
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].blockPosition = Blocks[i].destCoor;
		}
		this.board.setCurrentShape(this);
		return true;
	}

	public boolean moveLeft(Board board) {
		
		//first check if every BlockLogic is movable.
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].updateBlockLogicInformation(1, board);
		}
		
		//then return w/o updating if destination is not valid
		for(int i=0;i<Blocks.length;i++){
			if(!Blocks[i].isDestinationValid())
				return false;
		}
		
		//perform the movement if all Blocks' destinations are valid
		rotationCenter.x--;
		for(int i=0;i<Blocks.length;i++){
			Blocks[i].blockPosition = Blocks[i].destCoor;
		}
		this.board.setCurrentShape(this);
		return true;
	}
}
