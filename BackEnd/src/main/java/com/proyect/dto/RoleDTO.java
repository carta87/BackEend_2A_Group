package com.proyect.dto;

import lombok.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;
    private boolean enabled;
    private Set<ProfileDTO> profileList;
}
