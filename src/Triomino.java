
public class Triomino {

	public int left, right, center;

	/**
	 * constructor
	 */
	public Triomino (int left, int right, int center) {
		this.left = left;
		this.right = right;
		this.center = center;
    }

	/**
	 * turn current triomino
	 */
	public Triomino rotation() {
		int tmp = this.left;
		this.left = this.right;
		this.right = this.center;
		this.center = tmp;

		return this;
    }

    @Override
	public String toString() {
		return (
			"[left=" + Integer.toHexString(this.left) + ", right=" + Integer.toHexString(this.right) + ", center=" + Integer.toHexString(this.center) + "]");
	}
    
	// getter for left
	public int getLeft() {
		return this.left;
	}

	// getter for right
	public int getRight() {
		return this.right;
	}

	// getter for center
	public int getCenter() {
		return this.center;
	}
}

