package sdapostman;

import org.codehaus.jackson.map.ObjectMapper;
import requests.CreateUserRequest;
import requests.CreateUserResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-03-06.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        List<String> userIds = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Dodaj uzytkownika");
            System.out.println("2. Wyswietl id uzytkownika");
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
                    String createUserResponse = Sender.createUser("http://localhost:8081/sda-json/json", request);
                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userIds.add(response.getId());
                    break;

                case 2:
                    System.out.println(userIds.toString());
                    break;
                default:
                    break;
            }
        }
    }
}

