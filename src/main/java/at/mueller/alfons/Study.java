package at.mueller.alfons;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains data of the study and a list of series
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="studies")
public class Study {
    @XmlAttribute
    @Id
    @Column(name="s_id")
    private String studyInstanceUID;
    @Column(name="s_date")
    private Date studyDate;
    @Column(name="s_description")
    private String studyDescription;

    @XmlElement(name = "series")
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="s_id")
    private List<Series> seriesList = new ArrayList<Series>();

    public Study(){}

    public Study(DicomObject dcm){
        // TODO: read all the attribute values from DICOM object
        studyInstanceUID = dcm.getString(Tag.StudyInstanceUID);
        studyDate = dcm.getDate(Tag.StudyDate);
        studyDescription = dcm.getString(Tag.StudyDescription);
    }


    @Override
    public String toString() {
        return "Study{" +
                "studyInstanceUID='" + studyInstanceUID + '\'' +
                ", studyDate=" + studyDate +", StudyDescription= "+studyDescription+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Study study = (Study) o;

        return studyInstanceUID != null ? studyInstanceUID.equals(study.studyInstanceUID) : study.studyInstanceUID == null;
    }

    @Override
    public int hashCode() {
        return studyInstanceUID != null ? studyInstanceUID.hashCode() : 0;
    }

    public Series add(Series series) {
        int inx = seriesList.indexOf(series);
        if (inx >= 0)
            return seriesList.get(inx);
        seriesList.add(series);
        return series;
    }

    public String getStudyInstanceUID() {
        return studyInstanceUID;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }

    public String getStudyDescription(){return studyDescription;}

    public void setStudyInstanceUID(String studyInstanceUID) {
        this.studyInstanceUID = studyInstanceUID;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }
}
