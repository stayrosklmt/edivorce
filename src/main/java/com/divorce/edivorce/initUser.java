package com.divorce.edivorce;

import com.divorce.edivorce.model.Role;
import com.divorce.edivorce.model.User;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class initUser implements CommandLineRunner{
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstname("Jhon")
                .lastname("Krol")
                .socialSecurityNumber("471-33-1921")
                .taxIdentificationNumber("720-50-0327")
                .role(Role.ADMIN)
                .build();

        User husband = User.builder()
                .username("dcovetj")
                .password(passwordEncoder.encode("dcovetj_pass"))
                .firstname("Davin")
                .lastname("Covet")
                .socialSecurityNumber("633-32-8918")
                .taxIdentificationNumber("747-43-6080")
                .role(Role.USER)
                .build();

        User wife = User.builder()
                .username("whuglinz")
                .password(passwordEncoder.encode("whuglinz_pass"))
                .firstname("Waldo")
                .lastname("Huglin")
                .socialSecurityNumber("411-54-9100")
                .taxIdentificationNumber("559-66-3723")
                .role(Role.USER)
                .build();

        User husbandLwayer = User.builder()
                .username("mschopsn")
                .password(passwordEncoder.encode("mschopsn_pass"))
                .firstname("Madaline")
                .lastname("Schops")
                .socialSecurityNumber("149-01-1918")
                .taxIdentificationNumber("779-69-2221")
                .role(Role.LAWYER)
                .build();

        User wifeLawyer = User.builder()
                .username("rmaskew12")
                .password(passwordEncoder.encode("rmaskew12_pass"))
                .firstname("Roberto")
                .lastname("Maskew")
                .socialSecurityNumber("374-29-5801")
                .taxIdentificationNumber("773-84-5647")
                .role(Role.LAWYER)
                .build();

        User notary = User.builder()
                .username("gdenk1b")
                .password(passwordEncoder.encode("gdenk1b_pass"))
                .firstname("Ginger")
                .lastname("Denk")
                .socialSecurityNumber("569-58-8779")
                .taxIdentificationNumber("318-39-7104")
                .role(Role.NOTARY)
                .build();

        this.userRepository.saveAll(List.of(admin, husband, husbandLwayer, wife, wifeLawyer, notary));
    }
}
