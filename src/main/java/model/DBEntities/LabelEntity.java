package model.DBEntities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Label", schema = "public", catalog = "test_database")
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "label_tag", nullable = false)
    private String labelTag;

    @Column(name = "label_explanation")
    private String labelExplanation;

    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL)
    private Collection<DateLabelEntity> dateLabels;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelEntity that = (LabelEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (labelTag != null ? !labelTag.equals(that.labelTag) : that.labelTag != null) return false;
        if (labelExplanation != null ? !labelExplanation.equals(that.labelExplanation) : that.labelExplanation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (labelTag != null ? labelTag.hashCode() : 0);
        result = 31 * result + (labelExplanation != null ? labelExplanation.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelTag() {
        return labelTag;
    }

    public void setLabelTag(String labelTag) {
        this.labelTag = labelTag;
    }

    public String getLabelExplanation() {
        return labelExplanation;
    }

    public void setLabelExplanation(String labelExplanation) {
        this.labelExplanation = labelExplanation;
    }

    public Collection<DateLabelEntity> getDateLabels() {
        return dateLabels;
    }

    public void setDateLabels(Collection<DateLabelEntity> dateLabels) {
        this.dateLabels = dateLabels;
    }
}
