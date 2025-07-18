package PracticaCRUD.CRUD_API_JuanCarlos.Controller;

import PracticaCRUD.CRUD_API_JuanCarlos.Exceptions.ExceptionUsuarioDatosDuplicados;
import PracticaCRUD.CRUD_API_JuanCarlos.Exceptions.ExceptionUsuarioNoEncontrado;
import PracticaCRUD.CRUD_API_JuanCarlos.Models.DTO.DTOUsuario;
import PracticaCRUD.CRUD_API_JuanCarlos.Services.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PutMapping("/putUsuario/{idUsuario}")
    public ResponseEntity<?> putData(@PathVariable Long idUsuario, @Valid @RequestBody DTOUsuario json, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            DTOUsuario objActualizarUsuario = objUsuarioService.putUsuario(json, idUsuario);
            return ResponseEntity.ok(objActualizarUsuario);
        } catch (ExceptionUsuarioNoEncontrado e) {
            return ResponseEntity.notFound().build();
        } catch (ExceptionUsuarioDatosDuplicados e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos duplicados"
            ));
        }
    }

    //Cuarto método DELETE
    @DeleteMapping("/deleteUsuario/{idUsuario}")
    public ResponseEntity<?> deleteData(@Valid @PathVariable Long idUsuario) {
        try {
            if (!objUsuarioService.deleteUsuario(idUsuario)) {
                return ResponseEntity.badRequest().body("El ID no puede ser nulo o vacío");
            }
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar los datos asociados: " + e.getMessage());
        }
    }
}
