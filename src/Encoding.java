import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;



public class Encoding {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String input="";
		String inputfile= args[0];
      double  bit_length=Integer.parseInt(args[1]);
      System.out.println(inputfile);
      System.out.println(bit_length);
      
      FileReader fr = 
    	      new FileReader(inputfile); 
    	  
    	    int k; 
    	    while ((k=fr.read()) != -1) 
    	      //System.out.print((char) k); 
    	    	input=input+ (char) k;
             
      
      
      double MAX_TABLE_SIZE = Math.pow(2, bit_length);
      String str="";
      List<Integer> encodedValues = new ArrayList<Integer>();
      
      Map<String, Integer> table= new HashMap<String, Integer>();
      for(int i=0; i<255;i++)
      {
    	  table.put(""+(char)i, i);
      }
      
      for(char sym : input.toCharArray())
      {
    	  String str_sym=  str+sym;
    	  if(table.containsKey(str_sym)) 
    	  {
    		  str=str_sym;  		  
    		  
    	  }
    	  else
    	  {
    		  encodedValues.add(table.get(str));
    		  System.out.print(table.get(str)+"           ");
    		  if(table.size()<MAX_TABLE_SIZE)
    		  {
    			  table.put(str_sym, (int)table.size()+1);
    			 // System.out.println(str_sym +"     "+ table.get(str_sym));
    			  str=""+sym;
    		  }
    	  }
      }
      
      if (!str.equals(""))
      {
    	  encodedValues.add(table.get(str));
    	  System.out.print(table.get(str));
      }

         int endIndex = inputfile.lastIndexOf('.');
        String outputfile=  inputfile.substring(0, endIndex)+".lzw";
     // FileWriter writer = new FileWriter("output.txt"); 
      /* FileWriter writer = new FileWriter(outputfile);
      for(int j: encodedValues)
      {
        writer.write(j + System.lineSeparator());
      }
      writer.close();*/
        
       // OutputStream outputStream = new FileOutputStream(outputfile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile),Charset.forName("UTF-16BE") ));
        
   //  Iterator<Integer> Itr = encodedValues.iterator();
	//while (Itr.hasNext()) {
	//		writer.write(Itr.next());
	//	}
//        
//	writer.flush();
//	writer.close();
        
     for(int j: encodedValues)
        {
    // String	m= Integer.toString(j, 2);
     //int a = Integer.parseInt(m,2);
     //  writer.write(m + System.lineSeparator());
    	 writer.write(j);
       }
     writer.flush();
      writer.close();
      
	}

}
