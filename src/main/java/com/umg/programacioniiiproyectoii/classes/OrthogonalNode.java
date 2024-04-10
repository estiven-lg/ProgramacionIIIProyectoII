package com.umg.programacioniiiproyectoii.classes;

public class OrthogonalNode {

    private OrthogonalNode left, right, up, down;
    private int idX, idY;
    private Vehiculo value;

    public OrthogonalNode(Vehiculo value, int idX, int idY) {
        this.value = value;
        this.idX = idX;
        this.idY = idY;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }

    public OrthogonalNode getLeft() {
        return left;
    }

    public void setLeft(OrthogonalNode left) {
        this.left = left;
    }

    public OrthogonalNode getRight() {
        return right;
    }

    public void setRight(OrthogonalNode right) {
        this.right = right;
    }

    public OrthogonalNode getUp() {
        return up;
    }

    public void setUp(OrthogonalNode up) {
        this.up = up;
    }

    public OrthogonalNode getDown() {
        return down;
    }

    public void setDown(OrthogonalNode down) {
        this.down = down;
    }

    public OrthogonalNode getRowHead(int rowIndex) {

        if (this.idY == rowIndex) {
            return this;
        }

        if (this.idY < rowIndex || this.down == null) {
            return null;
        }

        return this.down.getRowHead(rowIndex);
    }

    public OrthogonalNode getColHead(int colIndex) {
      if (this.idX == colIndex) {
            return this;
        }

        if (this.idY < colIndex || this.down == null) {
            return null;
        }

        return this.down.getRowHead(colIndex);
    }

}
