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

			
	}
}
