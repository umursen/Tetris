import java.awt.*;

import javax.swing.*;

import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyConfigurationPanel extends JPanel{

	private static int ColumnLocation = Constants.SCREEN_WIDTH/2-250;
	private static int ColumnLocationButton = Constants.SCREEN_WIDTH/2-20;
	private static int RowLocation = Constants.SCREEN_WIDTH/3;
	
	
	private ApplicationWindow ownerFrame;
	private OptionsPanel ownerPanel;
	private JLabel left, right, rotate, dgrav, pause, KeyConfig, helper;
	private KeyConfigurationButton leftB, rightB, rotateB, dgravB, pauseB;
	private JButton back;
	public KeyConfigurationButton currentSelectedButton;
	private HashMap<Integer, String> KeyRepresentations;
	private KeyConfigurationPanelKeyListener keyListener;
	

	
	public KeyConfigurationPanel(ApplicationWindow ownerFrame, OptionsPanel ownerPanel){
		super();
		this.ownerFrame = ownerFrame;
		this.ownerPanel = ownerPanel;
		generetaKeyRepresentationHashMap();
		keyListener = new KeyConfigurationPanelKeyListener();
		
		KeyConfig= new JLabel("KEY CONFIGURATION");
		left = new JLabel("Left");
		right = new JLabel("Right");
		rotate = new JLabel("Rotate");
		dgrav = new JLabel("Double Gravity");
		pause = new JLabel("Pause");
		helper = new JLabel("Please enter a key...");
	
		leftB = new KeyConfigurationButton(this, KeyRepresentations.get(Options.KEY_CONFIG_LEFT), GameEvents.KEY_EVENT_MOVE_LEFT);
		rightB = new KeyConfigurationButton(this, KeyRepresentations.get(Options.KEY_CONFIG_RIGHT), GameEvents.KEY_EVENT_MOVE_RIGHT);
		rotateB = new KeyConfigurationButton(this, KeyRepresentations.get(Options.KEY_CONFIG_UP), GameEvents.KEY_EVENT_ROTATE);
		dgravB = new KeyConfigurationButton(this, KeyRepresentations.get(Options.KEY_CONFIG_DOWN), GameEvents.KEY_EVENT_MOVE_FAST_START);
		pauseB = new KeyConfigurationButton(this, KeyRepresentations.get(Options.KEY_CONFIG_PAUSE), GameEvents.KEY_EVENT_PAUSE_START);
		back = new JButton("Back");
		
		
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		add(KeyConfig);
		KeyConfig.setBounds(0, 50, Constants.SCREEN_WIDTH, 120);
		KeyConfig.setFont(new Font("Tahoma", Font.BOLD, 40));
		KeyConfig.setForeground(Constants.TITLE_COLOR);
		KeyConfig.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(left);
		left.setBounds(ColumnLocation, RowLocation,ColumnLocationButton-ColumnLocation, 60);
		left.setFont(new Font("Tahoma", Font.BOLD, 20));
		left.setForeground(Constants.TITLE_COLOR);
		
		
		add(right);
		right.setBounds(ColumnLocation, RowLocation+50, ColumnLocationButton-ColumnLocation, 60);
		right.setFont(new Font("Tahoma", Font.BOLD, 20));
		right.setForeground(Constants.TITLE_COLOR);
	
		add(rotate);
		rotate.setBounds(ColumnLocation, RowLocation+100, ColumnLocationButton-ColumnLocation, 60);
		rotate.setFont(new Font("Tahoma", Font.BOLD, 20));
		rotate.setForeground(Constants.TITLE_COLOR);
		
		add(dgrav);
		dgrav.setBounds(ColumnLocation, RowLocation+150, ColumnLocationButton-ColumnLocation, 60);
		dgrav.setFont(new Font("Tahoma", Font.BOLD, 20));
		dgrav.setForeground(Constants.TITLE_COLOR);
		
		add(pause);
		pause.setBounds(ColumnLocation, RowLocation+200, ColumnLocationButton-ColumnLocation, 60);
		pause.setFont(new Font("Tahoma", Font.BOLD, 20));
		pause.setForeground(Constants.TITLE_COLOR);
		
		add(helper);
		helper.setBounds((ColumnLocation + ColumnLocationButton)/2-40,(RowLocation+KeyConfig.getY()+KeyConfig.getHeight())/2, 200, 30);
		helper.setFont(new Font("Tahoma", Font.BOLD, 15));
		helper.setForeground(new Color(80,80,80));
		helper.setVisible(false);
		
		add(leftB);
		leftB.setBounds(ColumnLocationButton,left.getY()+(left.getHeight()/2-leftB.getHeight()/2),300,30);		
		
		add(rightB);
		rightB.setBounds(ColumnLocationButton,right.getY()+(right.getHeight()/2-rightB.getHeight()/2),300,30);		
		
		add(rotateB);
		rotateB.setBounds(ColumnLocationButton,rotate.getY()+(rotate.getHeight()/2-rotateB.getHeight()/2),300,30);		
		
		add(dgravB);
		dgravB.setBounds(ColumnLocationButton,dgrav.getY()+(dgrav.getHeight()/2-dgravB.getHeight()/2),300,30);		
		
		
		add(pauseB);
		pauseB.setBounds(ColumnLocationButton,pause.getY()+(pause.getHeight()/2-pauseB.getHeight()/2),300,30);		
		
		add(back);
		back.setBounds(50, 580, 100, 30);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentSelectedButton != null){
					currentSelectedButton.configure(null, 0);
					currentSelectedButton = null;
				}
				
				ownerFrame.remove(ownerPanel.keyConfigurationPanel);
				ownerFrame.add(ownerPanel);
				ownerPanel.repaint();
			}
			
		});
		
		repaint();
	}
	
	public void setHelperText(String text){
		helper.setText(text);
	}
	
	public void activateKeyListener(){
		addKeyListener(keyListener);
		requestFocusInWindow();
	}
	
	public void deactivateKeyListener(){
		removeKeyListener(keyListener);
	}
	
	public void switchHelperText(boolean open){
		helper.setVisible(open);
	}
	
	public boolean isConfiguring(){
		return (currentSelectedButton!=null);
	}
	
	public void generetaKeyRepresentationHashMap(){
		KeyRepresentations = new HashMap<Integer,String>();
		for(int i=48; i<=90; i++){
			KeyRepresentations.put(i,"< "+((char)i)+" >");
		}
		KeyRepresentations.put(32, "< Space >");
		KeyRepresentations.put(37, "< Left Arrow >");
		KeyRepresentations.put(38, "< Up Arrow >");
		KeyRepresentations.put(39, "< Right Arrow >");
		KeyRepresentations.put(40, "< Down Arrow >");
	}
	
	public void setCurrentSelectedButton(KeyConfigurationButton button){
		currentSelectedButton = button;
	}


	public void paint(Graphics g){
		super.paintComponent(g);
		KeyConfig.repaint();
		KeyConfig.setOpaque(true);
		KeyConfig.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		left.repaint();
		left.setOpaque(true);
		left.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		right.repaint();
		right.setOpaque(true);
		right.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		dgrav.repaint();
		dgrav.setOpaque(true);
		dgrav.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		rotate.repaint();
		rotate.setOpaque(true);
		rotate.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		pause.repaint();
		pause.setOpaque(true);
		pause.setBackground(Constants.GAME_BACKGROUND_COLOR);	
		
		helper.repaint();
		helper.setOpaque(true);
		helper.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		leftB.repaint();
		rightB.repaint();
		dgravB.repaint();
		rotateB.repaint();
		pauseB.repaint();
		back.repaint();
	}
	
	private class KeyConfigurationPanelKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if((key == Options.KEY_CONFIG_UP || key == Options.KEY_CONFIG_DOWN || key == Options.KEY_CONFIG_LEFT
					|| key == Options.KEY_CONFIG_RIGHT || key == Options.KEY_CONFIG_PAUSE)
					&& KeyRepresentations.get(key) != currentSelectedButton.getCurrentKeyString()){
				helper.setText("This key is occupied please select another key!");
			}else if(!KeyRepresentations.keySet().contains(key)){
				helper.setText("This key is not valid please select another key!");
			}else{
				currentSelectedButton.configure(KeyRepresentations.get(key), key);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}