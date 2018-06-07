package csc232;

//Authors: Daryl P Boggs, Taras Tataryn, Dominick Amalraj, and Christopher Yount
//Changed: 27 April 2016

/*
 * This is where it all comes together. It starts by making
 * the first location, and then creates the command line 
 * interface.

=======

/*Todolist:
 * 1. Allow a player to solve a puzzle with an item. 
 * Make the PuzzleItem more like ContainerItem. 
 * Test this by changing Sam Hill so that a soul appears in the players inventory 
 * and he gives the devil the soul solving the puzzle. - Wont happen.
 * 2. Fix PuzzleItem so that correct answer is not listed first. - Done
 * 3. More locations. More puzzles. Add Knight PuzzleItems to both level 1 doors.
 * 4. Anything else in Project 5.
 * 5. Update comments.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Driver 
{
	private static ArrayList<Location> map;
	private static int mapIndex;
	private static ContainerItem inventory;
	public static void help()
	{
		System.out.println("Valid Commands:");
		System.out.println("look");
		System.out.println("inventory");
		System.out.println("interact");
		System.out.println("[answer]");
		System.out.println("examine [item]");
		System.out.println("take [item]");
		System.out.println("take [item] from [container]");
		System.out.println("drop [item]");
		System.out.println("put [item] in [container]");
		System.out.println("go [direction]");
	}
	public static void look()
	{
		System.out.println(map.get(mapIndex).getDesc());
		
		if(map.get(mapIndex).getNumItems() == 0 && map.get(mapIndex).getNumHolders() == 0 && map.get(mapIndex).getRiddler() == null)
		{
			System.out.println("There aint nothing here.");
			return;
		}
		if(map.get(mapIndex).getNumItems() > 0 || map.get(mapIndex).getNumHolders() > 0)
		{
			System.out.print("These items are here: ");
		}
		if(map.get(mapIndex).getNumItems() > 0)
		{
			if(map.get(mapIndex).getNumItems() == 1)
			{
				System.out.print(map.get(mapIndex).getItem(0).getName());
			}
			else if(map.get(mapIndex).getNumItems() == 2 && map.get(mapIndex).getNumHolders() == 0)
			{
				System.out.print(map.get(mapIndex).getItem(0).getName() + " and " + map.get(mapIndex).getItem(1).getName());
			}
			else
			{
				for(int i = 0; i < map.get(mapIndex).getNumItems(); i++)
				{
					System.out.print(map.get(mapIndex).getItem(i).getName());
					if( i < map.get(mapIndex).getNumItems()-1 || (i == map.get(mapIndex).getNumItems()-1 && map.get(mapIndex).getNumHolders() > 0))
					{
						System.out.print(", ");
					}
					else if( i == map.get(mapIndex).getNumItems()-1)
					{
						System.out.print(", and ");
					}
				}
			}
		}
		if(map.get(mapIndex).getNumItems() > 0 && map.get(mapIndex).getNumHolders() > 0)
		{
			System.out.print(", ");
		}
		if(map.get(mapIndex).getNumHolders() > 0)
		{
			for(int i = 0; i < map.get(mapIndex).getNumHolders(); i++)
			{
				System.out.print(map.get(mapIndex).getHolder(i).getName());
				if( i < map.get(mapIndex).getNumHolders()-1)
				{
					System.out.print(", ");
				}
				else if( i == map.get(mapIndex).getNumHolders()-1)
				{
					System.out.print(", and ");
				}
			}
		}
		if(map.get(mapIndex).getRiddler() != null)
		{
			System.out.print("Someone is here: " + map.get(mapIndex).getRiddler().getName());
		}
		System.out.print("\n");
	}
	public static void inventory()
	{
		System.out.println(inventory.getDesc());
	}
	public static void answer(String a)
	{
		if(a.equals(map.get(mapIndex).getRiddler().getCorrect()))
		{
			if(map.get(mapIndex).getRiddler().getName().equals("devil"))
			{
				System.out.println("You trade your soul to the Devil to continue on your quest. I'd say 'you weren't using it anyway' to ridicule such poor judgement, but you were already burning, right?");
				System.out.println("The Devil: HA! HA! HA! HA! GO BACK TO THE TEMPLE AND COMPLETE YOUR QUEST!");
				mapIndex = 1;
				System.out.println(map.get(mapIndex).getDesc());
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("riddle#1"))
			{
				System.out.println("Woof...go to Sam Hill.");
				
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("drake"))
			{
				System.out.println("Drake says another one. You have passed my test. Good luck shawty, and by God's grace don't make it to Sam Hill.");
				map.get(mapIndex).setLocked("south");
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("doorai"))
			{
				System.out.println("The mechanical AI door sighs. It tells you that you can pass. The west is open.");
				map.get(mapIndex).setLocked("west");
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("final"))
			{
				System.out.println("Duhhhhhhhhhh... you wumbo. The gates open. A staircase leads you to a steampunk rocket. You walk in to the rocket, the doors close behind you. You feel unsafe, but there's a pulley like in the trolley cars. You take off. Just as you begin to see the stars, a bright light flashes above the ship. The ship goes into it. The floating steampunk city appears in the clouds. You have passed this difficult yet very easy trial. Congratulations.");
				map.get(mapIndex).setLocked("south");
				
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("fluffy"))
			{
				System.out.println("You get the riddle right... unexpectedly. Fluffy walks South and stops in front of the next hall.");
				map.get(mapIndex).setLocked("south");
				map.get(mapIndex).removePuzzle("fluffy");
			}
			else if(map.get(mapIndex).getRiddler().getName().equals("angryfluffy"))
			{
				System.out.println("You get the riddle right... unexpectedly. Fluffy runs off into the darkness, all three heads howling loudly.");
				map.get(mapIndex).setLocked("west");
				map.get(mapIndex).removePuzzle("angryfluffy");
			}
		}
		else
		{
			System.out.println("You answered wrong.");
		}
	}
	public static void examine(String item)
	{
		//Assumes only one item of a name exists. Don't duplicate item names!!!!!!!!!
		Item tempI = map.get(mapIndex).getItem(item);
		Item tempI2 = inventory.getItem(item);
		ContainerItem tempH = map.get(mapIndex).getHolder(item);
		if(tempI != null)
		{
			System.out.println(tempI.getDesc());
		}
		else if(tempI2 != null)
		{
			System.out.println(tempI2.getDesc());
		}
		else if(tempH != null)
		{
			System.out.println(tempH.getDesc());
		}
		else
		{
			System.out.println("I dont know what that is.");
		}
	}
	public static void interact()
	{
		RiddleItem temp = map.get(mapIndex).getRiddler();
		if(temp != null)
		{
			System.out.println(temp.getDesc());
			if(temp.getName().equals("devil"))
			{
				System.out.println("You continue burning.");
			}
			else if(temp.getName().equals("drake"))
			{
				temp.getResponses();
			}
			else if(temp.getName().equals("doorai"))
			{
				System.out.println("Hint: What is Aladdin's magical word? The mechanical AI door says think along the lines of a 'alakazoo' 'alakazam'. It's two words combined.");
				
			}
			else if(temp.getName().equals("finalRiddle"))
			{
				System.out.println("So... what is it?");
			}
			else if(temp.getName().equals("fluffy"))
			{
				System.out.println("So what is your answer? ");
			}
			else if(temp.getName().equals("angryfluffy"))
			{
				System.out.println("So what is your answer? ");
			}
		}
		else
		{
			System.out.println("I dont know what that is.");
		}
	}
	public static void take(String item)
	{
		Item temp = map.get(mapIndex).getItem(item);
		if(temp == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		inventory.addItem(temp);
		map.get(mapIndex).removeItem(item);
	}
	public static void drop(String item)
	{
		Item temp = inventory.getItem(item);
		if(temp == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		map.get(mapIndex).addItem(temp);
		inventory.removeItem(item);
	}
	public static void go(String dir)
	{
		if(map.get(mapIndex).isLocked(dir))
		{
			System.out.println("You cant go that way yet.");
			return;
		}
		String locName;
		if(dir.equals("south"))
		{
			locName = map.get(mapIndex).getSouth();
			for(int k = 0; k < map.size(); k++)
			{
				if(map.get(k).getName().equals(locName))
				{
					mapIndex = k;
					System.out.println(map.get(k).getDesc());
					if(map.get(k).getName().equals("mtrap1"))
					{
						mapIndex = 0;
						System.out.println(map.get(0).getDesc());
					}
					return;
				}
			}
			System.out.println("You cant go that way.");
		}
		else if(dir.equals("north"))
		{
			locName = map.get(mapIndex).getNorth();
			for(int k = 0; k < map.size(); k++)
			{
				if(map.get(k).getName().equals(locName))
				{
					mapIndex = k;
					System.out.println(map.get(k).getDesc());
					/*if(map.get(k).getName().equals("wtrap1"))
					{
						mapIndex = 0;
						System.out.println(map.get(0).getDesc());
					}*/
					return;
				}
			}
			System.out.println("You cant go that way.");
		}
		else if(dir.equals("west"))
		{
			locName = map.get(mapIndex).getWest();
			for(int k = 0; k < map.size(); k++)
			{
				if(map.get(k).getName().equals(locName))
				{
					mapIndex = k;
					System.out.println(map.get(k).getDesc());
					if(map.get(k).getName().equals("wtrap1"))
					{
						mapIndex = 0;
						System.out.println(map.get(0).getDesc());
					}
					return;
				}
			}
			System.out.println("You cant go that way.");
		}
		else if(dir.equals("east"))
		{
			locName = map.get(mapIndex).getEast();
			for(int k = 0; k < map.size(); k++)
			{
				if(map.get(k).getName().equals(locName))
				{
					mapIndex = k;
					System.out.println(map.get(k).getDesc());
					if(map.get(k).getName().equals("elevel3"))
					{
						mapIndex = 0;
						System.out.println(map.get(0).getDesc());
					}
					else if(map.get(k).getName().equals("elevel4"))
					{
						mapIndex = 0;
						System.out.println(map.get(0).getDesc());
					}
					return;
				}
			}
			System.out.println("You cant go that way.");
		}
		else
		{
			System.out.println(dir + " aint a direction!");
		}
	}
	public static void take(String item, String place)
	{
		ContainerItem tempH = map.get(mapIndex).getHolder(place);
		if(tempH == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		Item tempI = tempH.getItem(item);
		if(tempI == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		inventory.addItem(tempI);
		tempH.removeItem(item);
	}
	public static void put(String item, String place)
	{
		ContainerItem tempH = map.get(mapIndex).getHolder(place);
		if(tempH == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		Item tempI = tempH.getItem(item);
		if(tempI == null)
		{
			System.out.println("I dont know what that is.");
			return;
		}
		tempH.addItem(tempI);
		inventory.removeItem(item);
	}
	public static void main(String[] args) 
	{
		Scanner reader = new Scanner(System.in);
		map = new ArrayList<Location>();
		mapIndex = 0;
		inventory = new ContainerItem("Inventory", "Container", 5);
		
		//Create map.
		Location samHill = new Location(); //Index 0, Sam Hill
		RiddleItem devil = new RiddleItem();
		samHill.addRiddler(devil);
		
		
		map.add(samHill);
		Location start = new Location("start", "You are surrounded on all sides by the thick Caribbean forests, except in front of you is gateway with etchings of trains, steamstacks, and a magnificent floating steampunk city.", "mlevel1", null, null, null); //1
		map.add(start);
		RiddleItem test = new RiddleItem("Riddle#1", "What am I?", "Dog", "Cat", "Sheep", "Moose", 0);
		start.addRiddler(test);
		Location MLevel1 = new Location("mlevel1", "To the South is a hallway. The walls are lined with harpoon guns. At the end is a great gateway. In front of that gateway are three small towers with 64 rings. East and West are two open areas (free of scary weapons), each with a doorway on the South wall.", "mtrap1", "start", "wlevel1", "elevel1"); //2
		map.add(MLevel1);
		Location MTrap1 = new Location("mtrap1", "You should not have entered the hallway you fool! You are being impaled by harpoons! You die and go to Sam Hill...", null, null, null, null); //3
		map.add(MTrap1);
		Location WLevel1 = new Location("wlevel1", "To the east is the area you came from and the opposing area with the door on its South. To your South is a like door, with more magnificient Steampunk etchings. Before the door is a suit of knight's armour with a steampowered jetpack on its back. To the west is a small corridor with a podium inside. On top of this podium is steamed brocolli and you are mighty hungry.", "wlevel2", null, "wtrap1", "mlevel1", false, false, false, false); //4
		map.add(WLevel1);
		Location WTrap1 = new Location("wtrap1", "You follow your stomach into the corridor with the steamed broccoli. It doesn't occur to you to think about how steamed brocolli is just laying around in a temple, but you probably should have as it is actually just steam with a hologram. And so is the floor beneath you. As you read this, you are falling through the steam! You die and go to Sam Hill...", "wlevel2", null, null, null); //5
		map.add(WTrap1);
		Location ELevel1 = new Location("elevel1", "To the west is the area you came from and the opposing area with the door on its South. To your South is a like door, with more magnificient Steampunk etchings. Before the door is a suit of knight's armour with a steampowered jetpack on its back.", "elevel2", null, "mlevel1", null); //6
		map.add(ELevel1);
		Location WLevel2 = new Location("wlevel2", "To the north is where you just came from. To the east are walls lined with pure gold, and a mysterious hallway lined with pure gold. Your gut tells you not to, but the gold is enticing... To the west is another door, lined with etchings of Ancient Greek. And finally, to the south is a mysterious man who looks like Drake... and he looks like he has something to say...", "wlevel3", null, "wdoor2", "elevel2", true, false, false, false);
		RiddleItem drake = new RiddleItem( "drake", "Don't ask why I am here. I was sent to ask you what the next number in this sequence is: 1, 11, 21, 1211, 111221, ___ . Hint: read each number out loud. Which number is next?", "312211", "132211", "12", "3221112", 0);
		map.add(WLevel2);
		WLevel2.addRiddler(drake);
		Location WDoor2 = new Location ("wdoor2","You look to the north and you are blinded. Can't go that way... To the west is a wall with no sign of anything allowing you through. To the south you see something shiny, but can't tell what it is. Do you trust yourself? To the East is the corridor in which you just came from.", "skip", null, null, "wlevel2");
		map.add(WDoor2);
		Location skip = new Location ("skip", "You approach this shiny object, and notice it looks like a yellow sticky note... might be useful? To the west is absolutely nothing. To the north is where you came from, and you can't see what is to the south because of a barrage of humongous rocks. To the east is the mysterious man you saw earlier", null, "wdoor2", null,"wlevel3");
		Item stickyNote = new Item( "note", "Help", "The sticky note has a weird number on it: 312211");//add item (sticky note with answer to riddle with mysterious man)
		skip.addItem(stickyNote);
		map.add(skip);
		RiddleItem doorPuzzle = new RiddleItem("doorai", "The door is equipped with a steampunk mechanical AI. It asks you for a passcode", "opensesame");
		
		Location WLevel3 = new Location ("wlevel3", "You feel somewhat confident after getting past the mysterious man. Maybe east is the way to go this time? To the east is a hallway that is black in which you can't tell where it leads to. To the west is a metal door that most certainly can't be tampered with. To the south you see a hallway etched with weird ancient designs. Behind you to the north is the man you just spoke to...", "wlevel4", "wlevel2", "wdoor3", "mlevel3", false, false, true, false);
		map.add(WLevel3);
		WLevel3.addRiddler(doorPuzzle);
		Location WDoor3 = new Location("wdoor3", "You see a long hallway to your south. At the end of that hallway is a big gateway with a steampunk rocket etched on it in stone. You get excited. You start to run.", "final", null, null, "wlevel3");
		map.add(WDoor3);
		Location MLevel3 = new Location ("mlevel3", "Bats fly around as you get scared and start running through the dark hallway. Eventually you run into the side of a wall and suddenly everything illuminates. To the west is where you came from... no guarantees on finding your way back. To the east is another dark hallway... try your luck again? To the south is another room. You can't see inside it. You can't go north.", "mlevel4", null, "wlevel3", "elevel3");
		map.add(MLevel3);
		Location ELevel3 = new Location("elevel3", "You walk down the dark hallway and smell a weird aroma... like honey and beeswax. A swarm of bees suddenly appear and they are humongous and angry, and evil, and steampunk. You are stung to death to SamHill.", null, null, null, null);
		map.add(ELevel3);
		Location ELevel4 = new Location("elevel4", "Everyone in the McDonalds got mad cow from bad hamburgers. They attack and send you to Sam Hill.", null, null, null, null);
		map.add(ELevel4);
		Location WLevel4 = new Location("wlevel4", "The ancient designs look like the temple from the book of Ezekiel. To the north is where you just came from. To the west is nothingness. To the south is absolutely nothing. To the east is a 3 headed dog resembling the one in Harry Potter...", null, "wlevel3", null, "mlevel4");
		map.add(WLevel4);
		RiddleItem fluffy = new RiddleItem("fluffy", "As you approach Fluffy, the three heads look quite happy, in fact, they look super cute. You reach to pet them. AAAAAAAAAAAAAHHHHHH. One of the heads nearly bites off your arm. The dogs suddenly start to speak... English. They ask What grows when it eats, but dies when it drinks? Again, no hints.", "fire");
		Location MLevel4 = new Location("mlevel4", "You reach the three headed dogs. To the west is where you came from. To the north are crazy bats and a dark hallway. To the east is a McDonalds cafe.... wait what? To the south is a great hallway lined with gold. However the dogs are blocking you.", "mlevel5", "mlevel3", "wlevel4", "elevel4", true, false, false, false);
		map.add(MLevel4);
		MLevel4.addRiddler(fluffy);
		Location MLevel5 = new Location("mlevel5", "You follow the three headed dog into the hallway. He just growls at you. To the West is a hallway leading to the gateway, but Fluffy seems to want ask you another riddle.", null, "mlevel4", null, "final", false, false, true, false);
		RiddleItem fluffy2 = new RiddleItem("angryfluffy", "Fluffy seems angry now. The heads ask, 'What goes up and down, without moving?' Again, no hints.", "stairs");
		MLevel5.addRiddler(fluffy2);
		map.add(MLevel5);
		
		Location finalLoc = new Location("final", "You walk down this hallway for what seems like 10 minutes and finally reach the gate. The gate looks scary.", "floatingcity", "wdoor3", "mlevel5", null, true, false, false, false);
		map.add(finalLoc);	
		RiddleItem finalRiddle = new RiddleItem("final", "The gate is locked and is etched with weird images that seem to resemble Spongebob Squarepants and Patrick Star. Suddenly, a hologram of Patrick Star appears. He asks you: 'What is brown and sticky'? Sorry. No clues.", "stick");
		finalLoc.addRiddler(finalRiddle);
		mapIndex = 1;
		System.out.println(map.get(mapIndex).getDesc());
		/*Location start = new Location("The Entrance", "You are surrounded on all sides by the thick Caribbean forests, except in front of you is gateway with etchings of trains, steamstacks, and a magnificent floating steampunk city.");
		Item key = new Item("key", "Key", "The key is of the ancient kind. It obviously unlocks something ancient.");
		Item riddle1 = new Item("GoalPaper", "Riddle", "Congratulations on getting to the temple. Unfortunately it aint that easy. To get to the magnificent floating steampunk city you must solve all the riddles in the temple and if you lose, too bad. If you dont know what command to enter, enter 'help.'");
		ContainerItem basketCase = new ContainerItem("BasketCase", "Container", 5);

		Item basket1 = new Item("Basket", "Container", "This wicker basket needs a case");
		Item basket2 = new Item("Basket", "Container", "This wicker basket needs a case");
		
		map.add(start);
		
		map.get(mapIndex).addItem(key);
		map.get(mapIndex).addItem(riddle1);
		map.get(mapIndex).addHolder(basketCase);
		map.get(mapIndex).getHolder("BasketCase").addItem(basket1);
		map.get(mapIndex).getHolder("BasketCase").addItem(basket2);
		
		System.out.println(start.getDesc());
		System.out.println(riddle1.getDesc());*/
		
		while(true)
		{
			String line = reader.nextLine();
			String[] array = (line.toLowerCase()).split(" ");
			
			if(array.length == 1)
			{
				if(array[0].equals("quit"))
				{
					break;
				}
				else if(array[0].equals("help"))
				{
					help();
				}
				else if(array[0].equals("look"))
				{
					look();
				}
				else if(array[0].equals("inventory"))
				{
					inventory();
				}
				else if(array[0].equals("interact"))
				{
					interact();
				}
				else if(map.get(mapIndex).getRiddler().isOption(array[0]))
				{
					answer(array[0]);
				}
				else
				{
					System.out.println("Please ask the right question, sir.");
				}
			}
			else if(array.length == 2)
			{
				if(array[0].equals("examine"))
				{
					examine(array[1]);
				}
				else if(array[0].equals("take"))
				{
					take(array[1]);
				}
				else if(array[0].equals("drop"))
				{
					drop(array[1]);
				}
				else if(array[0].equals("go"))
				{
					go(array[1]);
				}
				else
				{
					System.out.println("Please ask the right question, sir.");
				}
			}
			else if(array.length == 4)
			{
				if(array[0].equals("take") && array[2].equals("from"))
				{
					take(array[1], array[3]);
				}
				else if(array[0].equals("put") && array[2].equals("in"))
				{
					put(array[1], array[3]);
				}
				else
				{
					System.out.println("Please ask the right question, sir.");
				}
			}
			else
			{
				System.out.println("Please ask the right question, sir.");
			}
		}
		reader.close();
	}
}
