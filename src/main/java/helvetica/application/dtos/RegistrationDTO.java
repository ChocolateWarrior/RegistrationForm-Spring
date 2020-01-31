package helvetica.application.dtos;

import helvetica.application.entities.Specification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDTO {

    @NotBlank(message = "{valid.not_blank.reg.not_empty}")
    @Size(min = 2, max = 30, message = "{valid.name.size}")
    private String firstName;

    @NotBlank(message = "{valid.not_blank.reg.not_empty}")
    @Size(min = 2, max = 30, message = "{valid.name.size}")
    private String lastName;

    @NotBlank(message = "{valid.not_blank.reg.not_empty}")
    @Size(min = 2, max = 15, message = "{valid.login.size}")
    private String login;

    @NotBlank(message = "{valid.not_blank.reg.not_empty}")
    @Size(min = 8, max = 50, message = "{valid.password.size}")
    private String password;

    private Set<Specification> specifications;

}
