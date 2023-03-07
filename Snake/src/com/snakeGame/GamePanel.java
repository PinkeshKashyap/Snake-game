package com.snakeGame;

import java.awt.*;

import java.awt.event.*;
import java.util.*;


import javax.swing.*;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	static final int WIDTH =600;
	static final int HEIGHT =600;
	static final int UNIT_SIZE= 15;
	static final int GAME_UNITS = (WIDTH*HEIGHT)/UNIT_SIZE*UNIT_SIZE;
//	static final int DELAY = 100;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 5;
	int applesEaten;
	int appleX;
	int appleY;
	char direction= 'D';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	
	public void startGame(){
		newApple();
		running = true;
		timer= new Timer(100,this);
		timer.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	public void move(){
		for(int i = bodyParts; i > 0; i--){
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		
//		switch(direction){
//		case 'U':
//			y[0]=y[0]-UNIT_SIZE;
//			break;
//		case 'D':
//			y[0]=y[0]+UNIT_SIZE;
//			break;
//		case 'L':
//			x[0]=x[0]-UNIT_SIZE;
//			break;
//		case 'R':
//			x[0]=x[0]+UNIT_SIZE;
//			break;
//		}
		if (direction == 'L') {
            x[0] = x[0] - UNIT_SIZE;
        } else if (direction == 'R') {
            x[0] = x[0] + UNIT_SIZE;
        } else if (direction == 'U') {
            y[0] = y[0] - UNIT_SIZE;
        } else {
            y[0] = y[0] + UNIT_SIZE;
        }
	}
	
public void checkApple(){
		
		if(x[0] == appleX && y[0] == appleY){
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}

	public void draw(Graphics g){

	if(running){
//		for(int i=0; i<HEIGHT/UNIT_SIZE; i++){                       // this for loop is for to view grid of frame
//			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, HEIGHT);
//			g.drawLine(0, i*UNIT_SIZE, WIDTH, i*UNIT_SIZE);
//		}
		g.setColor(Color.MAGENTA);                    // this line is for apple
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		for(int i = 0; i < bodyParts; i++){    //this for loop is for snake
			if(i==0){
				g.setColor(Color.lightGray);
				g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);    // this line is for head of snake 
				}
			else{
				g.setColor(Color.CYAN);
				g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);    // this line is for tail of snake
			}
			
			g.setColor(Color.white);
			g.setFont(new Font("Sans serif" , Font.ROMAN_BASELINE, 25));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

			
		}
		
		}
	else{
		gameOver(g);
	}
	
	}
	
	public void newApple(){
		
		appleX= random.nextInt((int)(WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY= random.nextInt((int)(HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	
	public void checkCollisions(){
		for(int i=bodyParts; i>0;i--){   //check if head touches body
			if(x[0] == x[i] && y[0]==y[i]){
				running = false;
			}
		}
		
//		if(x[0]<0){     //check if head touches the left border
//			running = false;
//		}
//		
//		if(x[0]> SCREEN_WIDTH){    //check if head touches the right border
//		running = false;
//		}
//		
//		if(y[0]<0){         //check if head touches the top border
//			running = false;
//		}
//		if(y[0]> SCREEN_HEIGHT){ //check if head touches the bottom border
//			running = false;
//		}
//		
		if(x[0]<0 || x[0]> WIDTH || y[0] <0 || y[0]> HEIGHT ){
			running = false;
		}
		if(!running){
			timer.stop();
		}
	}
	public void gameOver(Graphics g){
		
	
		 g.setColor(Color.red);
         g.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
         FontMetrics metrics = getFontMetrics(g.getFont());
         g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT/2);

         g.setColor(Color.white);
         g.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
         metrics = getFontMetrics(g.getFont());
         g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

         
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(running){
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;

            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;

            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
            
			}
			
				
			
		}
	}

}
