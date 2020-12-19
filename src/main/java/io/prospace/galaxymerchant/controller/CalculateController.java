package io.prospace.galaxymerchant.controller;
import java.util.HashMap;
import java.util.Map;

/**
 * @author azzuarezh
 *
 */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.prospace.galaxymerchant.utils.IntergalacticNumeral;
import io.prospace.galaxymerchant.utils.Response;
import io.prospace.galaxymerchant.utils.RomanNumeral;
import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;
import io.prospace.galaxymerchant.utils.exception.NullMaterialException;
import io.prospace.galaxymerchant.utils.exception.UnrecognizedStringException;

@RestController
public class CalculateController {
	@PostMapping("/api/calculate")
	public Response calculateValue(@RequestBody String input) {
		System.out.println("input from front-end:" + input);
		Response res = new Response();
		try {
			Map<String, Object> data = IntergalacticNumeral.checkQuery(input);			
			res.setData(data);
			res.setStatus(0);
		}
		catch(InvalidNumericException ex) {
			ex.printStackTrace();
			Map<String,Object> exceptionMap = new HashMap<>();
			exceptionMap.put("error", ex.getMessage().toString());
			res.setData(exceptionMap);
			res.setStatus(-1);
		} 
		catch (UnrecognizedStringException e) {
			e.printStackTrace();
			Map<String,Object> exceptionMap = new HashMap<>();
			exceptionMap.put("error", e.getMessage().toString());
			res.setData(exceptionMap);
			res.setStatus(-1);
		} 
		catch(NullMaterialException ne) {
			ne.printStackTrace();
			Map<String,Object> exceptionMap = new HashMap<>();
			exceptionMap.put("error", ne.getMessage().toString());
			res.setData(exceptionMap);
			res.setStatus(-1);
		}
		return res;
	}
}
