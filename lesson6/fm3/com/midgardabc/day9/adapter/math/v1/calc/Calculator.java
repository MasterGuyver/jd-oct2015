package com.midgardabc.day9.adapter.math.v1.calc;
import com.midgardabc.day9.adapter.math.*;

public class Calculator {

	public int summa(int a, int b) {
		return Arifmetika.summa(new int[] {a,b});
	}

	public int multiply(int a, int b) {
		int summ = 0;
		for(int i = 0; i<b; i++) {
			summ += Arifmetika.summa(new int[] {summ,a});
		}
		return summ;
	}
}
