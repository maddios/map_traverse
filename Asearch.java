import java.util.Vector;
import java.lang.Math;

public class Asearch
{
	private boolean repeat = false;
	private int startx = 3;
	private int starty = 2;
	private int goalx = 17;
	private int goaly = 17;
	private int cost = 0;
	private int index = -1;
	private int nodes = 1;
	private int nodes_mem = 1;
	private solution sol = new solution();
	private PriorityQueue Q = new PriorityQueue (Math.abs(goalx - startx+goaly - starty),startx,starty,cost);
	//private PriorityQueue fringe = new PriorityQueue(Math.abs(goalx - startx+goaly - starty),startx,starty,0); //initialize with starting state
	private char [][] map;


	public Asearch(char [][] map_m)
	{
		map = map_m;
		System.out.println("New Asearch initialized");
	}	
	private int f(int Posx, int Posy)
	{
		return cost+Math.abs(goalx - Posx)+Math.abs(goaly - Posy);
	}
	
	private void succesor(int Posx, int Posy)
	{
		State s = new State();
		State s1 = new State();
		State s2 = new State();
		State s3 = new State();
		if(Posx<=18)
		{
			if(map[Posy][Posx+1]!='#')
			{
				s.set(f(Posx+1,Posy),Posx+1,Posy,cost);
				s.setp(index);
				Q.insert(s);
				nodes++;
			}
		}
		if(Posx>=1)
		{
			if(map[Posy][Posx-1]!='#')
			{
				s1.set(f(Posx-1,Posy),Posx-1,Posy,cost);
				s1.setp(index);
				Q.insert(s1);
				nodes++;
			}
		}
		if(Posy<=18)
		{
			if(map[Posy+1][Posx]!='#')
			{
				s2.set(f(Posx,Posy+1),Posx,Posy+1,cost);
				s2.setp(index);
				Q.insert(s2);
				nodes++;
			}
		}
		if(Posy>=1)
		{
			if(map[Posy-1][Posx]!='#')
			{
				s3.set(f(Posx,Posy-1),Posx,Posy-1,cost);
				s3.setp(index);
				Q.insert(s3);
				nodes++;
			}
		}
		
	}
	public void driver()
	{
		State start = (State)Q.remove();
		nodes_mem--;
		State state = new State();
		index = sol.insert(start);
		nodes++;
		if(!isGoal(start.getx(), start.gety()))
		{
			System.out.println("Start is at goal");
		}
		cost++;
		succesor(start.getx(), start.gety());
		nodes_mem = (int)Q.size() + (int)sol.size();
		while(isGoal(state.getx(), state.gety()))
		{
			if(Q.isEmpty())
			{
				System.out.println("No path from start to finnish");
				return;
			}
			state = Q.remove();
			nodes_mem--;
			if(repeat)
			{
				if(sol.duplicate(state.getx(),state.gety()))
				{
					continue;
				}
			}
			index = sol.insert(state);
			nodes++;
			cost = state.getcost();
			cost++;
			succesor(state.getx(),state.gety());
			if(nodes_mem < (int)Q.size() + (int)sol.size())
			{
				nodes_mem = (int)Q.size() + (int)sol.size();
			}
		}
		//Q.display();
		//sol.display();
		display(index);
		System.out.println("Number of nodes created (total creation) " + nodes);
		System.out.println("Max number of nodes kept in memory at 1 time: " + nodes_mem);
		//System.out.println("X: " + state.getx() + " Y: " + state.gety() + " Cost: " + state.getcost());
	}
	
	public void display(int index)
	{
		Vector v = sol.solve(index);
		State s = new State();
		for(int i = 0; i < v.size();i++)
		{
			s = (State) v.get(i);
			write(s.getx(),s.gety(),'a');
		}
		out();
		
	}
	
	private boolean isGoal(int x, int y)
	{
		if(x == goalx && y == goaly)
		{
			return false;
		}
		return true;
	}
	
	public void write(int Posx, int Posy, char Char)
	{
		if(map[Posy][Posx]!='g')
		{
			if(map[Posy][Posx]!='s')
			{
				map[Posy][Posx] = Char;
			}
		}
	}
	public void out()
	{
		System.out.println("Complete path view:");
		for(int c = 0; c<=19; c++){
			System.out.println (map[c]);
		}	
	}
}