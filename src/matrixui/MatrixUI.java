/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author rkkruse
 */
public class MatrixUI extends Application {
    private static final int GRID_SIZE_X = 20;
    private static final int GRID_SIZE_Y = 20;
    private static final int CELL_W = 20;
    private static final int CELL_H = 20;

    private MatrixState state;
    private MatrixPainter painter;
    private MatrixLoop loop;
    
    @Override
    public void start(Stage primaryStage) {
        setupUI(primaryStage);
        
        loop = new MatrixLoop(state, painter);
        
        new Thread(loop).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    private void setupUI(Stage primaryStage) {        
        // a UI component to draw on
        Canvas canvas = new Canvas(
                GRID_SIZE_X * CELL_W, 
                GRID_SIZE_Y * CELL_H);
        
        // an object that knows the game state
        this.state = new MatrixState(
                GRID_SIZE_X, 
                GRID_SIZE_Y);        
        
        // a class that draws the game state to the UI
        this.painter = new MatrixPainter(
                canvas.getGraphicsContext2D(), // where it draws
                CELL_W, CELL_H,                // size of cells
                new Color[] { Color.GREEN, Color.RED }); // pallette
        
        // add canvas to scene
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, GRID_SIZE_X * CELL_W, GRID_SIZE_Y * CELL_H);
        primaryStage.setTitle("MatrixUI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // setup UI handlers
        canvas.setOnMouseClicked(e -> {
            int x = (int)(e.getX()/CELL_W);
            int y = (int)(e.getY()/CELL_H);
            state.toggle(x,y);
        });
        
        scene.setOnKeyPressed(e -> {
            loop.togglePause();
        });
        
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });        
    }
}
