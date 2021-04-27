package com.ggl.tictactoe.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.tictactoe.model.TicTacToeModel;

public class ControlPanel {

	private static final Insets normalInsets = new Insets(10, 10, 0, 10);

	private JButton goesFirstButton;
	private JButton startGameButton;

	private JPanel panel;

	private JTextField computerWinsField;
	private JTextField playerWinsField;

	private TicTacToeFrame frame;

	private TicTacToeModel model;

	public ControlPanel(TicTacToeFrame frame, TicTacToeModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());

		int gridy = 0;

		JLabel computerWinsLabel = new JLabel("Computer Wins:");
		addComponent(innerPanel, computerWinsLabel, 0, gridy, 1, 1,
				normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		computerWinsField = new JTextField(5);
		computerWinsField.setEditable(false);
		computerWinsField.setHorizontalAlignment(JTextField.TRAILING);
		computerWinsField.setText(Integer.toString(model.getComputerWins()));
		addComponent(innerPanel, computerWinsField, 1, gridy++, 1, 1,
				normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		JLabel playerWinsLabel = new JLabel("Player Wins:");
		addComponent(innerPanel, playerWinsLabel, 0, gridy, 1, 1, normalInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		playerWinsField = new JTextField(5);
		playerWinsField.setEditable(false);
		playerWinsField.setHorizontalAlignment(JTextField.TRAILING);
		playerWinsField.setText(Integer.toString(model.getPlayerWins()));
		addComponent(innerPanel, playerWinsField, 1, gridy++, 1, 1,
				normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		goesFirstButton = new JButton(getGoesFirstButtonText());
		goesFirstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				model.setPlayerGoesFirst(!model.isPlayerGoesFirst());
				goesFirstButton.setText(getGoesFirstButtonText());
			}
		});
		addComponent(innerPanel, goesFirstButton, 0, gridy++, 2, 1,
				normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				model.initialize();
				frame.setComputerMove();
				frame.repaintTicTacToePanel();
			}
		});
		addComponent(innerPanel, startGameButton, 0, gridy++, 2, 1,
				normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		panel.add(innerPanel);
	}

	private void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, Insets insets,
			int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
	
	public void updatePartControl() {
		computerWinsField.setText(Integer.toString(model.getComputerWins()));
		playerWinsField.setText(Integer.toString(model.getPlayerWins()));
	}

	private String getGoesFirstButtonText() {
		return model.isPlayerGoesFirst() ? "Player Moves First"
				: "Computer Moves First";
	}

	public JPanel getPanel() {
		return panel;
	}

}
