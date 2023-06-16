import java.util.Vector;
import java.lang.Math;

public class solution
{
	private Vector solution = new Vector();
	
	public int insert(State s)
	{
		solution.add(s);
		return (solution.size()-1);
	}
	
	public int size()
	{
		return solution.size();
	}
	
	public Vector solve(int pos)
	{
		Vector solve = new Vector();
		State state = new State();
		int index = pos;
		do
		{
			state = (State)solution.remove(index);
			index = state.getp();
			solve.add(state);
			if(state.getp()==-1)
			{
				break;
			}
		}while(true);
		
		return solve;
	}
	
	public boolean duplicate (int x, int y)
	{
		State s = new State();
		for (int i=0; i<solution.size(); i++)
		{
			s = (State)solution.get(i);
			if(s.getx()==x && s.gety()==y)
			{
				return true;
			}
		}
		return false;
	}
	
	public int find (int x, int y)
	{
		State s = new State();
		for (int i=0; i<solution.size(); i++)
		{
			s = (State)solution.get(i);
			if(s.getx()==x && s.gety()==y)
			{
				return i;
			}
		}
		return -1;
	}
	
	public void display()
	{
		System.out.println("displaying solution set");
		State s = new State();
		for (int i=0; i<solution.size(); i++)
		{
			s = (State)solution.get(i);
			System.out.println("f(n): " + s.getf() + " x: " + s.getx() + " y: " + s.gety() + " c: " + s.getcost() + " prev: " + s.getp());
		}
		System.out.println("end of solution set");
	}

	
}