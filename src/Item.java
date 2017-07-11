
public abstract class Item {
	private String name;
	private float weight = 0;
	private Player player;
	
	public Item(String itemName, float itemWeight, Player player){
		this.name = itemName;
		this.weight = itemWeight;
		this.player = player;
	}
	public String getName(){
		return this.name;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public float getWeight(){
		return this.weight;
	}
	public Player getPlayer(){
		return this.player;
	}
	public abstract void use();

}
