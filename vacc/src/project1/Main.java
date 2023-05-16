package project1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int x=10;	//for the do while function	
		
		Healthorg health = new Healthorg("HEALTH MINISTRY","url");//object of the class healthorg
		
		// initialize insured
		health.addInsured("11111111","Asfalismenos 1 ", "Chania");
		health.addInsured("22222222","Asfalismenos 2 ", "Chania");
		health.addInsured("33333333","Asfalismenos 3 ", "Chania");
		health.addInsured("44444444","Asfalismenos 4 ", "Chania");
		health.addInsured("55555555","Asfalismenos 5 ", "Chania");
		
		health.addInsured("66666666","Asfalismenos 6 ", "Rethimno");
		health.addInsured("77777777","Asfalismenos 7 ", "Rethimno");
		health.addInsured("88888888","Asfalismenos 8 ", "Rethimno");
		health.addInsured("99999999","Asfalismenos 9 ", "Rethimno");
		
		//initialize vaccination centers
		health.addCenter(22222, "CH-22", "Chania");
		health.addCenter(33333, "RTH-33", "Rethimno");
		
		//initialize doctors in vaccination centers
		health.addDoctorsInKek("111111", "Doctor 1", 22222);//Chania
		health.addDoctorsInKek("222222", "Doctor 2", 22222);
		health.addDoctorsInKek("333333", "Doctor 3", 22222);
		
		health.addDoctorsInKek("444444", "Doctor 4", 33333);//Rethimno
		health.addDoctorsInKek("555555", "Doctor 5", 33333);
		
		//initialize appointments for the first vaccination center , Chania
		health.enterDATE("11111111",0,0 ,0,20,0);
		health.enterDATE("22222222",0,3 ,0,21,0);
		health.enterDATE("33333333",0,3 ,0,22,1);
		health.enterDATE("44444444",0,3 ,0,23,2);		
		health.enterDATE("55555555",1,1 ,0,24,0);
		
		//initialize appointments for the second vaccination center , Rethimno
		health.enterDATE("66666666",1,1 ,1,30,0);
		health.enterDATE("77777777",1,1 ,1,31,1);
		health.enterDATE("88888888",1,2 ,1,32,1);
		health.enterDATE("99999999",2,1 ,1,33,0);
		 
	
/*menu*/System.out.println("\t~WELCOME~\n•If you want to enter an insured press 1\n•If you want to enter a vaccination center press 2\n•If you want to enter a doctor press 3\n•If you want to enter an appointment press 4\n•If you want to find and print the appointment press 5\n•If you want to print all the insured press 6\n•If you want to print all the doctors press 7\n•If you want to print all the vaccination centers prees 8\n•Press 0 to exit");
		Scanner scan= new Scanner(System.in);
		 
	 do {
		 System.out.println("\nEnter the number of the option you want:");		
		 int s=scan.nextInt();		
		 
		switch(s) {
			case 1:  System.out.println("Enter AMKA:"); //import insured
			         String Amka= scan.next();
			    
			         System.out.println("Enter name:");
			         scan.nextLine();
			         String Name =scan.nextLine();
			      
			         System.out.println("Enter city name:");
			         String City =scan.next();
			         			         
			         health.addInsured(Amka, Name, City);
			       
				break;
				
			case 2: System.out.println("Enter KEK:"); //import vaccination center
				    int Kek = scan.nextInt();
				   
				    System.out.println("Enter the title:");
			        scan.nextLine();
			        String title =scan.nextLine();
			        
			        System.out.println("Enter city name:");
			        String city =scan.next();
			        
			        health.addCenter( Kek, title, city);
			        		         
				break;
				
			case 3: System.out.println("Enter doctor's AM:"); //import doctor in vaccination center
					String am = scan.next();
					
					System.out.println("Enter doctor's name:");
				    scan.nextLine();
				    String docname = scan.nextLine();			
					
					health.VaccCentersAvvailability();
					
					System.out.println("Enter doctor's KEK:");
					int kek = scan.nextInt();
					
					health.addDoctorsInKek(am,docname, kek);
				
				break;
				
			case 4: System.out.println("Enter insured's AMKA:"); //add appointment and print info
			        String inputAmka= scan.next();
			      	
			        int index= health.findVaccCenter(inputAmka);
			       
			        if(index!=-1)
			       { 
			        System.out.println("Enter the day you want to get vaccinated exmpl: 1  ( Tomorrow ...press 1 to choose day 1 ect)");
			        int DAY = scan.nextInt();
			        
			        System.out.println("Enter the time you want to get vaccinated exmpl: 3  ( 10:00 ... press 3 to choose 10:00 ect)");
			        int SLOT = scan.nextInt();			        
			       	
			        int kap=30;
			        health.enterDATE(inputAmka,DAY-1, SLOT-1 ,index,kap,100);
			       }
		        
			    break;
			    		    
			case 5: System.out.println("Enter your AMKA , the center code or doctor's AM:"); //find and print appointmnt
			        String input= scan.next();
			
			        health.findAppointment(input);
				
				break;
				
			case 6: health.printInsured();
			
				break;
				
			case 7:health.printCenters();
			   break;
				
			case 8:health.printDoctori();
				break;
				
			case 0: x=0; // stops program
				break;
			default: System.out.println("You entered an invalid number try again"); //if the input is invalid
			    break;
				
		   }
		
	 }while (x!=0);
	 scan.close();	
	 
	 System.out.println("Thank you for using this program !");
	}	
}