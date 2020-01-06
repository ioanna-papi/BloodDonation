import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Hospital {
	
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
	//b = JOptionPane.showConfirmDialog(null, "Are you sure this is the correct amount?" , "Blood Bank Update", JOptionPane.PLAIN_MESSAGE);
	//JOptionPane.showMessageDialog(null, e1.getMessage(), "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
	//a = JOptionPane.showInputDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
	
	public static boolean Answer() {
		String answer = JOptionPane.showConfirmDialog(null, "Are you sure this is the correct amount?" , "Blood Bank Update", JOptionPane.PLAIN_MESSAGE);
		System.out.println(answer);
		
		if (answer.equals("yes")) {
			return true;
		}	
		else {
			return false;
		}
	}
	
	public static double limitLiters(String b) {
		double temp;
		do{
			temp = JOptionPane.showInputDialog(null,"Please enter the limit amount of blood for blood type " + b + " :", "Blood Bakn Update", JOptionPane.PLAIN_MESSAGE);
			if (temp < 0)
				System.err.println("Please enter a positive number");
		}while (temp<0);
		return temp;
			
	}
	
	public static double bloodLimit(int i) throws InputMismatchException{
		 	String b = bloodtype[i];
		 	boolean flag = false;		
		 	do {
				try {
			 		double temp = limitLiters(b);
					flag =  Answer();
					if (flag == true)
						LimitInLiters = temp;
					if (flag == false) 
						LimitInLiters = limitLiters(b);
					break;	
				}
				catch(final InputMismatchException e2) {
					System.err.println("The input has to be a number!");
					final Scanner input1 = new Scanner(System.in);
					double temp = limitLiters(b);
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
	
	/**This method lets hospitals create their own donation day*/
	public String makeDonationDay() {
		boolean flag = true;
		String d = null;
		String m = null;
		String y = null;
		Object[] year = {"2019","2020","2021","2022", "2023","2024","2025","2026","2027","2028","2029"};
		Object[] month = {"01","02","03","04", "05","06","07","08","09","10","11","12"};
		do {
			try {
				y =(String)JOptionPane.showInputDialog(null, "Year", "Choose the year of the donation", JOptionPane.PLAIN_MESSAGE, null, year, "2019" );
	            if (y.equals("null")) {
	            	throw new NullPointerException();
	            }
	            flag = false;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose a year!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = true;
           }
		} while(flag);
		
		do {
			try {
				m =(String)JOptionPane.showInputDialog(null, "Month", "Choose the month of the donation", JOptionPane.PLAIN_MESSAGE, null, month, "01" );
				if (m.equals("null")) {
					throw new NullPointerException();
	             }
				 flag = false;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose a month!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = true;
           }
		} while(flag);
		
		do {
			try {
				if (m == "01" || m == "03" || m == "05" || m == "07" || m == "08" || m == "10" || m == "12") {
					Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                    d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );
				} else if (m == "04" || m == "06" || m == "09" || m == "11") {
                    Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
                    d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );
				} else if (y == "2020" || y == "2024" || y == "2028") {
                    Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
                    d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );
				} else {
                    Object[] day = {"1","2","3","4", "5","6","7","8","9","10", "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
                    d =(String)JOptionPane.showInputDialog(null, "Day", "Choose the day of the donation", JOptionPane.PLAIN_MESSAGE, null, day, "1" );
				}
				if (d.equals("null")) {
                	throw new NullPointerException();
                }
				flag = false;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose a day!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = true;
           }
		} while(flag);
                String date = String.join("-",y, m , d);
                return date;
	}
	
}	
			
