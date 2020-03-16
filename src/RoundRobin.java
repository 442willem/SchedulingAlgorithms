import java.util.LinkedList;
import java.util.PriorityQueue;

public class RoundRobin extends Scheduler{
	int timeSlice;

	public RoundRobin(int i) {
		timeSlice=i;
	}


	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		LinkedList<Process> queue =new LinkedList<>();

		int huidigeTijd=0;

		while(!queue.isEmpty()||!processen.isEmpty()) {
			while(!processen.isEmpty()&&huidigeTijd>=processen.peek().arrivalTime)queue.add(processen.poll());			

			if(queue.isEmpty()&&!processen.isEmpty())huidigeTijd++;
			else{
				Process tijdelijk= queue.removeFirst();

				if(tijdelijk.getArrivalTime()>huidigeTijd)huidigeTijd=tijdelijk.getArrivalTime();			
				if(tijdelijk.getStartTijd()==-1)tijdelijk.setStartTijd(huidigeTijd);

				if(timeSlice<tijdelijk.getResterendeServiceTime()) {
					tijdelijk.setResterendeServiceTime(tijdelijk.getResterendeServiceTime()-timeSlice);
					huidigeTijd+=timeSlice;
					queue.addLast(tijdelijk);
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					scheduled.add(tijdelijk);
				}

					
			}

		}
		//berekening statistieken
		for(Process p:scheduled) {
			if (p.getId()<10) System.out.println(p.getWachtTijd()+" "+p.getOmloopTijd()+" "+p.getNormOmloopTijd());
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
