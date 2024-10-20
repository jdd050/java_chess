package com.jdd050.chess;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class Logic {
    // fields
    private HashMap<JLabel,Piece> pieces  = null;
    private int moveHorizontal = 0;
    private int moveVertical = 0;
    private Piece activePiece = null;
    private String trimmedType = "";

    // method to pass chessboard panel from Main
    public void setPieces(HashMap<JLabel, Piece> pieceHashMap) {
        pieces = pieceHashMap;
    }

    // method to check piece path for obstructions
    private boolean pathCheck() {
        // calculate destination coordinates
        Dimension currentLocation = activePiece.pieceLocation;
        Dimension destination = new Dimension(activePiece.pieceLocation.width + moveHorizontal, activePiece.pieceLocation.height + moveVertical);
        // check path in front of piece for other pieces
        if (!Objects.equals(trimmedType, "knight")) {
            // northeast (+, +)
            if (moveHorizontal > 0 && moveVertical > 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width + 1, currentLocation.height + 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // southeast (+, -)
            else if (moveHorizontal > 0 && moveVertical < 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width + 1, currentLocation.height - 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // east (+, =)
            else if (moveHorizontal > 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width + 1, currentLocation.height);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // west (-, =)
            else if (moveHorizontal < 0 && moveVertical == 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width - 1, currentLocation.height);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // northwest (-, +)
            else if (moveHorizontal < 0 && moveVertical > 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width - 1, currentLocation.height + 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // southwest (-, -)
            else if (moveHorizontal < 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width - 1, currentLocation.height - 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // north (=, +)
            else if (moveVertical > 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width, currentLocation.height + 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
            // south (=, -)
            else if (moveVertical < 0) {
                while (!Objects.equals(currentLocation, destination)) {
                    Dimension intermediateDestination = new Dimension(currentLocation.width, currentLocation.height - 1);
                    for (Piece potentialObstacle : pieces.values()) {
                        if (Objects.equals(intermediateDestination, potentialObstacle.pieceLocation)) {
                            return false;
                        }
                    }
                    currentLocation.setSize(intermediateDestination.width, intermediateDestination.height);
                }
                return true;
            }
        }
        return true;
    }

    // method to validate basic move logic
    public boolean validateMove(Piece piece, int moveX, int moveY) {
        // define fields for movement (for pathCheck)
        activePiece = piece;
        moveHorizontal = moveX;
        moveVertical = moveY;
        // get magnitude of movement components for logic validation
        moveX = Math.abs(moveX);
        moveY = Math.abs(moveY);
        // invalidate 0 distance moves
        if (moveX == 0 && moveY == 0) {
            return false;
        }
        // trim piece color
        String pieceType = "";
        if (piece.pieceType.contains("white_")) {
            pieceType = piece.pieceType.replace("white_", "");
        } else if (piece.pieceType.contains("black_")) {
            pieceType = piece.pieceType.replace("black_", "");
        }
        trimmedType = pieceType;
        // move logic
        switch (pieceType) {
            case "pawn":
                // disallow horizontal movement
                // en passant will be added later
                if (moveX > 0) { return false; }
                else if (moveY > 1) { return false; }
            case "rook":
                // disallow diagonal movement
                if (moveY >= 1 && moveX >= 1) { return false; }
                break;
            case "knight":
                // enforce knight movement pattern
                if (Math.abs(moveX - moveY) != 1) { return false; }
                break;
            case "bishop":
                // only allow diagonal movement
                if (!(moveY >= 1 && moveX == moveY)) { return false; }
                break;
            case "queen":
                // disallow illegal queen movements
                if ((moveX > 0 && moveY > moveX) || (moveY > 0 && moveX > moveY)) { return false; }
                break;
            case "king":
                if (moveX > 1 || moveY > 1) { return false; }
                break;
        }
        return pathCheck();
    }
}
