import java.util.PriorityQueue;

public class HighestResponseRatioNext extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();

		//scheduler
		int huidigeTijd=0;
		while (!processen.isEmpty()) {

			Process tijdelijk=new Process(-1,-1,1000000);
			int tijdelijkeTAT=-1;
			for(Process p: processen) {
				
				int wachten= huidigeTijd-p.getArrivalTime();
				if(wachten>0) {
					int tat=(wachten-p.getServiceTime())/p.getServiceTime();
					
					if(tijdelijkeTAT<tat && p.getArrivalTime()<huidigeTijd) {
						tijdelijkeTAT=tat;
						tijdelijk=p;
					}
				}
			}
			if(tijdelijk.getArrivalTime()>0) {
				processen.remove(tijdelijk);
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
