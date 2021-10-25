package com.sweta.learn;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sweta.learn.beans.User;
import com.sweta.learn.beans.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
    
    //testmethods
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("rajkumar@gmail.com");
        user.setPassword("raj2020");
        user.setName("RajKumar");
        user.setGender("Male");
        user.setAddress("Behala");
        user.setPhoneno(123456789);
        user.setDob("5/5/1993");
        user.setJoiningdate("20/10/2021");
        User savedUser = repo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getId());
         
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
         
    }
}
