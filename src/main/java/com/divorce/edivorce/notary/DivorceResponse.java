package com.divorce.edivorce.notary;

import com.divorce.edivorce.model.DivorceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivorceResponse {
    private UUID id;
    private String lawyerOneUsername;
    private String lawyerOneFirstname;
    private String lawyerOneLastname;
    private String husbandOneUsername;
    private String husbandOneFirstname;
    private String husbandOneLastname;
    private String lawyerTwoUsername;
    private String lawyerTwoFirstname;
    private String lawyerTwoLastname;
    private String husbandTwoUsername;
    private String husbandTwoFirstname;
    private String husbandTwoLastname;
    private UUID notary;
    private boolean signedByNotary;
    private DivorceStatus status;
    private Date marriage;
    private Date divorceCreated;
    private Date divorceApprovedByNotary;
}
