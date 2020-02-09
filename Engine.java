import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;

public class Engine 
{
	
	//method that converts a string to an int based on phone pad rules
	public int wordToNumber(String word)
	{
		int num = 0;
		word = word.toLowerCase();
		char[] wordArray = word.toCharArray();
		for(int count = 0; count < wordArray.length; count++)
		{
			num = num * 10;
			char letter = wordArray[count];
			switch(letter)
			{
				case 'a': case 'b': case 'c':
					num = num + 2;
					break;
				
				case 'd': case 'e': case 'f':
					num = num + 3;
					break;
					
				case 'g': case 'h': case 'i':
					num = num + 4;
					break;
				
				case 'j': case 'k': case 'l':
					num = num + 5;
					break;
				
				case 'm': case 'n': case 'o':
					num = num + 6;
					break;
				
				case 'p': case 'q': case 'r': case 's':
					num = num + 7;
					break;
				
				case 't': case 'u': case 'v':
					num = num + 8;
					break;
				
				case 'w': case 'x': case 'y': case 'z':
					num = num + 9;
					break;
					
				default:				
					num = -1;
					break;
				
			}
			if(num == -1)
			{
				break;
			}
			
		}
		return num;
	}
	
	//searches Dictionary.txt for a match to the given number
	public void dictionaryComparison(int phoneNumber, JTextArea textBox)
	throws IOException {
		File file = new File("Dictionary.txt");
		Scanner fileInput = new Scanner(file);
		int wordFound = 0;
		while(fileInput.hasNext())
		{
			String word = fileInput.next();
			int num = wordToNumber(word);
			if(num == phoneNumber)
			{
				textBox.setText(textBox.getText() + word + "\n");
				wordFound++;
				//continue
			}
			
			//checks the front of a phone number for words
			int phoneCopy = phoneNumber;
			int counter = 0;
			while(phoneCopy > num)
			{
				phoneCopy = phoneCopy / 10;
				counter++;
				if(num == phoneCopy)
				{
					int remainder = 0;
					double exponent = Math.pow(10, counter);
					remainder = phoneNumber % (int)exponent;
					//System.out.println(word + remainder);
					textBox.setText(textBox.getText() + word + remainder + "\n");
					wordFound++;
					break;
				}
				
			}
			
			//checks the back of a phone number for words
			phoneCopy = phoneNumber;
			boolean mostSignificantDigit = false;
			int exponent = 1;
			while(mostSignificantDigit == false)
			{
				if(phoneCopy / exponent < 10)
				{
					mostSignificantDigit = true;
				}
				else
				{
					exponent = exponent * 10;
				}
			}
			
			//checks the front of a phone number for words
			counter = 0;
			int exponentCopy = exponent;
			while(phoneCopy > num)
			{
				phoneCopy = phoneCopy % exponentCopy;
				if(phoneCopy == num)
				{
					int remainder = phoneNumber / exponentCopy;
					//System.out.println(remainder + word);
					textBox.setText(textBox.getText() + remainder + word + "\n");
					wordFound++;
					break;
				}
				exponentCopy = exponentCopy / 10;
			}
			//System.out.println(fileInput.next());
		}
		if(wordFound == 0)
		{
			textBox.setText("");
			textBox.setText("No matching words were found");
		}
	}
	
	//writes a given word to the Dictionary.txt file
	public void writeToDictionary(String word)
	throws IOException {
		File file = new File("Dictionary.txt");
		
		if (!file.exists()) 
		{
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file, true);
		fw.write(word);
		fw.close();
	}

}
