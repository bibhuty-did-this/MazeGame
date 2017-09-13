
import java.io.File;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainMenu {

    private JFrame Menu;//Main frame of the program
    private JButton Start;//Variable for the Start button
    private JButton Exit;//Variable for the Exit button
    private JButton MapMaker;//Variable for the MapMaker button
    private ImageIcon picture;//Variable for the picture to be retrieved
    private JLabel imageLabel;//Variable to display the retrieved picture

    private ArrayList<String> mapList;//Stores the names of the list of maps we have created or will create in future
    private JComboBox<String> lvlList;//It contains the names of all the existing maps in it

    private int menuWidth;//Width of each button/item on display
    private int menuHeight;//Height of each button/item on display
    private int menuY; //Button/item location on display
    private int WIDTH;//Width of the menu
    private int HEIGHT;//Height of the menu
    
            
	public MainMenu() {
	    //This function initializes all the private variables hence increasing encapsulation
        initialize();

    	//This function loads the names of maps present in mapList variable
    	getMapList();

        //This method initializes the menu
        setMenu();

        //This method sets the necessary functionality of the Start button
        setStartButton();
        
        //This method sets the necessary functionality of the MapMaker button
        setMapMakerButton();
        
        //This method initializes the level selector drop down list
        setLevelSelectorDropDown();
        
        //This method sets the necessary functionality of the exit button
        setExitButton();
        
        //Display Picture
        setDisplayPicture();
    }

    /**
     * This method initializes all the necessary private
     * variables which are to be used in future
     */
    private void initialize(){
        this.Menu = new JFrame("Maze");//Sets the title of the menu bar as "Maze"
        this.Start = new JButton("Play");//Sets the title of the button as "Play"
        this.Exit = new JButton("Exit");//Sets the title of another button as "Exit"
        this.MapMaker = new JButton("Map Maker");//Sets the title fo another button as "Map Maker"
        this.picture = new ImageIcon("res/Images/MazePicture.png");//Loads the picture from a given location to the variable
        this.imageLabel = new JLabel(picture);//Loads the picture from the variable to the label in the centre position

        this.menuWidth = 100;
        this.menuHeight = 30;
        this.menuY = 460;
        this.WIDTH = 550;
        this.HEIGHT = 530;
    }

    /**
     * This method is explicitly there to check whether the file we are trying to
     * get from outside exists or not. If the file exists then it populates the file
     * name in mapList variable
     */
    private void getMapList(){
        this.mapList = new ArrayList<>();//initializes the mapList are makes it ready to store the name of maps present

        //gets the file and loads it into mapList if the file exists
    	for(int i = 0; i < 99; i++){
    		File map = new File("./Level "+i+".map");//tries to retrieve file from the source
    		if(map.exists()){//checks for whether the file exists or not
    			System.out.println("Level "+i+" exists");//for debugging purpose as it displays the file which is added
    			mapList.add("Level "+i+".map");//adds the name of the file list to the mapList
    		}
    	}

    }

    /**
     * This method deals with housekeeping stuffs of the menu
     */
    private void setMenu(){
        Menu.setResizable(false);//menu is static
        Menu.setSize(WIDTH, HEIGHT);//sets the initial size(or size) of the menu
        Menu.setLayout(null);//sets the layout
        Menu.setLocationRelativeTo(null);//default pop-up, no need to think about any relative location to pop up
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//enables the default close button i.e. `x` button
    }

    /**
     * This method sets all the necessary functionality of the
     * Start button
     */
    private void setStartButton(){
        Start.setSize(menuWidth,menuHeight);//sets size of the button
        Start.setLocation(10, menuY);//sets the location of the button
        Menu.add(Start);//adds the button to main frame
        //function to define what happens when you click the button
        Start.addActionListener(event->{
            /*
             * When the start button is clicked extract the name of the selected maze
             * and navigate to the extracted maze.
             */
            new Maze(lvlList.getSelectedItem().toString());
            Menu.setVisible(false);//after we navigate to the required map hide the main menu
        });
    }

    /**
     * This method sets all the necessary functionality of the
     * MapMaker button
     */
    private void setMapMakerButton(){
        MapMaker.setSize(menuWidth+15,menuHeight);//sets the size of the button
        MapMaker.setLocation(122, menuY);//sets the button at the specified location
        Menu.add(MapMaker);//add the button to the menu
        //function to define what happens when you click the button
        MapMaker.addActionListener(event->{
            /*
             * It either creates a new map or edits the existing map
             */
            new MazeMapMaker();
            Menu.setVisible(false);//after we navigate to the required location, make the menu invisible
        });
    }

    /**
     * This method initializes the drop down list
     */
    private void setLevelSelectorDropDown(){
        lvlList = new JComboBox<>(mapList.toArray(new String[mapList.size()]));//put all the map names into it
        lvlList.setSize(menuWidth+35, menuHeight);//sets the size of the drop down
        lvlList.setLocation(250, menuY);//put the dropdown in the specified location
        Menu.add(lvlList);//add the drop down to the main menu
    }

    /**
     * This method initializes and sets all the necessary functionality of the exit button
     */
    private void setExitButton(){
        Exit.setSize(menuWidth,menuHeight);//sets the size of the exit button
        Exit.setLocation(397,menuY);//sets the location where the button to be put
        Menu.add(Exit);//adds exit to the main menu
        Exit.addActionListener(event->System.exit(0));//function to execute when the button is pressed
    }

    /**
     * This method initializes the picture taken from the source to the main menu
     */
    private void setDisplayPicture(){
        imageLabel.setBounds((WIDTH-412)/2, 25, 412, 412);
        imageLabel.setVisible(true);
        Menu.add(imageLabel);
        Menu.setVisible(true);
    }
}
