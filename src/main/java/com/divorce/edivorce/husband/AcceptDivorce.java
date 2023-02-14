package com.divorce.edivorce.husband;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptDivorce {
    private UUID id;
    private boolean husbandOne;
    private boolean accept;
}
