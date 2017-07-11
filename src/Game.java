/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game
{
    private Parser parser;
    private Player player;
    private Item item;
    Room lobby, outside, pub, restaurant, office, hotelRoom, secretRoom;
    

    public Game()
    {
        parser = new Parser();
        player = new Player();
        createRooms();
    }
    

    private void createRooms()
    {
        

        // create the rooms
        lobby = new Room("in the lobby of the Big State Hotel");
        outside = new Room("at the enterance of the hotel");
        pub = new Room("in the hotel club");
        restaurant = new Room("in the hotels restaurant");
        office = new Room("in the computing admin office");
        hotelRoom = new Room("in your own room..." + ".\n" + "huh.. Maybe you can open the air vent and go trough the airshaft..");
        secretRoom = new Room("in the secret room, search the drink!!");
        
        Key key = new Key("key", 1, player); 
        Poison drink = new Poison("drink", 5, player);

        lobby.getInventory().addItem(key);
        hotelRoom.getInventory().addItem(drink);
        
        hotelRoom.addOpenKey(key);
        hotelRoom.closeRoom();
        
        
        lobby.setExit("east", outside);
        lobby.setExit("south", restaurant);
        lobby.setExit("west", pub);
        lobby.setExit("up", hotelRoom);

        outside.setExit("west", lobby);

        pub.setExit("east", lobby);

        restaurant.setExit("north", lobby);
        restaurant.setExit("east", office);

        office.setExit("west", restaurant);
        
        hotelRoom.setExit("down", lobby);
        hotelRoom.setExit("airshaft", secretRoom);
        
        secretRoom.setExit("airshaft", hotelRoom);
        
        player.setCurrentRoom(lobby);  // start game lobby
        
    }



    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Cursed!");
        System.out.println("Enjoy my textadventure");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if(commandWord.equals("look"))
        	look(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if(commandWord.equals("eat"))
        	player.eat();
        else if(commandWord.equals("take")){
        	if(command.hasSecondWord()){
        		player.Pickup(command.getSecondWord());
        	}
        	else{
        		System.out.println("What do we need to take?");
        	}
        }
        else if(commandWord.equals("use")){
        	player.getInventory().useItem(command.getSecondWord());
        }
        else if (commandWord.equals("inventory"))
        	System.out.println(player.getInventory().showItems());
        	
        else if(commandWord.equals("drop"))
        	player.dropItem(command.getSecondWord());
        return wantToQuit;
        
        
        
    }

    private void printHelp()
    {
        System.out.println("You are cursed, and in the hotel is a box.");
        System.out.println("You went over to the hotel to find that box.");
        System.out.println("in that very special box is a poison what can help you solve your curse.");
        System.out.println("Because of the curse, you're losing health every step you take..");
        System.out.println("If your health is running low, you can go to the restaurant and eat something");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
       

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        
        if(!nextRoom.isOpen()){
        	System.out.println("The door is closed, search the key");
        	return;
        }
        
        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            player.normalDamage();
            System.out.println("Health = " + player.health);
            if(player.health <= 0){
            	System.out.println("You are dead!");
            	
            	
            	
            }
        }
    }
    private void look(Command command){
    	if(!command.hasSecondWord()){
    		System.out.println(player.getCurrentRoom().getLongDescription() + "\n" + player.getCurrentRoom().getInventory().showItems());
    		return;
    	}
    		
    }


    public boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
}
