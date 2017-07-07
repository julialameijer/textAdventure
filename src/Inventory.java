import java.util.ArrayList;
import java.util.List;


public class Inventory {
	
	private List<Item> itemList;
	public Inventory(){
		 itemList = new ArrayList<Item>();
	}
	public String showItems(){
		String returnString = "Items: ";
		for (Item item : itemList) {
			returnString += item.getName() + " , "; 
			
		}
		return returnString;
	}
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public Item removeItem(String itemName){
		Item removedItem = null; 
		for (Item item : itemList) {
			if(item.getName().equalsIgnoreCase(itemName)){
				removedItem = item;
				break;
			}
		}
		
		if(removedItem != null){
			itemList.remove(removedItem);
		}
		return removedItem;
	}
	public void calculateItems(){
		int totalItems = itemList.size();
		System.out.println(totalItems);
	}
}
