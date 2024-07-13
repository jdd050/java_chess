package com.jdd050.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Main extends JPanel {
    private JLabel selectedPiece = null;
    private int numMoves = 0;

    public JPanel chessBoard = null;
    public static Color lightSquareColor = Color.white;
    public static Color darkSquareColor = Color.gray;
    public static int files = 8;
    public static int ranks = 8;
    public HashMap<Piece, JLabel> pieces = new HashMap<>();

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
        chessBoard = createBoard();
        this.add(chessBoard, mainConstraints);
        // create pawns
        for (int i = 0; i < 8; i++) {
            addPawn(1, i, Color.black);
            addPawn(6, i, Color.white);
        }
        // create kings
        addKing(0, 4, Color.black);
        addKing(7, 4, Color.white);
        // create queens
        addQueen(0, 3, Color.black);
        addQueen(7, 3, Color.white);
        // create bishops
        addBishop(0, 2, Color.black);
        addBishop(0, 5, Color.black);
        addBishop(7, 2, Color.white);
        addBishop(7, 5, Color.white);
        // create knights
        addKnight(0, 1, Color.black);
        addKnight(0, 6, Color.black);
        addKnight(7, 1, Color.white);
        addKnight(7, 6, Color.white);
        // create rooks
        addRook(0, 0, Color.black);
        addRook(0, 7, Color.black);
        addRook(7, 0, Color.white);
        addRook(7, 7, Color.white);
        // create mouse listener devices for each piece
        createMouseListeners();
    }

    public JPanel createBoard() {
        // create the chess board container
        JPanel chessBoard = new JPanel(new GridLayout(ranks, files));
        // add the individual squares to the board
        for (int i = 0; i < ranks; i++) {
            for (int j = 0; j < files; j++) {
                // create the square and set its attributes
                JPanel square = new JPanel();
                // add mouse listener
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        onSquareClicked(e);
                    }
                });
                Color squareColor = (i % 2 == j % 2) ? lightSquareColor : darkSquareColor;
                square.setBackground(squareColor);
                // add the square to the board
                chessBoard.add(square);
            }
        }
        return chessBoard;
    }

    // methods to add pieces

    public void addPawn(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece pawn = new Piece("Pawn", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(pawn, pawn.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, pawn.piece);
    }

    public void addRook(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece rook = new Piece("Rook", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(rook, rook.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, rook.piece);
    }

    public void addKnight(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece knight = new Piece("Knight", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(knight, knight.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, knight.piece);
    }

    public void addBishop(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece bishop = new Piece("Bishop", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(bishop, bishop.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, bishop.piece);
    }

    public void addQueen(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece queen = new Piece("Queen", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(queen, queen.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, queen.piece);
    }

    public void addKing(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece king = new Piece("King", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(king, king.piece);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, king.piece);
    }

    // method for adding pieces to the board
    private void addPieceToBoard(JPanel chessBoard, int rank, int file, JLabel pieceLabel) {
        int index = (rank * files) + file;
        JPanel square = (JPanel) chessBoard.getComponent(index);
        square.add(pieceLabel);
        square.revalidate();
        square.repaint();
    }

    // method for creating mouse listeners on each piece
    private void createMouseListeners() {
        for (Map.Entry<Piece, JLabel> entry : pieces.entrySet()) {
            JLabel pieceLabel = (JLabel) entry.getValue();
            pieceLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onPieceClicked(e);
                }
            });
        }
    }

    // runs the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // container for all app elements
                Main contentFrame = new Main();
                // the window itself
                JFrame application = new JFrame("Chess");
                application.setPreferredSize(new Dimension(1200, 1200));
                application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                application.getContentPane().add(contentFrame);
                application.setLocationByPlatform(true);
                // finalize the window and run
                application.pack();
                application.setVisible(true);

            }
        });
    }

    public void onPieceClicked(MouseEvent e) {
        // check which piece was clicked
        JLabel clickedPiece = (JLabel) e.getSource();
        System.out.println("Piece clicked at: " + clickedPiece.getX() + " " + clickedPiece.getY());
        selectedPiece = clickedPiece;
    }

    public void onSquareClicked(MouseEvent e) {
        JPanel clickedSquare = (JPanel) e.getSource();
        System.out.println("Square clicked at: " + clickedSquare.getX() + " " + clickedSquare.getY());

        // if a piece is selected, move it to the selected square
        if (selectedPiece != null) {
            // remove image from old square and put in new square
            JPanel parentSquare = (JPanel) selectedPiece.getParent();
            clickedSquare.add(selectedPiece);
            parentSquare.remove(selectedPiece);
            // update squares
            clickedSquare.revalidate();
            clickedSquare.repaint();
            parentSquare.revalidate();
            parentSquare.repaint();
            // deselect piece after done
            System.out.println("Piece {" + selectedPiece.getName() + "} moved");
            selectedPiece = null;
            // increment move counter
            numMoves += 1;
        }
    }
}
