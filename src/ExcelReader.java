import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
	public static final String FILE_PATH = "utf8 csv.csv";
	public static String TEAM_NAME = "VfL Volkach";

	public static void main(String[] args)
	{
		readCsv(FILE_PATH, TEAM_NAME);
	}

	private static void readCsv(String filePath, String teamName)
	{
		List<Spieltag> spieltage = new ArrayList<>();
		ExcelWriter writer = new ExcelWriter("spielplan_importieren_" + teamName + ".csv");

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
					spieltag = new Spieltag(spiel, teamName, "24", "168", "", Teilnahme.ZUSAGEN);
				}
				else
				{
					if (spieltag.isSameDay(spiel))
					{
						spieltag.addGame(spiel, teamName);
					}
					else
					{
						spieltage.add(new Spieltag(spieltag));
						spieltag = null;
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
