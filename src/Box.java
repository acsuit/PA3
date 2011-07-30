
public class Box {
	double side, x, y;
	boolean isJewel;
	public Box (double side, double x, double y, boolean isJewel){
		this.side = side;
		this.x = x;
		this.y = y;
		this.isJewel = isJewel;
	}

	public boolean isInside(double x, double y){
		boolean inside = (Math.abs(x-this.x)<this.side/2) 
			&& (Math.abs(y-this.y)<this.side/2);
		return inside;
	}
	
	public void moves(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		if (this.isJewel = true){
			return "jewel with x=" + this.x + ", y=" +this.y +
				", side=" + this.side;
		}
		else
			return "box with x=" + this.x + ", y=" +this.y +
				", side=" + this.side;
	}

	
}
