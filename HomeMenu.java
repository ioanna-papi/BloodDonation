import javax.swing.JOptionPane;
/**
 * This class displays the main menu of the application
 * and the custom menus of each category of user;
 * Blood donor and Hospital*/
public class HomeMenu {

	public static void main(String[] args) {
		while (true) {
				boolean f = true;
				while (f) {
					String[] kind = {"Donor", "Hospital"};
					int ans = JOptionPane.showOptionDialog(null, "In which way would you like to use the application?",
							"MAIN MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
					if (ans == 0) {
						HomeMenu.printDonorMenu();
						f = false;
					} else if (ans == 1) {
						HomeMenu.printHospitalMenu();
						f = false;
					} else {
						f = true;
						JOptionPane.showMessageDialog(null, "Please choose one of the options", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					}	
				}
		}
	}
	
	/**
	 * This method displays blood donors' main menu,
	 * which includes sign up, log in fill out and update questionnaire*/
	public static void printDonorMenu() {
		String[] q = {"Fill out our questionnaire", "Log in"};
		int ans = JOptionPane.showOptionDialog(null, "Welcome to our application! Please choose one of the following",
                                                                "DONOR'S MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, q, q[0]);
		switch (ans) {
			case 0:
				BloodDonor.questionnaire();
				JOptionPane.showMessageDialog(null, "Congratulations! You've already done the first step to become a Blood Donor");
				JOptionPane.showMessageDialog(null, "Now press OK to sign up to our application");
				BloodDonor.signUp();
				JOptionPane.showMessageDialog(null, "Select log in option in the following menu");
				printDonorMenu();
			case 1:
				String username = (BloodDonor.logIn());
				boolean f = true;
				while (f) {
					int choice = JOptionPane.showConfirmDialog(null, "Would you like to update your questionnaire?");
					if (choice == 0) {
						BloodDonor.updateQuestionnaire();
						f = false;
					} else if (choice == 1) {
						JOptionPane.showMessageDialog(null, "Make sure that your answers don't need renewal");
						int c2 = JOptionPane.showConfirmDialog(null,"Have you changed your mind?");
						if (c2 == 0) {
							HomeMenu.printDonorMenu();
						} else {
							f = false;
						}
					} else {
						f = true;
					}
				}
			default:
				HomeMenu.printDonorMenu();
		}
	}
	
	/**
	 * This method displays hospitals' main menu, 
	 * which inclues sign up, log in, create new donation day,
	 * borrow blood from another hospital and update blood bank stcok*/
	public static void printHospitalMenu() {
		String username = null;
		JOptionPane.showMessageDialog(null, "Welcome to our application!");
		String[] kind = {"Sign up", "Log in"};
		int ans = JOptionPane.showOptionDialog(null, "Please choose one of the following",
                                                                "HOSPITAL MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
		switch (ans) {
			case 0:
				Hospital.signUp();
				username = (Hospital.logIn());
				JOptionPane.showMessageDialog(null, "Now we would like you to inform us about your blood bank stock");
				Hospital.bloodBankStock(username);
				hospitalSecondMenu(username);
			case 1:
				HomeMenu.hospitalSecondMenu(Hospital.logIn());
				HomeMenu.printHospitalMenu();
			default:
				HomeMenu.printHospitalMenu();	
		}	
		
	}
	
	/**
	 * This method displays hospital's second menu,
	 * meaning after they succesfully log in
	 * @param username is the hospital's username*/
	public static void hospitalSecondMenu(String username) {
		Object[] opt = {"Update blood bank stock","Skip this step"};
                int g = JOptionPane.showOptionDialog(null,"Now we would like you to inform us about your blood bank stock", "HOSPITAL MENU", JOptionPane.YES_NO_OPTION,
                                          JOptionPane.PLAIN_MESSAGE, null, opt, null);
                if (g == 0) {
                	Hospital.bloodBankStock(username);
                }
                String[] k = {"Borrow", "Donation Day"};
                int a2 = JOptionPane.showOptionDialog(null, "If you are in need of blood units, you can borrow blood from another"
                                          + " hospital, or make a new doantion day","BLOOD BORROW",
                                      JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, k, k[0]);
                 if (a2 == 0) {
                 	String bloodtype = null;
                        String[] bloodtypes = { "O+" , "O-" , "A+" , "A-" , "B+" , "B-" , "AB+" , "AB-" };
                        boolean flag = true;
                        while (flag) {
                        	bloodtype = (String) JOptionPane.showInputDialog(null, "Choose the blood type",
                                                          "SIGN UP", JOptionPane.PLAIN_MESSAGE, null, bloodtypes , "O+");
                                try {
                                	if (bloodtype.equals(null)) {
                                        	throw new NullPointerException();
                                        } else {
                                        	flag = false;
                                        }
                                } catch (NullPointerException e) {
                                	JOptionPane.showMessageDialog(null, "Please choose the blood type",
                                                                    "ALERT MESSAGE" , JOptionPane.WARNING_MESSAGE);
                                }
                        }
                        Messages.bloodBorrow(Messages.getRegion(username),bloodtype,username);
                 } else if (a2 == 1) {
                 	Messages.donationDay(Hospital.makeDonationDay(),username);
                        JOptionPane.showMessageDialog(null, "Thank you! You help us strengthen our action");
                 }
        }
}

