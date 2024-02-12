package ChessCore.Pieces;

import ChessCore.*;

public final class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return move.isDiagonalMove() && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        return move.isDiagonalMove() && !board.isTherePieceInBetween(move);
    }
}
