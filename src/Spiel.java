public class Spiel
{
	String datum;
	String nummer;
	String teamA;
	String teamB;
	String uhrzeit;
	String heimteam;

	public Spiel(String[] tabelle, String uhrzeit, String teamname)
	{
		this.datum = tabelle[0].replaceAll("(([A-Z]|[a-z])*, )", "");
		this.nummer = tabelle[1];
		this.teamA = tabelle[2].replace("�(H)", "");
		this.teamB = tabelle[3].replace("�(H)", "");
		this.uhrzeit = uhrzeit.replace(" Uhr", ":00");

		if (tabelle[2].contains("(H)"))
		{
			this.heimteam = this.teamA;
		}
		else if (tabelle[3].contains("(H)"))
		{
			this.heimteam = this.teamB;
		}
		else
		{
			this.heimteam = "";
		}
	}

	@Override public String toString()
	{
		return "Spiel{" + "datum='" + datum + '\'' + ", nummer='" + nummer + '\'' + ", teamA='" + teamA + '\''
			+ ", teamB='" + teamB + '\'' + ", uhrzeit='" + uhrzeit + '\'' + '}';
	}
}
