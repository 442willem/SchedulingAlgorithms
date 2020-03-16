import java.util.PriorityQueue;

public class ShortestJobFirst extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		//scheduler
		int huidigeTijd=0;
		while (!processen.isEmpty()) {
			
			Process tijdelijk=new Process(-1,-1,100000000);
			
			for(Process p: processen) {
				if(tijdelijk.getServiceTime()>p.getServiceTime() && p.getArrivalTime() <= huidigeTijd) {
					tijdelijk=p;
				}	
			}

			processen.remove(tijdelijk);
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
