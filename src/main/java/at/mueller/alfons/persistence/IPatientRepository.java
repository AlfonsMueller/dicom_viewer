package at.mueller.alfons.persistence;

import at.mueller.alfons.Patient;

import java.util.List;

public interface IPatientRepository {
    /**
     * Saves the patient to the database;
     * this means:
     *      insert if the patient does not exist yet in the database
     *      update if it does
     *
     * @param p reference to the patient to be saved
     * @throws Exception in case of database errors
     */
    public void save(Patient p) throws Exception;


    /**
     * retrieves all the patients available in the database,
     * inserts a Patient-instance into a list for each data set
     * and returns the list to the caller
     * @return list of patients
     * @throws Exception in case of database errors
     */
    public List<Patient> allPatients() throws Exception;

}
