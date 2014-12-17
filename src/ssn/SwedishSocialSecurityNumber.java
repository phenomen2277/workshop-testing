package ssn;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwedishSocialSecurityNumber {
	private Integer _year;
	private Integer _month;
	private Integer _day;
	private String _code;

	private Pattern _tenDigitPattern;
	private Matcher _matcher;
	private boolean _isOver100;

	private static final String TEN_DIGIT_PATTERN = "^[0-9]{2}[0-1][0-9][0-3][0-9][-|+][0-9]{4}$";

	public SwedishSocialSecurityNumber(String socialSecurityNumber)
			throws IllegalArgumentException {
		_tenDigitPattern = Pattern.compile(TEN_DIGIT_PATTERN);
		_isOver100 = false;

		if (!this.isTenDigit(socialSecurityNumber))
			throw new IllegalArgumentException();

		try {
			_year = Integer.parseInt(socialSecurityNumber.substring(0, 2));
			_month = Integer.parseInt(socialSecurityNumber.substring(2, 4));
			_day = Integer.parseInt(socialSecurityNumber.substring(4, 6));
			_code = socialSecurityNumber.substring(7, 11);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		if (socialSecurityNumber.charAt(6) == '+')
			_isOver100 = true;
		if (_day < 1 || _day > 31)
			throw new IllegalArgumentException();
		if (_month < 1 || _month > 12)
			throw new IllegalArgumentException();
		if (_year < 1)
			throw new IllegalArgumentException();
		
		if(this.isLeapYear(this.getYear()) && _month == 2 && _day == 29)
			throw new IllegalArgumentException();
	}

	public int getYear() {
		int tempInt = 0;

		Integer Year = Calendar.getInstance().get(Calendar.YEAR);

		if (_year < 10)
			tempInt = Integer.parseInt(String.format("%s0%d", Year.toString()
					.substring(0, 2), _year));
		else
			tempInt = Integer.parseInt(String.format("%s%d", Year.toString()
					.substring(0, 2), _year));

		if (_isOver100)
			return tempInt - 100;
		if (tempInt > Year)
			return tempInt - 100;

		return tempInt;

	}

	public String getMonth() {
		DateFormatSymbols dfs = new DateFormatSymbols();
		return dfs.getMonths()[_month - 1];
	}

	public int getMonthNumber() { return _month;}
	
	public int getDay() {
		return _day;
	}

	public String getCode() {
		return _code;
	}

	private boolean isTenDigit(String number) {
		_matcher = _tenDigitPattern.matcher(number);
		return _matcher.matches();
	}
	
	private boolean isLeapYear(int year){
		if ((year & 3) == 0 && ((year % 25) != 0 || (year & 15) == 0)) return true;
		return false;
	}

}