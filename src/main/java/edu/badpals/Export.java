package edu.badpals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Export {

    public static void exportXML(Document doc) {
        try {
            File f=new File("ref/profesor.xml");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(f);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            System.out.println("¡Error! No se ha podido llevar a cabo la transformación.");
        }
    }

    public static Document generateDOM() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("profesores");
            doc.appendChild(rootElement);

            ArrayList<Profesor> profesores = Profesor.cargarprofesores();

            for (Profesor profesor : profesores) {
                Element profesorElement = doc.createElement("profesor");

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(profesor.getNombre()));
                profesorElement.appendChild(nombre);

                Element dniProfesor = doc.createElement("dni");
                dniProfesor.appendChild(doc.createTextNode(profesor.getDni()));
                profesorElement.appendChild(dniProfesor);

                Element edadProfesor = doc.createElement("edad");
                edadProfesor.appendChild(doc.createTextNode(profesor.getEdad()));
                profesorElement.appendChild(edadProfesor);

                Element nomDepartamento = doc.createElement("departamento");
                nomDepartamento.appendChild(doc.createTextNode(profesor.getDepartamento()));
                profesorElement.appendChild(nomDepartamento);

                rootElement.appendChild(profesorElement);
            }
            return doc;
        } catch (Exception ex) {
            System.err.println("Error al crear el documento DOM: " + ex.getMessage());
            return null;
        }
    }

    public static void exportJSON() {
        exportXML(generateDOM());
        try {
            JsonNode node = new XmlMapper().readTree(new File("ref/profesor.xml"));
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("ref/profesor.json"), node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}