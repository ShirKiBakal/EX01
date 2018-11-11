package myMath;

import org.junit.Test;
import org.omg.CORBA.Policy;

import static org.junit.Assert.*;



public class PolynomTest {


	
	Monom m = new Monom(1,1);
	Monom m1=new Monom(m);
	Monom m2 =new Monom(-1,1);

	@Test
	public void testMonom()
	{
		
		Monom m1=new Monom(m);
		assertTrue(m.get_coefficient()==1);
		assertTrue(m.get_power()==1);
		assertTrue(m1.get_coefficient() == m.get_coefficient());
		assertFalse(m.equals(m2));
		assertTrue(m.equals(m1));
		
		//checking the add
		m.add(m1);
		Monom m3 = new Monom(2,1);
		assertTrue(m.equals(m3));
		
		//checking the mul
		Monom m7 = new Monom(2,3);
		Monom m8 = new Monom(3,4);
		Monom m9 = new Monom(6,7);
		m7.multiply(m8);
		System.out.println(m7);
		System.out.println(m9);
		assertTrue(m7.equals(m9));
		
	
		
		
		
	}

	@Test
	public void testPolynom()
	{
		
		//checking add Monom
		Polynom p1 = new Polynom ();
		Polynom p2 = new Polynom("1x^1");
		Monom m1 = new Monom(1,1);
		p1.add(m1);
		assertTrue(p1.equals(p2));
		
		
		//checking add Polynom
		Polynom p3 = new Polynom("2x^1");
		Polynom p4 = new Polynom("3x^1");
		Polynom p5 = new Polynom("5x");
		p3.add(p4);
		assertTrue(p3.equals(p5));
		
		//checking mul polynom
		Polynom p6 = new Polynom("2x+3");
		Polynom p7 = new Polynom("5x+2x^2");
		Polynom p8 = new Polynom("16x^2+15x+4x^3");
		p6.multiply(p7);
		assertTrue(p6.equals(p8));
		
		Polynom p9 = new Polynom();
		p6.multiply(p9);
		System.out.println(p6);
		
		//checking sub polynom
		Polynom p12 = new Polynom ("2x^2+4x");
		Polynom p13 = new Polynom ("2x^2");
		Polynom p14 = new Polynom ("4x");
		p12.substract(p13);
		assertTrue(p14.equals(p12));
		
		//checking isZero
		
		Polynom p15 = new Polynom();
		assertTrue(p15.isZero());
		
		//checking root
		Polynom p16 = new Polynom("1x^3+1x^0");
		System.out.println("Expect to get -1, answer is "+p16.root(-4, 4, 0.0001));
		Polynom p17 = new Polynom("1x^2+4x^1+-1x^0");
		System.out.println("expect to get -4.23, answer is "+p17.root(-5, -3, 0.0000001));
		
		//checking area
		Polynom p18 = new Polynom("1x^2+-5x^0");
		System.out.println("expect to get 0.25066, answer is "+p17.area(0.4, 0.6, 0.00001));
		System.out.println("expect to get 23.9583, answer is "+p18.area(2.5,5,0.00001));
		
		

	}





}
