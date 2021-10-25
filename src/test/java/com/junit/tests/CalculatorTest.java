package com.junit.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class CalculatorTest {
	
	private  Calculator lCalculator;
	private static Instant temporiseur;
	
	@BeforeAll
	static void initialiseClockDown() {
		System.out.println(LocalDateTime.now());
		temporiseur = Instant.now();
	}

	@BeforeEach
	void createCalculator() {
		lCalculator = new Calculator();
	}
	
	@AfterEach
	void detruireCalculator() {
	    lCalculator = null;	
	}
	
	@AfterAll
	static void displayClockDown() {
		System.out.println(LocalDateTime.now());
		long lDuration = Duration.between(temporiseur, Instant.now()).toMillis();
		System.out.print(MessageFormat.format("Ce test a durée {0} millisecondes", lDuration));
	}
	 
    @Test
	void testAddTwoPositiveOperands() {
		// Step Arrange : initialiser 2 opérandes.
    	int a = 2, b = 3;
    	int somme = 5;
    	// Step Act : Exécuter le système à tester.
    	int lReturnedSum = lCalculator.add(a,b);
    	// Step Assert (valide) : vérifier le résultat attendu
    	assertThat(lReturnedSum).isEqualTo(somme);
	}
    
    @Test
    void testMultiplyTwoOperands() {
    	int a = 3, b = 4;
    	int lExpectedResult = 12;
    	int lReturnedResult = lCalculator.muliply(a,b);
    	assertThat(lReturnedResult).isEqualTo(lExpectedResult);
    }
    
    @ParameterizedTest(name = "{0} * 0 doit être égale à Zero")
    @ValueSource(ints = {1,3,5,7,9,12})
    void testMultiplyToZeroOperand(int arg) {
    	// Arrange : RAS
    	// Act 
    	int lReturnedResult = lCalculator.muliply(arg, 0);
    	assertThat(lReturnedResult).isEqualTo(0);
    }
    
    @ParameterizedTest(name = "{0} * {1} doit être égale à {2}")
    @CsvSource({"3,2,6","4,2,8", "4,3,12"})
    void testParameterizedMultiplyOperation(int a, int b, int somme) {
    	// Arrange : RAS
    	// Act 
    	int lReturnedResult = lCalculator.muliply(a, b);
    	assertThat(lReturnedResult).isEqualTo(somme);
    }
    
    @Test
    @Timeout(1)
    void testAddition() {
    	int a = 2, b = 3;
        int lReturnedResult = lCalculator.addWithScum(a, b);
    	assertThat(lReturnedResult).isEqualTo(5);
    }
    
    @Test   
    void testRenvoieChiffresComposantUnNombre() {
    	// Arrange
    	final int lNumber = 98765;
    	// Act
    	final Set<Integer> lDigits = lCalculator.digitsSet(lNumber);
    	// Assert
    	final Set<Integer> lExpectedDigits = Stream.of(9,8,7,6,5).collect(Collectors.toSet());
    	assertEquals(lDigits, lExpectedDigits);
    	// OR
    	assertThat(lDigits).containsExactlyInAnyOrderElementsOf(lExpectedDigits);
    	// OR
    	assertThat(lDigits).containsExactlyInAnyOrder(6,9,8,5,7);
    }
    
    @Test   
    void testRenvoieChiffresComposantUnNombreNegatif() {
    	// Arrange
    	final int lNumber = -987655687;
    	// Act
    	final Set<Integer> lDigits = lCalculator.digitsSet(lNumber);
    	// Assert
    	final Set<Integer> lExpectedDigits = Stream.of(9,8,7,6,5).collect(Collectors.toSet());
    	assertEquals(lDigits, lExpectedDigits);
    	// OR
    	assertThat(lDigits).containsExactlyInAnyOrderElementsOf(lExpectedDigits);
    	// OR
    	assertThat(lDigits).containsExactlyInAnyOrder(6,9,8,5,7);
    }
    
    @Test   
    void testRenvoieChiffresComposantUnNombreAZero() {
    	// Arrange
    	final int lNumber = 0;
    	// Act
    	final Set<Integer> lDigits = lCalculator.digitsSet(lNumber);
    	
    	// OR
    	assertThat(lDigits).containsExactly(0);
    }
}
