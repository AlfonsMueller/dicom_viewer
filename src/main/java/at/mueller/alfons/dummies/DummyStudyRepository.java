package at.mueller.alfons.dummies;

import at.mueller.alfons.Study;
import at.mueller.alfons.persistence.IStudyRepository;

import java.util.ArrayList;
import java.util.List;

public class DummyStudyRepository implements IStudyRepository {
    List<Study> list = new ArrayList<>();

    @Override
    public void save(Study study) throws Exception {
        list.add(study);

    }

    @Override
    public List<Study> allStudies() throws Exception {
        return list;
    }
}
