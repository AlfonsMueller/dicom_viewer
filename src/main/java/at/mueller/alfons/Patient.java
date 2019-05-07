package at.mueller.alfons;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains patient data and the patient's studies list
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="patients")
public class Patient {
    @XmlAttribute
    @Id

    @Column(name="p_id")
    private String patientID;

    @Column(name="p_name")
    private String name;

    @Column(name="p_birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name="p_age")
    private String age;

    // @XmlElement(name = "study")
    // @Column(name="p_s_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="p_id")
    private List<Study> studyList = new ArrayList<Study>();

    public Patient(){}

    /**
     * reads attribute values from dicom object
     * @param dcm
     */
    public Patient(DicomObject dcm){
        // TODO: read all the attribute values from DICOM object
        patientID = dcm.getString(Tag.PatientID);
        name = dcm.getString(Tag.PatientName);
        birthDate = dcm.getDate(Tag.PatientBirthDate);
        age = dcm.getString(Tag.PatientAge);

    }

//    @Override
//    public String toString() {
//        return "Patient{" +
//                "patientID='" + patientID + '\'' +
//                ", name='" + name + '\'' +
//                ", birthDate=" + birthDate + '\'' +
//                ", age='" + age +
//                '}';
//    }

    /**
     * defines equal patients (equal if patientIDs are equal)
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        return patientID != null ? patientID.equals(patient.patientID) : patient.patientID == null;
    }

    @Override
    public int hashCode() {
        return patientID != null ? patientID.hashCode() : 0;
    }

    /**
     * if equal study (like the one passed in parameter)
     * is contained in study list: returns reference to found study
     * if not: adds study to study list and returns reference to this new
     * inserted study
     *
     * @param study to be inserted if not in list
     * @return reference to new inserted study or to equal study already in list
     */
    public Study add(Study study){
        int inx = studyList.indexOf(study);
        if (inx >= 0)
            return studyList.get(inx);
        studyList.add(study);
        return study;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Study> getStudyList() {
        return studyList;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
