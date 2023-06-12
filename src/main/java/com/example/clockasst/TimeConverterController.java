package com.example.clockasst;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/time")
public class TimeConverterController {

	@GetMapping("/{time}")
	public String convertTimeToWords(@PathVariable String time) {

		if (time.equals("12:00"))
			return "It's Midday";
		else if (time.equals("00:00"))
			return "It's Midnight";
		else {

			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);

			String hoursInWords = convertToWords(hours);
			String minutesInWords = convertToWords(minutes);

			return "It's " + hoursInWords + " " + minutesInWords;
		}
	}

	private static final Map<Integer, String> numberMap = new HashMap<>();

	static {
		numberMap.put(0, "zero");
		numberMap.put(1, "one");
		numberMap.put(2, "two");
		numberMap.put(3, "three");
		numberMap.put(4, "four");
		numberMap.put(5, "five");
		numberMap.put(6, "six");
		numberMap.put(7, "seven");
		numberMap.put(8, "eight");
		numberMap.put(9, "nine");
		numberMap.put(10, "ten");
		numberMap.put(11, "eleven");
		numberMap.put(12, "twelve");
		numberMap.put(13, "thirteen");
		numberMap.put(14, "fourteen");
		numberMap.put(15, "fifteen");
		numberMap.put(16, "sixteen");
		numberMap.put(17, "seventeen");
		numberMap.put(18, "eighteen");
		numberMap.put(19, "nineteen");
		numberMap.put(20, "twenty");
		numberMap.put(30, "thirty");
		numberMap.put(40, "forty");
		numberMap.put(50, "fifty");
		numberMap.put(60, "sixty");
		numberMap.put(70, "seventy");
		numberMap.put(80, "eighty");
		numberMap.put(90, "ninety");
	}

	private String convertToWords(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Number must be non-negative.");
		}

		if (number == 0) {
			return numberMap.get(0);
		}

		StringBuilder words = new StringBuilder();

		if (number >= 1000) {
			words.append(convertToWords(number / 1000)).append(" thousand ");
			number %= 1000;
		}

		if (number >= 100) {
			words.append(convertToWords(number / 100)).append(" hundred ");
			number %= 100;
		}

		if (numberMap.containsKey(number)) {
			words.append(numberMap.get(number));
		} else {
			int tens = (number / 10) * 10;
			int units = number % 10;
			words.append(numberMap.get(tens)).append("-").append(numberMap.get(units));
		}

		return words.toString();
	}

}
