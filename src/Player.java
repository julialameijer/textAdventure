
public class Player {
	private Room room; 
	int health = 100;
	public Player(){
		
	}

	public Room getCurrentRoom() {
		return room;
	}

	public void setCurrentRoom(Room room) {
		this.room = room;
	}
	public void normalDamage(){
		health = health -= 10;
	}
	public void damage(){
		health = health -= 20;
	}
	public void heal(){
		health = health += 10;
		System.out.println(health);
	}
	
}
