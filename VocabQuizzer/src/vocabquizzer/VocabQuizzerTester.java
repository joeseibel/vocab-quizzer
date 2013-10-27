package vocabquizzer;

public class VocabQuizzerTester
{
	public static void main(String[] args)
	{
		testLessonInOrder("Lesson 9");
//		testIndividual("Personal Pronouns", 5);
	}
	
	private static void testLessonInOrder(String lessonName)
	{
		QuizzerFrame frame = new QuizzerFrame();
		for (String[] wordPair : VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName))
			frame.showNextWord(wordPair[0], wordPair[1]);
		frame.dispose();
	}
	
	@SuppressWarnings("unused")
	private static void testIndividual(String lessonName, int index)
	{
		QuizzerFrame frame = new QuizzerFrame();
		String[] wordPair = VocabQuizzer.ALL_VOCAB_WORDS.get(lessonName)[index];
		frame.showNextWord(wordPair[0], wordPair[1]);
		frame.dispose();
	}
}