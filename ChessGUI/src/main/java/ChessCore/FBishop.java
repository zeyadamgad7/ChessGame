package ChessCore;

import ChessCore.Pieces.Bishop;
import ChessCore.Pieces.Piece;

public class FBishop implements PieceFactory {

    @Override
    public Piece createNewPiece(Player p) {
        Bishop fb = new Bishop(p);
        return fb;
    }

}
