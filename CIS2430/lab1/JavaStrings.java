import java.util.Scanner;

public class JavaStrings
{
	static boolean end = false;

	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		String[] sentences = new String[10];
		while(end == false)
		{
			System.out.println("Welcome to the main menu!\nPlease enter a number to choose one of the following options:\n\n (1) Enter a full sentence.\n (2) Print out all sentences entered thus far in the given order.\n (3) Print out the number of sentences that have been entered thus far.\n (4) Print out all sentences entered thus far in the reverse order.\n (5) Print out the number of characters in all sentences combined.\n (6) Calculate the total number of vowels contained in all stored sentences.\n (7) Perform search for a given word using case insensitive comparisons.\n (8) Perform search for a given word using case sensitive comparisons.\n (9) End program.\n");
			int answer = keyboard.nextInt();
			if(answer > 9 || answer < 1)
			{
				System.out.println("Error: Invalid input. You must enter a number from 1 to 9.\n");
			}
			if(answer == 1)
			{
				System.out.println("Please enter a sentence:\n");
				keyboard.nextLine();
				String sentence = keyboard.nextLine();
				boolean failure = true;
				for (int i = 0; i < sentences.length; i++) 
				{
					if(sentences[i] == null && failure == true)
					{
						sentences[i] = sentence;
						failure = false;
					}
				}
				if(failure)
				{
					System.out.println("Sentence could not be stored. Maximum number of sentences reached.\n");
				}
			}
			if(answer == 2)
			{
				for(int i = 0; i < sentences.length; i++)
				{
					if(sentences[i] != null)
					{
						System.out.println(sentences[i] + "\n");	
					}
				}
			}
			if(answer == 3)
			{
				System.out.println(sentenceCount(sentences) + "\n");
			}
			if(answer == 4)
			{
				for(int i = sentenceCount(sentences) - 1; i > -1 ; i--)
				{
					if(sentences[i] != null)
					{
						System.out.println(sentences[i] + "\n");	
					}
				}
			}
			if(answer == 5)
			{
				int count = 0;
				for(int i = 0; i < sentences.length; i++)
				{
					if(sentences[i] != null)
					{
						count += sentences[i].length(); // String.length() returns the number of characters in the string
					}
				}
				System.out.println(count + "\n");
			}
			if(answer == 6)
			{
				int count = 0;
				for(int i = 0; i < sentenceCount(sentences); i++)
				{
					for (int j = 0; j < sentences[i].length(); j++) 
					{
						if(sentences[i].toLowerCase().charAt(j) == 'a' || sentences[i].toLowerCase().charAt(j) == 'e' || sentences[i].toLowerCase().charAt(j) == 'i' || sentences[i].toLowerCase().charAt(j) == 'o' || sentences[i].toLowerCase().charAt(j) == 'u')
						{
							count++;
						}	
					}
				}
				System.out.println(count + "\n");
			}
			if(answer == 7)
			{
				// word search case insensitive
				System.out.println("Please enter the word to search:\n");
				boolean wordfound = false;
				String word = keyboard.next();
				for(int i = 0; i < sentenceCount(sentences); i++)
				{
					//String.split("[, ?!.;]") separates all the words in the sentence into their own string, returning as an array of words 
					String[] words = sentences[i].split("[, ?!.;]+");
					//Compare each word in the sentence to the search word
					for(int j = 0; j < words.length; j++)
					{
						if(words[j] != null)
						{
							if(word.equalsIgnoreCase(words[j]))
							{
								System.out.println("Word Found!");
								wordfound = true;
								System.out.println(sentences[i] + "\n");
							}
						}
					}
				}
				if(wordfound == false)
				{
					System.out.println("Word search unsuccessful. No matching words found.\n");
				}
			}
			if(answer == 8)
			{
				// word search case sensitive
				System.out.println("Please enter the word to search:\n");
				boolean wordfound = false;
				String word = keyboard.next();
				for(int i = 0; i < sentenceCount(sentences); i++)
				{
					//String.split("[, ?!.;]") separates all the words in the sentence into their own string, returning as an array of words 
					String[] words = sentences[i].split("[, ?!.;]+");
					//Compare each word in the sentence to the search word
					for(int j = 0; j < words.length; j++)
					{
						if(words[j] != null)
						{
							if(word.equals(words[j]))
							{
								System.out.println("Word Found!");
								wordfound = true;
								System.out.println(sentences[i] + "\n");
							}
						}
					}
				}
				if(wordfound == false)
				{
					System.out.println("Word search unsuccessful. No matching words found.\n");
				}
			}
			if(answer == 9)
			{
				end = true;
			}
		}
	}
	public static int sentenceCount(String[] sentences)
	{
		int count = 0;
		for(int i = 0; i < sentences.length; i++)
		{
			if(sentences[i] != null)
			{
				count++;
			}
		}
		return(count);
	}
}