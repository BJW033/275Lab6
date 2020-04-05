/*
 *  Brandon Wu 
 */
public class Model {
	private double xloc=0;
	private double yloc=0;
	double xIncrRef = 8;
	double yIncrRef = 3;
	double xIncr = 8;
	double yIncr = 3;
	double xIncrD[] = { -xIncr, 0, xIncr };
	double yIncrD[] = { -yIncr, 0, yIncr };
	String directionX ="east";
	String directionY="south";
	private Direction going=Direction.SOUTHEAST;
	int canvasWidth;
	int canvasHeight;
	int imgWidth;
	int imgHeight;
	BassMode mode = BassMode.DEFAULT;
	
	public Model(int CanvasWidth, int CanvasHeight, int ImageWidth,int ImageHeight) {
		canvasWidth = CanvasWidth;
		canvasHeight = CanvasHeight;
		imgWidth = ImageWidth;
		imgHeight = ImageHeight;
	}
	public void updateLocationandDirection() {
		xloc += xIncr;
		yloc += yIncr;
		if (xloc >= canvasWidth - imgWidth || xloc < -imgWidth / 5) {
			xloc = xloc - 2*xIncr;
			if (directionX.equals("west")) {
				xIncr = xIncrD[(int) (Math.random() * 10 % 2) + 1];
			} else if (directionX.equals("east")) {
				xIncr = xIncrD[(int) (Math.random() * 10 % 2)];
			} else {
				int choice[] = { -1, 1 };
				xIncr = xIncrD[choice[(int) (Math.random() * 10 % 2)] + 1];
			}
			deltaY();
			updateDirection();
			
		}

		if (yloc < -imgHeight / 5 || yloc >= canvasHeight - imgHeight) {
			yloc = yloc - 2*yIncr;
			if (directionY.equals("south")) {
				yIncr = yIncrD[(int) (Math.random() * 10 % 2) + 1];
			} else if (directionY.equals("north")) {
				yIncr = yIncrD[(int) (Math.random() * 10 % 2)];
			} else {
				int choice[] = { -1, 1 };
				yIncr = yIncrD[choice[(int) (Math.random() * 10 % 2)] + 1];
			}
			deltaX();
			updateDirection();
		}
		
		// If the directions are both empty, the loop continues to randomly find new
		// directions
		// till at least one is no longer empty.
		while (directionY.equals("") && directionX.equals("")) {
			xIncr = xIncrD[(int) (Math.random() * 10 % 3)];
			yIncr = yIncrD[(int) (Math.random() * 10 % 3)];
			updateDirection();
		}
	}
	private void updateDirection() {
		if (xIncr > 0) {
			directionX = "east";
		} else if (xIncr < 0) {
			directionX = "west";
		} else {
			directionX = "";
		}
		if (yIncr > 0) {
			directionY = "south";
		} else if (yIncr < 0) {
			directionY = "north";
		} else {
			directionY = "";
		}
		if("southeast".equals(directionY+directionX)) {
			going = Direction.SOUTHEAST;
		}
		else if("southwest".equals(directionY+directionX)) {
			going = Direction.SOUTHWEST;
		}
		else if("south".equals(directionY+directionX)) {
			going = Direction.SOUTH;
		}
		else if("northeast".equals(directionY+directionX)) {
			going = Direction.NORTHEAST;
		}
		else if("northwest".equals(directionY+directionX)) {
			going = Direction.NORTHWEST;
		}
		else if("north".equals(directionY+directionX)) {
			going = Direction.NORTH;
		}
		else if("east".equals(directionY+directionX)) {
			going = Direction.EAST;
		}
		else if("west".equals(directionY+directionX)) {
			going = Direction.WEST;
		}
	}

	// Randomly picks a new yIncr value
	private void deltaY() {
		yIncr = yIncrD[(int) (Math.random() * 10 % 3)];
	}

	// Randomly picks a new xIncr value
	private void deltaX() {
		xIncr = xIncrD[(int) (Math.random() * 10 % 3)];
	}
	public double getX() {
		return xloc;
	}
	public double getY() {
		return yloc;
	}
	public Direction getDirection() {
		return going;
	}
	/*
	 * Called when the controller detects the user pressed any key from WASD. 
	 * Sets the going direction to the new Direction Enum
	 * Sets the directionX/Y to the appropriate strings
	 * Sets the x and y Incr's to the appropriate North, South, East, West directions
	 */
	public void setDirection(Direction d) {
		going = d;
		switch(d) {
			case NORTH:
				directionY = "north";
				directionX = "";
				xIncr = xIncrD[1];
				yIncr = yIncrD[0];
				break;
			case SOUTH:
				directionY = "south";
				directionX = "";
				xIncr = xIncrD[1];
				yIncr = yIncrD[2];
				break;
			case EAST:
				directionX = "east";
				directionY = "";
				xIncr = xIncrD[2];
				yIncr = yIncrD[1];
				break;
			case WEST:
				directionX = "west";
				directionY = "";
				xIncr = xIncrD[0];
				yIncr = yIncrD[1];
				break;
				
		}
		
	}
/* Called when the user presses a key that impacts the images Mode. If the newly sent in 
 * 	Mode is DEFAULT, the increments and increment arrays of both x and y are set to the default
 * given by x/yIncrRef. IF the Mode is ATTAC then the increments and increment arrays are doubled.
 * Lastly if the MODE is CONFUSE then the increments and increment arrays are halved. 
 */
	public void setBassMode(BassMode bm) {
		mode = bm;
		switch(bm) {
			case DEFAULT:
				updateArrays(0);
				if(xIncr != 0) {
					xIncr = xIncrRef * xIncr/Math.abs(xIncr);
				}
				
				if(yIncr != 0) {
					yIncr = yIncrRef * yIncr/Math.abs(yIncr);
				}				
				break;
			case ATTAC:
				xIncr = xIncr*2;
				yIncr = yIncr*2;
				updateArrays(2);
				break;
			case CONFUSE:
				xIncr = xIncr/2;
				yIncr = yIncr/2;
				updateArrays(0.5);
				break;
				
		}
	}
	public BassMode getBassMode() {
		return mode;
	}
/*Called when the behavior changes. If x = 0 then the array of increments are reset 
 * to the defaults stored in xIncrRef or yIncrRef
 * Otherwise the increments are multiplied by the given value x.
*/
	private void updateArrays(double x) {
		if(x==0) {
			for(int a=-1;a<xIncrD.length-1; a++) {
				xIncrD[a+1] = xIncrRef * a;
			}
			for(int a=-1;a<yIncrD.length-1; a++) {
				yIncrD[a+1] = yIncrRef * a;
			}
		}
		else {
			for(int a=0;a<xIncrD.length; a++) {
				xIncrD[a] = xIncrD[a] * x;
			}
			for(int a=0;a<yIncrD.length; a++) {
				yIncrD[a] = yIncrD[a] * x;
			}
			
		}
	}
}
