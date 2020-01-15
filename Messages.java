import javax.swing.JOptionPane;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
/**
 * This class displays messages to blood donors and hospitals*/
public class Messages{
	
	/**
	 * This method creates a connection to the data base*/
	public static Statement connect() {
		String url = "jdbc:sqlserver://195.251.249.161:1433;" + "databaseName=DB20;user=G520;password=94we99494;";
		Connection dbcon;
		Statement stmt = null;
		try {
			dbcon = DriverManager.getConnection(url);
			stmt = dbcon.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	/**
	 * This method informs volunteers about a specific donation day a hospital from their region has created
	 * @param date the donation day date the hospital has created
	 * @param username is the hospital's username*/
	 public static void donationDay(String date, String username) {
         String region = null;
         try {
                 int r =Messages.connect().executeUpdate("INSERT INTO HospitalDonation (H_Username, Region, Don_Date)" + "VALUES('" + username + "', '"
                                 + getRegion(username) + "', '" + date + "')");

                 Messages.connect().close();
         } catch (Exception e) {
                 e.printStackTrace();
         }
         return;
 }

	/**This method shows notification to Blood Donors the day before a Blood Donation created by a Hospital*/
        public static void donationDayDisplay() {
                try {
                	String region = null, name = null;
                    ResultSet rs = Messages.connect().executeQuery("SELECT * FROM Hospital");
                    ResultSet RS = Messages.connect().executeQuery("SELECT * FROM HospitalDonation");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date today = new Date();
                    while (RS.next()) {
                    	Date d_date = RS.getDate("Don_Date");
                     	Date messageDate = d_date;
                     	long diff = BloodDonor.getDateDiff(today, d_date, TimeUnit.DAYS);
                        if (diff == 0) {
                    		while(rs.next()){
                    			if(rs.getString("Username").equals(RS.getString("H_Username"))){
                                    name = rs.getString("H_name");
                                    String message = "Tommorow (" +  messageDate +" ), " 
                                                   + "go to the donation section of "  + name + " Hospital and donate blood!";
                                    JFrame dialogExample = new DialogExample(message);
                                    dialogExample.setVisible(true);
                                    break;
                    			}

                    		}
                    	}
                    }
                    RS.close();
                    rs.close();
                    Messages.connect().close();
                } catch (Exception e) {
                	e.printStackTrace();
                }
                return;
        }


        /**
         * This method informs all volunteers about the default donation days*/
        public static void donationCalendar() {
                try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (java.lang.ClassNotFoundException e) {
                        System.out.print("ClassNotFoundException: ");
                        System.out.println(e.getMessage());
                }
                try {
                     	ResultSet rs = Messages.connect().executeQuery("SELECT * FROM DonationDays");
                        Date date = new Date();
                        while ((rs.next())) {
                                Date d_date = rs.getDate("D_Date");
                                long diff = BloodDonor.getDateDiff(date, d_date, TimeUnit.DAYS);
                                if (diff == 0) {
                                	String day = rs.getString("D_Day");
                                    String message = "Tommorow (" + d_date+ ") is " +  day;
                                    JFrame dialogExample = new DialogExample(message);
                                    dialogExample.setVisible(true);
                                }
                }
                rs.close();
                Messages.connect().close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return;
        }

	/**
	 * This method reutrns hospital's region
	 * @rparam username is the hospital' username */
	public static String getRegion(String username) {
		String region = null;
        try {
                ResultSet rs = Messages.connect().executeQuery("SELECT Region, Username FROM Hospital WHERE Username ='" + username +"'");
                while (rs.next()) {
                        region = rs.getString("Region");
                }
                Messages.connect().close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return region;
	}

		/**
	 * This method informs a hospital about it's shortage of blood in a specific blood type
	 * @param bloodtype the specific blood type which is lacking in the specific hospital
	 * @param username the hospital's username*/
	public static void shortageOfBlood(String bloodtype, String username) {
		JOptionPane.showMessageDialog(null, "SHORTAGE OF BLOOD TYPE " + bloodtype, "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
		boolean flag = true;
		while(flag) {
			String[] options = {"Blood borrow ", "Create donation day "};
			int choice = JOptionPane.showOptionDialog (null, "You can ask for blood borrow or create a blood donation day",
					"Message",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				    null,options,options[1]);
			if (choice == 0) {
				flag = false;
				Messages.bloodBorrow(Messages.getRegion(username),bloodtype, username);
				break;
			} else if (choice == 1) {
				flag = false;
				donationDay(Hospital.makeDonationDay(), username);
				break;
			} else {
				JOptionPane.showMessageDialog(null, "You have to choose one of the above!", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
		}
		return;
	}

	/**sends message to hospitals in order to borrow blood to a hospital in the same region
	 * @param region is the region the hospital belongs to
	 * @param bloodtype the type of blood the hospital is lacking
	 * @param username the hospital's username*/
	public static void bloodBorrow(String region, String bloodtype, String username) {
		String r = null;
		boolean f = true;
		do {
			try { 
	            ResultSet rs = Messages.connect().executeQuery("SELECT H_name, Region, Username FROM Hospital WHERE Username ='" + username +"'");
				while (rs.next()) {
					String name = rs.getString("H_name");
					r = rs.getString("Region");
					if (r.equals(region)) {
						//display message to hospitals
						JOptionPane.showMessageDialog(null, name + " has blood type " + bloodtype+ " shortage. Can you help?", "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
					}
				}
	            Messages.connect().close();
	            f = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (f);
		return;
	}

}
