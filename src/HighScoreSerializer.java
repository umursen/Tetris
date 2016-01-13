import java.util.*;
import java.io.*;

public class HighScoreSerializer {
	
	ArrayList<HighScoreTuple> highScoreList;
	
	public HighScoreSerializer(){
		readScores();
	}
	
	public ArrayList<HighScoreTuple> getHighScoreList(){
		return highScoreList;
	}
	
	public int getHighScoreNumber(){
		return highScoreList.size();
	}
	
	public void readScores(){
		highScoreList = new ArrayList<HighScoreTuple>();
		int counter=0;
		try{
			BufferedReader rd = new BufferedReader(new FileReader("scores.txt"));
			while(counter<5){
				String line = rd.readLine();
				if(line == null) break;
				int seperatorIndex=0;
				for(int i=line.length()-1; i>=0; i--){
					if(line.charAt(i) == '|'){
						seperatorIndex = i;
						break;
					}
				}
				if(seperatorIndex!=0){
					String name = line.substring(0,seperatorIndex);
					int score = Integer.parseInt(line.substring(seperatorIndex+1,line.length()));
					highScoreList.add(new HighScoreTuple(name,score));
					counter++;
				}
			}
			rd.close();
		} catch(IOException ex){
			return;
		}
	}
	private void writeScores(){
		listUpdate();
		try{
			PrintWriter wr = new PrintWriter(new FileWriter("scores.txt"));
			for(HighScoreTuple scoreTuple: highScoreList){
				String line = scoreTuple.getName()+"|"+scoreTuple.getScore();
				wr.println(line);
			}
			wr.close();
		} catch(IOException ex){
			return;
		}
	}
	
	public void newScore(String name, int score){
		HighScoreTuple newScoreTuple = new HighScoreTuple(name, score);
		highScoreList.add(newScoreTuple);
		writeScores();
		
	}
	
	private void listUpdate(){
		highScoreList.sort(new HighScoreTupleComparator());
		while(highScoreList.size()>5){
			highScoreList.remove(highScoreList.size()-1);
		}
	}
	public class HighScoreTupleComparator implements Comparator<HighScoreTuple> {
	   
		public int compare(HighScoreTuple t1, HighScoreTuple t2){
			if(t1.getScore() < t2.getScore())
				return 1;
			else if(t1.getScore() == t2.getScore())
				return 0;
			else
				return -1;
		}
	}
}
