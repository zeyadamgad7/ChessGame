package ChessCore.Pieces;

import ChessCore.*;

public final class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}
