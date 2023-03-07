package com.snakeGame;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	GameFrame(){
		GamePanel panel = new GamePanel();
		
		this.add(panel);   //or this.add(new GamePanel); we can do it in this way also
		this.setTitle("Snake");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.pack();
		
		
		
	}

}
