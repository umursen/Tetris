
public class BlockLogic {

	Coordinates blockPosition;
	boolean isDestinationValid;
	Coordinates destCoor;

	public BlockLogic(int x, int y) {
		super();
		blockPosition = new Coordinates();
		this.blockPosition.setX(x);
		this.blockPosition.setY(y);
		/*this.destCoor = null;
		this.isDestinationValid = false;*/
	}

	public Coordinates getCenter() {
		return blockPosition;
	}

	public void setCenter(Coordinates blockPosition) {
		this.blockPosition = blockPosition;
	}

	public Coordinates getDestCoor() {
		return destCoor;
	}

	public void setDestCoor(Coordinates destCoor) {
		this.destCoor = destCoor;
	}

	public boolean isDestinationValid() {
		return isDestinationValid;
	}

	public void setDestinationValid(boolean isDestinationValid) {
		this.isDestinationValid = isDestinationValid;
	}

	public void updateBlockLogicInformation(int direction, Board board){
		setDestinationCoordinates(direction);
		checkDestinationCoordinates(board);
	}

	void updateBlockLogicInformation(Coordinates rotationCenter, Board board){
		set90DestinationCoordinates(rotationCenter);
		checkDestinationCoordinates(board);
	}

	void setDestinationCoordinates(int direction){
		/*
		 * 1: left
		 * 2: right
		 * 3: down
		 */
		switch (direction) {
		case 1:
			destCoor = new Coordinates(blockPosition.getX() - 1, blockPosition.getY());
			break;
		case 2:
			destCoor = new Coordinates(blockPosition.getX() + 1, blockPosition.getY());
			break;

		default://case 3
			destCoor = new Coordinates(blockPosition.getX(), blockPosition.getY() + 1);
			break;
		}
	}

	void set90DestinationCoordinates(Coordinates rotationCenter){
		//does not check if something is on the way!
		//also, returns a double value. Must be rounded to represent a true block blockPosition before use.
		//according to this method, rotation blockPosition may be located outside the board.
		double rotationX = rotationCenter.getX();
		double rotationY = rotationCenter.getY();
		double centerX = blockPosition.getX() + 0.5;
		double centerY = blockPosition.getY() + 0.5;
		destCoor = new Coordinates((rotationX+rotationY-centerY - 0.5),  (centerX-rotationX+rotationY - 0.5));
		
	}

	void checkDestinationCoordinates(Board board){
		// fails if called before calling get90DestinationCoordinates. 

		if(destCoor.getX() > (board.getBoardWidth() - 1) || destCoor.getX() < 0){
			isDestinationValid = false;
			return;
		}
		if(destCoor.getY() > (board.getBoardHeight() - 1) ){
			isDestinationValid = false;
			return;
		}
		if(destCoor.getY() >= 0){
			if(board.getBlock((int) (destCoor.getX()), (int) (destCoor.getY()))){//if the destCoor is occupied
				isDestinationValid = false;
				return;
			}
		}
		
		isDestinationValid = true;
	}

}
