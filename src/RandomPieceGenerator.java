import java.util.Random;

public class RandomPieceGenerator {
	private char randomPiece;
	private Random randomGenerator;
	
	public RandomPieceGenerator(){
		randomGenerator = new Random();
		generateRandomPiece();
	}
	
	public void generateRandomPiece(){
		int randomNumber = 0;
		if(Options.OPTION_GAME_TYPE == 2)
			randomNumber = randomGenerator.nextInt(7);
		else if(Options.OPTION_GAME_TYPE == 1)
			randomNumber = randomGenerator.nextInt(10-7) + 7;
		else if(Options.OPTION_GAME_TYPE == 3)
			randomNumber = randomGenerator.nextInt(10);
		
		switch(randomNumber){
		case 0:
			randomPiece = 'Z';
			break;
		case 1:
			randomPiece = 'S';
			break;
		case 2:
			randomPiece = 'O';
			break;
		case 3:
			randomPiece = 'T';
			break;
		case 4:
			randomPiece = 'J';
			break;
		case 5:
			randomPiece = 'L';
			break;
		case 6:
			randomPiece = 'I';
			break;
		case 7:
			randomPiece = 'i';
			break;
		case 8:
			randomPiece = 'j';
			break;
		case 9:
			randomPiece = 'r';
			break;
		}

	}
	
	public char getRandomPiece(){
		return randomPiece;
	}
}
