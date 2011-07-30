
public class Disk {
	double radius, x, y, velx, vely;
	boolean noMove;
	static final double GRAVITY = -50;
	public Disk (double radius, double x, double y){
		this.radius = radius;
		this.x = x;
		this.y = y;

		this.noMove = false;
		
	}
	public boolean intersects(Disk disk){
		boolean intersect = ((Math.pow(this.x - disk.x, 2) + Math.pow(this.y - disk.y, 2)) <
				Math.pow(this.radius + disk.radius, 2));
		return intersect;
	}
	
	
	public boolean intersects(Box box){
		boolean intersect = ((Math.abs(this.x - box.x) < this.radius + (box.side) / 2
				&& (Math.abs(this.y - box.y)) < this.radius + (box.side) / 2));
		return intersect;
	}
	
	public boolean isInside(double x, double y){
		double smalldist = Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2);
		boolean inside = smalldist < Math.pow(this.radius, 2);
		return inside;
	}
	
	public double distance(double x, double y){
		double bigdist = Math.sqrt(Math.pow(x-this.x, 2) + Math.pow(y-this.y, 2));
		return bigdist;
	}
	
	public boolean close(double x, double y, double diff){
		return this.distance(x,y)<diff;
	}
	
	public void moves(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void update(long time){
		vely += (time / 500.0) * GRAVITY;
		x += velx * (time / 500.0);
		y += vely * (time / 500.0);
	}
	

	public String toString(){
		return "box with x=" + this.x + ", y=" +this.y +
			", side=" + this.radius;
	}
	

}
