

public class RankedPlayer implements Comparable<RankedPlayer>{
	
	private String name;
	private double kd;
	private int timesPlayed;
	
	public RankedPlayer(String name, double kd, int timesPlayed)
	{
		this.name = name;
		this.kd = kd;
		this.timesPlayed = timesPlayed;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int compareTo(RankedPlayer p)
	{
		if(!(this.kd == p.kd))
			return p.kd > this.kd ? 1 : -1;
		if(!(this.timesPlayed == p.timesPlayed))
			return p.timesPlayed - this.timesPlayed;
		return this.name.compareTo(p.name);
	}
	
	public boolean equals(RankedPlayer p)
	{
		return this.kd == p.kd && this.timesPlayed == p.timesPlayed && this.name.equals(p.name);
	}
	
	public String toString()
	{
		return name + ", " + String.format("%.3f", kd) + ", " + timesPlayed;
	}
}
