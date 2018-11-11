package myMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 4. 
 * 
 * @author Shiran
 *
 */
public class Polynom implements Polynom_able{

	// ********** add your code below ***********
	
	/**
	 * Default Constructor
	 */
	public Polynom()
	{
		poly = new ArrayList<Monom>();
	}
	
	/**
	 * Constructor from String s
	 * @param s
	 * 
	 */
	public Polynom(String s)
	{
		Polynom ans = new Polynom();
		if(s.equals("Polynom is Empty"))
		{
			return;
		}
		ans = string_test(s);
		Iterator<Monom> iter = ans.iterator();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			this.add(m);
		}
	}
	
	/**
	 * Printing the Polynom
	 */
	public String toString()
	{
		if(this.poly.isEmpty())
		{
			return "Polynom is Empty";
		}
		String temp="";
		Iterator<Monom> iter = this.poly.iterator();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			if(iter.hasNext() && m.get_coefficient()>=0)
			{
				temp += m+"+";
			}
			else if(iter.hasNext())
			{
				if(temp.length()>0)
				{
					temp = temp.substring(0, temp.length()-1);
				}
				temp += m+"+";
			}
			else
			{
				if(temp.length()>0 && m.get_coefficient()<0)
				{
					temp = temp.substring(0, temp.length()-1);
				}
				temp += m;
			}
		}
		return temp;
	}

	/**
	 * Sort using Monom_Comperator
	 */
	public void sort(){
		Collections.sort(this.poly, c);
	}

	/**
	 * get result of x in a Polynom. like F(X)
	 */
	public double f(double x) {
		double sum=0;
		Iterator<Monom> iter = this.poly.iterator();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			sum += m.f(x);
		}
		return sum;
	}

	/**
	 * add Polynom with other Polynom_able class
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iterator();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			this.add(m);
		}
		this.sort();

	}

	/**
	 * add Monom to this Polynom
	 */
	public void add(Monom m1) {
		if(m1.isZero())
		{
			return;
		}
		Iterator<Monom> iter = this.poly.iterator();
		while(iter.hasNext())
		{
			Monom m = new Monom(0,0); 
			m = iter.next();
			if(m.get_power()==m1.get_power())
			{
				m.add(m1);
				if(m.isZero())
				{
					this.poly.remove(m);
				}
				return;
			}
		}
		this.poly.add(m1);
		if(!poly.isEmpty())
		{
			this.sort();
		}
	}

	/**
	 * substract a Polynom from this Polynom
	 */
	public void substract(Polynom_able p1) {
		Polynom temp_poly = new Polynom();
		Monom temp_monom = new Monom(-1,0);
		temp_poly.add(temp_monom);
		temp_poly.multiply(p1);
		this.add(temp_poly);

	}

	/**
	 * Multiply this Polynom with other Polynom and store in This
	 */
	public void multiply(Polynom_able p1) {
		Iterator<Monom> iter = this.poly.iterator();
		Polynom temp_poly = new Polynom();
		Monom temp_monom = new Monom(0,0);
		while(iter.hasNext())
		{
			Monom m = iter.next();
			Iterator<Monom> iter2 = p1.iterator();
			while(iter2.hasNext())
			{
				Monom m1 = iter2.next();
				temp_monom = m.multiply2(m1);
				if(!temp_monom.isZero())
				{
					temp_poly.add(temp_monom);
				}
			}
		}
		this.poly=temp_poly.poly;
	}

	/**
	 * Multiply Monom m with this Polynom
	 * @param m
	 */
	public void multiply(Monom m) {
		Iterator<Monom> iter = this.poly.iterator();
		while(iter.hasNext())
		{
			Monom m1 = iter.next();
			m1.multiply(m);
		}
	}

	/**
	 * check if this polynom and p1 are the same polynom
	 */
	public boolean equals(Polynom_able p1) {
		boolean flag;
		Iterator <Monom> itr = p1.iterator();
		while (itr.hasNext())
		{
			flag=false;
			Monom m  = itr.next();
			Iterator <Monom> itr2 = iterator();
			while (itr2.hasNext())
			{
				Monom m2 = itr2.next();
				if (m.get_coefficient()==m2.get_coefficient() && m.get_power()==m2.get_power())
					flag = true;
			}
			if (flag == false) return false;
		}
		return true;
	}

	/**
	 * check if this Polynom is Zero Polynom
	 */
	public boolean isZero() {
		if(this.poly.isEmpty())
		{
			return true;
		}
		Iterator<Monom> iter = this.poly.iterator();
		boolean ans = true;
		while(iter.hasNext())
		{
			Monom m = iter.next();
			if(!m.isZero())
			{
				return false;
			}
		}
		return ans;
	}

	/**
	 * finds the place when the Polynom connect to x-axis. F(x)=0
	 */
	public double root(double x0, double x1, double eps) {

		if (f(x0)*f(x1)>0)
			try {
				throw new Exception();
			} catch (Exception e) {

				e.printStackTrace();
			}
		double newX = (x1+x0)/2;
		double newY = f(newX);

		if (Math.abs(newY)<Math.abs(eps)) return newX;
		if (f(x0)>0)
		{
			if (newY>0)
			{
				return root(newX,x1,eps);
			}
			else
			{
				return root(x0,newX,eps);
			}
		}
		else
		{
			if (newY>0)
			{
				return root(x0,newX,eps);
			}
			else
			{
				return root(newX,x1,eps);
			}
		}


	}

	/**
	 * copy this Polynom and return a new polynom
	 */
	public Polynom_able copy() {
		Polynom_able temp = new Polynom();
		Iterator<Monom> iter = this.poly.iterator();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			temp.add(m);
		}
		return temp;
	}

	/**
	 * return a derivative of this polynom
	 */
	public Polynom_able derivative() {
		Iterator<Monom> iter = this.poly.iterator();
		Polynom_able ans = new Polynom();
		while(iter.hasNext())
		{
			Monom m = iter.next();
			int power = m.get_power();
			double coaf = m.get_coefficient();
			int power_n = power-1;
			if(power_n>=0)
			{
				Monom temp = new Monom(power*coaf,power_n);
				ans.add(temp);
			}
		}
		return ans;

	}

	/**
	 * calculate the "positive" area between x0 and x1 with Riemann's Integral
	 */
	public double area(double x0, double x1, double eps) {
		if(x0>x1)
		{
			swap(x0,x1);
		}
		double squares = (x1-x0)/eps;
		double sum=0;
		double index=x0;
		double temp=0;
		for(double i=0;i<squares;i++)
		{
			temp=((this.f(index)+this.f(index+eps))*eps)/2;
			if(temp>0)
			{
				sum+=temp;
			}
			index+=eps;
		}
		return sum;
	}

	/**
	 *Returns the Iterator of this Polynom
	 */
	public Iterator<Monom> iterator() {
		return this.poly.iterator();
	}

	///////////// Private Methods ////////////////

	//Variables
	private ArrayList<Monom> poly = new ArrayList<Monom>();
	private Monom_Comperator c = new Monom_Comperator();

	private Polynom string_test(String s)
	{
		if(s==null)
		{
			throw new RuntimeException("Cant Init from Null!!");
		}
		Polynom ans = new Polynom();
		s = s.toLowerCase();
		int minues_index=0;
		if((minues_index = s.indexOf('-',1))!=-1 && s.charAt(minues_index-1)!='^')
		{
			ArrayList<Integer> index = new ArrayList<Integer>();
			for(int i=1;(i = s.indexOf('-', i+1)) != -1 ;i++)
			{
				index.add(i);
			}
			int index2 = 0;
			Iterator<Integer> iter = index.iterator();
			if(iter.hasNext() == false)
			{
				for(int i=0;(i = s.indexOf('-', i+1)) != -1 ;i++)
				{
					index.add(i);
				}
			}
			iter = index.iterator();
			while(iter.hasNext())
			{
				int ind = iter.next();
				ind+=index2;
				String temp = s;
				s=temp.substring(0, ind)+'+'+temp.substring(ind,temp.length());
				index2++;
			}
		}
		
		if(s.indexOf('+')!=-1)
		{
			StringTokenizer st = new StringTokenizer(s,"+");
			while(st.hasMoreTokens())
			{
				String s1 = st.nextToken();
				Monom m = new Monom(s1);
				ans.add(m);
			}
			return ans;
		}
		else
		{
			Monom m = new Monom(s);
			ans.add(m);
			return ans;
		}
	}

	private void swap(double a, double b)
	{
		double temp=a;
		a=b;
		b=temp;
	}



}
