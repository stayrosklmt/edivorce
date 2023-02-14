package com.divorce.edivorce.lawyer;

import com.divorce.edivorce.model.DivorceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivorceApplicationResponse {
    private DivorceStatus status;
    private String lawyerOne;
    private String husbandOne;
    private String lawyerTwo;
    private String husbandTwo;
    private Date marriageDate;
    private Date divorceCreated;

}
