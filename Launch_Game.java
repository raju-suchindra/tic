import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char [][] board;
	public TicTacToe()
	{
		board=new char[3][3];
		initboard();
	}

	void initboard()
	{
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}

	static void dispboard()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	static void placemark(int row, int col,char mark)
	{
		if(col>=0 && col<=2 && row>=0 && row<=2)
		{
			board[row][col]=mark;
		}
		else {
			System.out.println("Invalid input");
		}
	}

	static boolean colwincheck()
	{
		for(int j=0;j<3;j++)
		{
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
		}
		return false;
	}

	static boolean rowwincheck()
	{
		for(int j=0;j<3;j++)
		{
			if(board[j][0]!=' ' && board[j][0]==board[j][1] && board[j][1]==board[j][2])
			{
				return true;
			}
		}
		return false;
	}

	static boolean diagcheck()
	{
		if((board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) || 
				(board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]))
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	static boolean checkDraw()
	{
		for(int  i = 0 ; i<=2 ;i++) {
			for(int j = 0 ; j<=2 ;j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player
{
	String name;
	char mark;
	abstract void makeMove();
	
	boolean isValidMove(int row , int col)
	{
		if(row>=0 && row <=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col] ==' ') {
				return true;
			}
		}
		return false;
	}
	
}
class AIPlayer extends Player{

	AIPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row , col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);		}
		while(!isValidMove(row,col));
		
		TicTacToe.placemark(row, col, mark);
	}

}


class HumanPlayer extends Player{

	HumanPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row , col;
		do {
			System.out.println("Enter the row and col");
			row = scan.nextInt();
			col = scan.nextInt();
		}
		while(!isValidMove(row,col));
		
		TicTacToe.placemark(row, col, mark);
	}

}

public class LaunchGame {

	public static void main(String[] args) {
		TicTacToe t=new TicTacToe();
		
		HumanPlayer p1 = new HumanPlayer("Bavaji" , 'X');
		AIPlayer p2 = new AIPlayer("Tai " , 'O');
		
		
		Player cp;
		cp = p1;
		while(true) {

			System.out.println(cp.name +" turn");
			cp.makeMove();
			TicTacToe.dispboard();
			if(TicTacToe.colwincheck() || TicTacToe.diagcheck() || TicTacToe.rowwincheck()) {
				System.out.println(cp.name + " has won the game");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is a Draw");
				break;
			}
			else {
				if(cp == p1) {
					cp = p2;
				}
				else {
					cp = p1;
				}
			}
		}
		
	}

}
