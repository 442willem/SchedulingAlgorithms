import java.util.PriorityQueue;

public class RoundRobin extends Scheduler{
	int timeSlice;
	
	public RoundRobin(int i) {
		timeSlice=i;
	}

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		// TODO Auto-generated method stub
		return null;
	}

}
