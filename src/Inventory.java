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
	
	public void useItem(String itemName){
		for(int i = 0; i < itemList.size(); i++){
			if(itemList.get(i).getName().compareTo(itemName) == 0){

				itemList.get(i).use();
			}
		}
		System.out.println("You can't use that item");
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
}
