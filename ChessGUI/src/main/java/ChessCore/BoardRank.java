package ChessCore;

public enum BoardRank {
    FIRST(0),
    SECOND(1),
    THIRD(2),
    FORTH(3),
    FIFTH(4),
    SIXTH(5),
    SEVENTH(6),
    EIGHTH(7);

    private final int value;

    private final static BoardRank[] values = values();
    BoardRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean hasNext() {
        return value != EIGHTH.getValue();
    }

    public BoardRank getNext() {
        if (value == EIGHTH.getValue()) {
            throw new IndexOutOfBoundsException("There is no next.");
        }

        return values[ordinal() + 1];
    }

    public boolean hasPrevious() {
        return value != FIRST.getValue();
    }

    public BoardRank getPrevious() {
        if (value == FIRST.getValue()) {
            throw new IndexOutOfBoundsException("There is no previous.");
        }

        return values[ordinal() - 1];
    }
}
