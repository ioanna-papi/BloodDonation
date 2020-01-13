import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class updateQuestionnaireTest {

	@Test
	void test() {
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
		fail("Not yet implemented");
	}

}

