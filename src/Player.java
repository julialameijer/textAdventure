
public class Player {
	private Room room; 
	int health = 100;
	private Inventory inventory;
	
	public Player(){
		inventory = new Inventory();
	}

	public Room getCurrentRoom() {
		return room;
	}
	public void Pickup(String itemName){
		Item removedItem = getCurrentRoom().getInventory().removeItem(itemName);
		
		if(removedItem == null){
			System.out.println("We couldn't find the item!");
			return;
		}
		inventory.addItem(removedItem);
	}
	
	public void dropItem(String itemName){
		Item addItemToRoom = getInventory().removeItem(itemName);
		if(addItemToRoom == null){
			System.out.println("We couldn't find the item!");
			return;
		}
		getCurrentRoom().getInventory().addItem(addItemToRoom);
	}
	
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	public void setCurrentRoom(Room room) {
		this.room = room;
	}
	
	public void normalDamage(){
		health -= 10; 
	}
	public void damage(){
		health -= 10;
	}
	public void heal(){
		health = health += 10;
		System.out.println(health);
	}
	public void eat(){
		if(getCurrentRoom().getShortDescription().equals("in the hotels restaurant")){
    		if(health>= 100){
    			System.out.println("You are already full!");
    		}else{
    			heal();
    		}
    		
    	}else{
    		System.out.println("You can only eat in the restaurant!");
    	}
	}
	
}
