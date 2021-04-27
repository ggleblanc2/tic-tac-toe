package com.ggl.tictactoe.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ggl.tictactoe.controller.MouseMoveController;
import com.ggl.tictactoe.model.GameStatus;
import com.ggl.tictactoe.model.TicTacToeModel;

public class TicTacToePanel extends JPanel {

	private static final long serialVersionUID = -9150412950439480699L;

	private MouseMoveController controller;

	private TicTacToeModel model;

	public TicTacToePanel(TicTacToeFrame frame, TicTacToeModel model) {
		this.model = model;
		this.controller = new MouseMoveController(frame, model);
		this.addMouseListener(controller);
		this.setPreferredSize(new Dimension(360, 360));
	}

	public void setComputerMove() {
		if (!model.isPlayerGoesFirst()) {
			controller.setComputerMove();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		int[][] board = model.getBoard();
		int width = getWidth() / board.length;
		int height = getHeight() / board[0].length;

		int x = 0;
		int y = 0;

		Stroke stroke = new BasicStroke(7F);
		g2d.setStroke(stroke);
		g2d.setColor(Color.BLACK);

		x += width;
		for (int i = 0; i < (board.length - 1); i++) {
			g2d.drawLine(x, y, x, getHeight());
			x += width;
		}

		x = 0;
		y += width;
		for (int i = 0; i < (board.length - 1); i++) {
			g2d.drawLine(x, y, getWidth(), y);
			y += width;
		}

		float fontSize = (float) height * 72F / 96F;
		Font largeFont = getFont().deriveFont(fontSize);

		for (int i = 0; i < board.length; i++) {
			x = width * i;
			for (int j = 0; j < board[i].length; j++) {
				y = height * j;
				Rectangle r = new Rectangle(x, y, width, height);
				if (board[i][j] == 1) {
					g2d.setColor(Color.BLUE);
					centerString(g2d, r, "X", largeFont);
				} else if (board[i][j] == -1) {
					g2d.setColor(Color.CYAN);
					centerString(g2d, r, "O", largeFont);
				}
			}
		}

		if (model.getGameStatus() == GameStatus.TIE_GAME) {
			BufferedImage image = GameOverImage.createImage(getWidth(),
					getHeight(), "Tie Game");
			g2d.drawImage(image, 0, 0, this);
		} else if (model.getGameStatus() == GameStatus.COMPUTER_WINS) {
			BufferedImage image = GameOverImage.createImage(getWidth(),
					getHeight(), "Computer Wins");
			g2d.drawImage(image, 0, 0, this);
		} else if (model.getGameStatus() == GameStatus.PLAYER_WINS) {
			BufferedImage image = GameOverImage.createImage(getWidth(),
					getHeight(), "Player Wins");
			g2d.drawImage(image, 0, 0, this);
		}
	}

	/**
	 * This method centers a <code>String</code> in a bounding
	 * <code>Rectangle</code>.
	 * 
	 * @param g2d
	 *            - The <code>Graphics2D</code> instance.
	 * @param r
	 *            - The bounding <code>Rectangle</code>.
	 * @param s
	 *            - The <code>String</code> to center in the bounding rectangle.
	 * @param font
	 *            - The display font of the <code>String</code>
	 * 
	 * @see java.awt.Graphics
	 * @see java.awt.Rectangle
	 * @see java.lang.String
	 */
	private void centerString(Graphics2D g2d, Rectangle r, String s, Font font) {
		FontRenderContext frc = new FontRenderContext(null, true, true);

		Rectangle2D r2D = font.getStringBounds(s, frc);
		int rWidth = (int) Math.round(r2D.getWidth());
		int rHeight = (int) Math.round(r2D.getHeight());
		int rX = (int) Math.round(r2D.getX());
		int rY = (int) Math.round(r2D.getY());

		int a = (r.width / 2) - (rWidth / 2) - rX;
		int b = (r.height / 2) - (rHeight / 2) - rY;

		g2d.setFont(font);
		g2d.drawString(s, r.x + a, r.y + b);
	}

}
