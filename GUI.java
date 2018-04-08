import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import java.io.FileInputStream;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.Group;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.io.ObjectInputStream;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;

import javafx.scene.text.Text;

import javafx.event.ActionEvent;

/**
 * @author Aditya 2016219
 * @author Simran 2016097
 */
public class GUI extends Application 
{
	static int totpla;
    static public int total_players;
    static ArrayList<ComboBox<String>> undolist=new ArrayList<ComboBox<String>>();
    static String option;
    private static boolean flag = false;
    static int m;
    static int n;
    static boolean disable = true;
    int size;
    MenuItem menuItem1 = new MenuItem("Save and Exit");
    static Board board_deserialized;
    Button Start = new Button ("Start");
    Label notification = new Label ();
    Button Settings = new Button ("Settings");
    static Stage thestage;
    Button Back = new Button ("Back");
    static GridPane DisplayGrid;
    MenuItem menuItem2 = new MenuItem("Start New Game");
    static Stage NewStage;
    Scene sceneNew;
    MenuButton menuButton = new MenuButton("More options", null, menuItem1, menuItem2);
    static ArrayList<tile> list = new ArrayList<tile>();
    final static Button Undo = new Button ("Undo");
    static Player player_deserialized;
    
    static Board readin;
    static Player readin1;
    Scene scene;
    Scene display;

    
    static final ComboBox<String> colour1 = new ComboBox<String>();
    final Button done1 = new Button ("Done");
    static final ComboBox<String> colour2 = new ComboBox<String>();
    final Button done2 = new Button ("Done");
    static final ComboBox<String> colour3 = new ComboBox<String>();
    final Button done3 = new Button ("Done");
    static final ComboBox<String> colour4 = new ComboBox<String>();
    final Button done4 = new Button ("Done");
    static final ComboBox<String> colour5 = new ComboBox<String>();
    final Button done5 = new Button ("Done");
    static final ComboBox<String> colour6 = new ComboBox<String>();
    final Button done6 = new Button ("Done");
    static final ComboBox<String> colour7 = new ComboBox<String>();
    final Button done7 = new Button ("Done");
    static final ComboBox<String> colour8 = new ComboBox<String>();
    final Button done8 = new Button ("Done");
    final ComboBox<String> numberOfPlayers = new ComboBox<String>();
    final static Button ok = new Button ("OK");
    final Button Resume = new Button ("Resume");
    ComboBox<String> gridSize;
  
    Group group = new Group();
    
    ArrayList<String> colours = new ArrayList<String>(){
		{add("Red");add("Green");add("Blue");add("Yellow");add("Pink"); add("Cyan"); add("Orange");add("White");}
    };
    
    ArrayList<String> colours_final = new ArrayList<String>(){
    	{add("Red");add("Green");add("Blue");add("Yellow");add("Pink"); add("Cyan"); add("Orange");add("White");}
    };
    
    /**
     * it is used in deserializing
     * @param N is the condotions for ball
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void deserialize(int N) throws IOException, ClassNotFoundException	
	{
    	
		if (N == 1) {
		FileInputStream inp;
		ObjectInputStream in;
		inp= new FileInputStream("board.ser");
		in= new ObjectInputStream(inp);
		
		FileInputStream inp1;
		ObjectInputStream in1;
		inp1= new FileInputStream("player.ser");
		in1= new ObjectInputStream(inp1);
		readin=(Board) in.readObject();
		readin1=(Player) in1.readObject();
		try
		{
			board_deserialized = readin;
		}
		finally
		{
			in.close();
		}
		
		try
		{
			player_deserialized = readin1;
		}
		finally
		{
			in1.close();
		}
		}
		else {
				FileInputStream inp;
				ObjectInputStream in;
				FileInputStream inp1;
				ObjectInputStream in1;
				
				inp= new FileInputStream("board2.ser");
				in= new ObjectInputStream(inp);
				inp1= new FileInputStream("player2.ser");
				in1= new ObjectInputStream(inp1);
				
				try
				{
					board_deserialized = readin;
				}
				finally
				{
					in.close();
				}
				
				try
				{
					player_deserialized = readin1;
				}
				finally
				{
					in1.close();
				}
			
		}
	}
    /**used in disabling buttons
     * @param resume is the general button on which we have to use disbale option
     * @param a chooses true or false
     */
    public void setdisable(Button resume,Boolean a){
    	resume.setDisable(a);
    }
    /**this is used for disabling every combobox at once 
     * @param undolist a general arraylist containing combobox 
     * @param a chooses true or false
     */
    public void setdisable2(ArrayList<ComboBox<String>> undolist,Boolean a){
    	for(int i=1;i<8;i++){
    	undolist.get(i).setDisable(a);
    	}
    }
    /**adding palyer labels at once
     * @param gridNew is the scene in which label has to be placed
     */
    public void addingplayer(GridPane gridNew){
    	for(int i=1;i<=8;i++){
    		gridNew.add(new Label("Player "+i+": "),0, i);
    	}
    }
    /**adding color label at once
     * @param gridNew is the scene in which label has to be placed
     */
    public void addingcolor(GridPane gridNew){
    	for(int i=0;i<8;i++){
    		gridNew.add(undolist.get(i),1, i+1);
    	}
    }
    /**adding done button at once
     * @param gridNew scene in which button to be placed
     * @param done button to be added
     * @param i is the y position
     */
    public void addingdone(GridPane gridNew,Button done,int i){
    	gridNew.add(done,2, i);	
    }
    /**adjusting the size and value of combobox
     * @param colour is the general combobox
     * @param a is the value to set in it
     */
    public void settingcolor(ComboBox<String> colour,String a){
    	colour.setMaxWidth(100);
    	colour.setValue(a);
    }
    /**it checks where there is a file stored or not which can be used to resume the game
     * @throws IOException
     */
    public void check() throws IOException
    {
    	try
        {
        	FileInputStream inp;
        	inp= new FileInputStream("resume_board.ser");
    		ObjectInputStream in;
    		in= new ObjectInputStream(inp);
    		Board b1=(Board) in.readObject();
    		FileInputStream inp1;
    		inp1= new FileInputStream("resume_player.ser");
    		ObjectInputStream in1;
    		in1= new ObjectInputStream(inp1);
    		Player pin1=(Player) in1.readObject();
    		FileInputStream inp2;
    		inp2= new FileInputStream("resume_playerList.ser");
    		ObjectInputStream in2;
    		in2= new ObjectInputStream(inp2);
    		ArrayList<Player> arrayplayer=(ArrayList<Player>) in2.readObject();
    		
    		try
    		{
    			MainGame.board = b1;
    		}
    		finally
    		{
    			in.close();
    		}
    		
    		try
    		{
    			MainGame.present = pin1;
    		}
    		finally
    		{
    			in1.close();
    		}
    		
    		try
    		{
    			MainGame.players = arrayplayer;
    		}
    		finally
    		{
    			in2.close();
    		}
    		setdisable(Resume,false);
        }
        catch(ClassNotFoundException e)
        {
        	setdisable(Resume,true);
        }
        catch(FileNotFoundException e)
        {
        	setdisable(Resume,true);
        }
    }
    /**it is basically use in settings which on selecting one color removes that particular color from that list to avoid taking same color
     * @param colour1 color 1
     * @param colour2 color 2
     * @param colours arraylist of colors
     * @param done2 button on which action is used
     * @param colours_final arraylist of colors
     * @param min
     */
    public void undoing(ComboBox<String> colour1,ComboBox<String> colour2,ArrayList<String> colours,Button done2,ArrayList<String> colours_final,int min){
    	
			if(!(colour2.getItems().toString()).equals("[]"))
			{
				
				colour2.getItems().clear();
				for (String e : colours_final)
				{
					if(!colours.contains(e))
					{
						colours.add(e);
					}
				}
				for(int i=0;i<min;i++){
    				colours.remove(undolist.get(i).getValue());
    				}
				colour2.getItems().addAll(colours);
			}
			else
			{
				colours.remove(colour1.getValue());
				colour2.getItems().addAll(colours);
			}
			done2.setDisable(false);
			colour2.setValue("--");
			colour2.setDisable(false);
    
    }
    /**it returns the number of players selected to play the game
     * @return
     */
    public int handling(){
    	size=0;
		for(int i=0;i<8;i++){
		if(undolist.get(i).getValue().equals("--"))
		{
			 size = i;
		}
		}
//		System.out.println(size);
		return size;
		
	}
    /**
     * when the start is pressed it launches the main screen where matrix is there 
     */
    public void game()
    {
    	DisplayGrid.setMinSize(m * 50, (n + 1) * 50);
    	DisplayGrid.setAlignment(Pos.CENTER);
    	Cube mgame;
    	if (!flag)
    	{
    		MainGame.begin(m, n);
    	}
    	int r=0;
    	while( r < m) 
        {	int c=0;
            while(c < n) 
            {
            	tile a;
            	a= new tile();
            	mgame=MainGame.board.getMatrix(r, c);
                DisplayGrid.add(a, c, r);
                a.cube = mgame;  
                list.add(a);
                c++;
            }
            r++;
        }
    	totpla=0;
    	try
    	{
    		DisplayGrid.add(Undo, n+1, m - 1);
    		totpla++;
    		DisplayGrid.add(menuButton, n+1, 0);
    	}
    	catch(IllegalArgumentException e)
    	{
    		totpla=0;
    		System.out.println("DUPLICATE CHILD HERE");
    	}
    	thestage.setTitle("Game");
    	totpla++;
    	thestage.show();
    	thestage.setScene(display);
    	
    	gridSize.setValue("					    Select Grid Size for the game");
    	numberOfPlayers.setValue("						Select number of players");
    }
    
    @Override 
    public void start(Stage stage) throws IOException 
    {
    	undolist.add(colour1);
    	thestage = stage;
    	undolist.add(colour2);
    	Pane grid = new Pane();
    	gridSize = new ComboBox<String>();
    	GridPane gridNew = new GridPane();
    	undolist.add(colour3);
        thestage.setTitle("Main Page");
        undolist.add(colour4);
        scene = new Scene(new Group(), 550, 450, Color.WHITE);
        sceneNew = new Scene(new Group(), 550, 450, Color.WHITE);
        undolist.add(colour5);
        numberOfPlayers.getItems().addAll("2","3","4","5","6","7","8");
        gridSize.getItems().addAll("9x6","15x10");
        undolist.add(colour6);
        undolist.add(colour7);
        undolist.add(colour8);
        done1.setOnAction(new Event1());
        done2.setOnAction(new Event2());
        done3.setOnAction(new Event3());
        done4.setOnAction(new Event4());
        done5.setOnAction(new Event5());
        done6.setOnAction(new Event6());
        done7.setOnAction(new Event7()); 
        done8.setOnAction(new Event8());
        ok.setOnAction(new Event9());
        Back.setOnAction(new Event10()); 
        Resume.setOnAction(new Event11());  
        Undo.setOnAction(new Event12());
        menuItem2.setOnAction(new Event13());
        menuItem1.setOnAction(new Event14());
        Start.setOnAction(new Event15());
        Settings.setOnAction(new Event16()); 
        Text t = new Text(80, 40, "");
        Text t1 = new Text(25, 80, "Text");
        t1.setWrappingWidth(520);
        gridNew.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        t.setText("	 CHAIN REACTION");
        t1.setFont(new Font(14));
        t.setFont(Font.font ("Courier",FontWeight.EXTRA_BOLD, 28));
        t1.setFill(Color.BLACK);
        gridNew.setHgap(10);
        gridNew.setPadding(new Insets(5, 5, 5, 5));
        t.setFill(Color.BLACK);
	    t1.setText("The objective of Chain Reaction is to take control of the board by eliminating your opponents orb.\n\nPlayer takes it in turns to place their orbs in a cell. Once a cell has reached critical mass the orbs explode into the surrounding cells adding an extra orb and claiming the cell for the player. A player may only place their orbs in a blank cell or a cell that contains orbs of their own color.As soon as a player looses all their orbs they are out of the game.");
	    
	    
	    

        numberOfPlayers.setLayoutX(20);
        gridSize.setLayoutX(20);
        numberOfPlayers.setLayoutY(330);
        gridSize.setLayoutY(360);
        numberOfPlayers.setValue("						Select number of players");
        gridSize.setValue("					    Select Grid Size for the game");
        numberOfPlayers.setMinWidth(500);
       
        gridSize.setMinWidth(500);
        check();
        Resume.setMinWidth(500);
        grid.getChildren().add(gridSize);
        Start.setLayoutX(20);
        Resume.setLayoutX(20);
        Start.setLayoutY(400);
        settingcolor(colour1,"Red");
        Resume.setLayoutY(250);
        Start.setMinWidth(240);
        settingcolor(colour2,"Green");
        Settings.setMinWidth(240);
         
        
        
        Settings.setLayoutX(280);
        DisplayGrid = new GridPane();
        gridNew.add(new Label("Choose player settings: "), 0, 0);
        addingplayer(gridNew);
        Settings.setLayoutY(400);
        
        DisplayGrid.setVgap(1);
        DisplayGrid.setHgap(1);
        
        
        
        settingcolor(colour3,"Blue");
        settingcolor(colour4,"Yellow");
        settingcolor(colour5,"Pink");
        settingcolor(colour6,"Cyan");
        settingcolor(colour7,"Orange");
        settingcolor(colour8,"White");
        setdisable2(undolist,true);
        addingcolor(gridNew);

        setdisable(done2,true);
        setdisable(done3,true);
        setdisable(done4,true);
        setdisable(done5,true);
        setdisable(done6,true);
        setdisable(done7,true);
        setdisable(done8,true);
        
        Group root = (Group)scene.getRoot();
        Group root1 = (Group)sceneNew.getRoot();
        DisplayGrid.setAlignment(Pos.CENTER);
        DisplayGrid.setPadding(new Insets(10));
        
        addingdone(gridNew,done1,1);
        addingdone(gridNew,done2,2);
        addingdone(gridNew,done3,3);
        addingdone(gridNew,done4,4);
        addingdone(gridNew,done5,5);
        addingdone(gridNew,done6,6);
        addingdone(gridNew,done7,7);
        addingdone(gridNew,done8,8);
        
        colour1.getItems().addAll(colours);
        gridNew.add(Back, 0, 9);
       
        display = new Scene(DisplayGrid);
       
        grid.getChildren().add(Settings);
        grid.getChildren().add(Start);
        grid.getChildren().add(Resume);
        grid.getChildren().add(t);
        grid.getChildren().add(t1);
        grid.getChildren().add(numberOfPlayers);
        root.getChildren().add(grid);
        root1.getChildren().add(gridNew);
		
        thestage.setScene(scene);
        thestage.show();
    }
    class Event1 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if (colour1.getValue() != null && !colour1.getValue().toString().isEmpty())
			{
    			undoing(colour1,colour2,colours,done2,colours_final,1);
			}
		}
    }
    
    class Event2 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour2.getValue().equals("--")) && !colour2.getValue().toString().isEmpty())
			{
    			undoing(colour2,colour3,colours,done3,colours_final,2);
			}
		}
    }
    class Event3 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour3.getValue().equals("--")) && !colour3.getValue().toString().isEmpty())
			{	
    			undoing(colour3,colour4,colours,done4,colours_final,3);
			}
		}
	}
    class Event4 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour4.getValue().equals("--")) && !colour4.getValue().toString().isEmpty())
			{
    			undoing(colour4,colour5,colours,done5,colours_final,4);
			}
		}
	}
    class Event5 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour5.getValue().equals("--")) && !colour5.getValue().toString().isEmpty())
			{
    			undoing(colour5,colour6,colours,done6,colours_final,5);
			}
		}
	}
    class Event6 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour6.getValue().equals("--")) && !colour6.getValue().toString().isEmpty())
			{
    			undoing(colour6,colour7,colours,done7,colours_final,6);
			}
		}
	}
    class Event7 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour7.getValue().equals("--")) && !colour7.getValue().toString().isEmpty())
			{
    			undoing(colour7,colour8,colours,done8,colours_final,7);
			}
		}
	}
    class Event8 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if ((!colour8.getValue().equals("--")) && !colour8.getValue().toString().isEmpty())
    		{
    			thestage.setTitle("Main Page");
    			thestage.setScene(scene);
    		}
		}
	}
    class Event9 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			NewStage.close();
		}
	}
    class Event10 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
    		handling();
    		
    		if(!(undolist.get(7).getValue().equals("--")) && !(undolist.get(7).isDisable()))
    		{
    			size = 8;
    		}
    		System.out.println(size + " size, " + numberOfPlayers.getValue() + " players");
    		while(size == Integer.parseInt(numberOfPlayers.getValue()))
    		{
    			thestage.setTitle("Main Page");
    			thestage.setScene(scene);
    			break;
    		}
    		
		}
	}
    class Event11 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			MainGame.PrintBoard(MainGame.board);
			tile.ChangeGrid(Color.web(MainGame.present.Colour_string));
			tile a;
			n = (int)MainGame.board.BoardWidth();
			DisplayGrid.getChildren().clear();
			m = (int)MainGame.board.BoardLength();
			list.clear();
			int i=0;
			while(i < m)
			{	int j=0;
				while(j < n)
				{
					String color;
					final PhongMaterial redMaterial;
					a= new tile();
					DisplayGrid.add(a, j, i);
					a.cube = MainGame.board.getMatrix(i, j);
					color= MainGame.board.getMatrix(i, j).Colour;
					redMaterial= new PhongMaterial();
				    redMaterial.setSpecularColor(Color.web(color));
				    redMaterial.setDiffuseColor(Color.web(color));
				    int k=0;
					while(k < a.cube.ActualMass)
					{
						animation.animate(a, redMaterial);
						k++;
					}
					list.add(a);
					j++;
				}
				i++;
			}
			thestage.setScene(display);
			DisplayGrid.add(Undo, n+1, m - 1);
    		DisplayGrid.add(menuButton, n+1, 0);
    		thestage.setTitle("Game");
        	thestage.setScene(display);
        	thestage.show();
        	gridSize.setValue("					    Select Grid Size for the game");
        	numberOfPlayers.setValue("						Select number of players");
        	
        	Undo.setDisable(true);
		}
	}
    class Event12 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			flag = true;
			try 
			{
				deserialize(1);
			} 
			catch (ClassNotFoundException | IOException e) 
			{
				e.printStackTrace();
			}
			
			Board board;
			Player player;
			board= board_deserialized;
			player= player_deserialized;
			
			MainGame.board = board;
			MainGame.present = player;
			
			MainGame.player_number=MainGame.player_number-1;
			while(MainGame.player_number == 0)
			{
				MainGame.player_number = MainGame.players.size();
				break;
			}
			DisplayGrid.getChildren().clear();
		    tile.ChangeGrid(Color.web(player.Colour_string));
			list.clear();
			int i=0;
			while(i < GUI.m)
			{
				int j=0;
				while(j < GUI.n)
				{
					tile a ;
					a= new tile();
					DisplayGrid.add(a, j, i);
					a.cube = board.getMatrix(i, j);
					
					String color;
					color= board.getMatrix(i, j).Colour;
					final PhongMaterial redMaterial;
					redMaterial= new PhongMaterial();
				    redMaterial.setSpecularColor(Color.web(color));
				    redMaterial.setDiffuseColor(Color.web(color));
				    int k=0;
					while( k < a.cube.ActualMass)
					{
						animation.animate(a, redMaterial);
						k++;
					}
					list.add(a);
					j++;
				}
				i++;
			}
			DisplayGrid.add(Undo, n+1, m - 1);
			thestage.setTitle("Game");
			thestage.show();
    		DisplayGrid.add(menuButton, n+1, 0);
        	thestage.setScene(display);
        	gridSize.setValue("					    Select Grid Size for the game");
        	numberOfPlayers.setValue("						Select number of players");
        	Undo.setDisable(true);
		}
	}
    class Event13 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			game();
		}
	}
    class Event14 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			int numtrack=0;
			try {
				MainGame.serialize(MainGame.board, MainGame.present, 3);
			} catch (IOException e) {
				numtrack++;
				e.printStackTrace();
			}
			try {
				check();
			} catch (IOException e) {
				numtrack++;
				e.printStackTrace();
			}
			thestage.setScene(scene);
		}
	}
    class Event15 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String value;
			int num=0;
			int inx ;
			 if ((!numberOfPlayers.getValue().equals("						Select number of players")) && !numberOfPlayers.getValue().toString().isEmpty())
             {   
				 num=1;
         		MainGame.makeColorList();

             	total_players = Integer.parseInt(numberOfPlayers.getValue());
             	num=0;
                 if ((!gridSize.getValue().equals("					    Select Grid Size for the game")) && !gridSize.getValue().toString().isEmpty())
                 {
                	num=1;
                 	value= gridSize.getValue();
                 	inx= (value.indexOf("x") + 1);
                 	num=0;
                 	value = value.substring(inx);
                 	n = Integer.parseInt(value); 
                 	m = Integer.parseInt(gridSize.getValue().substring(0, (gridSize.getValue()).indexOf("x")));
                 	num++;
                 	game();
                 }
                
             }
		}
	}
    class Event16 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			thestage.setTitle("Settings");
			thestage.setScene(sceneNew);
		}
	}
    class Event17 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
    class Event18 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
		}
	}
}