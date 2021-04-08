package examReplacementCW;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RegistrationTesting {

	@Test
	void test() {

		Register ltest =new Register();
		int result=ltest.userSignup("sabin dangal", "sabin149", "sabin@gmail.com", "1234", "1234");
		assertEquals(1,result);
	}

}
