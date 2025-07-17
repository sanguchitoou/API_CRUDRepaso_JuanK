package PracticaCRUD.CRUD_API_JuanCarlos.Controller;

import PracticaCRUD.CRUD_API_JuanCarlos.Models.DTO.DTOUsuario;
import PracticaCRUD.CRUD_API_JuanCarlos.Services.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiUsuario")
@Validated
public class ControllerUsuario {

    //Inyección del Service sobre el controller
    @Autowired
    ServiceUsuario objUsuarioService;

    //Primer método GET
    @GetMapping("/getUsuario")
    public List<DTOUsuario> getData(){
        return objUsuarioService.getUser();
    }

    //Segundo método POST
    @PostMapping("/postUsuario")
    public ResponseEntity<?> postData(@Valid @RequestBody DTOUsuario usuario){
        try {
            DTOUsuario objRespuestaUsuario = objUsuarioService.postUsuario(usuario);
            if (objRespuestaUsuario == null){
                return ResponseEntity.badRequest().body("Datos inválidos");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("El usuario se ha regitrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar los datos ingresados: " + e.getMessage());
        }
    }

    //Tercer método PUT

    //Cuarto método DELETE
}
