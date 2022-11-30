import java.awt.Rectangle;

public class Object {
	protected int x, y; //upper left, top position
	protected int height, width;
	protected int speed;
	protected String image;
	protected Rectangle r;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		this.updateRectanglePosition();
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		this.updateRectanglePosition();
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		this.updateRectangleSize();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
		this.updateRectangleSize();
	}
	public String getImage() {
		return image;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Object() {
		super();
		this.x = -1;
		this.y = -1;
		this.width = -1;
		this.height = -1;
		this.speed = GameProperties.CHARACTER_STEP;
		this.image = "";
		this.r = new Rectangle(0,0,0,0);
	}
	public Object(int x, int y, int height, int width, int speed, String image) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.image = image;
		this.r = new Rectangle(x,y,width,height);
	}
	
	public void Display () {
		System.out.println("x,y: " + this.x + "," + this.y);
		System.out.println("width,height: " + this.width + "," + height);
		System.out.println("speed: " + this.speed);
		System.out.println("image: " + this.image);
	}

	
	
	public void updateRectanglePosition() {
		this.r.x = this.x;
		this.r.y = this.y;
	}
	public void updateRectangleSize() {
		this.r.width = this.width;
		this.r.height = this.height;
	}
	
	public Rectangle getRectangle() {
		return this.r;
	}
}