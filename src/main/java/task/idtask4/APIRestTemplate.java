package task.idtask4;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import task.idtask4.models.User;

public class APIRestTemplate {
    public static void main(String[] args) {
        String urlAPI = "http://94.198.50.185:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseGetTest = restTemplate.exchange(urlAPI, HttpMethod.GET, null, String.class);

        System.out.println("RestBody: ");
        System.out.println(responseGetTest.getBody());

        String sessionId = responseGetTest.getHeaders().getFirst("Set-Cookie");
        System.out.println("Id: " + sessionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.COOKIE, sessionId);
        User user1 = new User(3L, "James", "Brown", 20);
        HttpEntity<User> requestUser = new HttpEntity<>(user1, headers);
        ResponseEntity<String> responsePostU1 = restTemplate.exchange(urlAPI, HttpMethod.POST, requestUser, String.class);

        String Part1 = responsePostU1.getBody();
        System.out.println("FirstPart: ");
        System.out.println(responsePostU1.getBody());

        User user1Updated = new User(3L, "Thomas", "Shelby", 99);
        HttpEntity<User> requestUserUpdated = new HttpEntity<>(user1Updated, headers);
        responsePostU1 = restTemplate.exchange(urlAPI, HttpMethod.PUT, requestUserUpdated, String.class);

        System.out.println("SecondPart: ");
        System.out.println(responsePostU1.getBody());

        String Part2 = responsePostU1.getBody();
        String urlAPIDel = "http://94.198.50.185:7081/api/users/3";
        HttpEntity<Void> requestDel = new HttpEntity<>(headers);
        responsePostU1 = restTemplate.exchange(urlAPIDel, HttpMethod.DELETE, requestDel, String.class);

        String Part3 = responsePostU1.getBody();
        System.out.println("ThirdPart: ");
        System.out.println(responsePostU1.getBody());
        System.out.println("CodeFraz:");
        System.out.println(Part1 + Part2 + Part3);

    }
}
