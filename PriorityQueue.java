import java.util.Vector;

public class PriorityQueue 
{ 
	private Vector fringe = new Vector();
    private int index; 

	public boolean isEmpty()
	{
		if(fringe.size()<1)
		{
			return true;
		}
		return false;
		
	}

	public PriorityQueue(int f, int x, int y, int c)
	{
    	State s = new State();
    	s.set(f,x,y,c);
    	fringe.add(s);
	}
	
	public int size()
	{
		return fringe.size();
	}

    
    public boolean empty () { 
        return fringe.isEmpty(); 
    } 


    public void insert (State new_s) 
    { 
	   	State s = new State();
    	for(int i = 0; i<fringe.size();i++)
    	{
    		s = (State)fringe.get (i);
    		if(s.getf()>=new_s.getf())
    		{
  				fringe.add(i,new_s);
  				return;
    		}
    		
    	}
	    fringe.add(new_s);
    } 

	
	public void display()
	{
		System.out.println("displaying fringe");
		State s = new State();
		for (int i=0; i<fringe.size(); i++)
		{
			s = (State)fringe.get(i);
			System.out.println("f(n): " + s.getf() + " x: " + s.getx() + " y: " + s.gety() + " c: " + s.getcost());
		}
		System.out.println("end of fringe");
	}

    public State remove () { 
        if(fringe.isEmpty())
        {
        	return null;
        }
        
        return (State)fringe.remove(0);
        /*int maxIndex = 0; 

        // find the index of the item with the highest priority 
        for (int i=0; i<index-1; i++) { 
            if (array[i][0]<array[maxIndex][0]) { 
                maxIndex = i; 
            } 
        } 
        int [] result = new int[4];
        result[0] = array[maxIndex][0]; 
        result[1] = array[maxIndex][1]; 
        result[2] = array[maxIndex][2];
        result[3] = array[maxIndex][3];

		System.out.println(result[0] + " " + result[1] + " " + result[2] + " " + result[3]);
        // move the last item into the empty slot 
        index--; 
		array[maxIndex][0] = array[index][0];
		array[maxIndex][1] = array[index][1];
		array[maxIndex][2] = array[index][2];
		array[maxIndex][3] = array[index][3];		       
        return result; */
   } 
}