package helvetica.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private String specification;
    private String description;
    private LocalDateTime requestTime = LocalDateTime.now();
}
