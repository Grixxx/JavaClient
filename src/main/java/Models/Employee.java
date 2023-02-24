package Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employee implements Serializable {
    private int idEmployee;
    private int idUserFK;
    private int idSpecialtieFK;
    private BigDecimal resultSalary;
    private BigDecimal monthlyTariffRate;



    private static Employee instance;

    public Employee(){

    }


    public Employee (int idEmployee, int idUserFK, int idSpecialtieFK, BigDecimal resultSalary, BigDecimal monthlyTariffRate ){
        this.idEmployee = idEmployee;
        this.idUserFK = idUserFK;
        this.idSpecialtieFK = idSpecialtieFK;
        this.resultSalary = resultSalary;
        this.monthlyTariffRate = monthlyTariffRate;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdUserFK() {
        return idUserFK;
    }

    public void setIdUserFK(int idUserFK) {
        this.idUserFK = idUserFK;
    }

    public int getIdSpecialtieFK() {
        return idSpecialtieFK;
    }

    public void setIdSpecialtieFK(int idSpecialtieFK) {
        this.idSpecialtieFK = idSpecialtieFK;
    }

    public BigDecimal getResultSalary() {
        return resultSalary;
    }

    public void setResultSalary(BigDecimal resultSalary) {
        this.resultSalary = resultSalary;
    }

    public BigDecimal getMonthlyTariffRate() {
        return monthlyTariffRate;
    }

    public void setMonthlyTariffRate(BigDecimal monthlyTariffRate) {
        this.monthlyTariffRate = monthlyTariffRate;
    }
    public static Employee getInstance(){
        if(instance == null){
            instance = new Employee();
            return instance;
        }
        return instance;
    }
}
