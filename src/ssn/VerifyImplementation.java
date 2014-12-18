package ssn;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

/**
 * Use this class to verify your implementation
 * 
 * There are 14 test-cases
 * 
 * Count how many your implementation fulfills
 * 
 * Do not fix any errors, just count and report the number of failing test-cases
 */
public class VerifyImplementation {

	SwedishSocialSecurityNumber sut;
	SwedishSocialSecurityNumber bornBefore2000;
	SwedishSocialSecurityNumber bornAfter2000;
	
	@Before
	public void setUp() throws Exception {
		sut 	 		= new SwedishSocialSecurityNumber("810504-8303");
		bornAfter2000 	= new SwedishSocialSecurityNumber("140202-0166");
		bornBefore2000 	= new SwedishSocialSecurityNumber("141130+2951");
	}
	
	@Test
	public void shouldReturnCorrectYear() {
		int actual;
	
		actual = sut.getYear();
		assertEquals(1981, actual);
		
		actual = bornAfter2000.getYear();
		assertEquals(2014, actual);
		
		actual = bornBefore2000.getYear();
		assertEquals(1914, actual);
	}
	
	@Test
	public void shouldReturnCorrectMonth() {
		String actual;
		
		actual = sut.getMonth();
		assertEquals("May", actual);
		
		actual = bornAfter2000.getMonth();
		assertEquals("February", actual);
		
		actual = bornBefore2000.getMonth();
		assertEquals("November", actual);
	}
	
	@Test
	public void shouldReturnCorrectDay() {
		int actual;
		
		actual = sut.getDay();
		assertEquals(4, actual);
		
		actual = bornAfter2000.getDay();
		assertEquals(2, actual);
		
		actual = bornBefore2000.getDay();
		assertEquals(30, actual);
	}
	
	@Test
	public void shouldReturnCorrectCode() {
		String actual;
		
		actual = sut.getCode();
		assertEquals("8303", actual);
		
		actual = bornAfter2000.getCode();
		assertEquals("0166", actual);
		
		actual = bornBefore2000.getCode();
		assertEquals("2951", actual);
	}
	
	

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnEmptyInput() {
		new SwedishSocialSecurityNumber("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfNoSeparatorIsUsed() {
		new SwedishSocialSecurityNumber("8105048303");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfThousandsIsUsed() {
		new SwedishSocialSecurityNumber("19810504-8303");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnTooShort() {
		new SwedishSocialSecurityNumber("81050-8303");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnWrongSeparator() {
		new SwedishSocialSecurityNumber("810504_8303");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnMinusAsYear() {
		new SwedishSocialSecurityNumber("-20101-2564");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnMonth0() {
		new SwedishSocialSecurityNumber("120003-2564");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnMonth14() {
		new SwedishSocialSecurityNumber("121403-2564");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnDay0() {
		new SwedishSocialSecurityNumber("121200-2564");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionOnDay33() {
		new SwedishSocialSecurityNumber("121233-2564");
	}
}
