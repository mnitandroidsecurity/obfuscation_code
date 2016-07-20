import java.io.*;
import java.util.*;

class CntrolFlowChange
{
	public static void traverse(String path)
	{
		//System.out.println ("traverse");
		//changeCFG(path);

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
				{files = listOfFiles[i].getName();
				changeCFG(path+files);
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

	public static void changeCFG(String path)
	{

		String str;
		int caninsert = 0;
		String originalstring;
		//System.out.println("changeCFG");
		System.out.println(path);

		try{
					FileInputStream fstream = new FileInputStream(path);
			  		DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));

					/*FileOutputStream fostream = new FileOutputStream(path+"re.txt");
					OutputStreamWriter oswriter = new OutputStreamWriter(fostream);
					BufferedWriter outw = new BufferedWriter(oswriter);*/
			

					//String str;
					//String originalstring;
					str = br.readLine();
					originalstring=(str+'\n');
			  
			  
					while ((str = br.readLine()) != null) 
					  {
					  
		
						if(str.trim().compareTo(".prologue") == 0)
						{
							//System.out.println (str);
							    caninsert = 1;

							    originalstring+=(str+'\n');
							    originalstring+=('\n');

							    //Insert goto obfuscation code at the beginning of the method.
							    String newstr = "    goto :CFGGoto2";
							    originalstring+=(newstr+'\n');
							    originalstring+=('\n');

							    newstr = "    :CFGGoto1";
							    originalstring+=(newstr+'\n');
							    originalstring+=('\n');
							    continue;
					
						}

						if(str.trim().compareTo(".end method") == 0 && caninsert==1 )
						{
							
							caninsert = 0;

							originalstring+=('\n');

							//Insert goto obfuscation code at the end of the method.
							String newstr = "    :CFGGoto2";
							originalstring+=(newstr+'\n');
							originalstring+=('\n');

							newstr = "    goto :CFGGoto1";
							originalstring+=(newstr+'\n');
							originalstring+=('\n');

							originalstring+=(str+'\n');

							continue;
						}
					 	originalstring+=(str+'\n');
			
					  
					 }
					//System.out.println (originalstring);
	
			  
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
