package vocabquizzer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

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
			final ArrayList<String[]> correctOnFirstTry = new ArrayList<String[]>();
			final ArrayList<String[]> correctWithMultipleGuesses = new ArrayList<String[]>();
			final ArrayList<String[]> translationShown = new ArrayList<String[]>();
			int wordPairListIndex = 0;
			for (String[] wordPair : wordPairList)
			{
				switch (frame.showNextWord(wordPair[0], wordPair[1], wordPairListIndex + "/" + wordPairList.size(), correctOnFirstTry.size(),
						correctWithMultipleGuesses.size(), translationShown.size()))
				{
					case CORRECT:
						correctOnFirstTry.add(wordPair);
						break;
					case CORRECT_WITH_MULTIPLE_GUESSES:
						correctWithMultipleGuesses.add(wordPair);
						break;
					case TRANSLATION_SHOWN:
						translationShown.add(wordPair);
						break;
				}
				wordPairListIndex++;
			}
			try
			{
				SwingUtilities.invokeAndWait(new Runnable()
				{
					@Override
					public void run()
					{
						new ResultsDialog(frame, correctOnFirstTry, correctWithMultipleGuesses, translationShown).setVisible(true);
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
		allVocabWords.put("Lesson 20", new String[][]{
				{"אמן", "he was faithful"},
				{"אָרַר", "he cursed"},
				{"גּוֹאֵל", "redeemer"},
				{"גָּלָה", "he uncovered, revealed"},
				{"גָּמָל", "camel"},
				{"יוֹשֵׁב", "inhabitant"},
				{"יוֹצֵר", "potter"},
				{"יָצַר", "he formed"},
				{"מוֹשִׁיעַ", "savior, deliverer"},
				{"מַלְאָךְ", "angel, messenger"},
				{"נָגַע", "he touched, smote"},
				{"נָגַשׁ", "he approached"},
				{"נָהָר", "river"},
				{"סוֹפֵר", "scribe"},
				{"פָּדָה", "he ransomed, redeemed"},
				{"פָּשַׁע", "he rebelled, transgressed"},
				{"רוֹאֶה", "seer, prophet"},
				{"רוֹעֶה", "shepherd"},
				{"רָעָה", "he pastured, tended"},
				{"שַׂר", "prince, ruler"},
				{"שָׁאַר", "he was left, left over"},
				{"שׁוֹפֵט", "judge"},
				{"שָׁקָה", "he drank"},
				{"תּוֹעֵבָה", "abomination"}
		});
		allVocabWords.put("Lesson 21", new String[][]{
				{"בּוֹשׁ", "to be ashamed, confounded"},
				{"דָּבַק", "he cleaved, clung to"},
				{"הָרָה", "(she) conceived, became pregnant"},
				{"סוּר", "to turn aside"},
				{"פָּנָה", "he turned towards, faced, prepared"},
				{"רָחַץ", "he washed"},
				{"אֹ֣זֶן", "ear"},
				{"אֱנוֹשׁ", "man, mankind"},
				{"בֶּ֣גֶד", "garment"},
				{"בְּרָכָה", "blessing"},
				{"גּוֹרָל", "lot, portion, share"},
				{"גֶּ֣פֶן", "vine"},
				{"גֶּ֣שֶׁם", "rain, shower"},
				{"זְרֹעַ", "arm, strength"},
				{"מוֹעֵד", "appointed time, place"},
				{"עֵד", "a witness, testimony, evidence"},
				{"עַ֣יִן", "eye, fountain"},
				{"שֶׁ֣קֶר", "deception, falsehood"}
		});
		allVocabWords.put("Lesson 22", new String[][]{
				{"אָחַז", "he seized, took possession"},
				{"גָּנַב", "he stole"},
				{"הָפַךְ", "he overturned, changed"},
				{"הָרַס", "he broke down, destroyed"},
				{"חָגַר", "he bound, girded"},
				{"חדשׁ", "he renewed, repaired"},
				{"חָפֵץ", "he took delight in, desired"},
				{"עָרַךְ", "he arranged, set in order"},
				{"אָז", "then"},
				{"אַךְ", "surely, only"},
				{"אֱמוּנָה", "faithfulness, fidelity"},
				{"בְּאֵר", "well"},
				{"בְּכוֹר", "first-born, oldest"},
				{"דְּבַשׁ", "honey"},
				{"חֹק", "statute"},
				{"לָשׁוֹן", "tongue"},
				{"מָ֣וֶת", "death"},
				{"מִזְבֵּחַ", "altar, place of sacrifice"}
		});
		allVocabWords.put("Lesson 23", new String[][]{
				{"אָבָה", "he was willing"},
				{"אָבַל", "he mourned"},
				{"אזן", "he listened, heard"},
				{"אָמֵץ", "he was strong, firm, bold"},
				{"אָסַר", "he bound, imprisoned"},
				{"אָפָה", "he baked"},
				{"אָרַךְ", "he prolonged"},
				{"אָשֵׁם", "he committed a wrong, was guilty"},
				{"אֶ֣לֶף", "ox, thousand"},
				{"גּוֹי", "nation"},
				{"חוּץ", "a place outside the house, the outdoors, a street"},
				{"מֵאָה", "hundred"},
				{"פָּנִים", "face (faces)"},
				{"קֹ֣דֶשׁ", "holiness, holy thing, sanctuary"},
				{"שְׁנַ֣יִם", "two"},
				{"שְׁתַּ֣יִם", "two"},
				{"שָׁלשׁ", "three"},
				{"שְׁלשָׁה", "three"},
				{"אַרְבַּע", "four"},
				{"אַרְבָּעָה", "four"},
				{"חָמֵשׁ", "five"},
				{"חֲמִשָּׁה", "five"}
		});
		allVocabWords.put("Lesson 24", new String[][]{
				{"בָּחַן", "he tested"},
				{"בָּחַר", "he chose"},
				{"בָּעַר", "he burned"},
				{"זָעַק", "he cried out"},
				{"מהר", "he hastened"},
				{"נָחַל", "he took possession, inherited"},
				{"נחם", "he was sorry, had compassion, suffered grief, repented"},
				{"צָחַק", "he laughed"},
				{"צָעַק", "he cried out"},
				{"שָׂחַק", "he laughed, jested"},
				{"שָׁחַט", "he killed, slaughtered"},
				{"אָחֵר", "another, other"},
				{"אַחַר", "behind, after"},
				{"אֹיֵב", "enemy"},
				{"בָּקָר", "herd, cattle"},
				{"מִגְדָּל", "tower, fortress"},
				{"זֶ֣בַח", "sacrifice"},
				{"מִנְחָה", "offering, gift, tribute"}
		});
		allVocabWords.put("Lesson 25", new String[][]{
				{"בָּלַע", "he swallowed, consumed"},
				{"בָּקַע", "he split open"},
				{"גָּבַהּ", "he was high, proud"},
				{"זָרַע", "he sowed"},
				{"כָּרַע", "he knelt, bowed down"},
				{"מָשַׁח", "he anointed"},
				{"סָלַח", "he forgave"},
				{"פָּגַע", "he met, interceded"},
				{"פָּתַח", "he opened"},
				{"קָרַע", "he tore"},
				{"רָצַח", "he killed, murdered"},
				{"שָׂבַע", "he was satisfied, sated"},
				{"בָּמָה", "high place"},
				{"צוּר", "rock"},
				{"קֵץ", "end"},
				{"שֻׁלְחָן", "table"},
				{"שֶׁ֣מֶשׁ", "sun"},
				{"תּוֹלְדוֹת", "generations"}
		});
		allVocabWords.put("Lesson 26", new String[][]{
				{"חבא", "he hid"},
				{"טָמֵא", "he was unclean"},
				{"נבא", "he prophesied"},
				{"צָמֵא", "he was thirsty"},
				{"קָבַץ", "he collected, gathered"},
				{"קָדַשׁ", "he was holy"},
				{"שׁחת", "he destroyed, corrupted"},
				{"שָׁלֵם", "he was whole, complete"},
				{"כְּלִי", "tool, weapon, vessel"},
				{"נְאֻם", "utterance, oracle"},
				{"סָבִיב", "around, surrounding"},
				{"עֶ֣שֶׂר", "ten"},
				{"עֲשָׂרָה", "ten"},
				{"צֶ֣דֶק", "righteousness"},
				{"צְדָקָה", "righteousness"},
				{"שֶׁ֣בַע", "seven"},
				{"שִׁבְעָה", "seven"},
				{"שַׁ֣עַר", "gate"}
		});
		allVocabWords.put("Lesson 27", new String[][]{
				{"בָּכָה", "he wept"},
				{"זָנָה", "he committed adultery, fornication"},
				{"חָזָה", "he saw"},
				{"חָלָה", "he was sick, weak"},
				{"חָנָה", "he encamped"},
				{"חָרָה", "it was hot, burned"},
				{"יָרָה", "he taught"},
				{"כָּסָה", "he covered, concealed"},
				{"עָנָה", "he answered, replied"},
				{"צָפָה", "he kept watch, spied"},
				{"קָנָה", "he took possession, acquired, bought"},
				{"שׁחה", "he bowed down, worshipped"},
				{"אַמָּה", "cubit"},
				{"מַחֲנֶה", "camp, encampment"},
				{"מַטֶּה", "staff, rod, branch, tribe"},
				{"מַעֲשֶׂה", "work, deed"},
				{"מִשְׁפָּחָה", "family, clan"},
				{"עֹלָה", "whole burnt offering"}
		});
		allVocabWords.put("Lesson 28", new String[][]{
				{"נבט", "he saw, looked upon"},
				{"נָגַף", "he smote, struck"},
				{"נָדַח", "he drove out, banished, expelled"},
				{"נָדַר", "he vowed"},
				{"נהל", "he led, guided, refreshed"},
				{"נָזָה", "he spurted, spattered"},
				{"נָטַע", "he planted"},
				{"נָטַשׁ", "he left, forsook"},
				{"נסה", "he tested, tried"},
				{"נָסַע", "he set out, departed, journeyed"},
				{"נצב", "he stationed himself, took his stand"},
				{"נָצר", "he watched, guarded, kept"},
				{"נקה", "he was clean, innocent, guiltless"},
				{"נקם", "he avenged, took vengeance"},
				{"נשׂג", "he reached, overtook, attained"},
				{"נָתַץ", "he pulled down, broke down"},
				{"חַ֣יִל", "strength, wealth, army"},
				{"נַחֲלָה", "possession, inheritance"}
		});
		allVocabWords.put("Lesson 29", new String[][]{
				{"גּוּר", "to sojourn"},
				{"גִּיל", "to rejoice"},
				{"דִּין", "to judge"},
				{"חוּל/חִיל", "to whirl, dance, writhe"},
				{"לוּן/לִין", "to lodge, pass the night, abide"},
				{"מוּל", "to circumcise"},
				{"נוּחַ", "to rest, come to rest"},
				{"נוּס", "to flee"},
				{"נוּעַ", "to quiver, stagger, tremble"},
				{"עוּר", "to arouse oneself"},
				{"פּוּץ", "to be scattered"},
				{"רוּץ", "to run"},
				{"רִיב", "to strive, contend"},
				{"שִׁיר", "to sing"},
				{"שִׁית", "to put, place, set"},
				{"כָּשַׁל", "he stumbled"},
				{"לָכַד", "he seized, captured"},
				{"שָׁבַת", "he ceased, rested"}
		});
		allVocabWords.put("Lesson 30", new String[][]{
				{"יָבֵשׁ", "he dried up, was dry"},
				{"יָגַע", "he labored, grew weary"},
				{"ידה", "he praised, confessed, gave thanks"},
				{"יָטַב", "he did well, was good"},
				{"יכח", "he reproved, rebuked"},
				{"יָנַק", "he sucked"},
				{"יָסַד", "he founded, established"},
				{"יָסַר", "he admonished, chastised"},
				{"יָעַץ", "he counseled, advised"},
				{"יצב", "he stationed himself, took his stand"},
				{"יָצַק", "he poured out"},
				{"יָצַת", "he kindled, set on fire"},
				{"יָשַׁר", "he was straight, straight-forward, upright"},
				{"יתר", "he was left over, remained"},
				{"קָטַר", "he burned incense, caused a sacrifice to smoke"},
				{"שׁרת", "he ministered, served"},
				{"סָגַר", "he shut, closed"},
				{"שׁכם", "he arose early"}
		});
		allVocabWords.put("Lesson 31", new String[][]{
				{"בָּזַז", "he plundered, destroyed"},
				{"בָּלַל", "he mixed, confounded"},
				{"דָּמַם", "he was silent, speechless"},
				{"הלל", "he praised"},
				{"חלל", "he was polluted"},
				{"חָנַן", "he was gracious, showed favor"},
				{"חָתַת", "he was shattered, dismayed"},
				{"מָדַד", "he measured"},
				{"נָדַד", "he fled"},
				{"סָבַב", "he surrounded, turned about, went around"},
				{"צָרַר", "he was in distress"},
				{"רָנַן", "he shouted for joy, cried out"},
				{"שָׁדַד", "he devastated, destroyed"},
				{"שָׁמַם", "he was appalled, devastated"},
				{"תָּמַם", "he was finished, completed"},
				{"גְּבוּל", "boundary, border"},
				{"גִּבּוֹר", "hero, mighty one"},
				{"קֶ֣שֶׁת", "bow"}
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