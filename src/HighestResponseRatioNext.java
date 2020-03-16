import java.util.Comparator;
import java.util.PriorityQueue;
class SortByHighestResponseRatio implements Comparator<Process> { 
    public int compare(Process a, Process b) 
    { 
        return ((HighestResponseRatioNext.huidigeTijd-b.arrivalTime + b.serviceTime)/b.serviceTime) - ((HighestResponseRatioNext.huidigeTijd-a.arrivalTime + a.serviceTime)/a.serviceTime); 
    } 
} 
public class HighestResponseRatioNext extends Scheduler{
	public static int huidigeTijd;
	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		//scheduler
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processen.size(), new SortByHighestResponseRatio());
		huidigeTijd=0;
		while (!processen.isEmpty()) {
			while(!processen.isEmpty()&&processen.peek().arrivalTime<=huidigeTijd) {
				queue.add(processen.poll());
			}
			if(queue.size()!=0) {
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
