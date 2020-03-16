import java.util.PriorityQueue;

public class HighestResponseRatioNext extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();

		//scheduler
		int huidigeTijd=0;
		while (!processen.isEmpty()) {

			Process tijdelijk=processen.peek();
			int tijdelijkeTAT=-1;
			for(Process p: processen) {
				
				int wachten= huidigeTijd-p.getArrivalTime();
				if(wachten>0) {
					int tat=(wachten-p.getServiceTime())/p.getServiceTime();
					
					if(tijdelijkeTAT<tat) {
						tijdelijkeTAT=tat;
						tijdelijk=p;
					}
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
