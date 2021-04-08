package examReplacementCW;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;

import org.junit.jupiter.api.Test;

class ConnectionTesting {

	@Test
	void test() {
		DatabaseConnection db = new DatabaseConnection();
		Statement AO = db.connection();
		assertEquals(null, AO);

	}

}
