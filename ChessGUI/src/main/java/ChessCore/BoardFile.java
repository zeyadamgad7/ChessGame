package ChessCore;

public enum BoardFile {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int value;
    private final static BoardFile[] values = values();
    BoardFile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean hasNext() {
        return value != H.getValue();
    }

    public BoardFile getNext() {
        if (value == H.getValue()) {
            throw new IndexOutOfBoundsException("There is no next.");
        }

        return values[ordinal() + 1];
    }

    public boolean hasPrevious() {
        return value != A.getValue();
    }

    public BoardFile getPrevious() {
        if (value == A.getValue()) {
            throw new IndexOutOfBoundsException("There is no previous.");
        }

        return values[ordinal() - 1];
    }
}
