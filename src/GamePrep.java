import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep{
	
	public static void startGame(Frog frog, Car[] cars1, Car[] cars2, Car[] cars3, Log[] logs1, 
			Log[] logs2, Log[] logs3) {

		//set up frog
		frog = new Frog(375, 500, 50, 50, GameProperties.CHARACTER_STEP, "frog.png");

		//set up array of car 1 (the bottom lane)
		cars1= new Car[3];
		for(int i=0;i<cars1.length;i++){
			cars1[i]= new Car();
			cars1[i].setX(0 + 290*i);
			cars1[i].setY(450);
			cars1[i].setWidth(100);
			cars1[i].setHeight(50);
			cars1[i].setSpeed(30);
			cars1[i].setMoving(false);
			cars1[i].setImage("car_right.png");
			cars1[i].setFrog(frog);
			cars1[i].startMoving();
		}
		
		//set up array of car 2 (the mid lane)
		cars2= new Car[3];
		for(int i=0;i<cars2.length;i++){
			cars2[i]= new Car();
			cars2[i].setX(0 + 270*i);
			cars2[i].setY(385);
			cars2[i].setWidth(100);
			cars2[i].setHeight(50);
			cars2[i].setSpeed(-25);
			cars2[i].setMoving(false);
			cars2[i].setImage("car_left.png");
			cars2[i].setFrog(frog);
			cars2[i].startMoving();
		}
		
		//set up array of car 3 (the top lane)
		cars3= new Car[3];
		for(int i=0;i<cars3.length;i++){
			cars3[i]= new Car();
			cars3[i].setX(0 + 250*i);
			cars3[i].setY(320);
			cars3[i].setWidth(100);
			cars3[i].setHeight(40);
			cars3[i].setSpeed(40);
			cars3[i].setMoving(false);
			cars3[i].setImage("car_right.png");
			cars3[i].setFrog(frog);
			cars3[i].startMoving();
		}
		
		//set up array of log 1  (the bottom lane of river)
		logs1 = new Log[3];
		for(int i=0;i<logs1.length;i++){
			logs1[i]= new Log();
			logs1[i].setX(0+i*300);
			logs1[i].setY(190);
			logs1[i].setWidth(150);
			logs1[i].setHeight(60);
			logs1[i].setSpeed(25);
			logs1[i].setMoving(false);
			logs1[i].setImage("log.png");
			logs1[i].setFrog(frog);
			logs1[i].startMoving();
			
		}

		//set up array of log 2 (the mid lane of river)
		logs2 = new Log[3];
		for(int i=0;i<logs2.length;i++){
			logs2[i]= new Log();
			logs2[i].setX(0+i*350);
			logs2[i].setY(125);
			logs2[i].setWidth(150);
			logs2[i].setHeight(60);
			logs2[i].setSpeed(-35);
			logs2[i].setMoving(false);
			logs2[i].setImage("log.png");
			logs2[i].setFrog(frog);
			logs1[i].startMoving();
			
		}
		
		//set up array of log 3 (the top lane of river)
		logs3 = new Log[2];
		for(int i=0;i<logs3.length;i++){
			logs3[i]= new Log();
			logs3[i].setX(0+i*330);
			logs3[i].setY(60);
			logs3[i].setWidth(150);
			logs3[i].setHeight(60);
			logs3[i].setSpeed(35);
			logs3[i].setMoving(false);
			logs3[i].setImage("log.png");
			logs3[i].setFrog(frog);
			logs1[i].startMoving();
			
		}
	}


	//main 
	public static void main( String args []) throws IOException{
		//instances of our data classes (store position, etc here)
		Frog frog = new Frog();
		
		Car cars1[] = new Car[3];
		Car cars2[] = new Car[3];
		Car cars3[] = new Car[3];
		
		Log logs1[] = new Log[3];
		Log logs2[] = new Log[3];
		Log logs3[] = new Log[2];
		
		int gameScore = 0;
		
		startGame(frog, cars1,cars2, cars3, logs1, logs2, logs3);
		
		final int SERVER_PORT = 5556;
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Waiting for clients to connect...");
		while(true) {
			Socket s = server.accept();
			System.out.println("client connected");
			
			ServerService myService = new ServerService(s, frog, cars1, cars2, cars3, logs1, logs2, logs3);
			Thread t = new Thread(myService);
			t.start();
		}
		
	}

}