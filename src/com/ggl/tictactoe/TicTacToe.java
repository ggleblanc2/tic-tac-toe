package com.ggl.tictactoe;

import javax.swing.SwingUtilities;

import com.ggl.tictactoe.model.TicTacToeModel;
import com.ggl.tictactoe.view.TicTacToeFrame;

public class TicTacToe implements Runnable {

	@Override
	public void run() {
		new TicTacToeFrame(new TicTacToeModel());
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new TicTacToe());
	}

}
