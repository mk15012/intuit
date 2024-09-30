package com.intuit.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intuit.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntry {

    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private UserType userType;

}