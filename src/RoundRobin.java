import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RoundRobin extends Scheduler{
	int timeSlice;
	
	public RoundRobin(int i) {
		timeSlice=i;
	}

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		PriorityQueue<Process> scheduled = new PriorityQueue<Process>();
		Queue<Process> proc =new LinkedList<>();
		
		for(Process p: processen) {
			proc.add(new Process(p));
		}
		
		int huidigeTijd=0;
		while(!processen.isEmpty()) {
			Process tijdelijk= proc.poll();
			if(tijdelijk.getArrivalTime()>huidigeTijd)huidigeTijd=tijdelijk.getArrivalTime();			
			
			tijdelijk.setStartTijd(huidigeTijd);
			int serviceTime=tijdelijk.getServiceTime();
			if(timeSlice<serviceTime) {
				tijdelijk.verminder(timeSlice);;
				huidigeTijd+=timeSlice;
				proc.add(tijdelijk);
			}
			else {
				huidigeTijd+=serviceTime;
				tijdelijk.setEndTijd(huidigeTijd);
				tijdelijk.rekenUit();
				scheduled.add(tijdelijk);
			}
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
