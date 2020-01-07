import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Hospital {
	
	//declaring the variables
	static String username;
	static String fullname;
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

	public static boolean Answer() {
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure this is the correct amount?" , "Blood Bank Update", JOptionPane.PLAIN_MESSAGE);
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
                                if (region.equals(null)) {
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


                //Add hospital's credentials to the database
                try{} catch (){}
                logIn();
        }

	public static void logIn() {
		
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
			
