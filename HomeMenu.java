import java.util.Scanner;
import java.util.InputMismatchException;
public class HomeMenu {

	public static void main(String[] args) throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		loadObjects();
		while (true) {
				try {
					boolean f = true;
					while (f) {
						String[] kind = {"Donor", "Hospital"};
						String ans = JOptionPane.showOptionDialog(null, "In which way would you like to use the application?",
								"MAIN MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
						if (ans.equals("0")) {
							HomeMenu.printDonorMenu();
							f = false;
						} else if (ans.equals("1")) {
							HomeMenu.printHospitalMenu();
							f = false;
						} else {
							f = true;
						}
					}
			} catch (NullPointerException ex) {
				System.out.println(ex);
			}
		}
	}
	
	
	private static void printDonorMenu() {
		Scanner sc = new Scanner(System.in);
		JOptionPane.showMessageDialog(null,"Welcome to our application!");
		String[] q = {"Fill out our questionnaire", "Update questionnaire", "Log in"};
		String ans = JOptionPane.showOptionDialog(null, "Please choose one of the following",
                                                                "DONOR'S MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, q, q[0]);
		switch (ans) {
			case "1":
				BloodDonor.questionnaire();
				JOptionPane.showMessageDialog(null, "Congratulations! You've already done the first step to become a Blood Donor");
				JOptionPane.showMessageDialog(null, "Now press OK to sign up to our application");
				BloodDonor.signUp();
				printDonorMenu();
			case "2":
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
						BloodDonor.login();
					} else {
						f = true;
					}
				}
			default:
				HomeMenu.printDonorMenu();
		}
	}
	
	
	
	private static void printHospitalMenu() throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		JOptionPane.showMessageDialog(null, "Welcome to our application!");
		String[] kind = {"Sign up", "Log in", "Create a new donation day"};
		String ans = JOptionPane.showOptionDialog(null, "Please choose one of the following",
                                                                "HOSPITAL MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
		switch (ans) {
			case "1":
				Hospital.signUp();
				JOptionPane.showMessageDialog(null, "Now we would like you to inform us about your blood bank stock");
				Hospital.bloodBankStock();
				printHospitalMenu();
			case "2":
				Hospital.login();
				JOptionPane.showMessageDialog(null, "Now we would like you to inform us about your blood bank stock");
				System.out.print("Press 1 to inform us, or press another character if you have informed us recently");
				String a = sc.nextLine();
				if (a.equals("1")) {
					Hospital.bloodBankStock();
				}
				String[] k = {"Borrow", "Cancel"};
				String a2 = JOptionPane.showOptionDialog(null, "If you think you have shortage of stock, you can borrow blood from another hospital",
                                                                "BLOOD BORROW", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, k, k[0]);
				if (a2.equals("0")) {
					Messages.bloodBorrow();
				}
				HomeMenu.printHospitalMenu();
			case "3":
				Hospital.makeDonationDay();
				JOptionPane.showMessageDialog(null, "Thank you! You help us to strengthen our action");
				HomeMenu.printHospitalMenu();
			default:
				HomeMenu.printHospitalMenu();	
		}	
		
	}
	
	
	
	
	
	private static void loadObjects() {
		//Δημιουργία αντικειμένων για όλες τις κλάσεις
	}

}

