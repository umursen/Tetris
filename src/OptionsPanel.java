import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class OptionsPanel extends JPanel {
	private ApplicationWindow ownerFrame;
	KeyConfigurationPanel keyConfigurationPanel;
	private JButton KeyCon,BS1,BS2,BS3,Lvl1,Lvl2,Lvl3,Lvl4,Lvl5,BO1,BO2,BO3,back;
	private JLabel OptionsLabel,BoardSize,Level,BlockOptions;
	private OptionsPanelButtonListener buttonListener;
	int width = Constants.SCREEN_WIDTH;
	int height = Constants.SCREEN_HEIGHT;
			
	public OptionsPanel(ApplicationWindow ownerFrame) {
		super();
		this.ownerFrame = ownerFrame;
		
		OptionsLabel = new JLabel("Options");
		BoardSize = new JLabel("Board Size",JLabel.CENTER);
		Level = new JLabel("Level",JLabel.CENTER);
		BlockOptions = new JLabel("Block Options",JLabel.CENTER);
		buttonListener = new OptionsPanelButtonListener();
		keyConfigurationPanel = new KeyConfigurationPanel(ownerFrame,this);

		KeyCon = new JButton("Key Configuration");
		BS1 = new JButton("Small");
		BS2 = new JButton("Medium");
		BS3 = new JButton("Large");
		Lvl1 = new JButton("Level 1");
		Lvl2 = new JButton("Level 2");
		Lvl3 = new JButton("Level 3");
		Lvl4 = new JButton("Level 4");
		Lvl5 = new JButton("Level 5");
		BO1 = new JButton("Triminos");
		BO2 = new JButton("Tetriminos");
		BO3 = new JButton("Tetriminos & Triminos");		
		back = new JButton("Back");
		
		
		
		setLayout(null);
		setBackground(Constants.GAME_BACKGROUND_COLOR);
		setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		
		
		add(back);
		back.setBounds(50, 580, 100, 30);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		
		add(OptionsLabel);
		OptionsLabel.setBounds(0, 0, Constants.SCREEN_HEIGHT, 120);
		OptionsLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		OptionsLabel.setForeground(Constants.TITLE_COLOR);
		OptionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(KeyCon);
		KeyCon.setBounds(width/2-115,130,230,60);
		KeyCon.setFont(new Font("Tahoma", Font.BOLD, 20));		
		KeyCon.setFocusPainted(false);
		
		add(BoardSize);
		BoardSize.setBounds(0,200,Constants.SCREEN_WIDTH,50);
		BoardSize.setForeground(Constants.TITLE_COLOR); 
		BoardSize.setFont(new Font("Tahoma", Font.BOLD, 25));
		BoardSize.setHorizontalAlignment(SwingConstants.CENTER);
		add(BS1);
		BS1.setBounds(width/2-160,270,100,50);		
		BS1.setBorderPainted(false);
		BS1.setFocusPainted(false);
		add(BS2);
		BS2.setBounds(width/2-50,270,100,50);
		BS2.setFocusPainted(false);
		BS2.setBorderPainted(false);
		add(BS3);
		BS3.setBounds(width/2+60,270,100,50);		
		BS3.setBorderPainted(false);
		BS3.setFocusPainted(false);
		
		add(Level);
		Level.setBounds(0,445,Constants.SCREEN_WIDTH,40);
		Level.setForeground(Constants.TITLE_COLOR); 
		Level.setFont(new Font("Tahoma", Font.BOLD, 25));
		Level.setHorizontalAlignment(SwingConstants.CENTER);
		add(Lvl1);
		Lvl1.setBounds(width/2-220,500,80,50);
		Lvl1.setBorderPainted(false);
		Lvl1.setFocusPainted(false);
		add(Lvl2);
		Lvl2.setBounds(width/2-130,500,80,50);
		Lvl2.setBorderPainted(false);
		Lvl2.setFocusPainted(false);
		add(Lvl3);
		Lvl3.setBounds(width/2-40,500,80,50);
		Lvl3.setBorderPainted(false);
		Lvl3.setFocusPainted(false);
		add(Lvl4);
		Lvl4.setBounds(width/2+50,500,80,50);
		Lvl4.setBorderPainted(false);
		Lvl4.setFocusPainted(false);
		add(Lvl5);
		Lvl5.setBounds(width/2+140,500,80,50);
		Lvl5.setBorderPainted(false);
		Lvl5.setFocusPainted(false);		
		
		add(BlockOptions);
		BlockOptions.setBounds(0,330,Constants.SCREEN_WIDTH,50);
		BlockOptions.setForeground(Constants.TITLE_COLOR); 
		BlockOptions.setFont(new Font("Tahoma", Font.BOLD, 25));
		BlockOptions.setHorizontalAlignment(SwingConstants.CENTER);
		add(BO1);
		BO1.setBounds(width/2-250,390,160,50);
		BO1.setBorderPainted(false);
		BO1.setFocusPainted(false);
		add(BO2);
		BO2.setBounds(width/2-80,390,160,50);
		BO2.setBorderPainted(false);
		BO2.setFocusPainted(false);
		add(BO3);
		BO3.setBounds(width/2+90,390,160,50);
		BO3.setBorderPainted(false);
		BO3.setFocusPainted(false);
		
		KeyCon.addActionListener(buttonListener);
		BS1.addActionListener(buttonListener);
		BS2.addActionListener(buttonListener);;
		BS3.addActionListener(buttonListener);
		Lvl1.addActionListener(buttonListener);
		Lvl2.addActionListener(buttonListener);
		Lvl3.addActionListener(buttonListener);
		Lvl4.addActionListener(buttonListener);
		Lvl5.addActionListener(buttonListener);
		BO1.addActionListener(buttonListener);
		BO2.addActionListener(buttonListener);
		BO3.addActionListener(buttonListener);	
		back.addActionListener(buttonListener);	
		
		repaint();
		
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		BoardSize.repaint();
		BoardSize.setOpaque(true);
		BoardSize.setBackground(Constants.GAME_BACKGROUND_COLOR);
		
		OptionsLabel.repaint();
		OptionsLabel.setBackground(Constants.GAME_BACKGROUND_COLOR);
		OptionsLabel.setOpaque(true);
		
		Level.repaint();
		Level.setBackground(Constants.GAME_BACKGROUND_COLOR);
		Level.setOpaque(true);
		
		BlockOptions.repaint();
		BlockOptions.setBackground(Constants.GAME_BACKGROUND_COLOR);
		BlockOptions.setOpaque(true);
		
		/* Paints the shadow behind the selected buttons */
		g.fillRect(width/2-275+Options.OPTION_BOARD_SIZE*110,265,110,60);
		g.fillRect(width/2-425+Options.OPTION_GAME_TYPE*170,385,170,60);
		g.fillRect(width/2-315+Options.LEVEL*90,495,90,60);
		
		KeyCon.repaint();
		BS1.repaint();
		BS2.repaint();
		BS3.repaint();
		Lvl1.repaint();
		Lvl2.repaint();
		Lvl3.repaint();
		Lvl4.repaint();
		Lvl5.repaint();
		BO1.repaint();
		BO2.repaint();
		BO3.repaint();
		back.repaint();
	}
	
	private class OptionsPanelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			switch(action){
			case "Small": 
				Options.OPTION_BOARD_SIZE = 1;
				Options.ConfigureSizeOptions(Constants.SMALL_BOARD_WIDTH, Constants.SMALL_BOARD_HEIGHT);
				break;
				
			case "Medium":
				Options.OPTION_BOARD_SIZE = 2;
				Options.ConfigureSizeOptions(Constants.MEDIUM_BOARD_WIDTH, Constants.MEDIUM_BOARD_HEIGHT);
				break;
				
			case "Large":
				Options.OPTION_BOARD_SIZE = 3;
				Options.ConfigureSizeOptions(Constants.LARGE_BOARD_WIDTH, Constants.LARGE_BOARD_HEIGHT);
				break;
				
			case "Triminos":
				Options.OPTION_GAME_TYPE = 1;
				break;
				
			case "Tetriminos":
				Options.OPTION_GAME_TYPE = 2;
				break;
				
			case "Tetriminos & Triminos":
				Options.OPTION_GAME_TYPE = 3;
				break;
			case "Back":
				ownerFrame.remove(ownerFrame.options);
				ownerFrame.add(ownerFrame.menu);
				ownerFrame.menu.repaint();
				break;
			case "Key Configuration":
				
				ownerFrame.remove(ownerFrame.options);
				ownerFrame.add(keyConfigurationPanel);
				keyConfigurationPanel.repaint();
				break;
			default:
				String sub = action.substring(0,5);
				if(sub.equals("Level")){
					Options.setLevel(Integer.parseInt(action.substring(6,7)));
				}
				break;
			}
			
			ownerFrame.options.repaint();
			
		}
		
	}
}
