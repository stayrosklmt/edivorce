package com.divorce.edivorce.lawyer;

import com.divorce.edivorce.model.Divorce;
import com.divorce.edivorce.model.DivorceStatus;
import com.divorce.edivorce.repository.DivorceRepository;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LawyerService {
    private final UserRepository userRepository;
    private final DivorceRepository divorceRepository;

    public DivorceApplicationResponse initDivorce(DivorceRequest request) {

        Date divorceDate = new Date();
        if(request.getLawyerOne().equals(request.getLawyerTwo())){
            return DivorceApplicationResponse.builder()
                    .status(DivorceStatus.NOT_ALLOW_SAME_LAWYER)
                    .build();
        }

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -6);
        Date sixMonthsAgo = calendar.getTime();
        System.err.println(request.getMarriageDate().before(sixMonthsAgo));
        if (!request.getMarriageDate().before(sixMonthsAgo)) {
            return DivorceApplicationResponse.builder()
                    .status(DivorceStatus.UNSUCCESSFUL_DIVORCE)
                    .build();
        }

        System.err.println(request.getMarriageDate());
        Divorce divorce = Divorce.builder()
                .lawyerOne(userRepository.findByUsername(request.getLawyerOne()).get().getId())
                .lawyerTwo(userRepository.findByUsername(request.getLawyerTwo()).get().getId())
                .husbandOne(userRepository.findByUsername(request.getHusbandOne()).get().getId())
                .husbandTwo(userRepository.findByUsername(request.getHusbandTwo()).get().getId())
                .status(DivorceStatus.PENDING)
                .marriage(request.getMarriageDate())
                .divorceCreated(divorceDate)
                .build();

        divorceRepository.save(divorce);

        return new DivorceApplicationResponse().builder()
                .lawyerOne(request.getLawyerOne())
                .lawyerTwo(request.getLawyerTwo())
                .husbandOne(request.getHusbandOne())
                .husbandTwo(request.getHusbandTwo())
                .status(DivorceStatus.PENDING)
                .marriageDate(request.getMarriageDate())
                .divorceCreated(divorceDate)
                .build();
    }
}
