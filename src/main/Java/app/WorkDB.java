package app;

import java.util.List;

public interface WorkDB <E> {

    public E getLineDB(Integer id);

    public void updateLineDB();

    public void insertLineDB();

    public void deleteLineDB(Integer id);

    public List<E> getDB();

}
