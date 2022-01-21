package Adventure_Game;


public class Room {
	//The room number
	int roomNum;
	
	//The room name
	String roomName;
	
	//The room description
	String[] roomDesc = new String[4];
	
	//The objects in the room
	String[] roomObjects = new String[5];
	
	//Boolean that lets you know if room was visited already
	boolean visited;
	
	//The events in the room
	String[] event = new String[10];
	
	//Room number of the room exit leads to
	int northExit;
	int southExit;
	int eastExit;
	int westExit;
	
	int northEastExit;
	int northWestExit;
	int southEastExit;
	int southWestExit;
	
	//Constructor
	public Room() {
		roomNum = 0;
		roomName = "";
		for(int i = 0; i < 3; i++)
			roomDesc[i] = "";
		for(int i = 0; i < 5; i++)		
			roomObjects[i] = "";
		visited = false;
		for(int i = 0; i < 10; i++)	
			event[i] = "";
		northExit = -1;
		southExit = -1;
		eastExit = -1;
		westExit = -1;
	}
	
	//Constructor overload
	public Room(int num, String n, 
			String des1, String des2, String des3, 
			String rObj1, String rObj2, String rObj3, String rObj4, String rObj5, boolean v,
			String e1, String e2, String e3, String e4, String e5, String e6, String e7, 
			String e8, String e9, String e10, int ne, int se, int ee, int we) {
		
		roomNum = num;
		roomName = n;
		
		roomDesc[0] = des1;
		roomDesc[1] = des2;
		roomDesc[2] = des3;
		
		roomObjects[0] = rObj1;
		roomObjects[1] = rObj2;
		roomObjects[2] = rObj3;
		roomObjects[3] = rObj4;
		roomObjects[4] = rObj5;	
		
		visited = false;
		
		event[0] = e1;
		event[1] = e2;
		event[2] = e3;
		event[3] = e4;
		event[4] = e5;
		event[5] = e6;
		event[6] = e7;
		event[7] = e8;
		event[8] = e9;
		event[9] = e10;
		
		northExit = ne;
		southExit = se;
		eastExit = ee;
		westExit = we;
	}
	
	//Constructor overload for the vent
	public Room(int num, String n, 
			String des1, String des2, String des3, 
			String rObj1, String rObj2, String rObj3, String rObj4, String rObj5, boolean v,
			String e1, String e2, String e3, String e4, String e5, String e6, String e7, 
			String e8, String e9, String e10, int ne, int se, int ee, int we, 
			int nee, int nwe, int see, int swe) {
		
		roomNum = num;
		roomName = n;
		
		roomDesc[0] = des1;
		roomDesc[1] = des2;
		roomDesc[2] = des3;
		
		roomObjects[0] = rObj1;
		roomObjects[1] = rObj2;
		roomObjects[2] = rObj3;
		roomObjects[3] = rObj4;
		roomObjects[4] = rObj5;	
		
		visited = false;
		
		event[0] = e1;
		event[1] = e2;
		event[2] = e3;
		event[3] = e4;
		event[4] = e5;
		event[5] = e6;
		event[6] = e7;
		event[7] = e8;
		event[8] = e9;
		event[9] = e10;
		
		northExit = ne;
		southExit = se;
		eastExit = ee;
		westExit = we;
		
		northEastExit = nee;
		northWestExit = nwe;
		southEastExit = see;
		southWestExit = swe;
	}

}
