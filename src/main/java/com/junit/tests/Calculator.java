package com.junit.tests;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

	public int add(int a, int b) {
		return a+b;
	}
	
	public int addWithScum(int a, int b) {
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a+b;
	}

	public int muliply(int a, int b) {
		return a*b;
	}
	
	public double add(double a, double b) {
		return a + b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public Set<Integer> digitsSet(int pNumber) {
		final String lNumberDigits = String.valueOf(pNumber);
		final Set<Integer> lDigitsSet = new HashSet<>();
		for(int i = 0; i<lNumberDigits.length();i++) {
			if(!"-".equals(lNumberDigits.substring(i, i+1))) lDigitsSet.add(Integer.valueOf(lNumberDigits.substring(i, i+1)));
		}
		return lDigitsSet;
	}

}
