package csc232;

//Authors: Daryl P Boggs, Taras Tataryn, Dominick Amalraj, and Christopher Yount
//Changed: 11 April 2016

import java.util.ArrayList;

public class ContainerItem extends Item 
{
	private ArrayList<Item> items = new ArrayList<Item>();
	private final int size;
	
	public ContainerItem()
	{
		super.setName("basketcase");
		super.setType("medium-container");
		size = 5;
	}
	
	public ContainerItem(String n, String t, int s)
	{
		super.setName(n.toLowerCase());
		super.setType(t.toLowerCase());
		size = s;
	}
	
	@Override
	public String getName()
	{
		return super.getName();
	}
	
	@Override
	public String getType()
	{
		return super.getType();
	}

	public int getSize()
	{
		return size;
	}
	
	public int getNumItems()
	{
		return items.size();
	}
	
	@Override
	public String getDesc()
	{
		String desc;
		if(items.size() != 0)
		{
			desc = "The " + super.getName() + " contains a ";
			for(int k = 0; k < items.size(); k++)
			{
				desc = desc + items.get(k).getName();
				if(items.size() == 1)
				{
					desc = desc + ".";
				}
				else if(items.size() == 2)
				{
					if(k == 0)
					{
						desc = desc + " and a ";
					}
					else
					{
						desc = desc + ".";
					}
				}
				else if(items.size() > 2)
				{
					if(k == items.size() - 1)
					{
						desc = desc + ".";
					}
					else if(k == items.size() - 2)
					{
						desc = desc + ", and a ";
					}
					else
					{
						desc = desc + ", a ";
					}
				}
			}
		}
		else
		{
			desc = "This container contains nothing.";
		}
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
	
	public void addItem(Item i)
	{
		if(items.size() == size)
		{
			return;
		}
		else
		{
			items.add(i);
		}
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
}
