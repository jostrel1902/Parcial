package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportKidsLocationGenderDTO {
    private List<LocationGenderQuantityDTO> LocationGenderQuantityDTOS;

    public ReportKidsLocationGenderDTO(List<Location> cities){
        LocationGenderQuantityDTOS = new ArrayList<>();
        for(Location location: cities){
            LocationGenderQuantityDTOS.add(new LocationGenderQuantityDTO(location.getName()));
        }
    }
    // método actualizar
    public void updateQuantity(String city,char gender){
        for(LocationGenderQuantityDTO loc:LocationGenderQuantityDTOS){
            if(loc.getCity().equals(city)){
                for(GenderQuantityDTO genderDTO: loc.getGenders()){
                    if(genderDTO.getGender()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
