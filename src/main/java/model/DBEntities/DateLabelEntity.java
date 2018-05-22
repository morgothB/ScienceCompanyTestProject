package model.DBEntities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DateLable", schema = "public", catalog = "test_database")
public class DateLabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "lable_id", nullable = false)
    private LabelEntity label;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateLabelEntity that = (DateLabelEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (calendar != null ? !calendar.equals(that.calendar) : that.calendar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (calendar != null ? calendar.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LabelEntity getLabel() {
        return label;
    }

    public void setLabel(LabelEntity label) {
        this.label = label;
    }

    public CalendarEntity getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarEntity calendar) {
        this.calendar = calendar;
    }

    /* @ManyToOne
    @JoinColumn(name = "lable_id", referencedColumnName = "id", nullable = false)
    public LabelEntity getLableByLableId() {
        return lableByLableId;
    }

    public void setLableByLableId(LabelEntity lableByLableId) {
        this.lableByLableId = lableByLableId;
    }

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "id", nullable = false)
    public CalendarEntity getCalendarByCalendarId() {
        return calendarByCalendarId;
    }
*/

}
