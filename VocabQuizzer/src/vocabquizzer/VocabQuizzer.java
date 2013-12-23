package vocabquizzer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VocabQuizzer
{
	static final LinkedHashMap<String, String[][]> ALL_VOCAB_WORDS = createAllVocabWords();
	
	public static void main(String[] args)
	{
		ArrayList<String> selectedLessons = null;
		while (true)
		{
			selectedLessons = LessonChooser.getSelectedLessons(ALL_VOCAB_WORDS.keySet(), selectedLessons);
			ArrayList<String[]> wordPairList = new ArrayList<String[]>();
			for (String selectedLesson : selectedLessons)
				for (String[] wordPair : ALL_VOCAB_WORDS.get(selectedLesson))
					wordPairList.add(wordPair);
			Collections.shuffle(wordPairList);
			
			final QuizzerFrame frame = createQuizzerFrame();
			int numberCorrectOnFirstTry = 0;
			int numberCorrectWithMultipleGuesses = 0;
			int numberTranslationShown = 0;
			int wordPairListIndex = 0;
			for (String[] wordPair : wordPairList)
			{
				switch (frame.showNextWord(wordPair[0], wordPair[1], wordPairListIndex + "/" + wordPairList.size(), numberCorrectOnFirstTry,
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
			final int finalNumberCorrectOnFirstTry = numberCorrectOnFirstTry;
			final int finalNumberCorrectWithMultipleGuesses = numberCorrectWithMultipleGuesses;
			final int finalNumberTranslationShown = numberTranslationShown;
			try
			{
				SwingUtilities.invokeAndWait(new Runnable()
				{
					@Override
					public void run()
					{
						JOptionPane.showMessageDialog(frame, finalNumberCorrectOnFirstTry + " Correct on the first try.\n" + finalNumberCorrectWithMultipleGuesses +
								" Correct with multiple guesses.\n" + finalNumberTranslationShown + " Translations shown.", "Vocab Quizzer Results",
								JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
					}
				});
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
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
		allVocabWords.put("Lesson 8", new String[][]{
				{"(גָּדֹל) גָּדוֹל", "great, large"},
				{"זָקֵן", "old (of persons only)"},
				{"חָדָשׁ", "new"},
				{"חָזָק", "strong"},
				{"חַי", "living"},
				{"חָכָם", "wise"},
				{"יָפֶה", "beautiful, fair, handsome"},
				{"יָשָׁר", "straight, right, upright"},
				{"מַר", "bitter"},
				{"(קָדֹשׁ) קָדוֹשׁ", "holy"},
				{"קָטֹן", "small"},
				{"(קָרֹב) קָרוֹב", "near"},
				{"קָשֶׁה", "hard, difficult"},
				{"רַב", "many, much, great"},
				{"(רָחֹק) רָחוֹק", "far, distant"},
				{"רַע", "evil, bad"},
				{"רָעָה", "(an) evil"},
				{"תָּמִים", "perfect, complete, whole"}
		});
		allVocabWords.put("Personal Pronouns", new String[][]{
				{"אֲנִי ,אָנֹכִי", "I"},
				{"אַתָּה", "you (ms)"},
				{"אַתְּ", "you (fs)"},
				{"הוּא", "he/it"},
				{"הִיא", "she/it"},
				{"אֲנַ֣חְנוּ ,נַ֣חְנוּ ,אֲנוּ", "we"},
				{"אַתֶּם", "you (mp)"},
				{"אַתֶּן ,אַתֵּ֣נָה", "you (fp)"},
				{"הֵם ,הֵ֣מָּה", "they (m)"},
				{"הֵן ,הֵ֣נָּה", "they (f)"}
		});
		allVocabWords.put("Demonstrative Pronouns", new String[][]{
				{"זֶה", "this (m)"},
				{"זֹאת", "this (f)"},
				{"הוּא", "that (m)"},
				{"הִיא", "that (f)"},
				{"אֵ֣לֶּה", "these"},
				{"הֵ֣מָּה (הֵם)", "those (m)"},
				{"הֵ֣נָּה (הֵן)", "those (f)"}
		});
		allVocabWords.put("Lesson 9", new String[][]{
				{"אֶ֣בֶן", "stone"},
				{"דּוֹר", "generation"},
				{"יְרוּשָׁלַ֣יִם", "Jerusalem"},
				{"כֹּה", "thus"},
				{"לֶ֣חֶם", "bread"},
				{"מִדְבָּר", "wilderness, desert"},
				{"מָה", "What?"},
				{"מִי", "Who?"},
				{"מִשְׁפָּט", "judgement, justice"},
				{"משֶׁה", "Moses"},
				{"נָבִיא", "prophet"},
				{"נַ֣עַר", "lad, youth"},
				{"נַעֲרָה", "maiden, young woman"},
				{"סֵ֣פֶר", "book"},
				{"פֶּן", "lest"},
				{"רֶ֣גֶל", "foot"},
				{"שֶׁ֣מֶן", "oil, fat"},
				{"תּוֹרָה", "law, instruction"}
		});
		allVocabWords.put("Lesson 10", new String[][]{
				{"אֹ֣הֶל", "tent"},
				{"אֱמֶת", "truth"},
				{"אֵשׁ", "fire"},
				{"דָּם", "blood"},
				{"זָהָב", "gold"},
				{"חַיָּה", "living thing, animal"},
				{"חָכְמָה", "wisdom"},
				{"חֶ֣סֶד", "goodness, kindness"},
				{"יַ֣יִן", "wine"},
				{"כּוֹכָב", "star"},
				{"כֶּ֣סֶף", "silver"},
				{"מַלְכָּה", "queen"},
				{"מִצְרַ֣יִם", "Egypt"},
				{"סוּס", "horse"},
				{"עֵת", "time"},
				{"רֵעַ", "friend"},
				{"פֶּה", "mouth"},
				{"תְּהוֹם", "great deep, abyss"}
		});
		allVocabWords.put("Lesson 11", new String[][]{
				{"אָהַב", "he loved"},
				{"אָמַר", "he said"},
				{"בָּרָא", "he created"},
				{"הָיָה", "he was, became"},
				{"הָלַךְ", "he walked, went"},
				{"יָדַע", "he knew"},
				{"יַחְדָּו", "together"},
				{"יֶ֣לֶד", "child"},
				{"כָּבוֹד", "glory, honor"},
				{"תֵּבָה", "ark"},
				{"עוֹד", "again, yet, still"},
				{"עַל־פְּנֵי", "over, above"},
				{"עַתָּה", "now"},
				{"פֹּה", "here"},
				{"צְבָאוֹת", "hosts, armies"},
				{"יְהוָה צְבָאוֹת", "LORD of hosts"},
				{"שַׁבָּת", "sabbath"},
				{"שָׁם", "there"},
				{"שְׁנֵיהֶם", "the two of them"}
		});
		allVocabWords.put("Lesson 12", new String[][]{
				{"אוֹ", "or"},
				{"אוֹת", "sign"},
				{"אֵל", "God"},
				{"הִנֵּה ,הֵן", "behold"},
				{"זֶ֣רַע", "seed"},
				{"חֲצִי", "half"},
				{"לָקַח", "he took"},
				{"מָלַךְ", "he reigned, became king"},
				{"מָצָא", "he found"},
				{"נָפַל", "he fell"},
				{"עָבַד", "he served"},
				{"עָלָה", "he went up"},
				{"פָּקַד", "he visited, appointed"},
				{"קָרָא", "he called"},
				{"שָׁכַב", "he lay down"},
				{"שָׁלַח", "he sent"},
				{"שָׁמַע", "he heard, obeyed"},
				{"שָׁמַר", "he kept"}
		});
		allVocabWords.put("Feminine Absolute Numbers", new String[][]{
				{"אַחַת", "1"},
				{"שְׁתַּ֣יִם", "2"},
				{"שָׁלוֹשׁ", "3"},
				{"אַרְבַּע", "4"},
				{"חָמֵשׁ", "5"},
				{"שֵׁשׁ", "6"},
				{"שֶׁ֣בַע", "7"},
				{"שְׁמֹנֶה", "8"},
				{"תֵּ֣שַׁע", "9"},
				{"עֶ֣שֶׂר", "10"}
		});
		allVocabWords.put("Lesson 13", new String[][]{
				{"אָכַל", "he ate"},
				{"גָּנַב", "he stole"},
				{"דְּמוּת", "likeness, image"},
				{"דַּ֣עַת", "knowledge"},
				{"הַיִוֹם", "today"},
				{"הֵיכָל", "temple"},
				{"חֹ֣דֶשׁ", "new moon, month"},
				{"חוֹמָה", "wall"},
				{"כָּתַב", "he wrote"},
				{"לָכֵן", "therefore"},
				{"מַלְכּוּת", "kingdom"},
				{"נֶ֣גֶב", "Negev, dry country, south"},
				{"נָתַן", "he gave"},
				{"עָמַד", "he stood"},
				{"עָשָׂה", "he did, made"},
				{"צֹאן", "flock, sheep"},
				{"צַדִּיק", "righteous one"},
				{"צֶ֣לֶם", "image, likeness"}
		});
		allVocabWords.put("Lesson 14", new String[][]{
				{"אֶחָד", "one"},
				{"אֵת", "with"},
				{"בּדל", "he separated, divided"},
				{"בָּנָה", "he built"},
				{"בּקשׁ", "he sought"},
				{"בַּרְזֶל", "iron"},
				{"דּבר", "he spoke"},
				{"זָכָר", "male"},
				{"כֹּהֵן", "priest"},
				{"כֶּ֣רֶם", "vineyard"},
				{"כָּרַת", "he cut, cut off"},
				{"מִין", "species, kind"},
				{"מִלְחָמָה", "war, battle"},
				{"נְחֹ֣שֶׁת", "copper, bronze"},
				{"נְקֵבָה", "female"},
				{"עָנָן", "cloud"},
				{"פַּר", "young bull"},
				{"קֶ֣רֶב", "midst"}
		});
		allVocabWords.put("Lesson 15", new String[][]{
				{"אַ֣יִל", "ram"},
				{"בּוֹא", "to come, go"},
				{"זָכַר", "he remembered"},
				{"חָיָה", "he lived, revived"},
				{"מָשַׁל", "he ruled"},
				{"עֶ֣בֶד", "servant, slave"},
				{"עָבַר", "he passed over, through"},
				{"עוֹלָם", "eternity"},
				{"קוּם", "to arise, stand"},
				{"שָׂפָה", "lip, speech, edge"},
				{"שָׁאַל", "he asked"},
				{"שָׁבַר", "he broke in pieces"},
				{"שׁוּב", "to turn, return"},
				{"שׁוֹפָר", "ram's horn, trumpet"},
				{"שָׁכַח", "he forgot"},
				{"שָׁפַט", "he judged, delivered"},
				{"תָּמִיד", "continuously"},
				{"תְּפִלָּה", "prayer"}
		});
		allVocabWords.put("Lesson 16", new String[][]{
				{"בֶּ֣טֶן", "belly, body, womb"},
				{"בּרך", "he blessed"},
				{"גָּאַל", "he redeemed"},
				{"הָלַל", "he praised"},
				{"חַטָּאת", "sin"},
				{"לחם", "he fought"},
				{"לָמַד", "he learned"},
				{"מָלֵא", "he was full"},
				{"מָלַט", "he escaped"},
				{"נָשָׂא", "he lifted, carried"},
				{"סָתַר", "he concealed"},
				{"עָוֹן", "iniquity, guilt"},
				{"פֶּ֣שַׁע", "rebellion, transgression"},
				{"קָבַר", "he buried"},
				{"רָאָה", "he saw"},
				{"רָקִיעַ", "expanse, firmament"},
				{"שָׂרַף", "he burned"},
				{"שָׁפַךְ", "he poured out"}
		});
		allVocabWords.put("Lesson 17", new String[][]{
				{"בָּטַח", "he trusted"},
				{"גָּדַל", "he was (became) great"},
				{"דָּרַשׁ", "he sought, inquired"},
				{"דֶּ֣שֶׁא", "grass"},
				{"הָרַג", "he killed, slew"},
				{"זָבַח", "he sacrificed"},
				{"חָזַק", "he was (became) strong"},
				{"חָשַׁב", "he thought, devised, reckoned"},
				{"כָּבֵד", "he was (became) heavy"},
				{"כָּנָף", "wing, skirt"},
				{"כּפר", "he covered, made atonement"},
				{"לָבַשׁ", "he put on, wore"},
				{"נַ֣חַל", "torrent valley, wadi"},
				{"עָזַב", "he abandoned, left, forsook"},
				{"קָרַב", "he drew near, approached"},
				{"רָדַף", "he pursued, persecuted"},
				{"שֵׁ֣בֶט", "rod, staff, scepter, tribe"},
				{"שָׁכַן", "he settled, dwelt"}
		});
		allVocabWords.put("Lesson 18", new String[][]{
				{"יוֹמָם", "daily"},
				{"יָצָא", "he went out"},
				{"יָרֵא", "he feared"},
				{"יָשַׁב", "he sat, dwelt"},
				{"ישׁע", "he saved, delivered"},
				{"מוּת", "to die"},
				{"נצל", "he delivered"},
				{"ספר", "he counted"},
				{"עָזַר", "he helped"},
				{"עָנָה", "he answered, replied"},
				{"פּלל", "he prayed"},
				{"פָּרָה", "he was fruitful"},
				{"צוה", "he commanded"},
				{"רָבָה", "he became many, multiplied"},
				{"שִׂים", "to put, place"},
				{"שָׂמַח", "he rejoiced, was glad"},
				{"שׁבע", "he swore"},
				{"שׁלך", "he cast, threw"}
		});
		allVocabWords.put("Lesson 19", new String[][]{
				{"אָבַד", "he perished"},
				{"אָסַף", "he gathered"},
				{"בִּין", "to understand, discern"},
				{"בָּרַח", "he fled"},
				{"חָדַל", "he ceased"},
				{"חָטָא", "he sinned, missed the mark"},
				{"יָכֹל", "he was able"},
				{"יָלַד", "he begot (children)"},
				{"יָסַף", "he added"},
				{"יָרַד", "he went down"},
				{"יָרַשׁ", "he possessed, subdued"},
				{"כּוּן", "to be fixed, firm, established"},
				{"כָּלָה", "he (it) was completed, finished"},
				{"מאן", "he refused"},
				{"מָאַס", "he rejected, despised"},
				{"מָכַר", "he sold"},
				{"נגד", "he told, declared"},
				{"נָטָה", "he stretched out, extended"},
				{"נכה", "he struck, killed"},
				{"רוּם", "to be high, exalted"},
				{"רָפָא", "he healed, cured"},
				{"רָצָה", "he was gracious, took delight in"},
				{"שָׂנֵא", "he hated"},
				{"שָׁתָה", "he drank"}
		});
		return allVocabWords;
	}
	
	static final QuizzerFrame createQuizzerFrame()
	{
		class QuizzerFrameCreator implements Runnable
		{
			private QuizzerFrame quizzerFrame = null;
			
			@Override
			public void run()
			{
				quizzerFrame = new QuizzerFrame();
			}
		}
		QuizzerFrameCreator quizzerFrameCreator = new QuizzerFrameCreator();
		try
		{
			SwingUtilities.invokeAndWait(quizzerFrameCreator);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return quizzerFrameCreator.quizzerFrame;
	}
}