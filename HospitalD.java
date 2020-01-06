import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Hospital {

	String[] bloodtype = {"A+","A-","AB+","AB-","B+","B-","0+","O-"};

    public void bloodBankStock() {

		//Asking for BloodBank Update
		int update;
		do {
			update = JOptionPane.showConfirmDialog(null, "Would you like to update your blood-bank?", "BLOOD-BANK UPDATE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while ((update != JOptionPane.YES_OPTION) && (update != JOptionPane.NO_OPTION));

		//Updating BloodBank-Stock
		if (update == JOptionPane.YES_OPTION) {

			//Asking which bloodType to update
			Object[] types = {"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
			String type_update;
			do {
				type_update = (String)JOptionPane.showInputDialog(null, "BLOOD TYPE UPDATE","Choose a blood-type stock to update", JOptionPane.PLAIN_MESSAGE, null, types, "A+");
		    } while (type_update != "A+" && type_update != "A-" && type_update != "AB+" && type_update != "AB-" && type_update != "B+" && type_update != "B-" && type_update != "0+" && type_update != "0-");

			//Asking for blood INCOME or OUTCOME
			String[] kind = {"INCOME", "OUTCOME"};
			int option;
			do {
				option = JOptionPane.showOptionDialog(null,"Choose the kind of the update", null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, kind, kind[0]);
			} while ((option != 0) && (option != 1));

			//Asking for the blood-amound
			double amound = 0;
			do {
				try {
					amound = Double.parseDouble(JOptionPane.showInputDialog("Insert the amound of blood in liters: "));
				} catch (NullPointerException e1) {
				} catch (NumberFormatException e2) {
				}
			} while (amound <= 0);

			//Updating the bloodStock
			for (int i=0; i<=7; i++) {

				if (bloodtype[i]  == type_update) {

					if (option == 0) {
						bloodStock[i] += amound;
					} else {
						bloodStock[i] -= amound;
					}

					//Checking if the bloodStock is under the allowed limit of its bloodType
				   	if(bloodStock[i] <= bloodtypeLimit[i]){ //Maria's part, connection with method signUp in class Hospital
						// Showing WARNING message
						Messages m = new Messages();
						m.shortageOfBlood(type_update); //κλήση μεθόδου shortageOfBlood SOS SOS SOS
					}
				}
			}

		}
	}
}
