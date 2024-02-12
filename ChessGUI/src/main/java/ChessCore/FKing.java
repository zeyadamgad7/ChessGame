package ChessCore;

import ChessCore.Pieces.King;
import ChessCore.Pieces.Piece;

public class FKing implements PieceFactory{

    @Override
    public Piece createNewPiece(Player p) {
King fkg = new King(p);
return fkg;    }
    
}
