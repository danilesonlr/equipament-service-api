package com.softplan.api.dto.request;

import com.softplan.api.dto.complement.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterNoteRequestDTO {
    @NotNull(message = "Campo obrigatório")
    private Long orderNumber;
    @NotNull(message = "Campo obrigatório")
    private String description;
    @NotNull(message = "Campo obrigatório")
    private String technicalName;
    @NotNull(message = "Campo obrigatório")
    private StatusEnum status;
}
