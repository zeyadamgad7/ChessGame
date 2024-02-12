package ChessCore;

import ChessCore.Pieces.Piece;
import ChessCore.Pieces.Rook;

public class FRook implements PieceFactory{

    @Override
    public Piece createNewPiece(Player p) {
    Rook fr=new Rook(p);
    return fr;
    }
    
}
