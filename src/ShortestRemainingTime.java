import java.util.Comparator;
import java.util.PriorityQueue;
class SortByShortestRemainingTime implements Comparator<Process> { 
	public int compare(Process a, Process b) 
	{ 
		return a.serviceTime - b.serviceTime; 
	} 
} 

public class ShortestRemainingTime extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {

		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		//scheduler
		PriorityQueue<Process> queue = new PriorityQueue<Process>(processen.size(), new SortByShortestRemainingTime());
		int huidigeTijd=0;
		while (!processen.isEmpty()) {
			
			if(!processen.isEmpty()&&processen.peek().arrivalTime<=huidigeTijd) {
				queue.add(processen.poll());
			}

			if(queue.size()!=0) {
				Process tijdelijk=queue.poll();
				int nextIR=tijdelijk.resterendeServiceTime;
				if(!processen.isEmpty()&&processen.peek().arrivalTime<(tijdelijk.resterendeServiceTime+huidigeTijd))nextIR=processen.peek().arrivalTime-huidigeTijd;

				if(tijdelijk.getStartTijd()!=-1)tijdelijk.setStartTijd(huidigeTijd);
				huidigeTijd+=nextIR;			
				tijdelijk.resterendeServiceTime-=nextIR;

				if(tijdelijk.resterendeServiceTime==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					scheduled.add(tijdelijk);
				}
				else queue.add(tijdelijk);
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
