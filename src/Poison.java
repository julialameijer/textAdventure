
public class Poison extends Item {

	public Poison(String itemName, float itemWeight, Player player) {
		super(itemName, itemWeight, player);
		
	}
	public void use(){
		this.getPlayer().damage();
	}
	
}
