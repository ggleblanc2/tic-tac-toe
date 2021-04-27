package com.ggl.tictactoe.model;

public class TicTacToeModel {
	
	private static final int POSITION_WIDTH = 3;
	
	private boolean playerGoesFirst;
	
	private int computerWins;
	private int playerWins;
	
	/** Value of -1 for computer, 0 for neither, and 1 for player **/
	private int[][] board;
	
	private GameStatus gameStatus;
	
	public TicTacToeModel() {
		this.board = new int[POSITION_WIDTH][POSITION_WIDTH];
		this.playerGoesFirst = true;
		this.gameStatus = GameStatus.ACTIVE_GAME;
	}
	
	public void initialize() {
		resetBoard();
		this.gameStatus = GameStatus.ACTIVE_GAME;
	}
	
	private void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
	}

	public int[][] getBoard() {
		return board;
	}
	
	public void setComputerMove(int x, int y) {
		this.board[x][y] = -1;
	}
	
	public void setPlayerMove(int x, int y) {
		this.board[x][y] = 1;
	}

	public boolean isPlayerGoesFirst() {
		return playerGoesFirst;
	}

	public void setPlayerGoesFirst(boolean playerGoesFirst) {
		this.playerGoesFirst = playerGoesFirst;
	}

	public int getComputerWins() {
		return computerWins;
	}
	
	public void addComputerWin() {
		this.computerWins++;
	}

	public int getPlayerWins() {
		return playerWins;
	}
	
	public void addPlayerWin() {
		this.playerWins++;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public static int getPositionWidth() {
		return POSITION_WIDTH;
	}

}
