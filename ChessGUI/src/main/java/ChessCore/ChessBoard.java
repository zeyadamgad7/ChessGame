package ChessCore;

import ChessCore.Pieces.Piece;

public final class ChessBoard {
    private final Piece[][] board;

    public ChessBoard(Piece[][] board) {
        this.board = board;
    }

    // This is a copy constructor, used for cloning the ChessBoard.
    public ChessBoard(ChessBoard board) {
        this.board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = board.board[i][j];
            }
        }
    }

    public Piece getPieceAtSquare(Square square) {
        return board[square.getRank().ordinal()][square.getFile().ordinal()];
    }

    public void setPieceAtSquare(Square square, Piece piece) {
        board[square.getRank().ordinal()][square.getFile().ordinal()] = piece;
    }

    public boolean isTherePieceInBetween(Move move) {
        Square from = move.getFromSquare();
        Square to = move.getToSquare();
        // TODO: Refactor this.
        if (move.isVerticalMove()) {
            BoardFile file = from.getFile();
            boolean moveForward = move.getDeltaY() > 0;
            var currentRank = from.getRank();
            while (true) {
                currentRank = moveForward ? currentRank.getNext() : currentRank.getPrevious();

                if (currentRank == to.getRank()) {
                    break;
                }

                if (getPieceAtSquare(new Square(file, currentRank)) != null) {
                    return true;
                }
            }
        }
        else if (move.isHorizontalMove()) {
            BoardRank rank = from.getRank();
            boolean moveForward = move.getDeltaX() > 0;
            var currentFile = from.getFile();
            while (true) {
                currentFile = moveForward ? currentFile.getNext() : currentFile.getPrevious();

                if (currentFile == to.getFile()) {
                    break;
                }

                if (getPieceAtSquare(new Square(currentFile, rank)) != null) {
                    return true;
                }
            }
        }
        else if (move.isDiagonalMove()) {
            BoardRank currentRank = from.getRank();
            BoardFile currentFile = from.getFile();

            boolean moveForwardX = move.getDeltaX() > 0;
            boolean moveForwardY = move.getDeltaY() > 0;
            while (true) {
                currentRank = moveForwardY ? currentRank.getNext() : currentRank.getPrevious();
                currentFile = moveForwardX ? currentFile.getNext() : currentFile.getPrevious();

                if (currentRank == to.getRank() && currentFile == to.getFile()) {
                    break;
                }

                if (getPieceAtSquare(new Square(currentFile, currentRank)) != null) {
                    return true;
                }
            }
        }

        return false;
    }
}
