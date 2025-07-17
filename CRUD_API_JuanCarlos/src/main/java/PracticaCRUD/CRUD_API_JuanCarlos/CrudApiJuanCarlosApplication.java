package PracticaCRUD.CRUD_API_JuanCarlos;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApiJuanCarlosApplication {

	public static void main(String[] args) {
		//Cargar variables del archivo .env al sistema
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		//Código que permite que la API arranque correctamente, no se borra vro
		SpringApplication.run(CrudApiJuanCarlosApplication.class, args);
	}
}
