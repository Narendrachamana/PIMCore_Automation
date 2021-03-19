package com.Breville.Tests;

import java.time.LocalDate;
import java.util.ArrayList;


public class Sample {

	public void addMonth(String date,int months) {
		int period = 6;
		//int monthincrement = 4;
		
		ArrayList<LocalDate> al = new ArrayList<>();
		
		LocalDate localdatetime = LocalDate.parse(date);
	
		for (int i=1;i<=period;i++) {
			
			al.add( localdatetime.plusMonths(months));
		 localdatetime = localdatetime.plusMonths(months);
	
		}
		
		
		 System.out.println(al);
	}

	public static void main(String[] args) {
		Sample s1= new Sample();
		s1.addMonth("2021-03-08", 3);
		//	System.out.println(System.getProperty("os.name"));
		
		
	
}

}
