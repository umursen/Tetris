
public class HighScoreTuple{
	private String name;
	private int score;
	
	public HighScoreTuple(String name,int score){
		this.name = name;
		this.score = score;
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
	public String toString(){
		return "< "+name+", "+score+" >";
	}
}
