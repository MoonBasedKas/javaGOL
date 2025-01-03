package moon.gol;

import java.util.ArrayList;

public class board {

    //Keeps track of if a pixel exists by if its true. (true == alive)
    // (y,x) coordinates v, >
    public ArrayList<ArrayList<Boolean>> board = new ArrayList<>();

    private int width;
    private int height;

    /**
     * Constructs the inital board with a given arraylist of pixels.
     * @param width
     * @param height
     * @param pixels
     */
    public board(int width, int height, ArrayList<pixel> pixels){
        this.width = width;
        this.height = height;
        ArrayList<Boolean> temp;

        for(int i = 0; i < height; i++){
            temp = new ArrayList<Boolean>();
            for(int j = 0; j < width; j++){
                temp.add(false);
            }
            this.board.add(temp);
        }

        for(pixel p: pixels){
            board.get(p.x).set(p.y, true);
        }
    }

    /**
     * Returns this specific board may get rid of.
     * @return
     */
    public board getBoard(){
        return this;
    }

    
}
