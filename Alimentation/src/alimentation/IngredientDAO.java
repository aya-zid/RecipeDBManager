package alimentation;
import java.sql.*;
import java.util.ArrayList;

public class IngredientDAO extends DAO<Ingredient>{
public IngredientDAO(Connection con)
{
	super(con);
}
public ArrayList<Ingredient> findAll() {
    ArrayList<Ingredient> IngredientList = new ArrayList<Ingredient>();
    

    String sql = "SELECT * FROM Ingredient";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            String ref = rs.getString("RefIngredient");
            String nom = rs.getString("NomIngredient");
            String rt = rs.getString("RefType");
            TypeIngredient ty = getTypeIngredientById(rt);
            Ingredient ing = new Ingredient(ref,nom,ty);
            IngredientList.add(ing);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return IngredientList;
}    
    private TypeIngredient getTypeIngredientById(String rt)
    {
    	TypeIngredient ty = null;

        try {
             PreparedStatement st = con.prepareStatement("SELECT * FROM TypeIngredient WHERE RefType = ?");
            st.setString(1, rt);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                String id = r.getString("RefType");
                String name = r.getString("NomType");
                ty = new TypeIngredient(id, name);
            }

            r.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return ty;
    }
    //*************************************
    public Ingredient find(String id) {
    	Ingredient rg = null;
    	
        // Establish database connection
        try (
        	    Statement stm=this.con.createStatement();
        		
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Ingredient WHERE RefIngredient = ?");
        ){
            stmt.setString(1, id); // Set the id as a parameter in the query
            ResultSet rs = stmt.executeQuery();
            // If a record is found, create a Recipe object
            if (rs.next()) {
                String rid = rs.getString("RefIngredient");
                String rname = rs.getString("NomIngredient");
                String rt = rs.getString("RefType");
                TypeIngredient ty = getTypeIngredientById(rt);
                
                
                // Create the Recipe object with retrieved values
                rg = new Ingredient(rid,rname,ty);

            rs.close();
            } }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rg;
    }
//****************************
    public void create(Ingredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("INSERT INTO Ingredient (RefIngredient,NomIngredient,RefType) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, r.getRefIngredient()); 
            stmt.setString(2, r.getNomIngredient());
            TypeIngredient T=r.getRefType();
            stmt.setString(3,T.getRefType());
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
  //****************************
    public void update(Ingredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("UPDATE Ingredient SET NomIngredient=? ,RefType=? WHERE RefIngredient=?", Statement.RETURN_GENERATED_KEYS);
        ) { 
            stmt.setString(1, r.getNomIngredient());
            TypeIngredient T=r.getRefType();
            stmt.setString(2,T.getRefType());
            stmt.setString(3, r.getRefIngredient());
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
    //****************************
    public void delete(Ingredient r) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("DELETE FROM Ingredient WHERE RefIngredient=?", Statement.RETURN_GENERATED_KEYS);
        ) { 
            stmt.setString(1, r.getRefIngredient());
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
