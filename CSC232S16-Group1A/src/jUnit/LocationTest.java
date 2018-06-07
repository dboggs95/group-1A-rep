package jUnit;


import static org.junit.Assert.*;

import org.junit.Test;

import csc232.Item;
import csc232.ContainerItem;
import csc232.Location;

public class LocationTest 
{

	@Test
	public void testLocation() 
	{
		Location test = new Location();
		assertTrue(test.getName().equals("Hell"));
		assertTrue(test.getDesc().equals("You expected limbo, a flat, featureless, grey plain, but instead you are being engulfed in flames."));
	}

	@Test
	public void testLocationStringString() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		assertTrue(test.getName().equals("name"));
		assertTrue(test.getDesc().equals("description"));
	}

	@Test
	public void testSetName() 
	{
		Location test = new Location();
		test.setName("name");
		assertTrue(test.getName().equals("name"));
	}

	@Test
	public void testSetDesc() 
	{
		Location test = new Location();
		test.setDesc("description");
		assertTrue(test.getDesc().equals("description"));
	}

	@Test
	public void testGetName() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		assertTrue(test.getName().equals("name"));
	}

	@Test
	public void testGetDesc() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		assertTrue(test.getDesc().equals("description"));
	}

	@Test
	public void testGetItemString() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		Item iTest = new Item("iName", "iType", "iDescription");
		test.addItem(iTest);
		assertTrue(test.getItem(iTest.getName()).equals(iTest));
		Item getTest = test.getItem(iTest.getName());
		assertTrue(getTest.equals(iTest));
	}

	@Test
	public void testGetItemInt() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		Item iTest = new Item("iName", "iType", "iDescription");
		Item iTest2 = new Item("iName2", "iType", "iDescription");
		test.addItem(iTest);
		test.addItem(iTest2);
		assertTrue(test.getItem(0).equals(iTest));
		assertTrue(test.getItem(1).equals(iTest2));
		test.removeItem(iTest.getName());
		assertTrue(test.getItem(0).equals(iTest2));
	}

	@Test
	public void testGetNumItems() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		assertTrue(test.getNumItems() == 0);
		Item iTest = new Item("iName", "iType", "iDescription");
		Item iTest2 = new Item("iName2", "iType", "iDescription");
		test.addItem(iTest);
		assertTrue(test.getNumItems() == 1);
		test.addItem(iTest2);
		assertTrue(test.getNumItems() == 2);
		test.removeItem(iTest.getName());
		assertTrue(test.getNumItems() == 1);
	}

	@Test
	public void testGetHolderString() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		ContainerItem cTest = new ContainerItem("cName", "cType", 5);
		test.addHolder(cTest);
		assertTrue(test.getHolder(cTest.getName()).equals(cTest));
		ContainerItem getTest = test.getHolder(cTest.getName());
		assertTrue(getTest.equals(cTest));
	}

	@Test
	public void testGetHolderInt() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		ContainerItem cTest = new ContainerItem("cName", "cType", 5);
		ContainerItem cTest2 = new ContainerItem("cName2", "cType", 5);
		test.addHolder(cTest);
		test.addHolder(cTest2);
		assertTrue(test.getHolder(0).equals(cTest));
		assertTrue(test.getHolder(1).equals(cTest2));
		test.removeHolder(cTest.getName());
		assertTrue(test.getHolder(0).equals(cTest2));
	}

	@Test
	public void testGetNumHolders() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		assertTrue(test.getNumHolders() == 0);
		ContainerItem cTest = new ContainerItem("cName", "cType", 5);
		ContainerItem cTest2 = new ContainerItem("cName2", "cType", 5);
		test.addHolder(cTest);
		assertTrue(test.getNumHolders() == 1);
		test.addHolder(cTest2);
		assertTrue(test.getNumHolders() == 2);
		test.removeHolder(cTest.getName());
		assertTrue(test.getNumHolders() == 1);
	}

	@Test
	public void testAddItem() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		Item iTest = new Item("iName", "iType", "iDescription");
		test.addItem(iTest);
		Item getTest = test.getItem(iTest.getName());
		assertTrue(getTest.equals(iTest));
	}

	@Test
	public void testAddHolder() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		ContainerItem cTest = new ContainerItem("cName", "cType", 5);
		test.addHolder(cTest);
		ContainerItem getTest = test.getHolder(cTest.getName());
		assertTrue(getTest.equals(cTest));
	}

	@Test
	public void testRemoveItem() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		Item iTest = new Item("iName", "iType", "iDescription");
		test.addItem(iTest);
		test.removeItem("iname");
		assertTrue(test.getItem(iTest.getName()) == null);
	}

	@Test
	public void testRemoveHolder() 
	{
		Location test = new Location("name", "description", null, null, null, null);
		ContainerItem cTest = new ContainerItem("cName", "cType", 5);
		test.addHolder(cTest);
		test.removeHolder("cname");
		assertTrue(test.getHolder(cTest.getName()) == null);
	}

}
