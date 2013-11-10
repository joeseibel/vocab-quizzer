package vocabquizzer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class ShowTranslationDialog extends JDialog
{
	private JButton okButton = null;
	
	ShowTranslationDialog(QuizzerFrame parentFrame, String hebrewWord, String englishWord)
	{
		super(parentFrame, "Vocab Quizzer", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		layoutComponents(hebrewWord, englishWord);
		getRootPane().setDefaultButton(okButton);
		pack();
		setLocationRelativeTo(parentFrame);
		addListeners();
	}
	
	private void layoutComponents(String hebrewWord, String englishWord)
	{
		setLayout(new GridBagLayout());
		
		JLabel hebrewLabel = new JLabel(hebrewWord, SwingConstants.CENTER);
		hebrewLabel.setFont(hebrewLabel.getFont().deriveFont(100.0f));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 15;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(hebrewLabel, constraints);
		
		JLabel englishLabel = new JLabel(englishWord, SwingConstants.CENTER);
		englishLabel.setFont(englishLabel.getFont().deriveFont(50.0f));
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(englishLabel, constraints);
		
		okButton = new JButton("OK");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 20, 10, 20);
		add(okButton, constraints);
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