package com.jdd050.chess;

import com.jdd050.chess.Logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    public HashMap<JLabel, Piece> pieces = new HashMap<>();

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
                // create the square and set its name to its coordinates
                JPanel square = new JPanel();
                String coordinates = String.valueOf(j + 1) + "," + String.valueOf(i + 1);
                square.setName(coordinates);
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
        pieces.put(pawn.piece, pawn);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, pawn);
    }

    public void addRook(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece rook = new Piece("Rook", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(rook.piece, rook);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, rook);
    }

    public void addKnight(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece knight = new Piece("Knight", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(knight.piece, knight);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, knight);
    }

    public void addBishop(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece bishop = new Piece("Bishop", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(bishop.piece, bishop);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, bishop);
    }

    public void addQueen(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece queen = new Piece("Queen", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(queen.piece, queen);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, queen);
    }

    public void addKing(int rank, int file, Color pieceColor) {
        // get piece id based on amount of existing pieces
        int pieceId = pieces.size();
        // create the piece
        Piece king = new Piece("King", pieceColor, pieceId, new Dimension(rank, file));
        pieces.put(king.piece, king);
        // add piece to the board
        addPieceToBoard(chessBoard, rank, file, king);
    }

    // method for adding pieces to the board
    private void addPieceToBoard(JPanel chessBoard, int rank, int file, Piece piece) {
        // set index
        int index = (rank * files) + file;
        // set piece location
        piece.pieceLocation = new Dimension(rank + 1, file + 1);
        // add to board
        JPanel square = (JPanel) chessBoard.getComponent(index);
        square.add(piece.piece);
        square.revalidate();
        square.repaint();
    }

    // method for creating mouse listeners on each piece
    private void createMouseListeners() {
        for (Map.Entry<JLabel, Piece> entry : pieces.entrySet()) {
            JLabel pieceLabel = (JLabel) entry.getKey();
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
        System.out.println("Piece clicked at: " + pieces.get(clickedPiece).pieceLocation.height + " " + pieces.get(clickedPiece).pieceLocation.width);
        selectedPiece = clickedPiece;
    }

    // TODO:
    // Check for pieces in path, eg. bishop cannot jump over pawns, pieces cannot occupy same square
    // implement capture logic
    // implement turns
    public void onSquareClicked(MouseEvent e) {
        JPanel clickedSquare = (JPanel) e.getSource();
        Logic validation = new Logic();

        // if a piece is selected, move it to the selected square
        if (selectedPiece != null) {
            JPanel parentSquare = (JPanel) selectedPiece.getParent();
            // need to get Piece class instance to check index
            Piece pieceObject = pieces.get(selectedPiece);
            // calculate move distance in horizontal and vertical components for validation
            String parentCoordinates = parentSquare.getName();
            String clickedCoordinates = clickedSquare.getName();
            // convert parent coordinates to integers
            ArrayList<Integer> pCoordinates = new ArrayList<Integer>();
            for (int i = 0; i < parentCoordinates.length(); i++) {
                int number = parentCoordinates.charAt(i) - '0';
                // ignore non numbers
                if (number < 0) {
                    continue;
                } else {
                    pCoordinates.addLast(number);
                }
            }
            // convert clicked coordinates to integers
            ArrayList<Integer> cCoordinates = new ArrayList<Integer>();
            for (int i = 0; i < clickedCoordinates.length(); i++) {
                int number = clickedCoordinates.charAt(i) - '0';
                // ignore non numbers
                if (number < 0) {
                    continue;
                } else {
                    cCoordinates.addLast(number);
                }
            }
            // validate the move
            int moveX = Math.abs(cCoordinates.getFirst() - pCoordinates.getFirst());
            int moveY = Math.abs(cCoordinates.getLast() - pCoordinates.getLast());
            boolean validMove = validation.validateMove(pieceObject, moveX, moveY);
            // remove image from old square and put in new square
            if (validMove) {
                clickedSquare.add(selectedPiece);
                parentSquare.remove(selectedPiece);
                // update squares
                clickedSquare.revalidate();
                clickedSquare.repaint();
                parentSquare.revalidate();
                parentSquare.repaint();
                // display information about valid move
                System.out.println("Valid move #" + Integer.toString(numMoves) + ":");
                System.out.println("Piece '" + selectedPiece.getName() + "' moved from " + pCoordinates + " to " + cCoordinates + ".");
                // deselect piece after done
                selectedPiece = null;
                // increment move counter
                numMoves += 1;
            } else {
                System.out.println("## INVALID MOVE ##");
                selectedPiece = null;
            }
        }
    }
}
