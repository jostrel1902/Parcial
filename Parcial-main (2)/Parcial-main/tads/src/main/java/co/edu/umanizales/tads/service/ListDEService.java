package co.edu.umanizales.tads.service;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {

    private ListDE pets;
    public ListDEService() {pets = new ListDE();}
    public NodeDE getPets(){return pets.getHead();}
    public void addPet(Pet pet){pets.addPet(pet);}
    public void putPetsMaletoBeginning(){pets.putPetsToBeginning();}

    public String putTostring(){return pets.toString();}
    public void addPetToBeginning(Pet pet){pets.addPetToBeginning(pet);}
    public void deletePet(String name){pets.deletePet(name);}
    public void addInPos(Pet pet, int pos){pets.addInPos(pet,pos);}
    public void invertList(){pets.invertList();}
    public void orderByGender(){pets.orderByGender();}
    public int verifyPhone(PetDTO petDTO){return pets.verifyPhone(petDTO);}
    public void exchangeExtremes(){pets.exchangeExtremes();}
    public int getCountPetsByLocationCode(String code){return pets.getCounPetsByLocationCode(code);}
    public int getCountPetsByLocationCodeAndMale(String code){return pets.getCountPetsByLocationCodeAndMale(code);}
    public int getCountPetsByLocationCodeAndFemale(String code){return pets.getCountPetsByLocationCodeAndFemale(code);}
    public void earnPositions(String phone, int earn){pets.earnPositions(phone,earn);}
    public void LosePositions(String phone, int lose){pets.losePositions(phone,lose);}
    public void sendPetsToEndByChar(char user){pets.sendPetsToEndByChar(user);}
    public double getAverageAge(){return pets.getAverageAge();}
    public String generateReportByAge(){return pets.generateReportByAge();}
    public void deleteByAge(byte age){pets.deleteByAge(age);}
}