import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MultilevelFeedback extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {
		
		Queue<Process> queExtraHigh = new LinkedList<Process>();	//que met timeslice 1
		Queue<Process> queHigh = new LinkedList<Process>();			//que met timeslice 2
		Queue<Process> queAverage = new LinkedList<Process>();		//que met timeslice 3
		Queue<Process> queLow = new LinkedList<Process>();			//que met timeslice 4
		Queue<Process> queExtraLow = new LinkedList<Process>();		//que met timeslice 5
		
		PriorityQueue<Process> result=new PriorityQueue<Process>();
		
		//alle processen toevoegen aan de eerste que
		for(Process p: processen) {
			queExtraHigh.add(new Process(p));
		}
		int huidigeTijd=0;
		while(!queExtraHigh.isEmpty()|| !queHigh.isEmpty()|| !queAverage.isEmpty()|| !queLow.isEmpty()|| !queExtraLow.isEmpty()) {
			Process tijdelijk=new Process();
			
			//hoogste priority queue eerst leegmaken (timeslice = 1)
			if(!queExtraHigh.isEmpty()) {
				tijdelijk=queExtraHigh.poll();
				tijdelijk.setStartTijd(huidigeTijd);
				huidigeTijd+=1;
				tijdelijk.verminder(1);
				
				if(tijdelijk.getServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getServiceTime()>2) {
					queHigh.add(tijdelijk);
				}
				else {
					queExtraHigh.add(tijdelijk);
				}
			}
			//tweede hoogste priority queue leegmaken als eerste queue leeg is (timeslice = 2)
			else if(!queHigh.isEmpty()) {
				
				tijdelijk=queHigh.poll();
				tijdelijk.setStartTijd(huidigeTijd);
				huidigeTijd+=2;
				tijdelijk.verminder(2);
				
				if(tijdelijk.getServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getServiceTime()>4) {
					queAverage.add(tijdelijk);
				}
				else {
					queHigh.add(tijdelijk);
				}
			}
			//middenste queue leegmaken als alle vorige queue's leeg zijn (timeslice = 3)
			else if(!queAverage.isEmpty()) {

				tijdelijk=queAverage.poll();
				tijdelijk.setStartTijd(huidigeTijd);

				huidigeTijd+=3;
				tijdelijk.verminder(3);

				if(tijdelijk.getServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getServiceTime()>6) {
					queLow.add(tijdelijk);
				}
				else {
					queAverage.add(tijdelijk);
				}
			}
			//voorlaatste queue's leegmaken als alle vorige queue's leeg zijn (timeslice = 4)
			else if(!queLow.isEmpty()) {

				tijdelijk=queLow.poll();
				tijdelijk.setStartTijd(huidigeTijd);

				huidigeTijd+=4;
				tijdelijk.verminder(4);

				if(tijdelijk.getServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getServiceTime()>8) {
					queExtraLow.add(tijdelijk);
				}
				else {
					queLow.add(tijdelijk);
				}
			}
			//laatste queue's leegmaken als alle vorige queue's leeg zijn (timeslice = 5)
			else if(!queExtraLow.isEmpty()) {

				tijdelijk=queExtraLow.poll();
				tijdelijk.setStartTijd(huidigeTijd);

				huidigeTijd+=5;
				tijdelijk.verminder(5);

				if(tijdelijk.getServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else {
					queExtraLow.add(tijdelijk);
				}
			}
		}
		
		for(Process p:result) {
			gemWachttijd+=p.getWachtTijd();
			gemOmlooptijd+=p.getOmloopTijd();
			gemNormOmlooptijd+=p.getNormOmloopTijd();
		}
		gemWachttijd/=result.size();
		gemOmlooptijd/=result.size();
		gemNormOmlooptijd/=result.size();	

		return result;
	}

}
