import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Decoding {

	public static void main(String[] args) throws IOException,FileNotFoundException, UnsupportedEncodingException {
		
       String encodedFileName = args[0];
       int bitLength= Integer.parseInt(args[1]);
       
       //int[] a= {97,98,257,256};
       double maxTableSize= Math.pow(2,bitLength);
       
       
       
       Map<Integer,String> table = new HashMap<Integer,String>();
       
       for(int i=0; i<255;i++)
       {
    	   table.put(i,""+(char)i);
       }
       
       String newString ="";
        List<Integer> get_compress_values= new ArrayList<Integer>();
        
        BufferedReader br = null;
		InputStream inputStream  = new FileInputStream(encodedFileName);
		Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-16BE"); // The Charset UTF-16BE is used to read the 16-bit compressed file.
	
		 br = new BufferedReader(inputStreamReader);
		  
		double value=0;
		
         // reads to the end of the stream 
         while((value = br.read()) != -1)
         {
        	 get_compress_values.add((int) value);
         }
//        get_compress_values.add(a[0]);
//        get_compress_values.add(a[1]);
//        get_compress_values.add(a[2]);
//        get_compress_values.add(a[3]);
        
       
        
        String string = "" + table.get((int) get_compress_values.remove(0));
        
        StringBuffer decoded_values = new StringBuffer(string);
        
      for(int check_key: get_compress_values)
      {
    	  
    	  if(table.containsKey(check_key))
   	       newString=table.get(check_key);
    	  else
    		  newString= string+string.charAt(0);
    	  
    	  decoded_values.append(newString);
    	  
    	  if(table.size()< maxTableSize)
    	  {
    		  table.put(table.size()+1,string+newString.charAt(0));
    		  
    	  }
    	  
    	  string= newString;
    	  
   		  
      }
      System.out.print(decoded_values.toString());
      
      int endIndex = encodedFileName.lastIndexOf('.');
      String outputfile=  encodedFileName.substring(0, endIndex)+"_decoded.txt";
      
      FileWriter writer = new FileWriter(outputfile, true);
		 BufferedWriter bufferedWriter = new BufferedWriter(writer);
		 
		 bufferedWriter.write(decoded_values.toString());
		 bufferedWriter.flush();
			
			bufferedWriter.close();	
       
       
	}

}
