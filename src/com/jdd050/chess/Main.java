package com.jdd050.chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {

    public static Color lightSquareColor = Color.white;
    public static Color darkSquareColor = Color.black;
    public static int files = 8;
    public static int ranks = 8;
    public ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Main() {
        this.setBackground(Color.GRAY);
        // set up layout manager for the background
        this.setLayout(new GridBagLayout());
        GridBagConstraints mainConstraints = new GridBagConstraints();
        mainConstraints.fill = GridBagConstraints.BOTH;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.weightx = 1.0;
        mainConstraints.weighty = 1.0;
        mainConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        // create the chess board
        JPanel chessBoard = createBoard();
        this.add(chessBoard, mainConstraints);
        // create a pawn as example
        JLabel pawn = addPawn(0, 0, Color.white);
        if (pawn != null) {
            addPieceToBoard(chessBoard, 0, 0, pawn);
            System.out.println(chessBoard.getComponent(0));
        } else {
            System.out.println("Error encountered while creating or adding piece");
        }
    }

    public JPanel createBoard() {
        // create the chess board container
        JPanel chessBoard = new JPanel(new GridLayout(ranks, files));
        // add the individual squares to the board
        for (int i = 0; i < ranks; i++) {
            for (int j = 0; j < files; j++) {
                // create the square and set its attributes
                JPanel square = new JPanel();
                Color squareColor = (i % 2 == j % 2) ? lightSquareColor : darkSquareColor;
                square.setBackground(squareColor);
                // add the square to the board
                chessBoard.add(square);
            }
        }
        return chessBoard;
    }

    // methods to add pieces

    public JLabel addPawn(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece pawn = new Piece("Pawn", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(pawn);
        return pawn.piece;
    }

    public JLabel addRook(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece rook = new Piece("Rook", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(rook);
        return rook.piece;
    }

    public JLabel addKnight(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece knight = new Piece("Knight", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(knight);
        return knight.piece;
    }

    public JLabel addBishop(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece bishop = new Piece("Bishop", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(bishop);
        return bishop.piece;
    }

    public JLabel addQueen(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece queen = new Piece("Queen", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(queen);
        return queen.piece;
    }

    public JLabel addKing(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece king = new Piece("King", pieceColor, pieceId, new Dimension(rank, file));
        pieces.add(king);
        return king.piece;
    }

    // method for adding pieces to the board
    private void addPieceToBoard(JPanel chessBoard, int rank, int file, JLabel pieceLabel) {
        int index = (rank * files) + file;
        JPanel square = (JPanel) chessBoard.getComponent(index);
        square.add(pieceLabel);
        square.revalidate();
        square.repaint();
    }

    // runs the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // container for all app elements
                Main contentFrame = new Main();
                // the window itself
                JFrame application = new JFrame("Chess");
                application.setPreferredSize(new Dimension(800, 800));
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application.getContentPane().add(contentFrame);
                application.setLocationByPlatform(true);
                // finalize the window and run
                application.pack();
                application.setVisible(true);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
