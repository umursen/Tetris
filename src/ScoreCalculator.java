
public class ScoreCalculator {
	private int score;
	private int linesDeleted;
	private int level;
	boolean updated = false;

	public ScoreCalculator(){
		score = 0;
		linesDeleted = 0;
		level = Options.LEVEL;
	}

	public void calculateScore(int linesDestroyed){
		switch(linesDestroyed){
		case 1:
		score += Math.pow((double)Options.SPEED, -1) * 1000 * 1;
		this.linesDeleted+=linesDestroyed;
		break;
		case 2:
		score += (Math.pow((double)Options.SPEED, -1) * 1000 )* 3;
		this.linesDeleted+=linesDestroyed;
		break;
		case 3:
		score += (Math.pow((double)Options.SPEED, -1) * 1000 )* 6;
		this.linesDeleted+=linesDestroyed;
		break;
		case 4:
		score += (Math.pow((double)Options.SPEED, -1) * 1000 ) * 10;
		this.linesDeleted+=linesDestroyed;
		break;
		}
		if((getLevel() < (getLinesDeleted()/10)+Options.LEVEL) && getLevel()!= 5){
			setLevel((getLinesDeleted()/10) +Options.LEVEL);
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLinesDeleted() {
		return linesDeleted;
	}
	
	public void setLevel(int level){
		this.level = level;
		switch(level){
		case 1: Options.SPEED = Constants.INITIAL_SPEED;
		case 2: Options.SPEED = Constants.INITIAL_SPEED * 8 / 10; break;
		case 3: Options.SPEED = Constants.INITIAL_SPEED * 6 / 10; break;
		case 4: Options.SPEED = Constants.INITIAL_SPEED * 4 / 10; break;
		case 5: Options.SPEED = Constants.INITIAL_SPEED * 2 / 10; break;
		};
	}
	
	public int getLevel(){
		return level;
	}
	

}