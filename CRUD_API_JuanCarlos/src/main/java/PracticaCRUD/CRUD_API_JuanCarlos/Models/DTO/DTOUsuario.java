package PracticaCRUD.CRUD_API_JuanCarlos.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOUsuario {

    private Long idUsuario;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Positive(message = "EL ID del Grupo EXPO debe ser positivo")
    private Long idGrupoExpo;
    @Positive(message = "EL ID del ROL debe ser positivo")
    private Long idRol;
    @NotBlank @Email(message = "Debe ser un correo válido")
    private String correo;
    @NotBlank @Size(min = 8, message = "La contraseña debe de tener al menos 8 carácteres")
    private String contrasena;
    @NotNull
    @Positive(message = "EL ID del Cargo debe ser positivo")
    private Long idCargo;
}
