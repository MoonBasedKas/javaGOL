/**
 * This class handles the actual board and its logic.
 * 
 * TODO: diagonally flipping board glitch.
 */
package moon.gol;

import java.util.ArrayList;

public class Board {

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
    public Board(int width, int height, ArrayList<BoardPoint> pixels){
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

        for(BoardPoint p: pixels){
            board.get(p.x).set(p.y, true);
        }
    }



    /**
     * Returns this specific board may get rid of.
     * @return
     */
    public Board getBoard(){
        return this;
    }


    private Integer countNeighbors(int x, int y){

        int count = 0;

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                // Is current pixel?
                if(j == 0 && i == 0) break;

                if(j + x < 0 || i + y < 0) break; // negative value

                if(j + x >= width || i + y >= height) break; // negative value

                if(board.get(y + i).get(x + j)) count += 1; // Is the pixel alive
            }
        }

        return count;
    }


    private Boolean verify(int x, int y){
        int count = countNeighbors(x, y);

        if (count == 2 || count == 3) return true;

        return false;
    }
    

    public Board generateNextGeneration(){
        ArrayList<BoardPoint> cords = new ArrayList<BoardPoint>();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(verify(x, y)){
                    cords.add(new BoardPoint(x, y));
                }
            }
        }

        return new Board(width, height, cords);
    }
}
