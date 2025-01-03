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
    private double mouseX;
    private double mouseY;

    private List<BoardPoint> parts = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception{
        var scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates the content we are going to view
     * @return
     */
    private Parent createContent(){

        ArrayList<BoardPoint> pixels = new ArrayList<BoardPoint>();

        pixels.add(new BoardPoint(95, 95));

        Board board = new Board(1280, 720, pixels);

        var canvas = new Canvas(1280, 720);
        g = canvas.getGraphicsContext2D();
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                onUpdate();
            }
        };

        timer.start();
        var pane = new Pane(canvas);
        pane.setPrefSize(1280, 720);

        return pane;
    }

    // Draws the canvas on each update
    private void onUpdate(){
        g.clearRect(0, 0, 1280, 720); // Clears the frame
        parts.forEach(p -> {
            g.setFill(Color.BLUE);
            g.fillOval(p.x, p.y, 100, 100);
        });
    }



    public static void main(String[] args){
        launch(args);
    }
}
