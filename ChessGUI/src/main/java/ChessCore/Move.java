package ChessCore;

public final class Move {
    private final Square fromSquare;
    private final Square toSquare;
    private final PawnPromotion pawnPromotion;

    public Move(Square fromSquare, Square toSquare) {
        this(fromSquare, toSquare, PawnPromotion.None);
    }

    public Move(Square fromSquare, Square toSquare, PawnPromotion pawnPromotion) {
        this.fromSquare = fromSquare;
        this.toSquare = toSquare;
        this.pawnPromotion = pawnPromotion;
    }

    public Square getFromSquare() {
        return fromSquare;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public PawnPromotion getPawnPromotion() {
        return pawnPromotion;
    }

    public boolean isHorizontalMove() {
        return getDeltaX() != 0 && getDeltaY() == 0;
    }

    public boolean isVerticalMove() {
        return getDeltaX() == 0 && getDeltaY() != 0;
    }

    public boolean isDiagonalMove() {
        int deltaX = getAbsDeltaX();
        int deltaY = getAbsDeltaY();

        return deltaX == deltaY && deltaX != 0;
    }

    public int getDeltaX() {
        return getToSquare().getFile().getValue() - getFromSquare().getFile().getValue();
    }

    public int getDeltaY() {
        return getToSquare().getRank().getValue() - getFromSquare().getRank().getValue();
    }

    public int getAbsDeltaX() {
        return Math.abs(getDeltaX());
    }

    public int getAbsDeltaY() {
        return Math.abs(getDeltaY());
    }
}
