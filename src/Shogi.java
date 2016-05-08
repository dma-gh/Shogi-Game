import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Shogi extends JFrame {

	static JPanel boardGUI = new JPanel(new GridLayout(9,9));
	static JFrame frame = new JFrame();
	static JButton[][] squares = new JButton[9][9];
	static Board b = new Board();
	static Square lastClicked = null;

	public static void main(String[] args){

		frame.setSize(486,486);
		frame.setTitle("Shogi");
		frame.add(boardGUI);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add Squares to layout
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				squares[r][c] = new JButton();
				squares[r][c].setOpaque(true);
				squares[r][c].setSize(54, 54);
				squares[r][c].setBorder(new LineBorder(Color.BLACK));
				squares[r][c].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(int r=0;r<9;r++) {
							for(int c=0;c<9;c++) {
								if(e.getSource() == squares[r][c]) {
									if(lastClicked == null) {
										if(b.getSquare(r, c).getPiece() != null) {
											lastClicked = b.getSquare(r, c);
										}
									} else {
										b.movePiece(lastClicked, b.getSquare(r, c));
										lastClicked = null;
										updateBoard();
									}
								}
							}
						}
					}
				});
				boardGUI.add(squares[r][c]);
			}
		}

		updateBoard();
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
	}

	public static void updateBoard() {
		for(int r=0;r<9;r++) {
			for(int c=0;c<9;c++) {
				if(b.getSquare(r, c).getPiece() != null) {
					if(b.getSquare(r, c).getPiece().getOwner() == 1) {
						squares[r][c].setText(b.getSquare(r, c).getPiece().getSymbol());
					} else {
						squares[r][c].setText(b.getSquare(r, c).getPiece().getSymbol());
						squares[r][c].setForeground(Color.BLUE);
					}
				} else {
					squares[r][c].setText("");
					squares[r][c].setForeground(Color.BLACK);
				}
			}
		}
		frame.revalidate();
		frame.repaint();
	}
}