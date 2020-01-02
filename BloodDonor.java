<<<<<<< HEAD

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BloodDonor {
	static String fullname;
	private static String username;
	static String gender;
	static String bloodtype;
	static String SSN;
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
		
		if ((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("female"))) {
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


	/** This method lets users sign up to the application*/
	public static void signUp()  {
		//donor's full name
		 boolean flag = true;
                do {
			try{
				  String fullname = JOptionPane.showInputDialog(null,"Enter your full name: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			} catch (InputMismatchException e) {
		         	JOptionPane.showMessageDialog(null, "Please enter a valid name.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = false;
                	}
		}while (flag == true);

		//donor's username
		flag = false;
		do {
			String fullname = JOptionPane.showInputDialog(null,"Enter your username: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				if (flag) {
					JOptionPane.showMessageDialog(null, "The username is already used.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				} else {
					flag = false;
				}	
		}while (flag == true);
		
		//donor's gender
		flag = false;
		do {
			try {
				String gender = JOptionPane.showInputDialog(null,"Enter your gender: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				flag = correctGender(gender);
			} catch (GenderException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}while (flag == false);
			
		//donor's bloodtype
		flag = true;
		while (flag) {
		bloodtype = (String) JOptionPane.showInputDialog(null, "Blood Types", "Choose your blood type", JOptionPane.PLAIN_MESSAGE, null, bloodtypes, "O+" );
			try {
				if (bloodtype.equals(null)) {
					JOptionPane.showMessageDialog(null, "Please choose your blood type.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				} else {
					flag = false;
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose your blood type.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
		//donor's SSN
		flag = false;
		do {
			String SSN = JOptionPane.showInputDialog(null,"Enter your SSN: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			if (SSN.length() == 11) {
				break;
			} else {
				 JOptionPane.showMessageDialog(null, "Please enter a valid SSN (11 digits)", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = true;
			}
		}while (flag);
		
		
		
		//donor's region
		flag = true;
		do {
			try {
				System.out.println("Enter your region:");
				region = JOptionPane.showInputDialog(null,"Enter your region:", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				flag = false;
			}
			catch (InputMismatchException e4) {
				JOptionPane.showMessageDialog(null, "Please enter a valid region", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
				
		}while(flag);			
		
		//donor's password
		flag = false;
		do {
			String password = JOptionPane.showInputDialog(null,"Enter your password: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
			if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
				flag = true;
				break;
			
			} else {
				JOptionPane.showMessageDialog(null, "Your password must contain both numbers and letters.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				flag = false;
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
			String username_login = JOptionPane.showInputDialog(null,"Welcome! Please type your username", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
			flag = ((String) BloodDonors).contains(username_login);
			if (flag) {
				boolean flag1 = false;
				do {
					String password_login = JOptionPane.showInputDialog(null,"Enter your password", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
					flag1 = ((String) BloodDonors).contains(password_login);
					if (flag1 == false)
						JOptionPane.showMessageDialog(null, "Wrong password.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}while (flag1 == false);
			} else {
				JOptionPane.showMessageDialog(null, "This username is not registered!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}while (flag == false);
	}
		
	
public static void main(String args[]) {
	signUp();
	logIn(BloodDonors);
}
}
	
		 
=======
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BloodDonor {
	
	private static String[] answers = new String[51];
	
	public static void questionnaire() {
		Scanner sc = new Scanner(System.in);
		

			try {
			String url = "jdbc:mysql://localhost:3306/Donation?serverTimezone=UTC";
			            String driver = "com.mysql.cj.jdbc.Driver";
			            String userName = "root";
			            String password = "";
			Connection dbcon ;
			dbcon = DriverManager.getConnection(url, userName, password);
			Statement stmt = dbcon.createStatement();
			int i = 0;
			ResultSet rs = stmt.executeQuery("SELECT * FROM Questionnaire ");
			while (rs.next()) {
			String name = rs.getString("Q_id");
			String r = rs.getString("Question");
			System.out.println(name +" " + r);
			String a = sc.nextLine();
			answers[i] = a;
			i++;
			}
			rs.close();
			stmt.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		}
	


		public static void updateQuestionnaire() {
			Scanner sc = new Scanner(System.in);
			int a;
			System.out.println("1.Sex ,male/female\n2.Last Name\n3.First Name\n 4.Father's Name\n5.Year of Birth\n6.Place of Birth\n7.Profession\n8.ID Number\n9.Address\n"
		 		+ " 10.PostCode\n11.City\n12.Phone Number\n13.Have you ever gave blood before\n14.When was the last time you gave blood?\n"
		 		+ "15.Have you ever been excluded from a blood donation?\n16.Do you have any dangerous profession or hobby?\n17.Have you had any health problems before?\n"
		 		+ "18.jaundice or hepatitis\n19.syphilis\n20.malaria\n21.tuberculosis\n22.rheumatoid arthritis\n23.heart disease\n24.precardiac pan\n25.hypertension\n"
		 		+ "26.convulsions(as an adult)\n27.fainting\n28.stomach aliments\n29.ulcer\n30.kidney disease\n31.diabetes\n32.anemia\n33.allergy\n"
		 		+ "34.What are you allergic to?\n35.contagious disease in your environment?\n36.drug intake?\n37.taken aspirin the last 5 days?\n"
		 		+ "38.born or lived or traveled aboard?\n39.lost weight, had fever or swollen tonsils?\n40.have you ever had a cornea or scar implant in your eye?\n"
		 		+ "41.intake of growth hormone extract?\n42.tooth extraction or treatment the past week?\n43.vaccines the past week?\n"
		 		+ "44.surgery or medical examinations the past year?\n45.transfusion of blood or blood producers?\n46.tattoo or ear piercing or acupuncture\n"
		 		+ "47.piercedby a syringe needle\n48.any skin wounds or sores of yours or mucous membrane of your mouth came in contact with foreign blood?\n"
		 		+ "49.were you pregnant the past year?\n50.have you heard that your family is in danger of developing the Creutzfeldt-Jakob disease?\n");
			a = 1;
		 System.out.println("Which answer you want to change? press the number of the question if you want to exit press 0");
		 a = sc.nextInt();
		 changeQuestion(a);
		 while (a != 0 ) {
		 try {
		     if (a >= 1 && a <= 51) {
			 System.out.println("If you want to change another question press the number of the question or else press 0");
			 a = sc.nextInt();
			 changeQuestion(a);
		     }else { System.out.println("You must type the number of the question");
		     }
		 } catch (InputMismatchException e) { 
			 }
         }
	 
	}
		
		public static void changeQuestion(int a) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Update your answer");
			String a2 = sc.nextLine();
			// χειρισμό εξαιρέσεων (try-catch)
			answers[a-1] = a2;
		}
		
}
 
>>>>>>> 2681ec26951b30a7980868e67a7051d03fd7c180

