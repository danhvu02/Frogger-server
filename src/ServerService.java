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
	
	public ServerService() {
		super();
	}
	
	public ServerService (Socket aSocket, Frog myFrog) {
		super();
		this.s = aSocket;
		this.frog = myFrog;
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
			
		}
		

	}
}