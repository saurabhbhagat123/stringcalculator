package com.calculator;


import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {
	
	
	
	@Test
	public void calculateCase1(){
		String actual = "7+(6*5^2+3-4/2)";
		String expected = "158";
		Assert.assertEquals(expected, StringCalculator.calculate(actual));
	}
	
	@Test
	public void calculateCase2(){
		String actual = "7+(67(56*2))";
		String expected = "INVALID EXPRESSION";
		Assert.assertEquals(expected, StringCalculator.calculate(actual));
	}
	
	
	@Test
	public void calculateCase3(){
		String actual = "8*+7";
		String expected = "INVALID EXPRESSION";
		Assert.assertEquals(expected, StringCalculator.calculate(actual));
	}
	
	@Test
	public void calculateCase4(){
		String actual = "(8*5/8)-(3/1)-5";
		String expected = "-3";
		Assert.assertEquals(expected, StringCalculator.calculate(actual));
	}
	
	
	
	
	

}
