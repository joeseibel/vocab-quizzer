package vocabquizzer;

import javax.swing.SwingUtilities;

public class VocabQuizzerTester
{
	public static void main(String[] args)
	{
		testLessonInOrder("Lesson 13");
//		testIndividual("Lesson 10", 7);
	}
	
	private static void testLessonInOrder(String lessonName)
	{
		final QuizzerFrame frame = VocabQuizzer.createQuizzerFrame();
		int numberCorrectOnFirstTry = 0;
		int numberCorrectWithMultipleGuesses = 0;
		int numberTranslationShown = 0;
		int wordPairListIndex = 0;
		for (String[] wordPair : VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName))
		{
			switch (frame.showNextWord(wordPair[0], wordPair[1], wordPairListIndex + "/" + VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName).length, numberCorrectOnFirstTry,
					numberCorrectWithMultipleGuesses, numberTranslationShown))
			{
				case CORRECT:
					numberCorrectOnFirstTry++;
					break;
				case CORRECT_WITH_MULTIPLE_GUESSES:
					numberCorrectWithMultipleGuesses++;
					break;
				case TRANSLATION_SHOWN:
					numberTranslationShown++;
					break;
			}
			wordPairListIndex++;
		}
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				frame.dispose();
			}
		});
	}
	
	@SuppressWarnings("unused")
	private static void testIndividual(String lessonName, int index)
	{
		final QuizzerFrame frame = VocabQuizzer.createQuizzerFrame();
		String[] wordPair = VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName)[index];
		frame.showNextWord(wordPair[0], wordPair[1], "0/1", 0, 0, 0);
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				frame.dispose();
			}
		});
	}
}