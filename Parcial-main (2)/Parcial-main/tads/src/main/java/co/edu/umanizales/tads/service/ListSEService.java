package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {kids = new ListSE();}
    public void invert(){kids.invert();}
    public void changeExtremes(){kids.changeExtremes();}
    public void add(Kid kid){kids.add(kid);}
    public int getCountKidsbyLocationsCode(String code){return kids.getCountKidsByLocationCode(code);}
    public void deleteKidbyAge(byte age){kids.DeleteByAge(age);}
    public void gainPositionKid(String id, int gain){kids.gainPositionKid(id,gain);}
    public void IntercaleBoyandGirl(){kids.IntercaleBoyandGirl();}
    public void LosePositionKid(String id, int lose){kids.LosePositionKid(id, lose);}
    public float averageAge(){return kids.averageAge();}
    public void orderBoysToStart(){kids.orderBoysToStart();}
    public String reportByAge(){return kids.reportByAge();}
    public void addToFinalNameChar(String letter){kids.addToFinalNameChar(letter);}
    public void getReportKidsbyLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report){
        kids.getReportKidsByLocationGendersByAge(age,report);
    }
}

