package edu.badpals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Profesor {
    private String nombre;
    private String dni;
    private String edad;
    private String departamento;

    public Profesor(String nombre, String dni, String edad, String departamento) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", departamento='" + departamento + '\'' +
                '}';
    }

    public static ArrayList<Profesor> cargarprofesores(){
        try (BufferedReader lector = new BufferedReader(new FileReader("ref/Profesores.txt"))) {
            String linea;
            ArrayList<Profesor> profesores = new ArrayList<>();
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                Profesor profesor = new Profesor(datos[0], datos[1], datos[2], datos[3]);
                profesores.add(profesor);
            }
            return profesores;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
