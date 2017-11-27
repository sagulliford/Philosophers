/*
 * This code will demonstrate a way to solve the Dining Philosopher problem. 
 * The Philosopher class has a main method, an eat method, a philosophize method 
 * and a run method.
 * Source Code: https://github.com/rupakraj/dining-philosopher/blob/master/Dine.java
 * 
 */

public class Philosopher extends Thread {
	// Assigning variables to be used later
	int state;
	Thread t;
	boolean running;
	boolean beingUsed;
	String _name;
	Chopsticks[] c;
	int index = 0;
	public Chopsticks leftC;
	public Chopsticks rightC;

	public static void main(String[] args) {
		// Creating an array of Philosophers
		Philosopher[] p = new Philosopher[5];
		// Creating an array of Chopsticks and initializing them
		Chopsticks[] c = new Chopsticks[5];
		for (int i = 0; i < c.length; i++) {
			c[i] = new Chopsticks("Chopstick: " + i);
		}

		// Assigning the Philosphers their right and left chopsticks
		// Source code borrowed from GitHub, source cited above
		p[0] = new Philosopher("P: 0 - ", c[0], c[1]);
		p[1] = new Philosopher("P: 1 - ", c[1], c[2]);
		p[2] = new Philosopher("P: 2 - ", c[2], c[3]);
		p[3] = new Philosopher("P: 3 - ", c[3], c[4]);
		p[4] = new Philosopher("P: 4 - ", c[0], c[4]);

		// For loop creating the 5 philosopher threads
		for (int i = 0; i < p.length; i++) {
			Thread t = new Thread(p[i]);
			System.out.println("Starting Philosopher " + i);
			t.start();
		}
	}

	// Philosopher class borrowed from GitHub, source cited above
	public Philosopher(String name, Chopsticks left, Chopsticks right) {
		// Declaring the variables values
		this.state = 1;
		this._name = name;
		leftC = left;
		rightC = right;
	}

	public void eat() {
		// method idea gotten from Nate who told us to check to
		// see if the left is available, then the right. If so
		// pick them both up.
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		// Checking to see if left chopstick is available
		if (!leftC.getAvail()) {
			// Checking to see if right chopstick is avialable
			if (!rightC.getAvail()) {
				// If the right and left chopsticks are available then pick both
				// chopsticks up
				leftC.take();
				rightC.take();
				long temp = 5 + (long) (Math.random() * 5000);
				System.out.println(_name + " : Eat for " + temp);
				// Philosopher is eating for a certain amount of time
				try {
					Thread.sleep(temp);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch bloc
					e.printStackTrace();
				}
				// Realsing the chopsticks after the Philosopher is done eating
				rightC.release();
				leftC.release();
			}

		}
		// Philosophize after they are done eating
		philosophize();
	}

	public void philosophize() { // kinda throwaway method so time will elapse
		this.state = 1; // this.state borrowed from GitHub, source cited above
		long temp = 5 + (long) (Math.random() * 5000);
		System.out.println(_name + " : Philosophize for " + temp);
		try {
			Thread.sleep(temp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eat();
	}

	public void run() {
		try {
			Thread.sleep(3000);
			// so all the threads can start at the same time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eat();
	}

}
