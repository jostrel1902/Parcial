package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/listse")
@Data
@AllArgsConstructor
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@Validated @RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.add(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }



    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids(),
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/kidsbylocationgenders")
    public ResponseEntity<ResponseDTO> getReportKidsLocationGender(){
        ReportKidsLocationGenderDTO report =
                new ReportKidsLocationGenderDTO(locationService.getLocations());

        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null),HttpStatus.OK);
    }
// #1 Invertir lista
    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }
    // #2 Niños al comienzo
    @GetMapping(path="orderboystostart")
    public ResponseEntity<ResponseDTO>orderBoystoStart(){
        listSEService.orderBoysToStart();
        return new ResponseEntity<>(new ResponseDTO(
                200,"niños al comienzo",
                null), HttpStatus.OK);
    }
    // #3 Intercalar niño y niña
    @GetMapping(path="/intercaleboyandgirl")
    public ResponseEntity<ResponseDTO>IntercaleBoyandGirl(){
        listSEService.IntercaleBoyandGirl();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Los niños se intercalaron",
                null), HttpStatus.OK);
    }
    // #4 Borrar niño por edad
    @GetMapping(path="/deletekid/{age}")
    public ResponseEntity<ResponseDTO>deleteKidByAge(@PathVariable byte age){
        listSEService.deleteKidbyAge(age);
        return new ResponseEntity<>(new ResponseDTO(200, "Se borró el niño",
                null),HttpStatus.OK);
    }
    // #5 Promedio de edades
    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO>AverageAge(){
        return new ResponseEntity<>(new ResponseDTO(200,(float)listSEService.averageAge(),
                null), HttpStatus.OK);
    }
    // #6 Location


    //Obtener niños por ubicaciones
    @GetMapping(path = "/kidsbylocations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getCountKidsbyLocationsCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    // Por departamento
    @GetMapping(path="/kidsbydepartment")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(5)){
            int count = listSEService.getCountKidsbyLocationsCode(loc.getCode());

            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    // Por ciudad
    @GetMapping(path="/kidsbycity")
    public ResponseEntity<ResponseDTO> getKidsByCity(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocationsByCodeSize(8)){
            int count = listSEService.getCountKidsbyLocationsCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    // #7 Ganar posicion de niño
    @GetMapping(path="/gainpositionkid")
    public ResponseEntity<ResponseDTO> gainPositionKid(@RequestBody Map<String, Object> requestBody){
        String id= (String) requestBody.get("id");
        Integer gain=(Integer) requestBody.get("gain");
        listSEService.gainPositionKid(id, gain);
        return new ResponseEntity<>(new ResponseDTO(200,"Ganó posicion el niño",
                null), HttpStatus.OK);
    }
    // #8 Perder posicion de niño
    @GetMapping(path="/losepositionkid")
    public ResponseEntity<ResponseDTO> LosePositionKid(@RequestBody Map<String, Object> requestBody){
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listSEService.LosePositionKid(id, lose);
        return new ResponseEntity<>(new ResponseDTO(200,"Perdió posicion el niño",
                null), HttpStatus.OK);
    }
    // #9 Reporte de niños por edad
    @GetMapping(path="/reportbyage")
    public ResponseEntity<ResponseDTO> reportByAge(){
        return new ResponseEntity<>(new ResponseDTO(200,listSEService.reportByAge(),
                null), HttpStatus.OK);
    }
    // #10 Enviar al final
    @GetMapping(path="/addtofinalnamechar/{letter}")
    public ResponseEntity<ResponseDTO> addtoFinalNameChar(@PathVariable String letter){
        listSEService.addToFinalNameChar(letter);
        return new ResponseEntity<>(new ResponseDTO(200, "El niño está al final", null), HttpStatus.OK);
    }
    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> ChangeExtremes() {
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }
}
