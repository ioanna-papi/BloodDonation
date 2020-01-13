import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class changeQuestionTest {

	@Test
	void test() {
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
		fail("Not yet implemented");
	}

}

