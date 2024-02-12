package ChessCore;

import ChessCore.Pieces.Piece;
import ChessCore.Pieces.Queen;

public class FQueen implements PieceFactory {

    @Override
    public Piece createNewPiece(Player p) {
        Queen fq = new Queen(p);
        return fq;
    }

}
