package vocabquizzer;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class PrintWordsInOrder
{
	public static void main(String[] args)
	{
		TreeSet<WordEntry> treeSet = new TreeSet<PrintWordsInOrder.WordEntry>();
		for (Entry<String, String[][]> lessonEntry : VocabQuizzer.ALL_VOCAB_WORDS.entrySet())
			for (String[] wordPair : lessonEntry.getValue())
				treeSet.add(new WordEntry(wordPair[0], wordPair[1], lessonEntry.getKey()));
		for (WordEntry wordEntry : treeSet)
			System.out.println(wordEntry);
	}
	
	private static class WordEntry implements Comparable<WordEntry>
	{
		private final String hebrewWord;
		private final String englishWord;
		private final String lesson;
		
		public WordEntry(String hebrewWord, String englishWord, String lesson)
		{
			this.hebrewWord = hebrewWord;
			this.englishWord = englishWord;
			this.lesson = lesson;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			WordEntry o = (WordEntry)obj;
			return hebrewWord.equals(o.hebrewWord) && englishWord.equals(o.englishWord) && lesson.equals(o.lesson);
		}
		
		@Override
		public int compareTo(WordEntry o)
		{
			int comparisonResult = removePointings(hebrewWord).compareTo(removePointings(o.hebrewWord));
			if (comparisonResult == 0)
			{
				comparisonResult = hebrewWord.compareTo(o.hebrewWord);
				if (comparisonResult == 0)
				{
					comparisonResult = englishWord.compareTo(o.englishWord);
					if (comparisonResult == 0)
						comparisonResult = lesson.compareTo(o.lesson);
				}
			}
			return comparisonResult;
		}
		
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder(removePointings(hebrewWord));
			builder.append(";");
			builder.append(hebrewWord);
			builder.append(";");
			builder.append(englishWord);
			builder.append(";");
			builder.append(lesson);
			return builder.toString();
		}
		
		private final static HashSet<Character> acceptableCharacters = getAcceptableCharacters();
		private final static HashSet<Character> pointings = getPointings();
		
		private static HashSet<Character> getAcceptableCharacters()
		{
			HashSet<Character> acceptableCharacters = new HashSet<Character>();
			acceptableCharacters.add('א');
			acceptableCharacters.add('ב');
			acceptableCharacters.add('ח');
			acceptableCharacters.add('ו');
			acceptableCharacters.add('ת');
			acceptableCharacters.add('י');
			acceptableCharacters.add('ש');
			acceptableCharacters.add('ה');
			acceptableCharacters.add('ם');
			acceptableCharacters.add('ד');
			acceptableCharacters.add('ל');
			acceptableCharacters.add('ן');
			acceptableCharacters.add('ר');
			acceptableCharacters.add('ע');
			acceptableCharacters.add('ף');
			acceptableCharacters.add('ק');
			acceptableCharacters.add('ץ');
			acceptableCharacters.add('ג');
			acceptableCharacters.add('ך');
			acceptableCharacters.add('ט');
			acceptableCharacters.add('מ');
			acceptableCharacters.add('נ');
			acceptableCharacters.add('פ');
			acceptableCharacters.add('כ');
			acceptableCharacters.add('צ');
			acceptableCharacters.add('(');
			acceptableCharacters.add(')');
			acceptableCharacters.add(' ');
			acceptableCharacters.add('ז');
			acceptableCharacters.add(',');
			acceptableCharacters.add('ס');
			acceptableCharacters.add('־');
			acceptableCharacters.add('/');
			return acceptableCharacters;
		}
		
		private static HashSet<Character> getPointings()
		{
			HashSet<Character> pointings = new HashSet<Character>();
			pointings.add('ָ');
			pointings.add('ֹ');
			pointings.add('ִ');
			pointings.add('ׁ');
			pointings.add('ּ');
			pointings.add('ֵ');
			pointings.add('ֱ');
			pointings.add('ַ');
			pointings.add('ׂ');
			pointings.add('ְ');
			pointings.add('ֶ');
			pointings.add('֣');
			pointings.add('ֲ');
			pointings.add('ֻ');
			return pointings;
		}
		
		private static String removePointings(String hebrewWord)
		{
			StringBuilder wordWithoutPointings = new StringBuilder();
			for (int i = 0; i < hebrewWord.length(); i++)
			{
				if (acceptableCharacters.contains(hebrewWord.charAt(i)))
					wordWithoutPointings.append(hebrewWord.charAt(i));
				else if (!pointings.contains(hebrewWord.charAt(i)))
				{
					System.out.println("Unknown Character:");
					System.out.println(hebrewWord.charAt(i));
					System.out.println(hebrewWord);
					System.exit(0);
				}
			}
			return wordWithoutPointings.toString();
		}
	}
}