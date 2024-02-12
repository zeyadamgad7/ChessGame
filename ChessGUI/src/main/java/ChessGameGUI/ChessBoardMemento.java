package ChessGameGUI;

import ChessCore.ChessBoard;


public class ChessBoardMemento {
    private ChessBoard board;

    public ChessBoardMemento(ChessBoard board) {
        this.board = new ChessBoard(board);
    }

    public ChessBoard getBoard() {
        return new ChessBoard(board);
    }
}
