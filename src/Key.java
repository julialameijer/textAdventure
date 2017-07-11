
public class Key extends Item {

	public Key(String itemName, float itemWeight, Player player) {
		super(itemName, itemWeight, player);
		
	}
	public void use(){
		this.getPlayer().getCurrentRoom().canOpen(this);
	}

}
