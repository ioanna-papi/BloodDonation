import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.lang.NullPointerException;
import java.lang.NumberFormatException;

public class Hospital {
	
	//declaring the variables
	static String username;
	static String fullname;
	static String phonenumber;
	static double LimitInLiters;
	static String password;
	static String Address;
	static String region;
	static String hospital_login;
	static String password_login; 
	
	/**
	 * This method asks for confirmation about the given blood limit*/
	public static boolean Answer() {
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure this is the correct amount?" , "Blood Bank Update", JOptionPane.PLAIN_MESSAGE);
		 if (answer == JOptionPane.YES_OPTION) {
			return true;
		}	
		else {
			return false;
		}
	}
	
	/**
	 * This method checks if the given blood limit is valid, meaning blood limit is a positive number
	 * @param b is the given bloodtype*/
	public static double limitLiters(String b) {
         double temp = 0;
         boolean flag = true;
         do{
        	 do {
        		 try {
        			String t = JOptionPane.showInputDialog(null, "Please enter the limit amount of blood for blood type " + b + " :");
                     		temp = Double.valueOf(t);
                     		if (temp < 0) {
                    			flag = false;
                         		JOptionPane.showMessageDialog(null, "Please enter a positive number", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                         		break;
                     		}
        		 } catch (InputMismatchException e) {
        			 JOptionPane.showMessageDialog(null,"Please enter a number","ALERT MESSAGE",JOptionPane.WARNING_MESSAGE);
        		 }
        		 
        	 } while (flag);
         }while (temp<0);
         return temp;
	 }

	/**
	 * This method checks if the given blood limit is a number
	 * @param b is the given blood type
	 * @param username is the hospital's username*/
        public static void bloodLimit(String b, String username) {
         	boolean flag = true;
		double temp = 0;
         	do {
        		try {
        			temp = limitLiters(b);
                 		if (Answer() == true) {
                			LimitInLiters = temp;
                     			flag = false;
                 		} else {
                			LimitInLiters = limitLiters(b);
                			break;
                		}
             		} catch(InputMismatchException e2) {
                		JOptionPane.showMessageDialog(null, "The input has to be a number!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                		flag = true;
             		} catch (NumberFormatException e) {
                		JOptionPane.showMessageDialog(null, "The input has to be a number!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                		flag = true;
             		} catch (NullPointerException e) {
                		flag = true;
             		}
         	}while (flag);
		//Add hospital's blood type limit to the database
                try {
                        Messages.connect();
                        Connection dbcon = null;
                        Statement stmt = dbcon.createStatement();
                        int rs = stmt.executeUpdate("INSERT INTO BloodLimits (H_Username, BloodType, BloodLimit)" +
                                        "VALUES ('" + username + "', '" + b + "', '" + LimitInLiters +  "')");

                        stmt.close();
                        Messages.connect().close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return;
        }

	/**
	 * This method checks if the given phone number is type int and up to 10 digits
	 * @param phonenumber  the given phone number that the method checks*/
	 public static boolean correctPhonenumber(String phonenumber) {
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

        /**
	 * This method allows users to sign up to the application*/
        public static void signUp(){

                // Hospital's Name
                boolean flag = false;
                do {
                        try {
                                String fullname = JOptionPane.showInputDialog(null,"Enter your hospital's name: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
                        	flag = true;
				if (fullname.equals(null)) {
					throw new NullPointerException();
				}
			}
                        catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid hospital name.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                flag = false;
                        }
                }while (flag == false);

                //Hospital's username
                flag = false;
                do {
                        try {
                        	username = JOptionPane.showInputDialog(null,"Enter your hospital's username: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
                        	flag = true;
				if (username.equals(null)) {
					throw new NullPointerException();
				}
			}	
                        catch (NullPointerException e) {
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
                                if (region.equals(null)) {
                                        JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                                } else {
                                        flag = false;
                                }
                        } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Please choose your region.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                        }
                }

                //password
                flag = true;
                do {
                        try {
				password = JOptionPane.showInputDialog(null,"Enter your password: ", "SIGN UP", JOptionPane.INFORMATION_MESSAGE);
                        	if (password.matches("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$")) {
                                	flag = false;
                                	break;
                        	} else {
                                	JOptionPane.showMessageDialog(null, "Your password must contain both letters and numbers", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                        	}
				if (username.equals(null)) {
                        		throw new NullPointerException();
                        	}
			} catch (NullPointerException e) {
				flag = true;
                		JOptionPane.showMessageDialog(null, "Please enter your password", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}	

                }while(flag);


                //Add hospital's credentials to the database
                try {
			Messages.connect();
			Connection dbcon = null;
			Statement stmt = dbcon.createStatement();
			int rs = stmt.executeUpdate("INSERT INTO Hospital (Username, H_name, H_pass, Telephone, Address, Region)" + 
					"VALUES ('" + username + "', '" + fullname + "', '" + password + "', '" + phonenumber + "', '" + Address + "', '" + region +  "')");
			
			stmt.close();
			Messages.connect().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		 // Hospital's blood limit
                String a = null;
                        try {
                                Messages.connect();
                                Connection dbcon = null;
                                Statement stmt = dbcon.createStatement();
                                ResultSet rs = stmt.executeQuery("SELECT * FROM Bloodtypes, BloodLimits WHERE" +
                                                "Bloodlimits.BloodType = Bloodtypes.bloodtype AND BloodLimits.H_Username = '" + username +"'");
                                while (rs.next()) {
                                        String b = rs.getString("bloodtype");
                                        bloodLimit(b, username);
                                }
                                rs.close();
                                stmt.close();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

		return;
        }
	
	/**
	 * This method lets hospitals log in to their account*/
	public static String logIn() {
		boolean flag;
                do {
                        flag = false;
                        try {
                                ResultSet rs = Messages.connect().executeQuery("SELECT Username, H_Pass FROM Hospital");
                                hospital_login = JOptionPane.showInputDialog(null,"Welcome! Please type your username", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
                                password_login = JOptionPane.showInputDialog(null,"Enter your password", "LOG IN", JOptionPane.INFORMATION_MESSAGE);
                                while (rs.next()){
                                        if (rs.getString("Username").equals(hospital_login) && rs.getString("H_pass").equals(password_login)){
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
		insertBloodBankStock(username);
                return hospital_login;

	}

	/**
	 * This method initializes table BloodBankStock in the data base
	 * for the specific hospital
	 * @param username is the hospital's username*/
	public static void insertBloodBankStock(String username) {
		Double blood = 0.0;
		try {
                                Messages.connect();
                                Connection dbcon = null;
                                Statement stmt = dbcon.createStatement();
                                ResultSet rs = stmt.executeQuery("INSERT INTO BloodBankStock (H_Username, BloodType, Blood)" +
                                        "VALUES ('" + username + "', '" + bloodtype + "', '" + blood +  "')");

                                stmt.close();
                                Messages.connect().close();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
	}
	
	/**This method lets hospitals update their blood bank stock
	 *@param username is the hospital's username*/
	public static void bloodBankStock(String username) {

		//Asking for BloodBank Update
		int update;
		String b;
		do {
			update = JOptionPane.showConfirmDialog(null, "Would you like to update your blood-bank?", "BLOOD-BANK UPDATE",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while ((update != JOptionPane.YES_OPTION) && (update != JOptionPane.NO_OPTION));

		//Updating BloodBank-Stock
		if (update == JOptionPane.YES_OPTION) {

			//Asking which bloodType to update
			Object[] types = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
			String type_update;
			do {
				type_update = (String)JOptionPane.showInputDialog(null, "BLOOD TYPE UPDATE","Choose a blood-type stock to update",
						JOptionPane.PLAIN_MESSAGE, null, types, "A+");
			} while (type_update != "A+" && type_update != "A-" && type_update != "AB+" && type_update != "AB-" && 
					type_update != "B+" && type_update != "B-" && type_update != "0+" && type_update != "0-");

			//Asking for blood INCOME or OUTCOME
			String[] kind = {"INCOME", "OUTCOME"};
			int option;
			do {
				option = JOptionPane.showOptionDialog(null,"Choose the kind of the update", null, JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
			} while ((option != 0) && (option != 1));

			//Asking for the blood-amount
			double amount = 0;
         		do {
                 		try {
                         		amount = Double.parseDouble(JOptionPane.showInputDialog("Insert the amount of blood in liters: "));
                 		} catch (NullPointerException e1) {
                         		JOptionPane.showMessageDialog(null, "Please enter the amount of blood","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                 		} catch (NumberFormatException e2) {
                	     		JOptionPane.showMessageDialog(null, "Please enter a positive number","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);               
                 		}
         		} while (amount <= 0);

			//Updating the bloodStock
         		 try {
                     		Messages.connect();
                     		Connection dbcon = null;
                     		Statement stmt = dbcon.createStatement();
                     		ResultSet rs = stmt.executeQuery("SELECT * FROM Bloodtypes, BloodLimits WHERE" +
                                     "BloodLimits.BloodType = Bloodtypes.bloodtype AND BloodLimits.H_Username = '" + username +"'");
                     		while (rs.next()) {
                             		b = rs.getString("bloodtype");
                             		if (b.equals(type_update)) {
                            	 		updateBloodBankStock(option, amount, b, username);
             				}
                     		}	
                     		rs.close();
                     		stmt.close();
         		 } catch (Exception e) {
                     		e.printStackTrace();
         		 }				
		}
	}	
	

		/**
		 * This method changes hospital's blood bank stock, given the option
		 * @param option is the king of update; income  or outcome
		 * @param amount is the amount of blood to be subtracted or added to the blood bank stock
		 * @param bloodtype is the type of blood
		 * @param username is the hospital' username*/
		public static void updateBloodBankStock(int option, Double amount, String bloodtype, String username) {
			Double b, blood = null;
			try {
                		Messages.connect();
                		Connection dbcon = null;
                		Statement stmt = dbcon.createStatement();
                		ResultSet rs = stmt.executeQuery("SELECT * BloodLimits, BloodBankStock WHERE BloodLimits.BloodType = BloodBankStock.BloodType" +
                                	"AND BloodLimits.H_Username = BloodBankStock.H_Username AND BloodLimits.BloodType = '" 
                			+ bloodtype + "' AND BloodLimits.H_Username = '" + username +"'");
                		while (rs.next()) {
                			blood = rs.getDouble("Blood"); 
                        		b = rs.getDouble("BloodLimit");
                        		if (option == 0) {
            					blood += amount;
            				} else {
            					blood -= amount;
            				}
                        	update(username, bloodtype, blood);
            			//Checking if the bloodStock is under the allowed limit of its bloodType
            			if(blood <= b){
            			// Showing WARNING message
            				Messages m = new Messages();
            				m.shortageOfBlood(bloodtype, username);
            		}
                }
                rs.close();
                stmt.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}	
	}

		/**
		 * This method updates the data base with the current blood bank stock
		 * @param username is the hospital's username
		 * @param bloodtype is the given bloodtype
		 * @param blood is the current blood bank stock of the given blood type*/
		public static void update (String username, String bloodtype, Double blood) {
			try {
				Messages.connect();
                		Connection dbcon = null;
                		Statement stmt = dbcon.createStatement();
                		ResultSet rs = stmt.executeQuery("UPDATE BloodBankStock  SET H_Username = '" + username + "', BloodType ='" 
						+ bloodtype + "', Blood = '" + blood +  "' WHERE H_Username = '"
						+ username + "' AND BloodType ='" + bloodtype + "'");

                		stmt.close();
                		Messages.connect().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	

	/**This method lets hospitals create their own donation day*/
	public static String makeDonationDay(String username) {
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
                String name = username;
		return date + name;
	}
	
}	
			
