import java.io.*;
import java.util.*;

class InsertDeadCode
{
	public static void traverse(String path)
	{
		

		String files;
		//String str;
		//String originalstring;
	
		File folder = new File(path);
  		File[] listOfFiles = folder.listFiles();
		files = listOfFiles[0].getName();
		for (int i = 0; i < listOfFiles.length; i++) 
		{
		 
			if (listOfFiles[i].isFile() ) 
			   {
			   	if(listOfFiles[i].getName().endsWith(".smali"))
				//path = path+files;
				{
				files = listOfFiles[i].getName();
				methodInsert(path+files);
				}

			   }

			else if(listOfFiles[i].isDirectory())
			{
				files = listOfFiles[i].getName();
				traverse(path+"/"+files+"/");
				//System.out.println (files+" is a directory");

				

			}

			
		 }

		
	}

	public static void methodInsert(String path)
	{

		String str;
		int caninsert = 0;
		String originalstring;
		String ofs;
		String str1;
		//System.out.println("changeCFG");
		System.out.println(path);

		try{

					FileInputStream fstream1 = new FileInputStream("sourceCode/deadcode.txt");
			  		DataInputStream in1 = new DataInputStream(fstream1);
					BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
					str1 = br1.readLine();
					ofs = (str1+'\n');


					while((str1 = br1.readLine()) != null)
					{
						ofs += (str1+'\n');
					}
					//System.out.println(ofs);

					FileInputStream fstream = new FileInputStream(path);
			  		DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));

					
					str = br.readLine();
					originalstring=(str+'\n');
			  
			  
					while ((str = br.readLine()) != null) 
					  {
					  	
		
						if(str.contains("return-void"))
						{
							//System.out.println (str);
							    caninsert = 1;

							    
							    originalstring+=ofs;
							    originalstring+="\n";

							    
							    //continue;
					
						}
						originalstring+=(str+'\n');
						/*else
						{
							System.out.println ("not found");	
						}*/
	

						
					 	//originalstring+=(str+'\n');
			
					  
					 }
					//System.out.println (originalstring);
	
			  			in1.close();
					  in.close();

		
					FileOutputStream fostream = new FileOutputStream(path);
					OutputStreamWriter oswriter = new OutputStreamWriter(fostream);
					BufferedWriter outw = new BufferedWriter(oswriter);

					outw.write(originalstring);
					outw.close();
	

			    }catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
				  }

	}

	public static void main(String args[])
	{
		
		traverse(args[0]);
	
	}

}
