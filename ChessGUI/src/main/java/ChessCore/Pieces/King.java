package ChessCore.Pieces;

import ChessCore.*;

public final class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        var absDeltaX = move.getAbsDeltaX();
        var absDeltaY = move.getAbsDeltaY();
        if (absDeltaX <= 1 && absDeltaY <= 1) {
            return true;
        }

        // Note that Utilities.willOwnKingBeAttacked is responsible for checking whether
        // the intermediate castling square (i.e, F1, F8, D1, or D8) is not attacked.
        if (absDeltaY == 0) {
            if (move.getDeltaX() == 2) {
                if (game.isTherePieceInBetween(move)) {
                    return false;
                }
                // King-side
                if (getOwner() == Player.WHITE && game.isCanWhiteCastleKingSide()) {
                    // King is moving from E1 to G1.
                    if (move.getFromSquare().getRank() != BoardRank.FIRST || move.getFromSquare().getFile() != BoardFile.E) {
                        throw new RuntimeException("Castle can't be valid from square different than E1");
                    }
                    return true;
                } else if (getOwner() == Player.BLACK && game.isCanBlackCastleKingSide()) {
                    // King is moving from E8 to G8.
                    if (move.getFromSquare().getRank() != BoardRank.EIGHTH || move.getFromSquare().getFile() != BoardFile.E) {
                        throw new RuntimeException("Castle can't be valid from square different than E8");
                    }
                    return true;
                }
            } else if (move.getDeltaX() == -2) {
                if (game.isTherePieceInBetween(move)) {
                    return false;
                }
                // Queen-side.
                if (getOwner() == Player.WHITE && game.isCanWhiteCastleQueenSide()) {
                    // King is moving from E1 to C1
                    if (move.getFromSquare().getRank() != BoardRank.FIRST || move.getFromSquare().getFile() != BoardFile.E) {
                        throw new RuntimeException("Castle can't be valid from square different than E1");
                    }
                    return true;
                } else if (getOwner() == Player.BLACK && game.isCanBlackCastleQueenSide()) {
                    // King is moving from E8 to C8
                    if (move.getFromSquare().getRank() != BoardRank.EIGHTH || move.getFromSquare().getFile() != BoardFile.E) {
                        throw new RuntimeException("Castle can't be valid from square different than E8");
                    }
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        var deltaX = move.getAbsDeltaX();
        var deltaY = move.getAbsDeltaY();
        return deltaX <= 1 && deltaY <= 1;
    }
}
