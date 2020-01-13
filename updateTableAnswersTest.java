import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class updateTableAnswersTest {

	@Test
	void test() {
		try {
        	Messages.connect();
        	Connection dbcon = null;
        	Statement stmt = dbcon.createStatement();
			int rs = stmt.executeUpdate("UPDATE Answers SET Q_id = '" + qid + "', B_Username = '" + username + "', Answer = '" + a +"' WHERE B_Username = '" + username + "'");
        	stmt.close();
        	Messages.connect().close();
	} catch (Exception e) {
        	e.printStackTrace();
	}
		fail("Not yet implemented");
	}

}

