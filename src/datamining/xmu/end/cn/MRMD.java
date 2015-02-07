package datamining.xmu.end.cn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MRMD {

	public static void main(String[] args) throws Exception, Exception{
		// TODO Auto-generated method stub
		if(args.length == 0 ||args[0].equals("-help"))
		{
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		if(args.length != 14)
		{
			System.out.println("\r\nThe number of parameters are not enough  !!!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		String inputFile = args[1];          
		String outoputFile = args[3];
		int insNum = 0;
		try {
			insNum = Integer.parseInt(args[5]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\r\nThe parameter of -in is not a integer !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		if(insNum < 0)
		{
			System.out.println("\r\nThe parameter of -in is not available !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		int feaNum = 0;
		try {
			feaNum = Integer.parseInt(args[7]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\r\nThe parameter of -fn is not a integer !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		if(feaNum < 0)
		{
			System.out.println("\r\nThe parameter of -fn is not available !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		int labNum = 0;
		try {
			labNum = Integer.parseInt(args[9]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\r\nThe parameter of -ln is not a integer !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		if(labNum < 0)
		{
			System.out.println("\r\nThe parameter of -ln is not available !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		int seleFeaNum = 0;
		try {
			seleFeaNum = Integer.parseInt(args[11]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\r\nThe parameter of -sn is error !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		if(seleFeaNum < 0 || seleFeaNum > feaNum)
		{
			System.out.println("\r\nThe parameter of -sn is not available !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}

		int disFunc = 0;
		try {
			disFunc = Integer.parseInt(args[13]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("\r\nThe parameter of -df is not a integer !!\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		
		if(disFunc < 1 || disFunc > 4)
		{
			System.out.println("\r\nThe parameter of -df is error !! df={1, 2, 3, 4}\r\n");
			System.out.println("Usage: java -jar MRMD.jar -i inputFile -o outputFile -in insNum -fn feaNum -sn seleFeaNum -ln lableNum -df disFunc");
			System.exit(0);
		}
		double[][] inputData = new double[insNum][feaNum + labNum];
		getData gd = new getData();
		gd.setInsNum(insNum);
		gd.setFeaNum(feaNum);
		gd.setLabNum(labNum);
		gd.setData(inputData);
		gd.run(inputFile);
	
		
		double[] PearsonValue = new double[feaNum];
		Pearson pd = new Pearson(feaNum, labNum);
		pd.setInsNum(insNum);
		pd.setFeaNum(feaNum);
		pd.setLabNum(labNum);
		pd.setData(inputData);
		pd.setPearsonValue(PearsonValue);
		pd.run();
		
		double[] EuclideanValue = new double[feaNum];
		Euclidean ed = new Euclidean(feaNum);
		ed.setInsNum(insNum);
		ed.setFeaNum(feaNum);
		ed.setLabNum(labNum);
		ed.setData(inputData);
		ed.setEuclideanValue(EuclideanValue);
		
		
		double[] CosineValue = new double[feaNum];
		Cosine cd = new Cosine(feaNum);
		cd.setInsNum(insNum);
		cd.setFeaNum(feaNum);
		cd.setLabNum(labNum);
		cd.setData(inputData);
		cd.setCosineValue(CosineValue);
		
		
		double[] TanimotoValue = new double[feaNum];
		Tanimoto td = new Tanimoto(feaNum);
		td.setInsNum(insNum);
		td.setFeaNum(feaNum);
		td.setLabNum(labNum);
		td.setData(inputData);
		td.setTanimotoValue(TanimotoValue);
		
		
		inputData = null;

		double[] mrmrValue = new double[feaNum];
		switch (disFunc)
		{
			case 1:
				ed.run();
				for(int i = 0; i < feaNum; ++ i)
				{
					mrmrValue[i] = EuclideanValue[i] + PearsonValue[i];
				}
				break;
			case 2:
				cd.run();
				for(int i = 0; i < feaNum; ++ i)
				{
					mrmrValue[i] = CosineValue[i] + PearsonValue[i];
				}
				break;
			case 3:
				td.run();
				for(int i = 0; i < feaNum; ++ i)
				{
					mrmrValue[i] = TanimotoValue[i] + PearsonValue[i];
				}
				break;
			case 4:
				ed.run();
				cd.run();
				td.run();
				for(int i = 0; i < feaNum; ++ i)
				{
					mrmrValue[i] = (PearsonValue[i] * 3 + EuclideanValue[i] + CosineValue[i] + TanimotoValue[i])/3;
				}
				break;
			default:
				break;
		}
		
		PearsonValue = null;
		EuclideanValue = null;
		CosineValue = null;
		TanimotoValue = null;
		
		Map<String, Double> mrmrMap = new HashMap<String, Double>(); 
		List<Map.Entry<String, Double>> mrmrList = new ArrayList<Map.Entry<String, Double>>(mrmrMap.entrySet());
		mrmrList = initialHashMap(mrmrValue, feaNum);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outoputFile), false), "utf-8"));
		bufferedWriter.write("The number of selected features is: " + seleFeaNum + "\r\n\r\n");
		bufferedWriter.write("The index of selected features start from 0" + "\r\n\r\n");
		bufferedWriter.write("NO." + "		" + "FeaName" + "		" + "Score" + "\r\n\r\n");
		int line = 1;
		for(int i = 0; i < seleFeaNum; ++ i)
		{
			bufferedWriter.write(line + "		" + mrmrList.get(i).getKey() + "		" + mrmrList.get(i).getValue() + "\r\n");
			line ++;
		}
		bufferedWriter.flush();
		bufferedWriter.close();
		System.out.println("MRMD over!!!");
		
	}
	
	public static List initialHashMap(double data[], int feaNum)
	{
		Map<String, Double> mrmrMap = new HashMap<String, Double>(); 
		for(int i = 0; i < feaNum; ++ i)
		{
			mrmrMap.put("Fea" + i, data[i]);
		}
		
		List<Map.Entry<String, Double>> mrmrList = new ArrayList<Map.Entry<String, Double>>(mrmrMap.entrySet());
		Collections.sort(mrmrList, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2)
			{
//				return (o1.getValue()).compareTo(o2.getValue());
				if(Double.parseDouble(o1.getValue().toString()) < Double.parseDouble(o2.getValue().toString()))
				{
					return 1;
				}
				else if(Double.parseDouble(o1.getValue().toString()) == Double.parseDouble(o2.getValue().toString()))
				{
					return 0;
				}
				else {
					return -1;
				}
			}
			
		});
		return mrmrList;
	}

}
