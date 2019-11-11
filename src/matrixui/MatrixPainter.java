/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author rkkruse
 */
public class MatrixPainter {
    private final int w;
    private final int h;
    private final Color c1;
    private final Color c2;
    private final GraphicsContext context;
    
    public MatrixPainter(GraphicsContext context, int w, int h, Color[] palette) {
        this.w = w;
        this.h = h;
        this.c1 = palette[0];
        this.c2 = palette[1 % palette.length];
        this.context = context;
    }
    
    public void initFrame(int x, int y, boolean paused) {
        context.clearRect(0, 0, x * w, y * h);
        context.setFill(paused ? c2 : c1);
    }
    
    public void paintCell(int x, int y) {
        context.fillRect(x * w, y * h, w - 1, h - 1);
    }
}
