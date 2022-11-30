//this is the object we will control via keyboard
public class Frog extends Object{
	
	public Frog() {
		super(375,550,50,50, GameProperties.CHARACTER_STEP, "frog.png");

		}

	public Frog(int x, int y, int height, int width, int speed, String image) {
		super(x, y, height, width, speed, image);
	}
	
}