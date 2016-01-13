import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.Timer;

import sun.audio.*;

import java.io.*;


public class Run {
	public static void main(String[] args) {
		ApplicationWindow frame = new ApplicationWindow();
		frame.pack();
		frame.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		startMusic();
		Timer timer = new Timer(83000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				startMusic();
				
			}
			
		});
		timer.start();
		
	}
	
	private static void startMusic(){
		AudioPlayer audioPlayer = AudioPlayer.player;
		AudioStream audioStream;
		AudioData audioData;
		AudioDataStream audioDataStream;
		
		try {
			audioStream = new AudioStream(new FileInputStream("sounds/backgroundMusic.wav"));
			audioPlayer.start(audioStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
