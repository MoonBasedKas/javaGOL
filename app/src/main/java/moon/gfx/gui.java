/**
 * Handles displaying the information of the board to the user.
 * 
 * TODO: Allow for better pixel scaling.
 */

package moon.gfx;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import moon.gol.Board;
import moon.gol.BoardPoint;

public class gui extends Application{
    
    private GraphicsContext g;
    private Board board;
    private Integer width, height;
    private double mouseX;
    private double mouseY;
    private Integer pixelSize = 4;

    private List<BoardPoint> parts = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception{
        width = 1280;
        height = 720;
        this.initBoard();
        var scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Generates the content we are going to view. Needs to update the board
     * and translate that into pixels.
     * @return
     */
    private Parent createContent(){

        var canvas = new Canvas(1280, 720);
        g = canvas.getGraphicsContext2D();
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                onUpdate();
            }
        };
        timer.start();
        var pane = new Pane(canvas);
        pane.setPrefSize(1280, 720);




        return pane;
    }

    /**
     * Updates the canvas to reflect the new board.
     */
    private void onUpdate(){
        
        parts = translateBoardGFX();
        g.clearRect(0, 0, 1280, 720); // Clears the frame
        parts.forEach(p -> {
            g.setFill(Color.BLUE);
            g.fillRect(p.x * pixelSize, p.y * pixelSize, pixelSize, pixelSize);
        });
        board = board.generateNextGeneration(); // Update the board
    }

    /**
     * This will translate a given board into a displayable form.
     */
    private ArrayList<BoardPoint> translateBoardGFX(){
        ArrayList<BoardPoint> cords = new ArrayList<BoardPoint>();

        for(int y = 0; y < height/pixelSize; y++){
            for(int x = 0; x < width/pixelSize; x++){
                if(board.board.get(y).get(x)) cords.add(new BoardPoint(x, y));
            }
        }

        return cords;
    }


    private void initBoard(){
        ArrayList<BoardPoint> cords = new ArrayList<BoardPoint>();
        // cords.add(new BoardPoint(1, 1));
        cords.add(new BoardPoint(23, 64));
        cords.add(new BoardPoint(24, 65));
        cords.add(new BoardPoint(23, 66));

        cords.add(new BoardPoint(24, 66));

        cords.add(new BoardPoint(25, 65));
        board = new Board(width/pixelSize, height/pixelSize, cords);
        
    }


    public static void main(String[] args){
        // TODO: put board initalization here.
        launch(args);
    }
}
