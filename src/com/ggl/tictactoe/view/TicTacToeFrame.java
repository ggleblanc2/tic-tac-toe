package com.ggl.tictactoe.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ggl.tictactoe.model.TicTacToeModel;

public class TicTacToeFrame {

	private ControlPanel controlPanel;

	private JFrame frame;

	private TicTacToeModel model;

	private TicTacToePanel tttPanel;

	public TicTacToeFrame(TicTacToeModel model) {
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		frame = new JFrame();
		frame.setTitle("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

		tttPanel = new TicTacToePanel(this, model);
		mainPanel.add(tttPanel);

		controlPanel = new ControlPanel(this, model);
		mainPanel.add(controlPanel.getPanel());

		frame.add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}
	
	public TicTacToePanel getTicTacToePanel() {
		return tttPanel;
	}
	
	public void repaintTicTacToePanel() {
		tttPanel.repaint();
	}
	
	public void setComputerMove() {
		tttPanel.setComputerMove();
	}
	
	public void updateScores() {
		controlPanel.updatePartControl();
	}
}
