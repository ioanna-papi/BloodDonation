import javax.swing.JOptionPane;

public class HomeMenu {

	public static void main(String[] args) {
		loadObjects();
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
	
	
	public static void printDonorMenu() {
		JOptionPane.showMessageDialog(null,"Welcome to our application!", "DONOR'S MENU", JOptionPane.PLAIN_MESSAGE);
		String[] q = {"Fill out our questionnaire", "Update questionnaire", "Log in"};
		int ans = JOptionPane.showOptionDialog(null, "Please choose one of the following",
                                                                "DONOR'S MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, q, q[0]);
		switch (ans) {
			case 1:
				BloodDonor.questionnaire();
				JOptionPane.showMessageDialog(null, "Congratulations! You've already done the first step to become a Blood Donor");
				JOptionPane.showMessageDialog(null, "Now press OK to sign up to our application");
				BloodDonor.signUp();
				printDonorMenu();
			case 2:
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
						}
						JOptionPane.showMessageDialog(null, "Press OK to log in");
						BloodDonor.logIn();
					} else {
						f = true;
					}
				}
			default:
				HomeMenu.printDonorMenu();
		}
	}
	
	
	
	public static void printHospitalMenu() {
		JOptionPane.showMessageDialog(null, "Welcome to our application!");
		String[] kind = {"Sign up", "Log in", "Create a new donation day"};
		int ans = JOptionPane.showOptionDialog(null, "Please choose one of the following",
                                                                "HOSPITAL MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
		switch (ans) {
			case 1:
				Hospital.signUp();
				JOptionPane.showMessageDialog(null, "Now we would like you to inform us about your blood bank stock");
				Hospital.bloodBankStock();
				printHospitalMenu();
			case 2:
				Hospital.login();
				Object[] opt = {"Update blood bank stock","Skip this step"};
				int g = JOptionPane.showOptionDialog(null,"Now we would like you to inform us about your blood bank stock", "HOSPITAL MENU", JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, opt, null);
				if (g == 0) {
					Hospital.bloodBankStock();
				}
				String[] k = {"Borrow", "Cancel"};
				int a2 = JOptionPane.showOptionDialog(null, "If you think you have shortage of stock, you can borrow blood from another hospital",
                                                                "BLOOD BORROW", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, k, k[0]);
				if (a2 == 0) {
					Messages.bloodBorrow();
				}
				HomeMenu.printHospitalMenu();
			case 3:
				Hospital.makeDonationDay();
				JOptionPane.showMessageDialog(null, "Thank you! You help us to strengthen our action");
				HomeMenu.printHospitalMenu();
			default:
				HomeMenu.printHospitalMenu();	
		}	
		
	}
}

