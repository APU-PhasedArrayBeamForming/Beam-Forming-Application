package beam.common;

public class AngleVsPower {

	/////////////////////////////////////////////////////////////////////
	// Instance Variables
	private double signalAngle;
	private double signalPower;

	/////////////////////////////////////////////////////////////////////
	// Constructors
	// Default Constructor - Takes no parameters
	public AngleVsPower()
	{
		signalAngle = 0;
		signalPower = 0;
	}

	// Overloaded Constructor - Takes parameters
	public AngleVsPower(double angle, double power)
	{
		signalAngle = angle;
		signalPower = power;
	}

	/////////////////////////////////////////////////////////////////////
	// Getters - public returnType getInstanceVarName()
	public double getSignalAngle() { return signalAngle;}
	public double getSignalPower() { return signalPower; }

	/////////////////////////////////////////////////////////////////////
	// Setters
	public void setSignalAngle(double angle) { signalAngle = angle; }
	public void setSignalPower(double power) { signalPower = power; }


	/////////////////////////////////////////////////////////////////////
	// Override the toString() method to return a String the describes the
	// contents/attributes (i.e., the instance variables) of THIS burrito
	//		public String toString()
	//		{
	//			String ret = "";
	//			ret += "Meat type = " + this.meatType + "; ";
	//			if (this.hasGuac)
	//				ret += "Has guacamole; ";
	//			ret += "Number of tortillas = " + this.numTortillas + "; ";
	//			if (isWrapped)
	//				ret += "Is wrapped.";
	//			else
	//				ret += "Is NOT wrapped.";
	//				
	//			return ret;		
	//		}
	//		
	//		/////////////////////////////////////////////////////////////////////
	//		// Override the equals() method to return true if the object (obj) 
	//		// being passed in is a Burrito with the same exact properties as
	//		// THIS Burrito
	//		public boolean equals(Object obj)
	//		{
	//			// First check if the object (obj) being passed in is even a burrito
	//			if (!(obj instanceof Burrito))
	//				return false;
	//			else
	//			{
	//				Burrito otherBurrito = (Burrito)obj;
	//				if (hasGuac == otherBurrito.hasGuac &&
	//					isWrapped == otherBurrito.isWrapped &&
	//					meatType.equals(otherBurrito.meatType) &&
	//					numTortillas == otherBurrito.numTortillas)
	//				{
	//					return true;
	//				}
	//				else
	//					return false;
	//			}
	//		}

}
