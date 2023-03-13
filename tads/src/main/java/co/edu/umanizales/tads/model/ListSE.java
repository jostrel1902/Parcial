package co.edu.umanizales.tads.model;

import java.util.*;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

@Data
public class ListSE {
    private Node head;
    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid){
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid){
        if(head !=null)
        {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        else {
            head = new Node(kid);
        }
    }

    // metodo para añadir en posición
    public void addInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i=0;1<pos;i++){
            temp= temp.getNext();

        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }

    // metodo para eliminar niños recibiendo el id
    public void deleteKid(String Identification, int posicion)
    {Node temp= head;
        if(head!=null)
        {
            if(head.getData().equals(Identification))
            {
                head=null;
            }
            head=temp.getNext();
            // else (me arroja error)
            {
                for (int i = 0; i < posicion-1; i++)
                {
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext());
            }
        }
        else
        {
            head = null;
        }
    }

    // Código problema #9
    public static void main(String[] args) {

        // Crear una lista de objetos "Niño" y agregar varios niños a la lista.
        ArrayList<Niño> listaNinos = new ArrayList<>();
        listaNinos.add(new Niño("Lucas", 4));
        listaNinos.add(new Niño("Pedro", 6));
        listaNinos.add(new Niño("Juan", 9));
        listaNinos.add(new Niño("Maria", 10));
        listaNinos.add(new Niño("Ana", 5));
        listaNinos.add(new Niño("Luis", 8));
        listaNinos.add(new Niño("Sofia", 4));
        listaNinos.add(new Niño("Santiago", 6));
        listaNinos.add(new Niño("Valentina", 4));

        // Crear un arreglo "rangoEdades" con los rangos y las cantidades correspondientes.
        // El rango "n" corresponde a "m" cantidad de niños.
        int[][] rangoEdades = {{1, 5, 4}, {4, 6, 5}, {7, 9, 3}, {10, 12, 3}, {13, 15, 0}};

        // Crear una lista separada por rangos de edades para contabilizar los niños de cada rango.
        ArrayList<Integer> ninosPorRango = new ArrayList<>();

        // Recorrer la lista de niños y para cada niño:
        for (Niño nino : listaNinos) {
            // Obtener su edad.
            int edad = nino.getEdad();

            // Comparar la edad con los rangos de edades del arreglo "rangoEdades".
            for (int[] rango : rangoEdades) {
                int edadMin = rango[0];
                int edadMax = rango[1];

                // Si la edad del niño está dentro de un rango de edad, agregarlo a la lista separada correspondiente a ese rango.
                if (edad >= edadMin && edad <= edadMax) {
                    int cantidad = rango[2];
                    ninosPorRango.add(cantidad + 1);
                }
            }
        }

        // Ordenar la lista separada por rangos de edades de menor a mayor.
        Collections.sort(ninosPorRango);

        // Imprimir un informe para cada rango de edades mostrando el número de niños en ese rango.
        for (int i = 0; i < rangoEdades.length; i++) {
            int edadMin = rangoEdades[i][0];
            int edadMax = rangoEdades[i][1];
            int cantidad = ninosPorRango.get(i);

            System.out.println("Rango de edad: " + edadMin + "-" + edadMax + " años. Cantidad de niños: " + cantidad);
        }
    }

    static class Niño {
        private String nombre;
        private int edad;

        public Niño(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }
    }

    //Codigo #2

    public class Nino {
        private String nombre;
        private int edad;
        private String genero;

        // Se define el constructor de la case "Nino" con tres parámetros:
        public Nino(String nombre, int edad, String genero) {
            this.nombre = nombre; // Se asigna el valor del parámetro "nombre" al atributo "nombre"
            this.edad = edad;  // Se asigna el valor del parámetro "edad" al atributo "edad"
            this.genero = genero; // Se asigna el valor del parámetro "genero" al atributo "genero"
        }

        // Definimos el método "getNombre" que devuelve el valor del atributo "nombre"
        public String getNombre() {
            return nombre;
        }

        // Definimos el método "getEdad" que devuelve el valor del atributo "edad"
        public int getEdad() {
            return edad;
        }

        // Definimos el método "getGenero" que devuelve el valor del atributo "genero"
        public String getGenero() {
            return genero;
        }

        // Se define el método main de la clase y se crea una nueva lista de objetos de la clase Nino llamada "niños" utilizando el ArrayList. Luego agregamos 4 objetos de la clase Nino a la lista "niños" con diferentes valores
        public void main(String[] args) {
            List<Nino> ninos = new ArrayList<>();
            ninos.add(new Nino("Juan", 8, "masculino"));
            ninos.add(new Nino("Pedro", 7, "masculino"));
            ninos.add(new Nino("Maria", 6, "femenino"));
            ninos.add(new Nino("Ana", 9, "femenino"));

            List<Nino> ninosMasculinos = new ArrayList<>(); // Nueva lista de objetos llamada "ninosMasculinos" uitlizando ArrayList
            List<Nino> ninosFemeninos = new ArrayList<>(); // Nueva lista de objetos llamda "ninosFemeninos" utilizando ArrayList

            for (Nino nino : ninos) { //Bucle para recorrer la lista "ninos"
                if (nino.getGenero().equalsIgnoreCase("masculino")) { // Se comprueba si es masculino
                    ninosMasculinos.add(nino); // Si es asi, se agrega el objeto a la lista de "ninosMasculinos"
                } else { // Si no es asi, se agrega el objeto a la lista de "ninosFemeninos"
                    ninosFemeninos.add(nino);
                }
            }

            List<Nino> ninosOrdenados = new ArrayList<>();
            ninosOrdenados.addAll(ninosMasculinos); // Agregar todos los niños al inicio
            ninosOrdenados.addAll(ninosFemeninos); // Agregar todas las niñas al final

            for (Nino nino : ninosOrdenados) {
                System.out.println(nino.getNombre() + " - " + nino.getGenero());
            }
        }
    }
}