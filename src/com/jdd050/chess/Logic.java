package com.jdd050.chess;

import java.awt.*;

public class Logic {
    public boolean validateMove(Piece piece, int moveX, int moveY) {
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
                if ((moveX != 2 && moveY != 3) || (moveX != 3 && moveY != 2)) { return false; }
                break;
            case "bishop":
                // only allow diagonal movement
                if (!(moveX >= 1 && moveY >= 1)) { return false; }
                break;
            case "queen":
                // disallow illegal queen movements
                if ((moveX >= 1 && moveY > 1) || (moveY >= 1 && moveX > 1)) { return false; }
                break;
            case "king":
                if (moveX > 1 || moveY > 1) { return false; }
                break;
        }
        return true;
    }
}
