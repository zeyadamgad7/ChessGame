package ChessCore;

import ChessCore.Pieces.King;
import ChessCore.Pieces.Piece;

public final class Utilities {

    private Player player;
    private Utilities() {
    }

    public static Player revertPlayer(Player player) {
        return player == Player.WHITE ? Player.BLACK : Player.WHITE;
    }

    public static boolean willOwnKingBeAttacked(Player whoseTurn, Move move, ChessBoard board) {
        ChessBoard clone = new ChessBoard(board);
        Piece pieceFrom = clone.getPieceAtSquare(move.getFromSquare());
        Square intermediateCastleSquare = tryGetIntermediateCastleSquare(pieceFrom, move);
        clone.setPieceAtSquare(move.getFromSquare(), null);
        clone.setPieceAtSquare(move.getToSquare(), pieceFrom);

        Square kingSquare = getKingSquare(whoseTurn, clone);
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        for (BoardFile file : files) {
            for (BoardRank rank : ranks) {
                Square sq = new Square(file, rank);
                Piece p = clone.getPieceAtSquare(sq);
                if (p != null &&
                        p.getOwner() != whoseTurn &&
                        (p.isAttackingSquare(sq, kingSquare, clone) || (intermediateCastleSquare != null && p.isAttackingSquare(sq, intermediateCastleSquare, clone)))) {
                    return true;
                }
            }
        }

        return false;
    }

   /* public void setWhoseTurn(chessCore.Player){
    this.player =player;
    }*/
    public static boolean isInCheck(Player player, ChessBoard board) {
        ChessBoard clone = new ChessBoard(board);

        Square kingSquare = getKingSquare(player, clone);
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        for (BoardFile file : files) {
            for (BoardRank rank : ranks) {
                Square sq = new Square(file, rank);
                Piece p = clone.getPieceAtSquare(sq);
                if (p != null &&
                        p.getOwner() != player &&
                        p.isAttackingSquare(sq, kingSquare, clone)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Square tryGetIntermediateCastleSquare(Piece pieceFrom, Move move) {
        // Castling moves the king two squares.
        // The intermediate square is the square being "jumped", i.e, the square between the "from" and "to" squares.
        if (pieceFrom instanceof King && move.getAbsDeltaX() == 2 && move.getDeltaY() == 0) {
            // This is a castle move. The "from" square is either E1 for white, or E8 for black.
            // The intermediate square is one of:
            // F1 -> King-side white castle.
            // F8 -> King-side black castle
            // D1 -> Queen-side white castle.
            // D8 -> Queen-side black castle.
            BoardRank rank = move.getFromSquare().getRank();
            BoardFile file;
            if (move.getDeltaX() == 2) {
                file = BoardFile.F;
            } else {
                file = BoardFile.D;
            }

            return new Square(file, rank);
        }

        return null;
    }

    private static  Square getKingSquare(Player whoseTurn, ChessBoard board) {
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        for (BoardFile file : files) {
            for (BoardRank rank : ranks) {
                Square sq = new Square(file, rank);
                Piece p = board.getPieceAtSquare(sq);
                if (p instanceof King && p.getOwner() == whoseTurn) {
                    return sq;
                }
            }
        }

        throw new RuntimeException("There is no king in the board! Something went very wrong.");
    }
}
