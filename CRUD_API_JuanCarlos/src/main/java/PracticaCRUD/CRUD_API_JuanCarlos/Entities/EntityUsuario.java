package PracticaCRUD.CRUD_API_JuanCarlos.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBUSUARIO")
@Getter @Setter @ToString @EqualsAndHashCode
public class EntityUsuario {

    @Id @Column(name = "IDUSUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    private Long idUsuario;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "IDGRUPOEXPO")
    private Long idGrupoExpo;
    @Column(name = "IDROL")
    private Long idRol;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "IDCARGO")
    private Long idCargo;
}
