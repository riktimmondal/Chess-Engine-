package com.chess.engine.pieces;

import com.chess.engine.*;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATA_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};

    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(int currentCandidateOffset: CANDIDATA_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if(isFirstColumnExclusion(this.piecePosition, currentcandidateOffset) ||
                    isSecondColumnExclusion(this.piecePosition, currentcandidateOffset) || 
                    isSeventhColumnExclusion(this.piecePosition, currentcandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentcandidateOffset)) {
                    continue;
                }

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
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) ||(candidateOffset == -10)
                    || (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) ||(candidateOffset == -6)
                    || (candidateOffset == 10) || (candidateOffset == 17));
    }
}


