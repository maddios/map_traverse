
public class State
{
	
	private int Posx, Posy, f, cost, prev;
	
	public void set(int f1, int x, int y, int c)
	{
		Posx = x;
		Posy = y;
		f =	f1;
		cost = c;
	}
	public State()
	{
		prev = -1;
		Posx = -1;
		Posy = -1;
		f =	500;
		cost = -1;
	}
	
	public void setp(int p)
	{
		prev = p;
	}	
	
	public int getp ()
	{
		return prev;
	}
	public int getx ()
	{
		return Posx;
	}
	public int gety ()
	{
		return Posy;
	}
	public int getf ()
	{
		return f;
	}
	public int getcost ()
	{
		return cost;
	}
	
}