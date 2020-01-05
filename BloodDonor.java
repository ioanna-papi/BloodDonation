import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *This class implements the volunteers of the application*/
public class BloodDonor {
	static String fullname;
	private static String username;
	static String gender;
	static String bloodtype;
	static String SSN;
	private static String region;
	static String[]  bloodtypes = {"O+", "O-", "A+", "A-" ,"B+" ,"B-" ,"AB+" ,"AB-"}; 
	private static String password;
	static String username_login;
	static String password_login;
	final static Scanner input = new Scanner(System.in);
	private static String[] answers = new String[51];
	
	//custom exception for gender
	public static boolean correctGender(String gender) throws Exception{
		
		if ((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("female"))) {
			return true;
		} else {
			throw new Exception("Please type male or female.");
		}
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
			String username = JOptionPane.showInputDialog(null,"Enter your username: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				if (flag) {
					JOptionPane.showMessageDialog(null, "The username is already used.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				} else {
					flag = false;
				}	
		}while (flag == true);
		
 	        flag = false;
                do {
                        String email = JOptionPane.showInputDialog(null,"Enter your email: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
                                if (flag) {
                                        JOptionPane.showMessageDialog(null, "The email is already used.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
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
			bloodtype = (String) JOptionPane.showInputDialog(null, "Choose your blood type", "SIGN UP", JOptionPane.PLAIN_MESSAGE, null, bloodtypes, "O+" );
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
		Object[] possibilities = {"Attica","South Aegean Sea","North Aegean Sea","Central Greece","West Greece",
			 "Ionian Islands","Thessaly","Peloponnese","Epirus","Eastern Macedonia and Thrace",
			 "Central Macedonia","West Macedonia","Crete"};
		while (flag) {
			String region = (String)JOptionPane.showInputDialog(null, "Choose your region", "SIGN UP", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Attica" );
			try {
				if (region.equals(null)) {
					JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				} else {
					flag = false;
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			} 
		}
		
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

		Messages.connect();
		Statement stmt = dbcon.createStatement();
		ResultSet rs = stmt.executeUpdate("INSERT INTO BloodDonor (B_Name, B_Username, B_email, B_password, Gender, BloodType, SSN, Region)" + 
				"VALUES (fullname, username, email, gender, bloodtype, AMKA, region)");
		rs.close();
		stmt.close();
		Messages.connect().executeUpdate("INSERT INTO BloodDonor (B_Name, B_Username, B_email, B_password, Gender, BloodType, SSN, Region)" +
				"VALUES (fullname, username, email, gender, bloodtype, AMKA, region)");
		Messages.connect().close();
	}
	
	
	public static void logIn(Object BloodDonors) {
		boolean flag;
		do {
			flag = false;
			try {
				ResultSet rs = Messages.connect().executeQuery("SELECT B_Username, B_Password FROM BloodDonor");
				String username_login = JOptionPane.showInputDialog(null,"Welcome! Please type your username", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
				String password_login = JOptionPane.showInputDialog(null,"Enter your password", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
				while(rs.next()){
					if(rs.getString("B_Username").equals(username_login) && rs.getString("B_password").equals(password_login)){
						flag = true;
						break;
					}
				}	
				if(flag == false){
					JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
			 } catch (InputMismatchException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid username or password.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                flag = false;
                         }
	
		}while (flag == false);
	}
	
	/**
	 * This method shows all the questions of the questionnaire*/
	public static void questionnaire() {
		String a;
			try {   
				Messages.connect();
				Statement stmt = dbcon.createStatement();
				int i = 0;
				ResultSet rs = stmt.executeQuery("SELECT * FROM Questionnaire ");
				while (rs.next()) {
					String qid = rs.getString("Q_id");
					String r = rs.getString("Question");
					if (qid.equals("1")) {
						flag = false;
                				do {
                        				try {
                                				a = JOptionPane.showInputDialog(null,  qid + ". " +  r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
                                				flag = correctGender(gender);
                        				} catch (GenderException e1) {
                                				JOptionPane.showMessageDialog(null, e1.getMessage(), "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                        				}
                				}while (flag == false);
		
					} else if ((qid.equals("2")) || (qid.equals("3")) || (qid.equals("4")) || 
						(qid.equals("5")) || (qid.equals("6")) || (qid.equals("7")) || (qid.equals("8")) 
						|| (qid.equals("9")) || (qid.equals("10")) || (qid.equals("11")) || 
						(qid.equals("12")) || (qid.equals("14"))) {
						a = JOptionPane.showInputDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
					} else {
						 a = JOptionPane.showConfirmDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
					}
					answers[i++] = a;
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	

		/**
		 * This method takes the question that the user wants to change the answer to*/
		public static void updateQuestionnaire() {
			int a = 1;
			JOptionPane.showMessageDialog(null,"1.Gender\n2.Last Name\n3.First Name\n 4.Father's Name\n5.Year of Birth\n6.Place of Birth\n7.Profession\n8.ID Number\n9.Address\n"
		 		+ " 10.PostCode\n11.City\n12.Phone Number\n13.Have you ever gave blood before\n14.When was the last time you gave blood?\n"
		 		+ "15.excluded from a blood donation?\n16.dangerous profession or hobby?\n17.previous health problems?\n"
		 		+ "18.jaundice or hepatitis\n19.syphilis\n20.malaria\n21.tuberculosis\n22.rheumatoid arthritis\n23.heart disease\n24.precardiac pan\n25.hypertension\n"
		 		+ "26.convulsions(as an adult)\n27.fainting\n28.stomach aliments\n29.ulcer\n30.other surgeries\n31.kidney diseasea\n32.diabetes\n33.allergies\n"
		 		+ "34.anemia\n35.other diseasess\n36.contagious diseases in your envirnment?\n37.taken medicine?\n"
		 		+ "38.take aspirin?\n39.born or lived or traveled aboard?\n40.lost weight, had fever or swollen tonsils?\n"
		 		+ "41.cornea or scar implant in your eye?\n42.Creutzfeldt-Jakob disease?\n43.growth hormones?\n"
		 		+ "44. tooth extraction or treatment the past week?\n45.vaccines the past week?\n46.surgery or medical examinations the past year?\n"
				+"47.transfusion of blood or blood producers?\n48.tattoo or ear piercing or acupuncture?\n"
		 		+ "49.pierced by a syringe needle\n50.any skin wounds or scratches that came in contact with foreign blood?\n"
		 		+ "51.were you pregnant the past year?\n");
			boolean g = true;
			do {
				try {
					a = Integer.parseInt(JOptionPane.showInputDialog("If you want to change a question press the number of the question or else press 0"));
	            	 		if (a >= 1 && a <= 51) {
	            		 		g = false;
	            		 		changeQuestion (a);
						updateQuestionnaire();
	            	 		} else if (a == 0) {
	            		 		g = true;
	            		 		break;
	            		 	} else {
	            		 		JOptionPane.showMessageDialog(null, "Please enter a valid question number","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
	            	 		}
	           	  	} catch (NumberFormatException e) {
	                     		JOptionPane.showMessageDialog(null, "Please enter a number","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
	                     		g = true;
	             		}
			} while (g);	 
		}
		
		/**
		 * This method changes the answer of the given question*/
		public static void changeQuestion(int qid) {
			boolean flag;
			String a2;
			 if (qid.equals("1")) {
                         flag = false;
                         	do {
                                	try {
                                        	a2 = JOptionPane.showInputDialog(null,  qid + ". " +  r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
                                                flag = correctGender(gender);
                                        } catch (GenderException e1) {
                                        	JOptionPane.showMessageDialog(null, e1.getMessage(), "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                        }
                                }while (flag == false);
                         } else if ((qid.equals("2")) || (qid.equals("3")) || (qid.equals("4")) ||
                         	(qid.equals("5")) || (qid.equals("6")) || (qid.equals("7")) || (qid.equals("8"))
                                || (qid.equals("9")) || (qid.equals("10")) || (qid.equals("11")) ||
                                (qid.equals("12")) || (qid.equals("14"))) {
                                	a2 = JOptionPane.showInputDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
                         } else {
                         	a2 = JOptionPane.showConfirmDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
                         }
			answers[a-1] = a2;
			return;
		}		
}

