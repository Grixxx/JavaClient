package ConnectToServer;

import Models.*;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.LinkedList;

public class InteractionsWithServer {
    private static Socket socket;
    private static BufferedWriter output;
    private static BufferedReader input;

    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static Request r = new Request();
    private Request req = new Request();

    public InteractionsWithServer(Socket clientSocket) {
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            socket = clientSocket;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void sendMessageToServer(String messageToServer){
//        try{
//            output.write(messageToServer+ "\n");
//            output.flush();
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Error sending message to the server");
//        }
//    }
    public static void sendMessageToServer(String send){
        try{
            r.recMessage=send;
            oos.writeObject(r);
            oos.reset();

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to the server");
        }
    }
    private Request getMessage(){
        try {
            req = (Request) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return req;
    }
    public void registUsers(String fName, String lName, String login, String password){
        sendMessageToServer("РЕГИСТРАЦИЯ");
        sendMessageToServer(fName+" "+lName+" "+login+" "+password);
    }
    public String authUser(String login,String password) throws IOException {
        sendMessageToServer("ВХОД");
        sendMessageToServer(login+" "+password);
        String[] response = getMessage().recMessage.split(" ");
        if(response[0].equals("trueUser")){
            NowUser.getInstance().setIdUser(Integer.parseInt(response[1]));
            NowUser.getInstance().setFirstName(response[2]);
            NowUser.getInstance().setLastName(response[3]);
            NowUser.getInstance().setLogin(response[4]);
            NowUser.getInstance().setPassword(response[5]);
            return "trueUser";
        } else if (response[0].equals("trueAdmin")) {
            return "trueAdmin";
        }else
        {
            return "false";
        }

    }
//    public void registUsers(String fName, String lName, String login, String password){
//        User user = new User();
//        user.setFirstName(fName);
//        user.setLastName(lName);
//        user.setLogin(login);
//        user.setPassword(password);
//        try {
//            oos.writeObject(RequestType.REG);
//            oos.writeObject(user);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public String authUser(String login,String password) throws IOException {
//        User user = new User();
//        user.setLogin(login);
//        user.setPassword(password);
//        try {
//            oos.writeObject(RequestType.AUTH);
//            oos.writeObject(user);
//            String[] response = input.readLine().split(" ");
//            if(response[0].equals("trueUser")){
//                NowUser.setInstance((NowUser) ois.readObject());
//                System.out.println(NowUser.getInstance().getIdUser());
//                return "trueUser";
//            }else if (response[0].equals("trueAdmin")) {
//                return "trueAdmin";
//            }else {
//                return "false";
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void checkNowUserIsEmployee() throws IOException{
        sendMessageToServer("ПРОВЕРКА ЮЗЕРА НА РАБОТНИКА");
        sendMessageToServer(String.valueOf(NowUser.getInstance().getIdUser()));
        String[] response = getMessage().recMessage.split(" ");
        System.out.println(response[0]);
        NowUser.getInstance().setSpecialtieName(response[0]);
        NowUser.getInstance().setIdEmployee(Integer.parseInt(response[1]));
    }
    public LinkedList<NowUser> showNowUsers() throws IOException {
        sendMessageToServer("ПРОВЕРКАюзера");
        sendMessageToServer(String.valueOf(NowUser.getInstance().getIdUser()));
        LinkedList<NowUser> nowUser = new LinkedList<>();
        int sizeList = Integer.parseInt(getMessage().recMessage);
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll0(getMessage().recMessage,nowUser);
        }
        return nowUser;
    }
    public LinkedList<User> showAllUsers() throws IOException {
        sendMessageToServer("ВСЕ(АДМИН) ПОЛЬЗОВАТЕЛИ");
        LinkedList<User> users = new LinkedList<>();
        int sizeList = Integer.parseInt(getMessage().recMessage);
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll1(getMessage().recMessage,users);
        }
        return users;
    }
    public LinkedList<Employee> showAllEmployee() throws IOException {
        sendMessageToServer("ВСЕ(АДМИН) РАБОТНИКИ");
        LinkedList<Employee> employees= new LinkedList<>();
        int sizeList = Integer.parseInt(getMessage().recMessage);
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll2(getMessage().recMessage,employees);
        }
        return employees;
    }

    public LinkedList<Specialtie> showAllSpecialtie() throws IOException {
        sendMessageToServer("ВСЕ(АДМИН) ВАКАНСИИ");
        LinkedList<Specialtie> specialties = new LinkedList<>();
        int sizeList = Integer.parseInt(getMessage().recMessage);
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll3(getMessage().recMessage,specialties);
        }
        return specialties;
    }
    public LinkedList<Specialtie> showAllSpecialtieToUSer() throws IOException {
        sendMessageToServer("ВСЕ(ЮЗЕР) ВАКАНСИИ");
        LinkedList<Specialtie> specialties = new LinkedList<>();
        int sizeList = Integer.parseInt(getMessage().recMessage);
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll3(getMessage().recMessage,specialties);
        }
        return specialties;
    }
    public void showSalaryNowEmployee() throws IOException{
        sendMessageToServer("Зарпалата сотрудника");
        sendMessageToServer(String.valueOf(NowUser.getInstance().getIdEmployee()));
        String[] response = getMessage().recMessage.split(" ");
        NowUser.getInstance().setResultSalary(response[0]);
        File file = new File("FileWithSellery");
        PrintWriter pw = new PrintWriter(file);

        pw.println("Зарплата сотрудника " + NowUser.getInstance().getIdUser());
        pw.println(NowUser.getInstance().getResultSalary());

        pw.close();
    }

    private void parseStringInContributionAll0(String nowUser,LinkedList<NowUser> list){
        String[] subStr;
        subStr = nowUser.split(" ");
        list.add(new NowUser(subStr[0],subStr[1], subStr[2], subStr[3]));
    }

    private void parseStringInContributionAll1(String users,LinkedList<User> list){
        String[] subStr;
        subStr = users.split(" ");
        list.add(new User(Integer.parseInt(subStr[0]),subStr[1],subStr[2], subStr[3], subStr[4], subStr[5]));
    }
    private void parseStringInContributionAll2(String employees,LinkedList<Employee> list){
        String[] subStr;
        subStr = employees.split(" ");
        String resultSalary = subStr[3];
        BigDecimal resultSalaryDecimal = new BigDecimal(resultSalary);
        String resultRate = subStr[4];
        BigDecimal resultRateDecimal = new BigDecimal(resultRate);


        list.add(new Employee(Integer.parseInt(subStr[0]),Integer.parseInt(subStr[1]),Integer.parseInt(subStr[2]), resultSalaryDecimal, resultRateDecimal));
    }
    private void parseStringInContributionAll3(String specialties,LinkedList<Specialtie> list){
        String[] subStr;
        subStr = specialties.split(" ");
        list.add(Specialtie.builder().specialtieName(subStr[1]).idSpecialtie(Integer.parseInt(subStr[0])).build());
    }
    public void addUser(String fName, String lName, String login, String password) {
        sendMessageToServer("добавить(АДМИН) пользователя");
        sendMessageToServer(fName+" "+lName+" "+login+" "+password);
    }
    public void addEmployee(int idUser) {
        sendMessageToServer("сделать(АДМИН) работником");
        sendMessageToServer(String.valueOf(idUser));
    }
    public void addSpecialtie(String specialtieName) {
        sendMessageToServer("добавить(АДМИН) вакансию");
        sendMessageToServer(specialtieName);
    }

    public void addSpecialtieToEmployee(String idEmployee, String idSpecialtie) {
        sendMessageToServer("ПРИСВОИТЬ(АДМИН) ВАКАНСИЮ РАБОТНИКУ");
        sendMessageToServer(idEmployee + " " + idSpecialtie);
    }
    public void addSpecialtieToUser(int idSpecialtie) throws IOException {
            sendMessageToServer("СОЗДАНИЕ РАБОТНИКА И ПРИСВОИТЬ(ЮЗЕР) ВАКАНСИЮ РАБОТНИКУ");
            sendMessageToServer(NowUser.getInstance().getIdUser() + " " + idSpecialtie);

        }
    public void deleteUserinUserPanel(int idNowUser) {
        sendMessageToServer("удалить(ЮЗЕР) пользователя");
        sendMessageToServer(String.valueOf(idNowUser));
    }

    public void deleteUser(int idUser) {
        sendMessageToServer("удалить(АДМИН) пользователя");
        sendMessageToServer(String.valueOf(idUser));
    }
    public void deleteEmployee(int idEmployee) {
        sendMessageToServer("удалить(АДМИН) работника");
        sendMessageToServer(String.valueOf(idEmployee));
    }
    public void deleteSpecialtie(int idSpecialtie) {
        sendMessageToServer("удалить(АДМИН) вакансию");
        sendMessageToServer(String.valueOf(idSpecialtie));
    }
    public void deleteNowUserEmployee(int idEmployee) {
        sendMessageToServer("удалить(ЮЗЕР) работника");
        sendMessageToServer(String.valueOf(idEmployee));
    }
    public void changePassword(String password){
        sendMessageToServer("поменять(ЮЗЕР) пароль");
        sendMessageToServer(String.valueOf(NowUser.getInstance().getIdUser())+ " " + password);
    }



    public void calculateTimeSalaryEmployee(String paymentPerHour, String hours){
        sendMessageToServer("Рассчет повременной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+paymentPerHour + " " + hours);
    }

    public void calculateTimePremiumSalaryEmployee(String paymentPerHour, String hours, String premium ){
        sendMessageToServer("Рассчет повременной-премиальной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+paymentPerHour + " " + hours+ " "+ premium);
    }
    public void calculateStraightPieceworkSalary(String hourlyRate, String productionRate, String numberOfItems, String numberOfProducts ){
        sendMessageToServer("Рассчет прямой сдельной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+hourlyRate + " " + productionRate+ " "+ numberOfItems + " " + numberOfProducts);
    }
    public void calculatePremiumPieceworkSalary(String hourlyRate, String productionRate, String numberOfItems, String numberOfProducts, String performanceBonusPercentage,
                                               String overfulfillmentBonusPercentage,  String overfulfillmentBonusPlan){
        sendMessageToServer("Рассчет сдельно-премиальной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+hourlyRate + " " + productionRate+ " "+ numberOfItems + " " + numberOfProducts + " "+
                performanceBonusPercentage+" " + overfulfillmentBonusPercentage + " "+overfulfillmentBonusPlan);
    }
    public void calculateProgressivePieceworkSalary(String hourlyRate, String productionRate, String сolProductsPlan, String coefficient, String colProductsReleased){
        sendMessageToServer("Рассчет сдельно-прогрессивной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+hourlyRate + " " + productionRate+ " "+ сolProductsPlan + " " + coefficient + " "+
                colProductsReleased);
    }
    public void calculateIndirectPieceworkSalary(String ancillaryHourlyRate, String auxiliaryServiceRate, String  productionRateMain, String numberOfItems, String numberOfProducts){
        sendMessageToServer("Рассчет косвенно-сдельной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+ancillaryHourlyRate + " " + auxiliaryServiceRate+ " "+ productionRateMain + " " + numberOfItems + " "+
                numberOfProducts);
    }
    public void calculateAddAccordPieceworkSalary(String totalEarnings, String allWorkingTime, String  employeeTime){
        sendMessageToServer("Рассчет аккордной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+ totalEarnings + " " + allWorkingTime + " "+ employeeTime);
    }
    public void calculateAccordPremiumPieceworkSalary(String totalEarnings, String allWorkingTime, String  employeeTime, String premiumAcord){
        sendMessageToServer("Рассчет аккодно-премиальной ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+totalEarnings + " " + allWorkingTime+ " "+ employeeTime + " " + premiumAcord);
    }
    public void calculateMonthlyTariffRate(String monthlyTariffRateFirstClass, String tariffCoefficient){
        sendMessageToServer("Рассчет месечной тарифной ставки ЗП");
        sendMessageToServer(Employee.getInstance().getIdEmployee()+" "+monthlyTariffRateFirstClass + " " + tariffCoefficient);
    }
    public static void logout(Stage stage) throws IOException {
        closeEverything();
        stage.close();
    }

    public static void closeEverything(){
        try{
            sendMessageToServer("EXIT");
            if( input !=null)  input.close();
            if(output !=null) output.close();
            if(socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
