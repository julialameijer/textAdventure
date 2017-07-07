
public class Item {
	private String name;
	private int weight = 0;
	public Item(String string) {
		name = string;
	}
	public String getName(){
		return name;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}

}
