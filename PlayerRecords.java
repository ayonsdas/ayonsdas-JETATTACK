import java.util.*;

public class PlayerRecords {

	private Map<String, Integer> kills;
	private Map<String, Integer> deaths;
	private double kd;
	
	public PlayerRecords()
	{
		kills = new HashMap<>();
		deaths = new HashMap<>();
		kd = 0;
	}
	
	public Map<String, Integer> getKills()
	{
		return kills;
	}
	
	public Map<String, Integer> getDeaths()
	{
		return deaths;
	}
	
	public void addPlayer(String a)
	{
		if(!kills.containsKey(a))
			kills.put(a, 0);
		if(!deaths.containsKey(a))
			deaths.put(a, 0);
	}
	
	public void setKills(String a, int k)
	{
		kills.put(a, k);
	}
	
	public void setDeaths(String a, int k)
	{
		deaths.put(a, k);
	}
	
	public void kill(String a)
	{
		if(!kills.containsKey(a))
			kills.put(a, 0);
		kills.put(a, kills.get(a) + 1);
	}

	public void die(String a)
	{
		if(!deaths.containsKey(a))
			deaths.put(a, 0);
		deaths.put(a, deaths.get(a) + 1);
	}
	
	public void setKD(double k) {
		kd = k;
	}
	
	public void updateKD()
	{
		int ks = 0;
		int ds = 0;
		for(String k : kills.keySet())
			ks += kills.get(k);
		for(String d : deaths.keySet())
			ds += deaths.get(d);
		if(ds == 0)
			ds = 1;
		kd = (double) ks / ds;
	}
	
	public double getKD() {
		return kd;
	}
	
	public String toString() {
		return kills.toString() + " " + deaths.toString() + " " + kd;
	}
}