package ChessGameGUI;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ChessBoard;
import ChessCore.ClassicChessGame;
import ChessCore.Move;
import ChessCore.GameStatus;
import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;
import ChessCore.Pieces.*;
import ChessCore.Player;
import ChessCore.Square;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.abs;
import java.util.List;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrontEnd extends JPanel implements MouseListener {

    public ClassicChessGame game = new ClassicChessGame();
    public boolean selectedSquare = false;
    public List<Square> returnedmoves;
    public boolean validMove = false;
    public Square from;
    public Square to;
    int clicks = 1;
    public boolean firstMove = true;
    Stack<ChessBoardMemento> mementos = new Stack<>();

    Panel square;
    boolean white;
    Piece piece;
    ImageIcon iconb1;
    Image bp;
    ImageIcon iconb2;
    Image br;
    ImageIcon iconb3;
    Image bk;
    ImageIcon iconb4;
    Image bb;
    ImageIcon iconb5;
    Image bq;
    ImageIcon iconb6;
    Image bkg;

    ImageIcon iconw1;
    Image wp;
    ImageIcon iconw2;
    Image wr;
    ImageIcon iconw3;
    Image wk;
    ImageIcon iconw4;
    Image wb;
    ImageIcon iconw5;
    Image wq;
    ImageIcon iconw6;
    Image wkg;

    public FrontEnd() {
        //square = new Panel();
        white = true;

        setLayout(null);
        JButton undo = new JButton("UNDO");
        undo.addActionListener(this::undoButtonClicked);
        undo.setBounds(9 * 95, 7 * 95, 100, 50);
        add(undo);
        undo.setVisible(true);

        iconb1 = new ImageIcon("src\\main\\java\\pics\\BlackPawn.png");
        bp = iconb1.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconb2 = new ImageIcon("src\\main\\java\\pics\\BlackRook.png");
        br = iconb2.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconb3 = new ImageIcon("src\\main\\java\\pics\\BlackKnight.png");
        bk = iconb3.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconb4 = new ImageIcon("src\\main\\java\\pics\\BlackBishop.png");
        bb = iconb4.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconb5 = new ImageIcon("src\\main\\java\\pics\\BlackQueen.png");
        bq = iconb5.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconb6 = new ImageIcon("src\\main\\java\\pics\\BlackKing.png");
        bkg = iconb6.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);

        iconw1 = new ImageIcon("src\\main\\java\\pics\\WhitePawn.png");
        wp = iconw1.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconw2 = new ImageIcon("src\\main\\java\\pics\\WhiteRook.png");
        wr = iconw2.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconw3 = new ImageIcon("src\\main\\java\\pics\\WhiteKnight.png");
        wk = iconw3.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconw4 = new ImageIcon("src\\main\\java\\pics\\WhiteBishop.png");
        wb = iconw4.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconw5 = new ImageIcon("src\\main\\java\\pics\\WhiteQueen.png");
        wq = iconw5.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);
        iconw6 = new ImageIcon("src\\main\\java\\pics\\WhiteKing.png");
        wkg = iconw6.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT);

    }

    public static void main(String[] args) {
        FrontEnd f = new FrontEnd();
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.getContentPane().add(f);
        f.addMouseListener(f);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (white) {
                    g.setColor(Color.white.darker());
                } else {
                    g.setColor(Color.black.darker());
                }
                g.fillRect(x * 95, y * 95, 95, 95);
                white = !white;
            }
            white = !white;
        }
        if (selectedSquare == true && returnedmoves != null) {
            g.setColor(new Color(249, 255, 33, 200));
            for (int i = 0; i < returnedmoves.size(); i++) {
                int x[] = squareToCoordinate(returnedmoves.get(i));

                if (game.getWhoseTurn() == Player.BLACK) {
                    int i1 = x[0] / 95;
                    int j1 = x[1] / 95;
                    int i2 = FlipCoordinate(i1) * 95;
                    int j2 = FlipCoordinate(j1) * 95;
                    g.fillRect(i2, j2, 95, 95);

                } else {
                    g.fillRect(x[0], x[1], 95, 95);
                }

            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piece = game.getBoard().getPieceAtSquare(coordinateToSquare(i * 95, j * 95));
                int j1 = 0, i1 = 0;
                if (game.getWhoseTurn() == Player.BLACK) {
                    i1 = FlipCoordinate(i);
                    j1 = FlipCoordinate(j);
                }
                if (game.getGameStatus() == GameStatus.BLACK_WON) {
                    JOptionPane.showMessageDialog(null, "Black Won !");
                    System.exit(0);
                } else if (game.getGameStatus() == GameStatus.WHITE_WON) {
                    JOptionPane.showMessageDialog(null, "White Won !");
                    System.exit(0);
                } else if (game.getGameStatus() == GameStatus.INSUFFICIENT_MATERIAL) {
                    JOptionPane.showMessageDialog(null, "Insufficient Material!!");
                    System.exit(0);
                } else if (game.getGameStatus() == GameStatus.STALEMATE) {
                    JOptionPane.showMessageDialog(null, "StaleMate!!");
                    System.exit(0);
                }
                if (game.getBoard().getPieceAtSquare(coordinateToSquare(i * 95, j * 95)) == null) {
                    continue;
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof Pawn) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wp, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(wp, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof Pawn) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(bp, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(bp, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof Rook) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wr, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(wr, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof Rook) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(br, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(br, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof Bishop) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wb, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(wb, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof Bishop) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(bb, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(bb, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof Knight) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wk, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(wk, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof Knight) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(bk, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(bk, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof Queen) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wq, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(wq, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof Queen) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(bq, i1 * 95, j1 * 95, this);
                    } else {
                        g.drawImage(bq, i * 95, j * 95, this);
                    }
                }
                if (piece.getOwner() == Player.WHITE && piece instanceof King) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(wkg, i1 * 95, j1 * 95, this);
                        if (game.getGameStatus() == GameStatus.WHITE_UNDER_CHECK) {
                            g.setColor(Color.red);
                            g.fillRect(i1 * 95, j1 * 95, 95, 95);
                            g.drawImage(wkg, i1 * 95, j1 * 95, this);
                        }
                    } else {
                        g.drawImage(wkg, i * 95, j * 95, this);
                        if (game.getGameStatus() == GameStatus.WHITE_UNDER_CHECK) {
                            g.setColor(Color.red);
                            g.fillRect(i * 95, j * 95, 95, 95);
                            g.drawImage(wkg, i * 95, j * 95, this);
                        }
                    }
                }
                if (piece.getOwner() == Player.BLACK && piece instanceof King) {
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawImage(bkg, i1 * 95, j1 * 95, this);
                        if (game.getGameStatus() == GameStatus.BLACK_UNDER_CHECK) {
                            g.setColor(Color.red);
                            g.fillRect(i1 * 95, j1 * 95, 95, 95);
                            g.drawImage(bkg, i1 * 95, j1 * 95, this);
                        }
                    } else {
                        g.drawImage(bkg, i * 95, j * 95, this);
                        if (game.getGameStatus() == GameStatus.BLACK_UNDER_CHECK) {
                            g.setColor(Color.red);
                            g.fillRect(i * 95, j * 95, 95, 95);
                            g.drawImage(bkg, i1 * 95, j1 * 95, this);
                        }
                    }
                }
            }
        }
    }

    public Square coordinateToSquare(int x, int y) {
        BoardFile Letter = null;
        BoardRank Digit = null;
        if (x >= 0 * 95 && x < 1 * 95) {
            Letter = BoardFile.A;
        } else if (x >= 1 * 95 && x < 2 * 95) {
            Letter = BoardFile.B;
        } else if (x >= 2 * 95 && x < 3 * 95) {
            Letter = BoardFile.C;
        } else if (x >= 3 * 95 && x < 4 * 95) {
            Letter = BoardFile.D;
        } else if (x >= 4 * 95 && x < 5 * 95) {
            Letter = BoardFile.E;
        } else if (x >= 5 * 95 && x < 6 * 95) {
            Letter = BoardFile.F;
        } else if (x >= 6 * 95 && x < 7 * 95) {
            Letter = BoardFile.G;
        } else if (x >= 7 * 95 && x < 8 * 95) {
            Letter = BoardFile.H;
        }

        if (y >= 0 * 95 && y < 1 * 95) {
            Digit = BoardRank.EIGHTH;
        } else if (y >= 1 * 95 && y < 2 * 95) {
            Digit = BoardRank.SEVENTH;
        } else if (y >= 2 * 95 && y < 3 * 95) {
            Digit = BoardRank.SIXTH;
        } else if (y >= 3 * 95 && y < 4 * 95) {
            Digit = BoardRank.FIFTH;
        } else if (y >= 4 * 95 && y < 5 * 95) {
            Digit = BoardRank.FORTH;
        } else if (y >= 5 * 95 && y < 6 * 95) {
            Digit = BoardRank.THIRD;
        } else if (y >= 6 * 95 && y < 7 * 95) {
            Digit = BoardRank.SECOND;
        } else if (y >= 7 * 95 && y < 8 * 95) {
            Digit = BoardRank.FIRST;
        }

        Square sq = new Square(Letter, Digit);
        return sq;
    }

    public int[] squareToCoordinate(Square sq) {
        int[] list;
        list = new int[2];
        BoardFile Letter = sq.getFile();
        BoardRank Digit = sq.getRank();
        int x = -1, y = -1;
        if (null != Letter) {
            switch (Letter) {
                case A ->
                    x = 0 * 95;
                case B ->
                    x = 1 * 95;
                case C ->
                    x = 2 * 95;
                case D ->
                    x = 3 * 95;
                case E ->
                    x = 4 * 95;
                case F ->
                    x = 5 * 95;
                case G ->
                    x = 6 * 95;
                case H ->
                    x = 7 * 95;
                default -> {
                }
            }
        }

        if (null != Digit) {
            switch (Digit) {
                case EIGHTH ->
                    y = 0 * 95;
                case SEVENTH ->
                    y = 1 * 95;
                case SIXTH ->
                    y = 2 * 95;
                case FIFTH ->
                    y = 3 * 95;
                case FORTH ->
                    y = 4 * 95;
                case THIRD ->
                    y = 5 * 95;
                case SECOND ->
                    y = 6 * 95;
                case FIRST ->
                    y = 7 * 95;
                default -> {
                }
            }
        }
        list[0] = x;
        list[1] = y;
        return list;
    }

    public int FlipCoordinate(int coor) {
        int list;
        list = abs(coor - 7);
        return list;
    }
    
    public void addMemento(ChessBoardMemento memento){
        mementos.push(memento);
    }

    public void undo(){
        if (mementos.size() <= 1) {
            JOptionPane.showMessageDialog(null, "No more undos!");
            return;
        }
        
        mementos.pop();
        game.restoreFromMemento(mementos.peek());
        
        if (game.getWhoseTurn() == Player.BLACK) {
            game.setWhoseTurnWhite();
        } else {
            game.setWhoseTurnBlack();
        }
        this.repaint();
    }
    
    private void undoButtonClicked(ActionEvent e) {
        undo();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        validMove = false;
        selectedSquare = false;

        if (clicks > 2) {
            clicks = 1;
            to = null;
        }
        if (clicks == 1) {
            if (game.getWhoseTurn() == Player.BLACK) {
                int i = e.getX() / 95;
                int j = e.getY() / 95;
                int i1 = FlipCoordinate(i) * 95;
                int j1 = FlipCoordinate(j) * 95;
                from = coordinateToSquare(i1, j1);
            } else {
                from = coordinateToSquare(e.getX(), e.getY());
            }
            selectedSquare = true;

        } else if (clicks == 2) {
            if (game.getWhoseTurn() == Player.BLACK) {
                int i = e.getX() / 95;
                int j = e.getY() / 95;
                int i1 = FlipCoordinate(i) * 95;
                int j1 = FlipCoordinate(j) * 95;
                to = coordinateToSquare(i1, j1);
            } else {
                to = coordinateToSquare(e.getX(), e.getY());
            }
        }
        clicks++;

        returnedmoves = game.getAllValidMovesFromSquare(from);

        if (firstMove == true) {
            addMemento(game.createMemento());
            firstMove = false;
        }

        if (clicks == 3) {
            Move move = new Move(from, to);
            game.makeMove(move);
            addMemento(game.createMemento());
        }
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e){
}
}