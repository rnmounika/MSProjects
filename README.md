# MSProjects
ITCS 6114/8114: Algorithms and Data Structures                   
                                                 LZW Compression

Mounika Ramapuram Naik (801166351)

The Lempel–Ziv–Welch (LZW) is a lossless data compression algorithm, capable of processing any file types (text files, binaries, etc.). This algorithm reads an input sequence of symbols, groups the symbols into strings, and represents the strings with integer codes which takes the less space compared to that of the strings and hence Encoding is achieved. In the decoding part integers codes are converted into strings.

Project Descriptions:
Programming Language used: JAVA
Complier Version: 13.0.2
Encoder and Decoder java files have been implemented to achieve LZW algorithm.

Steps involved in Encoding: 
•	First step is to read symbols from input file one by one and append to string 
•	Create a table of type HashMap with all the default ASCII characters (0 to 255) with character as key and ascii as value 
•	Set maximum table size based on the input bit length 
•	Code reads one symbols at a time and appends them one by one to the current string (initially null). Each time it appends a symbol to the string, it checks whether the resulting string has a code in the dictionary. 
•	If it does, it reads in the next symbol and appends it to the current string. If the resulting string does not exist in the dictionary, it means it has found a new string: it outputs the code corresponding to the string without the newest symbol, adds the string concatenated with the newest symbol to the table with its code (which is the previous largest code value incremented by one), and resets the current string to the newest symbol
•	The algorithm continues to process symbols from the input sequence, building new strings until the sequence is exhausted, and it then outputs the code for the remaining string
Encoder.java Implementation:
In Encoder.java mainly three functions have been implemented.
1)	GetFileContent() 
This method is to get the input string from the input text file that to be encoded using FileInputStream, InputStreamReader and BufferedReader.
2)	encodingMethod()
This method performs actual encoding of the input data into integer codes and is implemented using pseudocode.
          Map<String,Integer> table = new HashMap<String,Integer>();
HashMap used for mapping strings to the code value as it can be easily accessed using keys and it’s a dynamic data structure that grows with the size of the entries and so its space efficient.
         List<Integer> output= new ArrayList<Integer>();
List is also dynamic and is used to return integer values after encoding and this can be easily iterated to write the resulting codes to file in UTF-16BE format.
3)	printToFile()
This method is used to print the encoded values in UTF-16BE format to encoded file with extension LZW.
OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-16BE");

Steps involved in Decoding: 
•	Read the encoded integer values from the encoded file.
•	Create a table of type HashMap with all the default ASCII characters (0 to 255) with ascii code as key and character as value.
•	Set maximum table size based on the input bit length 
•	Read an integer from the encoded sequence, look for the code in the table by using it as key, and output the string associated with the code.
•	Thereafter, the decoder reads in a new code, finds the new string indexed by this code, and outputs it.
•	The first character of this new string is concatenated to the previously decoded string. This new concatenation is added to the dictionary with an incremented code (simulating how the strings were added during compression). The decoded new string then becomes the previous string, and the process repeats.
•	If the code is not already in its dictionary, then new string corresponding to this code consists of the previously decoded string with the first character of the previously decoded string appended. Each time it reads in a code that does not exist in the dictionary, it must add the code and the corresponding string to the dictionary. 

Decoder.java Implementation:
In Decoder.java mainly three functions have been implemented.
1)	GetEncodedFileContent() 
This method is to get integer codes to list from the output values of encoder using UTF-16BE format using FileInputStream, InputStreamReader, BufferedReader.
Reader inputStreamReader = new InputStreamReader(inputStream,"UTF-16BE");
2)	decodingMethod()
This method performs reverse of encoding that is to convert the integer codes to the String and is implemented using pseudocode.
 Map<Integer, String> table= new HashMap<Integer, String>();
HashMap used for mapping strings to the code value as it can be easily accessed using keys and it’s a dynamic data structure that grows with the size of the entries and so its space efficient.
StringBuilder decodedValues= new StringBuilder(str);
StringBuilder is used to store the decoded output string.
3)	printToFile()
This method is used to print the decoded output string to a decoded file using FileOutputStream, OutputStreamWriter, BufferedWriter.

Compile the program:
Below is the command prompt syntax for compiling the java file.
                     javac filename.java
Ex: javac Encoder.java / javac Decoder.java
Once the above command is executed, a class file named filename.class is created





Runing the program:
For running both the Encoder and Decoder files we need to pass two arguments.
•	File Name
•	Maximum bit length  

For example, command line syntax for the encoding 
                       Java Encoder.java input.txt 12 
 And that for the decoding 
                       Java Decoder.java input.LZW 12 

The Encoder program produces the compressed file input.LZW in a 16bit format. The Decoder program converts the input.LZW into the original input file again. i.e Decoded text file.

 
Running the Encoder.java and generating the input.LZW file in source folder.

 

Encoded file in the Src folder of the project.

 

Content of the input.LZW file in UTF-16BE character encoding

 
Running the Decoder.java with input.LZW file as input file and generating decoded file.

 

 
Content of the decoded file which is equivalent to the input file content.

Exceptions:

FileNotFoundException:
If invalid file name is entered error is thrown as below:
 

Invalid Input format:
If Incorrect input format is provided code will redirect to provide the correct format:
 

Invalid maximum bit length:
As per the requirements the input bit length should be in range of 9 and 12 or the code will redirect to enter valid bit length.

 

What works/fails:
•	It is assumed that the input files are placed in the same folder as the source code.
•	Encoding/Decoding works on input files containing single/multiple lines with multiple words.
•	Program accepts fixed input bit length in the range 9 to 12. So, if the input length is not in this range then the user is again prompted to enter input in the specified range. ¬¬¬¬¬
•	It is assumed that the text file is given as input to encoder and binary file is given as input to decoder

