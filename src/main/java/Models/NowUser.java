package Models;

import java.io.Serializable;

public class NowUser implements Serializable {
    private int idUser;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String rolee;
    private int idEmployee;
    private int idSpecialti;

    private String specialtieName;
    private String resultSalary;

    public String getResultSalary() {
        return resultSalary;
    }

    public void setResultSalary(String resultSalary) {
        this.resultSalary = resultSalary;
    }

    private static NowUser instance;
    public NowUser(){

    }
    public static NowUser getInstance(){
        if(instance == null){
            instance = new NowUser();
            return instance;
        }
        return instance;
    }
    public NowUser(int idUser, String firstName, String lastName, String login, String password, String rolee){
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.rolee = rolee;
    }
    public NowUser(String firstName, String lastName, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
    public static void setInstance(NowUser nowUser){
        instance = nowUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return idUser;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getRolee() {
        return rolee;
    }

    public void setRolee(String rolee) {
        this.rolee = rolee;
    }
    public String getSpecialtieName() {
        return specialtieName;
    }

    public void setSpecialtieName(String specialtieName) {
        this.specialtieName = specialtieName;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public int getIdSpecialti() {
        return idSpecialti;
    }

    public void setIdSpecialti(int idSpecialti) {
        this.idSpecialti = idSpecialti;
    }
}

