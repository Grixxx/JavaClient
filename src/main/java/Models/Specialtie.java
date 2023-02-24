package Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Specialtie implements Serializable {
    private final int idSpecialtie;
    private final String specialtieName;

    public Specialtie (int idSpecialtie, String specialtieName ){
        this.idSpecialtie = idSpecialtie;
        this.specialtieName = specialtieName;
    }

    public static SpecialtieBuilder builder(){
        return new SpecialtieBuilder();
    }



    public int getIdSpecialtie() {
        return idSpecialtie;
    }

    public String getSpecialtieName() {
        return specialtieName;
    }

}
