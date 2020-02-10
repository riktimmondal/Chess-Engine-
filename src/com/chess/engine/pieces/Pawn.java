package com.chess.engine.pieces;

import com.chess.engine.*;
import java.util.Collection;
import java.*;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {7,8,9,16};

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection()*currentCandidateOffset);
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //Todo update this
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if(currentCandidateOffset == 16 && this.isFirstMove() && 
            (BoardUtils.SECOND_ROW[this.piecePosition] && thus.getPieceAlliance().isBlack()) || 
            (BoardUtils.SEVENTH_ROW[this.piecePosition] && thus.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection()*8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    //Todo update this
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }
            }
                else if(currentCandidateOffset == 7 &&
                !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && thus.getPieceAlliance().isWhite() ||
                BoardUtils.FIRST_COLUMN[this.piecePosition] && thus.getPieceAlliance().isBlack())) {
                    if(board.getTile(CandidateDestinationCoordinate).isTileOccupied()) {
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                                //Todo update this
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                }

                else if(currentCandidateOffset == 9 &&
                !(BoardUtils.FIRST_COLUMN[this.piecePosition] && thus.getPieceAlliance().isWhite() ||
                BoardUtils.EIGHTH_COLUMN[this.piecePosition] && thus.getPieceAlliance().isBlack())) {
                    if(board.getTile(CandidateDestinationCoordinate).isTileOccupied()) {
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                                //Todo update this
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                }
        }
        return ImmutableList(legalMoves);
    }
}