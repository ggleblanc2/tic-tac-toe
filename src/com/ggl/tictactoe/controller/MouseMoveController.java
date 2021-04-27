package com.ggl.tictactoe.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.ggl.tictactoe.model.GameStatus;
import com.ggl.tictactoe.model.TicTacToeModel;
import com.ggl.tictactoe.view.TicTacToeFrame;

public class MouseMoveController extends MouseAdapter {

	private ComputerMoveRunnable controller;

	private TicTacToeFrame frame;

	private TicTacToeModel model;

	public MouseMoveController(TicTacToeFrame frame, TicTacToeModel model) {
		this.frame = frame;
		this.model = model;
		this.controller = new ComputerMoveRunnable(frame, model);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if ((model.getGameStatus() == GameStatus.ACTIVE_GAME)
				&& (event.getButton() == MouseEvent.BUTTON1)) {
			int width = frame.getTicTacToePanel().getWidth();
			int height = frame.getTicTacToePanel().getHeight();
			int positionWidth = TicTacToeModel.getPositionWidth();

			Point p = event.getPoint();

			int x = getPosition(p.x, width, positionWidth);
			int y = getPosition(p.y, height, positionWidth);

			int[][] board = model.getBoard();
			if (board[x][y] == 0) {
				model.setPlayerMove(x, y);
				setComputerMove();
			}
		}
	}

	public void setComputerMove() {
		controller.run();
		frame.repaintTicTacToePanel();
	}

	private int getPosition(int location, int dimension, int squareWidth) {
		for (int i = 0; i < squareWidth; i++) {
			if (location < (dimension * (i + 1) / squareWidth)) {
				return i;
			}
		}

		return squareWidth - 1;
	}

}
