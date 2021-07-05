public class PairInt {
	
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/** 
	 * @return the value of x for the current PairInt
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the value of y for the current PairInt
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param x the value x should be set to
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y the value y should be set to
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/*
	 * determines whether two PairInt objects are equal or not
	 * @param Object that is being compared to a PairInt object
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Object p) {
		if ( (PairInt) p == this) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * provides string representation of PairInt object
	 * @return the String version of a PairInt object
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * provides a copy of a PairInt
	 * @return PairInt object that is a copy of another
	 */
	public PairInt copy() {
		int copyX = this.x;
		int copyY = this.y;
		PairInt copy = new PairInt(copyX, copyY);
		return copy;
	}
}
