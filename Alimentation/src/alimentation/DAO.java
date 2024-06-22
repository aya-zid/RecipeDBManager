package alimentation;
import java.sql.*;
import java.util.ArrayList;
public abstract class DAO <T> {
protected Connection con=null;
public DAO(Connection con)
{
	this.con=con;
}
public abstract void create(T obj);
public abstract void delete(T obj);
public abstract void update(T obj);
public abstract T find(String id);
public abstract ArrayList<T> findAll();
}
