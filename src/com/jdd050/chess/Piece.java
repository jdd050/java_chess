package com.jdd050.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Piece {

    public JLabel piece;
    public int pieceId;
    public Color pieceColor;
    public String pieceType;

    public Piece(String pieceName, Color piece_color, int piece_id, Dimension location) {
        // Clean the input string for the piece name
        pieceName = pieceName.toLowerCase();
        pieceName = pieceName.replace(" ", "");
        // Set instance variables
        pieceId = piece_id;
        pieceColor = piece_color;
        pieceType = pieceName;
        // Set instance variables
        try {
            piece = loadPieceImage(pieceName, pieceColor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load image files into the piece variables
    private JLabel loadPieceImage(String pieceName, Color pieceColor) throws IOException {
        JLabel pieceLabel = null;
        BufferedImage pieceImage = null;
        String imagePath = null;

        // Black pieces
        if (pieceColor == Color.black) {
            switch (pieceName) {
                case "pawn":
                    imagePath = "./assets/b_pawn_png_128px.png";
                    break;
                case "rook":
                    imagePath = "./assets/b_rook_png_128px.png";
                    break;
                case "knight":
                    imagePath = "./assets/b_knight_png_128px.png";
                    break;
                case "bishop":
                    imagePath = "./assets/b_bishop_png_128px.png";
                    break;
                case "king":
                    imagePath = "./assets/b_king_png_128px.png";
                    break;
                case "queen":
                    imagePath = "./assets/b_queen_png_128px.png";
                    break;
                default:
                    System.out.println("Invalid piece name provided: " + pieceName);
                    return null;
            }
        } else if (pieceColor == Color.white) {
            switch (pieceName) {
                case "pawn":
                    imagePath = "./assets/w_pawn_png_128px.png";
                    break;
                case "rook":
                    imagePath = "./assets/w_rook_png_128px.png";
                    break;
                case "knight":
                    imagePath = "./assets/w_knight_png_128px.png";
                    break;
                case "bishop":
                    imagePath = "./assets/w_bishop_png_128px.png";
                    break;
                case "king":
                    imagePath = "./assets/w_king_png_128px.png";
                    break;
                case "queen":
                    imagePath = "./assets/w_queen_png_128px.png";
                    break;
                default:
                    System.out.println("Invalid piece name provided: " + pieceName);
                    return null;
            }
        } else {
            System.out.println("Invalid color provided: " + pieceColor);
            return null;
        }

        // Load the image
        if (imagePath != null) {
            java.net.URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                pieceImage = ImageIO.read(imageUrl);
                pieceLabel = new JLabel(new ImageIcon(pieceImage));
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        }

        return pieceLabel;
    }
}
