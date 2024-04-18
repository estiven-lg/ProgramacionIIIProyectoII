package com.umg.programacioniiiproyectoii.classes;

import java.util.Stack;

public class OrthogonalNode {

    private OrthogonalNode left, right, up, down;
    private int idX, idY;
    private Vehicle value;

    private OrthogonalNode(Vehicle value, int idX, int idY) {
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
        this.idX = -1;
        this.idY = -1;
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

    public Vehicle getValue() {
        return this.value;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }

    /**
     * search left to right a node with the id X equals to XId
     * 
     * @param XId id in X of node to search
     * @return the node with id x equal XId or null if are not found
     */
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

    /**
     * search up to down a node with the id Y equals to YId
     * 
     * @param XId id in Y of node to search
     * @return the node with id Y equal IdY or null if are not found
     */
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

    /**
     * insert a node basing ourselves idx and link left and right nodes
     * 
     * @param node node to insert
     */
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

    /**
     * insert a node basing ourselves idy and link up and down nodes
     * 
     * @param node node to insert
     */
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

    /**
     * remove the node that idX be equal to node param and update the left and right
     * nodes
     * 
     * @param node node to remove
     */
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

    /**
     * Insert a new value in a especific position
     * 
     * @param value value to insert
     * @param x     posion in x axis
     * @param y     posion in y axis
     */
    public void insert(Vehicle value, int x, int y) {
        OrthogonalNode newNode = new OrthogonalNode(value, x, y);

        if (this.getYById(y) == null) {
            this.insertY(new OrthogonalNode(null, -1, y));
        }
        this.getYById(y).insertX(newNode);

        if (this.getXById(x) == null) {
            this.insertX(new OrthogonalNode(null, x, -1));
        }
        this.getXById(x).insertY(newNode);
    }

    /**
     * remove a value in a especific position
     * 
     * @param x posion in x axis
     * @param y posion in y axis
     */
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

    /**
     * get a node in a specific position
     * 
     * @param x posion in x axis
     * @param y posion in y axis
     * @return
     */
    public OrthogonalNode get(int x, int y) {
        try {
            return this.getXById(x).getYById(y);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * compare the value attributes with the param text
     * 
     * @param compareText to to compare
     * @return if the text and the value make match
     */
    private boolean match(String compareText) {
        if (this.getValue() == null) {
            return false;
        }

        if (this.getValue().getPlate() != null && this.getValue().getPlate().contains(compareText)) {
            return true;
        }

        if (this.getValue().getOwner() != null && this.getValue().getOwner().contains(compareText)) {
            return true;
        }

        if (this.getValue().getColor() != null && this.getValue().getColor().contains(compareText)) {
            return true;
        }

        if (this.getValue().getLine() != null && this.getValue().getLine().contains(compareText)) {
            return true;
        }

        if (this.getValue().getModel() != null && this.getValue().getModel().contains(compareText)) {
            return true;
        }

        return false;

    }

    /**
     * search in the linkend nodes to search nodes
     * 
     * @param search texto to search nodes
     * @return list of node that make match
     */
    public OrthogonalNode[] find(String search) {
        Stack<OrthogonalNode> nodeList = new Stack<OrthogonalNode>();

        OrthogonalNode x = this;
        OrthogonalNode y = null;

        while (x != null) {
            y = x;
            while (y != null) {
                if (y.match(search)) {
                    nodeList.push(y);
                }
                y = y.getRight();
            }

            x = x.getDown();
        }

        return nodeList.toArray(OrthogonalNode[]::new);
    }

    @Override
    public String toString() {

        if (this.idX == -1 && this.idY == -1) {
            return ("[i,j]");
        }

        if (this.idX == -1) {
            return ("[i," + this.idY + "]");
        }

        if (this.idY == -1) {
            return ("[" + this.idX + ",j]");
        }

        return ("(" + this.idX + "," + this.idY + ")");

    }

    /**
     * show a representation of Ortagonal node
     * 
     * @return texto to print in console
     */
    public String toStringR() {
        OrthogonalNode y = this;
        String text = "";

        while (y != null) {
            OrthogonalNode x = this;
            while (x != null) {
                if (this.get(x.getIdX(), y.getIdY()) != null) {
                    text += this.get(x.getIdX(), y.getIdY()) + "-";
                } else {
                    text += "------";
                }
                x = x.getRight();
            }

            x = this;
            text += "\n";

            while (x != null) {

                text += "  |   ";

                x = x.getRight();
            }

            text += "\n";

            y = y.getDown();
        }

        return text;
    }

    /**
     * show a representation of state the matrix using plate
     * 
     * @return texto to print in console
     */
    public String toStringPlates(int numCol, int numRow) {

        String text = "";

        for (int y = 0; y < numCol; y++) {
            for (int x = 0; x < numRow; x++) {
                text += "<<(" + y + x + ")>>";
            }
            text += "\n";
            for (int x = 0; x < numRow; x++) {

                OrthogonalNode vehicleNode = this.get(x, y);
                if (vehicleNode != null && vehicleNode.getValue() != null) {
                    text += vehicleNode.getValue().getPlate() + "-";
                } else {
                    text += " vacio -";
                }
            }

            text += "\n";

        }

        return text;

    }
}
