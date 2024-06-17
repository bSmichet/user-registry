package fr.airfrance.userregistry.domain;

public class UserTestSamples {

    public static User getUserSample1() {
        return new User()
                .id("id1")
                .firstName("firstName1")
                .lastName("lastName1")
                .address("address1")
                .postalCode("postalCode1")
                .email("email1")
                .phoneNumber("phoneNumber1");
    }

    public static User getUserSample2() {
        return new User()
                .id("id2")
                .firstName("firstName2")
                .lastName("lastName2")
                .address("address2")
                .postalCode("postalCode2")
                .email("email2")
                .phoneNumber("phoneNumber2");
    }
}
