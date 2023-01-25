package Registry_office.Repository;

import Registry_office.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where email= ? and is_deleted= false", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "select * from users where is_deleted= false", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "select * from users where id= ? and is_deleted= false", nativeQuery = true)
    Optional<User> getById(long id);

    @Query(value = "select * from users where email= ? and is_deleted= true", nativeQuery = true)
    Optional<User> findIfExistTheUserByEmail(String email);

    @Query(value = "select * from users where is_deleted= true", nativeQuery = true)
    List<User> getAllUsersDeteled();
}
//creare se fosse possibile i test per le ultime due funzioni create