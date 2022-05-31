package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({

        "name",
        "job",
        "id",
        "createdAt",
})
public class Successful {

    @JsonProperty("name")
    String name;
    @JsonProperty("job")
    String job;
        @JsonProperty("id")
    Integer id;
    @JsonProperty("createdAt")
    String createdAt;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public Integer getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
