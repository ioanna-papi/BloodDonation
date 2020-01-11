import java.sql.*;
import javax.swing.JOptionPane;

public class Messages {
	
	int choice;
	
	public Messages() {
		super();
	}
	
	/**
	 * This method informs a hospital about it's shortage of blood in a specific blood type
	 * @param bloodtype the specific blood type which is lacking in the specific hospital
	 * @param username the hospital's username*/
	public void shortageOfBlood(String bloodtype, String username) {
		String region = null;
                try {
                        Messages.connect();
                        Connection dcon = null;
                        Statement stmt = dbcon.createStatement(); //incorrect syntax of values
                        ResultSet rs = stmt.executeQuery("SELECT Region, Username FROM Hospital WHERE Username ='" + username +"'");
                        while (rs.next()) {
                                region = rs.getString("Region");
                        }
                        stmt.close();
                        Messages.connect().close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
		JOptionPane.showMessageDialog(null, "SHORTAGE OF BLOOD TYPE " + bloodtype, "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
		boolean flag = true;
		while(flag) {
			String[] options = {"Blood borrow ", "Create donation day "};
			int choice = JOptionPane.showOptionDialog (null, "You can ask for blood borrow or create a blood donation day",
					"Message",JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,options,options[1]);
			if (choice == 0) {
				flag = false;
				Messages mes = new Messages();
	 		       	mes.bloodBorrow(region,bloodtype, username); 
			} else if (choice == 1) {
				flag = false;
				Hospital h = new Hospital();
	        		h.makeDonationDay(username);
			} else {
				JOptionPane.showMessageDialog(null, "You have to choose one of the above!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}

	}
	
	/**sends message to hospitals in order to borrow blood to a hospital in the same region
	 * @param region is the region the hospital belongs to
	 * @param bloodtype the type of blood the hospital is lacking
	 * @param username the hospital's username*/
	public void bloodBorrow(String region, String bloodtype, String username) {
		String r = null; 
		try {
                        Messages.connect();
                        Connection dcon = null;
                        Statement stmt = dbcon.createStatement(); //incorrect syntax of values
                        ResultSet rs = stmt.executeQuery("SELECT H_name, Region, Username FROM Hospital WHERE Username ='" + username +"'");
			while (rs.next()) {
				String name = rs.getString("H_name");
				r = rs.getString("Region");
				if (r.equals(region)) {
					//display message to hospitals
					JOptionPane.showMessageDialog(null, name + " has blood type " + bloodtype+ " shortage.", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
			}
                        stmt.close();
                        Messages.connect().close();
                } catch (Exception e) {
                        e.printStackTrace();
                }	
	}
}

