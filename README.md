vocab-quizzer
=============
This is a simple little vocabulary flashcard program that I wrote while taking a Biblical Hebrew course.  It is written entirely in Java and uses Swing for the GUI.  The main method for this program is in the class `VocabQuizzer`.

Upon launching, the user is presented with a list of lessons to choose from. Each lesson contains a collection of Hebrew words from the textbook *Biblical Hebrew: An Introductory Grammar* by Page H. Kelley. Alongside the lessons are other collections of words such as pronouns, numbers, and tests. The contents of the tests categories come from quizzes that were given throughout the course.  These were added to the quizzer to help prepare for the final exam. Once the user selects the lessons, then the quizzer will present all the words from those lessons in a random order exactly once.  Afterwards, a summary of the user's performance is presented.

All of the Hebrew words and their English translations are hard-coded in the class `VocabQuizzer`. In order for an English answer to be considered correct by the quizzer, it must exactly match, ignoring case, the English translation provided in the *Biblical Hebrew* textbook. For example, the textbook lists the translation of **"אֲשֶׁר"** to be **"who, which, what"**. In this case, the order of the three English words matter and there must be two commas and two spaces.

The classes `LessonChooser`, `QuizzerFrame`, `ResultsDialog`, and `ShowTranslationDialog` are the various JFrames and JDialogs of the quizzer. The classes `PrintWordsInOrder` and `VocabQuizzerTester` are not a part of the program and were used to help me with my studies and to test display of Hebrew words when entering them for each lesson.
