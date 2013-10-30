package vocabquizzer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class QuizzerFrame extends JFrame
{
	enum QuizResult {CORRECT, CORRECT_WITH_MULTIPLE_GUESSES, TRANSLATION_SHOWN}
	
	private QuizResult currentQuizResult = null;
	private String correctEnglishAnswer = null;
	private boolean incorrectGuessGiven = false;
	
	private JLabel progressLabel = null;
	private JLabel correctOnFirstTryLabel = null;
	private JLabel correctOnMultipleGuessesLabel = null;
	private JLabel translationsShownLabel = null;
	private JLabel hebrewLabel = null;
	private JTextField englishField = null;
	private JButton submitAnswerButton = null;
	private JButton showTranslationButton = null;
	
	//Construct from event dispatch thread.
	QuizzerFrame()
	{
		super("Vocab Quizzer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		layoutComponents();
		addListeners();
		getRootPane().setDefaultButton(submitAnswerButton);
		setSize(700, 300);
	}
	
	//Call from main thread.
	QuizResult showNextWord(final String hebrewWord, final String correctEnglishAnswer, final String progress, final int correctOnFirstTry,
			final int correctOnMultipleGuesses, final int translationsShown)
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					hebrewLabel.setText(hebrewWord);
					progressLabel.setText(progress);
					correctOnFirstTryLabel.setText(Integer.toString(correctOnFirstTry));
					correctOnMultipleGuessesLabel.setText(Integer.toString(correctOnMultipleGuesses));
					translationsShownLabel.setText(Integer.toString(translationsShown));
					QuizzerFrame.this.correctEnglishAnswer = correctEnglishAnswer;
					incorrectGuessGiven = false;
					submitAnswerButton.setEnabled(true);
					showTranslationButton.setEnabled(true);
					englishField.setText(null);
					englishField.requestFocus();
					if (!isVisible())
						setLocationByPlatform(true);
					setVisible(true);
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
		synchronized (this)
		{
			do
			{
				try
				{
					wait();
				}
				catch (InterruptedException e)
				{
					//Do nothing.
				}
			} while (currentQuizResult == null);
		}
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				
				@Override
				public void run()
				{
					submitAnswerButton.setEnabled(false);
					showTranslationButton.setEnabled(false);
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
		return currentQuizResult;
	}
	
	private void layoutComponents()
	{
		setLayout(new GridBagLayout());
		
		JPanel statusPanel = createStatusPanel();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(statusPanel, constraints);
		
		hebrewLabel = new JLabel();
		hebrewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hebrewLabel.setFont(hebrewLabel.getFont().deriveFont(100.0f));
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(hebrewLabel, constraints);
		
		englishField = new JTextField();
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(0, 15, 0, 15);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(englishField, constraints);
		
		submitAnswerButton = new JButton("Submit Answer");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		add(submitAnswerButton, constraints);
		
		showTranslationButton = new JButton("Show Translation");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		add(showTranslationButton, constraints);
	}
	
	private JPanel createStatusPanel()
	{
		JPanel statusPanel = new JPanel(new GridBagLayout());
		
		JLabel label = new JLabel("Progress:");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(label, constraints);
		
		label = new JLabel("Correct on First Try:");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(label, constraints);
		
		label = new JLabel("Correct on Multiple Guesses:");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(label, constraints);
		
		label = new JLabel("Translations Shown:");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(label, constraints);
		
		progressLabel = new JLabel("52/356");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(progressLabel, constraints);
		
		correctOnFirstTryLabel = new JLabel("32");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(correctOnFirstTryLabel, constraints);
		
		correctOnMultipleGuessesLabel = new JLabel("65");
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(correctOnMultipleGuessesLabel, constraints);
		
		translationsShownLabel = new JLabel("654");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		statusPanel.add(translationsShownLabel, constraints);
		
		return statusPanel;
	}
	
	private void addListeners()
	{
		submitAnswerButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (englishField.getText().trim().equalsIgnoreCase(correctEnglishAnswer.trim()))
				{
					JOptionPane.showMessageDialog(QuizzerFrame.this, "Correct.", "Vocab Quizzer", JOptionPane.INFORMATION_MESSAGE);
					synchronized (QuizzerFrame.this)
					{
						if (incorrectGuessGiven)
							currentQuizResult = QuizResult.CORRECT_WITH_MULTIPLE_GUESSES;
						else
							currentQuizResult = QuizResult.CORRECT;
						QuizzerFrame.this.notify();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(QuizzerFrame.this, "Incorrect.", "Vocab Quizzer", JOptionPane.ERROR_MESSAGE);
					incorrectGuessGiven = true;
					englishField.setText(null);
					englishField.requestFocus();
				}
			}
		});
		showTranslationButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ShowTranslationDialog(QuizzerFrame.this, hebrewLabel.getText(), correctEnglishAnswer).setVisible(true);
				synchronized (QuizzerFrame.this)
				{
					currentQuizResult = QuizResult.TRANSLATION_SHOWN;
					QuizzerFrame.this.notify();
				}
			}
		});
	}
}