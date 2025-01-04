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
            board.get(p.y).set(p.x, true);
        }
    }





    private Integer countNeighbors(int x, int y){

        int count = 0;

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                // System.out.print(j);
                // System.out.println(i);
                // Is current pixel?
                if(j == 0 && i == 0) {}

                else if(j + x < 0 || i + y < 0) {} // negative value

                else if(j + x >= width || i + y >= height) {} // negative value

                else if(board.get(y + i).get(x + j)) count += 1; // Is the pixel alive
            }
        }

        return count;
    }


    private Boolean verify(int x, int y){
        int count = countNeighbors(x, y);

        if (count == 2 && board.get(y).get(x)){
            return true;
        }

        return count == 3;

    }

    
    public void addPixel(int x, int y){
        if ((x > -1 && y > -1) && (x < width && y < height))
        board.get(y).set(x, true);
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
