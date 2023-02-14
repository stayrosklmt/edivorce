package com.divorce.edivorce.husband;

import com.divorce.edivorce.model.Divorce;
import com.divorce.edivorce.model.DivorceStatus;
import com.divorce.edivorce.model.User;
import com.divorce.edivorce.notary.DivorceResponse;
import com.divorce.edivorce.repository.DivorceRepository;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HusbandService {
    private final DivorceRepository divorceRepository;

    private final UserRepository userRepository;

    public List<DivorceResponse> getDivorce(UUID id) {
        List<Divorce> divorce = this.divorceRepository.findAllByHusbandOne(id);
        List<Divorce> divorce1 = this.divorceRepository.findAllByHusbandTwo(id);
        Set<Divorce> set = new HashSet<>(divorce);
        set.addAll(divorce1);
        List<Divorce> result = new ArrayList<>(set);

        return result
                .stream().map(div -> {
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
                }).collect(Collectors.toList());
    }

    public DivorceResponse signHusband(AcceptDivorce divorce) throws Exception {
        Optional<Divorce> temp = this.divorceRepository.findById(divorce.getId());
        Divorce tempDivorce = temp.get();
        if(!tempDivorce.isSignedByNotary()) throw new Exception("Has not signed by notary");
        if (divorce.isHusbandOne()) {
            tempDivorce.setSignedByHusbandOne(true);
            tempDivorce.setApprovedByHusbandOne(new Date());
        } else {
            tempDivorce.setSignedByHusbandTwo(true);
            tempDivorce.setApprovedByHusbandTwo(new Date());
        }
        if (tempDivorce.isSignedByHusbandOne() == tempDivorce.isSignedByHusbandTwo()) {
            tempDivorce.setStatus(DivorceStatus.SUCCESSFUL_DIVORCE);
            tempDivorce.setDivorceEnd(new Date());
        }

        if(!divorce.isAccept()) {
             tempDivorce.setStatus(DivorceStatus.UNSUCCESSFUL_DIVORCE);
             tempDivorce.setDivorceEnd(new Date());
        }

        //return divorce response
        User lawyerOne = this.userRepository.findUserById(tempDivorce.getLawyerOne());
        User husbandOne = this.userRepository.findUserById(tempDivorce.getHusbandOne());
        User lawyerTwo = this.userRepository.findUserById(tempDivorce.getLawyerTwo());
        User husbandTwo = this.userRepository.findUserById(tempDivorce.getHusbandTwo());
        DivorceResponse divRes = DivorceResponse.builder()
                .id(tempDivorce.getId())
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
                .notary(tempDivorce.getNotary())
                .signedByNotary(tempDivorce.isSignedByNotary())
                .status(tempDivorce.getStatus())
                .marriage(tempDivorce.getMarriage())
                .divorceCreated(tempDivorce.getDivorceCreated())
                .divorceApprovedByNotary(tempDivorce.getDivorceApprovedByNotary())
                .build();

        this.divorceRepository.save(tempDivorce);
        return divRes;
    }
}
