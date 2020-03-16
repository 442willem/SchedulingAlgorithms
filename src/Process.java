
public class Process implements Cloneable, Comparable<Object>{
	int id;
	int arrivalTime;
	int serviceTime;
	
	int startTijd;
	int endTijd;
	
	int omloopTijd;
	int normOmloopTijd;
	int wachtTijd;
	
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

	public int getOmloopTijd() {
		return omloopTijd;
	}

	public void setOmloopTijd(int omloopTijd) {
		this.omloopTijd = omloopTijd;
	}

	public int getNormOmloopTijd() {
		return normOmloopTijd;
	}

	public void setNormOmloopTijd(int normOmloopTijd) {
		this.normOmloopTijd = normOmloopTijd;
	}

	public int getWachtTijd() {
		return wachtTijd;
	}

	public void setWachtTijd(int wachtTijd) {
		this.wachtTijd = wachtTijd;
	}
	
	public void verminder(int tijd) {
		serviceTime-=tijd;
	}

	public Process(int i, int a, int s) {
		id=i;arrivalTime=a;serviceTime=s;
	}
	public Process() {
		id=0;arrivalTime=0;serviceTime=0;
	}
	public Process(Process p) {
		this.id=p.id;
		this.arrivalTime=p.arrivalTime;
		this.serviceTime=p.serviceTime;
		this.startTijd=p.startTijd;
		this.endTijd=p.endTijd;
		this.normOmloopTijd=p.normOmloopTijd;
		this.omloopTijd=p.omloopTijd;
		this.wachtTijd=p.wachtTijd;
	}

	public void rekenUit() {
		wachtTijd=startTijd-arrivalTime;
		omloopTijd=wachtTijd+serviceTime;
		normOmloopTijd=omloopTijd/serviceTime;
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
}
