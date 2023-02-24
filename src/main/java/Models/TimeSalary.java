package Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class TimeSalary implements Serializable {
    private int idUser;
    private BigDecimal paymentPerHour;
    private int hours;
    private int premium;

    public TimeSalary(){

    }


    public TimeSalary (int idUser, BigDecimal paymentPerHour, int hours){
        this.idUser = idUser;
        this.paymentPerHour = paymentPerHour;
        this.hours = hours;
    }

    public TimeSalary(int idUser, BigDecimal paymentPerHour, int hours, int premium) {
        this.idUser = idUser;
        this.paymentPerHour = paymentPerHour;
        this.hours = hours;
        this.premium = premium;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(BigDecimal paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }
}
