package Registry_office.Repository;

import Registry_office.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    //aggiungere la query per la funzione "findByEmail"
    //aggiungere la query per la funzione "findAll" che viene fornita dalla repository
    //creare un modo per rendere il codice usufruibile da tutti quelli che runnano il tuo codice (ovvero modificare il file yml per compilare il programma senza andare a modificare i parametri d'accesso al db)
}
