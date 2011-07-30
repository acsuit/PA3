
public class GameMain {
	public static void main(String[] args) {
		GameModel game = new GameModel();
		game.boxes.add(new Box(0f,1f,2f,false));
		game.jewels.add(new Box(0f,3f,2f,true));
		Disk d = new Disk(0f,10f,1f);
		game.disks.add(d);
		d.velx=1; d.vely=20;
		game.toString();
		
		for(int i=0; i<200; i++) {
			System.out.println("Level "+i);
			game.updateGame(100L*i);
			System.out.println(game.toString());
		}



		System.out.println("completing the method");
		}
}
