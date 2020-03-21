import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MultilevelFeedback extends Scheduler{

	@Override
	public PriorityQueue<Process> schedule(PriorityQueue<Process> processen) {

		Queue<Process> queExtraHigh = new LinkedList<Process>();	//que met timeslice 10
		Queue<Process> queHigh = new LinkedList<Process>();			//que met timeslice 20
		Queue<Process> queAverage = new LinkedList<Process>();		//que met timeslice 30
		Queue<Process> queLow = new LinkedList<Process>();			//que met timeslice 40
		Queue<Process> queExtraLow = new LinkedList<Process>();		//que met timeslice 50
		
		Queue<Process> tussenLijst = new LinkedList<Process>();
		
		PriorityQueue<Process> result=new PriorityQueue<Process>();

		int EH=1;
		int H=2;
		int A=4;
		int L=8;
		int EL=16;

		int huidigeTijd=1;
		while(!processen.isEmpty() || !queExtraHigh.isEmpty()|| !queHigh.isEmpty()|| !queAverage.isEmpty()|| !queLow.isEmpty()|| !queExtraLow.isEmpty()) {
			Process tijdelijk;
			
			if(!processen.isEmpty()) {
				for(Process p: processen) {
					if(p.getArrivalTime()<=huidigeTijd) {
						tussenLijst.add(p);
						queExtraHigh.add(new Process(p));
					}
				}

				for(Process p: tussenLijst) {
					processen.remove(p);
				}
				tussenLijst.clear();
			}
			//hoogste priority queue eerst leegmaken (timeslice = 1)
			if(!queExtraHigh.isEmpty()) {
				tijdelijk=queExtraHigh.poll();

				tijdelijk.setStartTijd(huidigeTijd);
				if(tijdelijk.getResterendeServiceTime()>=EH) {
					tijdelijk.verminder(EH);
					huidigeTijd+=EH;
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.verminder(EH);

				}
				if(tijdelijk.getResterendeServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getResterendeServiceTime()>EH*2) {
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

				if(tijdelijk.getResterendeServiceTime()>=H) {
					tijdelijk.verminder(H);
					huidigeTijd+=H;
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.verminder(H);

				}

				if(tijdelijk.getResterendeServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getResterendeServiceTime()>H*2) {
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

				if(tijdelijk.getResterendeServiceTime()>=A) {
					tijdelijk.verminder(A);
					huidigeTijd+=A;
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.verminder(A);

				}

				if(tijdelijk.getResterendeServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getResterendeServiceTime()>A*2) {
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

				if(tijdelijk.getResterendeServiceTime()>=L) {
					tijdelijk.verminder(L);
					huidigeTijd+=L;
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.verminder(L);

				}

				if(tijdelijk.getResterendeServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else if(tijdelijk.getResterendeServiceTime()>L*2) {
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

				if(tijdelijk.getResterendeServiceTime()>=EL) {
					tijdelijk.verminder(EL);
					huidigeTijd+=EL;
				}
				else {
					huidigeTijd+=tijdelijk.getResterendeServiceTime();
					tijdelijk.verminder(EL);

				}

				if(tijdelijk.getResterendeServiceTime()==0) {
					tijdelijk.setEndTijd(huidigeTijd);
					tijdelijk.rekenUit();
					result.add(tijdelijk);
				}
				else {
					queExtraLow.add(tijdelijk);
				}
			}
			else huidigeTijd++;
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
