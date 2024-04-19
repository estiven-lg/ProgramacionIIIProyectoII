package com.umg.programacioniiiproyectoii;

import com.umg.programacioniiiproyectoii.classes.OrthogonalNode;
import com.umg.programacioniiiproyectoii.classes.Vehicle;

import java.util.Scanner;

public class ProgramacionIIIProyectoII {

    public static void main(String[] args) {
        OrthogonalNode vehicleList = new OrthogonalNode();
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        int opcion = -1, maxNumCol = 10, maxNumRow = 10;
        int parkingNum = 0;
        int x = 1, y = 1;


        while (!exit) {
            System.out.println("________________________");
            System.out.println("|que desea hacer?       |");
            System.out.println("|1. Ingresar Vehiculo   |");
            System.out.println("|2. Sacar Vehiculo      |");
            System.out.println("|3. Buscar Vehiculos    |");
            System.out.println("|4. Ver parqueos        |");
            System.out.println("|5. Ver matriz Ortagonal|");
            System.out.println("|_______________________|");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:

                    Vehicle newVehicle = new Vehicle();
                    while (!Vehicle.validarPlacas(newVehicle.getPlate())) {
                        System.out.println("Ingrese la placa del vehiculo");
                        newVehicle.setPlate(input.nextLine());

                        if (!Vehicle.validarPlacas(newVehicle.getPlate())) {
                            System.out.println("Placa no valida!!!!");
                        }
                    }

                    System.out.println("Ingrese el color del vehiculo");
                    newVehicle.setColor(input.nextLine());

                    System.out.println("Ingrese la linea del vehiculo");
                    newVehicle.setLine(input.nextLine());

                    System.out.println("Ingrese el modelo del vehiculo");
                    newVehicle.setModel(input.nextLine());

                    System.out.println("Ingrese el nombre de el propietario del vehiculo");
                    newVehicle.setOwner(input.nextLine());

                    do {
                        System.out.println("selecione un parqueo del 1 al " + maxNumCol * maxNumRow);
                        parkingNum = input.nextInt();
                        x = parkingNum % maxNumCol;
                        y = parkingNum / maxNumRow;

                        if (vehicleList.get(x, y) != null) {
                            System.out.println("Parqueo ocupado");
                        }

                        if (parkingNum > (maxNumCol * maxNumRow)) {
                            System.out.println("Numero de parqueo no valido");
                        }

                    } while (vehicleList.get(x, y) != null || parkingNum > (maxNumCol * maxNumRow));

                    vehicleList.insert(newVehicle, x, y);

                    break;

                case 2:

                    do {
                        System.out.println("selecione un parqueo del 1 al " + maxNumCol * maxNumRow);
                        parkingNum = input.nextInt();
                        x = parkingNum % maxNumCol;
                        y = parkingNum / maxNumRow;

                        if (vehicleList.get(x, y) == null) {
                            System.out.println("Parqueo ya esta vacio");
                        }

                        if (parkingNum > (maxNumCol * maxNumRow)) {
                            System.out.println("Numero de parqueo no valido");
                        }

                    } while (vehicleList.get(x, y) == null || parkingNum > (maxNumCol * maxNumRow));

                    vehicleList.remove(x, y);
                    break;
                case 3:
                    System.out.println("Ingrese una palabra clave para hacer la busqueda");
                    String search = input.nextLine();
                    OrthogonalNode[] foundNodesVehicle = vehicleList.find(search);

                    for (OrthogonalNode vehicleNode : foundNodesVehicle) {
                        System.out.println("-------------------");
                        System.out.println(vehicleNode.getValue());
                    }
                    break;

                case 4:
                    System.out.println("-------------------");
                    System.out.println(vehicleList.toStringPlates(maxNumCol, maxNumRow));
                    System.out.println("-------------------");
                    break;
                case 5:
                    System.out.println("-------------------");
                    System.out.println(vehicleList.toStringR());
                    System.out.println("-------------------");
                    break;

                default:
                    System.out.println("opcion no valida");
                    break;

            }

        }

    }
}
