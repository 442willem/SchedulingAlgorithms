import java.math.BigDecimal;
import java.math.RoundingMode;

public class Process implements Cloneable, Comparable<Object>{
	int id;
	int arrivalTime;
	int serviceTime;

	int startTijd;
	int endTijd;
	int resterendeServiceTime;


	double omloopTijd;
	double normOmloopTijd;
	double wachtTijd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getStartTijd() {
		return startTijd;
	}

	public void setStartTijd(int startTijd) {
		this.startTijd = startTijd;
	}

	public int getEndTijd() {
		return endTijd;
	}

	public void setEndTijd(int endTijd) {
		this.endTijd = endTijd;
	}

	public int getResterendeServiceTime() {
		return resterendeServiceTime;
	}

	public void setResterendeServiceTime(int resterendServiceTime) {
		this.resterendeServiceTime = resterendServiceTime;
	}

	public double getOmloopTijd() {
		return omloopTijd;
	}

	public void setOmloopTijd(int omloopTijd) {
		this.omloopTijd = omloopTijd;
	}

	public double getNormOmloopTijd() {
		return normOmloopTijd;
	}

	public void setNormOmloopTijd(int normOmloopTijd) {
		this.normOmloopTijd = normOmloopTijd;
	}

	public double getWachtTijd() {
		return wachtTijd;
	}

	public void setWachtTijd(int wachtTijd) {
		this.wachtTijd = wachtTijd;
	}

	public void verminder(int tijd) {
		if(resterendeServiceTime-tijd<=0)resterendeServiceTime=0;
		else resterendeServiceTime-=tijd;
	}

	public Process(int i, int a, int s) {
		id=i;arrivalTime=a;serviceTime=s;resterendeServiceTime=s;
	}
	public Process() {
		id=0;arrivalTime=0;serviceTime=0;
	}
	public Process(Process poll) {
		id= poll.id;
		arrivalTime=poll.arrivalTime;
		serviceTime=poll.serviceTime;
		resterendeServiceTime=poll.serviceTime;
		startTijd=-1;
	}

	public void schrijf() {
		System.out.println("id:"+id+" arriv: "+arrivalTime+" serv: "+serviceTime);
	}
	public void rekenUit() {
		wachtTijd=endTijd-arrivalTime-serviceTime;		
		omloopTijd=wachtTijd+serviceTime;
		normOmloopTijd=round(omloopTijd/serviceTime,5);
	}	

	// Nodig mits gebruik PriorityQueue
	@Override
	public int compareTo(Object o) {
		Process p = (Process) o;
		return this.arrivalTime < p.arrivalTime ? -1 : 1;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Process cloned = new Process();
		cloned.id = this.id;
		cloned.arrivalTime = this.arrivalTime;
		cloned.serviceTime = this.serviceTime;
		cloned.startTijd = this.startTijd;
		cloned.endTijd = this.endTijd;
		cloned.omloopTijd = this.omloopTijd;
		cloned.normOmloopTijd = this.normOmloopTijd;
		cloned.wachtTijd = this.wachtTijd;
		return cloned;
	}
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}}
