package main;

import java.io.*;

public class ExcelWriter
{
	private BufferedWriter bufferedWriter;

	public ExcelWriter(String filePath)
	{
		setBufferedWriter(filePath);
	}

	private void setBufferedWriter(String filePath)
	{
		try
		{
			bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			File file = new File(filePath);
			setBufferedWriter(filePath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void writeIntoFile(String line)
	{
		try
		{
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		ExcelWriter excelWriter = new ExcelWriter("testFile2.csv");
		excelWriter.writeIntoFile("test;test;tesdfsdfst;test;");
		excelWriter.close();
	}
}
