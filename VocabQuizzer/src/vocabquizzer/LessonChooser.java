package vocabquizzer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LessonChooser extends JFrame
{
	private JButton selectAllButton = null;
	private JButton deselectAllButton = null;
	private JCheckBox[] lessonCheckboxes = null;
	private JButton ok = null;
	
	private ArrayList<String> selectedLessons = null;
	
	static ArrayList<String> getSelectedLessons(Set<String> lessonNames, ArrayList<String> selectedLessons)
	{
		LessonChooser lessonChooser = new LessonChooser(lessonNames, selectedLessons);
		synchronized (lessonChooser)
		{
			do
			{
				try
				{
					lessonChooser.wait();
				}
				catch (InterruptedException e)
				{
				}
			} while (lessonChooser.selectedLessons == null);
		}
		lessonChooser.dispose();
		return lessonChooser.selectedLessons;
	}
	
	private LessonChooser(Set<String> lessonNames, ArrayList<String> selectedLessons)
	{
		super("Vocab Quizzer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		layoutComponents(lessonNames, selectedLessons);
		getRootPane().setDefaultButton(ok);
		pack();
		addListeners();
		setLocationByPlatform(true);
		setVisible(true);
	}
	
	private void layoutComponents(Set<String> lessonNames, ArrayList<String> selectedLessons)
	{
		setLayout(new GridBagLayout());
		
		selectAllButton = new JButton("Select All");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 10, 5, 5);
		add(selectAllButton, constraints);
		
		deselectAllButton = new JButton("Deselect All");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(10, 5, 5, 10);
		add(deselectAllButton, constraints);
		
		JPanel checkBoxPanel = createCheckBoxPanel(lessonNames, selectedLessons);
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		add(checkBoxPanel, constraints);
		
		ok = new JButton("OK");
		constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(5, 10, 10, 10);
		add(ok, constraints);
	}
	
	private JPanel createCheckBoxPanel(Set<String> lessonNames, ArrayList<String> selectedLessons)
	{
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new GridBagLayout());
		
		lessonCheckboxes = new JCheckBox[lessonNames.size()];
		int i = 0;
		for (String lessonName : lessonNames)
		{
			lessonCheckboxes[i] = new JCheckBox(lessonName, selectedLessons == null || selectedLessons.contains(lessonName));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = i / 10;
			constraints.gridy = i % 10;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.insets = new Insets(5, 5, 5, 5);
			checkBoxPanel.add(lessonCheckboxes[i], constraints);
			i++;
		}
		
		return checkBoxPanel;
	}
	
	private void addListeners()
	{
		selectAllButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (JCheckBox checkBox : lessonCheckboxes)
					checkBox.setSelected(true);
				ok.setEnabled(true);
			}
		});
		deselectAllButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (JCheckBox checkBox : lessonCheckboxes)
					checkBox.setSelected(false);
				ok.setEnabled(false);
			}
		});
		for (JCheckBox checkBox : lessonCheckboxes)
		{
			checkBox.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					for (JCheckBox checkBox : lessonCheckboxes)
					{
						if (checkBox.isSelected())
						{
							ok.setEnabled(true);
							return;
						}
					}
					ok.setEnabled(false);
				}
			});
		}
		ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<String> selectedLessons = new ArrayList<String>();
				for (JCheckBox checkBox : lessonCheckboxes)
				{
					if (checkBox.isSelected())
						selectedLessons.add(checkBox.getText());
				}
				synchronized (LessonChooser.this)
				{
					LessonChooser.this.selectedLessons = selectedLessons;
					LessonChooser.this.notify();
				}
			}
		});
	}
}