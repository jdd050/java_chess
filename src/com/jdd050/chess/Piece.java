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
    public Dimension pieceLocation;
    public boolean inCheck;
    public Piece(String pieceName, Color piece_color, int piece_id, Dimension location) {
        // Clean the input string for the piece name
        if (piece_color == Color.black) {
            pieceName = "black_" + pieceName.toLowerCase();
            pieceName = pieceName.replace(" ", "");
        } else if (piece_color == Color.white) {
            pieceName = "white_" + pieceName.toLowerCase();
            pieceName = pieceName.replace(" ", "");
        }
        // Set instance variables
        pieceId = piece_id;
        pieceColor = piece_color;
        pieceType = pieceName;
        pieceLocation = location;
        // this instance variable only to be used when piece.pieceType = queen
        inCheck = false;
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
                case "black_pawn":
                    imagePath = "./assets/b_pawn_png_128px.png";
                    break;
                case "black_rook":
                    imagePath = "./assets/b_rook_png_128px.png";
                    break;
                case "black_knight":
                    imagePath = "./assets/b_knight_png_128px.png";
                    break;
                case "black_bishop":
                    imagePath = "./assets/b_bishop_png_128px.png";
                    break;
                case "black_king":
                    imagePath = "./assets/b_king_png_128px.png";
                    break;
                case "black_queen":
                    imagePath = "./assets/b_queen_png_128px.png";
                    break;
                default:
                    System.out.println("Invalid piece name provided: " + pieceName);
                    return null;
            }
        } else if (pieceColor == Color.white) {
            switch (pieceName) {
                case "white_pawn":
                    imagePath = "./assets/w_pawn_png_128px.png";
                    break;
                case "white_rook":
                    imagePath = "./assets/w_rook_png_128px.png";
                    break;
                case "white_knight":
                    imagePath = "./assets/w_knight_png_128px.png";
                    break;
                case "white_bishop":
                    imagePath = "./assets/w_bishop_png_128px.png";
                    break;
                case "white_king":
                    imagePath = "./assets/w_king_png_128px.png";
                    break;
                case "white_queen":
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
                pieceLabel.setName(pieceName);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        }
        return pieceLabel;
    }
}
