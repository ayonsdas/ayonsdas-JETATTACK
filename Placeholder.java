/**
 * The Placeholder class is a helper class to maintain kills and deaths.
 * @author Ayon Das
 * Collaborators: Nathan Ma, Neil Gupta
 * Teacher Name: Ms. Bailey
 * Period: 5, 6
 * Due Date 5-19-2022
 */

import java.util.*;

public class Placeholder {

	private Map<String, Integer> kills;
	private Map<String, Integer> deaths;
	private double kd;
	
	/**
	 * Creates a new PlaceHolder
	 */
	public Placeholder()
	{
		kills = new HashMap<>();
		deaths = new HashMap<>();
		kd = 0;
	}
	
	/**
	 * Returns a mapping of players to kills
	 * @return Map of players to kills
	 */
	public Map<String, Integer> getKills()
	{
		return kills;
	}
	
	/**
	 * Returns a mapping of players to deaths
	 * @return Map of players to deaths
	 */
	public Map<String, Integer> getDeaths()
	{
		return deaths;
	}
	
	/**
	 * Adds a player into the map
	 * @param a Name of player to add
	 */
	public void addPlayer(String a)
	{
		kills.put(a, 0);
		deaths.put(a, 0);
	}
	
	/**
	 * Sets the number of kills a player has
	 * @param a Name of player to update
	 * @param k Number of kills to set player to
	 */
	public void setKills(String a, int k)
	{
		kills.put(a, k);
	}
	
	/**
	 * Sets the number of deaths a player has
	 * @param a Name of player to update
	 * @param k Number of deaths to set player to
	 */
	public void setDeaths(String a, int k)
	{
		deaths.put(a, k);
	}
	
	/**
	 * Gives a player a kill
	 * @param a Name of player that gets a kill
	 */
	public void kill(String a)
	{
		if(kills.containsKey(a))
			kills.put(a, 0);
		kills.put(a, kills.get(a) + 1);
	}

	/**
	 * Gives a player a death
	 * @param a Name of player that dies
	 */
	public void die(String a)
	{
		if(deaths.containsKey(a))
			deaths.put(a, 0);
		deaths.put(a, deaths.get(a) + 1);
	}
	
	/**
	 * Sets the K/D ratio of a Player
	 * @param k Name of player to update K/D
	 */
	public void setKD(double k) {
		kd = k;
	}
	
	/**
	 * Calculates proper K/D ratio
	 */
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
	
	/**
	 * Returns K/D ratio
	 * @return K/D ratio as a double
	 */
	public double getKD() {
		return kd;
	}
	
	/**
	 * Returns Kills and Deaths as a String
	 * @return Kills and Deaths as a String
	 */
	public String toString() {
		return kills.toString() + " " + deaths.toString() + " " + kd;
	}
}