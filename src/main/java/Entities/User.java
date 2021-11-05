package Entities;

import Service.AttributeEncryptor;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "User")
public class User {

    @Id
    private String login;
    private String password;
    private boolean active;
    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //UML indicate the user is the parent class
    private Person person;
    //@Convert(converter = AttributeEncryptor.class)
    private String encryptedPassword;

    public User() {
    }

    public User(String login, String password, boolean active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public User(String login, String password, boolean active, String encryptedPassword) {
        this.login = login;
        this.password = password;
        this.active = active;
        this.encryptedPassword = encryptedPassword;
    }


    public User(String login, String password, boolean active, Person person) {
        this.login = login;
        this.password = password;
        this.active = active;
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String encrypt(String password) {
        /* Plain-text password initialization. */
        password = "myPassword";
        String encryptedPassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                stringBuilder.append(Integer
                        .toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            /* Complete hashed password in hexadecimal format */
            encryptedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", person=" + person +
                ", encryptPassword='" +encryptedPassword + '\'' +
                '}';
    }
}
