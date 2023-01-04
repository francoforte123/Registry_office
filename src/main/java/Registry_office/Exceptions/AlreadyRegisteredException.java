package Registry_office.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class AlreadyRegisteredException extends RuntimeException {

    private String message;

    public AlreadyRegisteredException(String msg) {
        super(msg);
        this.message= msg;
    }

    public AlreadyRegisteredException() {
    }
}
