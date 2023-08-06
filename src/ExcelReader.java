import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
	public static final String FILE_PATH = "damen 1.csv";
	public static String TEAM_NAME = "VfL Volkach";
	public static String STUNDEN_ABSAGEN = "24";
	public static String STUNDEN_ERINNERUNG = "168";

	public static void main(String[] args)
	{
		readCsv(FILE_PATH, TEAM_NAME);
	}

	private static void readCsv(String filePath, String teamName)
	{
		List<Spieltag> spieltage = new ArrayList<>();
		ExcelWriter writer = new ExcelWriter("importieren_" + FILE_PATH);
		int countGames = 0;

		try (BufferedReader bf = new BufferedReader(new FileReader(filePath)))
		{
			String tabelle = bf.readLine();
			String uhrzeit = bf.readLine();
			Spieltag spieltag = null;
			while (tabelle != null && uhrzeit != null)
			{
				String[] values = tabelle.split(";");
				String[] values2 = uhrzeit.split(";");
				Spiel spiel = new Spiel(values, values2[0], teamName);
				if (spieltag == null)
				{
					spieltag = new Spieltag(spiel, teamName, STUNDEN_ABSAGEN, STUNDEN_ERINNERUNG, "",
						Teilnahme.ZUSAGEN);
					countGames++;
				}
				else
				{
					if (spieltag.isSameDay(spiel) && countGames <= 2)
					{
						spieltag.addGame(spiel, teamName);
						countGames++;
					}
					else
					{
						spieltage.add(new Spieltag(spieltag));
						spieltag = null;
						countGames = 0;
					}
				}
				tabelle = bf.readLine();
				uhrzeit = bf.readLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		spieltage.forEach(sp -> writer.writeIntoFile(sp.toString()));
	}

}
