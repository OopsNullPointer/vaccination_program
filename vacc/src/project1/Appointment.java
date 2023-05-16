package project1;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Appointment {
	private int KAP;
	private DayOfWeek apDate;
	private LocalTime slot;
	private String apAmka;
	private int apKek;
	private String docAm;

	public Appointment(String apAmka,int apKek,String docAm,DayOfWeek day,LocalTime slot,int kap)
	{
		KAP=kap;		
		apDate=day;
		this.slot=slot;
		this.apKek=apKek;
		this.apAmka=apAmka;
		this.docAm=docAm;		
	}
	
		public int getKAP() {
			return KAP;
		}
		public DayOfWeek getApDate() {
			return apDate;
		}
		public LocalTime getSlot() {
			return slot;
		}
		public int getApKek() {
			return apKek;
		}
		public String getDocAm() {
			return docAm;
		}
		public String getApAmka() {
			return apAmka;
		}
			
}