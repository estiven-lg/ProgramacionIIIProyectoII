package com.umg.programacioniiiproyectoii.classes;

public class OrthogonalNode {

    private OrthogonalNode left, right, up, down;
    private int idX, idY;
    private Vehiculo value;

    private OrthogonalNode(Vehiculo value, int idX, int idY) {
        this.value = value;
        this.idX = idX;
        this.idY = idY;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }

    public OrthogonalNode() {
        this.value = null;
        this.idX = 0;
        this.idY = 0;
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

    public Vehiculo getValue() {
        return this.value;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }

    private OrthogonalNode getXById(int XId) {
        OrthogonalNode node = this;

        while (node.getRight() != null && node.getIdX() < XId) {
            node = node.getRight();
        }

        if (XId == node.getIdX()) {
            return node;
        }
        return null;
    }

    private OrthogonalNode getYById(int YId) {
        OrthogonalNode node = this;

        while (node.getDown() != null && node.getIdY() < YId) {
            node = node.getDown();
        }

        if (YId == node.getIdY()) {
            return node;
        }

        return null;
    }

    private void insertX(OrthogonalNode node) {
        OrthogonalNode leftNode = this, rightNode = this.getRight();

        while (leftNode.getRight() != null && rightNode.getIdX() < node.idX) {
            leftNode = rightNode;
            rightNode = rightNode.getRight();
        }

        leftNode.setRight(node);
        node.setLeft(leftNode);

        if (rightNode != null) {
            node.setRight(rightNode);
            rightNode.setLeft(node);
        }

    }

    private void insertY(OrthogonalNode node) {
        OrthogonalNode upNode = this, downNode = this.getDown();

        while (downNode != null && downNode.getIdY() < node.idY) {
            upNode = downNode;
            downNode = downNode.getDown();
        }

        upNode.setDown(node);
        node.setUp(upNode);

        if (downNode != null) {
            node.setDown(downNode);
            downNode.setUp(node);
        }
    }

    private void removeX(OrthogonalNode node) {
        if (node.getRight() != null) {
            node.getLeft().setRight(node.getRight());
            node.getRight().setLeft(node.getLeft());
        } else {
            node.getLeft().setRight(null);
        }
    }

    private void removeY(OrthogonalNode node) {
        if (node.getDown() != null) {
            node.getUp().setDown(node.getDown());
            node.getDown().setUp(node.getUp());
        } else {
            node.getUp().setDown(null);
        }
    }

    public void insert(Vehiculo value, int x, int y) {
        OrthogonalNode newNode = new OrthogonalNode(value, x, y);

        if (this.getYById(y) == null) {
            this.insertY(new OrthogonalNode(null, 0, y));
        }
        this.getYById(y).insertX(newNode);

        if (this.getXById(x) == null) {
            this.insertX(new OrthogonalNode(null, x, 0));
        }
        this.getXById(x).insertY(newNode);
    }

    public void remove(int x, int y) {
        OrthogonalNode deletedNode = this.getXById(x).getYById(y);

        this.getYById(y).removeX(deletedNode);
        if (this.getYById(y).getRight() == null) {
            this.removeY(this.getYById(y));
        }

        this.getXById(x).removeY(deletedNode);
        if (this.getXById(x).getDown() == null) {
            this.removeX(this.getXById(x));
        }

    }

    public OrthogonalNode get(int x, int y) {
        try {
            return this.getXById(x).getYById(y);
        } catch (Exception e) {
            return null;
        }
    }

    public void print() {
        OrthogonalNode y = this;

        while (y != null) {
            OrthogonalNode x = this;
            while (x != null) {
                if (this.get(x.getIdX(), y.getIdY()) != null) {
                    System.out.print(this.get(x.getIdX(), y.getIdY()) + "-");
                } else {
                    System.out.print("------");
                }
                x = x.getRight();
            }

            x = this;
            System.out.println("");

            while (x != null) {

                System.out.print("  |   ");

                x = x.getRight();
            }

            System.out.println("");
            y = y.getDown();
        }
    }


    public void printPlate() {
        OrthogonalNode y = this;

        while (y != null) {
            OrthogonalNode x = this;
            while (x != null) {
                if (this.get(x.getIdX(), y.getIdY()) != null) {
                    System.out.print(this.get(x.getIdX(), y.getIdY()) + "-");
                } else {
                    System.out.print("------");
                }
                x = x.getRight();
            }

            x = this;
            System.out.println("");

            while (x != null) {

                System.out.print("  |   ");

                x = x.getRight();
            }

            System.out.println("");
            y = y.getDown();
        }
    }

    private boolean match(String compareText) {
        if (this.getValue() == null) {
            return false;
        }

        if (this.getValue().getPlate().contains(compareText)) {
            return true;
        }

        if (this.getValue().getOwner().contains(compareText)) {
            return true;
        }

        if (this.getValue().getColor().contains(compareText)) {
            return true;
        }

        if (this.getValue().getLine().contains(compareText)) {
            return true;
        }

        if (this.getValue().getModel().contains(compareText)) {
            return true;
        }

        return false;

    }

    @Override
    public String toString() {

        if (this.idX == 0 || this.idY == 0) {
            return ("[" + this.idX + "," + this.idY + "]");
        }

        return ("(" + this.idX + "," + this.idY + ")");

    }
}
