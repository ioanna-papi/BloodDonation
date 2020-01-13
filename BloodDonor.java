import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 *This class implements the volunteers of the application*/
public class BloodDonor {
	static String fullname;
	private static String username;
	static String gender;
	static String email;
	static String bloodtype;
	static String SSN;
	private static String region;
	static String[]  bloodtypes = {"O+", "O-", "A+", "A-" ,"B+" ,"B-" ,"AB+" ,"AB-"}; 
	private static String password;
	static String username_login;
	static String password_login;
	static ArrayList<String> answers = new ArrayList<String>();

	/** This method lets users sign up to the application*/
	public static void signUp()  {
		//donor's full name
		boolean flag = true;
        	do {
        		try{
        			String fullname = JOptionPane.showInputDialog(null,"Enter your full name: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
        			if (fullname.equals("null")) {
        				throw new NullPointerException();
        			}
        			flag = false;
            		} catch (InputMismatchException e) {
            			JOptionPane.showMessageDialog(null, "Please enter a valid name.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                		flag = true;
            		} catch (NullPointerException e) {
            			JOptionPane.showMessageDialog(null, "Please enter your name.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                		flag = true;
            		}
		}while (flag);

		//donor's username
		flag = true;
		do {
			try {
				ResultSet rs = Messages.connect().executeQuery("SELECT B_Username FROM BloodDonor");
				String username = JOptionPane.showInputDialog(null,"Enter your username: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				if (rs.getString("B_Username").equals(username)) {
					JOptionPane.showMessageDialog(null, "The username is already used.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				} else {
					flag = false;
				}
				if (username.equals("null")) {
        				throw new NullPointerException();
        			}
				flag = false;
			} catch (NullPointerException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Please enter a username.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                		flag = true;
			}	
		}while (flag);
		
		//donor's email
 	    	flag = true;
 	    	do {
 	    		try {
				ResultSet rs = Messages.connect().executeQuery("SELECT B_email FROM BloodDonor");
 	    			email = JOptionPane.showInputDialog(null,"Enter your email: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
 	    			if (rs.getString("B_email").equals(email)) {
 	    				JOptionPane.showMessageDialog(null, "The email is already used.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
 	    			}
 	    			if (email.equals("null")) {
        				throw new NullPointerException();
        			}
        			flag = false;
 	    		} catch (NullPointerException | SQLException e) {
 	    			JOptionPane.showMessageDialog(null, "Please enter your email", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
 	    		}	
            	}while (flag);

	    	//donor's gender
 	    	flag = false;
 	    	Object[] opt = {"Male", "Female"};
		do {
			try {
				int g = JOptionPane.showOptionDialog(null,"Choose your gender: ", "SIGN UP", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, opt, null);
				if (g == 0) {
					gender = "male";
				} else if (g == 1){
					gender = "female";
				} else {
					throw new NullPointerException();
				}
				System.out.println(g + gender);
				flag = true;
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "Please choose your gender", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}while (flag == false);
			
		//donor's bloodtype
		flag = true;
		while (flag) {
			bloodtype = (String) JOptionPane.showInputDialog(null, "Choose your blood type", "SIGN UP", JOptionPane.PLAIN_MESSAGE, null, bloodtypes, "O+" );
			try {
				if (bloodtype.equals(null)) {
					throw new NullPointerException();
				} else {
					flag = false;
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose your blood type.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//donor's SSN
		flag = true;
		do {
			try {
				String SSN = JOptionPane.showInputDialog(null,"Enter your SSN: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				if (SSN.length() == 11) {
					flag = false;
					break;
				} else {
					 JOptionPane.showMessageDialog(null, "Please enter a valid SSN (11 digits)", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					flag = true;
				}
				if (SSN.equals(null)) {
					throw new NullPointerException();
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please enter your SSN", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
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
					throw new NullPointerException();
				} else {
					flag = false;
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			} 
		}
		
		//donor's password
		flag = true;
		do {
			try {
				String password = JOptionPane.showInputDialog(null,"Enter your password: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
				if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
					flag = false;
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Your password must contain both numbers and letters.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					flag = true;
				}
				if (password.equals(null)) {
					throw new NullPointerException();
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please enter your password", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
			
		}while(flag);

		//insert user's data into data base
		try {
			Messages.connect();
			Connection dbcon = null;
			Statement stmt = dbcon.createStatement();
			int rs = stmt.executeUpdate("INSERT INTO BloodDonor (B_Name, B_Username, B_email, B_password, Gender, BloodType, SSN, Region)" + 
				"VALUES ('" + fullname + "', '" + username + "', '" + email + "', '" + gender + "', '" + bloodtype + "', '" + SSN + "', '" + region + "')");
			stmt.close();
			Messages.connect().close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		insertAnswers(username);
	}

	/**
	 * This method inserts donor's answers to the questionnaire into data base
	 * @param username is the donor's username*/
	public static void insertAnswers(String username) {
		String user = username;
		String answer = null;
		String id = null;
        	try {
        		Messages.connect();
            		Connection dbcon = null;
          		Statement stmt = dbcon.createStatement();
          		for (int i = 0; i< 51; i++) {
            			answer = answers.get(i);
            			id = String.valueOf(answers.indexOf(answer)) + 1;
            			int rs = stmt.executeUpdate("INSERT INTO Answers (Q_id, B_Username, Answer)" +
                      	        	"VALUES ('" + id + "', '" + user +"', '" + answer +"')");
           		}
            		stmt.close();
            		Messages.connect().close();
         	} catch (Exception e) {
        		e.printStackTrace();
         	}
	}
	
	/**This method allows users to log in to the appication*/
	public static String logIn() {
		boolean flag;
		do {
			flag = false;
			try {
				ResultSet rs = Messages.connect().executeQuery("SELECT B_Username, B_Password FROM BloodDonor");
				username_login = JOptionPane.showInputDialog(null,"Welcome! Please type your username", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
				password_login = JOptionPane.showInputDialog(null,"Enter your password", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
				while (rs.next()){
					if (rs.getString("B_Username").equals(username_login) && rs.getString("B_password").equals(password_login)){
						flag = true;
						break;
					}
				}	
				if (flag == false){
					JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
			 } catch (InputMismatchException | SQLException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid username or password.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                flag = false;
                         }
	
		}while (flag == false);
		return username_login;
	}
	
	/**
	 * This method lets users answer to the questions of the questionnaire*/
	public static void questionnaire() {
		boolean flag = true;
		String a = null;
			try {   
				Messages.connect();
				Connection dbcon = null;
				Statement stmt = dbcon.createStatement();
				int i = 0;
				ResultSet rs = stmt.executeQuery("SELECT * FROM Questionnaire ");
				while (rs.next()) {
					int qid = rs.getInt("Q_id");
					String r = rs.getString("Question");
					do {
						try {
							if (qid == 1) {
								flag = false;
 	    							Object[] opt = {"Male", "Female"};
								do {
									try {
										int g = JOptionPane.showOptionDialog(null,"Choose your gender: ", "SIGN UP", JOptionPane.YES_NO_OPTION,
												JOptionPane.PLAIN_MESSAGE, null, opt, null);
										if (g == 0) {
											gender = "male";
										} else if (g == 1){
											gender = "female";
										} else {
											throw new NullPointerException();
										}
										flag = false;
									} catch (NullPointerException e1) {
										JOptionPane.showMessageDialog(null, "Please choose your gender", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
									}
								}while (flag == false);
							} else if (((qid >= 2) && (qid<=12)) || (qid == 14)) {
								a = JOptionPane.showInputDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
								if (a.equals(null)) {
									throw new NullPointerException();
								} else {
									flag = false;
								}
							} else {
								int p = JOptionPane.showConfirmDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						 		a = String.valueOf(p);
						 		if (p == -1) {
						 			throw new NullPointerException();
						 		} else {
						 		flag = false;
						 		}
							}
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(null, "Please insert your answer", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
							flag = true;
						}
					} while (flag);
					if (checkQuestion(qid, a) == false) {
						JOptionPane.showMessageDialog(null, "We regret to inform you that you are not compatible as a blood donor.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					} else {
						answers.add(a);
					}
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	
		/**
		 * This method updates data base with the donor's answers to the questionnaire
		 * @param qid is the id of the question
		 * @param username is the donor's username
		 * @param a is the answer to the question*/
		public static void updateTableAnswers(int qid, String username, String a) {
                	try {
                        	Messages.connect();
                        	Connection dbcon = null;
                        	Statement stmt = dbcon.createStatement();
             			int rs = stmt.executeUpdate("UPDATE Answers SET Q_id = '" + qid+ "', B_Username = '" + username + "', Answer = '" + a +"'");
                        	stmt.close();
                        	Messages.connect().close();
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
	            		 		changeQuestion(a, username);
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
		 * This method changes the answer of the given question
		 * @param qid is the id of the question
		 * @param username is the donor's username*/
		public static void changeQuestion(int qid, String username) {
			boolean flag = false;
			String a2 = null;
          		int d = 0;
			do {
				try {
					if ((qid <= 12) || (qid == 14)) {
						a2 = JOptionPane.showInputDialog(null,  qid + " Update your answer", "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
                				flag = true;
					} else {
						d = JOptionPane.showConfirmDialog(null, qid + " Update your answer", "QUESTIONNAIRE", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                				a2 = String.valueOf(d);
                        			flag = true;
                			} 
                			if (a2.equals(null) || (d == -1)) {
                				throw new NullPointerException();
                			}
                		} catch (NullPointerException e1) {
                			JOptionPane.showMessageDialog(null, "Please insert your answer", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                    			flag = false;
                		}
            		}while (flag == false);
			updateTableAnswers(qid, username, a2);
			return;	
		}

		/**This method checks if the users is compatible as a blood donor by his answers
		 * @param qid is the id of the question
		 * @param an is the answer to the question*/
		public static boolean checkQuestion (int qid, String an) {
			boolean flag = false;
			Date date1 = new Date();
			if (qid == 14) {
				try {
					date1=new SimpleDateFormat("yyyy/MM/dd").parse(an);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(System.currentTimeMillis());
				long diff = getDateDiff(date1,date,TimeUnit.DAYS);
				if (diff >= 90){
					flag = true;
				}
			} else if (qid > 14) {
				if (an.equals("1")) {
					flag = true; //if the users answers no, he is compatible
				}
			} else {
				flag = true; //the rest of the answers don't need checking
			}
			return flag;
		}


		/**This method returns the difference between current date and given date
		 * @param date1 is the given date
		 * @param date2 is the current date
		 * @param timeUnit is the selected timeUnit*/
		public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		    long diffInMillies = date2.getTime() - date1.getTime();
		    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		}
}

