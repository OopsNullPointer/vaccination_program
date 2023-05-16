package project1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Healthorg {
	static final int maxIsnured=100;
	static final int maxVaccCenter=10;
	private String nameOfservice ;
	private String URL;
	private Insured []listOfInsured ;
	private int NumberOfInsured;
	private VaccinationCenter [] listOfVaccCenter;
	private int numberOfCenters;
	LocalDateTime dateTime=  LocalDateTime.parse(LocalDate.now().plusDays(1).toString()+"T09:00");
	
	public Healthorg(String nameOfservice,String URL)
	{
		this.nameOfservice = nameOfservice;
		this.URL=URL;
		listOfInsured= new Insured[maxIsnured];
		NumberOfInsured=0;
		listOfVaccCenter=new VaccinationCenter[maxVaccCenter];
		numberOfCenters=0;
	}
	
	public void addInsured (String Amka,String Name,String City)//this method stores insured persons
	{
		Insured person = new Insured( Amka, Name, City);		
		listOfInsured[NumberOfInsured]=person;
		NumberOfInsured++;
	}
	
	public void printInsured()//prints all the stored insured
	{
		for(int k=0; k<NumberOfInsured; k++)
		System.out.println(listOfInsured[k].getNameOfinsured()+" "+listOfInsured[k].getAMKA()+" " +listOfInsured[k].getNameOfcity());
	}

	public void addCenter(int Kek,String title,String city)//this method stores and checks the uniqueness of the vaccination center
	{
		 VaccinationCenter Vcenter= new VaccinationCenter(Kek,title,city );
	       
	        if(numberOfCenters==0)
	        	{
	        		addVaccCenter(Vcenter);
	        		numberOfCenters++;
	        	}
	        else {
	                checkUniqness(city,Vcenter);
	             }
	}
	
	public void addVaccCenter (VaccinationCenter v)//this method stores vaccination centers
	{
		listOfVaccCenter[numberOfCenters]=v;
		
	}
	
	public void printCenters()//print all the stored vaccination centers
	{
		for(int i=0; i<numberOfCenters; i++)
			{
			   System.out.println(listOfVaccCenter[i].getCenterTitle()+" "+listOfVaccCenter[i].getCenterCode()+" "+listOfVaccCenter[i].getCenterCity());
			}
			
	}
		
	public void checkUniqness(String city, VaccinationCenter Vcenter)//checks if there is only one vaccination center in a city
	{ 
		int l=numberOfCenters;
		for(int i=0; i<l; i++)//check if the vaccination center is the only one in the city
    	{
				     if (city.equals(listOfVaccCenter[i].getCenterCity()))
				    {
					System.out.println("Only one vaccination center in a city!");
					break;
				   }
				     addVaccCenter (Vcenter);
			} 
		numberOfCenters++;
	}
	
	public void VaccCentersAvvailability()//if a center has room for more doctors it appears before you add a doctor in a vaccination center 
	{
		for(int p=0; p<numberOfCenters; p++) 
		{
			if(listOfVaccCenter[p].getNumOfDocs()!= VaccinationCenter.getMaxnumofdocs())
			System.out.println("Available vaccination center "+listOfVaccCenter[p].getCenterCity()+" code "+listOfVaccCenter[p].getCenterCode());
		}
		
	}
	
	public void addDoctorsInKek(String am,String docname, int kek)//this method adds doctors in a vaccination center
	{
		int checkForVaccinationCenters =0;//checks if the code of vaccination center the user entered is valid
		
		for(int i=0; i<numberOfCenters; i++)
		{
			if (kek == listOfVaccCenter[i].getCenterCode() )
			{
				Doctor newDoc= new Doctor(am,docname);
				listOfVaccCenter[i].addDoctors(newDoc);

				checkForVaccinationCenters ++;
			}
		}				
		if(checkForVaccinationCenters==0)
			System.out.println("There are no vaccination centers with this code: "+kek);
	}
	
	public void printDoctori()
	{			
		System.out.println("________________________________________________________");

		for (int i=0; i<numberOfCenters; i++)
		{
			System.out.println("Doctors in vaccination center with this code: "+listOfVaccCenter[i].getCenterCode()+" are:");
			listOfVaccCenter[i].printDocs();
			System.out.println("________________________________________________________");
		}
	}
			
	public int findVaccCenter(String amka)//finds the Vaccination center which is in the same city with the insured
	{
		int k=0;
		int check=0;
		for(int i=0; i<NumberOfInsured; i++)
		{ 
			if(listOfInsured[i].getAMKA().equals(amka))
			{
				for( k=0; k<numberOfCenters; k++)
				{
					if(listOfVaccCenter[k].getCenterCity().equals(listOfInsured[i].getNameOfcity())&& listOfInsured[i].getApp()== null)
					{	
						System.out.println("______________________________________");
						System.out.println("|Choose time and day for vaccination |\n|Everyday for 7 days:\t\t     |\n|1)9:00-9:30\t\t\t     |\n|2)9:30-10:00\t\t\t     |\n|3)10:00-10:30\t\t\t     |\n|4)10:30-11:00\t\t\t     |");
						System.out.println("|____________________________________|\n");
						listOfVaccCenter[k].Timetable();
						return k;
					}
					else if(listOfInsured[i].getApp() != null)
					{
						System.out.println("YOU HAVE ALREADY BOOKED AN APPOINTMENT");
						check++;
						return -1;
					}
				}
			}
		}
		if(check==0)
		  {
			System.out.println("This insured :"+amka + " is not inserted in any vaccination center try another AMKA or enter a new vaccination center");
		  }
		return -1;			
	}
	
	public void enterDATE(String amka,int DAY,int SLOT, int vacCenterIndex,int kap,int initial)//this method creates a new appointment
	{	
		DayOfWeek day = dateTime.getDayOfWeek().plus(DAY);
		LocalTime time = dateTime.toLocalTime().plusMinutes(SLOT*30);
		if(  DAY<7 && SLOT<4 && listOfVaccCenter[vacCenterIndex].checksApp(DAY,SLOT, day,time)!=404) 
		{
			if(NumberOfInsured>9)//for the initialization bc for the first 9 insured i initialize the doctors and KAP an for the others its done by the system 
			{
				Random r = new Random();
				int low = 40;
				int high = 180;
				kap = r.nextInt(high-low) + low;//generate random numbers between 40 and 180
				
				Appointment app= listOfVaccCenter[vacCenterIndex].newAppointment(day, time, amka,kap,initial,DAY,SLOT);
			
					for(int i=0; i<NumberOfInsured; i++)
					{ 
						if(listOfInsured[i].getAMKA().equals(amka))
						{
							listOfInsured[i].insuredAppointment(app);
							System.out.println("Your appointment is in vaccination center "+listOfVaccCenter[vacCenterIndex].getCenterTitle()+" with code: "+listOfVaccCenter[vacCenterIndex].getCenterCode()+" and it's located in "+listOfVaccCenter[vacCenterIndex].getCenterCity());
							printInfo ( amka);
						}
					}
			    }
		
			else
				{	
					Appointment app= listOfVaccCenter[vacCenterIndex].newAppointment(day, time, amka,kap,initial,DAY,SLOT);
		
					for(int i=0; i<NumberOfInsured; i++)
					{ 
						if(listOfInsured[i].getAMKA().equals(amka))
						{
							listOfInsured[i].insuredAppointment(app);					
						}
					}
				}
		}
		else
			System.out.println("You entered an invalid date!");					
	}
	 	 
	public void printInfo (String amka)//this method prints the info of appointment in case 4
	{
		for(int i=0; i<NumberOfInsured; i++)
		{ 
			if(listOfInsured[i].getApp()!=null && listOfInsured[i].getAMKA().equals(amka) )
				{
					System.out.println("Day of vaccination: "+listOfInsured[i].getApp().getApDate()+" time of vaccination: "+listOfInsured[i].getApp().getSlot()+" your appointment code:"+listOfInsured[i].getApp().getKAP()+" doctor's AM:"+listOfInsured[i].getApp().getDocAm());
				}		
		}
	}
	
    public void findAppointment(String input)//this method finds and print the appointments
	{
    	int check=0;//check if the user enters valid input
		for(int i=0; i<NumberOfInsured; i++)//method if the user enters his AMKA
		{
			if(listOfInsured[i].getAMKA().equals(input) && listOfInsured[i].getApp()!=null)
			{
				System.out.println(("The appointment is on "+listOfInsured[i].getApp().getApDate()+" at "+listOfInsured[i].getApp().getSlot()+" doctor's AM:"+listOfInsured[i].getApp().getDocAm()+"\nYour appointment code is: "+listOfInsured[i].getApp().getKAP()) +" and the vaccination center is:"+listOfInsured[i].getApp().getApKek()+" which is located in "+listOfInsured[i].getNameOfcity());
				check=100;
				break;			
			}
		}
			
		for(int d=0;d<numberOfCenters; d++) //method if the user enters center code
		{
			String KEK=Integer.toString(listOfVaccCenter[d].getCenterCode());
			if(KEK.equals(input))
			{
				for(int k=0;k<NumberOfInsured ; k++)
				{
					if(listOfInsured[k].getApp()!=null)
					{
						listOfInsured[k].findVaccInsured(input); 
						check=200;
					}
					
				}
				
			}
		}
				
		if(check==0)
		{
			for (int t=0; t< numberOfCenters; t++)//method if the user enters doctor's Am
				{		
					if(check!=1)
					{
						check=listOfVaccCenter[t].doctorsAm(input);
					}
				}
		  }
		
		if(check==0 || check==-1)// check if the input is valid
		{
			System.out.println("Try again you entered wrong AMKA or an insured that haven't book an appointment yet, or wrong center code, or wrong doctor's AM");
		}
				
	}
    
		public String getNameOfservice() {
			return nameOfservice;
		}
		public String getURL() {
			return URL;
		}
}