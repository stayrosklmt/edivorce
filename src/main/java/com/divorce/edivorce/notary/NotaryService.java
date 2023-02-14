package com.divorce.edivorce.notary;

import com.divorce.edivorce.model.Divorce;
import com.divorce.edivorce.model.DivorceStatus;
import com.divorce.edivorce.model.User;
import com.divorce.edivorce.repository.DivorceRepository;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotaryService {

    private final DivorceRepository divorceRepository;
    private final UserRepository userRepository;

    public List<DivorceResponse> getDivorces() {
        List<Divorce> divorcesList = divorceRepository.findAll();
        return divorcesList
                .stream().map(divorce -> {
                    User lawyerOne = this.userRepository.findUserById(divorce.getLawyerOne());
                    User husbandOne = this.userRepository.findUserById(divorce.getHusbandOne());
                    User lawyerTwo = this.userRepository.findUserById(divorce.getLawyerTwo());
                    User husbandTwo = this.userRepository.findUserById(divorce.getHusbandTwo());
                    DivorceResponse div = DivorceResponse.builder()
                            .id(divorce.getId())
                            .lawyerOneUsername(lawyerOne.getUsername())
                            .lawyerOneFirstname(lawyerOne.getFirstname())
                            .lawyerOneLastname(lawyerOne.getLastname())
                            .husbandOneUsername(husbandOne.getUsername())
                            .husbandOneFirstname(husbandOne.getFirstname())
                            .husbandOneLastname(husbandOne.getLastname())
                            .lawyerTwoUsername(lawyerTwo.getUsername())
                            .lawyerTwoFirstname(lawyerTwo.getFirstname())
                            .lawyerTwoLastname(lawyerTwo.getLastname())
                            .husbandTwoUsername(husbandTwo.getUsername())
                            .husbandTwoFirstname(husbandTwo.getFirstname())
                            .husbandTwoLastname(husbandTwo.getLastname())
                            .notary(divorce.getNotary())
                            .signedByNotary(divorce.isSignedByNotary())
                            .status(divorce.getStatus())
                            .marriage(divorce.getMarriage())
                            .divorceCreated(divorce.getDivorceCreated())
                            .divorceApprovedByNotary(divorce.getDivorceApprovedByNotary())
                            .build();
                    return div;
                }).collect(Collectors.toList());
    }

    public DivorceResponse approvalDivorce(ApprovalRequest request) {
        Optional<Divorce> divorce = this.divorceRepository.findById(request.getDivorceId());
        divorce.get().setSignedByNotary(request.isApproval());
        divorce.get().setNotary(request.getNotaryId());
        divorce.get().setDivorceApprovedByNotary(new Date());
        if (request.isApproval()) {
            divorce.get().setStatus(DivorceStatus.APPROVED);
        } else {
            divorce.get().setStatus(DivorceStatus.NOT_APPROVED);
        }
        Divorce div = this.divorceRepository.save(divorce.get());
        User lawyerOne = this.userRepository.findUserById(div.getLawyerOne());
        User husbandOne = this.userRepository.findUserById(div.getHusbandOne());
        User lawyerTwo = this.userRepository.findUserById(div.getLawyerTwo());
        User husbandTwo = this.userRepository.findUserById(div.getHusbandTwo());
        DivorceResponse divRes = DivorceResponse.builder()
                .id(div.getId())
                .lawyerOneUsername(lawyerOne.getUsername())
                .lawyerOneFirstname(lawyerOne.getFirstname())
                .lawyerOneLastname(lawyerOne.getLastname())
                .husbandOneUsername(husbandOne.getUsername())
                .husbandOneFirstname(husbandOne.getFirstname())
                .husbandOneLastname(husbandOne.getLastname())
                .lawyerTwoUsername(lawyerTwo.getUsername())
                .lawyerTwoFirstname(lawyerTwo.getFirstname())
                .lawyerTwoLastname(lawyerTwo.getLastname())
                .husbandTwoUsername(husbandTwo.getUsername())
                .husbandTwoFirstname(husbandTwo.getFirstname())
                .husbandTwoLastname(husbandTwo.getLastname())
                .notary(div.getNotary())
                .signedByNotary(div.isSignedByNotary())
                .status(div.getStatus())
                .marriage(div.getMarriage())
                .divorceCreated(div.getDivorceCreated())
                .divorceApprovedByNotary(div.getDivorceApprovedByNotary())
                .build();
        return divRes;
    }
}
