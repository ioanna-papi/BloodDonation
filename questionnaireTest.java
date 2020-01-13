import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class questionnaireTest {

	@Test
	void test() {
		boolean flag = true;
		String a = null;
			try {   
				Messages.connect();
				Connection dbcon = null;
				Statement stmt = dbcon.createStatement();
				int i = 0;
				ResultSet rs = stmt.executeQuery("SELECT * FROM Questionnaire ");
				while (rs.next()) {
					int qid = rs.getInt("Q_id");
					String r = rs.getString("Question");
					do {
						try {
							if (qid == 1) {
								flag = false;
 	    							Object[] opt = {"Male", "Female"};
								do {
									try {
										int g = JOptionPane.showOptionDialog(null,"Choose your gender: ", "SIGN UP", JOptionPane.YES_NO_OPTION,
												JOptionPane.PLAIN_MESSAGE, null, opt, null);
										if (g == 0) {
											gender = "male";
										} else if (g == 1){
											gender = "female";
										} else {
											throw new NullPointerException();
										}
										flag = false;
									} catch (NullPointerException e1) {
										JOptionPane.showMessageDialog(null, "Please choose your gender", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
									}
								}while (flag == false);
							} else if (((qid >= 2) && (qid<=12)) || (qid == 14)) {
								a = JOptionPane.showInputDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.PLAIN_MESSAGE);
								if (a.equals(null)) {
									throw new NullPointerException();
								} else {
									flag = false;
								}
							} else {
								int p = JOptionPane.showConfirmDialog(null, qid + ". " + r, "QUESTIONNAIRE", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						 		a = String.valueOf(p);
						 		if (p == -1) {
						 			throw new NullPointerException();
						 		} else {
						 		flag = false;
						 		}
							}
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(null, "Please insert your answer", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
							flag = true;
						}
					} while (flag);
					if (checkQuestion(qid, a) == false) {
						JOptionPane.showMessageDialog(null, "We regret to inform you that you are not compatible as a blood donor.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					} else {
						answers.add(a);
					}
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		fail("Not yet implemented");
	}

}

