package alimentation;
import java.sql.*;
import java.util.ArrayList;

public class TypeIngredientDAO extends DAO<TypeIngredient>{
public TypeIngredientDAO(Connection con)
{
	super(con);
}
public ArrayList<TypeIngredient> findAll() {
	ArrayList<TypeIngredient> TypeIngredientList = new ArrayList<TypeIngredient>();
    
    String sql = "SELECT * FROM TypeIngredient";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            String ref = rs.getString("RefType");
            String nom = rs.getString("NomType");
            
            TypeIngredient tying = new TypeIngredient(ref, nom);
            TypeIngredientList.add(tying);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return TypeIngredientList;
}

//****************
    public TypeIngredient find(String id) {
    	TypeIngredient rg = null;

        // Establish database connection
        try (
        	    Statement stm=this.con.createStatement();
        		
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TypeIngredient WHERE RefType = ?");
        ) {
            stmt.setString(1, id); // Set the id as a parameter in the query
            ResultSet rs = stmt.executeQuery();
            // If a record is found, create a Recipe object
            if (rs.next()) {
                String rid = rs.getString("RefType");
                String rname = rs.getString("NomType");
                // Create the Recipe object with retrieved values
                rg = new TypeIngredient(rid, rname);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rg;
    }
//**********************************
    public void create(TypeIngredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("INSERT INTO TypeIngredient (RefType,NomType) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, r.getRefType()); 
            stmt.setString(2, r.getNomType()); 
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
  //**********************************
    public void update(TypeIngredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("UPDATE TypeIngredient SET NomType=? WHERE RefType=?", Statement.RETURN_GENERATED_KEYS);
        ) { 
            stmt.setString(1, r.getNomType()); 
            stmt.setString(2, r.getRefType());
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
    //**********************************
    public void delete(TypeIngredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("DELETE FROM TypeIngredient WHERE RefType=?", Statement.RETURN_GENERATED_KEYS);
        ) { 
            stmt.setString(1, r.getRefType());
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
