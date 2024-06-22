package alimentation;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
public class ProduitDAO extends DAO<Produit>{
public ProduitDAO(Connection con)
{
	super(con);
}
public ArrayList<Produit> findAll() {
    ArrayList<Produit> ProduitList = new ArrayList<Produit>();
    

    String sql = "SELECT * FROM Produit";
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
     stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            String a = rs.getString("RefProduit");
            String b = rs.getString("DescriptifProduit");
        	Date c=rs.getDate("DatePremption");
        	int d = rs.getInt("QuantiteProduit");
        	int e = rs.getInt("PrixProduit");
            String rt1 = rs.getString("RefRangement");
            String rt2 = rs.getString("RefIngredient");
            Rangement ty1 = getRangementById(rt1);
            Ingredient ty2 = getIngredientById(rt2);
            
            Produit pr = new Produit(a,b,c,d,e,ty1,ty2);
            ProduitList.add(pr);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
   return ProduitList;
}    
    private Rangement getRangementById(String rt)
    {
    	Rangement ty = null;

        try {
             PreparedStatement st = con.prepareStatement("SELECT * FROM Rangement WHERE RefRangement = ?");
            st.setString(1, rt);
            ResultSet r = st.executeQuery();

            if (r.next()) {
                String a = r.getString("RefRangement");
                String b = r.getString("NomRangement");
                ty = new Rangement(a,b);
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
             PreparedStatement st = con.prepareStatement("SELECT * FROM Ingredient WHERE RefIngredient = ?");
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
//*****************************   
   public Produit find(String id) {
    	
	   Produit pr=null;
        // Establish database connection
        try (
        	    Statement stm=this.con.createStatement();
        		
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM Produit WHERE RefProduit = ?");
        ){
            stmt.setString(1, id); // Set the id as a parameter in the query
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	 String a = rs.getString("RefProduit");
                 String b = rs.getString("DescriptifProduit");
             	Date c=rs.getDate("DatePremption");
             	int d = rs.getInt("QuantiteProduit");
             	int e = rs.getInt("PrixProduit");
                 String rt1 = rs.getString("RefRangement");
                 String rt2 = rs.getString("RefIngredient");
                 Rangement ty1 = getRangementById(rt1);
                 Ingredient ty2 = getIngredientById(rt2);
                 
                 pr = new Produit(a,b,c,d,e,ty1,ty2);
     

            rs.close();
            } }
        catch (SQLException e) {
            e.printStackTrace();
        }
		return pr;
    }
 //**********************
   public void create(Produit p) {
       try (
            PreparedStatement stmt = this.con.prepareStatement("INSERT INTO Produit (RefProduit,DescriptifProduit,DatePremption,QuantiteProduit,PrixProduit,RefRangement,RefIngredient) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
       ) {
           stmt.setString(1, p.getRefProduit()); 
           stmt.setString(2, p.getDescriptifProduit());
           stmt.setDate(3,new java.sql.Date(p.getDatePremption().getTime()));
           stmt.setInt(4, p.getQuantiteProduit()); 
           stmt.setInt(5, p.getPrixProduit());
           Rangement T=p.getRefRangement();
           stmt.setString(6,T.getRefRangement());
           Ingredient I=p.getRefIngredient();
           stmt.setString(7,I.getRefIngredient());
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
   //**********************
   public void update(Produit p) {
       try (
            PreparedStatement stmt = this.con.prepareStatement("UPDATE Produit SET DescriptifProduit=? ,DatePremption=? ,QuantiteProduit=?,PrixProduit=?,RefRangement=?,RefIngredient=? WHERE RefProduit=?", Statement.RETURN_GENERATED_KEYS);
       ) {
            
           stmt.setString(1, p.getDescriptifProduit());
           stmt.setDate(2,new java.sql.Date(p.getDatePremption().getTime()));
           stmt.setInt(3, p.getQuantiteProduit()); 
           stmt.setInt(4, p.getPrixProduit());
           Rangement T=p.getRefRangement();
           stmt.setString(5,T.getRefRangement());
           Ingredient I=p.getRefIngredient();
           stmt.setString(6,I.getRefIngredient());
           stmt.setString(7, p.getRefProduit());
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
   //**********************
   public void delete(Produit p) {
       try (
            PreparedStatement stmt = this.con.prepareStatement("DELETE FROM Produit WHERE RefProduit=?", Statement.RETURN_GENERATED_KEYS);
       ) {
           stmt.setString(1, p.getRefProduit());
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
