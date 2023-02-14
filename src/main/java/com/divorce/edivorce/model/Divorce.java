package com.divorce.edivorce.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "divorce")
public class Divorce {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "lawyer_one")
    private UUID lawyerOne;
    @Column(name = "husband_one")
    private UUID husbandOne;
    @Column(name = "lawyer_two")
    private UUID lawyerTwo;
    @Column(name = "husband_two")
    private UUID husbandTwo;
    private UUID notary;
    @Column(name = "signed_by_notary")
    private boolean signedByNotary;
    @Column(name = "signed_by_husband_one")
    private boolean signedByHusbandOne;
    @Column(name = "signed_by_husband_two")
    private boolean signedByHusbandTwo;
    private DivorceStatus status;
    @Column(name = "marriage_date")
    private Date marriage;
    @Column(name = "divorce_created")
    private Date divorceCreated;
    @Column(name = "divorce_approved_by_notary")
    private Date divorceApprovedByNotary;
    @Column(name = "approved_by_husband_one")
    private Date approvedByHusbandOne;
    @Column(name = "approved_by_husband_two")
    private Date approvedByHusbandTwo;
    @Column(name = "divorce_end")
    private Date divorceEnd;
}
