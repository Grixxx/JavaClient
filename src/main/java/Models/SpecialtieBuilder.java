package Models;

public  class SpecialtieBuilder{
    private int idSpecialtie;
    private String specialtieName;

    public SpecialtieBuilder idSpecialtie(int idSpecialtie){
        this.idSpecialtie = idSpecialtie;
        return this;
    }

    public SpecialtieBuilder specialtieName(String specialtieName){
        this.specialtieName = specialtieName;
        return this;
    }

    public Specialtie build(){
        return new Specialtie(idSpecialtie,specialtieName);
    }
}
