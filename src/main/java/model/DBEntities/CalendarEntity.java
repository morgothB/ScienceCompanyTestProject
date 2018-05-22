package model.DBEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Calendar", schema = "public", catalog = "test_database")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private WorkerEntity worker;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    private List<DateLabelEntity> dateLabels = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEntity that = (CalendarEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (worker != null ? !worker.equals(that.worker) : that.worker != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (worker != null ? worker.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(WorkerEntity worker) {
        this.worker = worker;
    }

    public List<DateLabelEntity> getDateLabels() {
        return dateLabels;
    }

    public void setDateLabels(List<DateLabelEntity> dateLabels) {
        this.dateLabels = dateLabels;
    }

    /*@ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public WorkerEntity getWorkerByWorkerId() {
        return workerByWorkerId;
    }

    public void setWorkerByWorkerId(WorkerEntity workerByWorkerId) {
        this.workerByWorkerId = workerByWorkerId;
    }

    @OneToMany(mappedBy = "calendarByCalendarId")
    public Collection<DateLabelEntity> getDateLablesById() {
        return dateLablesById;
    }*/

}
