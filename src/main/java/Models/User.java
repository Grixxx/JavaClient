package Models;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;
    private String specialtieName;
    private int idEmployee;




    private String resultSalary;



    public User(){

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User (int idUser, String firstName, String lastName, String login, String password, String role){
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getSpecialtieName() {
        return specialtieName;
    }

    public void setSpecialtieName(String specialtieName) {
        this.specialtieName = specialtieName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password=" + password +
                '}';
    }
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public String getResultSalary() {
        return resultSalary;
    }

    public void setResultSalary(String resultSalary) {
        this.resultSalary = resultSalary;
    }
}
