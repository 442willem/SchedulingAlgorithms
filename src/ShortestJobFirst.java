import java.util.Comparator;
import java.util.PriorityQueue;
class SortByShortestJob implements Comparator<Process> { 
    public int compare(Process a, Process b) 
    { 
        return a.serviceTime - b.serviceTime; 
    } 
} 

public class ShortestJobFirst extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {

		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		//scheduler
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processen.size(), new SortByShortestJob());
		int huidigeTijd=0;
		while (!processen.isEmpty()||!queue.isEmpty()) {
			while(!processen.isEmpty()&&processen.peek().arrivalTime<=huidigeTijd) {
				queue.add(processen.poll());
			}
			
			if(!queue.isEmpty()) {
			Process tijdelijk=queue.poll();
			tijdelijk.setStartTijd(huidigeTijd);
			huidigeTijd+=tijdelijk.getServiceTime();
			tijdelijk.setEndTijd(huidigeTijd);
			tijdelijk.rekenUit();
			scheduled.add(tijdelijk);
			}
			else huidigeTijd++;
		}

		//berekening statistieken
		for(Process p:scheduled) {
			gemWachttijd+=p.getWachtTijd();
			gemOmlooptijd+=p.getOmloopTijd();
			gemNormOmlooptijd+=p.getNormOmloopTijd();
		}
		gemWachttijd/=scheduled.size();
		gemOmlooptijd/=scheduled.size();
		gemNormOmlooptijd/=scheduled.size();		
		return scheduled;
	}

}
