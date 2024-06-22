package alimentation;
import java.sql.*;
import java.util.ArrayList;

public class RecetteDAO extends DAO<Recette>{
public RecetteDAO(Connection con)
{
	super(con);
}
public ArrayList<Recette> findAll() {
	ArrayList<Recette> RecetteList = new ArrayList<Recette>();
    

    String sql = "SELECT * FROM Recette";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            String ref = rs.getString("RefRecette");
            String nom = rs.getString("NomRecette");
            String desc = rs.getString("DescriptifRecette");
            int cal = rs.getInt("CaloriesRecette");
            String diff = rs.getString("Difficulte");
            int tp = rs.getInt("TempsPreparation");
            int tc = rs.getInt("TempsCuisson");
            int nb = rs.getInt("NbPersonnes");
            
            Recette rec = new Recette(ref,nom,desc,cal,diff,tp,tc,nb);
            RecetteList.add(rec);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return RecetteList;
}
public Recette find(String id) {
	Recette rg = null;

    // Establish database connection
    try (
    	    Statement stm=this.con.createStatement();
    		
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM Recette WHERE RefRecette = ?");
    ) {
        stmt.setString(1, id); // Set the id as a parameter in the query
        ResultSet rs = stmt.executeQuery();
        // If a record is found, create a Recipe object
        if (rs.next()) {
            String rid = rs.getString("RefRecette");
            String rname = rs.getString("NomRecette");
            String desc = rs.getString("DescriptifRecette");
            int cal = rs.getInt("CaloriesRecette");
            String diff = rs.getString("Difficulte");
            int tp = rs.getInt("TempsPreparation");
            int tc = rs.getInt("TempsCuisson");
            int nb = rs.getInt("NbPersonnes");
            
            // Create the Recipe object with retrieved values
            rg = new Recette(rid, rname,desc,cal,diff,tp,tc,nb);
        }

        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return rg;
}
//*******************************
public void create(Recette r) {
    try (
         PreparedStatement stmt = this.con.prepareStatement("INSERT INTO Recette (RefRecette,NomRecette,DescriptifRecette,CaloriesRecette,Difficulte,TempsPreparation,TempsCuisson,NbPersonnes) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
    ) {
        stmt.setString(1, r.getRefRecette()); 
        stmt.setString(2, r.getNomRecette()); 
        stmt.setString(3, r.getDescriptifRecette()); 
        stmt.setInt(4, r.getCaloriesRecette());
        stmt.setString(5, r.getDifficulte()); 
        stmt.setInt(6, r.getTempsPreparation()); 
        stmt.setInt(7, r.getTempsCuisson()); 
        stmt.setInt(8, r.getNbPersonnes());
        
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
//*******************************
public void update(Recette r) {
  try (
       PreparedStatement stmt = this.con.prepareStatement("UPDATE Recette SET NomRecette=?,DescriptifRecette=?,CaloriesRecette=?,Difficulte=?,TempsPreparation=?,TempsCuisson=?,NbPersonnes=? WHERE RefRecette=?", Statement.RETURN_GENERATED_KEYS);
  ) {

      stmt.setString(1, r.getNomRecette()); 
      stmt.setString(2, r.getDescriptifRecette()); 
      stmt.setInt(3, r.getCaloriesRecette());
      stmt.setString(4, r.getDifficulte()); 
      stmt.setInt(5, r.getTempsPreparation()); 
      stmt.setInt(6, r.getTempsCuisson()); 
      stmt.setInt(7, r.getNbPersonnes());
      stmt.setString(8, r.getRefRecette()); 
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
//*******************************
public void delete(Recette r) {
try (
     PreparedStatement stmt = this.con.prepareStatement("DELETE FROM Recette WHERE RefRecette=?", Statement.RETURN_GENERATED_KEYS);
) {
    stmt.setString(1, r.getRefRecette()); 
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
