package project1;

public class Insured {
	private String AMKA;
	private String nameOfinsured;
	private String nameOfcity;
	private Appointment app;
		
	public Insured (String amka, String name,String city)
	{
		AMKA=amka;
		nameOfinsured= name;
		nameOfcity= city; 		
	}
	
	public void insuredAppointment(Appointment a)//stores the appointment to each insured individually 
	{
		app=a;
	}
	
	public void findVaccInsured(String imput)//finds vaccination center
	{
		String KEK=Integer.toString(app.getApKek());//convert in order to compare and find\print the info
		
		if(KEK.equals(imput))			
			{
				System.out.println("The appointment is on "+app.getApDate()+" at "+app.getSlot()+" doctor's AM:"+app.getDocAm()+" your appointment code:"+app.getKAP());
			}	
	}
	
		public Appointment getApp() {
			return app;
		}
		public String getAMKA() {
			return AMKA;
		}
		public String getNameOfinsured() {
			return nameOfinsured;
		}
		public String getNameOfcity() {
			return nameOfcity;
		}
}