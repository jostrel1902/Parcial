package co.edu.umanizales.tads.model;

import lombok.Data;
import co.edu.umanizales.tads.controller.dto.PetDTO;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void addPet(Pet pet) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            NodeDE newNode = new NodeDE(pet);
            temp.setNext(newNode);
            temp.setPrevious(temp);
        } else {
            this.head = new NodeDE(pet);
        }
        size++;
    }

    public int getPosByPhone(String phone) {
        NodeDE temp = this.head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getOwnerPhone().equals(phone)) {
                acum = acum + 1;
                temp = temp.getNext();
            }
        }
        return acum;
    }

    public void putPetsToBeginning() {
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listDE1.addPetToBeginning(temp.getData());
                } else if (temp.getData().getGender() == 'F') {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }

    public void addPetToBeginning(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (this.head != null) {
            this.head.setPrevious(newNode);
        }
        this.head = newNode;
        size++;
    }

    public void deletePet(String phone) {
        NodeDE empt = null;
        NodeDE temp = head;

        while (temp != null && !temp.getData().getOwnerPhone().equals(phone)) {
            empt = temp;
            temp = temp.getNext();
        }

        if (temp == null) {
            return;
        }

        if (empt == null) {
            head = temp.getNext();
        } else {
            empt.setNext(temp.getNext());
        }
        if (temp.getNext() != null) {
            temp.getNext().setPrevious(empt);
        }
        size--;
    }

    public void addInPos(Pet pet, int pos1) {
        NodeDE temp = head;
        NodeDE newNode = new NodeDE(pet);
        if (pos1 >= size)
            addPet(pet);
        if (pos1 == 0) {
            addPetToBeginning(pet);
        }
        for (int i = 0; temp.getNext() != null && i < pos1 - 1; i++) {
            temp = temp.getNext();
        }
        newNode.setNext((temp.getNext()));
        temp.setNext(temp.getNext());
        if (newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        }
        newNode.setPrevious(temp);
        size++;
    }

    public void invertList() {
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                listDE1.addPetToBeginning(temp.getData());
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }

    public void orderByGender() {
        ListDE listDE1 = new ListDE();
        int sum = 0;
        NodeDE temp = head;
        if (head == null) {
            System.out.println("No hay datos");
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listDE1.addPetToBeginning(temp.getData());

                }
                temp = temp.getNext();
            }
        }
        temp = head;
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listDE1.addInPos(temp.getData(), sum);
                temp = temp.getNext();
                sum = sum + 2;
            } else {
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }


    public Pet getPetByPhone(String phone) {
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                temp = temp.getPrevious();
            }
            while (temp != null && !temp.getData().getOwnerPhone().equals(phone)) {
                temp = temp.getNext();
            }
        }
        Pet pet = new Pet(temp.getData().getAge(), temp.getData().getName(),
                temp.getData().getType(), temp.getData().getRace(), temp.getData().getLocation(), temp.getData().getGender(), temp.getData().getOwnerPhone());
        return pet;
    }

    public int verifyPhone(PetDTO petDTO) {
        NodeDE temp = this.head;
        boolean found = false;
        while (temp != null) {
            if (temp.getData().getOwnerPhone().equals(petDTO.getOwnerPhone())) {
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ? 1 : 0;
    }

    public void exchangeExtremes() {
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                temp = temp.getPrevious();
            }
            Pet copy = temp.getData();
            if (this.head.getNext() != null && this.head.getPrevious() != null) {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                }
                this.head.setData(temp.getData());
                temp.setData(copy);
            }
        }

    }


    public int getCounPetsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountPetsByLocationCodeAndMale(String code) {
        int male = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code) && temp.getData().getGender() == 'M') {
                    male++;
                }
                temp = temp.getNext();
            }
        }
        return male;
    }

    public int getCountPetsByLocationCodeAndFemale(String code) {
        int female = 0;
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code) && temp.getData().getGender() == 'F') {
                    female++;
                }
                temp = temp.getNext();
            }
        }
        return female;
    }

    public void earnPositions(String phone, int earn) {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getOwnerPhone().equals(phone)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = getPosByPhone(phone) - earn;
        listDE1.addInPos(getPetByPhone(phone), sum);
        this.head = listDE1.getHead();
    }

    public void losePositions(String phone, int lose) {
        NodeDE temp = head;
        int sum = 0;
        ListDE listDE1 = new ListDE();
        if (head != null) {
            while (temp != null) {
                if (!temp.getData().getName().equals(phone)) {
                    listDE1.addPet(temp.getData());
                    temp = temp.getNext();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = lose + getPosByPhone(phone);
        listDE1.addInPos(getPetByPhone(phone), sum);
        this.head = listDE1.getHead();
    }

    public void sendPetsToEndByChar(char user) {
        ListDE listDE1 = new ListDE();
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getName().charAt(0) != user) {
                    listDE1.addPetToBeginning(temp.getData());
                } else {
                    listDE1.addPet(temp.getData());
                }
                temp = temp.getNext();
            }
        }
        this.head = listDE1.getHead();
    }

    public double getAverageAge() {
        double averageAge = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                averageAge = averageAge + temp.getData().getAge();
                temp = temp.getNext();
            }
            averageAge = averageAge / size;
            return averageAge;
        }
        return averageAge;
    }

    public String generateReportByAge() {
        int quantity1 = 0;
        int quantity2 = 0;
        int quantity3 = 0;
        int quantity4 = 0;
        int quantity5 = 0;
        NodeDE temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() >= 0 && temp.getData().getAge() <= 3) {
                    quantity1++;
                } else if (temp.getData().getAge() > 3 && temp.getData().getAge() <= 6) {
                    quantity2++;
                } else if (temp.getData().getAge() > 6 && temp.getData().getAge() <= 9) {
                    quantity3++;
                } else if (temp.getData().getAge() > 9 && temp.getData().getAge() <= 12) {
                    quantity4++;
                } else if (temp.getData().getAge() > 12 && temp.getData().getAge() <= 15) {
                    quantity5++;
                }
                temp = temp.getNext();
            }
        }
        return " niños entre 0-3 años:" + quantity1 + " niños entre 4-6 años:" + quantity2 +
                " niños entre 7-9 años:" + quantity3 + " niños entre 10-12 años:" + quantity4 +
                " niños entre 13-15 años:" + quantity5;
    }

    public void deleteByAge(byte age) {
        NodeDE temp = this.head;
        ListDE listDE1 = new ListDE();
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    listDE1.addPetToBeginning(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listDE1.getHead();
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodeDE temp = this.head;
        sb.append("[");
        while (temp != null) {
            sb.append(temp.getData().toString());
            temp = temp.getNext();
            if (temp != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    public void deleteKamikaze(String id) {
        // Comprueba si el nodo de inicio no es nulo
        if (this.head != null) {
            // Declaración de variables
            NodeDE empt = null; // El nodo anterior al nodo actual
            NodeDE temp = this.head; // El nodo actual

            // Bucle while que recorre los nodos cuyo OwnerPhone coincide con id
            while (temp != null && temp.getData().getOwnerPhone().equals(id)) {
                empt = temp; // Guarda el nodo anterior al nodo actual
                temp = temp.getNext(); // Avanza al siguiente nodo
            }

            // Si empt sigue siendo nulo, significa que el nodo a eliminar es el nodo de inicio
            if (empt == null) {
                this.head = null; // Elimina el nodo de inicio
            }

            // Si empt no es nulo, significa que el nodo a eliminar está en alguna otra posición de la lista
            if (temp.getNext() != null) {
                // Establece los punteros "next" y "previous" del nodo anterior y siguiente al nodo a eliminar, respectivamente
                temp.setNext(temp); // Hace que el siguiente nodo apunte al nodo actual
                temp.setPrevious(temp); // Hace que el nodo actual apunte al siguiente nodo
                empt.setNext(empt.getNext().getNext()); // Salta el nodo a eliminar
                empt.setPrevious(empt.getPrevious().getPrevious()); // Sal


            }
        }
    }
}