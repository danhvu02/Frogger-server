import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//this is the object that as the thread
public class Log extends Object implements Runnable {

	private Boolean visible, moving;
	private Thread tLog;
	private JLabel LogLabel, FrogLabel;
	private Frog frog;
	private JButton StartButton;
	
	public Log() {
		super(0, 0, 150, 60, GameProperties.CHARACTER_STEP, "log.png");
		this.visible = true;
		this.moving = false;
	}
	
	public void setLogLabel(JLabel temp) {
		this.LogLabel = temp;
		
	}
	
	public void setFrog(Frog temp) {
		this.frog = temp;
	}

	public void setFrogLabel(JLabel temp) {
		this.FrogLabel = temp;
	}

	public void setStartButton(JButton temp) {
		this.StartButton = temp;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public void show() {
		this.visible = true;
	}
	
	public void hide() {
		this.visible = true;
	}
	
	public void Display () {
		System.out.println("Log - x,y: " + this.x + "," + this.y);
		System.out.println("width,height: " + this.width + "," + height);
		System.out.println("image: " + this.image);
		//super.Display();
		System.out.println("visible: " + this.visible);
		System.out.println("moving: " + this.moving);
	}
	
	public void startMoving() {
		System.out.println("Move!");
		if (!this.moving) {
			tLog = new Thread(this, "Log Thread");
			tLog.start();
		}
	}

	@Override
	public void run() {
		System.out.println("Log Thread started");
		this.moving = true;

		while (this.moving) {
			//moving instructions
			
			//get current x
			int currentX = this.x;
			
			//increase x
			currentX += this.speed;
			
			//boundary check right-side
			if (this.speed > 0 && currentX >= GameProperties.SCREEN_WIDTH) {
				currentX = -1 * this.width;
			} 
			
			//boundary check left-side
			else if (this.speed < 0 && currentX < (-1 * this.width)) {
				currentX = GameProperties.SCREEN_WIDTH;
			}
			
			//update x
			//this.x = currentX;
			this.setX(currentX);
			System.out.println("Log X, Y: " + this.x + "," + this.y);
			
			this.detectCollision();
			
			
			//pause
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("End Log Thread");
		this.StartButton.setText("Start");
		
	}
	
	public void detectCollision() {
		
		if (!r.intersects( frog.getRectangle() ) && frog.getY() >= 60 && frog.getY() <= 250) {

			System.out.println("Game Over!");
			this.moving = true;
			frog.setX(375);
			frog.setY(500);
			frog.getX();
			frog.getY();
			this.FrogLabel.setLocation(frog.getX(), frog.getY());

		}
		
		if (r.intersects( frog.getRectangle() )) {
			System.out.println("On Log!");
			this.moving = true;
			frog.setSpeed(this.speed);
			frog.setX(frog.getX()+ frog.getSpeed());
			frog.getX();
			this.FrogLabel.setLocation(frog.getX(), frog.getY());
			
		}
	}
	
	


}