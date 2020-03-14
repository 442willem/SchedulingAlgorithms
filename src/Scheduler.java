import java.util.PriorityQueue;

public abstract class Scheduler {
	double gemOmlooptijd;
	double gemNormOmlooptijd;
	double gemWachttijd;
	
	public Scheduler() {
		gemOmlooptijd=0;
		gemNormOmlooptijd=0;
		gemWachttijd=0;
	}
	
	public abstract PriorityQueue<Process> schedule(PriorityQueue<Process> processen);
}
