/*
 * The Chopstick class has the methods take, release and getAvail.
 * Source Code: https://github.com/rupakraj/dining-philosopher/blob/master/Dine.java
 * 
 */
public class Chopsticks {
	boolean beingUsed;
	// Borrowed from GitHub, cited above
	String chopstickName;

	// Borrowed from GitHub, cited above
	public Chopsticks(String chopstickName) {
		this.chopstickName = chopstickName;
	}

	// identical to Github, but we had already created these methods
	// before as pickup and drop. Just named differently...
	public synchronized void take() {
		beingUsed = true;
	}

	public synchronized void release() {
		beingUsed = false;
	}
	//Added this method to see if was Available to take
	public boolean getAvail() {
		return beingUsed;
	}
}