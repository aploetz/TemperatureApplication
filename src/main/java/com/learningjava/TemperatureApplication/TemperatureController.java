package com.learningjava.TemperatureApplication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/temperature")
@RestController
public class TemperatureController {

	private record Temperature(double celsius, double fahrenheit) {};

	@GetMapping("/fahrenheit/from/{tempInCelsius}")
	public ResponseEntity<Temperature> getFahrenheit(
	HttpServletRequest req,
	@PathVariable(value="tempInCelsius") double tempInCelsius) {
		
		double tempInFahrenheit = computeFahrenheit(tempInCelsius);
		Temperature temp = new Temperature(tempInCelsius, tempInFahrenheit);

		return ResponseEntity.ok(temp);
	}
	
	@GetMapping("/celsius/from/{tempInFahrenheit}")
	public ResponseEntity<Temperature> getCelsius(
	HttpServletRequest req,
	@PathVariable(value="tempInFahrenheit") double tempInFahrenheit) {
			
		double tempInCelsius = computeCelsius(tempInFahrenheit);
		Temperature temp = new Temperature(tempInCelsius, tempInFahrenheit);
			
		return ResponseEntity.ok(temp);
	}

	public double computeFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
		}

	public double computeCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9; 
	}

}
