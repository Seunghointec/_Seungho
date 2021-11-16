package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //unidirectional bc there is no @ in the course class
    private Course courseActive;
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Course> coursesHistory;


    public Person() {
    }

    public Person(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person(String firstName, String lastName, Gender gender, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.courseActive = course;
    }

    public Person(Integer id, String firstName, String lastName, Gender gender, Course course, List<Course> coursesHistory) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.courseActive = course;
        this.coursesHistory = coursesHistory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Course getCourseActive() {
        return courseActive;
    }

    public void setCourseActive(Course courseActive) {
        this.courseActive = courseActive;
    }

    public List<Course> getCoursesHistory() {
        return coursesHistory;
    }

    public void setCoursesHistory(List<Course> coursesHistory) {
        this.coursesHistory = coursesHistory;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", course=" + courseActive +
                ", courseHistory" + coursesHistory +
                '}';
    }
}
