import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KeyConfigurationButton extends JButton{
	
	private KeyConfigurationPanel keyConfigurationPanel;
	private KeyConfigurationButtonActionListener actionListener;
	private String currentKeyString;
	private int eventOfKey;
	
	
	public KeyConfigurationButton(KeyConfigurationPanel keyConfigurationPanel, String keyString, int eventOfKey){
		super(keyString);
		currentKeyString = keyString;
		this.eventOfKey = eventOfKey;
		this.keyConfigurationPanel = keyConfigurationPanel;
		setFont(new Font("Tahoma", Font.BOLD, 20));
		actionListener = new KeyConfigurationButtonActionListener();
		
		setSize(100,30);
		setBorderPainted(false);
		setFocusPainted(false);
		
		super.addActionListener(actionListener);
	}
	
	private void nowConfiguring(){
		super.setText("...");
		super.setEnabled(false);
		keyConfigurationPanel.switchHelperText(true);
		keyConfigurationPanel.activateKeyListener();
	}
	private void doneConfiguring(){
		super.setText(currentKeyString);
		super.setEnabled(true);
		keyConfigurationPanel.switchHelperText(false);
		keyConfigurationPanel.deactivateKeyListener();
		keyConfigurationPanel.setCurrentSelectedButton(null);
	}
	
	private void selectThisButton(){
		keyConfigurationPanel.setHelperText("Please enter a key...");
		keyConfigurationPanel.setCurrentSelectedButton(this);
	}
	
	public void configure(String newKeyString, int newKeyCode){
		if(newKeyString == null){
			super.setText(currentKeyString);
		}else{
			switch(eventOfKey){
			case GameEvents.KEY_EVENT_ROTATE: Options.KEY_CONFIG_UP = newKeyCode;break;
			case GameEvents.KEY_EVENT_MOVE_RIGHT: Options.KEY_CONFIG_RIGHT = newKeyCode; break;
			case GameEvents.KEY_EVENT_MOVE_LEFT: Options.KEY_CONFIG_LEFT = newKeyCode; break;
			case GameEvents.KEY_EVENT_MOVE_FAST_START:Options.KEY_CONFIG_DOWN = newKeyCode; break;
			case GameEvents.KEY_EVENT_PAUSE_START: Options.KEY_CONFIG_PAUSE = newKeyCode; break;
			}
			
			currentKeyString = newKeyString;
		}
		doneConfiguring();
	}
	
	public String getCurrentKeyString(){
		return currentKeyString;
	}
	
	
	private class KeyConfigurationButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(keyConfigurationPanel.isConfiguring()){
				keyConfigurationPanel.currentSelectedButton.configure(null,0);
			}
			selectThisButton();
			nowConfiguring();
			
		}
		
	}
	
}
