package ChessCore;

import ChessCore.Pieces.Piece;

interface PieceFactory {
    Piece createNewPiece(Player p);
}
