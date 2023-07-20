import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
	public static String TEAM_NAME = "VfL Volkach II";

	public static void main(String[] args)
	{
		readCsv("spielplan.csv");
	}

	private static void readCsv(String filePath)
	{
		List<Spieltag> spieltage = new ArrayList<>();
		ExcelWriter writer = new ExcelWriter("spielplan_importieren.csv");

		try (BufferedReader bf = new BufferedReader(new FileReader(filePath)))
		{
			String tabelle = bf.readLine();
			String uhrzeit = bf.readLine();
			Spieltag spieltag = null;
			while (tabelle != null && uhrzeit != null)
			{
				String[] values = tabelle.split(";");
				String[] values2 = uhrzeit.split(";");
				Spiel spiel = new Spiel(values, values2[0], TEAM_NAME);
				if (spieltag == null)
				{
					spieltag = new Spieltag(spiel, TEAM_NAME);
				}
				else
				{
					if (spieltag.isSameDay(spiel))
					{
						spieltag.addGame(spiel, TEAM_NAME);
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
