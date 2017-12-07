import java.util.Scanner;


public class Timecard {

	/**
	 * A small program used to calculate total hours worked by employees over a 2 week period.
	 * 
	 * Assumes employees start work in the morning and stop in the afternoon.
	 * 
	 * Program takes in in/out times, and if in military time converts to 12hr, and totals the hours up.
	 * 
	 */
	private int timeIn_HR;
	private int timeIn_MIN;
	private int timeOut_HR;
	private int timeOut_MIN;
	private int timeTotal_HR;
	private int timeTotal_MIN;
	private int[] timeTotals;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("***********************************************");
		System.out.println("* This is a very basic time sheet calculator. *");
		System.out.println("* Please follow the instructions and enter -1 *");
		System.out.println("* when finished.                              *");
		System.out.println("***********************************************");
		Timecard tc = new Timecard();
	}
	
	public Timecard(){
		timeIn_HR = 0;
		timeIn_MIN = 0;
		timeOut_HR = 0;
		timeOut_MIN = 0;
		timeTotal_HR = 0;
		timeTotal_MIN = 0;
		timeTotals = new int[14]; 
		run();
	}
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		int count = 0;
		int temp = 0;
		while(count < 14){
			//Get in time
			System.out.print("\n\nPlease enter the IN time hour: ");
			timeIn_HR = sc.nextInt();
			if(isDone(timeIn_HR))
				break;
			System.out.print("Please enter the IN time minutes: ");
			timeIn_MIN = sc.nextInt();
			if(isDone(timeIn_MIN))
				break;

			
			//Get out time
			System.out.print("\nPlease enter the OUT time hour: ");
			timeOut_HR = sc.nextInt();
			if(isDone(timeOut_HR))
				break;
			System.out.print("Please enter the OUT time minutes: ");
			timeOut_MIN = sc.nextInt();
			if(isDone(timeOut_MIN))
				break;
			
			//store total minutes for the day
			temp = calcDayTotal(timeIn_HR, timeIn_MIN, timeOut_HR, timeOut_MIN);
			timeTotals[count] = temp; 
			//System.out.println(temp);
			count++;
		}
		
		calculate();
		System.out.println("Total time: " + timeTotal_HR + ":" + timeTotal_MIN);
		
	}
	
	public boolean isDone(int x){
		if(x == -1){
			return true;
		}
		return false;
	}
	
	/*
	 * Returns total minutes in that shift and will later in the process be 
	 * formatted into hours and minutes
	 */
	public int calcDayTotal(int tiH, int tiM, int toH, int toM){
		int totalHours = (12-tiH) + toH;
		int totalMins;
		if(toM > tiM){
			totalMins = toM - tiM;
		}else if(tiM > toM){
			totalMins = (60-tiM) + toM;
		}else{
			totalMins = 0;
		}
		totalMins += totalHours * 60;
		return totalMins;
	}
	
	public void calculate(){
		int minutes = 0;
		for(int m: timeTotals){
			minutes += m;
		}
		timeTotal_HR = minutes/60;
		timeTotal_MIN = minutes%60;
	}

}
