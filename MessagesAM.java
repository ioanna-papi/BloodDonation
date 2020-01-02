import java.sql.*;
public class Messages{

	public Messages() {
		super();
	}
	public void donationCalendar() {
		try {
			//get a connection to db
		    String url = "jdbc:sqlserver://195.251.249.161:1433;databaseName = DB56;user = G520;password = 94we99494";
		    //String driver = "com.mysql.cj.jdbc.Driver";

			Connection dbcon ;
			dbcon = DriverManager.getConnection(url);
			Statement stmt = dbcon.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DonationDays");
			ResultSet RS = stmt.executeQuery("SELECT * FROM BloodDonor");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			int i = 1;
			int d ,m;
			while ((rs.next())) {
				Date d_date = rs.getDate(i);
				String strDate = formatter.format(d_date);
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
				i++;
				rs.close();
				RS.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
	
