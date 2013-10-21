package vocabquizzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class VocabQuizzer
{
	static final LinkedHashMap<String, String[][]> ALL_VOCAB_WORDS = createAllVocabWords();
	
	public static void main(String[] args)
	{
		ArrayList<String> selectedLessons = LessonChooser.getSelectedLessons(ALL_VOCAB_WORDS.keySet());
		ArrayList<String[]> wordPairList = new ArrayList<String[]>();
		for (String selectedLesson : selectedLessons)
			for (String[] wordPair : ALL_VOCAB_WORDS.get(selectedLesson))
				wordPairList.add(wordPair);
		Collections.shuffle(wordPairList);
		
		QuizzerFrame frame = new QuizzerFrame();
		int numberCorrect = 0;
		int numberCorrectWithMultipleGuesses = 0;
		int numberTranslationShown = 0;
		for (String[] wordPair : wordPairList)
		{
			switch (frame.showNextWord(wordPair[0], wordPair[1]))
			{
				case CORRECT:
					numberCorrect++;
					break;
				case CORRECT_WITH_MULTIPLE_GUESSES:
					numberCorrectWithMultipleGuesses++;
					break;
				case TRANSLATION_SHOWN:
					numberTranslationShown++;
					break;
			}
		}
		JOptionPane.showMessageDialog(frame, numberCorrect + " Correct on the first try.\n" + numberCorrectWithMultipleGuesses + " Correct with multiple guesses.\n" +
				numberTranslationShown + " Translations shown.", "Vocab Quizzer Results", JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
	
	private static LinkedHashMap<String, String[][]> createAllVocabWords()
	{
		LinkedHashMap<String, String[][]> allVocabWords = new LinkedHashMap<String, String[][]>();
		allVocabWords.put("Lesson 3", new String[][]{
				{"אָב", "father, ancestor"},
				{"אָח", "brother"},
				{"אָחוֹת", "sister"},
				{"אִישׁ", "man"},
				{"אִשָּׁה", "woman"},
				{"אֵם", "mother"},
				{"אָדָם", "man, humankind"},
				{"אֱלֹהִים", "God"},
				{"בֵּן", "son"},
				{"בַּת", "daughter"},
				{"יִשְׂרָאֵל", "Israel"},
				{"לֵב", "heart"},
				{"עִיר", "city"},
				{"עוֹף", "bird(s)"},
				{"עַם", "people"},
				{"קוֹל", "voice"},
				{"רֹאשׁ", "head"},
				{"שֵׁם", "name"}
		});
		allVocabWords.put("Lesson 5", new String[][]{
				{"אוֹר", "light"},
				{"אֶ֣רֶץ", "earth"},
				{"אֲשֶׁר", "who, which, what"},
				{"בַּ֣יִת", "house"},
				{"בְּרִית", "covenant"},
				{"גַּן", "garden"},
				{"דָּבָר", "word, thing"},
				{"הַר", "mountain"},
				{"חַג", "feast, festival"},
				{"חֶ֣רֶב", "sword"},
				{"חֹ֣שֶׁךְ", "darkness"},
				{"טוֹב", "good"},
				{"יָם", "sea"},
				{"מַ֣יִם", "water"},
				{"מֶ֣לֶךְ", "king"},
				{"רוּחַ", "spirit, wind"},
				{"שָׁלוֹם", "peace"},
				{"שָׁנָה", "year"}
		});
		allVocabWords.put("Lesson 6", new String[][]{
				{"אַחֲרֵי", "after, behind"},
				{"אֶל", "to, into, toward"},
				{"בֵּין", "between"},
				{"לִפְנֵי", "before, in the presence of"},
				{"מִן", "from, out of"},
				{"עַד", "until, unto"},
				{"עִם", "with"},
				{"עַל", "upon, above, about"},
				{"תַּ֣חַת", "under, instead of"},
				{"אֵין", "there is not"},
				{"בֹּ֣קֶר", "morning"},
				{"יָד", "hand"},
				{"יוֹם", "day"},
				{"יֵשׁ", "there is, there are"},
				{"לֹא", "not"},
				{"לַ֣יְלָה", "night"},
				{"מָקוֹם", "place"},
				{"עֵץ", "tree"},
				{"עֶ֣רֶב", "evening"},
				{"פְּרִי", "fruit"}
		});
		allVocabWords.put("Lesson 7", new String[][]{
				{"אֲדָמָה", "ground, earth"},
				{"אֲדֹנָי", "Lord"},
				{"יְהוָה", "LORD"},
				{"בְּהֵמָה", "cattle"},
				{"בָּשָׂר", "flesh"},
				{"בְּתוֹךְ", "in the midst of"},
				{"גַּם", "also"},
				{"דֶּ֣רֶךְ", "way"},
				{"יַבָּשָׁה", "dry ground"},
				{"כִּי", "for, that, because"},
				{"כֹּל", "all, every"},
				{"כֵּן", "thus, so"},
				{"מְאֹד", "very, exceedingly"},
				{"מִצְוָה", "commandment"},
				{"נֶ֣פֶשׁ", "soul, living being"},
				{"עָפָר", "dust"},
				{"שָׂדֶה", "field"},
				{"שָׁמַ֣יִם", "heavens, sky"}
		});
		return allVocabWords;
	}
}