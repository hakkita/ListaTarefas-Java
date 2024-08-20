package ifsc.ctds.tarefa.dao;

import java.util.List;

public interface DAO<T> {
	Object get(Long id);
	
	List<T> getAll();
	
	int create(T t);
	
	boolean update(T t, String[] params);
	
	boolean delete(T t);
}
