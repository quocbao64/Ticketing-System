package com.programing.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerResponse {

    private Long id;
    private String numberPhone;
    private String email;
    private String fullName;

}
