package project1;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Doctor {
	private String AM;
	private String doctorName;
	private Appointment[] DocApp;
	private int numberOfappointements;
	
	public Doctor (String am , String name)
	{
		AM = am;
		doctorName = name;
		numberOfappointements=0;
		DocApp= new Appointment[20];
	}
	public void addDoctorApp(Appointment a) //add's doctor in kek
	{
		DocApp[numberOfappointements]= a;
		numberOfappointements++;
	}
	
	public void docsApPrint()//prints the appointment if you search with doctor's AM
	{
		for (int k=0; k<numberOfappointements; k++)
		{
			System.out.println("the appointment is in day:"+DocApp[k].getApDate()+" and in slot: "+DocApp[k].getSlot());
		}
	}
	
	public int docChecker(DayOfWeek DAY,LocalTime time)//checks if the doctor with the less appointments has an appointment in the same day and time as the new appointment
	{
		for(int day=0; day<numberOfappointements; day++)
		{		
			if(DAY.equals(DocApp[day].getApDate()))
			{
				for(int slot=0; slot<numberOfappointements; slot++)
				{
					if(time.equals(DocApp[slot].getSlot()))
					{
						return -1;
					}
				}
			}
		}
		return 1;
	}
			
		public int getNumberOfappointements() {
			return numberOfappointements;
		}
		public String getAM() {
			return AM;
		}
		public void setAM(String aM) {
			AM = aM;
		}
		public String getDoctorName() {
			return doctorName;
		}
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
		public Appointment[] getDocApp() {
			return DocApp;
		}
		public void setDocApp(Appointment[] docApp) {
			DocApp = docApp;
		}		
}