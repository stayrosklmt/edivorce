package com.divorce.edivorce.lawyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivorceRequest {
    private String lawyerOne;
    private String husbandOne;
    private String lawyerTwo;
    private String husbandTwo;
    private Date marriageDate;
}
