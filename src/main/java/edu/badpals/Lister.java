package edu.badpals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Lister {

    public static ArrayList<String> getProfesorsShorted() {
        try (BufferedReader lector = new BufferedReader(new
                FileReader(Path.of("ref/Profesores.txt").toFile()));) {
            ArrayList<String> profesores = new ArrayList<>();
            String linea;
            while ((linea = lector.readLine()) != null) {
                profesores.add(Arrays.toString(linea.split(";")));
            }
            profesores.sort(Comparator.naturalOrder());
            return profesores;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void showProfesoresShorted() {
        ArrayList<String> profesoresShorted = getProfesorsShorted();
        for (String profesor : profesoresShorted) {
            System.out.println(profesor);
        }
    }
}