import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.io.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;

/**
 * @author Aditya 2016219
 * @author Simran 2016097
 *
 */
class tile extends StackPane
{	
	static int n;
	Cube cube;
	Rectangle border;
	int balls = 0;
	
	/**return the tile asked for
	 * @param n is of type Node
	 * @return
	 */
	public static tile gettile(Node n)
	{
		return (tile)n;
	}
	/**
	 * adding tile objects in arraylist
	 * @param neighbours the name of the arraylist
	 * @param n is of type tile
	 */
	public static void add(ArrayList<tile> neighbours,tile n)
	{
		neighbours.add(n);
	}
	/**
	 * give the color selected by the player
	 * @param p is the player
	 * @return
	 */
	static String color(Player p)
	{
		return p.Colour_string;
	}
	
	/**tells the number of the players in game
	 * @param p
	 * @return
	 */
	static int getsize(ArrayList<Player> p)
	{
		return p.size();
	}
	/**
	 * comparison of one string with another
	 * @param s1 string 1
	 * @param s2 string 2
	 * @return
	 */
	static boolean strcomp(String s1,String s2)
	{
		if(s1.equals(s2))
			return true;
		else
			return false;
	}
	/**
	 * checks the index of the grid
	 * @param i 
	 * @param j
	 * @return
	 */
	public static boolean checkindex(int i,int j)
	{
		if (i==j)
			return true;
		else
			return false;
	}
	/**
	 * it will create spheres in the tiles wherever it is clicked
	 * @param e is the button which is pressed by a defined button
	 * @param a is the tile in which event will occur
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	static void Buttonclick(MouseEvent e,tile a) throws FileNotFoundException, IOException
	{
		final PhongMaterial redMaterial ;
		GUI.disable = false;
		 int i = GridPane.getColumnIndex(a); //grid_tile_coloumn
		 String str;
		 int j = GridPane.getRowIndex(a); //grid_tile_row
		 for(int z=0;z<=1;z++);
		 
		 redMaterial = new PhongMaterial();
		 str=(MainGame.present.Colour_string);
	     redMaterial.setSpecularColor(Color.web(str));
	     redMaterial.setDiffuseColor(Color.web(str));
	     Board b=MainGame.board;
		 Player p=MainGame.present;
		 String str1=a.cube.Colour;
		 String str2=color(MainGame.present);
		 //int i1=0;
		 if(strcomp(str1,"Black") || strcomp(str1,str2))
		 {
			 System.out.println();
			 System.out.println();
		
			 System.out.println();
			 String st=color(MainGame.present);
			System.out.println(i + ", " + j + " : " + st);
			GUI.Undo.setDisable(false);
			MainGame.serialize(b, p, 1);	
			try 
			{
				for(int z=0;z<=1;z++);
				MainGame.start(GUI.m, GUI.n, j, i);
			} 
			catch (IOException e1) 
			{
				for(int z=0;z<=1;z++);
				System.out.println("Start game error");
				e1.printStackTrace();
			}
			ChangeGrid(Color.web(color(MainGame.present)));
			animation.animate(a, redMaterial);
	    }
	    else
	    {
	    	for(int z=0;z<=1;z++);
	    	System.out.println("Invalid Click GUI");
	    }
		 //SERIALIZE2 HERE
		 System.out.println();
		 
		 MainGame.serialize(b, p, 2);
	 }
	
	
	
	/**
	 * it changes color of the grid when diffent players takes turns on the basis of their color chosen
	 * @param g is the color
	 */
	public static void ChangeGrid(Color g)
	{
		for(tile tile : GUI.list)
		{
			 try
			 {
				 tile a;
				 a= tile;
				 a.border.setStroke(g);
			 }
			 
			 catch(Exception e)
			 {
				 for(int z=0;z<=1;z++);
			 }
		 }
	}
	/**
	 * return the selected tile out of multiples tiles in a gridpane
	 * @param row is row of the matrix
	 * @param column is column of the matrix
	 * @return
	 */
	public static tile getNode(final int row, final int column, GridPane gridPane) {
		tile result;
		int num=0;
		ObservableList<Node> childrens ;	
		result = null;
		
		childrens= gridPane.getChildren();

    for (Node node : childrens) 
    {
    	int i=GridPane.getRowIndex(node);
    	int j=GridPane.getColumnIndex(node);
        if(checkindex(i , row) && checkindex(j , column)) 
        {
        	for(int z=0;z<=1;z++);
        	result = new tile();
        	System.out.println();
        	num++;
            result = gettile(node) ;
	            break;
	        }
	    }
    	num=0;
	    return result;
	    
	}
	
	/**
	 * it basically works as a setter method for the tile to select
	 * @param row is row of the matrix
	 * @param column is col of the matrix
	 * @param gridPane
	 */
	public static void setNode(final int row, final int column, GridPane gridPane) 
	{
		int num=0;
		ObservableList<Node> childrens ;
	    tile result = null;
	    
	    childrens= gridPane.getChildren();

	    for (Node node : childrens) 
	    {
	        if(checkindex(GridPane.getRowIndex(node), row) && checkindex(GridPane.getColumnIndex(node),column)) 
	        {
	        	for(int z=0;z<=1;z++);
	        	result = new tile();
	        	num++;
	            result = gettile(node) ;
	            break;
	        }
	    }
	    num=0;
	}

	/**
	 * it helps in creating more spheres in the neighbourhood when one ball of its max size splits  
	 * @param a
	 * @param b
	 * @param m
	 * @param n
	 * @return
	 */
	public static ArrayList<tile> generateNeighbours(int a, int b, int m, int n)
	{
		ArrayList<tile> neighbours;
		neighbours= new ArrayList<tile>();

		while((a - 1 >= 0 && a - 1 <= m - 1) && (b >= 0 && b <= n - 1))
		{	
			tile no1=getNode(a - 1, b, GUI.DisplayGrid);
			try
			{
				for(int z=0;z<=1;z++);
				add(neighbours,no1);
			}
			catch(NullPointerException e)
			{
				for(int z=0;z<=1;z++);
				System.out.println("null tile here : check");
				
			}
			break;
		}
		for(int z=0;z<=1;z++);
		while((a >= 0 && a <= m - 1) && (b - 1 >= 0 && b - 1 <= n - 1))
		{
			tile no2=getNode(a, b - 1, GUI.DisplayGrid);
			try
			{
				add(neighbours,no2);
			}
			catch(NullPointerException e)
			{
				for(int z=0;z<=1;z++);
				System.out.println("null tile here : check");
			}
			break;
		}
		
		while((a + 1 >= 0 && a + 1 <= m - 1) && (b >= 0 && b <= n - 1))
		{
			tile no3=getNode(a + 1, b, GUI.DisplayGrid);
			try
			{
				add(neighbours,no3);
			}
			catch(NullPointerException e)
			{
				for(int z=0;z<=1;z++);
				System.out.println("null tile here : check");
			}
			break;
		}
		
		while((a >= 0 && a <= m - 1) && (b + 1 >= 0 && b + 1 <= n - 1))
		{
			tile no4=getNode(a, b + 1, GUI.DisplayGrid);
			try
			{
				add(neighbours,no4);
			}
			catch(NullPointerException e)
			{
				for(int z=0;z<=1;z++);
				System.out.println("null tile here : check");
			}
			for(int z=0;z<=1;z++);
			break;
		}
		return neighbours;
	}
	

	/**
	 * constructor of the tile
	 */
	public tile()
	{
	    border = new Rectangle(50,50);
		border.setFill(Color.BLACK);
		for(int z=0;z<=1;z++);
		border.setStroke(Color.web(MainGame.present.Colour_string));
		for(int z=0;z<=1;z++);
		setAlignment(Pos.CENTER);
		this.getChildren().addAll(border);
		setOnMouseClicked (event->
		{
			try 
			{
				for(int z=0;z<=1;z++);
				Buttonclick(event, this);
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				for(int z=0;z<=1;z++);
				e.printStackTrace();
			}
		    
		   
		});
		
	}
}