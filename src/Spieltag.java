public class Spieltag
{
	String spieltyp;
	String gegner;
	String startDatum;
	String endDatum;
	String startZeit;
	String treffenZeit;
	String endZeit;
	String heimspiel;
	String gelaende;
	String adresse;
	String infos;
	String teilnahme;
	String nominierung;
	String absageStunden;
	String erinnerungStunden;

	String teamname;
	boolean istDabei = false;

	//	public Spieltag(String gegner, String startDatum, String endDatum, String startZeit, String heimspiel,
	//		String absageStunden, String erinnerungStunden)
	//	{
	//		this.gegner = gegner;
	//		this.startDatum = startDatum;
	//		this.endDatum = endDatum;
	//		this.startZeit = startZeit;
	//		this.heimspiel = heimspiel;
	//		this.absageStunden = absageStunden;
	//		this.erinnerungStunden = erinnerungStunden;
	//	}

	public Spieltag(Spieltag spieltag)
	{
		this.spieltyp = spieltag.spieltyp;
		this.gegner = spieltag.gegner;
		this.startDatum = spieltag.startDatum;
		this.endDatum = spieltag.endDatum;
		this.startZeit = spieltag.startZeit;
		this.treffenZeit = spieltag.treffenZeit;
		this.endZeit = spieltag.endZeit;
		this.heimspiel = spieltag.heimspiel;
		this.gelaende = spieltag.gelaende;
		this.adresse = spieltag.adresse;
		this.infos = spieltag.infos;
		this.teilnahme = spieltag.teilnahme;
		this.nominierung = spieltag.nominierung;
		this.absageStunden = spieltag.absageStunden;
		this.erinnerungStunden = spieltag.erinnerungStunden;

		this.teamname = spieltag.teamname;
		this.istDabei = spieltag.istDabei;
	}

	public Spieltag(Spiel spiel, String teamname)
	{
		if (teamname.equals(spiel.teamA) || teamname.equals(spiel.teamB))
		{
			istDabei = true;
		}
		this.gegner = (teamname.equals(spiel.teamA) ? spiel.teamB : spiel.teamA);
		if (!teamname.equals(spiel.teamB))
		{
			this.gegner += ", " + spiel.teamB;
		}
		this.startDatum = spiel.datum;
		this.endDatum = spiel.datum;
		this.startZeit = spiel.uhrzeit;
		this.heimspiel = teamname.equals(spiel.heimteam) ? "ja" : "nein";
		this.absageStunden = "0";
		this.erinnerungStunden = "24";

		this.spieltyp = this.heimspiel.equals("ja") ? "Heimspiel" : "Auswärts";
		this.treffenZeit = "";
		this.endZeit = "";
		this.gelaende = "Halle";
		this.adresse = "";
		this.infos = "";
		this.teilnahme = "Spieler müssen zusagen";
		this.nominierung = "";

		this.teamname = teamname;
	}

	public boolean isSameDay(Spiel spiel)
	{
		return this.startDatum.equals(spiel.datum);
	}

	public void addGame(Spiel spiel, String teamname)
	{
		if (teamname.equals(spiel.teamA) || teamname.equals(spiel.teamB))
		{
			istDabei = true;
		}
		this.gegner += ", " + (teamname.equals(spiel.teamA) ? spiel.teamB : spiel.teamA);
		this.heimspiel = teamname.equals(spiel.heimteam) ? "ja" : this.heimspiel;
	}

	private String addIfNotNull(String attribute)
	{
		if (attribute != null)
		{
			return attribute + ";";
		}
		else
			return ";";
	}

	@Override public String toString()
	{
		if (!istDabei)
		{
			return "";
		}
		return addIfNotNull(this.spieltyp) + addIfNotNull(this.gegner) + addIfNotNull(this.startDatum) + addIfNotNull(
			this.endDatum) + addIfNotNull(this.startZeit) + addIfNotNull(this.treffenZeit) + addIfNotNull(this.endZeit)
			+ addIfNotNull(this.heimspiel) + addIfNotNull(this.gelaende) + addIfNotNull(this.adresse) + addIfNotNull(
			this.infos) + addIfNotNull(this.teilnahme) + addIfNotNull(this.nominierung) + addIfNotNull(
			this.absageStunden) + addIfNotNull(this.erinnerungStunden);
	}
}
