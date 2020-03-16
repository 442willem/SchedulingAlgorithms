import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args){
		int aantalProcessen=10000; //10000-20000-50000
		
		XMLParser parser=new XMLParser();
		parser.leesProcessen(aantalProcessen);
		
		System.out.println("Aantal Processen: " + aantalProcessen);
		PriorityQueue<Process> processen=parser.getProcessen();
		
		Scheduler firstComeFirstServed = new FirstComeFirstServed();
		PriorityQueue<Process> hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		firstComeFirstServed.schedule(hulpProcessen);	
		System.out.println("First Come First Served:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler shortestJobFirst = new ShortestJobFirst();
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		shortestJobFirst.schedule(hulpProcessen);	
		System.out.println("Shortest Job First:");
		System.out.println("	Gemiddelde wachtijd: " + shortestJobFirst.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + shortestJobFirst.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + shortestJobFirst.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler shortestRemainingTime = new ShortestRemainingTime();
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		shortestRemainingTime.schedule(hulpProcessen);	
		System.out.println("Shortest Remaining Time:");
		System.out.println("	Gemiddelde wachtijd: " + shortestRemainingTime.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + shortestRemainingTime.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + shortestRemainingTime.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler roundRobin2 = new RoundRobin(2);
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		roundRobin2.schedule(hulpProcessen);	
		System.out.println("Round Robin(timeslice q=2):");
		System.out.println("	Gemiddelde wachtijd: " + roundRobin2.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + roundRobin2.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + roundRobin2.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler roundRobin4 = new RoundRobin(4);
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		roundRobin4.schedule(hulpProcessen);
		System.out.println("Round Robin(timeslice q=4):");
		System.out.println("	Gemiddelde wachtijd: " + roundRobin4.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + roundRobin4.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + roundRobin4.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler roundRobin8 = new RoundRobin(8);
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		roundRobin8.schedule(hulpProcessen);
		System.out.println("Round Robin(timeslice q=8):");
		System.out.println("	Gemiddelde wachtijd: " + roundRobin8.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + roundRobin8.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + roundRobin8.gemNormOmlooptijd);
		hulpProcessen.clear();	
		
		Scheduler highestResponseRatioNext = new HighestResponseRatioNext();
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		highestResponseRatioNext.schedule(hulpProcessen);	
		System.out.println("Highest Response Ratio Next:");
		System.out.println("	Gemiddelde wachtijd: " + highestResponseRatioNext.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + highestResponseRatioNext.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + highestResponseRatioNext.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		
		Scheduler multilevelFeedback = new MultilevelFeedback();
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		multilevelFeedback.schedule(hulpProcessen);	
		System.out.println("Multilevel Feedback:");
		System.out.println("	Gemiddelde wachtijd: " + multilevelFeedback.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + multilevelFeedback.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + multilevelFeedback.gemNormOmlooptijd);
		hulpProcessen.clear();
		
		Scheduler multilevelFeedbackV2 = new MultilevelFeedbackV2();
		hulpProcessen = new PriorityQueue<>();
		for(Process p:processen) {
			hulpProcessen.add(new Process(p));
		}
		multilevelFeedbackV2.schedule(hulpProcessen);	
		System.out.println("Multilevel Feedback versie 2:");
		System.out.println("	Gemiddelde wachtijd: " + multilevelFeedbackV2.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + multilevelFeedbackV2.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + multilevelFeedbackV2.gemNormOmlooptijd);
		hulpProcessen.clear();
	}
}
