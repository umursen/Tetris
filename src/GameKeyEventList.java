import java.util.Iterator;
import java.util.LinkedList;


public class GameKeyEventList {
	
	private LinkedList<GameKeyEvent> gameKeyEventList;
	
	public GameKeyEventList(){
		gameKeyEventList = new LinkedList<GameKeyEvent>();
	}
	
	public synchronized void append(GameKeyEvent e) {
		gameKeyEventList.add(e);
	}
	
	public int size(){
		return gameKeyEventList.size();
	}
	
	public synchronized LinkedList<GameKeyEvent> migrateList() {
		
		if(gameKeyEventList.size() == 0) return null;
		
		LinkedList<GameKeyEvent> holderList = new LinkedList<GameKeyEvent>();
		
		for(Iterator<GameKeyEvent> i = gameKeyEventList.iterator(); i.hasNext();){
			holderList.add(i.next());
		}
		gameKeyEventList.clear();
		return holderList;
		
	}
}