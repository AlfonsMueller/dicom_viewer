package at.mueller.alfons.persistence;

import at.mueller.alfons.Study;

import java.util.List;

public interface IStudyRepository {
    /**
     * saves the study to the database
     * @param study Study to be saved
     * @throws Exception
     */
    public void save(Study study) throws Exception;

    /**
     * retrieves all studies from the database
     * @return list of all saved studies
     * @throws Exception
     */
    public List<Study> allStudies() throws Exception;


}
