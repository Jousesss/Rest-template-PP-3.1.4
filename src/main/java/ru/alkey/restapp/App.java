package ru.alkey.restapp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.alkey.restapp.models.User;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final String baseUrl = "http://94.198.50.185:7081/api/users";

        String resultString = ""; // printed in the end of app

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> objectRequestEntity = new HttpEntity<>(headers);

        // Получаю пользователей
        ResponseEntity<User[]> responseUsers = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                objectRequestEntity,
                User[].class);

        headers.set("Cookie",responseUsers.getHeaders().getFirst("Set-Cookie"));

        // Добавляю пользователя
        User userToPost = new User(3L,"James","Brown",(byte)24);
        HttpEntity<User> userRequestEntity = new HttpEntity<>(userToPost,headers);

        ResponseEntity<String> responseString = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                userRequestEntity,
                String.class);

        resultString += responseString.getBody();

        // Редактирую пользователя
        User userToPut = new User(3L,"Thomas","Shelby",(byte)24);
        userRequestEntity = new HttpEntity<>(userToPost,headers);

        responseString = restTemplate.exchange(
                baseUrl,
                HttpMethod.PUT,
                userRequestEntity,
                String.class);

        resultString += responseString.getBody();

        // Удаляю пользователя
        Long userId = 3L;
        objectRequestEntity = new HttpEntity<>(headers);

        responseString = restTemplate.exchange(
                baseUrl + "/" + userId,
                HttpMethod.DELETE,
                objectRequestEntity,
                String.class);

        resultString += responseString.getBody();


        System.out.println(
                "=======================\n\n" +
                "Результат: " + resultString +
                "\nКолл-во символов: " + resultString.length() +
                "\n\n=======================");


















        //        final String requestUrl = "http://94.198.50.185:7081/api/users";
//
//        // Строчка со всеми полученными кусочками результатов из каждого запроса
//        String resultString = null;
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<String> responseUsers = restTemplate.getForEntity(requestUrl, String.class);
//        String sessionId = responseUsers.getHeaders().getFirst("Set-Cookie");
//
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("Cookie", sessionId);
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        User userToPost = new User(3L,"James","Brown",(byte)25);
//        HttpEntity<User> request = new HttpEntity<>(userToPost, requestHeaders);
//
//
//        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);
//        resultString += response.getBody();
//        System.out.println("1. " + resultString);
//
//        restTemplate.put(requestUrl, request);
//        resultString += response.getBody();
//        System.out.println("2. " + resultString);
//
//        // Отправляем DELETE-запрос для удаления пользователя
//        restTemplate.delete((requestUrl + "/3"), request);
//        resultString += response.getBody();
//        System.out.println("3. " + resultString);
//
//
//        System.out.println("==================\n\nРЕЗУЛЬТАТ: " +
//                "\n\nSymbols: " + resultString.length() +
//                "\n\n==================");
    }
}
