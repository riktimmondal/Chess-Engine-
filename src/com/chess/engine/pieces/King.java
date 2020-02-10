package com.chess.engine.pieces;

import com.chess.engine.*;
import com.google.common.collect.ImmutableList;

import java.util.*;

public class King extends Piece{

    private final static int[] CANDIDATA_MOVE_COORDINATES = {-9,-8,-7,-1,1,7,8,9};

    King(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATA_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate =this.piecePosition + currentCandidateOffset;
            
            if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
            || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance)
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate));
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -9) ||(candidateOffset == -1)
                    || (candidateOffset == 7));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -7) || (candidateOffset == 1) || (candidateOffset == 9));
    }
}

