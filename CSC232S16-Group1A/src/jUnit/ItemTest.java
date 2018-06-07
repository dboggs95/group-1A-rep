package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import csc232.Item;

public class ItemTest 
{

	@Test
	public void testItem1() 
	{
		Item test = new Item();
		assertTrue(test.getName().equals("item"));
		assertTrue(test.getType().equals("generic"));
		assertTrue(test.getDesc().equals("Those idiot developers let a generic item into the game!"));
	}

	@Test
	public void testItem2() 
	{
		Item test = new Item("name", "type", "description");
		assertTrue(test.getName().equals("name"));
		assertTrue(test.getType().equals("type"));
		assertTrue(test.getDesc().equals("description"));
	}

	@Test
	public void testGetName() 
	{
		Item test = new Item("name", "type", "description");
		assertTrue(test.getName().equals("name"));
	}

	@Test
	public void testGetType() 
	{
		Item test = new Item("name", "type", "description");
		assertTrue(test.getType().equals("type"));
	}

	@Test
	public void testGetDesc() 
	{
		Item test = new Item("name", "type", "description");
		assertTrue(test.getDesc().equals("description"));
	}

	@Test
	public void testSetName() 
	{
		Item test = new Item();
		test.setName("name");
		assertTrue(test.getName().equals("name"));
	}

	@Test
	public void testSetType() 
	{
		Item test = new Item();
		test.setType("type");
		assertTrue(test.getType().equals("type"));
	}

	@Test
	public void testSetDesc() 
	{
		Item test = new Item();
		test.setDesc("description");
		assertTrue(test.getDesc().equals("description"));
	}

}
