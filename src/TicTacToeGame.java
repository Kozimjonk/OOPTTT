import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeGame extends JFrame{
    JFrame frame = new JFrame();

    JPanel mainPnl;
    JPanel topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JLabel titleLbl;
    JButton quitButton;

    boolean dontSwitch;
    TicTacToeBoard TTTBoard = new TicTacToeBoard();

    public TicTacToeGame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createMiddlePanel();
        mainPnl.add(midPnl, BorderLayout.CENTER);
        createBottomPanel();
        mainPnl.add(botPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel()
    {
        topPnl = new JPanel();
        titleLbl = new JLabel("TicTacToe",JLabel.CENTER);
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 38));



        topPnl.add(titleLbl);

    }

    private void createMiddlePanel()
    {
        midPnl = new JPanel();
        midPnl.setLayout(new GridLayout(3,3));
        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                midPnl.add(TTTBoard.getBoard()[row][col]);

                TTTBoard.getBoard()[row][col].addActionListener(
                        (ActionEvent ae) -> {
                            Object source = ae.getSource();
                            if(source instanceof JButton)
                            {
                                JButton buttonSource = (JButton)source;

                                if(buttonSource.getText().equals(" "))
                                {
                                    buttonSource.setText(TTTBoard.getPlayer());
                                    TTTBoard.setMoveCnt(TTTBoard.getMoveCnt() + 1);
                                    if(TTTBoard.getMoveCnt() >= TTTBoard.getMOVES_FOR_WIN())
                                    {
                                        if(isWin(TTTBoard.getPlayer()))
                                        {
                                            int a = JOptionPane.showConfirmDialog(frame, TTTBoard.getPlayer() + " wins! Play Again?");
                                            if(a == JOptionPane.YES_OPTION)
                                            {
                                                TTTBoard.clearBoard();
                                            }
                                            else if (a == JOptionPane.NO_OPTION)
                                            {
                                                System.exit(0);
                                            }
                                        }
                                    }
                                    if(TTTBoard.getMoveCnt() >= TTTBoard.getMOVES_FOR_TIE())
                                    {
                                        if(TTTBoard.isTie())
                                        {
                                            int a = JOptionPane.showConfirmDialog(frame, "It's a tie! Play again?");
                                            if(a == JOptionPane.YES_OPTION)
                                            {
                                                TTTBoard.clearBoard();
                                            }
                                            else if (a == JOptionPane.NO_OPTION)
                                            {
                                                System.exit(0);
                                            }
                                        }
                                    }


                                    if(TTTBoard.getPlayer().equals("X") && !dontSwitch)
                                    {
                                        TTTBoard.setPlayer("O");
                                    }
                                    else
                                    {
                                        TTTBoard.setPlayer("X");
                                    }
                                    dontSwitch = false; //keeps player from switching after a game is over



                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(frame, "That is an invalid move: spot already taken");
                                }
                            }
                        });
            }
    }


    private void createBottomPanel()
    {
        botPnl = new JPanel();
        botPnl.setLayout(new GridLayout(1,2));
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        botPnl.add(quitButton);
    }

    private boolean isWin(String player)
    {
        if(TTTBoard.isColWin(player) || TTTBoard.isRowWin(player) || TTTBoard.isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }


}