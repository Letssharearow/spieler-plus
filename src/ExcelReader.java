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

		try (BufferedReader bf = new BufferedReader(new FileReader(filePath)))
		{
			String tabelle = bf.readLine();
			String uhrzeit = bf.readLine();
			Spieltag spieltag = new Spieltag(teamName, STUNDEN_ABSAGEN, STUNDEN_ERINNERUNG, "", Teilnahme.ZUSAGEN);
			while (tabelle != null && uhrzeit != null)
			{
				String[] values = tabelle.split(";");
				String[] values2 = uhrzeit.split(";");
				Spiel spiel = new Spiel(values, values2[0]);

				if (!spieltag.hasEnemy())
				{
					spieltag.addGame(spiel, teamName);
				}
				else if (spieltag.isSameDay(spiel))
				{
					if (spieltag.isPlaying(spiel))
					{
						spieltag.addGame(spiel, teamName);
					}
				}
				else
				{
					spieltage.add(new Spieltag(spieltag));
					spieltag = new Spieltag(teamName, STUNDEN_ABSAGEN, STUNDEN_ERINNERUNG, "", Teilnahme.ZUSAGEN);
				}

				tabelle = bf.readLine();
				uhrzeit = bf.readLine();
			}

			if (spieltag.hasEnemy())
			{
				spieltage.add(new Spieltag(spieltag));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		spieltage.forEach(sp -> writer.writeIntoFile(sp.toString()));
	}

}
