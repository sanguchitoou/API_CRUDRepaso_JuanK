package PracticaCRUD.CRUD_API_JuanCarlos.Services;

import PracticaCRUD.CRUD_API_JuanCarlos.Entities.EntityUsuario;
import PracticaCRUD.CRUD_API_JuanCarlos.Models.DTO.DTOUsuario;
import PracticaCRUD.CRUD_API_JuanCarlos.Models.Repositories.RepositoryUsuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceUsuario {

    //Inyección del REPOSITORIO, inicio de la API
    @Autowired
    RepositoryUsuario objUsuarioRepo;

    //Método GET
    public List<DTOUsuario> getUser() {
        List<EntityUsuario> getAllUser = objUsuarioRepo.findAll();
        return getAllUser.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    //Método POST
    public DTOUsuario postUsuario(DTOUsuario dtoUsuario){
        if (dtoUsuario == null){
            throw new IllegalArgumentException("No pueden haber campos vacíos");
        }
        EntityUsuario objUsuarioGuardado = objUsuarioRepo.save(convertirAEntity(dtoUsuario));
        return convertirADTO(objUsuarioGuardado);
    }

    //Método PUT
    public DTOUsuario putUsuario(DTOUsuario dtoUsuario, Long idUsuario) {
        if(dtoUsuario == null){
            throw new IllegalArgumentException("No pueden haber campos vacíos");
        }
        EntityUsuario objUsuarioExists = objUsuarioRepo.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + idUsuario));
        objUsuarioExists.setNombre(dtoUsuario.getNombre());
        objUsuarioExists.setApellido(dtoUsuario.getApellido());
        objUsuarioExists.setIdGrupoExpo(dtoUsuario.getIdGrupoExpo());
        objUsuarioExists.setIdRol(dtoUsuario.getIdRol());
        objUsuarioExists.setCorreo(dtoUsuario.getCorreo());
        objUsuarioExists.setIdCargo(dtoUsuario.getIdCargo());

        EntityUsuario objUsuarioEntity = objUsuarioRepo.save(objUsuarioExists);
        return convertirADTO(objUsuarioEntity);
    }

    //Método DELETE
    public boolean deleteUsuario(Long idUsuario){
        try{
            if (idUsuario == null || idUsuario.describeConstable().isEmpty()) {
                throw new IllegalArgumentException("El ID del Usuario no puede ser nulo o vacío");
            }

            boolean exists = objUsuarioRepo.existsById(idUsuario);
            if (!exists) {
                throw new EntityNotFoundException("No se encontró el Usuario con ID: " + idUsuario);
            }

            objUsuarioRepo.deleteById(idUsuario);
            return true;
        }
        catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("No se encontró la Inspección");
        }
    }

    //Conversiones
    private DTOUsuario convertirADTO(EntityUsuario entityUsuario){
        //Creación del objeto a retornar
        DTOUsuario objConvertDTOUser = new DTOUsuario();
        //Datos del Entity al DTO
        objConvertDTOUser.setIdUsuario(entityUsuario.getIdUsuario());
        objConvertDTOUser.setNombre(entityUsuario.getNombre());
        objConvertDTOUser.setApellido(entityUsuario.getApellido());
        objConvertDTOUser.setIdGrupoExpo(entityUsuario.getIdGrupoExpo());
        objConvertDTOUser.setIdRol(entityUsuario.getIdRol());
        objConvertDTOUser.setCorreo(entityUsuario.getCorreo());
        objConvertDTOUser.setContrasena(entityUsuario.getContrasena());
        objConvertDTOUser.setIdCargo(entityUsuario.getIdCargo());

        //Retornamos el objeto DTO
        return objConvertDTOUser;
    }

    private EntityUsuario convertirAEntity(DTOUsuario dtoUsuario){
        EntityUsuario objEntityUsuario = new EntityUsuario();
        objEntityUsuario.setNombre(dtoUsuario.getNombre());
        objEntityUsuario.setApellido(dtoUsuario.getApellido());
        objEntityUsuario.setIdGrupoExpo(dtoUsuario.getIdGrupoExpo());
        objEntityUsuario.setIdRol(dtoUsuario.getIdRol());
        objEntityUsuario.setCorreo(dtoUsuario.getCorreo());
        objEntityUsuario.setContrasena(dtoUsuario.getContrasena());
        objEntityUsuario.setIdCargo(dtoUsuario.getIdCargo());
        return objEntityUsuario;
    }
}