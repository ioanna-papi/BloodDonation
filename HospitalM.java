import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HospitalM {
	
	//declaring the variables
	static String username;
	static String phonenumber;
	static double LimitInLiters;
    static String password;
	static String Address;
	public static ArrayList<Object> Hospitals = new ArrayList<Object>();
    static Double[] bloodtypeLimit = new Double[8]; //a list with the minimum blood amount the current hospital needs
	static String[]  bloodtype = {"O+", "O-", "A+", "A-" ,"B+" ,"B-" ,"AB+" ,"AB-"}; 
	static Scanner input = new Scanner(System.in);
	public static Object getList;
	static String hospital_login;
	static String password_login;
	static String fullname; 
	public static boolean Answer() {
		System.out.println("Are you sure this is the correct amount?Type yes or no.");
		Scanner sc = new Scanner(System.in);
		String answer = sc.nextLine();
		if (answer.equals("yes")) {
			return true;
		}	
		else {
			return false;
		}
	}
	public static double limitLiters(Scanner input,String b) {
		double temp;
		do{
			System.out.println("Please enter the limit amount of blood for blood type "+b+" :");
			temp = input.nextDouble();
			if (temp < 0)
				System.err.println("Please enter a positive number");
		}while (temp<0);
		return temp;
			
	}
	public static double bloodLimit(Scanner input,int i) throws InputMismatchException{
		 	String b = bloodtype[i];
		 	boolean flag = false;
			
		 	do {
				try {
			 		double temp = limitLiters(input,b);
					flag =  Answer();
					if (flag == true)
						LimitInLiters = temp;
					if (flag == false) 
						LimitInLiters = limitLiters(input,b);
					break;	
				}
				catch(final InputMismatchException e2) {
					System.err.println("The input has to be a number!");
					final Scanner input1 = new Scanner(System.in);
					double temp = limitLiters(input1,b);
					flag =  Answer();
					if (flag == false) { 
						LimitInLiters =temp;
						continue;	
					}
					if (flag == true) {
						
						break;
					}
					
					
					
				}
			}while (flag == false);
		 	
		 	
	
		return LimitInLiters;
	}
	public static boolean correctPhonenumber(String phonenumber) throws HospitalPhoneNumberException{
		boolean flag = false;
		do {
			
			if (phonenumber.length() <= 11) {
				break;
			}	
			else {
				flag = true;
				System.err.println("Please enter a valid phone number(up to 10 digits)");
			}
		}while (flag);
		return flag;
	}
	
	
	//sign up method
	public static void signUp(){
		
		// Hospital's Name
		boolean flag = true;
		do {
			try {	
					System.out.println("Enter your hospital's name: ");
					fullname = input.nextLine();
					
			}
			catch (InputMismatchException e) {
				System.err.println("Please enter a valid hospital name.");
				flag = false;
		}
		}while (flag == false);
		
		// Hospital's phone number
			flag = true;
			while (flag == true) {
				try {
					System.out.println("Enter your phone number.");
					phonenumber = input.nextLine();
					flag = correctPhonenumber(phonenumber);
				}
				catch (HospitalPhoneNumberException e1) {
					System.err.println(e1.getMessage());
				}
			}
		
			
			
		
		// Hospital's address	
		flag = true;
		do {
			System.out.println("Enter your hospital's address");
			Address = input.nextLine();
			if (Address.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				break;
			} else {
				flag = false;
				System.err.println("Your address must contain both numbers and letters");
			}
			
		}while(flag == false);
	
				
		
		
		// Hospital's blood limit
		for (int i = 0;i<=7; i++) {
			bloodtypeLimit[i] = bloodLimit(input,i);
		}
			
		//password
		flag = false;
		do {
			System.out.println("Enter a new password:");
			Scanner input1 = new Scanner(System.in);
			String password = input1.nextLine();
			if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				break;
			} else {
				flag = false;
				System.err.println("Your password must contain both numbers and letters");
			}
			
		}while(flag == false);
	
			
		
		
		Object CurrentHospitalData[] = new Object[5];
		//a list that contains the current hospital's data that are about to be a part of the "Hospitals" list
		
		CurrentHospitalData[0] = username;
		CurrentHospitalData[1] = phonenumber;
		CurrentHospitalData[2] = Address;
		CurrentHospitalData[3] = bloodtypeLimit;
		CurrentHospitalData[4] = password;
		
		Hospitals.add(CurrentHospitalData);
		logIn(Hospitals);
	}
	public static void logIn(Object Hospitals) {
		
			boolean flag = true;
			do {
				System.out.println("Welcome!Please type your hospital's name. ");
				hospital_login = input.nextLine();
				flag = ((String) Hospitals).contains(hospital_login);
				if (flag) {
					boolean flag1 = true;
					do {
						System.out.println("Enter your password.");
						password_login = input.nextLine();
						flag1 = ((String) Hospitals).contains(password_login);
						if (flag1 == false)
							System.err.println("Wrong password!");
					}while (flag1 == false);
				}
					
				else {
					System.err.println("This username is not registered!");
				}
			}while (flag == false);
	}

		
public static void main (String args[]) {
	signUp();
	
}
}	
			
