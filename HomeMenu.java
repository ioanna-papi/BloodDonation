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
						System.out.println("In which way would you like to use our application?");
						System.out.println("As a hospital or a donor?");
						System.out.println("Press D if you are a Blood Donor or you intend to become");
						System.out.print("Press H if you are one of the Hospitals that organize blood donations or you intend to become");
						String ans = sc.nextLine();
						if (ans.toUpperCase().equals("D")) {
							printDonorMenu();
							f = false;
						} else if (ans.toUpperCase().equals("H")) {
							printHospitalMenu();
							f = false;
						} else {
							f = true;
							throw new InputMismatchException();
						}
					}
			} catch (InputMismatchException ex) {
				System.out.println();
			}
		}
	}
	
	
	private static void printDonorMenu() throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to our application!");
		System.out.println("At first we would like to fill out a questionnaire to learn more about you");
		System.out.println("Press 1 to fill out our questionnaire");
		System.out.print("Press 2 if you have already done it");
		String ans = sc.nextLine();
		switch (ans) {
			case "1":
				BloodDonor.questionnaire();
				System.out.println("Congratulations!");
				System.out.println("You've already done the first step to become a Blood Donor");
				System.out.println("Now press enter to sign up to our application");
				sc.nextLine();
				BloodDonor.signUp();
				printDonorMenu();
			case "2":
				try {
					boolean f = true;
					while (f) {
						System.out.println("Would you like to update your questionnaire?");
						System.out.print("Answer us with a yes or no");
						String choice = sc.nextLine();
						if (choice.toUpperCase().equals("YES") || choice.toUpperCase().contains("Y")) {
							BloodDonor.updateQuestionnaire();
						} else if (choice.toUpperCase().equals("NO") || choice.toUpperCase().contains("N")) {
							System.out.println("Make sure that your answers don't need renewal");
							System.out.print("Press 1 if you changed your mind, else type an other character");
							String c2 = sc.nextLine();
							if (c2.equals("1")) {
								f = false;
							}
							System.out.print("Press enter to login");
							sc.nextLine();
							BloodDonor.login();
						} else {
							f = false;
							throw new InputMismatchException();
						}
					}
				} catch (InputMismatchException e) {
					System.out.println();
				}
			default:
				printDonorMenu();
		}
	}
	
	
	
	private static void printHospitalMenu() throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to our application!");
		System.out.println("Press 1 to sign up");
		System.out.println("Press 2 for login");
		System.out.print("Press 3 to create a new Donation Day");
		String ans = sc.nextLine();
		switch (ans) {
			case "1":
				Hospital.signUp();
				System.out.println("Now we would like to inform us about your blood stock");
				Hospital.bloodBankStock();
				printHospitalMenu();
			case "2":
				Hospital.login();
				System.out.println("Now we would like to inform us about your blood stock");
				System.out.print("Press 1 to inform us, or press another character if you have informed us recently");
				String a = sc.nextLine();
				if (a.equals("1")) {
					Hospital.bloodBankStock();
				}
				System.out.println("If you think you have a shortage of stocks, you can borrow from another hospital");
				System.out.print("Press 1 if you want to ask for help, or press another character");
				String a2 = sc.nextLine();
				if (a2.equals("1")) {
					Messages.bloodBorrow();
				}
				printHospitalMenu();
			case "3":
				Hospital.makeDonationDay();
				System.out.println("Thank you! You help us to strengthen our action");
				printHospitalMenu();
			default:
				printHospitalMenu();	
		}	
		
	}
	
	
	
	
	
	private static void loadObjects() {
		//Δημιουργία αντικειμένων για όλες τις κλάσεις
	}

}

