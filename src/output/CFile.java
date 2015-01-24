package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

;

public class CFile {
	 File f;
	 BufferedWriter output;
	 File dir=null;
	
	public boolean makefile(String fName) throws IOException
	{
		f = new File(fName);
		if (f.exists()) {
			// System.out.print("�ļ�����");
			return false;
		} else {
			// System.out.print("�ļ�������");
			f.createNewFile();// �������򴴽�
			return true;
		}
	  
	   
	 }
	
	public void inputRow(String row) 
	{  
		
		try {
			output= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   try {
		output.write(row);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   try {
		output.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 } 
	public void inputList(List<Entry<String, Double>> list) throws Throwable
	{  
		Iterator itr = list.iterator();
		while (itr.hasNext())
		{
			Entry<String, Double> nextObj =(Entry<String, Double>) itr.next();
			output= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
			output.write(nextObj.getKey()+"\t"+nextObj.getValue()+"\r\n");
		}
		output.close();
	 } 
	
	public boolean createDir(String path)
	{
		dir=new File(path);
		if(dir.mkdir()) return true;
		else return false;
	}
	public String getFilePreStr(String name)
	{
		File f = new File(name);
		String []fileParam= f.getName().split("\\.");
		String filePreStr=fileParam[0];
		String fileLastStr=fileParam[1];
		return  filePreStr;
	}
	public void createSubDir(String dirname,File fparent){
		createDir(fparent.getParent()+"\\"+dirname+"\\");
	}
	public void createSubDir(File fparent){
		createDir(fparent.getParent()+"\\"+fparent.getName()+"\\");
	}

}
