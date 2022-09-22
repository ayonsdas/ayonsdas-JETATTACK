import java.util.*;
import java.io.*;

public class Stats
{
	
	private Map<String, PlayerRecords> page;
	private Scanner s;
	private PrintWriter o;
	String file;
	
	public Stats(String file) throws FileNotFoundException
	{
		this.file = file;
		s = new Scanner(new File(file));
		page = new HashMap<>();
		while(true)
		{
			String key = s.next();
			if(key.equals("END"))
				break;
			String playerName = s.next();
			s.nextLine();
			s.nextLine();
			PlayerRecords p = new PlayerRecords();
			page.put(playerName,  p);
			while(true)
			{
				String rName = s.next();
				if(rName.equals("Deaths:"))
					break;
				int rKills = s.nextInt();
				p.setKills(rName, rKills);
			}
			s.nextLine();
			while(true)
			{
				String rName = s.next();
				if(rName.equals("K/D:"))
					break;
				int rDeaths = s.nextInt();
				p.setDeaths(rName, rDeaths);
			}
			p.setKD(s.nextDouble());
			s.nextLine();
			s.nextLine();
		}
		update();
	}
	
	public void newKill(String p1, String p2)
	{
		if(!page.containsKey(p1))
			page.put(p1, new PlayerRecords());
		page.get(p1).kill(p2);
		updateKD(p1);
	}
	
	public void newDeath(String p1, String p2)
	{
		if(!page.containsKey(p1))
			page.put(p1, new PlayerRecords());
		page.get(p1).die(p2);
		updateKD(p1);
	}
	
	public void newPlay(String p1, String p2)
	{
		if(!page.containsKey(p1))
			page.put(p1, new PlayerRecords());
		page.get(p1).addPlayer(p2);
	}
	
	public void updateKD(String p1)
	{
		if(!page.containsKey(p1))
			page.put(p1, new PlayerRecords());
		page.get(p1).updateKD();
	}
	
	public Set<String> players()
	{
		return page.keySet();
	}
	
	public int numTimesPlayed(String p1)
	{
		if(!page.containsKey(p1))
			return 0;
		int count = 0;
		for(String k : page.get(p1).getKills().keySet())
		{
			count += page.get(p1).getKills().get(k);
			count += page.get(p1).getDeaths().get(k);
		}
		return count;
	}
	
	public double getKD(String p1)
	{
		if(!page.containsKey(p1))
			return 0;
		return page.get(p1).getKD();
	}
	
	public void update() throws FileNotFoundException
	{
		o = new PrintWriter(new File(file));
		for(String key : page.keySet())
		{
			o.println("Player: " + key);
			o.println("Kills:");
			Map<String, Integer> k = page.get(key).getKills();
			for(String killKey : k.keySet())
				o.println("\t" + killKey + " " + k.get(killKey));
			o.println("Deaths:");
			Map<String, Integer> d = page.get(key).getDeaths();
			for(String deathKey : d.keySet())
				o.println("\t" + deathKey + " " + d.get(deathKey));
			o.printf("K/D: %.3f%n%n", page.get(key).getKD());
		}
		o.println("END");
		o.close();
	}
	
	public boolean hasPlays()
	{
		return !page.isEmpty();
	}
	
	public Set<String> getPlayers()
	{
		return page.keySet();
	}
	
	public String toString()
	{
		return page.toString();
	}
}