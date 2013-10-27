package vocabquizzer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class QuizzerFrame extends JFrame
{
	enum QuizResult {CORRECT, CORRECT_WITH_MULTIPLE_GUESSES, TRANSLATION_SHOWN}
	
	private QuizResult currentQuizResult = null;
	private String correctEnglishAnswer = null;
	private boolean incorrectGuessGiven = false;
	
	private JLabel hebrewLabel = null;
	private JTextField englishField = null;
	private JButton submitAnswerButton = null;
	private JButton showTranslationButton = null;
	
	QuizzerFrame()
	{
		super("Vocab Quizzer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		layoutComponents();
		addListeners();
		getRootPane().setDefaultButton(submitAnswerButton);
		setSize(700, 300);
	}
	
	QuizResult showNextWord(String hebrewWord, String correctEnglishAnswer)
	{
		hebrewLabel.setText(hebrewWord);
		this.correctEnglishAnswer = correctEnglishAnswer;
		incorrectGuessGiven = false;
		submitAnswerButton.setEnabled(true);
		showTranslationButton.setEnabled(true);
		englishField.setText(null);
		englishField.requestFocus();
		if (!isVisible())
			setLocationByPlatform(true);
		setVisible(true);
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
		submitAnswerButton.setEnabled(false);
		showTranslationButton.setEnabled(false);
		return currentQuizResult;
	}
	
	private void layoutComponents()
	{
		setLayout(new GridBagLayout());
		
		hebrewLabel = new JLabel();
		hebrewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hebrewLabel.setFont(hebrewLabel.getFont().deriveFont(100.0f));
		GridBagConstraints constraints = new GridBagConstraints();
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