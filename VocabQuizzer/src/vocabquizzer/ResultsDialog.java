package vocabquizzer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

class ResultsDialog extends JDialog
{
	private JButton okButton = null;
	
	ResultsDialog(QuizzerFrame parentFrame, ArrayList<String[]> correctOnFirstTry, ArrayList<String[]> correctWithMultipleGuesses, ArrayList<String[]> translationShown)
	{
		super(parentFrame, "Vocab Quizzer Results", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		layoutComponents(correctOnFirstTry, correctWithMultipleGuesses, translationShown);
		getRootPane().setDefaultButton(okButton);
		pack();
		setLocationRelativeTo(parentFrame);
		addListeners();
	}
	
	private void layoutComponents(ArrayList<String[]> correctOnFirstTry, ArrayList<String[]> correctWithMultipleGuesses, ArrayList<String[]> translationShown)
	{
		setLayout(new GridBagLayout());
		
		JLabel label = new JLabel("<html>" + correctOnFirstTry.size() + " Correct on the first try.<br>" + correctWithMultipleGuesses.size() +
				" Correct with multiple guesses.<br>" + translationShown.size() + " Translations shown.</html>", SwingConstants.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(label, constraints);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		if (!correctOnFirstTry.isEmpty())
			tabbedPane.addTab("Correct on first try", createWordListTab(correctOnFirstTry));
		if (!correctWithMultipleGuesses.isEmpty())
			tabbedPane.addTab("Correct with multiple guesses", createWordListTab(correctWithMultipleGuesses));
		if (!translationShown.isEmpty())
			tabbedPane.addTab("Translations shown", createWordListTab(translationShown));
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(tabbedPane, constraints);
		
		okButton = new JButton("OK");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(okButton, constraints);
	}
	
	private JScrollPane createWordListTab(ArrayList<String[]> wordList)
	{
		JPanel panel = new JPanel(new GridBagLayout());
		
		boolean firstIteration = true;
		for (String[] wordPair : wordList)
		{
			if (firstIteration)
				firstIteration = false;
			else
			{
				JSeparator separator = new JSeparator();
				GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridwidth = GridBagConstraints.REMAINDER;
				constraints.weightx = 1;
				constraints.weighty = 1;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				panel.add(separator, constraints);
			}
			
			JLabel hebrewLabel = new JLabel(wordPair[0], SwingConstants.CENTER);
			hebrewLabel.setFont(hebrewLabel.getFont().deriveFont(100.0f));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridwidth = GridBagConstraints.REMAINDER;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.ipadx = 15;
			constraints.insets = new Insets(10, 20, 0, 20);
			panel.add(hebrewLabel, constraints);
			
			JLabel englishLabel = new JLabel(wordPair[1], SwingConstants.CENTER);
			englishLabel.setFont(englishLabel.getFont().deriveFont(50.0f));
			constraints = new GridBagConstraints();
			constraints.gridwidth = GridBagConstraints.REMAINDER;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.insets = new Insets(0, 20, 10, 20);
			panel.add(englishLabel, constraints);
		}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(Math.max((int)scrollPane.getPreferredSize().getWidth(), 500), 400));
		return scrollPane;
	}
	
	private void addListeners()
	{
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
	}
}