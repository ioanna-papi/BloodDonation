
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.ArrayList;

public class BloodDonor {
	private static String username;
	static String gender;
	static String bloodtype;
	static String AMKA;
	private static String region;
	static String[]  bloodtypes = {"O+", "O-", "A+", "A-" ,"B+" ,"B-" ,"AB+" ,"AB-"}; 
	private static String password;
	static ArrayList<Object> BloodDonors = new ArrayList<Object>();
	static String username_login;
	static String password_login;
	static Object CurrentBloodDonorData[] = new Object[6];
	
	final static Scanner input = new Scanner(System.in);
	
	
	
	
	
	//custom exceptions for bloodtype&gender
	public static boolean correctGender(String gender) throws GenderException{
		
		if ((gender.equals("male")) || (gender.equals("female"))) {
			return true;
		}
		else {
			throw new GenderException("Please type male or female.");
		}
		
	}
	public static boolean correctBloodType(String bloodtype, String bloodtypes[]) throws BloodTypeException {
		boolean flag = false;
		int i=0;
		while ((flag == false) && (i<=7)) {
			if (bloodtype.equals(bloodtypes[i])) 
				flag = true;
			else 
				i++;
		}
			if (flag == false) 
				throw new BloodTypeException("Please enter a valid bloodtype.");
				return flag;
		}



	public static void signUp()  {
		
		
		
		//donor's username
		boolean flag = false;
		do {
				System.out.println("Enter your full name:");
				username = input.nextLine();
				flag = BloodDonors.contains(username);
				if (flag)
					System.err.println("The username is already used.");
				else
					flag = false;
		}while (flag == true);
			
			
		
			
		
		
		
		
		//donor's gender
				flag = false;
				do {
					try {
						System.out.println("Gender:");
						gender = input.nextLine();
						flag = correctGender(gender);
					}
					catch (GenderException e1) {
						System.out.println(e1.getMessage());
					}
				}while (flag == false);
				
					
			
		//donor's bloodtype
		boolean ifloopneeded = false;
		//ifloopneeded is a boolean variable used to determine whether user gave valid data
		//or they need to give new data because they were wrong
		do {
			try {
				System.out.println("Please type your bloodtype choosing one of the following:");
				for (int i=0; i<=7; i++) {
					System.out.println(bloodtypes[i]);		
				 }
				 bloodtype = input.nextLine();
				 ifloopneeded = correctBloodType(bloodtype,bloodtypes);
							
			 }
			 catch(BloodTypeException e2) {
				System.out.println(e2.getMessage());
			 }
		}while (ifloopneeded == false);
					
		
		
		
		//donor's AMKA
		flag = false;
		do {
			System.out.println("Enter your AMKA:");
			Scanner input3 = new Scanner(System.in);
			AMKA = input.nextLine();
			if (AMKA.length() == 11) {
				break;
			}	
			else {
				flag = true;
				System.err.println("Please enter a valid AMKA (11 digits)");
			}
		}while (flag);
		
		
		
		//donor's region
		flag = true;
		do {
			try {
				System.out.println("Enter your region:");
				Scanner input4 = new Scanner(System.in);
				region = input4.nextLine();
			}
			catch (InputMismatchException e4) {
				System.err.println("Please enter a valid region.");
			}
				
		}while(flag == false);			
		
		//donor's password
		flag = false;
		do {
			System.out.println("Enter a new password:");
			Scanner input1 = new Scanner(System.in);
			String password = input1.nextLine();
			if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				flag = true;
				break;
			
			} else {
				flag = false;
				System.err.println("Your password must contain both numbers and letters");
			}
			
		}while(flag == false);
	
		
	
		CurrentBloodDonorData[0] = username; 
		CurrentBloodDonorData[1] = gender;
		CurrentBloodDonorData[2] = bloodtype;
		CurrentBloodDonorData[3] = AMKA;
		CurrentBloodDonorData[4] = region;
		CurrentBloodDonorData[5] = password;
		
		
		BloodDonors.add(CurrentBloodDonorData);
		logIn(BloodDonors);
	}
	
	
	public static void logIn(Object BloodDonors) {
		
		boolean flag = false;
		do {
			System.out.println("Welcome!Please type your username. ");
			username_login = input.nextLine();
			flag = ((String) BloodDonors).contains(username_login);
			if (flag) {
				boolean flag1 = false;
				do {
					System.out.println("Enter your password.");
					password_login = input.nextLine();
					flag1 = ((String) BloodDonors).contains(password_login);
					if (flag1 == false)
						System.err.println("Wrong Password!");
				}while (flag1 == false);
			}
				
			else {
				System.err.println("This username is not registered!");
			}
		}while (flag == false);
		
		
		
		
	}
		
	
public static void main(String args[]) {
	signUp();
	logIn(BloodDonors);
}
}
		
		
		
	
		 

