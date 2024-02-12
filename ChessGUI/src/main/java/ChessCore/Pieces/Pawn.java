package ChessCore.Pieces;

import ChessCore.*;

public final class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        Player whoseTurn = game.getWhoseTurn();
        Square from = move.getFromSquare();
        Square to = move.getToSquare();
        if (whoseTurn == Player.WHITE) {
            if (from.getRank() == BoardRank.SECOND && move.getDeltaY() == 2) {
                return move.getDeltaX() == 0 &&
                        !game.hasPieceIn(new Square(from.getFile(), BoardRank.THIRD)) &&
                        !game.hasPieceIn(new Square(from.getFile(), BoardRank.FORTH));
            }

            if (move.getDeltaY() != 1) {
                return false;
            }

            if (to.getRank() == BoardRank.EIGHTH && move.getPawnPromotion() == PawnPromotion.None) {
                return false;
            }

            if (move.getAbsDeltaX() == 1) {
                return game.hasPieceInSquareForPlayer(to, Utilities.revertPlayer(whoseTurn)) || canCaptureEnpassant(game, move);
            }
            else if (move.getAbsDeltaX() == 0) {
                return !game.hasPieceIn(to);
            }
        } else {
            if (from.getRank() == BoardRank.SEVENTH && move.getDeltaY() == -2) {
                return move.getDeltaX() == 0 &&
                        !game.hasPieceIn(new Square(from.getFile(), BoardRank.SIXTH)) &&
                        !game.hasPieceIn(new Square(from.getFile(), BoardRank.FIFTH));
            }

            if (move.getDeltaY() != -1) {
                return false;
            }

            if (to.getRank() == BoardRank.FIRST && move.getPawnPromotion() == PawnPromotion.None) {
                return false;
            }

            if (move.getAbsDeltaX() == 1) {
                return game.hasPieceInSquareForPlayer(to, Utilities.revertPlayer(whoseTurn)) || canCaptureEnpassant(game, move);
            }
            else if (move.getAbsDeltaX() == 0) {
                return !game.hasPieceIn(to);
            }
        }

        return false;
    }

    private boolean canCaptureEnpassant(ChessGame game, Move move) {
        Move lastMove = game.getLastMove();
        return lastMove != null &&
                game.getPieceAtSquare(lastMove.getToSquare()) instanceof Pawn &&
                lastMove.getAbsDeltaY() == 2 &&
                lastMove.getToSquare().getRank() == move.getFromSquare().getRank() &&
                lastMove.getToSquare().getFile() == move.getToSquare().getFile();
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        int attackingDeltaY = getOwner() == Player.WHITE ? 1 : -1;
        return move.getDeltaY() == attackingDeltaY && move.getAbsDeltaX() == 1;
    }
}
