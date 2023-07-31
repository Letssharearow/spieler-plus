public enum Teilnahme
{
	ZUSAGEN("Spieler müssen zusagen"), ABSAGEN("Spieler müssen absagen");

	private String displayName;

	Teilnahme(String s)
	{
		this.displayName = s;
	}

	// Optionally and/or additionally, toString.
	@Override public String toString()
	{
		return displayName;
	}
}
