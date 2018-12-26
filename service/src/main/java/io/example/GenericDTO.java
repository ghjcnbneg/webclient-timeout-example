package io.example;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO {

    public static final GenericDTO DEFAULT_GENERIC_DTO = new GenericDTO(10L, 20L, "reactor");

    private Long id;
    private Long max;
    private String group;

}
