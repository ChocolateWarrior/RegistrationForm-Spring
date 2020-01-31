package helvetica.application.dtos;

import helvetica.application.entities.RequestState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDisplayDTO {

    private int id;
    private String specification;
    private String description;
    private LocalDateTime requestTime;
    private int userId;
    private int masterId;
    private LocalDateTime finishTime;
    private BigDecimal price;
    private RequestState state;

}
