package csc232;

//Authors: Daryl P Boggs, Taras Tataryn, Dominick Amalraj, and Christopher Yount
//Changed: 27 April 2016

/*
 * Every place in the game will be made with this class.  
 * The class stores the place's name and description, as
 * well as a list of items located here.
 */

import java.util.ArrayList;

public class Location 
{
	private ArrayList<Item> items;
	private ArrayList<ContainerItem> holders;
	private RiddleItem riddler;
	private String shortName;
	private String desc;
	private String southward;
	private String northward;
	private String westward;
	private String eastward;
	private boolean[] isLocked;
	
	public Location()
	{
		shortName = "samHill";
		desc = "You realise it is quite toasty. 'Where in Sam Hill am I?' you wonder. Oh, actually you are in Sam Hill; you are being engulfed in eternal hellfire! The Devil just stands there, jeering at you! But perhaps he would cut a deal...";
		items = new ArrayList<Item>();
		holders = new ArrayList<ContainerItem>();
		riddler = new RiddleItem();
		southward = "samHill";
		westward = "samHill";
		northward = "samHill";
		eastward = "samHill";
		isLocked = new boolean[4];
		isLocked[0] = false;
		isLocked[1] = false;
		isLocked[2] = false;
		isLocked[3] = false;
	}
	
	public Location(String n, String d, String S, String N, String W, String E)
	{
		shortName = n.toLowerCase();
		desc = d;
		items = new ArrayList<Item>();
		holders = new ArrayList<ContainerItem>();
		riddler = null;
		southward = S;
		northward = N;
		westward = W;
		eastward = E;
		isLocked = new boolean[4];
		isLocked[0] = false;
		isLocked[1] = false;
		isLocked[2] = false;
		isLocked[3] = false;
	}
	
	public Location(String n, String d, String S, String N, String W, String E, boolean Sl, boolean Nl, boolean Wl, boolean El)
	{
		shortName = n.toLowerCase();
		desc = d;
		items = new ArrayList<Item>();
		holders = new ArrayList<ContainerItem>();
		riddler = null;
		southward = S;
		northward = N;
		westward = W;
		eastward = E;
		isLocked = new boolean[4];
		isLocked[0] = Sl;
		isLocked[1] = Nl;
		isLocked[2] = Wl;
		isLocked[3] = El;
	}
	
	public void setName(String n)
	{
		shortName = n.toLowerCase();
	}
	
	public void setDesc(String d)
	{
		desc = d;
	}
	
	public void setRiddler(RiddleItem r)
	{
		riddler = r;
	}
	
	public void setLocked(String d)
	{
		if(d.equals("south"))
		{
			isLocked[0] = !isLocked[0];
		}
		if(d.equals("north"))
		{
			isLocked[1] = !isLocked[1];
		}
		if(d.equals("west"))
		{
			isLocked[2] = !isLocked[2];
		}
		if(d.equals("east"))
		{
			isLocked[3] = !isLocked[3];
		}
	}
	
	public String getName()
	{
		return shortName;
	}
	
	public String getSouth()
	{
		return southward;
	}
	
	public String getNorth()
	{
		return northward;
	}
	
	public String getWest()
	{
		return westward;
	}
	
	public String getEast()
	{
		return eastward;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	public Item getItem(String name)
	{
		name = name.toLowerCase();
		for(Item item : items)
		{
			if(name.equals(item.getName()))
			{
				return item;
			}
		}
		return null;
	}
	
	public Item getItem(int index)
	{
		if ((index >= 0) && (index < items.size()))
			return items.get(index);
		else
			return null;
	}
	
	/*public Item getItem(String i, String h)
	{
		int hIndex = -1;
		for(int k = 0; k < holders.size(); k++)
		{
			if(holders.get(k).getName().equals(hIndex))
			{
				hIndex = k;
			}
		}
		if(hIndex == -1)
		{
			return null;
		}
		return holders.get(hIndex).getItem(i);
	}*/
	
	public int getNumItems()
	{
		return items.size();
	}
	
	public ContainerItem getHolder(String name)
	{
		name = name.toLowerCase();
		for(ContainerItem holder : holders)
		{
			if(name.equals(holder.getName()))
			{
				return holder;
			}
		}
		return null;
	}
	
	public Item getHolder(int index)
	{
		if ((index >= 0) && (index < holders.size()))
			return holders.get(index);
		else
			return null;
	}
	
	public int getNumHolders()
	{
		return holders.size();
	}
	
	public RiddleItem getRiddler()
	{
		return riddler;
	}
	
	public boolean isLocked(String d)
	{
		if(d.equals("south"))
		{
			return isLocked[0];
		}
		if(d.equals("north"))
		{
			return isLocked[1];
		}
		if(d.equals("west"))
		{
			return isLocked[2];
		}
		if(d.equals("east"))
		{
			return isLocked[3];
		}
		return false;
	}
	
	public void addItem(Item i)
	{
		items.add(i);
	}
	
	public void addHolder(ContainerItem i)
	{
		holders.add(i);
	}
	public void addRiddler(RiddleItem r)
	{
		riddler = r;
	}
	
	public void removeItem(String i)
	{
		for(int k = 0; k < items.size(); k++)
		{
			if(items.get(k).getName().equals(i))
			{
				items.remove(k);
			}
		}
	}
	
	public void removeHolder(String i)
	{
		for(int k = 0; k < holders.size(); k++)
		{
			if(holders.get(k).getName().equals(i))
			{
				holders.remove(k);
			}
		}
	}
	
	public void removePuzzle(String i)
	{
		riddler = null;
	}
}
