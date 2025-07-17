package PracticaCRUD.CRUD_API_JuanCarlos.Models.Repositories;

import PracticaCRUD.CRUD_API_JuanCarlos.Entities.EntityUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends JpaRepository<EntityUsuario, Long> {
}
