package edu.badpals;

import java.util.Scanner;

public class Menu {

    public static void showMenu() {

        System.out.println("=========== MENU =========== \n");
        System.out.println("Eliga una opción (numero):");
        System.out.println("1. Listar");
        System.out.println("2. Añadir");
        System.out.println("3. Borrar");
        System.out.println("4. Exportar");
        System.out.println("5. Salir");
    }

    public static void chooseOption() {
        Scanner sc = new Scanner(System.in);
        Integer exit = 0;
        while (exit == 0) {
            switch (sc.nextLine()) {
                case "1":
                    listar();
                    System.out.println("Profesores listados");
                    break;
                case "2":
                    ProfesorManage.addProfesor();
                    System.out.println("Profesor añadido");
                    break;
                case "3":
                    ProfesorManage.removeProfesor();
                    System.out.println("Profesor borrado");
                    break;
                case "4":
                    exportar();
                    System.out.println("Profesores exportados");
                    break;
                case "5":
                    exit = 1;
                    System.out.println("Saliendo ...");
                    break;
            }
            if (exit == 0) {
                showMenu();
            }
        }
    }

    public static void listar() {
        Lister.showProfesoresShorted();
    }

    public static void exportar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cómo lo quieres exportar? (XML o JSON - default JSON)");
        if (sc.nextLine() == "XML"){
            Export.exportXML(Export.generateDOM());
        } else {
            Export.exportJSON();
        }
    }
}