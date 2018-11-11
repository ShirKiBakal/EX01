
package myMath;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Shiran
 *
 */
public class Monom implements function{

	/**
	 * Full Constructor - where a is the Coefficient and b is the power
	 * @param a coefficient
	 * @param b Power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * String Constructor - Makes a Monom from String.
	 * @param s String to Init
	 */
	public Monom(String s){
		s = s.toLowerCase();
		String temp = "";
		int index=0;
		double a=0;
		int b=0;

		if((index = s.indexOf('x'))!=-1)
		{
			if(index+2<s.length() && index!=0)
			{
				if(s.charAt(index+1)=='^')
				{
					try
					{
						if(s.charAt(0) == '-' && index==1)
						{
							a = -1;
						}
						else
						{
							a = Double.parseDouble(s.substring(0,index));
						}
						b = Integer.parseInt(s.substring(index+2,s.length()));
					}
					catch (Exception e) {
						throw new RuntimeException("Syntax is not allow for Init!!");
					}

				}
				else
				{
					throw new RuntimeException("Syntax is not allow for Init!!");
				}
			}
			else if(index+1==s.length())
			{
				b=1;
				if(s.length()==1)
				{
					a=1;
				}
				else if(s.charAt(0)=='-')
				{
					if(index==1)
					{
						a=-1;
					}
					else
					{
						a = Double.parseDouble(s.substring(0,index));
					}

				}
				else
				{
					try
					{
						temp = s.substring(0, index);
						a = Double.parseDouble(temp);
					}
					catch (Exception e) {
						throw new RuntimeException("Syntax is not allow for Init!!");
					}
				}
			}
			else if(index==0)
			{
				a=1;
				try
				{
					b=Integer.parseInt(s.substring(index+2,s.length()));
				}
				catch (Exception e) {
					throw new RuntimeException("Syntax is not allow for Init!!");
				}

			}
			else
			{
				throw new RuntimeException("Syntax is not allow for Init!!");
			}

		}
		else
		{
			try
			{
				a = Double.parseDouble(s);
			}
			catch (Exception e) {
				throw new RuntimeException("Syntax is not allow for Init!!");
			}
		}

		this.set_coefficient(a);
		this.set_power(b);

	}

	/**
	 * Copy Constructor
	 * @param ot Monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public boolean equals(Monom m1) {

		return (m1._power==_power&&m1._coefficient==_coefficient);		//chak if the power and coefficient equl 
	}

	/**
	 * returns the Coefficient of Monom
	 * @return _coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * returns the Power of Monom
	 * @return _power
	 */
	public int get_power() {
		return this._power;
	}


	/**
	 * returns the Value of the Monom, at x. like F(x)=X^2
	 */
	public double f(double x) {
		return this._coefficient*Math.pow(x, this._power);
	} 

	/**
	 * Prints the Monom
	 */
	public String toString()
	{
		String s="";
		if(this.get_power()==0)
		{
			if(this.get_coefficient()%1==0)
			{
				s = ""+(int)this.get_coefficient();
			}
			else
			{
				s = ""+this.get_coefficient();
			}
		}
		else if(this.get_power()==1)
		{

			if(this.get_coefficient()==1)
			{
				s = "x";
			}
			else if(this.get_coefficient()==-1)
			{
				s="-x";
			}
			else if(this.get_coefficient()%1==0)
			{
				s = (int)this.get_coefficient()+"x";
			}
			else
			{
				s = this.get_coefficient()+"x";
			}

		}
		else
		{
			if(this.get_coefficient()==1)
			{
				s = "x^"+this.get_power();
			}
			else if(this.get_coefficient()==-1)
			{
				s = "-x^"+this.get_power();
			}
			else
			{
				if(this.get_coefficient()%1==0)
				{
					s = (int)this.get_coefficient()+"x^"+this.get_power();
				}
				else
				{
					s = this.get_coefficient()+"x^"+this.get_power();
				}
			}
		}
		return s;
	}

	/**
	 * Adds two monom together.
	 * @param m Monom
	 */
	public void add(Monom m)
	{
		if(this._power==m._power)
		{
			this._coefficient+=m._coefficient;
		}
		else
		{
			throw new RuntimeException("Cant add Monoms with Different Powers");
		}
	} 

	/**
	 * Checks if the Monom is "zero monom" , Coaf = 0;
	 * @return True or False
	 */
	public boolean isZero()
	{
		if(this.get_coefficient()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Multiply Two Monoms and Saving the result in the "this"
	 * @param m Monom
	 */
	public void multiply(Monom m){
		int temp_power = this.get_power();
		double temp_coaf = this.get_coefficient();
		temp_power += m.get_power();
		temp_coaf *= m.get_coefficient();
		this.set_power(temp_power);
		this.set_coefficient(temp_coaf);
	}

	/**
	 * ultiply Two Monoms and returning the result as a new Monom
	 * @param m Monom
	 * @return Monom
	 */
	public Monom multiply2(Monom m){
		int temp_power = this.get_power();
		double temp_coaf = this.get_coefficient();
		temp_power += m.get_power();
		temp_coaf *= m.get_coefficient();
		Monom ans = new Monom(temp_coaf,temp_power);
		return ans;
	}

	//****************** Private Methods and Data *****************

	/**
	 * Set the coaf
	 * @param a
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}

	/**
	 * set the power, must not be negative
	 * @param b
	 */
	private void set_power(int b) {
		if(b<0)
		{
			throw new RuntimeException("Power Cant be Negative!!!");
		}
		this._power = b;
	}

	private double _coefficient; // 
	private int _power;

}
