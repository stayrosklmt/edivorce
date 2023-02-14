package com.divorce.edivorce.notary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalRequest {
    private UUID divorceId;
    private UUID notaryId;
    private boolean approval;
}
