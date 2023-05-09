package co.edu.umanizales.tads.model;

import java.util.*;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;


@Data
public class ListSE {
    private Node head;
    private int size;
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
        size ++;
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

    //get in pos by id

    public int getPosByID(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum++;
                temp.getNext();

            }

        }
        return acum;
    }

    // #1 Invertir lista
    public void invert() {
        if(this.head != null) {
            ListSE listCp= new ListSE();
            Node temp= this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp= temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
    // #2 Adicionar al inicio
    public void orderBoysToStart(){
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }

    }
    public void addToStart (Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node (kid);
        }
        size++;
    }

    // #3 Intercalar niño y niña

    public void IntercaleBoyandGirl(){
        ListSE listM = new ListSE();
        ListSE listF = new ListSE();
        Node temp = head;
        while (temp != null){
            if (temp.getData().getGender()== 'M'){}
            listM.add(temp.getData());
        }
        if(temp.getData().getGender()== 'F'){
            listF.add(temp.getData());
        }
    }

    // #4 Eliminar por edad dada

    public void DeleteByAge(Byte age){
        Node temp = head;

        ListSE listSE1 = new ListSE();
        //Se recorre la lista original y se crea una lista copia

        while (temp != null){
            if (temp.getData().getAge() != age){
                listSE1.addToStart(temp.getData());
                temp.getNext();
            }
        }
        this.head = listSE1.getHead();
    }

    // #5 Promedio de edad de los niños de la lista

    public float averageAge(){
        if (head != null){
            Node temp = head;
            int count = 0;
            int ages = 0;
            while (temp.getNext()!=null){
                count++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages/count;
        }else{
            return (int) 0;
        }
    }
    // #7 Definir que adelante un numero de posiciones
    public void gainPositionKid(String id, int gain){
        Node temp = head;
        int sum=0;
        ListSE listSECp = new ListSE();
                if(head != null){
                    while(temp !=null){
                        if(!temp.getData().getIdentification().equals(id)){
                            listSECp.add(temp.getData());
                            temp = temp.getNext();
                        }else{
                            temp = temp.getNext();
                        }
                    }
                }
        sum=getPosByID(id)- gain;
        listSECp.addInpos(getKidByid(id),sum);
        this.head = listSECp.getHead();
    }
    // #8 Perder posiciones
    public void LosePositionKid(String id, int lose){
        Node temp = head;
        lose = 0;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null){
            while (temp != null && !temp.getData().getIdentification().equals(id)){
                listSE1.add(temp.getData());
            }
        }
        sum = lose + getPosByID(id);
        listSE1.addInpos(getKidByid(id), sum);
    }

    // #9 Obtener informe de niños por rango de edades
    public String reportByAge(){
        int quan1 = 0;
        int quan2 = 0;
        int quan3 = 0;
        int quan4 = 0;
        int quan5 = 0;
        Node temp = this.head;
        if (this.head != null){
            if (temp.getData().getAge() >= 0 && temp.getData().getAge() <= 3){
                quan1++;
            } else if (temp.getData().getAge() > 2 && temp.getData().getAge() <= 6){
                quan2++;
            } else if (temp.getData().getAge() >6 && temp.getData().getAge() <= 9){
                quan3++;
            } else if (temp.getData().getAge() >9 && temp.getData().getAge() <= 12){
                quan4++;
            } else if (temp.getData().getAge() >12 && temp.getData().getAge() <= 15){
                quan5++;
            }
            temp = temp.getNext();
        }
        return "niños entre 0 y 3 años"+quan1+"niños entre 4 y 6 años"+quan2+"niños entre 7 y 9 años"+quan3+
                "niños entre 7 y 9 años"+quan4+"niños entre 10 y 12 años"+quan5;
    }
    //#10 Enviar al final
    public void addToFinalNameChar(String letter) {
        ListSE listSECp = new ListSE();
        Node temp = head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter) != temp.getData().getName().startsWith(letter)) {
                } else {
                    listSECp.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listSECp.getHead();
        }
    }

        public void changeExtremes(){
            if (this.head != null && this.head.getNext() != null) {
                Node temp = this.head;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                Kid copy = this.head.getData();
                this.head.setData(temp.getData());
                temp.setData(copy);
            }
        }



    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null){
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }

        }
        return count;
    }

    public int verifyId(Kid kid){
        Node temp = this.head;
        Boolean found = false;
        while (temp !=null) {
            if (temp.getData().getIdentification().equals(kid.getIdentification())){
                found=true;
                break;
            }
            temp = temp.getNext();
        }
        return found ?1 :0;
    }
    //metodo para añadir nuevo nodo y nuevo niño en una posición
    public void addInpos(Kid kid, int pos) {
        Node temp= head;
        for(int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node (kid);
        temp.setNext(newNode);
    }

    //metodo para eliminar niños por id
    public void deleteByIdentification(String identification){
        Node currentNode = head;
        Node prevNode = null;
        while (currentNode != null && currentNode.getData().getIdentification()!= identification){
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if(currentNode != null){
            if(prevNode == null){
                head = currentNode.getNext();
            }else{
                prevNode.setNext(currentNode.getNext());
            }
        }
    }
    // adelantar en posición
    public void advanceInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i= 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }
    //añadir por posición
    public void addbyposition(Kid kid, int position){
        Node nuevoNodo = new Node(kid);
        if(position ==0){
            nuevoNodo.setNext(head);
            head = nuevoNodo;
        }else{
            Node actual = head;
            for (int i= 1; i < position - 1; i++){
                actual = actual.getNext();
            }
            nuevoNodo.setNext(actual.getNext());
            actual.setNext(nuevoNodo);
        }
    }
    //metodo para obtener la lista de ciudad y cuantos niños hay
    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report){
        if (head != null){
            Node temp = this.head;
            while (temp != null){
                if (temp.getData().getAge() > age){
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }
    private int getPosByIdentification (String identification){return 0;}

    //get Kidbyid
    public Kid getKidByid(String id){
        Node temp = head;
        if (head != null) {
            while (temp != null){
                temp.getNext();
                while (!temp.getData().getIdentification().equals(id)) {
                    temp.getNext();
                }
                temp.getData();
            }
        }
        Kid kid = new Kid (temp.getData().getIdentification(),temp.getData().getName(),temp.getData().getAge());
        return kid;
    }
}

