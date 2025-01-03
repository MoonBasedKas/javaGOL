package moon.gfx;

import java.awt.geom.Point2D;
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

public class gui extends Application{
    
    private GraphicsContext g;
    private double mouseX;
    private double mouseY;

    private List<Particle> parts = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception{
        var scene = new Scene(createContent());

        scene.setOnMouseMoved(value -> {
            mouseX = value.getX();
            mouseY = value.getY();
        });
        // stage.setScene(new Scene(createContent()));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates the content we are going to view
     * @return
     */
    private Parent createContent(){
        for(int i = 0; i < 72; i ++){
            for(int j = 0; j < 128; j++){
                parts.add(new Particle(j * 10, i * 10, Color.BLUE));
            }
        }

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
            g.setFill(p.color);
            g.fillOval(p.x - 1, p.y - 1, 2, 2);
        });
    }

    /**
     * local class
     */
    private static class Particle{
        double x;
        double y;
        Color color;

        
        Particle(double x, double y, Color color){
            this.x = x;
            this.y = y;
            this.color = color;
        }

        void update(Point2D cursor){
            var distance = cursor.distance(x,y);               
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
