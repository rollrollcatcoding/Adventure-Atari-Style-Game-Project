//Fan Yang, Jake Sherer, Shohrukh Jalolov, Daniel Paterson
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * This is where we initialize our treasures, movement vectors, the track, checkpoint and timer
 */
public class AdventureZoo {
    private static Treasure gold = new Treasure("Gold", new Color(223, 215, 62), new Vector(0.5, 0.5), 0.02);
    private static Treasure silver = new Treasure("Silver", new Color(164, 166, 138), new Vector(0.8, 0.5), 0.02);
    private static Treasure diamond = new Treasure("Diamond", new Color(255, 202, 195), new Vector(0.2, 0.5), 0.02);
    private static Treasure dog = new Treasure("Dog", new Color(207, 136, 4), new Vector(0.6, 0.8), 0.02);
    private static Treasure key = new Treasure("Key", new Color(179, 191, 184), new Vector(0.2, 0.94), 0.02);
    private static Treasure fire = new Treasure("Fire", new Color(255, 85, 25), new Vector(0.9, 0.94), 0.02);
    private static Treasure water = new Treasure("Water", new Color(250, 248, 255), new Vector(0.4, 0.1), 0.02);

    //here we initialize out movement variables
    private static final double STEP = 0.005;
    private static final Vector UP = new Vector(0, STEP);
    private static final Vector DOWN = new Vector(0, -STEP);
    private static final Vector LEFT = new Vector(-STEP, 0);
    private static final Vector RIGHT = new Vector(STEP, 0);
    private static Human player;
    private long startTime; //timer

    private static ArrayList<LineSegment> track; //array of track
    private static ArrayList<LineSegment> checkpoints; //array of checkpoints

    private static ArrayList<Treasure> treasures = new ArrayList<>(); //array of treasures
    private static Treasure[] bag = new Treasure[100];

    private static boolean trackOne = true;
    private static boolean trackTwo;
    private static boolean trackThree;


    private static int treasureCount; //counter for treasure

    /**
     * Constructs a game
     */
    private AdventureZoo() {
        player = new Human(0.07, 0.07, "HUMAN", Color.BLUE);
        track = createTrack();
        checkpoints = new ArrayList<>();
        checkpoints.add(new LineSegment(1, 0.4, 1, 0.6));
        treasures.add(gold);
        treasures.add(diamond);
        treasures.add(silver);
        treasures.add(key);
        treasures.add(dog);
        treasures.add(water);
        treasures.add(fire);
    }

    /**
     * Constructs and runs a game
     */
    public static void main(String[] args) {
        new AdventureZoo().run();
    }

    /**
     * Here we draw the treasures and place them in the AdventureMaze
     */

    private static void drawTreasures() {
        for (Treasure treasure : treasures) {
            StdDraw.setPenColor(treasure.getColor());
            StdDraw.filledCircle(treasure.getLocation().getX(), treasure.getLocation().getY(), treasure.getRadius());
        }
    }

    /**
     * Here we check when the player hits a wall in
     * the maze and stops the player from crossing the wall
     * We also check which level the player is in
     */

    private static void intersectsLine() {
        //We call change track
        if(trackOne){
            changeToTrackTwo();
        }else if(trackTwo){
            changeToTrackThree();
        }
    }

    /**
     * Here we change from one Track to the next Track and clear everything to set
     * the new treasure, player and new LineSegment
     */
    private static void changeToTrackTwo() {
        for (LineSegment line : checkpoints) {
            if (player.intersects(line) && treasureCount == 7) {
                trackTwo = true;
                trackThree = false;
                trackOne = false;
                track = createTrackTwo();
                treasures.clear();
                player = new Human(0.1, 0.3, "Human", Color.CYAN);
                gold.setLocation(new Vector(0.9, 0.35));
                treasures.add(gold);
                diamond.setLocation(new Vector(0.7, 0.1));
                treasures.add(diamond);
                silver.setLocation(new Vector(0.35, 0.1));
                treasures.add(silver);
                key.setLocation(new Vector(0.4, 0.3));
                treasures.add(key);
                dog.setLocation(new Vector(0.5, 0.9));
                treasures.add(dog);
                water.setLocation(new Vector(0.1, 0.9));
                treasures.add(water);
                fire.setLocation(new Vector(0.15, 0.15));
                treasures.add(fire);

            }
        }

    }

    /**
     * here we change from track two to track three
     * this resets the treasures, player and lines
     */

    private static void changeToTrackThree() {
        for (LineSegment line : checkpoints) {
            if (player.intersects(line) && treasureCount == 14) {
                trackTwo = false;
                trackThree = true;
                trackOne = false;
                track = createTrackThree();
                treasures.clear();
                player = new Human(0.1, 0.3, "Human", Color.CYAN);
                gold.setLocation(new Vector(0.1, 0.85));
                treasures.add(gold);
                diamond.setLocation(new Vector(0.15, 0.1));
                treasures.add(diamond);
                silver.setLocation(new Vector(0.4, 0.1));
                treasures.add(silver);
                key.setLocation(new Vector(0.8, 0.1));
                treasures.add(key);
                dog.setLocation(new Vector(0.8, 0.89));
                treasures.add(dog);
                water.setLocation(new Vector(0.4, 0.89));
                treasures.add(water);
                fire.setLocation(new Vector(0.6, 0.65));
                treasures.add(fire);
            }

        }
    }

    /**
     * Here we check if player collects the treasure and removes the treasure from the map
     */
    private static void collectTreasure() {
        Treasure current = new Treasure(null, null, null, 0);
        for (Treasure treasure : treasures) {
            if (intersects(treasure)){
                bag[++treasureCount] = treasure;
                current=treasure;
            }
        }
        treasures.remove(current);
    }


    /**
     * Here we calculate the distance between the treasure and the player and
     * decide if the player should collect the treasure or not
     */
    private static boolean intersects(Treasure treasure){
       return Math.sqrt(Math.pow(player.getCenter().getY()-treasure.getLocation().getY(),
               2)+ Math.pow(player.getCenter().getX()-treasure.getLocation().getX(),2)) < .01;
    }

    /**
     * Creates track one
     */
    private ArrayList<LineSegment> createTrack() {
        ArrayList<LineSegment> track = new ArrayList<>();
        // Outer edges
        track.add(new LineSegment(0, 0, 1, 0));
        track.add(new LineSegment(1, 0.60, 1, 1));
        track.add(new LineSegment(1, 0, 1, 0.40));
        track.add(new LineSegment(1, 1, 0, 1));
        track.add(new LineSegment(0, 1, 0, 0));
        // Inner edges
        track.add(new LineSegment(0.5, 0, 0.5, 0.25));
        track.add(new LineSegment(0.5, 0.75, 0.5, 1));
        track.add(new LineSegment(0.10, 0.35, 0.10, 0.65));
        track.add(new LineSegment(0.90, 0.35, 0.90, 0.65));
        track.add(new LineSegment(0.25, 0.25, 0.25, 0.75));
        track.add(new LineSegment(0.75, 0.25, 0.75, 0.75));
        track.add(new LineSegment(0.10, 0.75, 0.25, 0.75));
        track.add(new LineSegment(0.10, 0.25, 0.25, 0.25));
        track.add(new LineSegment(0.10, 0.13, 0.35, 0.13));
        track.add(new LineSegment(0.65, 0.13, 0.90, 0.13));
        track.add(new LineSegment(0.65, 0.87, 0.90, 0.87));
        track.add(new LineSegment(0.10, 0.87, 0.35, 0.87));
        track.add(new LineSegment(0.90, 0.75, 0.75, 0.75));
        track.add(new LineSegment(0.90, 0.25, 0.75, 0.25));
        //CENTER square
        track.add(new LineSegment(0.35, 0.65, 0.65, 0.65));
        track.add(new LineSegment(0.35, 0.35, 0.35, 0.65));
        track.add(new LineSegment(0.65, 0.65, 0.65, 0.35));
        track.add(new LineSegment(0.35, 0.35, 0.45, 0.35));
        track.add(new LineSegment(0.55, 0.35, 0.65, 0.35));

        return track;
    }

    /**
     *Here we draw track two
     */
    private static ArrayList<LineSegment> createTrackTwo() {
        ArrayList<LineSegment> track = new ArrayList<>();
        // Outer edges
        track.add(new LineSegment(0, 0, 1, 0));
        track.add(new LineSegment(1, 0.60, 1, 1));
        track.add(new LineSegment(1, 0, 1, 0.40));
        track.add(new LineSegment(1, 1, 0, 1));
        track.add(new LineSegment(0, 1, 0, 0));
        // Inner edges
        track.add(new LineSegment(0, 0.2, 0.1, 0.2));
        track.add(new LineSegment(0.3, 0, 0.3, 0.2));
        track.add(new LineSegment(0.6, 0, 0.6, 0.2));
        track.add(new LineSegment(0.9, 0, 0.9, 0.2));
        track.add(new LineSegment(0.4, 0.2, 0.6, 0.2));
        track.add(new LineSegment(0.2, 0.4, 1, 0.4));
        track.add(new LineSegment(0.2, 0.2, 0.2, 0.8));
        track.add(new LineSegment(0.2, 0.8, 0.4, 0.8));
        track.add(new LineSegment(0.6, 1, 0.6, 0.6));
        track.add(new LineSegment(0.8, 0.8, 0.8, 0.2));
        track.add(new LineSegment(0.4, 0.6, 0.6, 0.6));
        track.add(new LineSegment(0.9, 0.3, 1, 0.3));
        track.add(new LineSegment(0.5, 0.3, 0.5, 0.4));

        return track;
    }

    /**
     *Here we draw track three
     */

    private static ArrayList<LineSegment> createTrackThree() {
        ArrayList<LineSegment> track = new ArrayList<LineSegment>();
        // Outer edges
        track.add(new LineSegment(0, 0, 1, 0 ));
        track.add(new LineSegment(1, 0, 1, 1));
        track.add(new LineSegment(1, 1, 0, 1));
        track.add(new LineSegment(0, 1, 0, 0));
        //outer corners
        track.add(new LineSegment(0.25, 0.75, 0.5, 0.75));
        track.add(new LineSegment(0.5, 0.25, 0.75, 0.25));
        track.add(new LineSegment(0.25, 0.75, 0.25, 0.5));
        track.add(new LineSegment(0.75, 0.5, 0.75, 0.25));
        //corner wall things
        track.add(new LineSegment(0.75, 1, 0.75, 0.80));
        track.add(new LineSegment(0.25, 0, 0.25, 0.20));
        track.add(new LineSegment(0.85, 0.65, 1, 0.65));
        track.add(new LineSegment(0, 0.35, 0.15, 0.35));
        //add center square
        track.add(new LineSegment(0.350, 0.40, 0.350, 0.60));
        track.add(new LineSegment(0.350, 0.40, 0.650, 0.40));
        track.add(new LineSegment(0.350, 0.60, 0.650, 0.60));
        track.add(new LineSegment(0.650, 0.40, 0.650, 0.60));
        //little walls
        track.add(new LineSegment(0.55, 0.10, 0.55, 0.25));
        track.add(new LineSegment(0.70, 0, 0.70, 0.15));
        track.add(new LineSegment(0.45, 1, 0.45, 0.85));
        track.add(new LineSegment(0.30, 0.75, 0.30, 0.90));
        track.add(new LineSegment(0.10, 0.70, 0.25, 0.70));
        track.add(new LineSegment(0, 0.60, 0.15, 0.60));
        track.add(new LineSegment(0.75, 0.40, 0.90, 0.40));
        track.add(new LineSegment(0.85, 0.30, 1, 0.30));

        return track;
    }

    /**
     * Runs the game and updates the game, keeps track of how long the player has played
     * and shows if the player won or not
     */
    private void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.05, 1.05);
            StdDraw.pause(10);
        //Here we say what startTime is
        startTime = System.currentTimeMillis();
            update();
    }

    /**
     * Draws the field and everything as declared in more detail below
     */

    private void draw() {
      // Fill the Background with black square
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(.5, .5, 0.5);

        // Draw the track
        StdDraw.setPenColor(Color.CYAN);
        for (LineSegment line : track) {
            Vector a = line.getA();
            Vector b = line.getB();
            StdDraw.setPenRadius(0.03);
            StdDraw.line(a.getX(), a.getY(), b.getX(), b.getY());
        }

        //This is the door
        StdDraw.setPenColor(Color.CYAN);
        LineSegment finishLine = checkpoints.get(0);
        Vector a = finishLine.getA();
        Vector b = finishLine.getB();
        StdDraw.setPenRadius(0.03);
        StdDraw.line(1, 0.4, 1, 0.6);

        // Draw the Treasures
        drawTreasures();

        //This draws the timer
        StdDraw.setPenColor();
        StdDraw.text(0.9, 1, "Time: " + (System.currentTimeMillis() - startTime) / 1000);

        // Draw the player
        Vector center = player.getCenter();
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledSquare(center.getX(), center.getY(), 0.02);

        //Draw the treasure counter
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.1, .997,"Treasure");
        StdDraw.text(0.2, .997,String.valueOf(treasureCount));

        StdDraw.show();

    }

    /**
     * This controls all onscreen text
     */

    private void textTalk(){
        //gives the player a hint on how to move to level two
        if( treasureCount == 7 && trackOne){
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(0.5, 0.3, "The Door On The Right Side!");
            StdDraw.show();
        }

        //provides encouragement for the player
        if (treasureCount == 10) {
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(0.8, 0.85, "Great Work");
            StdDraw.show();
        }

        //gives the player instructions on how to play
        if (treasureCount == 0){
            StdDraw.setPenColor(Color.ORANGE);
            StdDraw.text(0.5, 0.55, "Use W,A,S,D to  ");
            StdDraw.text(0.5,0.43, "Collect Treasures");
            StdDraw.show();
        }

        //tells the player that they are on the right track
        if (treasureCount == 3) {
            StdDraw.setPenColor(Color.ORANGE);
            StdDraw.text(0.5, 0.55, "Four more left ");
            StdDraw.text(0.5, 0.43, "To Unlock the Door");
            StdDraw.show();
        }

        //gives a hint for how to get to level three
        if( treasureCount == 14 && trackTwo){
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(0.5, 0.5, "The Door On The Right Side!");
            StdDraw.show();
        }

        //Lets the player know when they've won!
        if (treasureCount == 21) {
            track = createTrackThree();
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.text(0.5, 0.5, "You Won!");
            StdDraw.show();
        }
    }
    /**
     * Updates the field and counts until the player has won the game
     */
    private void update() {
        while (true) {
            handleKeys();
            draw();
            intersectsLine();
            collectTreasure();
            textTalk();
        }
    }

    /**
     * Handles key presses and if player intersects line
     * it doesn't allow player to make the move
     */
    private void handleKeys() {

        if (StdDraw.isKeyPressed(KeyEvent.VK_W) ) {
            player.shift(UP);
           for (LineSegment lineSegment: track)
               if(player.intersects(lineSegment))
                player.shift(DOWN);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_A) ) {
            player.shift(LEFT);
            for (LineSegment lineSegment: track)
                if(player.intersects(lineSegment))
                    player.shift(RIGHT);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_S) ) {
            player.shift(DOWN);
            for (LineSegment lineSegment: track)
                if(player.intersects(lineSegment))
                    player.shift(UP);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            player.shift(RIGHT);
            for (LineSegment lineSegment: track)
                if(player.intersects(lineSegment))
                    player.shift(LEFT);
        }
    }


}
