package com.chess.engine.pieces;

import com.chess.engine.*;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        //TODO update this
        this.isFirstMove = false;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    
    public int getPiecePosition() {
        return this.piecePosition;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                // TODO Auto-generated method stub
                return true;
            }
        };

        private String pieceName;
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
        
    }
}