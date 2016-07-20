import java.io.*;
import java.util.*;

class StringOb
{
	public static void traverse(String path)
	{
		

		String files;
		
	
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
				find(path+files);
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

public static void find(String path)
{
	String str;
	int caninsert = 0;
	String originalstring;
	String ofs;
	String str1;
	
	System.out.println(path);
	
	
	//String teststr;
	try{
	
		FileInputStream fstream = new FileInputStream(path);
  		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
		str = br.readLine();
		originalstring=(str+'\n');
	  
		
		while ((str = br.readLine()) != null) 
	  	{
			//originalstring+=(str+'\n');
					
			if(str.contains("const-string") && !str.contains("\\n") && !str.contains("\\u") && !str.contains("\\r"))
			{
				char[] teststr = str.toCharArray();
				String vstr = "";

				boolean flagenstr = false;
				int flagv=0;

				for (int i=0;i<teststr.length;i++)
            			{
					if (teststr[i]=='v'||teststr[i]=='p')
					{
						flagv++;
					}
					if (teststr[i]==',')
                    			{flagv++;}
					if (teststr[i]=='"')
					{
						flagenstr=true;
						flagv++;
					}
					if (flagv==1)
					{
						vstr+=teststr[i];
					}
					else if(flagenstr==true && (i!=teststr.length-1))
					{
						if ((teststr[i]<='z'&&teststr[i]>='a')||(teststr[i]<='Z'&&teststr[i]>='A')||teststr[i]=='"')
							{
								if (teststr[i]!='"')
								{
									teststr[i]=encryptString(teststr[i]);
									//System.out.println (teststr[i]);							
								}
							}
						else
						{}
					}
								
				}
				
				//System.out.println(originalstring);

				String newstr;
			  	originalstring+='\n';
	
				if (vstr!="p"&&vstr.length()<=2)
				{

					str = String.valueOf(teststr);
					originalstring += str;

					/*for (int i=0;i<teststr.length;i++)
		    			{ 	//str = "";
						//System.out.println(teststr[i]);
						originalstring += teststr[i];
						
					}*/
					originalstring +=('\n');
					originalstring +=('\n');
			
					newstr = "    invoke-static {"+vstr+"}, Lcom/DecryptFunc;->decryptionMethod(Ljava/lang/String;)Ljava/lang/String;";
					originalstring+=(newstr+'\n');
					originalstring+='\n';
					//Insert moving decrypt string result code back to the const string after decrypt string function code.
					newstr = "    move-result-object "+vstr;
					//System.out.println(str);
					//System.out.println(newstr);
					originalstring+=(newstr+'\n');
				}
				else
				{
					originalstring+=(str+'\n');
				}
				continue;
						
			}
			
			originalstring+=(str+'\n');
		}
		
			
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



static char encryptString(char c)
{
	int t = c;

    if (t<='Z'&&t>='A')
    {
        t-='A';
        t=(t+10)%26;
        t+='A';
    }
    else if (t<='z'&&t>='a')
    {
        t-='a';
        t=(t+10)%26;
        t+='a';
    }
	c = (char) t;
    return c;
}


	public static void main(String args[])
	{
		
		traverse(args[0]);
	
	}

}
