import java.util.Vector;
import java.lang.Math;

public class BiDiSearch
{
	private boolean repeat = true; //set to false to not avoid repeated states
	private int startx = 3;
	private int starty = 2;
	private int goalx = 17;
	private int goaly = 17;
	private int cost = 0;
	private int index_f = -1;
	private int index_b = -1;
	private int nodes = 1;
	private int nodes_mem = 1;
	private solution sol_f = new solution();
	private solution sol_b = new solution();
	private Queue Q_f = new Queue (1,startx,starty,cost);
	private Queue Q_b = new Queue (1,goalx,goaly,cost);
	//private PriorityQueue fringe = new PriorityQueue(Math.abs(goalx - startx+goaly - starty),startx,starty,0); //initialize with starting state
	private char [][] map;

	public BiDiSearch(char [][]map_m)
	{
		map = map_m;
		System.out.println("New BiDiSearch initialized");
	}	
	private int f(int Posx, int Posy, int cost)
	{
		//cost++;
		//return cost+Math.abs(goalx - Posx)+Math.abs(goaly - Posy);
		return 1;
	}
	
	private void succesor(int Posx, int Posy,boolean dir)
	{
		State s = new State();
		State s1 = new State();
		State s2 = new State();
		State s3 = new State();
		
		if(dir)
		{
			if(Posx<=18)
			{
				if((char)map[Posy][Posx+1]!='#')
				{
					s.set(1,Posx+1,Posy,cost);
					s.setp(index_f);
					Q_f.insert(s);
					nodes++;
				}
			}
			if(Posx>=1)
			{
				if((char)map[Posy][Posx-1]!='#')
				{
					s1.set(1,Posx-1,Posy,cost);
					s1.setp(index_f);
					Q_f.insert(s1);
					nodes++;
				}
			}
			if(Posy<=18)
			{
				if((char)map[Posy+1][Posx]!='#')
				{
					s2.set(1,Posx,Posy+1,cost);
					s2.setp(index_f);
					Q_f.insert(s2);
					nodes++;
				}
			}
			if(Posy>=1)
			{
				if((char)map[Posy-1][Posx]!='#')
				{
					s3.set(1,Posx,Posy-1,cost);
					s3.setp(index_f);
					Q_f.insert(s3);
					nodes++;
				}
			}
		}
		else
		{
			if(Posx<=18)
			{
				if((char)map[Posy][Posx+1]!='#')
				{
					s.set(1,Posx+1,Posy,cost);
					s.setp(index_b);
					Q_b.insert(s);
					nodes++;
				}
			}
			if(Posx>=1)
			{
				if((char)map[Posy][Posx-1]!='#')
				{
					s1.set(1,Posx-1,Posy,cost);
					s1.setp(index_b);
					Q_b.insert(s1);
					nodes++;
				}
			}
			if(Posy<=18)
			{
				if((char)map[Posy+1][Posx]!='#')
				{
					s2.set(1,Posx,Posy+1,cost);
					s2.setp(index_b);
					Q_b.insert(s2);
					nodes++;
				}
			}
			if(Posy>=1)
			{
				if((char)map[Posy-1][Posx]!='#')
				{
					s3.set(1,Posx,Posy-1,cost);
					s3.setp(index_b);
					Q_b.insert(s3);
					nodes++;
				}
			}
		}
	}
	public void driver()
	{
		State start_f = (State)Q_f.remove();
		State start_b = (State)Q_b.remove();
		nodes_mem-=2;
		State state_f = new State();
		State state_b = new State();
		index_f = sol_f.insert(start_f);
		index_b = sol_b.insert(start_b);
		nodes+=2;
		
		if(!isGoal(start_f.getx(), start_f.gety()))
		{
			System.out.println("Start is at goal");
		}
		cost+=2;
		
		succesor(start_f.getx(), start_f.gety(),true);
		succesor(start_b.getx(), start_b.gety(),false);
		nodes_mem = (int)Q_f.size() + (int)sol_f.size()+(int)Q_b.size() + (int)sol_b.size();
		while(isGoal(state_f.getx(), state_f.gety()))
		{
			if(Q_f.isEmpty()||Q_b.isEmpty())
			{
				System.out.println("No path from start to finnish");
				return;
			}
			state_f = Q_f.remove();
			state_b = Q_b.remove();
			nodes_mem-=2;
			if(repeat)
			{
				if(sol_b.duplicate(state_b.getx(),state_b.gety()))
				{
					if(sol_f.duplicate(state_f.getx(),state_f.gety()))
					{
						continue; //commented out such that repeated states are not avoided
					}
					else
					{
						index_f = sol_f.insert(state_f);
						nodes++;
						succesor(state_f.getx(),state_f.gety(),true);
						if(nodes_mem < (int)Q_f.size() + (int)sol_f.size())
						{
							nodes_mem = (int)Q_f.size() + (int)sol_f.size();
						}
						continue;
					}
				}
				else
				{
					if(sol_f.duplicate(state_f.getx(),state_f.gety()))
					{
						index_b = sol_b.insert(state_b);
						nodes++;
						succesor(state_b.getx(),state_b.gety(),false);
						if(nodes_mem < (int)Q_b.size() + (int)sol_b.size())
						{
							nodes_mem = (int)Q_b.size() + (int)sol_b.size();
						}
						continue; 
					}
					else
					{
						index_f = sol_f.insert(state_f);
						index_b = sol_b.insert(state_b);
						nodes+=2;
						succesor(state_f.getx(),state_f.gety(),true);
						succesor(state_b.getx(),state_b.gety(),false);
						if(nodes_mem < (int)Q_f.size() + (int)sol_f.size()+(int)Q_b.size() + (int)sol_b.size())
						{
							nodes_mem = (int)Q_f.size() + (int)sol_f.size()+(int)Q_b.size() + (int)sol_b.size();
						}
					}
				}
			}
			else
			{
				index_f = sol_f.insert(state_f);
				index_b = sol_b.insert(state_b);
				nodes+=2;
				succesor(state_f.getx(),state_f.gety(),true);
				succesor(state_b.getx(),state_b.gety(),false);
				if(nodes_mem < (int)Q_f.size() + (int)sol_f.size()+(int)Q_b.size() + (int)sol_b.size())
				{
					nodes_mem = (int)Q_f.size() + (int)sol_f.size()+(int)Q_b.size() + (int)sol_b.size();
				}
			}
		}
		display_f(sol_f.find(state_f.getx(), state_f.gety()));
		display_b(sol_b.find(state_f.getx(), state_f.gety()));
		System.out.println("Number of nodes created (total creation) " + nodes);
		System.out.println("Max number of nodes kept in memory at 1 time: " + nodes_mem);
	}
	
	public void display_f(int index)
	{
		Vector v = sol_f.solve(index);
		State s = new State();
		for(int i = 0; i < v.size();i++)
		{
			s = (State) v.get(i);
			write(s.getx(),s.gety(),'B');
		}
	}
	
	public void display_b(int index)
	{
		Vector v = sol_b.solve(index);
		State s = new State();
		for(int i = 0; i < v.size();i++)
		{
			s = (State) v.get(i);
			write(s.getx(),s.gety(),'B');
		}
		out();
	}	
	
	private boolean isGoal(int x, int y)
	{
		int pos;
		if(sol_b.duplicate(x,y))
		{
			System.out.println("Intersected at X: " + x + " Y: " + y);
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