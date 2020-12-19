package io.prospace.galaxymerchant.utils;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;
import io.prospace.galaxymerchant.utils.exception.NullMaterialException;
import io.prospace.galaxymerchant.utils.exception.UnrecognizedStringException;

@DisplayName("Test case for intergalactic numeral class")
public class IntergalacticNumeralTest {
	public static final String expectedInvalidNumericMessage = "Romanial Number not valid!";
	public static final String expectedUnrecognizedStringException = "I have no Idea what you are talking about";
	public static final String expectedNullMaterialException = "Material not found!, use \" How much is \" instead";

	
	@Test
	@DisplayName("Glob is I")
    void globisI() throws InvalidNumericException, UnrecognizedStringException {
        assertEquals("I", IntergalacticNumeral.ConvertIntergatalacticToRoman("glob"));
    }
	
	@Test
	@DisplayName("Glob is I")
    void prokIsV() throws InvalidNumericException, UnrecognizedStringException {
        assertEquals("V", IntergalacticNumeral.ConvertIntergatalacticToRoman("prok"));
    }
	
	@Test
	@DisplayName("Glob is I")
    void pishIsX() throws InvalidNumericException, UnrecognizedStringException {
        assertEquals("X", IntergalacticNumeral.ConvertIntergatalacticToRoman("pish"));
    }
	
	@Test
	@DisplayName("Glob is I")
    void tegjIsL() throws InvalidNumericException, UnrecognizedStringException {
        assertEquals("L", IntergalacticNumeral.ConvertIntergatalacticToRoman("tegj"));
    }
	
	//tag::based-on-test-input[]
	@Test
	@DisplayName("test valid how much query")
    void testValidHowMuch() throws InvalidNumericException, UnrecognizedStringException, NullMaterialException {
		Map<String,Object> testMap = IntergalacticNumeral.checkQuery("how much is pish tegj glob glob ?");
        assertThat(testMap.get("value"), is(42.0));
    }
	
	
	@Test
	@DisplayName("test valid how many query #1")
    void testValidHowMany1() throws InvalidNumericException, UnrecognizedStringException, NullMaterialException {
		Map<String,Object> testMap = IntergalacticNumeral.checkQuery("how many credits is glob prok silver ?");
        assertThat(testMap.get("value"), is(68.0));
    }
	
	@Test
	@DisplayName("test valid how many query #2")
    void testValidHowMany2() throws InvalidNumericException, UnrecognizedStringException, NullMaterialException {
		Map<String,Object> testMap = IntergalacticNumeral.checkQuery("how many credits is glob prok gold ?");
        assertThat(testMap.get("value"), is(57800.0));
    }
	
	@Test
	@DisplayName("test valid how many query #3")
    void testValidHowMany3() throws InvalidNumericException, UnrecognizedStringException, NullMaterialException {
		Map<String,Object> testMap = IntergalacticNumeral.checkQuery("how many credits is glob prok iron ?");
        assertThat(testMap.get("value"), is(782.0));
    }
	
	@Test
	@DisplayName("test invalid how much query")
    void testInValidHowMuch() throws InvalidNumericException, UnrecognizedStringException, NullMaterialException {
		Exception exception = assertThrows(UnrecognizedStringException.class, () -> {
			Map<String,Object> testMap = IntergalacticNumeral.checkQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
	    });
		
		 String actualMessage = exception.getMessage();
		 assertTrue(actualMessage.contains(expectedUnrecognizedStringException));
    }
	//end::based-on-test-input[]
}
