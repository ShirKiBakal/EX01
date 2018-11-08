package myMath;
/**
 * @author Shiran
 */


import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {
	// ******** add your code below *********
	public Monom_Comperator() {
		
	}
	
	/**
	 * Comapre two Monoms (arg0 and arg1) by their Power.
	 * if arg0.power bigger than arg1.power - return 1
	 * if arg0.power smaller than arg1.power - return -1
	 * if arg0.power==arg1.power - return 0
	 */
	public int compare(Monom arg0, Monom arg1) {
		if(arg0.get_power()<arg1.get_power())
		{
			return 1;
		}
		else if(arg0.get_power()>arg1.get_power())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
