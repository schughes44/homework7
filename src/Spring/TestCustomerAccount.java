package Spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TestCustomerAccount {
	private CustomerAccount account;
	
	@Mock
	private CustomerAccount spyForAccount;
	
	@Mock
	private CustomerAccountDAO cad;
	
	@BeforeEach
	void setUp() throws Exception {
		account = new CustomerAccount();
		spyForAccount = spy(account);
		cad = mock(CustomerAccountDAO.class); 
	}
	
	@AfterEach
	void tearDown() throws Exception {
		account = null;
		spyForAccount = null;
		cad = null;
	}
	
	@Test
	void test_createNewAccount_shouldThrowNoAccountCreatedExceptionOnAdd() throws SQLException, NoAccountCreatedException {
		// Scott Hughes Test
		String name = "Scott";
		String phone = "6035551234";
		
		when(cad.newAcctNumber(anyString(), anyString())).thenThrow(new SQLException());
		spyForAccount.newCustomerAccountDAO(cad);
		
		try {
			spyForAccount.createNewAccount(name, phone);
			fail("Failed exception");
		} catch (NoAccountCreatedException nae) {
			assertEquals(String.format("Account for %s at %s not created", name, phone), nae.getMessage(), "The exception messages do not match");
		}	
	}
	
	//Monica Test
	@Test
	void TestUpdateCustomerNameThrowsExceptionIfAccountNumberDoesNotExist() throws NoSuchCustomerAccountException {
		// test data
		String name = "Andy";
		String acctNum = "987654321";
		
		try {
			name.UpdateCustomerName(name, acctNum);
		} catch (SQLException se) {
			String expected = String.format(name, acctNum);
		}	
	}
	
	//Alber's Test
	@Test
	void testUpdateCustomerName() throws NoSuchCustomerAccountException, SQLException {
		String actNum = "561836456";
		String name = "Alber";
		
		when(cad).thenThrow(new SQLException());
		spyForAccount.newCustomerAccountDAO(cad);
		
		try {
			spyForAccount.updateCustomerName(actNum, name);
			fail("Failed Exception");
		} catch (NoSuchCustomerAccountException e) {
			assertEquals(String.format("No customer record with acctount number %s ", actNum), e.getMessage(), "The exception message do not match");
		}
	}

}