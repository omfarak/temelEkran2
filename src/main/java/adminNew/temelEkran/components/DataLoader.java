package adminNew.temelEkran.components;

import adminNew.temelEkran.entity.Role;
import adminNew.temelEkran.entity.User;
import adminNew.temelEkran.repository.RoleRepository;
import adminNew.temelEkran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        Role schoolRole = roleRepository.findByName("ROLE_SCHOOL");
        if (schoolRole == null) {
            schoolRole = new Role("ROLE_SCHOOL");
            roleRepository.save(schoolRole);
        }

        Role studentRole = roleRepository.findByName("ROLE_STUDENT");
        if (studentRole == null) {
            studentRole = new Role("ROLE_STUDENT");
            roleRepository.save(studentRole);
        }

        Role schoolAdminRole = roleRepository.findByName("ROLE_SCHOOLADMIN");
        if (schoolAdminRole == null) {
            schoolAdminRole = new Role("ROLE_SCHOOLADMIN");
            roleRepository.save(schoolAdminRole);
        }



        // Kullanıcıları oluşturup rolleri atayın
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("school") == null) {
            User school = new User();
            school.setUsername("school");
            school.setPassword(passwordEncoder.encode("password"));
            school.setRoles(new HashSet<>(Collections.singletonList(schoolRole)));
            userRepository.save(school);
        }

        if (userRepository.findByUsername("student") == null) {
            User student = new User();
            student.setUsername("student");
            student.setPassword(passwordEncoder.encode("password"));
            student.setRoles(new HashSet<>(Collections.singletonList(studentRole)));
            userRepository.save(student);
        }
        if (userRepository.findByUsername("privatschule") == null) {
            User school = new User();
            school.setUsername("privatschule");
            school.setPassword(passwordEncoder.encode("password"));
            school.setRoles(new HashSet<>(Collections.singletonList(schoolRole)));
            userRepository.save(school);
        }
        if (userRepository.findByUsername("info@unitutor.de") == null) {
            User school = new User();
            school.setUsername("info@unitutor.de");
            school.setPassword(passwordEncoder.encode("aydin1971"));
            school.setRoles(new HashSet<>(Collections.singletonList(schoolRole)));
            userRepository.save(school);
        }
        if (userRepository.findByUsername("testschool@gmail.com") == null) {
            User school = new User();
            school.setUsername("testschool@gmail.com");
            school.setPassword(passwordEncoder.encode("password"));
            school.setRoles(new HashSet<>(Collections.singletonList(schoolRole)));
            userRepository.save(school);
        }
        if (userRepository.findByUsername("testschool@gmail.com.admin") == null) {
            User school = new User();
            school.setUsername("testschool@gmail.com.admin");
            school.setPassword(passwordEncoder.encode("password"));
            school.setRoles(new HashSet<>(Collections.singletonList(schoolAdminRole)));
            userRepository.save(school);
        }


    }
}
