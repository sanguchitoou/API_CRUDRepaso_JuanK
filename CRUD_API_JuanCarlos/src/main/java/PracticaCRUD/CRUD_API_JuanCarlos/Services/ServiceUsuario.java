package PracticaCRUD.CRUD_API_JuanCarlos.Services;

import PracticaCRUD.CRUD_API_JuanCarlos.Models.Repositories.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {

    @Autowired
    RepositoryUsuario objUsuarioRepo;


}
