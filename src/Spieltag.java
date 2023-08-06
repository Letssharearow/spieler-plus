public class Spieltag
{
	String spieltyp;
	String gegner = "";
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
	}

	public Spieltag(Spiel spiel, String teamname, String absageStunden, String erinnerungStunden, String treffenZeit,
		Teilnahme teilnahme)
	{
		this.gegner = (teamname.equals(spiel.teamA) ? spiel.teamB : spiel.teamA);
		if (!teamname.equals(spiel.teamB) && !this.gegner.equals(spiel.teamB))
		{
			this.gegner += ", " + spiel.teamB;
		}
		this.startDatum = spiel.datum;
		this.endDatum = spiel.datum;
		this.startZeit = spiel.uhrzeit;
		this.heimspiel = teamname.equals(spiel.heimteam) ? "ja" : "nein";
		this.absageStunden = absageStunden;
		this.erinnerungStunden = erinnerungStunden;

		this.spieltyp = this.heimspiel.equals("ja") ? "Heimspiel" : "Auswärts";
		this.treffenZeit = treffenZeit;
		this.endZeit = "";
		this.gelaende = "Halle";
		this.adresse = "";
		this.infos = "";
		this.teilnahme = teilnahme.toString();
		this.nominierung = "";

		this.teamname = teamname;
	}

	public Spieltag(String teamname, String absageStunden, String erinnerungStunden, String treffenZeit,
		Teilnahme teilnahme)
	{
		this.absageStunden = absageStunden;
		this.erinnerungStunden = erinnerungStunden;

		this.treffenZeit = treffenZeit;
		this.endZeit = "";
		this.gelaende = "Halle";
		this.adresse = "";
		this.infos = "";
		this.teilnahme = teilnahme.toString();
		this.nominierung = "";

		this.teamname = teamname;
	}

	public boolean isSameDay(Spiel spiel)
	{
		return this.startDatum.equals(spiel.datum);
	}

	public boolean isPlaying(Spiel spiel)
	{
		return this.teamname.equals(spiel.teamA) || this.teamname.equals(spiel.teamB);
	}

	public boolean hasEnemy()
	{
		return !this.gegner.equals("");
	}

	public void addGame(Spiel spiel, String teamname)
	{
		if (!spiel.teamA.equals(teamname) && !spiel.teamB.equals(teamname))
		{
			return;
		}
		this.startDatum = spiel.datum;
		this.endDatum = spiel.datum;
		this.startZeit = spiel.uhrzeit;
		this.heimspiel = teamname.equals(spiel.heimteam) ? "ja" : "nein";
		this.spieltyp = this.heimspiel.equals("ja") ? "Heimspiel" : "Auswärts";
		this.gegner = (this.gegner.equals("") ? "" : (this.gegner + ", ")) + (teamname.equals(spiel.teamA) ?
			spiel.teamB :
			spiel.teamA);
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
		return addIfNotNull(this.spieltyp) + addIfNotNull(this.gegner) + addIfNotNull(this.startDatum) + addIfNotNull(
			this.endDatum) + addIfNotNull(this.startZeit) + addIfNotNull(this.treffenZeit) + addIfNotNull(this.endZeit)
			+ addIfNotNull(this.heimspiel) + addIfNotNull(this.gelaende) + addIfNotNull(this.adresse) + addIfNotNull(
			this.infos) + addIfNotNull(this.teilnahme) + addIfNotNull(this.nominierung) + addIfNotNull(
			this.absageStunden) + addIfNotNull(this.erinnerungStunden);
	}
}
