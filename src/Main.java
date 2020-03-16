import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args){
		int aantalProcessen=10000; //10000-20000-50000
		
		XMLParser parser=new XMLParser();
		parser.leesProcessen(aantalProcessen);
		
		System.out.println("Aantal Processen: " + aantalProcessen);
		PriorityQueue<Process> processen=parser.getProcessen();
		
		Scheduler firstComeFirstServed = new FirstComeFirstServed();
		firstComeFirstServed.schedule(processen);	
		System.out.println("First Come First Served:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		

		Scheduler roundRobin2 = new RoundRobin(2);
		roundRobin2.schedule(processen);	
		System.out.println("Round Robin q=2:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		

		Scheduler roundRobin4 = new RoundRobin(4);
		roundRobin4.schedule(processen);	
		System.out.println("Round Robin q=4:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		
/*
		Scheduler roundRobin8 = new RoundRobin(8);
		roundRobin8.schedule(processen);	
		System.out.println("First Come First Served:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		*/
		
		Scheduler shortestJobFirst = new ShortestJobFirst();
		shortestJobFirst.schedule(processen);	
		System.out.println("Shortest Job First:");
		System.out.println("	Gemiddelde wachtijd: " + firstComeFirstServed.gemWachttijd);
		System.out.println("	Gemiddelde omlooptijd: " + firstComeFirstServed.gemOmlooptijd);
		System.out.println("	Gemiddelde genormaliseerde omlooptijd: " + firstComeFirstServed.gemNormOmlooptijd);
		
			
	}
}
