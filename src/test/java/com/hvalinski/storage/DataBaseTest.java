package com.hvalinski.storage;

import com.hvalinski.storage.dataBase.*;
import com.hvalinski.storage.repository.PersonRepository;
import com.hvalinski.storage.repository.RolesRepository;
import com.hvalinski.storage.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseTest {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PersonRepository personRepository;


    @Test
    public void addToDatabase() throws Exception {
        try{
            Users users = new Users( "Cher", "321");
            usersRepository.save(users);

            Person person = new Person("Cher Nov","+375 (29) 321-21-12", "eeeemail@mail.ru ");
            personRepository.save(person);

            Roles roles = new Roles("new Role");
            rolesRepository.save(roles);
        }
        catch (Exception e){
            e.printStackTrace();
            fail("Тест провален");
        }
    }


    @Test
    public void changeUserRecord() throws Exception {
        Users users = usersRepository.findByLogin("Cher").orElseThrow();
        users.setLogin("CherNov");
        users.setPassword("newPassword");
        usersRepository.save(users);

        Roles roles = rolesRepository.findById(users.getUser_id()).orElseThrow();
        roles.setRole_name("newRole");
        rolesRepository.save(roles);

        Person person = personRepository.findById(users.getUser_id()).orElseThrow();
        person.setPerson_name("new Name");
        person.setMail("new Email");
        person.setPhone("new Phone");
        personRepository.save(person);

    }

    @Test
    public void readFromDatabase() throws Exception {
        Users user = usersRepository.findByLogin("CherNov").orElseThrow();
        assertEquals("CherNov", user.getLogin());
        assertEquals("newPassword", user.getPassword());

        Person person = personRepository.findById(user.getUser_id()).orElseThrow();
        assertEquals("new Name", person.getPerson_name());
        assertEquals("new Email", person.getMail());
        assertEquals("new Phone", person.getPhone());

    }

    @Test
    public void deleteFromDatabase() throws Exception {
        try{
            Users user = usersRepository.findByLogin("CherNov").orElseThrow();
            long person_id = user.getUser_id();

            usersRepository.delete(user);

            Person person = personRepository.findById(person_id).orElseThrow();
            personRepository.delete(person);
        }
        catch (Exception e){
            e.printStackTrace();
            fail("Тест провален");
        }
    }
}