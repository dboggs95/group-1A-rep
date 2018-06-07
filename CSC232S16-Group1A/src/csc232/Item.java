package csc232;

//Authors: Daryl P Boggs
//Changed: 11 April 2016

/*
 * I made the isQuest variable to prevent player from misusing 
 * an important item and aimlessly wandering the game, unable 
 * to complete it. I made the isActive variable and its sister 
 * variable activeState to use for something such as a lantern 
 * or flashlight, which is lit (active) or is not lit (inactive).
 * The toString method is designed to reflect whether at least
 * one of these two boolean variables are true.
*/

public class Item 
{
	private String shortName;
	private String type;
	private String desc;
	/*private boolean isQuest;  //For later use
	private boolean isActive; 
	private String activeState;*/
	
	public Item()
	{
		shortName = "item";
		type = "generic";
		desc = "How careless! The developers left a generic item here! That shouldn't have happened!";
	}
	public Item(String n, String t, String d)
	{
		shortName = n.toLowerCase();
		type = t.toLowerCase();
		desc = d;
	}
	//=====================
	public String getName()
	{
		return shortName;
	}
	public String getType()
	{
		return type;
	}
	public String getDesc()
	{
		return desc;
	}
	//============================
	public void setName(String n)
	{
		shortName = n.toLowerCase();
	}
	public void setType(String t)
	{
		type = t.toLowerCase();
	}
	public void setDesc(String d)
	{
		desc = d;
	}
	//============================
	public String toString()
	{
		String s = "Name: " + shortName + "\n" + "Type: " + type + "\n" + "Description: " + desc + "\n";
		/*if(isQuest())
		{
			s = s + "[Quest Item]" + "\n";
		}
		if(isActive())
		{
			s = s + "Status: " + activeState + "\n";
		}*/
		return s;
	}
}

