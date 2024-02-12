package ChessCore;

import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;

public class FPawn implements PieceFactory{

    @Override
    public Piece createNewPiece(Player p) {
        Pawn fp=new Pawn(p); 
        return fp;
    }
    
}
