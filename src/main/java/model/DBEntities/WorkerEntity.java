package model.DBEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Worker", schema = "public", catalog = "test_database")
public class WorkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "mid_name")
    private String middleName;

    @Column(name = "nob_particle")
    private String nobParticle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "worker", cascade = CascadeType.ALL)
    private List<CalendarEntity> calendarsId = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dep_id", nullable = false)
    private DepartmentEntity department;

    public WorkerEntity(){}

    public WorkerEntity(String firstName, String middleName, String secondName, String nobParticle, DepartmentEntity department){
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setSecondName(secondName);
        this.setNobParticle(nobParticle);
        this.setDepartment(department);
        department.getWorkers().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerEntity that = (WorkerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (nobParticle != null ? !nobParticle.equals(that.nobParticle) : that.nobParticle != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (nobParticle != null ? nobParticle.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", nobParticle='" + nobParticle + '\'' +
                ", department=" + department.getName() +
                '}';
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNobParticle() {
        return nobParticle;
    }

    public void setNobParticle(String nobParticle) {
        this.nobParticle = nobParticle;
    }

    public List<CalendarEntity> getCalendarsId() {
        return calendarsId;
    }

    public void setCalendarsId(List<CalendarEntity> calendarsId) {
        this.calendarsId = calendarsId;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }


}
