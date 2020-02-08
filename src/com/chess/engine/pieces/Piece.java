package com.chess.engine.pieces;

import com.chess.engine.*;
import com.google.common.collect.Collections2;

import java.*;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    public abstract Collections<Move> calculateLegalMoves(final Board board);
}