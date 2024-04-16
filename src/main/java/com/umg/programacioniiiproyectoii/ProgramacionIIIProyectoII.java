
package com.umg.programacioniiiproyectoii;

import com.umg.programacioniiiproyectoii.classes.OrthogonalNode;

public class ProgramacionIIIProyectoII {

    public static void main(String[] args) {
        OrthogonalNode list = new OrthogonalNode();
        list.insert(null, 1, 2);
        list.insert(null, 5, 2);
        list.insert(null, 5, 3);
 
        // list.printOrthogonalNode();

        list.insert(null, 4, 2);
        list.insert(null, 4, 5);
  


        list.remove(4, 2);
        // list.remove(5, 2);
        // list.remove(1, 2);
        // System.out.println("------------");
        // list.remove(5, 2);

        list.print();
    }
}
