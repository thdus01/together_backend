package together.study.model;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Data
@Repository
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class vms1365APIVO {
    String listApiType;
    String seq;
    String title;
    String centName;
    String areaName;
    String actPlace;
    String actTypeName;
    int status;
    String teenager;


}
