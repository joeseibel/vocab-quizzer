package vocabquizzer;

public class VocabQuizzerTester
{
	public static void main(String[] args)
	{
		testLessonInOrder("Lesson 7");
//		testAllInOrder();
//		testRange(5, 10);
//		testIndividual(16);
	}
	
	private static void testLessonInOrder(String lessonName)
	{
		QuizzerFrame frame = new QuizzerFrame();
		for (String[] wordPair : VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName))
			frame.showNextWord(wordPair[0], wordPair[1]);
		frame.dispose();
	}
	
//	@SuppressWarnings("unused")
//	private static void testAllInOrder()
//	{
//		QuizzerFrame frame = new QuizzerFrame();
//		for (String[] wordPair : VOCAB_WORDS)
//			frame.showNextWord(wordPair[0], wordPair[1]);
//		frame.dispose();
//	}
	
//	@SuppressWarnings("unused")
//	private static void testRange(int lowerBound, int upperBound)
//	{
//		QuizzerFrame frame = new QuizzerFrame();
//		for (int i = lowerBound; i <= upperBound; i++)
//			frame.showNextWord(VOCAB_WORDS[i][0], VOCAB_WORDS[i][1]);
//		frame.dispose();
//	}
	
//	@SuppressWarnings("unused")
//	private static void testIndividual(int index)
//	{
//		QuizzerFrame frame = new QuizzerFrame();
//		frame.showNextWord(VOCAB_WORDS[index][0], VOCAB_WORDS[index][1]);
//		frame.dispose();
//	}
}