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
public class MatrixState {
    private boolean[] matrix;
    private final int sizeX, sizeY;
    
    public MatrixState(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.matrix = new boolean[sizeX * sizeY];
    }
    
    public boolean[] getMatrix() {
        return this.matrix;
    }
    public int getSizeX() {
        return this.sizeX;
    }
    public int getSizeY() {
        return this.sizeY;
    }

    public void toggle(int x, int y) {
        setCell(x, y, !getCell(x, y));
    }
    
    public boolean getCell(int x, int y) {
        int i = y * sizeX + x;
        return matrix[i];
    }
    
    public void setCell(int x, int y, boolean value) {
        int i = y * sizeX + x;
        matrix[i] = value;
    }
    
    public void setMatrix(boolean[] matrix) {
        this.matrix = matrix;
    }
}
