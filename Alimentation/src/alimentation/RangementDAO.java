package alimentation;
import java.sql.*;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class RangementDAO extends DAO<Rangement>{
public RangementDAO(Connection con)
{
	super(con);
}
public ArrayList<Rangement> findAll() {
    ArrayList<Rangement> RangementList = new ArrayList<Rangement>();
    

    String sql = "SELECT * FROM Rangement";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
        	String ref = rs.getString("RefRangement");
            String nom = rs.getString("NomRangement");
            
            Rangement rang = new Rangement(ref, nom);
            RangementList.add(rang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return RangementList;
}
//****************
    public Rangement find(String id) {
    	Rangement rg = null;
        // Establish database connection
        try (
        	 //Statement stm=this.con.createStatement();  		
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Rangement WHERE RefRangement = ?");
        ) {
            stmt.setString(1, id); // Set the id as a parameter in the query
            ResultSet rs = stmt.executeQuery();
            // If a record is found, create a Recipe object
            if (rs.next()) {
            	String rid = rs.getString("RefRangement");
                String rname = rs.getString("NomRangement");
                rg = new Rangement(rid, rname);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rg;
    }
 //*************************
    public void create(Rangement r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("INSERT INTO Rangement (RefRangement, NomRangement) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, r.getRefRangement()); 
            stmt.setString(2, r.getNomRangement()); 
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) { 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //*************************
    public void update(Rangement r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("UPDATE Rangement SET NomRangement=? WHERE RefRangement=?", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, r.getNomRangement());
            stmt.setString(2, r.getRefRangement());  
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) { 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //*************************
    public void delete(Rangement r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("DELETE FROM Rangement WHERE RefRangement=?", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, r.getRefRangement());  
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) { 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



