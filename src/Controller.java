import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener{

	private GameKeyEventList eventList;
	private boolean paused;


	public Controller(GameKeyEventList eventList){
		this.eventList = eventList;
		paused = false;

	}


	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if(key == Options.KEY_CONFIG_UP){
			if(!paused){
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_ROTATE));
			}

		}
		else if(key == Options.KEY_CONFIG_RIGHT){
			if(!paused)
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_MOVE_RIGHT));
		}
		else if(key == Options.KEY_CONFIG_LEFT){
			if(!paused)
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_MOVE_LEFT));
		}
		else if(key == Options.KEY_CONFIG_DOWN){
			if(!paused){
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_MOVE_FAST_START));
			}
		}

		else if(key == Options.KEY_CONFIG_PAUSE){
			if(!paused){
				paused = true;
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_PAUSE_START));
			} else{
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_PAUSE_STOP));
				paused = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == Options.KEY_CONFIG_DOWN){
			if(!paused){
				eventList.append(new GameKeyEvent(GameEvents.KEY_EVENT_MOVE_FAST_STOP));
			}
		}


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
