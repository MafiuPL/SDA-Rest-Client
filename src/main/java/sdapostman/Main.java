package sdapostman;

import messages.GetUserResponse;
import org.codehaus.jackson.map.ObjectMapper;
import messages.CreateUserRequest;
import messages.CreateUserResponse;
import utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-03-06.
 */
public class Main {

    public static final String URL = "http://localhost:8081/sda-json/json";

    public static void main(String[] args) throws IOException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        List<String> userIds = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Dodaj uzytkownika");
            System.out.println("2. Wyswietl id uzytkownika");
            System.out.println("3. Wyswietl dane uzytkownikow");
            String choiceRaw = scanner.nextLine();
            Integer choice = Integer.parseInt(choiceRaw);

            switch (choice) {
                case 1:

                    createUserRequest = new CreateUserRequest();
                    System.out.println("Podaj login");
                    createUserRequest.setLogin(scanner.nextLine());
                    System.out.println("Podaj imie");
                    createUserRequest.setName(scanner.nextLine());
                    System.out.println("Podaj mail");
                    createUserRequest.setMail(scanner.nextLine());


//        String createUserRequest = "{" +
//                "\"name\":\"mateusz\"," +
//                "\"login\":\"mj\"," +
//                "\"mail\":\"mj@o2.pl\"" +
//                "}";
                    ObjectMapper mapper = new ObjectMapper();
                    String request = mapper.writeValueAsString(createUserRequest);
                    String createUserResponse = Sender.createUser(URL, request);
                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userIds.add(response.getId());
                    break;

                case 2:
                    System.out.println(userIds.toString());
                    break;
                case 3:
                    List<String> getUserResponses = new ArrayList<>();
                    for (String id : userIds) {
                        getUserResponses.add(Sender.getUser(URL, id));
                    }
                    System.out.println(getUserResponses.toString());
                    break;
                default:
                    break;
            }
        }
    }
}

