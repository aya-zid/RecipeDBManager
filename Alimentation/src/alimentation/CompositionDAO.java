package alimentation;
import java.sql.*;
import java.util.ArrayList;

public class CompositionDAO extends DAO<Composition>{
public CompositionDAO(Connection con)
{
	super(con);
}
public ArrayList<Composition> findAll() {
    ArrayList<Composition> CompositionList = new ArrayList<Composition>();
    

    String sql = "SELECT * FROM Composition";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            int ref = rs.getInt("RefComposition");
            int qte = rs.getInt("QuantiteComposition");
            String rt1 = rs.getString("RefRecette");
            String rt2 = rs.getString("RefIngredient");
            Recette ty1 = getRecetteById(rt1);
            Ingredient ty2 = getIngredientById(rt2);
            
            Composition comp = new Composition(ref,qte,ty1,ty2);
            CompositionList.add(comp);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return CompositionList;
}    
    private Recette getRecetteById(String rt)
    {
    	Recette ty = null;

        try {
             PreparedStatement st = con.prepareStatement("SELECT * FROM Recette WHERE RefRecette = ?");
            st.setString(1, rt);
            ResultSet r = st.executeQuery();

            if (r.next()) {
                String a = r.getString("RefRecette");
                String b = r.getString("NomRecette");
                String c = r.getString("DescriptifRecette");
                int d = r.getInt("CaloriesRecette");
                String e = r.getString("Difficulte");
                int f = r.getInt("TempsPreparation");
                int g = r.getInt("TempsCuisson");
                int h = r.getInt("NbPersonnes");
                ty = new Recette(a,b,c,d,e,f,g,h);
            }

            r.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return ty;
    }
    //*************************************
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

    //*******************************************
    private Ingredient getIngredientById(String rt)
    {
    	Ingredient ty = null;

        try {
             PreparedStatement st = con.prepareStatement("SELECT * FROM Ingredient WHERE RefIngredient= ?");
            st.setString(1, rt);
            ResultSet r = st.executeQuery();

            if (r.next()) {
                String a = r.getString("RefIngredient");
                String b = r.getString("NomIngredient");
                String s = r.getString("RefType");
                TypeIngredient c=getTypeIngredientById(s);
                ty = new Ingredient(a,b,c);
            }

            r.close();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return ty;
    }
    
    
    
    
    
    //********************************
    public Composition find(String id) {
    	
    	Composition comp=null;
        // Establish database connection
        try (
        	   // Statement stm=this.con.createStatement();
        		
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Composition WHERE RefComposition = ?");
        ){
            stmt.setString(1, id); // Set the id as a parameter in the query
            ResultSet rs = stmt.executeQuery();
            // If a record is found, create a Recipe object
            if (rs.next()) {
            	 int ref = rs.getInt("RefComposition");
                 int qte = rs.getInt("QuantiteComposition");
                 String rt1 = rs.getString("RefRecette");
                 String rt2 = rs.getString("RefIngredient");
                 Recette ty1 = getRecetteById(rt1);
                 Ingredient ty2 = getIngredientById(rt2);
                 
                  comp = new Composition(ref,qte,ty1,ty2);

            rs.close();
            } }
        catch (SQLException e) {
            e.printStackTrace();
        }

        
		return comp;
    }
  //****************************
    public void create(Composition c) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("INSERT INTO Composition (RefComposition,QuantiteComposition,RefRecette,RefIngredient) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, c.getRefComposition()); 
            stmt.setInt(2, c.getQuantiteComposition());
            Recette T=c.getRefRecette();
            stmt.setString(3,T.getRefRecette());
            Ingredient I=c.getRefIngredient();
            stmt.setString(4,I.getRefIngredient());
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
    public void update(Composition c) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("UPDATE Composition QuantiteComposition=?,RefRecette=?,RefIngredient=? WHERE RefComposition=?", Statement.RETURN_GENERATED_KEYS);
         ) {
            
            stmt.setInt(1, c.getQuantiteComposition());
            Recette T=c.getRefRecette();
            stmt.setString(2,T.getRefRecette());
            Ingredient I=c.getRefIngredient();
            stmt.setString(3,I.getRefIngredient());
            stmt.setInt(4, c.getRefComposition()); 
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
    public void delete(Composition c) {
        try (
             PreparedStatement stmt = this.con.prepareStatement("DELETE FROM Composition WHERE RefComposition=?", Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, c.getRefComposition()); 
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

