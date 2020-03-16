import java.util.PriorityQueue;

public class ShortestJobFirst extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		
		//scheduler
				int huidigeTijd=0;
				while (!processen.isEmpty()) {
					
					Process tijdelijk=processen.peek();
					for(Process p: processen) {
						if(tijdelijk.getServiceTime()>p.getServiceTime()) {
							tijdelijk=p;
						}
					}
					
					processen.remove(tijdelijk);
					
					if(tijdelijk.getArrivalTime()>huidigeTijd)huidigeTijd=tijdelijk.getArrivalTime();			
					
					tijdelijk.setStartTijd(huidigeTijd);
					huidigeTijd+=tijdelijk.getServiceTime();
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					scheduled.add(tijdelijk);
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
