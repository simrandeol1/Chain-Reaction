import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.animation.*;

import javafx.scene.Group;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import javafx.util.Duration;
 /**
 * @author Aditya 2016219
 * @author Simran 2016097
 */
class animation extends Application
{
	static Group group;
	static Sphere cur;
	static boolean stop = false;
	static ArrayList<Sphere> four_spheres  =new ArrayList<Sphere>();
	public static void splitting(tile Tile,int notileball,int no,PhongMaterial redMaterial)
	/**function used for splitting of spheres*/
	{
		

		
		Group a ;
		a= new Group();
		notileball = notileball - no;
		int i=0;
		while(i < notileball)
		{
			spherecre(redMaterial, a);
			i++;
		}
		RotateTransition rt;
		Duration durinmil;
		durinmil=Duration.millis(2000);
		rt = new RotateTransition(durinmil, a);
		setTranslate(a, a.getChildren().size());
		setTranslate1(a, a.getChildren().size());
		setTranslate2(a, a.getChildren().size());
		rotate(rt);
		Tile.getChildren().add(a);
	}
	public static void splitting1(Group a,int temp,PhongMaterial redMaterial)
	/**function used for splitting of spheres*/
	{
		
		
		
		int i=0;
		while(i < temp )
		{
			spherecre(redMaterial,a);
			i++;
		}
		RotateTransition rt;
		Duration durinmil=Duration.millis(2000);
		rt = new RotateTransition(durinmil, a);
		setTranslate(a, a.getChildren().size());
		setTranslate1(a, a.getChildren().size());
		setTranslate2(a, a.getChildren().size());
		rotate(rt);
	}
	public static void spherecre(PhongMaterial redMaterial,Group a)
	/**creates a sphere */
	
	{
		
	
		
		Sphere o = new Sphere();
		o.setRadius(6.0);
		o.setMaterial(redMaterial);
		a.getChildren().add(o);
	}
	public static void anime(PhongMaterial redMaterial,ArrayList<tile> neighbouringCells,tile Tile,int i,ParallelTransition mainTransition)
	/**function to show the animation of splitting of spheres*/

	{
		
		
		tile neighbour;
		neighbour= neighbouringCells.get(i);
		int gprowindexnei=GridPane.getRowIndex(neighbour);
		int gpcolindexnei=GridPane.getColumnIndex(neighbour);
		int gprowindextile=GridPane.getRowIndex(Tile);
		int gpcolindextile=GridPane.getColumnIndex(Tile);
		Sphere cur = new Sphere();
		TranslateTransition move;
		cur.setRadius(6.0);
		cur.setMaterial(redMaterial);
        move= new TranslateTransition();
        move.setDuration(Duration.seconds(1));
        int moveX ;
        moveX= gprowindexnei- gprowindextile;
        move.setNode(cur);
        int moveY;
        moveY= gpcolindexnei-gpcolindextile ;
        move.setToX(moveY*50);
        neighbour.toBack();
        move.setToY(moveX*50);
        
        Tile.getChildren().add(cur);
        mainTransition.getChildren().add(move);
	}
	
	public static void rotate(RotateTransition trans)
	/**function to rotate the spheres*/
	{	
		

		
		int cycle=RotateTransition.INDEFINITE;
		trans.setCycleCount(cycle);
		Interpolator polar=Interpolator.LINEAR;
		trans.setInterpolator(polar);
		trans.setFromAngle(0);
		trans.setByAngle(360);
		trans.play();
	}
	
	public static ArrayList<Sphere> createSphereList(PhongMaterial Material)
	/**creates a group of spheres for them to rotate together*/
	{	
		
		
		int i=0;
		while (i < 4)
		{
			Sphere sphere = new Sphere();
			sphere.setRadius(6.0);
			sphere.setMaterial(Material);
			four_spheres.add(sphere);
			i++;
		}
		return four_spheres;
	}
	
	
	public static void split(tile Tile, PhongMaterial redMaterial) 
	/**split function further  calls splitting function  to split the balls to neighbour tiles*/
	{

		
		int tilegetsi=Tile.getChildren().size();
		try
		{
			Tile.getChildren().remove(1, tilegetsi);
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
		}
		ArrayList<tile> neighbouringCells;
		neighbouringCells= tile.generateNeighbours(GridPane.getRowIndex(Tile), GridPane.getColumnIndex(Tile), GUI.m, GUI.n);
		if (Tile.cube.CriticalMass == 2)
		{
			if(Tile.balls <= 2)
			{
				Tile.balls = 0;
			}
			else
			{
				splitting(Tile,Tile.balls,2,redMaterial);
			}
		}
		
		
		
		else if (Tile.cube.CriticalMass == 3)
		{
			if(Tile.balls > 3)
			{
				splitting(Tile,Tile.balls,3,redMaterial);
			}
			
			else
			{
				Tile.balls = 0;
			}
		}
		
		
		else if(Tile.cube.CriticalMass == 4)
		{
			if(Tile.balls > 4)
			{
				splitting(Tile,Tile.balls,4,redMaterial);
			}
			else
			{
				Tile.balls = 0;
			}
		}
		else
		{
			System.out.println("Code Gdbd hai");
		}
		int i=0;
		while(i < Tile.cube.CriticalMass)
		{
		//System.out.println(MainGame.present.getColour() + " initial colour");
			animate(neighbouringCells.get(i), redMaterial);
			i++;
		}
	}
	
	
	public static void setTranslate(Group a, int size)
	 /** Tells the balls where to move*/
	{
		
		
		if (size == 2)
		{
			a.getChildren().get(1).setTranslateX(5);
			a.getChildren().get(1).setTranslateY(5);
		}
	}
	public static void setTranslate1(Group a, int size)
	 /** Tells the balls where to move*/
	{
		if (size == 3)
		{
			a.getChildren().get(2).setTranslateY(-4);
			a.getChildren().get(0).setTranslateX(0);
			a.getChildren().get(0).setTranslateY(4);
		}
	}
	public static void setTranslate2(Group a, int size)
	 /** Tells the balls where to move*/

	{
		if (size == 3)
		{
			a.getChildren().get(1).setTranslateX(+4);
			a.getChildren().get(1).setTranslateY(-4);
			a.getChildren().get(2).setTranslateX(-4);
		}
	}
	
	
	public static void animate(tile Tile, PhongMaterial redMaterial)
	 /** Checks if the CriticalMAss is greater than the balls present in the tile*/

	{
		
		
		Tile.balls=Tile.balls+1;
		try
		{
			Tile.getChildren().remove(1);
			for(int i=0;i<1;i++){};
			int temp = Tile.balls % Tile.cube.CriticalMass;
			group = new Group();
			splitting1(group,temp,redMaterial);
		}
		catch(Exception e)
		{
			RotateTransition rt;
			group = new Group();
			int i=0;
			while(i < Tile.balls)
			{
				spherecre(redMaterial,group);
				i++;
			}
			rt = new RotateTransition(Duration.millis(3000), group);
			

		}
		Tile.getChildren().add(group);
		System.out.println();
		if(Tile.cube.CriticalMass < Tile.balls)
		{
			ParallelTransition mainTransition;
			ArrayList<tile> neighbouringCells;
			mainTransition= new ParallelTransition();
			neighbouringCells= tile.generateNeighbours(GridPane.getRowIndex(Tile), GridPane.getColumnIndex(Tile), GUI.m, GUI.n);
			
			while (Tile.balls == Tile.cube.CriticalMass)
			{
				Tile.getChildren().remove(1, Tile.getChildren().size());
				break;
			}
			int i=0;
			while(i < Tile.cube.CriticalMass)
            {	
				anime(redMaterial, neighbouringCells,Tile,i,mainTransition);
                if(Tile.cube.CriticalMass == 2)
                {
                	System.out.println(Tile.balls);
                }
                i++;
            }
			mainTransition.play();
            mainTransition.setOnFinished(e->
            {
            	stop = true;
            });
		
		}
		if (Tile.cube.CriticalMass == Tile.balls){
			
			ParallelTransition mainTransition;
			
			ArrayList<tile> neighbouringCells;
			neighbouringCells= tile.generateNeighbours(GridPane.getRowIndex(Tile), GridPane.getColumnIndex(Tile), GUI.m, GUI.n);
			
			mainTransition = new ParallelTransition();
			
			while(Tile.balls == Tile.cube.CriticalMass)
			{
				Tile.getChildren().remove(1, Tile.getChildren().size());
				break;
			}
			int i=0;
			while (i < Tile.cube.CriticalMass)
            {				    
				anime(redMaterial, neighbouringCells,Tile,i,mainTransition);
                
                if(Tile.cube.CriticalMass == 2)
                {
                	System.out.println(Tile.balls);
                }
                i++;
            }
			int tilesi;
			mainTransition.play();
			tilesi=Tile.getChildren().size();
			mainTransition.setOnFinished (e ->{
            	Tile.getChildren().remove(1, tilesi);;
            	split(Tile, redMaterial);
            	stop = true;
            }
            );
		}	
	}
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// TODO Auto-generated method stub
	}
}
 class Cube extends GridPane implements Serializable
 /** cube class represents a single box of the grid*/

 {
 	public static final long serialVersionUID = 1L;
 	
 	public int X;
 	public int Y;
 	public void X1(int n)
		/** assigns X with a value n passed to this function as an argument */

 	{
 		this.X=n;
 	}
 	public void Y1(int n)
		/** assigns Y with a value n passed to this function as an argument */

 	{
 		this.Y=n;
 	}
 	public int CriticalMass;
 	public int ActualMass;
 	
 	public String Colour;
 	public void cube1(int n)
		/** assigns CriticalMass with a value n passed to this function as an argument */

 	{
 		this.CriticalMass=n;
 	}
 	
 	public Cube(String Type)
 	{
 		/** Constructor of Cube */
 		for(int z=0;z<=1;z++);
 		this.ActualMass = 0;
 		this.Colour = "Black";
 		 X1(-1);
 		 Y1(-1);
 	
 		 if(Type=="Edge")
 			 cube1(3);
 		 else if(Type=="Corner")
 			 cube1(2);
 		 else
 			cube1(4);
 		
 	}
 	
 }
class Player implements Serializable
/**
Class of every Player 
*/
{
	
	public int num;
	public static final long serialVersionUID = 1L;
	public int chance = 0;
	public int num1;
	public String Colour_string;
	public int Colour_string2;
	public int NumberOfBalls;
	
	
	public Player(Color Color, String Colour)
	/** Constructor of Player */
	{ 
		
		for(int z=0;z<=1;z++);
		this.chance = 0;
		this.Colour_string = Colour;
		this.NumberOfBalls = 0;
	}
}

class Board implements Serializable
/**
Class to represent whole Grid 
*/
{
	
	
	private static final long serialVersionUID = 1L;
	private Cube[][] matrix;
	public Board(int m, int n)
	/** Constructor of Board */
	{
		
		matrix = new Cube[m][n];
		int i=0;
		while(i < m)
		{
			int j=0;
			while(j < n)
			{
				if((i==0&&j==0 )||(i==0&&j==n-1)){
						for(int z=0;z<=1;z++);
						Cube cube = new Cube("Corner");
						cube.X = i;
						for(int z=0;z<=1;z++);
						cube.Y = j;
						matrix[i][j] = cube;
					}
				else if(i==0)
					{
						for(int z=0;z<=1;z++);
						Cube cube = new Cube("Edge");
						cube.X = i;
						for(int z=0;z<=1;z++);
						cube.Y = j;
						matrix[i][j] = cube;
					}
				else if((i==m-1&&j==0 )||(i==m-1&&j==n-1))
				{
					for(int z=0;z<=1;z++);
					Cube cube = new Cube("Corner");
					for(int z=0;z<=1;z++);
					cube.X = i;
					cube.Y = j;
					matrix[i][j] = cube;
					}
				else if(i==m-1)
				{
					for(int z=0;z<=1;z++);
					Cube cube = new Cube("Edge");
					cube.X = i;
					for(int z=0;z<=1;z++);
					cube.Y = j;
					matrix[i][j] = cube;
				}
				else if(j==0||j==n-1)
				{
					for(int z=0;z<=1;z++);
					Cube cube = new Cube("Edge");
					cube.X = i;
					for(int z=0;z<=1;z++);
					cube.Y = j;
					matrix[i][j] = cube;
				}
				else
				{
					for(int z=0;z<=1;z++);
					Cube cube = new Cube("Normal");
					cube.X = i;
					for(int z=0;z<=1;z++);
					cube.Y = j;
					matrix[i][j] = cube;
				}
			j++;	
			}
			i++;
		}
	}
	
	public float BoardLength () 
	/** Returns the length of matrix */
	{
		
		return (int)this.matrix.length;
	}
	
	public float BoardWidth () 
	/** Returns the width of matrix */
	{
		
		return (int)this.matrix[1].length;
	}
	
	public void setMatrix(int a, int b, int mass)
	/**sets actual mass of a cube at coordinates a and b*/
	{
		
		this.matrix[a][b].ActualMass=mass;
		for(int z=0;z<=1;z++);
	}
	
	public Cube getMatrix(int a, int b)
	/**returns a particular cube at a and b coordinates from the matrix*/
	{
		return this.matrix[a][b];
	}
}
public class MainGame extends Application
/** Main class that initiates the play function and starts the game*/

{
	static Board board;
	static int player_number = 1;
	static Player present;
	static Player next;
	static int nsp;
	static ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<String> colours = new ArrayList<String>();
	static ArrayList<Color> colours_COLOR = new ArrayList<Color>();
	static ArrayList<String> add1=new ArrayList<String>();
	
	
	
	public static final void PrintBoard(Board board)
	 /** Function to Print grid*/

	{
		add1.add("colour1");
		add1.add("colour2");
		add1.add("colour3");
		add1.add("colour4");
		add1.add("colour5");
		add1.add("colour6");
		add1.add("colour7");
		add1.add("colour8");
		int i=0,j=0;
		int length=(int)board.BoardLength();
		int width=(int)board.BoardWidth();
		while(i< length)
		{
			while(j<width)
			{
				String a ;
				a= board.getMatrix(i, j).Colour.substring(0, 1);
				int b;
				b= board.getMatrix(i, j).ActualMass;
				
				if (b == 0 && a.equals("B") ) {
					b = 1;
				}
				if (b == 0) {
					a = "B";
					b = 1;
				}
				String str;
				str=String.join("", Collections.nCopies(b, a));
				System.out.print( str+ " ");
				j++;
			}
			
			System.out.println("");
			i++;
		}
		
	}
	public static void addingcolorlist(ArrayList<String> colours,String value)
	 /** Adds colors to Color ArrayList*/

	{
		
		colours.add(value);
		colours_COLOR.add(Color.web(value));
	}
	
	public static void play(int a, int b, int m, int n, Board board, Player present)
	 /**Function to Start the game*/

	{
		//checking if cell (a,b) lies in the matrix
		for(int i=0;i<5;i++){};
		if ((a >= 0 && a <= m - 1) && (b >= 0 && b <= n - 1))
		{
			// increase the actual mass of cube at (a,b) by 1

			int change1;
			change1=board.getMatrix(a, b).ActualMass ;
			board.setMatrix(a, b, (change1+ 1));
			
			String ExistingColor;
			ExistingColor = board.getMatrix(a, b).Colour;
			
			Player ExistingColorPlayer;
			
			if(ExistingColor!="Black")
			{
				ExistingColorPlayer = GetPlayerByColor(ExistingColor);
				int change2;
				change2=ExistingColorPlayer.NumberOfBalls;
				
				ExistingColorPlayer.NumberOfBalls=(change2)+1;
			}
			else
			{
				ExistingColorPlayer = present;
				int change3;
				change3=present.NumberOfBalls;
				
				present.NumberOfBalls=(change3)+1;
			}
			
			String change4;
			change4=present.Colour_string;
			board.getMatrix(a, b).Colour=change4;
			
			int b1;
			b1=ExistingColorPlayer.NumberOfBalls;
			int b2;
			b2=board.getMatrix(a, b).ActualMass;
			
			ExistingColorPlayer.NumberOfBalls=(b1)-(b2);
			
			int b11;
			b11=present.NumberOfBalls;
			int b12;
			b12=board.getMatrix(a, b).ActualMass;

			present.NumberOfBalls=(b11)+(b12);
			
			int b21;
			b21=board.getMatrix(a, b).ActualMass;
			int b22;
			b22=board.getMatrix(a, b).CriticalMass;
			
			if(b21 >= b22)
			{
				// decrease actual by critical mass
		
				int b31;
				b31=board.getMatrix(a, b).ActualMass;
				int b32;
				b32=board.getMatrix(a, b).CriticalMass;
				
				board.setMatrix(a, b, ( b31-b32 ));
				
				// turning back to black here.
				board.getMatrix(a, b).Colour="Black";
//				Decrease the number of balls of present player by the critical mass of the block
				int b41;
				b41=present.NumberOfBalls;
				int b42;
				b42=board.getMatrix(a, b).CriticalMass;
				
				present.NumberOfBalls=(b41)+(b42);
				play(a + 1, b, m, n, board, present);
				for(int i=0;i<5;i++){};
				play(a - 1, b, m, n, board, present);
				play(a, b + 1, m, n, board, present);
				for(int i=0;i<5;i++){};
				play(a, b - 1, m, n, board, present);
			}
		}
	}
	
	public static Player GetPlayerByColor (String Color) 
	 /** Function to access a player by its color*/

	{ 
		int i=0;
		while(i<players.size())
		{
			String check;
			check=players.get(i).Colour_string;
			if (check==Color) 
			{
				Player p;
				p=players.get(i);
				return p;
			}i++;
		}return null;
	}
	public static ArrayList<String> makeColorList()
	 /** Function used to Create an ArrayList of color*/

	{
		
		String value;
		value = GUI.colour1.getValue();
		if(!value.equals("--"))
		{
			addingcolorlist(colours,value);
			value = GUI.colour2.getValue();
			if(!value.equals("--"))
			{
				addingcolorlist(colours,value);
				value = GUI.colour3.getValue();
				if(!value.equals("--"))
				{
					addingcolorlist(colours,value);
					value = GUI.colour4.getValue();
					if(!value.equals("--"))
					{
						addingcolorlist(colours,value);
						value = GUI.colour5.getValue();
						if(!value.equals("--"))
						{
							addingcolorlist(colours,value);
							value = GUI.colour6.getValue();
							if(!value.equals("--"))
							{
								addingcolorlist(colours,value);
								value = GUI.colour7.getValue();
								if(!value.equals("--"))
								{
									addingcolorlist(colours,value);
									value = GUI.colour8.getValue();
									if(!value.equals("--"))
									{
										addingcolorlist(colours,value);
										return colours;
									}
									else
									{
										return colours;
									}
								}
								else
								{
									for(int i=0;i<5;i++){
										
									}
									return colours;
								}
								
							}
							else
							{
								for(int i=0;i<5;i++){
									
								}
								return colours;
							}
						}
						else
						{
							for(int i=0;i<5;i++){
								
							}
							return colours;
						}
					}
					else
					{
						return colours;
					}
				}
				else
				{
					for(int i=0;i<5;i++){
						
					}
					return colours;
				}
			}
			else
			{
				return colours;
			}
		}
		else
		{
			System.out.println("Please select player colours from settings menu");
			return colours;
		}
	}
	public static void serialize(Board board, Player player, int N) throws	IOException	
	 /** Function to Serialize the Grid */

	
	{
		if (N == 1) {
		FileOutputStream inp;
		ObjectOutputStream out;
		FileOutputStream inp1;
		ObjectOutputStream out1;
		
		inp= new FileOutputStream("board.ser");
		
		inp1= new FileOutputStream("player.ser");
		out1= new ObjectOutputStream(inp1);
		out= new ObjectOutputStream(inp);
		nsp=0;
		try
		{
			nsp++;
			out.writeObject(board);
		}
		finally
		{
			out.close();
		}
		
		try
		{
			nsp++;
			out1.writeObject(player);
		}
		finally
		{
			out1.close();
		}
		}
		else if (N == 2) {
				FileOutputStream inp;
				ObjectOutputStream out;
				FileOutputStream inp1;
				ObjectOutputStream out1;
				
				inp= new FileOutputStream("board2.ser");
				out= new ObjectOutputStream(inp);
				inp1= new FileOutputStream("player2.ser");
				out1= new ObjectOutputStream(inp1);
				nsp=0;
				try
				{
					nsp++;
					out.writeObject(board);
				}
				finally
				{
					out.close();
				}
				
				try
				{
					nsp++;
					out1.writeObject(player);
				}
				finally
				{
					out1.close();
				}
			
		}
		else
		{
			ObjectOutputStream out;
			FileOutputStream inp;
			FileOutputStream inp1;
			ObjectOutputStream out1;
			FileOutputStream inp2;
			ObjectOutputStream out2 ;
			
			inp= new FileOutputStream("resume_board.ser");
			out= new ObjectOutputStream(inp);
			inp1= new FileOutputStream("resume_player.ser");
			out1= new ObjectOutputStream(inp1);
			inp2= new FileOutputStream("resume_playerList.ser");
			out2= new ObjectOutputStream(inp2);
			nsp=0;
			try
			{
				nsp++;
				out.writeObject(board);
			}
			finally
			{
				out.close();
			}
			
			try
			{
				nsp++;
				out1.writeObject(player);
			}
			finally
			{
				out1.close();
			}
			
			try
			{
				nsp++;
				out2.writeObject(MainGame.players);
			}
			finally
			{
				out2.close();
			}
		}
	}
	
	
	public static void begin(int m, int n)
	 /** Function to represent the front page of game*/

	{
		if ((colours== null))
		{
			System.out.println("Please select player colours from settings menu");
			
		}
		else
		{
			int i=0;
			while(i < GUI.total_players)
			{
				Player p;
				p=new Player(colours_COLOR.get(i), colours.get(i));
				players.add(p);
				i++;
			}
		}
		board = new Board(m, n);
		for(int i=0;i<1;i++){};
		present = players.get(player_number - 1);
		next = players.get(player_number);
	}
	
	public static void start(int m, int n, int x, int y) throws IOException
	 /** Function to start the game after the selection of number of player and grid size*/

	{	for(int i=0;i<1;i++){};
		int colour_flag = 0;
		int i=0;
		
//		Serialization.serialize(board, players.get(0));
		ArrayList<Cube> neighbour;
		
		neighbour= new ArrayList<Cube>();
		if (players.size() > 1)
		{
			// input coordinates (x,y) for a particular move
			
			// take turn here, enter that block if the block is empty i.e. is black else if the block has same colour as the player taking chance.
			String check;
			check=board.getMatrix(x, y).Colour;
			if((check=="Black") || (check==present.Colour_string))
			{

				//SERIALIZE one and two here
				
				neighbour.add(board.getMatrix(x, y));
				play(x, y, m, n, board, present);
				present.chance=1;
				
				//SERIALIZE2 HERE
			
				// check if all players have taken 1st chance
				Player p;
				p=players.get(players.size() - 1);
				if(p.chance == 1)
				{
					colour_flag = 1;
				}
				//check for elimination here
				if (colour_flag == 1) // i.e. first chance has been taken by all
				{
					while(i<players.size())
					{
						int check2;
						check2=players.get(i).NumberOfBalls;
						if( check2== 0)
						{
							String s;
							s=players.get(i).Colour_string;
							System.out.println(s + " player to get eliminated");
							players.remove(i);
						}
						i++;
					}
				}
				
				player_number++;
				if (player_number > players.size())
				{
					player_number = player_number - players.size();
				}
				try
				{
					Player p3;
					p3=players.get(player_number - 1);
					present = p3;
				}
				catch(Exception e)
				{
					System.out.println("WINNER DECLARED is " + present.Colour_string);
				}
				try
				{
					Player p1;
					p1=players.get(player_number);
					next = p1;
				}
				catch (IndexOutOfBoundsException e)
				{
					Player p0=players.get(0);
					next = p0;
				}
			}
			else
			{
				System.out.println("Invalid click");
			}
		}
		else
		{
			String s0;
			s0=players.get(0).Colour_string;
			System.out.println(s0 + " is the winner");
		}
	}
	public static void main(String[] args) throws IOException
	 /** Main method*/

	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		GUI.launch(args);
		//Reader.init(System.in);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	 /** Basic function for the start of GUI components*/

	{
		GUI object = new GUI();
		object.start(primaryStage);
	}

}