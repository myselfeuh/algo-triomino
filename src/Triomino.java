
public class Triomino {
    
	private int a, b, c;
    
	Triomino (int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
    }
    	
    @Override
	public String toString() {
		return "[a=" + Integer.toHexString(a) + ", b=" + Integer.toHexString(b) + ", c=" + Integer.toHexString(c) + "]";
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setC(int c) {
		this.c = c;
	}
    
}

