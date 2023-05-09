package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(path="listde")
public class ListDEController{
    @Autowired
    private ListDEService listDEService;
    @Autowired LocationService locationService;
    @GetMapping(path="/getlist")
    public ResponseEntity<ResponseDTO> getPets(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.putTostring(),
                null), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@Valid @RequestBody PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (listDEService.verifyPhone(petDTO)==0){
            if (location==null){
                return new ResponseEntity<>(new ResponseDTO(404,"Ubicacion no existente",
                        null), HttpStatus.OK);
            }
            listDEService.addPet(new Pet(petDTO.getAge(),
                    petDTO.getName(),
                    petDTO.getType(),
                    petDTO.getRace(),
                    location,
                    petDTO.getGender(),
                    petDTO.getOwnerPhone()));
            return new ResponseEntity<>(new ResponseDTO(200,"Se adicionó el petacon",
                    null), HttpStatus.OK);
        }else{
            throw new RequestException("400","Telefono inexistente",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path="/deletepet/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String name){
        listDEService.deletePet(name);
        return new ResponseEntity<>(new ResponseDTO(200,"Se eliminó la mascota",
                null), HttpStatus.OK);
    }
    @PostMapping(path="/adpettobeginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(@Valid @RequestBody Pet pet) {
        listDEService.addPetToBeginning(pet);
        return new ResponseEntity<>(new ResponseDTO(200,"Se agregó la mascota al inicio",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/petsbylocations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for (Location loc: locationService.getLocations()){
            if(listDEService.getPets().getData().getAge() > age){
                int count = listDEService.getCountPetsByLocationCode(loc.getCode());
                int male = listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female = listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count>0){
                    petByLocationDTOS.add(new PetByLocationDTO(loc,female,male,count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path="/petsbydepartment/{age}")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for (Location loc: locationService.getLocationsByCodeSize(5)){
            if (listDEService.getPets().getData().getAge()>age){
                int count = listDEService.getCountPetsByLocationCode(loc.getCode());
                int male = listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female = listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count>0){
                    petByLocationDTOS.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path="/petsbycity/{age}")
    public ResponseEntity<ResponseDTO> getKidsByCity(@PathVariable byte age){
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(8)){
            if (listDEService.getPets().getData().getAge() > age){
                int count=listDEService.getCountPetsByLocationCode(loc.getCode());
                int male=listDEService.getCountPetsByLocationCodeAndMale(loc.getCode());
                int female=listDEService.getCountPetsByLocationCodeAndFemale(loc.getCode());
                if (count > 0){
                    petByLocationDTOS.add(new PetByLocationDTO(loc,female,male,count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path="/getaverageage")
    public ResponseEntity<ResponseDTO>getAverageAge(){
        listDEService.getAverageAge();
        return new ResponseEntity<>(new ResponseDTO(200,"La edad promedio de las mascotas es:"+listDEService.getAverageAge(),
                null), HttpStatus.OK);
    }
    @PostMapping(path="/sendpetstoendbychar")
    public ResponseEntity<ResponseDTO> sendPetsToEndByChar(@RequestBody Map<String, Object> requestData){
        String userString = (String) requestData.get("user");
        char user = userString.toLowerCase().charAt(0);
        listDEService.sendPetsToEndByChar(user);
        return new ResponseEntity<>(new ResponseDTO(200,"Se envió la mascota al final", null), HttpStatus.OK);
    }
    @PostMapping(path="/losepositions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody Map<String, Object> requestBody){
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listDEService.LosePositions(id,lose);
        return new ResponseEntity<>(new ResponseDTO(200,"Se reordeno la posición",
                null), HttpStatus.OK);
    }
    @GetMapping(path="/orderbygender")
    public ResponseEntity<ResponseDTO> orderByGender(){
        listDEService.orderByGender();
        return new ResponseEntity<>(new ResponseDTO(200, "Se ordenarón las mascotas",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/exchangelistde")
    public ResponseEntity<ResponseDTO> exchangeExtremes() {
        listDEService.exchangeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se intercambiaron los extremos",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/invertlist")
    public ResponseEntity<ResponseDTO> invertList() {
        listDEService.invertList();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se invirtió la lista",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/deletepetbyage/{age}")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable byte age) {
        listDEService.deleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se eliminaron las mascotas de esa edad",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/maletobeginning")
    public ResponseEntity<ResponseDTO> putKidsToBeginning() {
        listDEService.putPetsMaletoBeginning();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se agregaron machos al principio y feminas al final",
                null), HttpStatus.OK);
    }
    @PostMapping(path = "/earnpositions")
    public ResponseEntity<ResponseDTO> earnPositions(@RequestBody Map<String, Object> requestBody) {
        String id = (String) requestBody.get("id");
        Integer earn = (Integer) requestBody.get("earn");
        listDEService.earnPositions(id, earn);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se reordenaron las posiciones",
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/generatereportbyage")
    public ResponseEntity<ResponseDTO>  generateReportByAge(){
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.generateReportByAge(),
                null), HttpStatus.OK);
    }
    @PostMapping(path="/deletekamikaze")
    public ResponseEntity<ResponseDTO> deleteKamikaze(@PathVariable String id){
        listDEService.deleteKamikaze(id);
        return new ResponseEntity<>(new ResponseDTO(200, "La mascota fue eliminada",
                null), HttpStatus.OK);
    }
}