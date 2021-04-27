package com.ggl.tictactoe.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
 
public class GameOverImage {
     
    public static BufferedImage createImage(int width, int height, String s) {
    	BufferedImage image = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
 
        g.setComposite(AlphaComposite.getInstance(
                AlphaComposite.CLEAR));
         
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
         
        g.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER));
         
        g.setColor(Color.RED);
        Font font = g.getFont();
        Font largeFont = font.deriveFont(48.0F);
        FontRenderContext frc = 
                new FontRenderContext(null, true, true);
        Rectangle2D r = largeFont.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r.getWidth());
        int rHeight = (int) Math.round(r.getHeight());
        int rX = (int) Math.round(r.getX());
        int rY = (int) Math.round(r.getY());
         
        int x = (width / 2) - (rWidth / 2) - rX;
        int y = (height / 2) - (rHeight / 2) - rY;
         
        g.setFont(largeFont);
        g.drawString(s, x, y);
         
        g.dispose();
        
        return image;
    }
 
}
