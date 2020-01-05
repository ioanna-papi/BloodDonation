import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HospitalM {
	
	//declaring the variables
	static String username;
	static String fullname;
	static String phonenumber;
	static double LimitInLiters;
	static String password;
	static String Address;
	static String region;
	public static ArrayList<Object> Hospitals = new ArrayList<Object>();
	static Double[] bloodtypeLimit = new Double[8]; //a list with the minimum blood amount the current hospital needs
	static String[]  bloodtype = {"O+", "O-", "A+", "A-" ,"B+" ,"B-" ,"AB+" ,"AB-"}; 
	public static Object getList;
	static String hospital_login;
	static String password_login; 
	
	public static boolean Answer() {
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure this is the correct amount?");
		if (answer == JOptionPane.YES_OPTION) {
			return true;
		}	
		else {
			return false;
		}
	}
	
	public static double limitLiters(String b) {
		double temp;
		do{
			String t = JOptionPane.showInputDialog(null, "Please enter the limit amount of blood for blood type " + b + " :");
			//doesn't perform a test if temp is not a number
			temp = Double.valueOf(t);
			if (temp < 0)
				JOptionPane.showMessageDialog(null, "Please enter a positive number", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
		}while (temp<0);
		return temp;
			
	}
	public static double bloodLimit(int i) throws InputMismatchException{
		 	String b = bloodtype[i];
		 	boolean flag = true;			
		 	do {
				try {
			 		double temp = limitLiters(b);
					if (Answer() == true) {
						LimitInLiters = temp;
						flag = false;
					} else {
						LimitInLiters = limitLiters(b);
						break;
					}
				}
				catch(final InputMismatchException e2) {
					JOptionPane.showMessageDialog(null, "The input has to be a number!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					double temp = limitLiters(b);
					if (Answer() == true) { 
						LimitInLiters =temp;
						flag = false;
						continue;	
					} else {	
						break;
					}				
				}
			}while (flag);
			return LimitInLiters;
	}

	public static boolean correctPhonenumber(String phonenumber) throws HospitalPhoneNumberException{
		boolean flag = false;
		while (flag == false) {
			
			if ((phonenumber.length() <= 11) && ((phonenumber.length() >= 10))) {
				flag = true;
				break;
			} else {
				flag = true;
				JOptionPane.showInputDialog(null, "Please enter a valid phone number(up to 10 digits)" , "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}
		return flag;
	}
	
	//sign up method
	public static void signUp(){
		
		// Hospital's Name
		boo lean flag = true;
		do {
			try {	
				String fullname = JOptionPane.showInputDialog(null,"Enter your hospital's name: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid hospital name.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = false;
			}
		}while (flag == false);
		
		//Hospital's username
		boolean f = true;
		do {
			try {
				String username = JOptionPane.showInputDialog(null,"Enter your hospital's username: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (InputMismatchException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid hospital username.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				f = false;
		}
		}while (f == false);

		// Hospital's phone number
			flag = true;
			phonenumber = JOptionPane.showInputDialog(null,"Enter your hospital's phone number: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			do {
				try {
					int p = 0;
					p=Integer.parseInt(phonenumber);
					Hospital.correctPhonenumber(phonenumber);
					flag = false;
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Phone number must contain only numbers.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					phonenumber = JOptionPane.showInputDialog(null,"Enter your hospital's phone number: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				}
			}while (flag);		
		
		// Hospital's address	
		flag = true;
		Address = JOptionPane.showInputDialog(null,"Enter your hospital's address: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
		do {
			if (Address.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				flag = false;
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Your address must contain both letters and numbers", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				Address = JOptionPane.showInputDialog(null,"Enter your hospital's address: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			}
		}while(flag);
				
		
		 //Hospital's region
                flag = true;
                Object[] possibilities = {"Attica","South Aegean Sea","North Aegean Sea","Central Greece","West Greece",
                         "Ionian Islands","Thessaly","Peloponnese","Epirus","Eastern Macedonia and Thrace",
                         "Central Macedonia","West Macedonia","Crete"};
                while (flag) {
                        region = (String)JOptionPane.showInputDialog(null, "Choose your region", "SIGN UP", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Attica" );
                        try {
                                if (bloodtype.equals(null)) {
                                        JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                } else {
                                        flag = false;
                                }
                        } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                        }
                }

		
		// Hospital's blood limit
		for (int i = 0;i<=7; i++) {
			bloodtypeLimit[i] = bloodLimit(i);
		}
			
		//password
		flag = true;
		do {
			String password = JOptionPane.showInputDialog(null,"Enter your password: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				flag = false;
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Your password must contain both letters and numbers", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);

			}
			
		}while(flag);
			
		
		
		Object CurrentHospitalData[] = new Object[5];
		//a list that contains the current hospital's data that are about to be a part of the "Hospitals" list
		
		CurrentHospitalData[0] = fullname;
		CurrentHospitalData[1] = username;
		CurrentHospitalData[2] = phonenumber;
		CurrentHospitalData[3] = Address;
		CurrentHospitalData[4] = bloodtypeLimit;
		CurrentHospitalData[5] = password;
		
		Hospitals.add(CurrentHospitalData);
		logIn(Hospitals);
	}
	public static void logIn(Object Hospitals) {
		
			boolean flag = true;
			do {
				String hospital_login = JOptionPane.showInputDialog(null,"Welcome!Please type your hospital's username. ", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
				flag = ((String) Hospitals).contains(hospital_login);
				if (flag) {
					boolean flag1 = true;
					do {
						String password_login = JOptionPane.showInputDialog(null,"Enter your password.", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
						flag1 = ((String) Hospitals).contains(password_login);
						if (flag1 == false)
							JOptionPane.showMessageDialog(null,"Wrong password!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					}while (flag1 == false);
				}
					
				else {
					JOptionPane.showMessageDialog(null,"This username is not registered!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
			}while (flag == false);
	}

		
public static void main (String args[]) {
	signUp();
	
}
}	
			
