import java.util.ArrayList;


public class GameModel {
	
	public ArrayList<Disk> disks = new ArrayList<Disk>();
	public ArrayList<Box> boxes = new ArrayList<Box>();
	public ArrayList<Box> jewels = new ArrayList<Box>();
	
	private long lTime, cTime, sTime = 0;
	
	public double gameLength=15;
	public double timeR;
	
	public int wins, losses;
	
	private long changetime;
	
	private boolean firstTime=true;
	
	public boolean levelDone=true;
	
	public boolean won = false;
	public boolean lost = false;
	
	public double gravity = -50;
	
	public GameModel(){
		
	}
	public void createLevel(int level){
		createLevel(level,500,500);
	}
	public void createLevel(int level, int width, int height){
		sTime = System.currentTimeMillis();
		timeR = gameLength;
		switch (level){
			case 1: 
				String levelstring="1, 150f, 500f, 50f; 2, 150f, 50f, 50f; 3, 180f, 150f, 50f";
				
			}
		}
	public void createLevelObjects(String level){
		String[] objects = level.split("; ");
		for (String object: objects){
			String[] objel = object.split(", ");
			int type = Integer.parseInt(objel[0]);
			double x = Double.parseDouble(objel[1]);
			double y = Double.parseDouble(objel[2]);
			double w = Double.parseDouble(objel[3]);
			switch (type){
			case 1:
				Disk d = new Disk(x, y, w);
				d.velx = 10;
				d.vely = 102;
				disks.add(d);
			case 2:
				boxes.add(new Box(x,y,w, false));
			case 3:
				jewels.add(new Box(x,y,w,true));
			}
		}
		}

	public Disk diskTouch(double x, double y){
		if (disks.size()==0) 
			return null;

		Disk closestDisk = disks.get(0);
		double closestDistance = closestDisk.distance(x, y);
		for (Disk d:disks){
			double d1 = d.distance(x, y);
			if (d1 < closestDistance){
				closestDistance = d1;
				closestDisk = d;
			}
		}
		if (closestDistance < 100)
			return closestDisk;
		else
			return null;
	}
	
	public Box boxTouch(double x, double y){
		for (Box b:boxes)
		if (b.isInside(x,y)) 
			return b;
		return null;
		}
	public Box jewelTouch(double x, double y){
		for (Box j:jewels)
		if (j.isInside(x,y)) 
			return j;
		return null;
	}
	public void resetGame(){
		disks.clear();
		boxes.clear();
		jewels.clear();
		levelDone=false;
		won=false;
		lost=false;
	}
	
	public void updateGame(long now) {
		if (levelDone) return; // don't do anything when the game is not being played ...

		cTime = now;
		if (firstTime){
			changetime=0;
			firstTime=false;
		} 
		else {
			changetime = cTime - lTime;
		}

		// see if the user has run out of time
		timeR = gameLength - (now-sTime)/1000f;
		if (timeR<0){
			levelDone=true; lost=true;
			losses += 1;
			return;
		}


		ArrayList<Disk> activeDisks = new ArrayList<Disk>();
		for (Disk d:disks){
			if (!d.noMove)
				activeDisks.add(d);
		}

		// if all of the disks are static (i.e. frozen), the level is over and the user lost.
		if (activeDisks.size()==0){
			levelDone=true; lost=true;
			losses += 1;
			return;
		}

		// if there are non-static disks, then we update their positions
		lTime = cTime;
		ArrayList<Box> hitJewel= new ArrayList<Box>();
		ArrayList<Disk> hitDisk = new ArrayList<Disk>();

		for (Disk d:activeDisks){
		// let gravity move the object over the time interval dt
			d.update(changetime);

		// check to see if the disk has hit any squares, if so it become static
			for (Box s:boxes)
				if (d.intersects(s))
					d.noMove=true;

		// check to see if the disk has hit any static disks, and make it static if it has
			for (Disk d1:disks)
				if (d.intersects(d1) && d1.noMove)
					d.noMove = true;

		// check to see if the disk has hit any targets, and if so the level is over..

			for (Box s:jewels)
				if (d.intersects(s)){
					hitJewel.add(s);
					hitDisk.add(d);
				}
	}

		for (Box s:hitJewel)
		jewels.remove(s);

		if (jewels.size()==0) {
			levelDone=true;
			won=true;
			wins += 1;
		}

		}
	
	public String toString(){
		java.lang.StringBuffer s= new StringBuffer();
		s.append("currenttime = "+lTime + "\n");
			for (Disk d:disks){
				s.append("disk "+d+"\n");
			}
			for (Box sq:boxes){
				s.append("square "+sq+"\n");
			}
			for (Box t:jewels){
				s.append("target "+t+"\n");
			}
			return s.toString();
		}







}
	
