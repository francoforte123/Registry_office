package Registry_office.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    @Column(unique = true)
    private String email;
    private String password;
    private String gender;
    private double height;
    private boolean isDeleted;
}
