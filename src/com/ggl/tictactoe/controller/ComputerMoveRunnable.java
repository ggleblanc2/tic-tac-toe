package com.ggl.tictactoe.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ggl.tictactoe.model.GameStatus;
import com.ggl.tictactoe.model.TicTacToeModel;
import com.ggl.tictactoe.view.TicTacToeFrame;

public class ComputerMoveRunnable implements Runnable {

	private Random random;

	private TicTacToeFrame frame;

	private TicTacToeModel model;

	public ComputerMoveRunnable(TicTacToeFrame frame, TicTacToeModel model) {
		this.frame = frame;
		this.model = model;
		this.random = new Random();
	}

	@Override
	public void run() {
		int[][] board = model.getBoard();

		// Has player won?
		if (isGameOver(board)) {
			return;
		}

		// Move for the win
		Point p = determineNearWinner(board, -1);
		if (p.x >= 0) {
			board[p.x][p.y] = -1;
			isGameOver(board);
			return;
		}

		// Block player wins
		p = determineNearWinner(board, 1);
		if (p.x >= 0) {
			board[p.x][p.y] = -1;
			isGameOver(board);
			return;
		}

		// Move to the center
		if (board[1][1] == 0) {
			board[1][1] = -1;
			return;
		}

		// Move to a corner next
		if (moveToACorner(board)) {
			isGameOver(board);
			return;
		}

		// Move anywhere
		p = moveAnywhere(board);
		if (p.x >= 0) {
			board[p.x][p.y] = -1;
			isGameOver(board);
			return;
		}
	}

	private boolean moveToACorner(int[][] board) {
		List<Point> p = getVacantCorners(board);
		if (p.size() > 0) {
			int pos = random.nextInt(p.size());
			Point s = p.get(pos);
			board[s.x][s.y] = -1;
			return true;
		}

		return false;
	}

	private List<Point> getVacantCorners(int[][] board) {
		List<Point> cornerPoints = new ArrayList<Point>(4);
		getCornerPoint(board, cornerPoints, 2, 2);
		getCornerPoint(board, cornerPoints, 0, 2);
		getCornerPoint(board, cornerPoints, 2, 0);
		getCornerPoint(board, cornerPoints, 0, 0);
		return cornerPoints;
	}

	private void getCornerPoint(int[][] board, List<Point> cornerPoints, int x,
			int y) {
		if (board[x][y] == 0) {
			cornerPoints.add(new Point(x, y));
		}
	}

	private Point moveAnywhere(int[][] board) {
		List<Point> p = new ArrayList<Point>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					p.add(new Point(i, j));
				}
			}
		}

		if (p.size() > 0) {
			int pos = random.nextInt(p.size());
			return p.get(pos);
		}

		return new Point(-11, -11);
	}

	private boolean isGameOver(int[][] board) {
		int value = determineWinner(board);
		if (value == 1) {
			model.setGameStatus(GameStatus.PLAYER_WINS);
			model.addPlayerWin();
			frame.updateScores();
			return true;
		} else if (value == -1) {
			model.setGameStatus(GameStatus.COMPUTER_WINS);
			model.addComputerWin();
			frame.updateScores();
			return true;
		} else if (isTieGame(board)) {
			model.setGameStatus(GameStatus.TIE_GAME);
			return true;
		}

		return false;
	}

	private Point determineNearWinner(int[][] board, int player) {
		for (int i = 0; i < board.length; i++) {
			Point p = checkTwo(board, player, new Point(0, i), new Point(1, i),
					new Point(2, i));
			Point r = checkTwo(board, player, new Point(i, 0), new Point(i, 1),
					new Point(i, 2));
			if (p.x > -10) {
				return p;
			}
			if (r.x > -10) {
				return r;
			}
		}

		Point p = checkTwo(board, player, new Point(0, 0), new Point(1, 1),
				new Point(2, 2));
		if (p.x > -10) {
			return p;
		}

		p = checkTwo(board, player, new Point(2, 0), new Point(1, 1),
				new Point(0, 2));
		if (p.x > -10) {
			return p;
		}

		return p;
	}

	private int determineWinner(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			int row1 = checkThree(board[0][i], board[1][i], board[2][i]);
			int row2 = checkThree(board[i][0], board[i][1], board[i][2]);
			if (row1 != 0) {
				return row1;
			}
			if (row2 != 0) {
				return row2;
			}
		}

		int row = checkThree(board[0][0], board[1][1], board[2][2]);
		if (row != 0) {
			return row;
		}

		row = checkThree(board[0][2], board[1][1], board[2][0]);
		if (row != 0) {
			return row;
		}

		return 0;
	}

	private boolean isTieGame(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	private Point checkTwo(int[][] board, int player, Point... p) {
		if ((board[p[0].x][p[0].y] == player)
				&& (board[p[1].x][p[1].y] == player)
				&& (board[p[2].x][p[2].y] == 0)) {
			return p[2];
		}
		if ((board[p[0].x][p[0].y] == player) && (board[p[1].x][p[1].y] == 0)
				&& (board[p[2].x][p[2].y] == player)) {
			return p[1];
		}
		if ((board[p[0].x][p[0].y] == 0) && (board[p[1].x][p[1].y] == player)
				&& (board[p[2].x][p[2].y] == player)) {
			return p[0];
		}

		return new Point(-11, -11);
	}

	private int checkThree(int pos1, int pos2, int pos3) {
		if ((pos1 == 1) && (pos2 == 1) && (pos3 == 1)) {
			return 1;
		}

		if ((pos1 == -1) && (pos2 == -1) && (pos3 == -1)) {
			return -1;
		}

		return 0;
	}
}
