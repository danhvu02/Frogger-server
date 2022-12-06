import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//this is the object that as the thread
public class Car extends Object implements Runnable {

	protected Boolean visible, moving;
	protected Thread t;
	protected JLabel CarLabel, FrogLabel;
	protected Frog frog;
	protected JButton StartButton;
	
	public Car() {
		super(0, 0, 100, 50, GameProperties.CHARACTER_STEP, "car_right.png");
		this.visible = true;
		this.moving = false;
	}
	
	public void setCarLabel(JLabel temp) {
		this.CarLabel = temp;
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
		System.out.println("Car - x,y: " + this.x + "," + this.y);
		System.out.println("width,height: " + this.width + "," + height);
		System.out.println("image: " + this.image);
		//super.Display();
		System.out.println("visible: " + this.visible);
		System.out.println("moving: " + this.moving);
	}
	
	public void startMoving() {
		System.out.println("Move!");
		if (!this.moving) {
			t = new Thread(this, "Car Thread");
			t.start();
		}
	}

	public String setCarType(){
		if(this.speed>0){
			return "car_right.png";
		} else {
			return "car_left.png";
		}
	}
	
	@Override
	public void run() {
		System.out.println("Car Thread started");
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
			//System.out.println("Car X, Y: " + this.x + "," + this.y);
			
			//check for collision
			if ( this.visible ) this.detectCollision();
			
			//pause
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("End Thread");
		this.StartButton.setText("Start");
		
	}
	
	private void detectCollision() {
		if (r.intersects( frog.getRectangle() )) {
			this.moving = true;
			frog.setX(375);
			frog.setY(500);
			frog.getX();
			frog.getY();
			this.FrogLabel.setLocation(frog.getX(), frog.getY());
			//SEND COLLISIONDETECTED to client
		}
	}
	


}