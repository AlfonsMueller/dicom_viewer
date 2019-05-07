package at.mueller.alfons.persistence;

import at.mueller.alfons.Patient;
import at.mueller.alfons.Instance;
import at.mueller.alfons.Series;
import at.mueller.alfons.Study;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class Demo {
   public Demo(){
       try {
           File dicom = new File("images\\SOUS - 702\\IM-0001-0001.dcm");
           DicomInputStream input = new DicomInputStream(dicom);
           DicomObject dcm = input.readDicomObject();
           Patient patient = new Patient(dcm);
           Study study = new Study(dcm);
           Instance image = new Instance(dcm, dicom);
           Series serie = new Series(dcm);


           EntityManager em = Persistence
                   .createEntityManagerFactory("viewer")
                   .createEntityManager();

           em.persist(patient);

           em.getTransaction().begin();
           em.getTransaction().commit();
       }
       catch(java.io.IOException e){
           System.out.println("Error while trying to reach dicom object");
       }
   }









}
