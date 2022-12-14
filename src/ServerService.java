import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


//processing routine on server (B)
public class ServerService implements Runnable {
	final int CLIENT_PORT = 5656;

	private Socket s;
	private Scanner in;

	private Frog frog;
	private Car carsBot[];
	private Car carsMid[];
	private Car carsTop[];
	
	private Log logsBot[];
	private Log logsMid[];
	private Log logsTop[];
	
	public ServerService() {
		super();
	}
	
	public ServerService (Socket aSocket, Frog myFrog, Car[] carsBot,  Car[] carsMid,  Car[] carsTop, Log[] logsBot, 
					 	  Log[] logsMid, Log[] logsTop) {
		super();
		this.s = aSocket;
		this.frog = myFrog;
		this.carsBot = carsBot;
		this.carsMid = carsMid;
		this.carsTop = carsTop;
		this.logsBot = logsBot;
		this.logsMid = logsMid;
		this.logsTop = logsTop;
	}
	
	
	public void run() {
		
		try {
			in = new Scanner(s.getInputStream());
			processRequest( );
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//processing the requests
	public void processRequest () throws IOException {
		//if next request is empty then return
		while(true) {
			if(!in.hasNext( )){
				return;
			}
			String command = in.next();
			if (command.equals("Quit")) {
				return;
			} else {
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command) throws IOException{
	
		if ( command.equals("MOVE_FROG")) {
			String direction = in.next();
			int x = frog.getX();
			int y = frog.getY();
			
			if (direction.equals("UP")) {			
				//modify frog step to match with lanes
				if (y <= 450) {
					y -= GameProperties.CHARACTER_STEP + 14;
				} else {
					y -= GameProperties.CHARACTER_STEP;
				}
			} else if (direction.equals("DOWN")) {			
				//modify frog step to match with lanes
				if (y >= 450) {
					y += GameProperties.CHARACTER_STEP;
				} else {
					y += GameProperties.CHARACTER_STEP + 14;
				}
			} else if (direction.equals("LEFT")) {	
				if (x > 0) {
					x -= GameProperties.CHARACTER_STEP;
				}
				
			} else if (direction.equals("RIGHT")) {	
				if (x < GameProperties.SCREEN_WIDTH) {
					x += GameProperties.CHARACTER_STEP;	
				}
			}
			
			frog.setX(x);
			frog.setY(y);
			
			System.out.println("Frog moved");
			
		}
		
		if (command.equals("GETFROG")) {
			int x = frog.getX();
			int y = frog.getY();
			
			//send a response
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			//Initialize data stream to send data out
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			String commandOut = "FROG_POSITION " + x + " " + y + "\n";
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
			s2.close();
			
		} else if (command.equals("GETCARS")) {
			
			int x1 = carsBot[1].getX();
			int y1 = carsBot[1].getY();
			int x2 = carsBot[2].getX();
			int y2 = carsBot[2].getY();
			int x3 = carsBot[3].getX();
			int y3 = carsBot[3].getY();
			
			int x4 = carsMid[1].getX();
			int y4 = carsMid[1].getY();
			int x5 = carsMid[2].getX();
			int y5 = carsMid[2].getY();
			int x6 = carsMid[3].getX();
			int y6 = carsMid[3].getY();
			
			int x7 = carsTop[1].getX();
			int y7 = carsTop[1].getY();
			int x8 = carsTop[2].getX();
			int y8 = carsTop[2].getY();
			int x9 = carsTop[3].getX();
			int y9 = carsTop[3].getY();
			
			//send a response
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			//Initialize data stream to send data out
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			String commandOut = "GETCARS " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3 + " " 
								+ x4 + " " + y4 + " " + x5 + " " + y5 + " " + x6 + " " + y6 + " " +
								x7 + " " + y7 + " " + x8 + " " + y8 + " " + x9 + " " + y9 + "\n"; 
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
			s2.close();
			
		}else if (command.equals("GETLOGS")) {
				
				int x1 = logsBot[1].getX();
				int y1 = logsBot[1].getY();
				int x2 = logsBot[2].getX();
				int y2 = logsBot[2].getY();
				int x3 = logsBot[3].getX();
				int y3 = logsBot[3].getY();
				
				int x4 = logsMid[1].getX();
				int y4 = logsMid[1].getY();
				int x5 = logsMid[2].getX();
				int y5 = logsMid[2].getY();
				int x6 = logsMid[3].getX();
				int y6 = logsMid[3].getY();
				
				int x7 = logsTop[1].getX();
				int y7 = logsTop[1].getY();
				int x8 = logsTop[2].getX();
				int y8 = logsTop[2].getY();
				
				//send a response
				Socket s2 = new Socket("localhost", CLIENT_PORT);
				
				//Initialize data stream to send data out
				OutputStream outstream = s2.getOutputStream();
				PrintWriter out = new PrintWriter(outstream);

				String commandOut = "GETLOGS " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3 + " " 
									+ x4 + " " + y4 + " " + x5 + " " + y5 + " " + x6 + " " + y6 + " " +
									x7 + " " + y7 + " " + x8 + " " + y8 + "\n"; 
				System.out.println("Sending: " + commandOut);
				out.println(commandOut);
				out.flush();
				s2.close();
		} else if (command.equals("STARTGAME")) {
			for(int i=0;i<3;i++){
				carsBot[i].setMoving(true);
				carsMid[i].setMoving(true);
				carsTop[i].setMoving(true);
				logsBot[i].setMoving(true);
				logsMid[i].setMoving(true);
			}
			
			for(int i=0;i<2;i++){
				logsTop[i].setMoving(true);
			}
			
			//send a response
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			//Initialize data stream to send data out
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			String commandOut = "STARTGAME";
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
			s2.close();
			
		} else if (command.equals("STOPGAME")) {
			for(int i=0;i<3;i++){
				carsBot[i].setMoving(false);
				carsMid[i].setMoving(false);
				carsTop[i].setMoving(false);
				logsBot[i].setMoving(false);
				logsMid[i].setMoving(false);
			}
			
			for(int i=0;i<2;i++){
				logsTop[i].setMoving(false);
			}
			
			//send a response
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			//Initialize data stream to send data out
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			String commandOut = "STOPGAME";
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
			s2.close();
		}
		

	}
}
