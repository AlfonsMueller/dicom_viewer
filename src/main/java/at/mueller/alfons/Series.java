package at.mueller.alfons;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * contains informations of series and a list of images
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Series {
    @XmlAttribute
    private String seriesInstanceUID;
    private Date acquisitionTime;

    @XmlElement(name = "instance")
    private List<Instance> instanceList = new ArrayList<Instance>();


    public Series(){}

    public Series(DicomObject dcm){
        // TODO: read all the attribute values from DICOM object
        seriesInstanceUID = dcm.getString(Tag.SeriesInstanceUID);
        acquisitionTime = dcm.getDate(Tag.AcquisitionDateTime);
    }


    @Override
    public String toString() {
        return "Series{" +
                "seriesInstanceUID='" + seriesInstanceUID + '\'' +
                ", acquisitionTime=" + acquisitionTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Series series = (Series) o;

        return seriesInstanceUID != null ? seriesInstanceUID.equals(series.seriesInstanceUID) : series.seriesInstanceUID == null;
    }

    @Override
    public int hashCode() {
        return seriesInstanceUID != null ? seriesInstanceUID.hashCode() : 0;
    }

    public Instance add(Instance image){
        int inx = instanceList.indexOf(image);
        if (inx >= 0)
            return instanceList.get(inx);
        instanceList.add(image);
        return image;
    }

    public String getSeriesInstanceUID() {
        return seriesInstanceUID;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public List<Instance> getInstanceList() {
        return instanceList;
    }
}
