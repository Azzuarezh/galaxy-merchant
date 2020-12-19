/**
 * 
 */
package io.prospace.galaxymerchant.utils;

/**
 * @author Azzuarezh
 *
 * 2020

 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

import io.prospace.galaxymerchant.utils.RomanNumeral;
import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;

import org.junit.jupiter.api.Test;

@DisplayName("Test case for roman numeral class")
public class RomanNumeralTest {
	public static final String expectedInvalidNumericMessage = "Romanial Number not valid!";

	@Test
	@DisplayName("I is 1")
    void iIsOne() throws InvalidNumericException {
        assertEquals(1, RomanNumeral.convertRomanTextToInt("I"));
    }
	
	@Test
	@DisplayName("IV is 4")
    void ivIsFour() throws InvalidNumericException {
        assertEquals(4, RomanNumeral.convertRomanTextToInt("IV"));
    }
	
	@Test
	@DisplayName("V is 5")
    void vIsFive() throws InvalidNumericException {
        assertEquals(5, RomanNumeral.convertRomanTextToInt("V"));
    }
	
	@Test
	@DisplayName("IX is 9")
    void ixIsNine() throws InvalidNumericException {
        assertEquals(9, RomanNumeral.convertRomanTextToInt("IX"));
    }
	
	@Test
	@DisplayName("X is 10")
    void xIsTen() throws InvalidNumericException {
        assertEquals(10, RomanNumeral.convertRomanTextToInt("X"));
    }
	
	@Test
	@DisplayName("L is 50")
    void lIsFifty() throws InvalidNumericException {
        assertEquals(50, RomanNumeral.convertRomanTextToInt("L"));
    }
	
	@Test
	@DisplayName("XC is 90")
    void xcIsHundred() throws InvalidNumericException {
        assertEquals(90, RomanNumeral.convertRomanTextToInt("XC"));
    }
	
	@Test
	@DisplayName("C is 100")
    void cIsHundred() throws InvalidNumericException {
        assertEquals(100, RomanNumeral.convertRomanTextToInt("C"));
    }
	
	@Test
	@DisplayName("CD is 400")
    void cdIsFourHundred() throws InvalidNumericException {
        assertEquals(400, RomanNumeral.convertRomanTextToInt("CD"));
    }
	
	@Test
	@DisplayName("D is 500")
    void dIsFiveHundred() throws InvalidNumericException {
        assertEquals(500, RomanNumeral.convertRomanTextToInt("D"));
    }
	
	@Test
	@DisplayName("M is 1000")
    void dIsThousand() throws InvalidNumericException {
        assertEquals(1000, RomanNumeral.convertRomanTextToInt("M"));
    }
	
	@Test
	@DisplayName("I only repeated 3x with itself")
    void iiiOnly3times() throws InvalidNumericException {
        assertEquals(3, RomanNumeral.convertRomanTextToInt("III"));
    }
	
	@Test
	@DisplayName("I only repeated 1x with V (IIV is not valid, use III instead)")
    void iOnly1timeswithV() throws InvalidNumericException {
        assertEquals(4, RomanNumeral.convertRomanTextToInt("IV"));
    }
	
	@Test
	@DisplayName("I only repeated 1x with X (IIX is not valid, use VIII instead)")
    void iOnly1timeswithX() throws InvalidNumericException {
        assertEquals(9, RomanNumeral.convertRomanTextToInt("IX"));
    }
	
	@Test
	@DisplayName("X only repeated 3x with itself")
    void xOnly3times() throws InvalidNumericException {
        assertEquals(30, RomanNumeral.convertRomanTextToInt("XXX"));
    }
	
	@Test
	@DisplayName("X only repeated 1x with L (XXL is not valid, use XXX instead)")
    void xOnly1timeswithL() throws InvalidNumericException {
        assertEquals(40, RomanNumeral.convertRomanTextToInt("XL"));
    }
	
	@Test
	@DisplayName("X only repeated 1x with C (XXC is not valid, use LXXX instead)")
    void xOnly1timeswithC() throws InvalidNumericException {
        assertEquals(90, RomanNumeral.convertRomanTextToInt("XC"));
    }
	
	@Test
	@DisplayName("C only repeated 1x with D (CCD is not valid, use CCC instead)")
    void cOnly1timeswithC() throws InvalidNumericException {
        assertEquals(400, RomanNumeral.convertRomanTextToInt("CD"));
    }
	
	@Test
	@DisplayName("C only repeated 1x with M (CCM is not valid, use DCCC instead)")
    void cOnly1timeswithM() throws InvalidNumericException {
        assertEquals(900, RomanNumeral.convertRomanTextToInt("CM"));
    }
	
	@Test
	@DisplayName("M only repeated 3x with itself")
    void mOnly3times() throws InvalidNumericException {
        assertEquals(3000, RomanNumeral.convertRomanTextToInt("MMM"));
    }
	
	// tag::negative-test[]
	@Test
	@DisplayName("I will Error if repeated 4x")
    void i4timesthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("IIII");
	    });

	   
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by X")
    void vSubstractedXthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VX");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by V")
    void vSubstractedVthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VV");
	    });
 
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by L")
    void vSubstractedLthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VL");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by C")
    void vSubstractedCthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VC");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by D")
    void vSubstractedDthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VD");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("V will Error if followed by M")
    void vSubstractedMthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("VM");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("X will Error if followed by D")
    void xSubstractedDthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("XD");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("X will Error if followed by M")
    void xSubstractedMthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("XM");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("L will Error if followed by L")
    void lSubstractedLthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("LL");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("L will Error if followed by C")
    void lSubstractedCthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("LC");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("L will Error if followed by D")
    void lSubstractedDthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("LD");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("L will Error if followed by M")
    void lSubstractedMthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("LM");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("C will Error if repeated 4x")
    void c4timesthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("CCCC");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("D will Error if followed by D")
    void d4timesthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("DD");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	@Test
	@DisplayName("M will Error if repeated 4x")
    void m4timesthrowErr() throws InvalidNumericException {
		Exception exception = assertThrows(InvalidNumericException.class, () -> {
			RomanNumeral.convertRomanTextToInt("MMMM");
	    });

	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedInvalidNumericMessage));
    }
	
	// end::negative-test[]
}
