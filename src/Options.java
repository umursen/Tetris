
public class Options {
	public static int BOARD_WIDTH = Constants.MEDIUM_BOARD_WIDTH;
	public static int BOARD_HEIGHT = Constants.MEDIUM_BOARD_HEIGHT;
	
	/* Key Configurations */
	public static int KEY_CONFIG_UP = 38;
	public static int KEY_CONFIG_LEFT = 37;
	public static int KEY_CONFIG_DOWN = 40;
	public static int KEY_CONFIG_RIGHT = 39;
	public static int KEY_CONFIG_PAUSE = 32;
	public static int SPEED = Constants.INITIAL_SPEED;
	public static int BLOCK_SIZE = Constants.ANIMATION_WINDOW_HEIGHT/Constants.MEDIUM_BOARD_HEIGHT;
	
	/* OptionPanel related variables */
	public static int LEVEL = 1;
	public static int OPTION_BOARD_SIZE = 2;
	public static int OPTION_BLOCK_OPTION = 2;
	public static int OPTION_GAME_TYPE = 2;



	public static void ConfigureSizeOptions(int width, int height){

		BOARD_WIDTH = width;
		BOARD_HEIGHT = height;

		BLOCK_SIZE = Constants.ANIMATION_WINDOW_HEIGHT/height;

	}
	
	public static void setLevel(int level){
		LEVEL = level;
		switch(level){
		case 1: SPEED = Constants.INITIAL_SPEED;
		case 2: SPEED = Constants.INITIAL_SPEED * 8 / 10; break;
		case 3: SPEED = Constants.INITIAL_SPEED * 6 / 10; break;
		case 4: SPEED = Constants.INITIAL_SPEED * 4 / 10; break;
		case 5: SPEED = Constants.INITIAL_SPEED * 2 / 10; break;
		};
	}




}
