package model;

import java.util.Random;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Department of Computer Science, UMCP
 * @author King Bass
 */

public abstract class Game {
	
	protected BoardCell[][] board;
	private int maxRows;
	private int maxCols;
	private int score;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.score = 0;
		board = new BoardCell[maxRows][maxCols];
		for (int rowNum = 0; rowNum < maxRows; rowNum++) {
			for (int colNum = 0; colNum < maxCols; colNum++) {
				board[rowNum][colNum] = BoardCell.EMPTY;
			}
		}
		score = 0;
	}

	public int getMaxRows() {
		return this.maxRows;
	}

	public int getMaxCols() {
		return this.maxCols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		this.board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return this.board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for (int colNum = 0; colNum < maxCols; colNum++) {
			board[rowIndex][colNum] = cell;
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int rowNum = 0; rowNum < this.maxRows; rowNum++) {
			for (int colNum = 0; colNum < this.maxCols; colNum++) {
				if (colNum == colIndex) {
					board[rowNum][colNum] = cell;
				}
			}
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for (int rowNum = 0; rowNum < this.maxRows; rowNum++) {
			for (int colNum = 0; colNum < this.maxCols; colNum++) {
				board[rowNum][colNum] = cell;
			}
		}
	}
	
	/**
	 * Creates a copy of the game's board to use, alter, and reassign without 
	 * a privacy leak.
	 * 
	 * @return copy of the parameters board.
	 */
	public BoardCell[][] getCopyBoard() {
		BoardCell[][] copy = new BoardCell[maxRows][maxCols];
		for (int rowNum = 0; rowNum < maxRows; rowNum++) {
			for (int colNum = 0; colNum < maxCols; colNum++) {
				copy[rowNum][colNum] = board[rowNum][colNum];
			}
		}
		return copy;
	}

	/**
	 * Determines if the game is over.
	 * 
	 * @return True if game is over.
	 */
	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
	
	/* For Testing */
	/*

	public abstract int turnUpCellsEmpty(int rowIdx, int colIdx, BoardCell[][] 
			board);
	
	public abstract int turnDownCellsEmpty(int rowIdx, int colIdx, BoardCell[][]
			board);
	
	public abstract int turnLeftCellsEmpty(int rowIdx, int colIdx, BoardCell[][]
			board);
	
	public abstract int turnRightCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board);
	
	public abstract int turnTopLeftCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board);
	
	public abstract int turnTopRightCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board);

	public abstract int turnBottomLeftCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board);
	
	public abstract int turnBottomRightCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board);
	
	public void setBoard(BoardCell[][] board) {
		this.board = board;
	}
	
	public abstract void turnCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board);

	public abstract int getEmptyRow(BoardCell[][] board);
	
	public abstract void removeEmptyRow(int emptyRowIdx);
	*/

}