package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("_id")   // Because JSON uses "_id"
    private String id;

    private String firstName;
    private String lastName;
    private String email;

    @JsonProperty("__v")   // Because JSON uses "__v"
    private int version;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getVersion() {
        return version;
    }
}
