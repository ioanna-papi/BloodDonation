import javax.swing.JOptionPane;
import java.lang.NullPointerException;
import java.lang.NumberFormatException;

public class Hospital {


	/**This method lets hospitals update their blood bank stock*/
	public void bloodBankStock(String username) {

		//Asking for BloodBank Update
		int update;
		do {
			update = JOptionPane.showConfirmDialog(null, "Are you sure you want to update your blood-bank?", "CONFIRMATION",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while ((update != JOptionPane.YES_OPTION) && (update != JOptionPane.NO_OPTION));

		//Updating BloodBank-Stock
		if (update == JOptionPane.YES_OPTION) {

			//Asking which bloodType to update
			Object[] types = {"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
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
				option = JOptionPane.showOptionDialog(null,"Choose the cause of the change: ", null, JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
			} while ((option != 0) && (option != 1));

			//Asking for the blood-amount
			double amount = 0;
         		do {
                 		try {
                         		amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount of blood in liters: "));
                 		} catch (NullPointerException e1) {
                         		JOptionPane.showMessageDialog(null, "Please enter the amount of blood","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
                 		} catch (NumberFormatException e2) {
                	     		JOptionPane.showMessageDialog(null, "Please enter a positive number","ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);               
                 		}
         		} while (amount <= 0);

			//Updating the bloodStock
			updateBloodBankStock(option, amount, type_update, username);
		} else {
	                JOptionPane.showMessageDialog(null, "You are going to continue without any changes made", "CANCELED UPADATE", JOptionPanE.INFORMATION_MESSAGE );
			HomeMenu.printHospitalMenu();
		}
	}

	/** *This method changes the blood bank stock, given the option */
	public static void updateBloodBankStock(int option, double amount, String bloodtype, String username) {
		
		double blood, b; //connection with database in file Hospital.java

		if (option == 0) {
			blood += amount;
		} else {
			blood -= amount;
		}

		//Checking if the bloodStock is under the alowed limit
		if (blood < b) {
			//Showing warning message
			Messages.shortageOfBlood(bloodtype, username);
		}
	}
}
