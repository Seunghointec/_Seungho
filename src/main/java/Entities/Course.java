package Entities;

import javax.persistence.*;
import java.util.List;
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(length =2000)
    private String description;
    private String code;
    private String imageURL;
    private boolean active;
    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Module> modules; //if modules is made first then we need to put cascade in the this class

    public Course() {
    }

    public Course(String name, String description, String code, String imageURL,
                  boolean active) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.imageURL = imageURL;
        this.active = active;
    }

    public Course(String name, String description, String code, String imageURL,
                  boolean active, List<Module> modules) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.imageURL = imageURL;
        this.active = active;
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", active=" + active +
                ", modules=" + modules +
                '}';
    }
}
