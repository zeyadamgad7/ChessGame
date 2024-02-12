package ChessCore;

import ChessCore.Pieces.Knight;
import ChessCore.Pieces.Piece;

public class FKnight implements PieceFactory{

    @Override
    public Piece createNewPiece(Player p) {
Knight fk = new Knight(p);
return fk;
    }
    
}
