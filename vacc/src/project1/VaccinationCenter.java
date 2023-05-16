package project1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VaccinationCenter {
	static final int maxNumOfDocs=5;
	static final int maxNumOfDays=7;
	static final int maxNumOfSlots=4;
	private int centerCode;
	private String centerTitle;
	private String centerCity;
	private Doctor[] doctorlist;
	private int numOfDocs;
	private int numOfapp;
	private Appointment [][][]timetable;
	LocalDateTime dateTime=  LocalDateTime.parse(LocalDate.now().plusDays(1).toString()+"T09:00");
		
	public VaccinationCenter ( int Kek, String title, String city)
	{
		centerCode = Kek;
		centerTitle= title;
		centerCity = city;
		doctorlist = new Doctor[maxNumOfDocs];
		numOfDocs  = 0;
		numOfapp   = 0;
		timetable  = new Appointment[maxNumOfDays][maxNumOfSlots][maxNumOfDocs];		
	}
	public Appointment newAppointment(DayOfWeek Day,LocalTime Slot,String imputAmka,int kap,int initial,int day,int slot)//adds appointments to the timetable 
	{		
		if(initial==100)//for the insured that are not initialized
		{
			Appointment app=new Appointment(imputAmka,centerCode,doctorlist[minApp( Day, Slot)].getAM(),Day,Slot,kap);//creates an app
			timetable[day][slot][minApp( Day, Slot)]= app; //stores the appointment to timetable
			doctorlist[minApp( Day, Slot)].addDoctorApp(app);//stores the appointment to the doctor's list
			numOfapp++;
			return app;
	  }
		else//for the initialized insured
		{
			Appointment app=new Appointment(imputAmka,centerCode,doctorlist[initial].getAM(),Day,Slot,kap);
			timetable[day][slot][initial]= app;
			doctorlist[initial].addDoctorApp(app);
			numOfapp++;
		
	   return app;
		}
	}
 	
	public void Timetable ()//this method prints the available day and time to the user
	{
		System.out.println("Dates for appointments:");
		System.out.println("___________________________");
		for(int day=0; day<maxNumOfDays ; day++)
		{
			for(int slot =0; slot<maxNumOfSlots; slot++)
			{
				for(int doc=0 ; doc<numOfDocs; doc++)
				{
					if(timetable[day][slot][doc]== null)//if its null its available bc nothing is stored
					{	
						DayOfWeek DAY = dateTime.getDayOfWeek().plus(day);
						LocalTime time = dateTime.toLocalTime().plusMinutes(slot*30);
						System.out.println(DAY+" at "+time +" Available" );
						break;
					}
					else
					{ 
						int count=0;
						for(int i=0; i<numOfDocs; i++)
						  {
							if(timetable[day][slot][i]!=null)//this checks if a time slot is occupied by every doctor in a vaccination center
								count++;
						  }
						if(count==numOfDocs)
						{
							DayOfWeek DAY = dateTime.getDayOfWeek().plus(day);
							LocalTime time = dateTime.toLocalTime().plusMinutes(slot*30);
							System.out.println(DAY+" at "+time +" ***FULL***" );
							break;
						}
						
					}
					
				}
			}
			System.out.println("=============================");
		}
	}
	
	public int checksApp(int day,int slot,DayOfWeek Day,LocalTime Slot)//checks valid input for appointment
	{		
			if(timetable[day][slot][minApp( Day,Slot)]!= null)
			{
				System.out.println("The time you enterd is unavailable!");
				return 404;
			}	
		return 1;
	}
	

	public int minApp(DayOfWeek Day,LocalTime Slot)//this method finds the doctor with the minimum appointments 
 	{
		int min=200;
		int l=0;
		for(int i=0; i<numOfDocs; i++)
		{
			if(doctorlist[i].docChecker(Day, Slot)!=-1)
			{
						for(int m=0; m<numOfDocs; m++)
							{
								if( doctorlist[m].getNumberOfappointements()<min )
								{
									min=doctorlist[m].getNumberOfappointements();
								}
							}	
						for( l=0; l<numOfDocs; l++)
						{
							if(doctorlist[l].docChecker(Day, Slot)!=-1 && min==doctorlist[l].getNumberOfappointements())
								{			
									break;
								}
						}		
			}
		
		}
		return l;		
	}
	
	
	public void addDoctors(Doctor d)//this method stores the doctors
	{
		doctorlist[numOfDocs]= d;
		numOfDocs++;
	}
	
	public void printDocs()
	{
		for(int i =0; i<numOfDocs; i++)
		  System.out.println(doctorlist[i].getDoctorName()+" "+doctorlist[i].getAM());
	}
	
	public int doctorsAm(String input)//finds appointments if the user enters doctors AM is used in case 5 
	{
		for(int doc=0; doc<numOfDocs; doc++)
		{
			{
				if(input.equals(doctorlist[doc].getAM()) && doctorlist[doc].getNumberOfappointements()>0 )
				{					
					doctorlist[doc].docsApPrint();
				    return 1;
				}
			}
		}
		return -1;
	}
	
	public String getDocName(String am)// finds doctor name in order to print it for vaccination info
	{
		for(int doc=0; doc< numOfDocs; doc++) 
		{			
			if(doctorlist[doc].getAM().equals(am)) 
			{
				String name =doctorlist[doc].getDoctorName();
				return name;
			}		
		}		
		return "-1";
	}	
			
		public static int getMaxnumofdocs() {
			return maxNumOfDocs;
		}
		public static int getMaxnumofdays() {
			return maxNumOfDays;
		}
		public static int getMaxnumofslots() {
			return maxNumOfSlots;
		}
		public int getNumOfDocs() {
		   return numOfDocs;
	    }
		public int getNumOfapp() {
			return numOfapp;
		}
		public Doctor[] getDoctorlist() {
			return doctorlist;
		}
		public int getCenterCode() {
			return centerCode;
		}
		public String getCenterTitle() {
			return centerTitle;
		}
		public String getCenterCity() {
			return centerCity;
		}

}