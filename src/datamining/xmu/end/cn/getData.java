package datamining.xmu.end.cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class getData 
{
	private  int insNum;
	private  int feaNum;
	private  int labNum;
	private  double[][] data;
	
	public void setInsNum(int insNum)
	{
		this.insNum = insNum;
	}
	
	public void setFeaNum(int feaNum)
	{
		this.feaNum = feaNum;
	}
	
	public void setLabNum(int labNum)
	{
		this.labNum = labNum;
	}
	
	public void setData(double[][] data)
	{
		this.data = data;
	}
	
	public double[][] run(String inputPath) throws Exception, Exception
	{
		System.out.println("Initialization...");
		int line = 1;
		try {
			
			File InputFile = new File(inputPath);
			if(!InputFile.exists())
			{
				System.out.println("Can't find input file: " + InputFile);
				System.exit(0);
			}
			BufferedReader InputBR = new BufferedReader(new InputStreamReader(new FileInputStream(InputFile), "utf-8"));
			String InputLine = InputBR.readLine();
			int row = 0;
			String[] dataString;
			while(InputLine != null)
			{
				if(InputLine.length() == 0)
				{
					InputLine = InputBR.readLine();
					line ++;
					continue;
				}
				dataString = InputLine.split(",");
				for(int col = 0; col < dataString.length; ++ col)
				{
					data[row][col] = Double.parseDouble(dataString[col]);
				}
				InputLine = InputBR.readLine();
				line ++;
				row ++;
			}
			InputBR.close();
			
			
		} catch (Exception e) {
			System.out.println("The input file has error at line : " + line);
			System.exit(0);
			// TODO: handle exception
		}
		System.out.println("Initialization over!!!");
		return data;
	}
}
