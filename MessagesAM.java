import ava.sql.*;

public class Messages{
	
	public Messages(){
		super();
	}

	public static Statement connect() {
		String url = "jdbc:sqlserver://195.251.249.161:1433;databaseName = DB56;user = G520;password = 94we99494;
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
	
	public void donationDay(String date){
		try {
			ResultSet rs = Messages.connect().executeQuery("SELECT * FROM BloodDonor");
			ResultSet RS = Messages.connect().executeQuery("SELECT * FROM DonationDays");
			String day_name = JOptionPane.showInputDialog(null, "Enter the Donation Day Name",
			                "MAKE NEW DONATION DAY", JOptionPane.PLAIN_MESSAGE);
			for (;;) {
				try {
					String don_date = JOptionPane.showInputDialog(null, "Enter the Donation Date",
					                "MAKE NEW DONATION DAY", JOptionPane.PLAIN_MESSAGE);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date newdate = formatter.parse(don_date);
					break;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a date yyyy-MM-dd.",
					                "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
				}
			}
			rs.close();
			RS.close();
			Messages.connect().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void donationCalendar() {
		try {
			ResultSet rs = Messages.connect().executeQuery("SELECT * FROM DonationDays");
			ResultSet RS = Messages.connect().executeQuery("SELECT * FROM BloodDonor");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			int d ,m;
			while ((rs.next())) {
				Date d_date = rs.getDate("D_Date");
				String strDate = formatter.format(date);
				String strDateDay = strDate.substring(8);
				String strDateMonth = strDate.substring(5,6);
				String strDateYear = strDate.substring(0,3);
				d = Integer.parseInt(strDateDay);
				m = Integer.parseInt(strDateMonth);
				if (d-5 <= 0) {
					if(m == 2 || m == 4 || m == 6 || m == 9 || m == 11) {
						d = 30 + (d-5);
						m -= 1;
					} else {
						d = 31 + (d-5);
						if(m == 1){
							m = 12;
						}else{
							m -= 1;
						}
					}
				}
				String strd = Integer.toString(d); 
				String strm = Integer.toString(m);
				if (d < 10) {
					 strd = "0" + d;	 
				}
				if (m < 10) {
					 strm = "0" + m;
				}   
				String messageDate = strDateYear + "-" + strm + "-" + strd;
				if (formatter.format(date).equals(messageDate)) {
					String day = rs.getString(i);
					JOptionPane.showMessageDialog(null, day, strDate, JOptionPane.INFORMATION_MESSAGE);					
				}
				rs.close();
				RS.close();
				Messages.connect().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
