package com.xdlr.webflux.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Document(collection = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 1, max = 100)
    private Integer age;

}
