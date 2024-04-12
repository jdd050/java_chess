package com.jdd050.chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
	
	private static final int ranks = 8;
	private static final int files = ranks;
	private static final Dimension squareSize = new Dimension(80, 80);
	private static final Color lightSquareColor = new Color(254, 238, 213);
	private static final Color darkSquareColor = new Color(205, 157, 111);
	private static JPanel chessBoard = new JPanel(new GridLayout(ranks, files));
	private static ArrayList<ArrayList<JPanel>> squares = new ArrayList<ArrayList<JPanel>>();
	private static ArrayList<JLabel> pieces = new ArrayList<JLabel>();
	
	public Main() {
		// populate ArrayList 
		do {
			squares.add(new ArrayList<JPanel>());
		} while (squares.size() < 8);
		
		// create ranks/files
		for (int file = 0; file < files; file++) {
			for (int rank = 0; rank < ranks; rank++) {
				// create a square and set its size/color
				JPanel square = new JPanel();
				square.setPreferredSize(squareSize);
				Color squareColor = (rank % 2 == file % 2) ? lightSquareColor : darkSquareColor;
				square.setBackground(squareColor);
				// add square to the chess board
				chessBoard.add(square);
				// add square to 2D ArrayList (mimicking a grid)
				squares.get(file).add(square);
			}
		}
		
		createGameGrid();
		addPieceTextures();
		// assign actionListeners to each piece
		for (JLabel piece : pieces) {
			piece.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}});
		}
		
	}
	
	/*
	 * methods that create the chess board
	 */
	
	// creates the grid with the chess board and labels (what the player can see)
	public void createGameGrid() {
		// define layout for main panel
		setLayout(new GridBagLayout());
		GridBagConstraints gbConstraints = new GridBagConstraints();
		
		// left-side rank labels
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		gbConstraints.gridheight = 1;
		gbConstraints.weightx = 0.0;
		gbConstraints.weighty = 0.0;
		gbConstraints.fill = GridBagConstraints.BOTH;
		gbConstraints.anchor = GridBagConstraints.WEST;
		gbConstraints.insets = new Insets(0, 10, 0, 10);
		add(createRankPanel(), gbConstraints);
		
		// right-size rank labels
		gbConstraints.gridx = 2;
		gbConstraints.anchor = GridBagConstraints.EAST;
		add(createRankPanel(), gbConstraints);
		
		// top file labels
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		gbConstraints.anchor = GridBagConstraints.SOUTH;
		gbConstraints.insets = new Insets(5, 0, 5, 0);
		add(createFilePanel(), gbConstraints);
		
		// bottom file labels
		gbConstraints.gridy = 2;
		gbConstraints.anchor = GridBagConstraints.NORTH;
		add(createFilePanel(), gbConstraints);
		
		// the chess board itself
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.anchor = GridBagConstraints.CENTER;
		gbConstraints.insets = new Insets(0, 0, 0 ,0);
		add(chessBoard, gbConstraints);
	}
	
	// creates a files panel (for labeling) and returns the panel
	private JPanel createFilePanel() {
		JPanel filePanel = new JPanel(new GridLayout(1,0));
		for (int i = 0; i < files; i++) {
			// set current letter by increasing its ASCII value
			char fileLetter = (char) ('A' + i);
			JLabel letter = new JLabel(String.valueOf(fileLetter), SwingConstants.CENTER);
			letter.setForeground(Color.white);
			filePanel.add(letter);
			filePanel.setBackground(Color.gray);
		}
		return filePanel;
	}
	
	// creates a ranks panel (for labeling) and returns the panel
	private JPanel createRankPanel() {
		JPanel rankPanel = new JPanel(new GridLayout(0,1));
		for (int i = 0; i < ranks; i++) {
			// count down rather than up
			int currentRow = ranks - i;
			JLabel number = new JLabel(String.valueOf(currentRow));
			number.setForeground(Color.white);
			rankPanel.add(number);
			rankPanel.setBackground(Color.gray);
		}
		return rankPanel;
	}
	
	/*
	 *  methods that add and manage chess pieces
	 */
	
	// adds the textures for each piece on the board and gives them names
	public void addPieceTextures() {
		// black pawns
		try {
			BufferedImage black_pawn = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_pawn.png"));
			for (int i = 0; i < ranks; i++) {
				JLabel blackPawn = new JLabel(new ImageIcon(black_pawn));
				
				blackPawn.setName("BLACK_PAWN_" + (i + 1));
				
				pieces.add(blackPawn);
				squares.get(1).get(i).add(blackPawn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// black rooks
		try {
			BufferedImage black_rook = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_rook.png"));
			JLabel blackRookL = new JLabel(new ImageIcon(black_rook));
			blackRookL.setName("BLACK_ROOK_LEFT");
			JLabel blackRookR = new JLabel(new ImageIcon(black_rook));
			blackRookR.setName("BLACK_ROOK_RIGHT");
			
			pieces.add(blackRookR);
			pieces.add(blackRookL);
			
			squares.get(0).get(0).add(blackRookL);
			squares.get(0).get(7).add(blackRookR);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// black knights
		try {
			BufferedImage black_knight = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_knight.png"));
			JLabel blackKnightL = new JLabel(new ImageIcon(black_knight));
			blackKnightL.setName("BLACK_KNIGHT_LEFT");
			JLabel blackKnightR = new JLabel(new ImageIcon(black_knight));
			blackKnightR.setName("BLACK_KNIGHT_RIGHT");
			
			pieces.add(blackKnightL);
			pieces.add(blackKnightL);
			
			squares.get(0).get(1).add(blackKnightL);
			squares.get(0).get(6).add(blackKnightR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// black bishops
		try {
			BufferedImage black_bishop = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_bishop.png"));
			JLabel blackBishopL = new JLabel(new ImageIcon(black_bishop));
			blackBishopL.setName("BLACK_BISHOP_LEFT");
			JLabel blackBishopR = new JLabel(new ImageIcon(black_bishop));
			blackBishopR.setName("BLACK_BISHOP_RIGHT");
			
			pieces.add(blackBishopL);
			pieces.add(blackBishopR);
			
			squares.get(0).get(2).add(blackBishopL);
			squares.get(0).get(5).add(blackBishopR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// black king
		try {
			BufferedImage black_king = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_king.png"));
			JLabel blackKing = new JLabel(new ImageIcon(black_king));
			blackKing.setName("BLACK_KING");
			
			pieces.add(blackKing);
			
			squares.get(0).get(3).add(blackKing);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// black queen
		try {
			BufferedImage black_queen = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\black_queen.png"));
			JLabel blackQueen = new JLabel(new ImageIcon(black_queen));
			blackQueen.setName("BLACK_QUEEN");
			
			pieces.add(blackQueen);
			
			squares.get(0).get(4).add(blackQueen);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// white pawns
		try {
			BufferedImage white_pawn = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_pawn.png"));
			for (int i = 0; i < ranks; i++) {
				JLabel whitePawn = new JLabel(new ImageIcon(white_pawn));
				whitePawn.setName("WHITE_PAWN_" + (i + 1));
				
				pieces.add(whitePawn);
				
				squares.get(6).get(i).add(whitePawn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// white rooks
		try {
			BufferedImage white_rook = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_rook.png"));
			JLabel whiteRookL = new JLabel(new ImageIcon(white_rook));
			whiteRookL.setName("WHITE_ROOK_LEFT");
			JLabel whiteRookR = new JLabel(new ImageIcon(white_rook));
			whiteRookR.setName("WHITE_ROOK_RIGHT");
			
			pieces.add(whiteRookL);
			pieces.add(whiteRookR);
			
			squares.get(7).get(0).add(whiteRookL);
			squares.get(7).get(7).add(whiteRookR);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// white knights
		try {
			BufferedImage white_knight = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_knight.png"));
			JLabel whiteKnightL = new JLabel(new ImageIcon(white_knight));
			whiteKnightL.setName("WHITE_KNIGHT_LEFT");
			JLabel whiteKnightR = new JLabel(new ImageIcon(white_knight));
			whiteKnightR.setName("WHITE_KNIGHT_RIGHT");
			
			pieces.add(whiteKnightL);
			pieces.add(whiteKnightR);
			
			squares.get(7).get(1).add(whiteKnightL);
			squares.get(7).get(6).add(whiteKnightR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// white bishops
		try {
			BufferedImage white_bishop = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_bishop.png"));
			JLabel whiteBishopL = new JLabel(new ImageIcon(white_bishop));
			whiteBishopL.setName("WHITE_BISHOP_LEFT");
			JLabel whiteBishopR = new JLabel(new ImageIcon(white_bishop));
			whiteBishopR.setName("WHITE_BISHOP_RIGHT");
			
			pieces.add(whiteBishopL);
			pieces.add(whiteBishopR);
			
			squares.get(7).get(2).add(whiteBishopL);
			squares.get(7).get(5).add(whiteBishopR);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// white king
		try {
			BufferedImage white_king = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_king.png"));
			JLabel whiteKing = new JLabel(new ImageIcon(white_king));
			whiteKing.setName("WHITE_KING");
			
			pieces.add(whiteKing);
			
			squares.get(7).get(3).add(whiteKing);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// white queen
		try {
			BufferedImage white_queen = ImageIO.read(new File("C:\\Users\\joeyd\\OneDrive\\Documents\\GitHub\\Java\\chess\\src\\com\\jdd050\\chess\\assets\\white_queen.png"));
			JLabel whiteQueen = new JLabel(new ImageIcon(white_queen));
			whiteQueen.setName("WHITE_QUEEN");
			
			pieces.add(whiteQueen);
			
			squares.get(7).get(4).add(whiteQueen);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * run methods
	 */
	
	// create the root window
	private static void createAndShowGui() {
		// calls
		Main mainPanel = new Main();
		
		// make and configure window
		mainPanel.setBackground(Color.gray);
		JFrame frame = new JFrame("Java Chess v1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	// start the app
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}
	
	/*
	 * move chess pieces
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
