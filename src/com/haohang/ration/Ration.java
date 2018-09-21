package com.haohang.ration;

import java.util.Scanner;

public class Ration {
	
	public int numerator;
	public int denominator;
	
	public Ration(int numerator, int denominator) {
		yuefen(numerator, denominator);
	}
	
	public Ration mul(Ration mulR) {
		return new Ration((this.numerator * mulR.numerator), (this.denominator * mulR.denominator));
	}
	
	public Ration div(Ration mulR) {
		return new Ration((this.numerator * mulR.denominator), (this.denominator * mulR.numerator));
	}
	public Ration add(Ration mulR) {
		return new Ration((this.numerator * mulR.denominator + mulR.numerator * this.denominator), (this.denominator * mulR.denominator));
	}
	public Ration sub(Ration mulR) {
		return new Ration((this.numerator * mulR.denominator - mulR.numerator * this.denominator), (this.denominator * mulR.denominator));
	}

	@Override
	public String toString() {
		if (this.denominator == 1) {
			return String.valueOf(this.numerator);
		} else{
//			return new String(this.numerator + "/" + this.denominator);
			if (this.numerator > this.denominator) {
				int dai = numerator / denominator;
				return new String(dai + "'" + (this.numerator - this.denominator*dai) + "/" + this.denominator);
			} else {
				return new String(this.numerator + "/" + this.denominator);
			}
		}
	}
	
	private void yuefen(int a, int b) {
		int oa = a;
		int ob = b;
		while (b != 0) {
			int r = a%b;
			a = b;
			b = r;
		}
		this.numerator = oa / a;
		this.denominator = ob / a;
	}

}
