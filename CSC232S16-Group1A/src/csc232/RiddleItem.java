package csc232;

//Authors: Daryl P Boggs
//Changed: 27 April 2016

/*
 * This class creates items that ask riddles and keeps track of possible answers. 
 * The correct answer is the first in the array.
*/

public class RiddleItem extends Item 
{
	String[] responses;
	int correct;
	
	RiddleItem()
	{
		super.setName("devil");
		super.setType("puzzle");
		super.setDesc("The Devil: I AM THE BRINGER OF DOOM! BOW BEFORE YOUR NEW OVERLORD!");
		responses = new String[1];
		responses[0] ="iwouldliketosellmysoul";
		correct = 0;
	}
	
	RiddleItem(String n, String d, String r1)
	{
		super.setName(n.toLowerCase());
		super.setType("puzzle");
		super.setDesc(d.toLowerCase());
		responses = new String[1];
		responses[0] = r1;
		correct = 0;
	}
	
	RiddleItem(String n, String d, String r1, String r2, int c)
	{
		super.setName(n);
		super.setType("puzzle");
		super.setDesc(d);
		responses = new String[2];
		responses[0] = r1;
		responses[1] = r2;
		correct = c;
	}
	
	RiddleItem(String n, String d, String r1, String r2, String r3, int c)
	{
		super.setName(n);
		super.setType("puzzle");
		super.setDesc(d);
		responses = new String[3];
		responses[0] = r1;
		responses[1] = r2;
		responses[2] = r3;
		correct = c;
	}
	
	RiddleItem(String n, String d, String r1, String r2, String r3, String r4, int c)
	{
		super.setName(n);
		super.setType("puzzle");
		super.setDesc(d);
		responses = new String[4];
		
		responses[0] = r1;
		responses[1] = r2;
		responses[2] = r3;
		responses[3] = r4;
		correct = c;
	}
	
	public void getResponses()
	{
		/*answers = new String[3];
		
		Random randomGenerator = new Random();
		for( int i=0; i<4; i++)
		{
			int randomInt = randomGenerator.nextInt(4);
			answers[randomInt]=responses[i];
		}
		*/
		//TODO Prevent the correct answer from showing up first by randomizing which number goes first.
		if(responses.length == 1)
		{
			System.out.println("You think of one possible answer: " + responses[0] + ".");
		}
		else if(responses.length == 2)
		{
			System.out.println("You think of two possible answers: " + responses[0] + " and " + responses[1] + ".");
		}
		else if(responses.length == 3)
		{
			System.out.println("You think of three possible answers: " + responses[1] + ", " + responses[0] + ", and" + responses[2] + ".");
		}
		else if(responses.length == 4)
		{
			System.out.println("You think of four possible answers: " + responses[3] + ", " + responses[1] + ", " + responses[0] + ", and" + responses[3] + ".");
		}
	}
	public boolean isOption(String a)
	{
		for(int k = 0; k < responses.length; k++)
		{
			if(a.equals(responses[k].trim().toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}
	public String getCorrect()
	{
		return responses[correct];
	}
}
