package com.jdd050.chess;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Piece {
	
	public JLabel piece;
	public int pieceId;
	public Color pieceColor;
	public String pieceType;
	
	public Piece(String pieceName, Color piece_color, int piece_id) {
		// clean the input string for the piece name
		pieceName = pieceName.toLowerCase();
		pieceName = pieceName.replace(" ", "");
		// set instance variables
		try {
			piece = loadPieceImage(pieceName, pieceColor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pieceId = piece_id;
		pieceColor = piece_color;
		pieceType = pieceName;
	}
	
	// load image files into the piece variables
	private JLabel loadPieceImage(String pieceName, Color pieceColor) throws IOException {
		JLabel pieceLabel = null;
		BufferedImage pieceImage = null;
		// black pieces
		if (pieceColor == Color.black) {
			switch (pieceName) {
			case ("pawn"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_pawn_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("rook"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_rook_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("knight"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_knight_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("bishop"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_bishop_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("king"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_king_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("queen"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/b_queen_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			}
		}
		if (pieceColor == Color.white) {
			switch (pieceName) {
			case ("pawn"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_pawn_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("rook"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_rook_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("knight"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_knight_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("bishop"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_bishop_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("king"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_king_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			case ("queen"):
				// load the image
				pieceImage = ImageIO.read(new File("./assets/w_queen_png_128px.png"));
				// create the label using the image
				pieceLabel = new JLabel(new ImageIcon(pieceImage));
			}
		} 
		else {
			System.out.print("Invalid color or name provided");
			return null;
		}
		return pieceLabel;
	}
	
}
