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
    Room lobby, outside, pub, restaurant, office, hotelRoom, secretRoom;
    Item key, food, specialDrink, box, poison;
    

    public Game()
    {
        parser = new Parser();
        player = new Player();
        createItems();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    

    private void createRooms()
    {
        

        // create the rooms
        lobby = new Room("in the lobby of the Big State Hotel");
        outside = new Room("at the enterance of the hotel");
        pub = new Room("in the hotel club");
        restaurant = new Room("in the hotels restaurant");
        office = new Room("in the computing admin office");
        hotelRoom = new Room("in your own room");
        secretRoom = new Room("in the secret room, search the drink!!");

        lobby.getInventory().addItem(key);
        
        // initialise room exits
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
        player.setCurrentRoom(lobby);  // start game lobby
    }
    private void createItems()
    {
      
        key = new Item("key");
        food = new Item("Food");
        specialDrink = new Item("The special drink");
        key.setWeight(3);
        food.setWeight(7);
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

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Cursed!");
        System.out.println("Enjoy my textadventure");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    
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
        else if (commandWord.equals("inventory"))
        	System.out.println(player.getInventory().showItems());
        	
        else if(commandWord.equals("drop"))
        	player.dropItem(command.getSecondWord());
        return wantToQuit;
        
        
        
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     * magic box, you are cursed and in the magic box is a poison what can help you solve your curse.
     */
    
    
    private void printHelp()
    {
        System.out.println("You are cursed, and in the hotel is a box.");
        System.out.println("You went over to the hotel to find that box.");
        System.out.println("in that very special box is a poison what can help you solve your curse.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
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

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
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
