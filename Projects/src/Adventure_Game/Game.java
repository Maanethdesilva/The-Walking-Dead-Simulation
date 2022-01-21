package Adventure_Game;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Room[] roomMap = new Room[9];

		// Imports all the rooms
		rooms(roomMap);
		
		System.out.println("\t\tWELCOME TO THE WALKING DEAD STIMULATION" + "\nGuide (type in \"Info\" to show this again)"
				+ "\n\n1. Make sure to always check your menu by typing \"Menu\". The menu will give you hints"
				+ "\n   on what to do next as the options change based on every action and change in position."
				+ "\n\n2. You must take an item out of your backpack to use it. The backpack"
				+ "\n   helps you hold onto objects and move between rooms."
				+ "\n\n3. Beware of motorcycle gang members and walkers they are extremely dangerous. You can "
				+ "\n   hide from gang members but you cannot outrun them."
				+ "\n\n4. Your backpack can only carry a maximum of 5 objects."
				+ "\n\n5. Your objective is to find a car, car keys, and a gps so you can get to your family without dying."
				+ "\n\n6. Make sure to explore the entire room and see menu options before leaving to avoid missing important"
				+ "\n   objects."
				+ "\n\n7. Type out the options that you have in the menu to execute them. These are not case sensitive but will"
				+ "\n   not work if you enter an extra space or misspell the command."
				+ "\n\n\t\tPress enter to continue...");
		
		input.nextLine();
		
		String[] presetFunctions = { "Menu", "Info", "Look around", "put in", "take out" };

		int exit = 1;

		String[] backpack = new String[100];
		for (int i = 0; i < backpack.length; i++)
			backpack[i] = "";

		int[] backpackArrPos = { 0 };
		int[] roomObjArrPos = new int[roomMap.length]; // Makes sure that the room has a maximum of 5 objects

		for (int i = 0; i < roomMap.length; i++) {
			roomObjArrPos[i] = 0;
		}

		// Game navigation
		do {
			if (exit == 0) {
				System.out.println("\nSorry you lost! Better luck next time!");
				break;
			} else if (exit == 1) {
				exit = hospitalRoom(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 2) {
				exit = balcony(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 3) {
				exit = road(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 4) {
				exit = graveyard(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 5) {
				exit = livingRoom(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 6) {
				exit = saferoom(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 7) {
				exit = ventSystem(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 8) {
				exit = officeStudy(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 9) {
				exit = frontLawn(roomMap, presetFunctions, backpack, backpackArrPos, roomObjArrPos);
			} else if (exit == 10) {
				System.out.println("\nCongrats! You have won the game and can now return to your family!");
			}

		} while (exit != 0 && exit != 10);

	}
	/**
	 * 
	 * @param roomMap
	 * @param roomNum
	 * @param object
	 * @return whether the object is taken out or not
	 */
	public static boolean check(Room[] roomMap, int roomNum, String object) {
		boolean check = false;

		for (int i = 0; i < roomMap[roomNum - 1].roomObjects.length; i++) {
			if (roomMap[roomNum - 1].roomObjects[i].equalsIgnoreCase(object)) {
				check = true;
				break;
			} else {
				check = false;
			}
		}
		return check;
	}
	/**
	 * 
	 * @param roomMap
	 * @param roomNum
	 * @param input
	 * @return whether the user input is an event in the room
	 */
	public static boolean isEventCheck(Room[] roomMap, int roomNum, String input) {
		boolean check = false;
		// Loop to run the code through an array of the a room event and check if its
		// one of them
		for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
			if (input.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
				check = true;
				break;
			}
		}
		return check;
	}
	/**
	 * 
	 * @param roomMap
	 * @param roomNum
	 * @param input
	 * @param presetFunctions
	 * @return whether the user input is a preset function
	 */
	public static boolean isPresetCheck(Room[] roomMap, int roomNum, String input, String[] presetFunctions) {
		boolean check = false;
		// Loop to run the code through an array of the preset word functions and check
		// if its one of them
		for (int i = 0; i < presetFunctions.length; i++) {
			if (input.equalsIgnoreCase(presetFunctions[i])) {
				check = true;
				break;
			}
		}
		return check;
	}
	/**
	 * 
	 * @param roomMap
	 * @param roomNum
	 */
	public static void menu(Room[] roomMap, int roomNum) {

		System.out.print("Menu (Enter \"Menu\" to show again):" + "\n1. Info" + "\n2. Look around"
				+ "\n3. Put in (to put an item in your backpack)" + "\n4. Take out (to take out item from backpack)");

		for (int i = 0; i <= roomMap[roomNum - 1].event.length - 1; i++) {
			System.out.print("\n" + (i + 5) + ". " + roomMap[roomNum - 1].event[i]);
			if (i <= roomMap[roomNum - 1].event.length - 2 && roomMap[roomNum - 1].event[i + 1].equals("")) {
				break;
			}
		}
		System.out.println();
	}
	/**
	 * 
	 * @param room
	 * @param roomMap
	 * @param userInput
	 * @param backpackArrPos
	 * @param backpack
	 * @param roomObjArrPos
	 * @param inputs
	 */
	public static void defaultMenuTasks(int room, Room[] roomMap, String userInput, int[] backpackArrPos,
			String[] backpack, int[] roomObjArrPos, String[] inputs) {

		if (userInput.equalsIgnoreCase(inputs[0])) {

			menu(roomMap, room);

		} else if (userInput.equalsIgnoreCase(inputs[1])) {

			System.out.println("\t\tWELCOME TO THE WALKING DEAD STIMULATION" + "\nGuide (type in \"Info\" to show this again)"
					+ "\n\n1. Make sure to always check your menu by typing \"Menu\". The menu will give you hints"
					+ "\n   on what to do next as the options change based on every action and change in position."
					+ "\n\n2. You must take an item out of your backpack to use it. The backpack"
					+ "\n   helps you hold onto objects and move between rooms."
					+ "\n\n3. Beware of motorcycle gang members and walkers they are extremely dangerous. You can "
					+ "\n   hide from gang members but you cannot outrun them."
					+ "\n\n4. Your backpack can only carry a maximum of 5 objects."
					+ "\n\n5. Your objective is to find a car, car keys, and a gps so you can get to your family without dying."
					+ "\n\n6. Make sure to explore the entire room and see menu options before leaving to avoid missing important"
					+ "\n   objects."
					+ "\n\n7. Type out the options that you have in the menu to execute them. These are not case sensitive but will"
					+ "\n   not work if you enter an extra space or misspell the command.");

		} else if (userInput.equalsIgnoreCase(inputs[2])) {

			look(room, roomMap);

		} else if (userInput.equalsIgnoreCase(inputs[3])) {

			backpackInput(room, roomMap, backpack, roomObjArrPos, backpackArrPos);

		} else if (userInput.equalsIgnoreCase(inputs[4])) {

			backpackOutput(room, roomMap, backpack, roomObjArrPos, backpackArrPos);

		}

	}
	/**
	 * 
	 * @param room
	 * @param roomMap
	 */
	public static void look(int room, Room[] roomMap) {
		System.out.println(
				roomMap[room - 1].roomNum + ") " + roomMap[room - 1].roomName + "\n" + roomMap[room - 1].roomDesc[0]);

	}
	/**
	 * 
	 * @param roomMap
	 * @param room
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 */
	public static void sort(Room[] roomMap, int room, String[] backpack, int[] backpackArrPos, int[] roomObjArrPos) {
		// sort room array
		boolean sorted = true;
		String obj;
		do {
			for (int j = 0; j < 4; j++) {
				if (roomMap[room - 1].roomObjects[j].length() < roomMap[room - 1].roomObjects[j + 1].length()) {
					obj = roomMap[room - 1].roomObjects[j];
					roomMap[room - 1].roomObjects[j] = roomMap[room - 1].roomObjects[j + 1];
					roomMap[room - 1].roomObjects[j + 1] = obj;
					sorted = false;
				} else {
					sorted = true;
				}
			}
		} while (!sorted);

		// Sort backpack array by String length
		sorted = true;
		do {
			for (int j = 0; j < 4; j++) {
				if (backpack[j].length() < backpack[j + 1].length()) {
					obj = backpack[j];
					backpack[j] = backpack[j + 1];
					backpack[j + 1] = obj;
					sorted = false;
				} else {
					sorted = true;
				}
			}
		} while (!sorted);

		// Calculate room objects array length
		for (int i = 0; i < 5; i++) {
			roomObjArrPos[room - 1] = i;
			if (roomMap[room - 1].roomObjects[i].equals("")) {
				break;
			}
		}

		// Calculate backpack objects array length
		for (int i = 0; i < 5; i++) {
			backpackArrPos[0] = i;
			if (backpack[i].equals("")) {
				break;
			}
		}

	}
	/**
	 * 
	 * @param room
	 * @param roomMap
	 * @param backpack
	 * @param roomObjArrPos
	 * @param backpackArrPos
	 */
	public static void backpackInput(int room, Room[] roomMap, String[] backpack, int[] roomObjArrPos,
			int[] backpackArrPos) {

		Scanner input = new Scanner(System.in);
		boolean foundObject = false;
		String object;
		int objNum;

		sort(roomMap, room, backpack, backpackArrPos, roomObjArrPos);

		// Counts the length of the array
		System.out.println("What would you like to put in the backpack:");
		for (int i = 0; i < roomObjArrPos[room - 1]; i++) {
			System.out.println(i + 1 + " - " + roomMap[room - 1].roomObjects[i]);

		}
		// If backpack has no items
		if (roomObjArrPos[room - 1] == 0) {
			System.out.println("(No objects nearby)");
			object = "not an object";
		} else {
			object = input.nextLine();
		}

		for (int i = 0; i <= roomObjArrPos[room - 1]; i++) {
			if (object.equalsIgnoreCase(roomMap[room - 1].roomObjects[i])) {
				foundObject = true;
				break;
			}
		}

		if (foundObject) {
			objNum = 0;
			while (objNum < 5 && !(roomMap[room - 1].roomObjects[objNum].equalsIgnoreCase(object))) {
				objNum++;
			}

			// Add object into the backpack from the room

			System.out.println("Added to backpack");
			backpack[backpackArrPos[0]] = object;
			roomMap[room - 1].roomObjects[objNum] = "";

		} else if (!(object.equals("not an object"))) {
			System.out.println("Object wasn't found");
		}
	}
	/**
	 * 
	 * @param room
	 * @param roomMap
	 * @param backpack
	 * @param roomObjArrPos
	 * @param backpackArrPos
	 */
	public static void backpackOutput(int room, Room[] roomMap, String[] backpack, int[] roomObjArrPos,
			int[] backpackArrPos) {

		Scanner input = new Scanner(System.in);
		boolean foundObject = false;
		String object;
		backpackArrPos[0] = 1;

		sort(roomMap, room, backpack, backpackArrPos, roomObjArrPos);

		// Display list of items in backpack
		System.out.println("What would you like to take out of the backpack?");
		for (int i = 0; i < backpackArrPos[0]; i++) {
			System.out.println(i + 1 + "- " + backpack[i]);
		}

		if (backpackArrPos[0] == 0) {
			System.out.println("(Your backpack is empty)");
			object = "not an object";
		} else {
			object = input.nextLine();
		}

		int objNum = 0;
		for (int i = 0; i <= backpackArrPos[0]; i++) {
			if (object.equalsIgnoreCase(backpack[i])) {
				foundObject = true;
				break;
			}
		}

		if (foundObject) {
			// Add object into the room (or user's hands) from the backpack
			objNum = 0;
			while (objNum < 5 && !(object.equalsIgnoreCase(backpack[objNum]))) {
				objNum++;
			}
			System.out.println("Took out " + object + " from bag");
			roomMap[room - 1].roomObjects[roomObjArrPos[room - 1]] = backpack[objNum];
			backpack[objNum] = "";

		} else if (!(object.equals("not an object"))) {

			System.out.println("Object was not found");
		}

	}
	/**
	 * 
	 * @param roomMap
	 */
	public static void rooms(Room[] roomMap) {
		// Hospital room
		roomMap[0] = new Room(1, "Hospital room",
				"The room you wake up in which has a bag of inventory, a sleeping nurse, and a respirator. There is a window "
						+ "\non the North end of the room connected to a balcony and a barred door on the south end\n",
				"", "", "", "", "", "", "", false, "Wake up nurse", "Climb out window", "", "", "", "", "", "", "", "",
				2, 0, 0, 0);

		// Balcony
		roomMap[1] = new Room(2, "Balcony",
				"You are on a balcony on the third story of the hospital. You can see the road "
						+ "\ngoing north through a forest in front of you when you look down. There is a "
						+ "\ndrainpipe on the east end of the balcony. All you see is greenery in front of you. "
						+ "\nThere is a pocketknife on the railing.",
				"", "", "Pocket knife", "", "", "", "", false, "Climb down drain", "Enter road", "", "", "", "", "", "",
				"", "", 3, 0, 0, 0);

		// Road (forest trail)
		roomMap[2] = new Room(3, "Road",
				"You can faintly see a graveyard about a kilometre away. You are starting to lose "
						+ "\ndaylight. And you are surrounded by the forest around you. The forest is crowded"
						+ "\nwith walkers but they have not noticed you yet.",
				"", "", "", "", "", "", "", false, "Go forward", "", "", "", "", "", "", "", "", "", 4, 0, 0, 0);

		// Graveyard
		roomMap[3] = new Room(4, "Graveyard",
				"You see tombstones all around you. Most of the tombstones have been dug up. In the "
						+ "\neast direction, you see a brick wall with no walls and a chained-up door with "
						+ "\na lock. You see one grave on the northeast corner that has not been dug up with "
						+ "\na shovel next to it.",
				"", "", "", "", "", "", "", false, "Go northeast", "Go east", "", "", "", "", "", "", "", "", 0, 3, 5,
				0);

		// Living room
		roomMap[4] = new Room(5, "Living room",
				"You are in a room with a tv set on the north end and a large bookshelf "
						+ "\non the east end. The north wall has a window showing "
						+ "\na garden and a road on the other end going from east to west. There is a "
						+ "\nvent on top of the window. On the south end, there is an unconcious old man "
						+ "\nlying on the floor",
				"", "", "", "", "", "", "", false, // needs more object parameters
				"Go west", "Go north", "Go east", "Go south", "", "", "", "", "", "", 7, 0, 6, 4);

		// Saferoom
		roomMap[5] = new Room(6, "Saferoom",
				"You are in a dimly lit room where there is no light switch. The only door is located on the "
						+ "\nwest side of the room and it automatically closes. On the north end, there is a vent and on the "
						+ "\nsouth wall, there is one white cabinet. " + "\nThe rest of the room is completely empty.",
				"", "", "", "", "", "", "", false, "Go north", "Go west", "Go south", "Go east", "", "", "", "", "", "",
				7, 0, 0, 5);

		// Vent System
		roomMap[6] = new Room(7, "Vent System",
				"You are in the vent system barely wide enough for you and your inventory bag. You are surrounded "
						+ "\nby metal but can see light coming in from every room in the house. You can only see the room"
						+ "\nwhen you travel to whichever vent entrance.",
				"", "", "", "", "", "", "", false, "Go northeast", "Go south", "Go southwest", "", "", "", "", "", "",
				"", 0, 6, 0, 0, 8, 0, 0, 5);

		// Office study
		roomMap[7] = new Room(8, "Office study",
				"There is a door with the word ìDANGERî spray-painted in red on the east end of the room. "
						+ "\nThe south end of the room has a vent and a landscape painting on the wall."
						+ "\nThere is a basic desk with a cupboard on the west wall. ",
				"", "", "", "", "", "", "", false, "Go north", "Go south", "Go east", "Go west", "", "", "", "", "", "",
				0, 7, 9, 0);

		// Front lawn
		roomMap[8] = new Room(9, "Front lawn",
				"On the far end of the front lawn, there is a white car parked beside the curb. "
						+ "\nThere is a road going west on the north side of the lawn. "
						+ "\nThere is a pile of mauled corpses on the east end floor which do not look like the "
						+ "\nwork of walkers but a wild dangerous animal. Beside the car, you can see a wild creature"
						+ "\nwho seems to be guarding the car."
						+ "\n\nIts a sphinx, an ancient mythological creature with a womans head and lions body. If "
						+ "\nyou were to approach it, she would ask you a riddle. If you fail, you will be mauled to death."
						+ "\nIf you succeed, you will be granted the car and left alone.",
				"", "", "", "", "", "", "", false, "Go north", "Go west", "Go east", "Go south", "", "", "", "", "", "",
				0, 6, 0, 0);

	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from hospital room
	 */
	public static int hospitalRoom(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit = 0;
		boolean isPresetFunction;
		boolean isEvent;

		String userInput;

		// Hospital room
		look(1, roomMap);
		menu(roomMap, 1);

		do {
			isPresetFunction = false; // boolean turns true when user input is a preset function
			isEvent = false; // boolean turns true when user input is a room event
			userInput = input.nextLine(); // Records user input as string
			int roomNum = 1;

			// Loop to run the code through an array of the preset word functions and check
			// if its one of them
			for (int i = 0; i < presetFunctions.length; i++) {
				if (userInput.equalsIgnoreCase(presetFunctions[i])) {
					isPresetFunction = true;
					break;
				}
			}

			// Loop to run the code through an array of the a room event and check if its
			// one of them
			for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
					isEvent = true;
					break;
				}
			}

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {
				// If the input is an event from the hospital room
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) {
					System.out.println(
							"The nurse wakes up:" + "\n\"Welcome to the new world Rick. We call it the walking dead."
									+ "\nI was assigned to keep you safe during your coma. Your name is"
									+ "\nRick Grimes, a former cop. You have a wife named Lori Grimes and"
									+ "\na son named Carl Grimes. "
									+ "\n\nIf you are not aware, the world has been taken over by a disease that"
									+ "\ncauses people to wake up as zombies after they die. We call them walkers."
									+ "\nThey are extremely dangerous and will eat you alive on sight. Please be"
									+ "\ncareful. You need to find your family Rick. "
									+ "\n\nTo help you, I will give you a virtual assistent. It will give"
									+ "\nyou your options in every situation you are in so make sure to call it"
									+ "\nwith \"Menu\". It is extremely important to keep checking your menu as it"
									+ "\ncan save your life and help you if you are not sure what to do next."
									+ "\n\nPick up whatever that seems helpful even if its not exactly helpful"
									+ "\nat the moment. Please be aware for motorcycle gang members because they"
									+ "\nwill loot you and leave you to the walkers. "
									+ "\n\nI wish you the best of Luck!\"" + "\n");
				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) {
					exit = 2;
					break;
				}

			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from balcony
	 */
	public static int balcony(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit = 0;
		boolean isPresetFunction;
		boolean isEvent;
		boolean downDrainpipe = false;

		String userInput;

		// Balcony
		look(2, roomMap);

		do {
			isPresetFunction = false; // boolean turns true when user input is a preset function
			isEvent = false; // boolean turns true when user input is a room event
			userInput = input.nextLine(); // Records user input as string
			int roomNum = 2;

			// Loop to run the code through an array of the preset word functions and check
			// if its one of them
			for (int i = 0; i < presetFunctions.length; i++) {
				if (userInput.equalsIgnoreCase(presetFunctions[i])) {
					isPresetFunction = true;
					break;
				}
			}

			// Loop to run the code through an array of the a room event and check if its
			// one of them
			for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
					isEvent = true;
					break;
				}
			}

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) { // If the input is an event from the balcony
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0]) && !downDrainpipe) {
					// If user decides to climb down the drain
					downDrainpipe = true;

					// Removes ability to climb down drain from menu
					roomMap[roomNum - 1].event[0] = "Climb down drain (unavailable)";

					// Creates new description for when user looks around
					System.out.println("Climbed down drain:\n");
					roomMap[roomNum
							- 1].roomDesc[0] = "There is a forest all around you and a fence preventing you from entering it. In front "
									+ "\nof you, there is an opening that connects to a road which goes through the forest";

					System.out.println(roomMap[roomNum - 1].roomDesc[0]);

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1]) && downDrainpipe) {
					exit = 3;
					break;
				} else if (!downDrainpipe) {
					System.out.println("You're on the third floor...");
				}

			} else { // If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from road
	 */
	public static int road(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit = 0;
		boolean isPresetFunction;
		boolean isEvent;
		boolean downDrainpipe = false;
		int roomNum = 3;

		String userInput;

		// Balcony
		look(3, roomMap);

		int forwardInt = 0;
		boolean escapeGang = false;
		boolean hasPocketKnife = false;

		do {
			isPresetFunction = false; // boolean turns true when user input is a preset function
			isEvent = false; // boolean turns true when user input is a room event
			userInput = input.nextLine(); // Records user input as string

			// Checks if the user has a pocket knife
			for (int i = 0; i < roomMap[roomNum - 1].roomObjects.length; i++) {
				if (roomMap[roomNum - 1].roomObjects[i].equalsIgnoreCase("Pocket Knife")) {
					hasPocketKnife = true;
					break;
				} else {
					hasPocketKnife = false;
				}
			}

			// Loop to run the code through an array of the preset word functions and check
			// if its one of them
			for (int i = 0; i < presetFunctions.length; i++) {
				if (userInput.equalsIgnoreCase(presetFunctions[i])) {
					isPresetFunction = true;
					break;
				}
			}

			// Loop to run the code through an array of the a room event and check if its
			// one of them
			for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
					isEvent = true;
					break;
				}
			}

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {
				// If the input is an event from the hospital room
				// Go forward
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) {
					forwardInt++;

					if (forwardInt == 1) {
						roomMap[roomNum - 1].roomDesc[0] = "MIDDLE OF ROAD\n"
								+ "You are walking and the graveyard is only 500m away.\n"
								+ "There is a big tree with shallow branches on your right.\n"
								+ "Then, from a distance behind you, you hear a motorcycle racing towards you.";

						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						roomMap[roomNum - 1].event[1] = "Climb big tree";

						// If user decides to move forward without escaping bike.
					} else if (forwardInt == 2 && !escapeGang) {

						System.out.println("YOU DIED"
								+ "\nYou could not outrun the biker gang member and he looted you and left you to the walkers.");
						exit = 0;
						break;
						// If user goes forward after escaping gang
					} else if (forwardInt == 2 && escapeGang) {
						exit = 4;
						break;
					}
				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) {
					if (forwardInt == 1) {
						roomMap[roomNum - 1].roomDesc[0] = "ON TOP OF TREE\n"
								+ "You look down as the gang member comes out of his motorcycle and walks\n"
								+ "toward the right side of the road. On your left there is greenery all around.\n"
								+ "On your right there is a killer hornets nest right underneath the gang member\n"
								+ "on a thin branch next to you\n"
								+ "The killer hornets are getting angry with all the noise, you must act fast.";

						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						roomMap[roomNum - 1].event[1] = "Climb big tree (unavailable)";
						roomMap[roomNum - 1].event[2] = "Cut the thin branch";
					}

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) {

					// If user does, nest drops and game continues
					if (hasPocketKnife) {
						System.out.println("You're saved!\n"
								+ "The nest drops onto the gang member who screams, gets onto his motorcycle and"
								+ "\ndrives off to the hospital."
								+ "\nYou come down from the tree and now you're right where you left off having"
								+ "\nescaped the gang member");
						escapeGang = true;

						roomMap[roomNum - 1].event[1] = "";
						roomMap[roomNum - 1].event[2] = "";

						// If not, user cannot cut branch.
					} else {
						System.out.println("Cut it with what? You're not holding anything sharp!");
					}
				}

			} else { // If the input is neither a preset function or room event
				System.out.println("Sorry, you cannot.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from graveyard
	 */
	public static int graveyard(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit = 0;
		boolean isPresetFunction;
		boolean isEvent;
		boolean downDrainpipe = false;
		int roomNum = 3;

		String userInput;

		// Graveyard
		look(4, roomMap);
		boolean walkerInvade = false;
		boolean hasCutters = false;

		do {
			isPresetFunction = false; // boolean turns true when user input is a preset function
			isEvent = false; // boolean turns true when user input is a room event
			userInput = input.nextLine(); // Records user input as string
			roomNum = 4;

			// Checks if the user has a pair of chain cutters
			for (int i = 0; i < roomMap[roomNum - 1].roomObjects.length; i++) {
				if (roomMap[roomNum - 1].roomObjects[i].equalsIgnoreCase("Chain cutters")) {
					hasCutters = true;
					break;
				} else {
					hasCutters = false;
				}
			}

			// Loop to run the code through an array of the preset word functions and check
			// if its one of them
			for (int i = 0; i < presetFunctions.length; i++) {
				if (userInput.equalsIgnoreCase(presetFunctions[i])) {
					isPresetFunction = true;
					break;
				}
			}

			// Loop to run the code through an array of the a room event and check if its
			// one of them
			for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
					isEvent = true;
					break;
				}
			}

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {
				// If the input is an event from the hospital room
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0]) && !walkerInvade) {

					// Change in users view
					roomMap[roomNum - 1].roomDesc[0] = "UNDUG GRAVE\n"
							+ "In front of you is the undug grave which says:"
							+ "\n\n\t\tIN LOVING MEMORY OF RICK GRIMES"
							+ "\n\nThere is a shovel next to your own grave and all around you are dug up graves.\n"
							+ "The door on the east end is chained up.";

					System.out.println(roomMap[roomNum - 1].roomDesc[0]);

					// Change in events
					roomMap[roomNum - 1].event[2] = "Pick up shovel";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0]) && walkerInvade) {

					System.out.println("YOU DIED\nThe walkers caught you.");
					exit = 0;
					break;

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) {

					System.out.println("EAST DOOR" + "\nThe door handle is chained up.");
					if (walkerInvade) {
						System.out.println("Hurry up, the walkers are almost onto you");
					}
					// Change in events
					roomMap[roomNum - 1].event[2] = "Cut the chains";
					roomMap[roomNum - 1].event[3] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) {

					if (roomMap[roomNum - 1].event[2].equalsIgnoreCase("pick up shovel")) {
						System.out.println("Picked up shovel.");

						// Change in events
						roomMap[roomNum - 1].event[2] = "Pick up shovel (already picked up)";
						roomMap[roomNum - 1].event[3] = "Dig up grave";

					} else if (roomMap[roomNum - 1].event[2].equalsIgnoreCase("cut the chains")) {
						if (hasCutters) {
							// User cuts the chains and enters next room
							System.out.println("You cut the chains and open the door."
									+ "\nThe walkers are almost at the door too." + "\nYou enter into the room\n"
									+ "Press enter to continue...");

							input.nextLine();
							exit = 5;
							break;
						} else {
							System.out.println("With what? Your arms are not strong enough!");
						}

					}

					// Dig up grave
				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) {

					System.out.println("You dig up the grave and open the coffin."
							+ "\nInside, there is a DVD and pair of chain cutters."
							+ "You start to hear low growls coming from the south end" + "\nof the graveyard");

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "COFFIN\n"
							+ "The walkers are coming in from the gate! You must act fast before you get eaten alilve!";
					walkerInvade = true;
					// Change in events
					roomMap[roomNum - 1].event[2] = "";
					roomMap[roomNum - 1].event[3] = "";

					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "DVD";
					roomMap[roomNum - 1].roomObjects[1] = "Chain cutters";

				}

			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? I didn't get that");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from the living room
	 */
	public static int livingRoom(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit;
		boolean isPresetFunction;
		boolean isEvent;
		boolean hasDisc;
		boolean hasInsulin;

		int roomNum = 5;

		String userInput;

		// Living room
		look(5, roomMap);

		do {
			exit = 0;
			isPresetFunction = false; // boolean turns true when user input is a preset function
			isEvent = false; // boolean turns true when user input is a room event
			userInput = input.nextLine(); // Records user input as string

			// Loop to run the code through an array of the preset word functions and check
			// if its one of them
			for (int i = 0; i < presetFunctions.length; i++) {
				if (userInput.equalsIgnoreCase(presetFunctions[i])) {
					isPresetFunction = true;
					break;
				}
			}

			// Loop to run the code through an array of the a room event and check if its
			// one of them
			for (int i = 0; i < roomMap[roomNum - 1].event.length; i++) {
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[i])) {
					isEvent = true;
					break;
				}
			}

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {
				// If the input is an event from the hospital room
				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) { // West wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "WEST WALL" + "\nThe north end has a tv set"
							+ "\nThe east wall has a large bookshelf"
							+ "\nThere is an unconscious old man on the south end";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "";
					roomMap[roomNum - 1].event[5] = "";
					// Change in room objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) { // North wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "NORTH WALL"
							+ "\nThere is a turned on tv set on your left and there is a vent above the window";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Insert disc into TV";
					roomMap[roomNum - 1].event[5] = "Climb into vent";
					// Change in room objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) { // East wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "EAST WALL" + "\nThere is a large book shelf in front of you";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Move bookshelf";
					roomMap[roomNum - 1].event[5] = "";
					// Change in room objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) { // South wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "SOUTH WALL"
							+ "\nThere is an unconcious man lying on the floor in front of you";

					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Wake up old man";
					roomMap[roomNum - 1].event[5] = "";
					// Change in room objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[4])) { // Situational events

					if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Wake up old man")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "SOUTH WALL" + "\nThere is an old man in need of insulin";
						System.out.println("The old man wakes up:"
								+ "\n\"P-please help m-me. I ne-need my insulin because I'm going into diabetic shock."
								+ "\nI-I c-can get you a GPS if you help me. There should be some insulin in the sa-\""
								+ "\nWith that, the man falls unconcious.");
						// Change in events
						roomMap[roomNum - 1].event[4] = "Inject insulin into old man";
						roomMap[roomNum - 1].event[5] = "";
						// Change in room objects
						roomMap[roomNum - 1].roomObjects[0] = "";

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Insert disc into TV")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "NORTH WALL"
								+ "\nThere is a turned on tv set on your left and there is a vent above the window";

						hasDisc = check(roomMap, roomNum, "DVD");

						if (hasDisc) {
							System.out.println("You play the disc:"
									+ "\nYour wife, Lori Grimes is sitting on a sofa through the screen. Carl is"
									+ "\nplaying with a bunch of kids in the back. Then Lori speaks:"
									+ "\n\n\"Rick, if you woke up from the coma, I want you to know that Carl and I"
									+ "\nare still safe. Right now, it is December 23, 2019. We're staying at a "
									+ "\nsafehouse on 200 Elm Street in Barrie. You're going to need to find a car and"
									+ "\na GPS. Please come find us Rick if you're watching this.\""
									+ "\n\n-TAPE ENDS-");
							// Change in events
							roomMap[roomNum - 1].event[4] = "Insert disc into TV";
							roomMap[roomNum - 1].event[5] = "Climb into vent";
							// Change in room objects
							roomMap[roomNum - 1].roomObjects[0] = "";
						} else {
							System.out.println("There is no disc in your hands");
						}

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Move bookshelf")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "EAST WALL" + "\nThere is a door in front of you";
						System.out.println(roomMap[roomNum - 1].roomDesc[0]);

						// Change in events
						roomMap[roomNum - 1].event[4] = "Open door";
						roomMap[roomNum - 1].event[5] = "";
						// Change in room objects
						roomMap[roomNum - 1].roomObjects[0] = "";

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Inject insulin into old man")) {
						hasInsulin = check(roomMap, roomNum, "insulin shot");

						if (hasInsulin) {
							// Change in description
							roomMap[roomNum - 1].roomDesc[0] = "SOUTH WALL"
									+ "\nThe old man wakes up and holds out a GPS";
							System.out.println("It worked!"
									+ "\nThe old man wakes up from diabetic shock, thanks you, and pulls out a GPS from"
									+ "\nhis pocket and holds it out for you. ");
							// Change in events
							roomMap[roomNum - 1].event[4] = "";
							roomMap[roomNum - 1].event[5] = "";
							// Change in room objects
							roomMap[roomNum - 1].roomObjects[0] = "GPS";
						} else {
							System.out.println("You don't have insulin on you.");
						}

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open door")) {
						System.out.println("Inside you see the saferoom." + "\nPress enter to continue...");
						input.nextLine();
						exit = 6;
						break;

					}

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[5])) { // Situational events
					exit = 7;
					break;
				}

			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from the saferoom
	 */
	public static int saferoom(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit;
		boolean isPresetFunction;
		boolean isEvent;

		int roomNum = 6;

		String userInput;

		// Saferoom
		look(6, roomMap);
		menu(roomMap, 6);

		do {
			exit = 0;
			userInput = input.nextLine(); // Records user input as string

			isPresetFunction = isPresetCheck(roomMap, roomNum, userInput, presetFunctions); // boolean turns true when
																							// user input is a preset
																							// function
			isEvent = isEventCheck(roomMap, roomNum, userInput); // boolean turns true when user input is a room event

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {

				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) { // North wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "NORTH WALL" + "\nThere is a vent on the wall";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Climb into vent";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) { // West wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "WEST WALL"
							+ "\nThe west door to the living room has been automatically closed and locked for"
							+ "\nsafety reasons.";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open door";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) { // South wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "SOUTH WALL" + "\nThere is a white cabinet next to the wall.";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open cabinet";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) { // East wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "EAST WALL" + "\nNothing over here but a blank wall";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[4])) { // Situational events

					if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Climb into vent")) {

						exit = 7;
						break;

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open door")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "WEST WALL"
								+ "\nThe west door to the living room has been automatically closed and locked for"
								+ "\nsafety reasons.";
						System.out.println("You do not have a key or facial recognition to open the door.");
						// Change in events
						roomMap[roomNum - 1].event[4] = "";
						roomMap[roomNum - 1].event[5] = "";
						// Change in objects
						roomMap[roomNum - 1].roomObjects[0] = "";

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open cabinet")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "CABINET"
								+ "\nInside, there is an opened box of insulin shots with only one remaining.";
						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						// Change in events
						roomMap[roomNum - 1].event[4] = "";
						roomMap[roomNum - 1].event[5] = "";
						// Change in objects
						roomMap[roomNum - 1].roomObjects[0] = "Insulin shot";

					}
				}
			} else {
				// If the input is neither a preset function or room event
				System.out.println("That made no sense. Use the menu to figure out your options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from the vent system
	 */
	public static int ventSystem(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit;
		boolean isPresetFunction;
		boolean isEvent;

		int roomNum = 7;

		String userInput;

		// Vent System
		look(7, roomMap);

		do {
			exit = 0;
			userInput = input.nextLine(); // Records user input as string

			isPresetFunction = isPresetCheck(roomMap, roomNum, userInput, presetFunctions); // boolean turns true when
																							// user input is a preset
																							// function
			isEvent = isEventCheck(roomMap, roomNum, userInput); // boolean turns true when user input is a room event

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {

				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) { // Office study

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "OFFICE STUDY VENT"
							+ "\nYou are in a vent where the office study is on your left";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[3] = "Enter office study";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) { // Saferoom

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "SAFEROOM VENT"
							+ "\nYou are in a vent where the saferoom is on your right";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[3] = "Enter saferoom";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) { // Living room

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "LIVING ROOM VENT"
							+ "\nYou are in a vent where the living is on your right";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[3] = "Enter living room";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) { // Situational events

					if (roomMap[roomNum - 1].event[3].equalsIgnoreCase("Enter office study")) {

						exit = 8;
						break;

					} else if (roomMap[roomNum - 1].event[3].equalsIgnoreCase("Enter saferoom")) {

						exit = 6;
						break;

					} else if (roomMap[roomNum - 1].event[3].equalsIgnoreCase("Enter living room")) {

						exit = 5;
						break;

					}
				}
			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from the office study
	 */
	public static int officeStudy(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int passcode;
		int exit;
		boolean isPresetFunction;
		boolean isEvent;

		int roomNum = 8;

		String userInput;

		// Office study
		look(8, roomMap);

		do {
			exit = 0;
			userInput = input.nextLine(); // Records user input as string

			isPresetFunction = isPresetCheck(roomMap, roomNum, userInput, presetFunctions); // boolean turns true when
																							// user input is a preset
																							// function
			isEvent = isEventCheck(roomMap, roomNum, userInput); // boolean turns true when user input is a room event

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {

				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) { // North wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "NORTH WALL"
							+ "\nThere is a window that shows the outside road going from west to east";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "";
					roomMap[roomNum - 1].event[5] = "";

					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) { // South wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "SOUTH WALL"
							+ "\nThere is a vent and framed painting on the wall";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Climb into vent";
					roomMap[roomNum - 1].event[5] = "Take down painting";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) { // East wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "EAST WALL"
							+ "\nThere is a door that has been \"DANGER\" spray painted in red. From the window"
							+ "\nbeside the door, you can see the front lawn with a car parked next to the curb.";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open door";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) { // West wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "WEST WALL" + "\nThere is a basic desk with a cupboard";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open desk cupboard";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[4])) { // Situational events

					if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Climb into vent")) {

						exit = 7;
						break;

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open door")) {

						exit = 9;
						break;

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open desk cupboard")) {

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "DESK CUPBOARD" + "\nThere is a piece of paper stating:"
								+ "\n\n\t\tEMAIL PASSWORD: coolestharrypotterfan12345"
								+ "\n\n\t\tSKYPE PASSWORD: coolestharrypotterfan12345"
								+ "\n\n\t\tSNAPCHAT PASSWORD: coolestharrypotterfan12345"
								+ "\n\n\t\tINSTAGRAM PASSWORD: coolestharrypotterfan12345" + "\n\n\t\tSAFE CODE: 7539";

						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						// Change in events
						roomMap[roomNum - 1].event[4] = "";
						roomMap[roomNum - 1].event[5] = "";
						// Change in objects
						roomMap[roomNum - 1].roomObjects[0] = "";

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open safe")) {
						System.out.println("Enter pin: ");
						passcode = input.nextInt();
						if (passcode == 7539) {
							// Change in description
							roomMap[roomNum - 1].roomDesc[0] = "OPENED SAFE"
									+ "\nThe safe holds a spare set of car keys";
							System.out.println(roomMap[roomNum - 1].roomDesc[0]);
							// Change in events
							roomMap[roomNum - 1].event[4] = "";
							roomMap[roomNum - 1].event[5] = "";
							// Change in objects
							roomMap[roomNum - 1].roomObjects[0] = "car keys";
						} else {
							System.out.println("Incorrect pin.\n");
						}

					}
				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[5])) { // Take down painting

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "CLOSED SAFE"
							+ "\nThere is a safe where the painting was hung up earlier.";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open safe";
					roomMap[roomNum - 1].event[5] = "";
					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";

				}
			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}
	/**
	 * 
	 * @param roomMap
	 * @param presetFunctions
	 * @param backpack
	 * @param backpackArrPos
	 * @param roomObjArrPos
	 * @return exit user takes from the front lawn
	 */
	public static int frontLawn(Room[] roomMap, String[] presetFunctions, String[] backpack, int[] backpackArrPos,
			int[] roomObjArrPos) {

		Scanner input = new Scanner(System.in);

		int exit;
		boolean isPresetFunction;
		boolean isEvent;
		boolean escapedSphinx = false;
		boolean hasKeys;
		boolean hasGPS;
		String riddle;

		int roomNum = 9;

		String userInput;

		// Front Lawn
		look(9, roomMap);

		do {
			exit = 0;
			userInput = input.nextLine(); // Records user input as string

			isPresetFunction = isPresetCheck(roomMap, roomNum, userInput, presetFunctions); // boolean turns true when
																							// user input is a preset
																							// function
			isEvent = isEventCheck(roomMap, roomNum, userInput); // boolean turns true when user input is a room event

			if (isPresetFunction) {

				defaultMenuTasks(roomNum, roomMap, userInput, backpackArrPos, backpack, roomObjArrPos, presetFunctions);

			} else if (isEvent) {

				if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[0])) { // North wall
					if (!escapedSphinx) {
						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "CURB"
								+ "\nThe sphinx watches you carefully as you stand in front of her. She is"
								+ "\nthe only thing in between you and the car.";
						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						// Change in events
						roomMap[roomNum - 1].event[4] = "Talk to sphinx";
						roomMap[roomNum - 1].event[5] = "";

						// Change in objects
						roomMap[roomNum - 1].roomObjects[0] = "";
						roomMap[roomNum - 1].roomObjects[1] = "";

					} else {
						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "CAR" + "\nThe car is parked beside the curb.";
						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						// Change in events
						roomMap[roomNum - 1].event[4] = "Enter car";
						roomMap[roomNum - 1].event[5] = "";

						// Change in objects
						roomMap[roomNum - 1].roomObjects[0] = "";
						roomMap[roomNum - 1].roomObjects[1] = "";
					}

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[1])) { // West wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "WEST WALL"
							+ "\nThere is the door you came from to get into the front lawn from the office study.";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "Open door";
					roomMap[roomNum - 1].event[5] = "";

					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";
					roomMap[roomNum - 1].roomObjects[1] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[2])) { // East wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "DEAD BODY PILE"
							+ "\nA pile of non-walker mauled corpses lay on the floor. Yikes!";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "";
					roomMap[roomNum - 1].event[5] = "";

					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";
					roomMap[roomNum - 1].roomObjects[1] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[3])) { // South wall

					// Change in description
					roomMap[roomNum - 1].roomDesc[0] = "GARDEN"
							+ "\nThere is a table and umbrella with a book:\n\tHARRY POTTER AND THE DEATHLY HALLOWS";
					System.out.println(roomMap[roomNum - 1].roomDesc[0]);
					// Change in events
					roomMap[roomNum - 1].event[4] = "";
					roomMap[roomNum - 1].event[5] = "";

					// Change in objects
					roomMap[roomNum - 1].roomObjects[0] = "";
					roomMap[roomNum - 1].roomObjects[1] = "";

				} else if (userInput.equalsIgnoreCase(roomMap[roomNum - 1].event[4])) { // Situational events

					if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Talk to sphinx")) {

						System.out.println("The sphinx says:"
								+ "\n\"YOU MUST ANSWER CORRECTLY OR I WILL RIP U INTO SHREDS. The question is:"
								+ "\nWhat is my favourite book series?\"");

						riddle = input.nextLine();
						if (riddle.equalsIgnoreCase("Harry Potter")) {
							System.out
									.println("GOOD JOB!" + "\nThe sphinx smiles and walks away. The car is all yours!");

							escapedSphinx = true;
							// Change in description
							roomMap[roomNum - 1].roomDesc[0] = "CAR" + "\nThe car is parked beside the curb.";
							System.out.println("\nThe car is parked beside the curb.");
							// Change in events
							roomMap[roomNum - 1].event[4] = "Enter car";
							roomMap[roomNum - 1].event[5] = "";

							// Change in objects
							roomMap[roomNum - 1].roomObjects[0] = "";
							roomMap[roomNum - 1].roomObjects[1] = "";

						} else {
							System.out
									.println("YOU DIED!" + "\nThe sphinx pounces onto you and everything goes black.");
							exit = 0;
							break;
						}

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Open door")) {

						exit = 8;
						break;

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Enter car")) { // Enter car

						// Change in description
						roomMap[roomNum - 1].roomDesc[0] = "INSIDE CAR"
								+ "\nThe car is turned off and you see the road ahead of you";

						System.out.println(roomMap[roomNum - 1].roomDesc[0]);
						// Change in events
						roomMap[roomNum - 1].event[4] = "Start car";
						roomMap[roomNum - 1].event[5] = "";

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Start car")) { // Start car
						hasKeys = check(roomMap, roomNum, "car keys");

						if (hasKeys) {
							// Change in description
							roomMap[roomNum - 1].roomDesc[0] = "STARTED CAR"
									+ "\nWith a gps, you can find and drive to the safehouse that your family is "
									+ "\nstaying at.";
							System.out.println(roomMap[roomNum - 1].roomDesc[0]);
							// Change in events
							roomMap[roomNum - 1].event[4] = "Drive to safehouse";
							roomMap[roomNum - 1].event[5] = "";

						} else {
							System.out.println("How? You need the car keys");
						}

					} else if (roomMap[roomNum - 1].event[4].equalsIgnoreCase("Drive to safehouse")) { // Drive to
																										// safehouse
						hasGPS = check(roomMap, roomNum, "GPS");

						if (hasGPS) {
							// Wins game
							exit = 10;
							break;

						} else {
							System.out.println("You need a GPS");
						}

					}
				}
			} else {
				// If the input is neither a preset function or room event
				System.out.println("Pardon? Please type one of the menu options.");

			}

		} while (0 == 0);

		return (exit);
	}

}