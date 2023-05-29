package tests;

import model.*;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Tests {
	
	// Testing the basic Game constructor with an empty ClearCellGame.
	@Test
	public void testing01GameConstructor01() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		String answer = getBoardStr(g1);
		String expectedAnswer = "Board(Rows: 2, Columns: 2)\n";
		for (int rowNum = 0; rowNum < 2; rowNum++) { 
			expectedAnswer += "..\n";
		}
		assertEquals(answer, expectedAnswer);
	}
	
	// Testing get and set methods for maxRow and maxCol in Game.
	@Test
	public void testing02GetMaxRowAndCol01() {
		Game g1 = new ClearCellGame(2,2, new Random(), 1);
		int rowAnswer = g1.getMaxRows();
		int colAnswer = g1.getMaxCols();
		assertTrue(rowAnswer == colAnswer);
	}
	
	// Testing to see if a board cell is set
	@Test
	public void testing03SetBoardCell01() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		g1.setBoardCell(0, 1, BoardCell.RED);
		BoardCell answer = g1.getBoardCell(0, 1);
		BoardCell expectedAnswer = BoardCell.RED;
		assertEquals(expectedAnswer.getName(), answer.getName());
	}
	
	// Testing to see if isGameOver returns false
	@Test
	public void testing04IsGameOver01() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		boolean answer = g1.isGameOver();
		assertFalse(answer);
	}
	
	// Testing to see if isGameOver returns true
	// Also tests setRowWithColor
	@Test
	public void testing05IsGameOver02() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		g1.setRowWithColor(1, BoardCell.RED);
		boolean answer = g1.isGameOver();
		assertTrue(answer);
	}
	
	// Testing to see if the nextAnimationStep Works
	@Test
	public void testing06NextAnimationStep01() {
		Game g1 = new ClearCellGame(3, 2, new Random(1), 1);
		g1.nextAnimationStep();
		g1.nextAnimationStep();
		Game g2 = new ClearCellGame(3, 2, new Random(1), 1);
		g2.setBoardCell(0, 0, BoardCell.BLUE);
		g2.setBoardCell(0, 1, BoardCell.YELLOW);
		g2.setBoardCell(1, 0, BoardCell.RED);
		g2.setBoardCell(1, 1, BoardCell.YELLOW);
		String expectedAnswer = getBoardStr(g1);
		String answer = getBoardStr(g2);
		assertEquals(expectedAnswer, answer);
	}
	
	// Testing to see if an entire column is set to red
	@Test
	public void testing07SetColWithColor01() {
		Game g1 = new ClearCellGame(2 , 2, new Random(), 1);
		g1.setColWithColor(1, BoardCell.RED);
		Game g2 = new ClearCellGame(2, 2, new Random(), 1);
		g2.setBoardCell(0, 1, BoardCell.RED);
		g2.setBoardCell(1, 1, BoardCell.RED);
		String expectedAnswer = getBoardStr(g2);
		String answer = getBoardStr(g1);
		assertEquals(expectedAnswer, answer);
	}
	
	// Testing to see if an entire row is set to red
	@Test
	public void testing08SetRowWithColor() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		g1.setRowWithColor(1, BoardCell.RED);
		Game g2 = new ClearCellGame(2, 2, new Random(), 1);
		g2.setBoardCell(1, 0, BoardCell.RED);
		g2.setBoardCell(1, 1, BoardCell.RED);
		String expectedAnswer = getBoardStr(g2);
		String answer = getBoardStr(g1);
		assertEquals(expectedAnswer, answer);
	}

	// Testing to see if whole is set to red
	@Test
	public void testing09SetBoardWithColor() {
		Game g1 = new ClearCellGame(2, 2, new Random(), 1);
		g1.setBoardWithColor(BoardCell.RED);
		Game g2 = new ClearCellGame(2, 2, new Random(), 1);
		g2.setBoardCell(0, 0, BoardCell.RED);
		g2.setBoardCell(0, 1, BoardCell.RED);
		g2.setBoardCell(1, 0, BoardCell.RED);
		g2.setBoardCell(1, 1, BoardCell.RED);
		String expectedAnswer = getBoardStr(g2);
		String answer = getBoardStr(g1);
		assertEquals(expectedAnswer, answer);
	}
	
	/* Private Method Testing */
//	// Testing to see if all consecutive Red cells above target cell turn empty
//	@Test
//	public void testing010TurnUpCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnUpCellsEmpty(3, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 1, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 0, BoardCell.EMPTY);
//		g2.setBoardCell(1, 0, BoardCell.EMPTY);
//		g2.setBoardCell(2, 0, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells turn empty above target but stop when the 
//	// board's cell is no longer red.
//	@Test
//	public void testing011TurnUpCellsEmpty02() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		g1.setBoardCell(1, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnUpCellsEmpty(4, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 1, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.BLUE);
//		g2.setBoardCell(2, 0, BoardCell.EMPTY);
//		g2.setBoardCell(3, 0, BoardCell.EMPTY);
//		g2.setBoardCell(4, 0, BoardCell.RED);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing012TurnUpCellsEmpty03() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		g1.setBoardCell(1, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int expectedAnswer = 2;
//		int answer = g1.turnUpCellsEmpty(4, 0, answerBoard);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all consecutive Blue cells above target cell turn empty
//	@Test
//	public void testing013TurnDownCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnDownCellsEmpty(2, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 1, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(3, 0, BoardCell.EMPTY);
//		g2.setBoardCell(4, 0, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells turn empty below target but stop when the 
//	// board's cell is no longer red.
//	@Test
//	public void testing014TurnDownCellsEmpty02() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(3, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnDownCellsEmpty(0, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 1, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.BLUE);
//		g2.setBoardCell(0, 0, BoardCell.RED);
//		g2.setBoardCell(1, 0, BoardCell.EMPTY);
//		g2.setBoardCell(2, 0, BoardCell.EMPTY);
//		g2.setBoardCell(4, 0, BoardCell.RED);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing015TurnDownCellsEmpty03() {
//		Game g1 = new ClearCellGame(5, 1, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(3, 0, BoardCell.RED);
//		g1.setBoardCell(3, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.turnDownCellsEmpty(0, 0, answerBoard);
//		int expectedAnswer = 2;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all consecutive Red cells left of target cell turn
//	// empty
//	@Test
//	public void testing016TurnLeftCellsEmpty01() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnLeftCellsEmpty(0, 3, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(1, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 0, BoardCell.EMPTY);
//		g2.setBoardCell(0, 1, BoardCell.EMPTY);
//		g2.setBoardCell(0, 2, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells turn empty left of target but stop when the 
//	// board's cell is no longer red.
//	@Test
//	public void testing017TurnLeftCellsEmpty02() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnLeftCellsEmpty(0, 3, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(1, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 0, BoardCell.BLUE);
//		g2.setBoardCell(0, 1, BoardCell.EMPTY);
//		g2.setBoardCell(0, 2, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing018TurnLeftCellsEmpty03() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.turnLeftCellsEmpty(0, 3, answerBoard);
//		int expectedAnswer = 2;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all consecutive Red cells right of target cell turn
//	// empty
//	@Test
//	public void testing019TurnRightCellsEmpty01() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnRightCellsEmpty(0, 1, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(1, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 2, BoardCell.EMPTY);
//		g2.setBoardCell(0, 3, BoardCell.EMPTY);
//		g2.setBoardCell(0, 4, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if all cells turn empty right of target but stop when the
//	// board's cell is no longer red.
//	@Test
//	public void testing020TurnRightCellsEmpty02() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 4, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnRightCellsEmpty(0, 1, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(1, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 4, BoardCell.BLUE);
//		g2.setBoardCell(0, 2, BoardCell.EMPTY);
//		g2.setBoardCell(0, 3, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing021TurnRightCellsEmpty03() {
//		Game g1 = new ClearCellGame(1, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 4, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.turnRightCellsEmpty(0, 1, answerBoard);
//		int expectedAnswer = 2;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all consecutive Red cells top-left of target cell turn
//	// empty
//	@Test
//	public void testing022TurnTopLeftCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnTopLeftCellsEmpty(2, 2, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 0, BoardCell.EMPTY);
//		g2.setBoardCell(1, 1, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if all cells turn empty top-left of target but stop when 
//	// the board's cell is no longer red.
//	@Test
//	public void testing023TurnTopLeftCellsEmpty02() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnTopLeftCellsEmpty(4, 4, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 0, BoardCell.BLUE);
//		g2.setBoardCell(1, 1, BoardCell.EMPTY);
//		g2.setBoardCell(2, 2, BoardCell.EMPTY);
//		g2.setBoardCell(3, 3, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing024TurnTopLeftCellsEmpty03() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.turnTopLeftCellsEmpty(4, 4, answerBoard);
//		int expectedAnswer = 3;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all consecutive Red cells top-right of target cell turn
//	// empty
//	@Test
//	public void testing025TurnTopRightCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnTopRightCellsEmpty(2, 2, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 4, BoardCell.EMPTY);
//		g2.setBoardCell(1, 3, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if all cells turn empty top-right of target but stop when
//	// the board's cell is no longer red.
//	@Test
//	public void testing026TurnTopRightCellsEmpty02() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 4, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnTopRightCellsEmpty(4, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(0, 4, BoardCell.BLUE);
//		g2.setBoardCell(1, 3, BoardCell.EMPTY);
//		g2.setBoardCell(2, 2, BoardCell.EMPTY);
//		g2.setBoardCell(3, 1, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//
//	// Testing to see if score is updated accordingly
//	@Test
//	public void testing027TurnTopRightCellsEmpty03() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(0, 4, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.turnTopRightCellsEmpty(4, 0, answerBoard);
//		int expectedAnswer = 3;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells turn empty bottom-left of target but stop 
//	// when the board's cell is no longer red.
//	@Test
//	public void testing028TurnBottomLeftCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(4, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnBottomLeftCellsEmpty(0, 4, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(4, 0, BoardCell.BLUE);
//		g2.setBoardCell(3, 1, BoardCell.EMPTY);
//		g2.setBoardCell(2, 2, BoardCell.EMPTY);
//		g2.setBoardCell(1, 3, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells turn empty bottom-right of target but stop 
//	// when the board's cell is no longer red.
//	@Test
//	public void testing029TurnBottomRightCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(4, 4, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnBottomRightCellsEmpty(0, 0, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setBoardCell(4, 4, BoardCell.BLUE);
//		g2.setBoardCell(1, 1, BoardCell.EMPTY);
//		g2.setBoardCell(2, 2, BoardCell.EMPTY);
//		g2.setBoardCell(3, 3, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if all cells clear accordingly
//	@Test
//	public void testing030TurnCellsEmpty01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(1, 1, BoardCell.BLUE);
//		g1.setBoardCell(0, 2, BoardCell.BLUE);
//		g1.setBoardCell(2, 4, BoardCell.BLUE);
//		g1.setBoardCell(4, 4, BoardCell.BLUE);
//		g1.setBoardCell(3, 3, BoardCell.BLUE);
//		g1.setBoardCell(2, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnCellsEmpty(2, 2, answerBoard);
//		g1.setBoard(answerBoard);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardCell(0, 0, BoardCell.RED);
//		g2.setBoardCell(0, 1, BoardCell.RED);
//		g2.setBoardCell(0, 2, BoardCell.BLUE);
//		g2.setBoardCell(0, 3, BoardCell.RED);
//		g2.setBoardCell(1, 0, BoardCell.RED);
//		g2.setBoardCell(1, 1, BoardCell.BLUE);
//		g2.setBoardCell(1, 4, BoardCell.RED);
//		g2.setBoardCell(2, 0, BoardCell.BLUE);
//		g2.setBoardCell(2, 4, BoardCell.BLUE);
//		g2.setBoardCell(3, 0, BoardCell.RED);
//		g2.setBoardCell(3, 3, BoardCell.BLUE);
//		g2.setBoardCell(3, 4, BoardCell.RED);
//		g2.setBoardCell(4, 1, BoardCell.RED);
//		g2.setBoardCell(4, 3, BoardCell.RED);
//		g2.setBoardCell(4, 4, BoardCell.BLUE);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if score updates accordingly
//	@Test
//	public void testing031TurnCellsEmpty02() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setBoardCell(1, 1, BoardCell.BLUE);
//		g1.setBoardCell(0, 2, BoardCell.BLUE);
//		g1.setBoardCell(2, 4, BoardCell.BLUE);
//		g1.setBoardCell(4, 4, BoardCell.BLUE);
//		g1.setBoardCell(3, 3, BoardCell.BLUE);
//		g1.setBoardCell(2, 0, BoardCell.BLUE);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		g1.turnCellsEmpty(2, 2, answerBoard);
//		int answer = g1.getScore();
//		int expectedAnswer = 10;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if it returns correct empty row
//	@Test
//	public void testing032HasEmptyRow01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setRowWithColor(2, BoardCell.EMPTY);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int answer = g1.getEmptyRow(answerBoard);
//		int expectedAnswer = 2;
//		assertEquals(expectedAnswer, answer);
//	}
//	
//	// Testing to see if the board is shifted accordingly.
//	@Test
//	public void testing033RemoveEmptyRow01() {
//		Game g1 = new ClearCellGame(5, 5, new Random(), 1);
//		g1.setBoardWithColor(BoardCell.RED);
//		g1.setRowWithColor(2, BoardCell.EMPTY);
//		BoardCell[][] answerBoard = g1.getCopyBoard();
//		int emptyRowIdx = g1.getEmptyRow(answerBoard);
//		g1.removeEmptyRow(emptyRowIdx);
//		Game g2 = new ClearCellGame(5, 5, new Random(), 1);
//		g2.setBoardWithColor(BoardCell.RED);
//		g2.setRowWithColor(4, BoardCell.EMPTY);
//		String answer = getBoardStr(g1);
//		String expectedAnswer = getBoardStr(g2);
//		assertEquals(expectedAnswer, answer);
//	}
		
	/* Support methods */
	// Also tests getBoardCell
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}
		return answer;
	}
}
