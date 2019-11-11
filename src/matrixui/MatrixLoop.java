/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixui;

/**
 *
 * @author rkkruse
 */
public class MatrixLoop implements Runnable {
    private final MatrixPainter painter;
    private final MatrixState state;
    private final long intervalMS = 50L;
    private boolean running;
    private boolean paused;
    
    public MatrixLoop(MatrixState state, MatrixPainter painter) {
        this.state = state;
        this.painter = painter;
        this.running = true;
        this.paused = true;
    }
    
    @Override
    public void run() {
        long nowMS = System.currentTimeMillis();
        long thenMS = nowMS - intervalMS;
        
        while (running) {
            nowMS = System.currentTimeMillis();
            
            if (nowMS > thenMS + intervalMS) {
                // remember last game loop time
                thenMS = nowMS;
                
                // calls the update function - game logic goes there
                if (!paused) {
                    update();
                }
                
                // calls the draw function - render logic goes there
                draw();                
            }
            else {
                // wait until next interval
                try {                
                    Thread.sleep((thenMS + intervalMS) - nowMS);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void draw() {
        // Get the game state data
        boolean[] matrix = state.getMatrix();        
        painter.initFrame(state.getSizeX(), state.getSizeY(), paused);

        for (int i = 0; i < matrix.length; i ++) {
            if (matrix[i]) {
                int x = i % state.getSizeX();
                int y = i / state.getSizeX();
                
                // draw a square for every active cell
                painter.paintCell(x, y);
            }
        }
    }
    
    public void togglePause() {
        // Switch pause state to whatever the opposite is
        this.paused = !this.paused;
        System.out.println("Paused: " + this.paused);
    }
    
    void update() {
    }
}
