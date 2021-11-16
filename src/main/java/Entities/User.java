package Entities;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {

    @Id
    private String login;
    private String passwordHash;
    private boolean active;
    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //UML owner


    private Person person;
    private String encryptedPassword;

    public User() {
    }

    public User(String login, String passwordHash, boolean active) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.active = active;
    }

    public User(String login, String passwordHash, boolean active, String encryptedPassword) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.active = active;
        this.encryptedPassword = encryptedPassword;
    }


    public User(String login, String passwordHash, boolean active, String encryptedPassword, Person person) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.active = active;
        this.person = person;
        this.encryptedPassword = encryptedPassword;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(passwordHash, user.passwordHash) && Objects.equals(encryptedPassword, user.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordHash, encryptedPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + passwordHash + '\'' +
                ", active=" + active +
                ", person=" + person +
                ", encryptPassword='" +encryptedPassword + '\'' +
                '}';
    }
}
