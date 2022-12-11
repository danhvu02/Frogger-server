public enum Score {
	//using singleton to store data and get data from
	  INSTANCE;
	  private int score;
	  private int x;
	  private int y;
	  private Score(){
	    score = 0;
	    x = 0;
	  }
	  
	  public int getScore(){
	    return score;
	  }
	  
	  public void setScore(int score) {
		  this.score = score;
	  }
	  
	  public void addScore(int score){
	    this.score += score;
	  }
	  
	  public void minusScore(int score) {
		  this.score -= score;
	  }
	  
	  public int getX() {
		  return this.x;
	  }
	  public void setX(int x) {
		 this.x += x;
	  }
	  public int getY() {
		  return this.y;
	  }
	  public void setY(int y) {
		  this.y += y;
	  }
}	