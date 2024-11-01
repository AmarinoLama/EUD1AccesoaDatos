package edu.badpals;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProfesorManage {

    public static String nombre = "";
    public static String dni = "";
    public static String edad = "";
    public static String departamento = "";

    public static void getInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el nombre del profesor: ");
        nombre = sc.nextLine();

        do {
            System.out.println("Escribe el dni del profesor: (8 números y una letra mayúscula) ");
            dni = sc.nextLine();
        } while (!checkDni(dni));


        System.out.println("Escribe la edad del profesor: ");
        edad = sc.nextLine();

        do {
            System.out.println("Escribe el departamento del profesor: (Informática, Ciencias, Matemáticas, Lengua, Literatura ");
            departamento = sc.nextLine();
        } while (!checkDepartament(departamento));
    }

    public static void addProfesor() {

        getInfo();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ref/Profesores.txt", true))) {
            writer.write("\n" + nombre + ";" + dni + ";" + edad + ";" + departamento);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void removeProfesor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime el DNI del profesor que quieres borrar");
        String dni = sc.nextLine();

        ArrayList<String> profesores = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader("ref/Profesores.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.contains(dni)) {
                    profesores.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ref/Profesores.txt"))) {
            for (String profesor : profesores) {
                writer.write(profesor);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static boolean checkDni(String dni) {
        try {
            String alfabet = "TRWAGMYFPDXBNJZSQVHLCKE";
            Integer number = Integer.parseInt(dni.substring(0, 8));
            Character letter = dni.charAt(8);
            if (alfabet.charAt(number % 23) == letter) {
                return true;
            } else {
                return false;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("El dni debe contener 8 números y una letra mayúscula");
            return false;
        }
    }

    public static boolean checkDepartament(String departamento) {
        ArrayList<String> departaments = new ArrayList<>(Arrays.asList("Informática", "Ciencias", "Matemáticas", "Lengua", "Literatura"));
        return departaments.contains(departamento);
    }
}
