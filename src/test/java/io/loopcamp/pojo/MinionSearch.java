package io.loopcamp.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MinionSearch {
    @JsonProperty ("content")
    private List <Minion> allMinions;
    private int totalElement;

}