package alimentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.*;
public class Main {

	public static void main(String[] args) {
		DBMSConnection cnx=new DBMSConnection();
//*********************************************************************************
		JPanel panel = new JPanel(new GridLayout(3,3));
		 JFrame frame = new JFrame("Requetes");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.add(panel);frame.pack();frame.setVisible(true);
//*********Q1************************************************************************
		 System.out.println("RQ1 :");
        RecetteDAO r1 = new RecetteDAO(cnx.getConnection());
        ArrayList<Recette> recipes = r1.findAll();
        ArrayList<String> q1 = new ArrayList<>();
        for (Recette recipe : recipes) {
            if (recipe.getCaloriesRecette() > 300) {
                q1.add(recipe.getNomRecette());
            }
        }
        System.out.println("Nomss de recettes de plus que 300 calories :");
        for (String recipeName : q1) {
            System.out.println(recipeName);
        }
        System.out.println("");
//*
		 DefaultTableModel model1 = new DefaultTableModel();
		 model1.setColumnIdentifiers(new String[] { "Noms de recettes de plus que 300 calories :" });
		 for (Recette recipe : recipes) {
		     if (recipe.getCaloriesRecette() > 300) {
		         model1.addRow(new Object[] { recipe.getNomRecette() });
		     }
		 }
		 JTable table1 = new JTable(model1);
		 JScrollPane scrollPane1 = new JScrollPane(table1);
		 panel.add(scrollPane1);
		       
//*********Q2**************************************************************************************
        System.out.println("RQ2 :");
        ProduitDAO r2 = new ProduitDAO(cnx.getConnection());
        ArrayList<Produit> pr = r2.findAll();
        ArrayList<String> q2 = new ArrayList<>();
        for (Produit i : pr) {
            if (i.getRefRangement().getNomRangement().compareTo("réfrigérateur")==0) {
                q2.add(i.getDescriptifProduit());
            }
        }
        System.out.println("Descriptifs des produits présents dans le réfrigérateur :");
        for (String j : q2) {
            System.out.println(j);
        } 
        System.out.println("");
//*
		 DefaultTableModel model2 = new DefaultTableModel();
		 model2.setColumnIdentifiers(new String[] { "Descriptifs des produits présents dans le réfrigérateur :" });
	     for (Produit i : pr) {
	            if (i.getRefRangement().getNomRangement().compareTo("réfrigérateur")==0) {
	            	model2.addRow(new Object[] { i.getDescriptifProduit() });
	            }
	     }
		 JTable table2 = new JTable(model2);
		 JScrollPane scrollPane2 = new JScrollPane(table2);
		 panel.add(scrollPane2);
        
//*********Q3********************************************************************************************
        System.out.println("RQ3 :");
        CompositionDAO r3 = new CompositionDAO(cnx.getConnection());
        ArrayList<Composition> comp = r3.findAll();
        ArrayList<String> q3 = new ArrayList<>();
        for (Composition i : comp) {
            if (i.getRefIngredient().getRefType().getNomType().compareTo("fruit de mer")==0) {
                q3.add(i.getRefRecette().getNomRecette());
            }
        }
        System.out.println(" les noms de recettes a la base de fruits de mer :");
        for (String j : q3) {
            System.out.println(j);
        } 
        System.out.println("");
//*
		 DefaultTableModel model3 = new DefaultTableModel();
		 model3.setColumnIdentifiers(new String[] { "les noms de recettes a la base de fruits de mer :" });
	     for (Composition i : comp) {
	            if (i.getRefIngredient().getRefType().getNomType().compareTo("fruit de mer")==0) {
	            	model3.addRow(new Object[] { i.getRefRecette().getNomRecette() });
	            }
	     }
		 JTable table3 = new JTable(model3);
		 JScrollPane scrollPane3 = new JScrollPane(table3);
		 panel.add(scrollPane3);
//*********Q4********************************************************************************************
        System.out.println("RQ4 :");
        ArrayList<String> ingr = new ArrayList<>();
        ArrayList<String> q4 = new ArrayList<>();
        for (Composition i : comp) {
            if (i.getRefRecette().getRefRecette().compareTo("r0002")==0) {
                ingr.add(i.getRefIngredient().getRefIngredient());
            }
        }
        for (Produit i : pr) {
        	for (String j : ingr)
        	{
        		 if (i.getRefIngredient().getRefIngredient().compareTo(j)==0) {
        		   q4.add(i.getRefRangement().getNomRangement());
        		   }        		 
        	}

        }
        System.out.println(" les noms de rangements dans lesquels nous trouvons certains des ingredients necessaires a la recette  r0002  :");
        for (String j : q4) {
            System.out.println(j);
        } 
        System.out.println("");
//*
		 DefaultTableModel model4 = new DefaultTableModel();
		 model4.setColumnIdentifiers(new String[] { " les noms de rangements dans lesquels nous trouvons certains des ingredients necessaires a la recette  r0002  :" });
	     for (Produit i : pr) {
	    	 for(String j : ingr)
	    	 {
	    	    if (i.getRefIngredient().getRefIngredient().compareTo(j)==0) {
	            	model4.addRow(new Object[] { i.getRefRangement().getNomRangement() });
	            } 
	    	 }

	     }
		 JTable table4 = new JTable(model4);
		 JScrollPane scrollPane4 = new JScrollPane(table4);
		 panel.add(scrollPane4);
//*********Q5******************************************************************************************************
        System.out.println("RQ5 :");
        ArrayList<String> q5 = new ArrayList<>();
        for (Recette recipe : recipes) {
            if (recipe.getNomRecette().indexOf("mousse") >0) {
                q5.add(recipe.getNomRecette());
            }
        }
        System.out.println("Noms de recettes contenant la chaine de caractere mousse :");
        for (String recipeName : q5) {
            System.out.println(recipeName);
        }  
        System.out.println("");
//*
		 DefaultTableModel model5 = new DefaultTableModel();
		 model5.setColumnIdentifiers(new String[] { "Noms de recettes contenant la chaine de caractere mousse :" });
	     for (Recette recipe : recipes) {
	            if (recipe.getNomRecette().indexOf("mousse") >0) {
	            	model5.addRow(new Object[] { recipe.getNomRecette() });
	            }
	     }
		 JTable table5 = new JTable(model5);
		 JScrollPane scrollPane5 = new JScrollPane(table5);
		 panel.add(scrollPane5);
 //*********Q6*****************************************************************************************************
        System.out.println("RQ6 :");
        int sum=0;
        for (Produit i : pr) {
        		 if (i.getRefIngredient().getNomIngredient().compareTo("farine")==0) {
        			 sum+=i.getQuantiteProduit();
        		 } 
        	}
        System.out.println("Quantite de farine présente chez l'utilisateur :");
        System.out.println(sum); 
        System.out.println("");
        
//*
		 DefaultTableModel model6 = new DefaultTableModel();
		 model6.setColumnIdentifiers(new String[] { "Quantite de farine présente chez l'utilisateur :" });
	            	model6.addRow(new Object[] { sum });
	            
		 JTable table6 = new JTable(model6);
		 JScrollPane scrollPane6 = new JScrollPane(table6);
		 panel.add(scrollPane6);
//*********Q7********************************************************************************************************
		System.out.println("RQ7 :");
        int nb=0;
        for (Composition i : comp) {
        		 if (i.getRefIngredient().getNomIngredient().indexOf("oeuf")>0) {
        			nb++;
        		 } 
        	}
        System.out.println("Le nombre des recettes utilisant des oeufs :");
        System.out.println(nb);
        System.out.println("");
 //*
		 DefaultTableModel model7 = new DefaultTableModel();
		 model7.setColumnIdentifiers(new String[] { "Le nombre des recettes utilisant des oeufs :" });
		 model7.addRow(new Object[] { nb });
		 JTable table7 = new JTable(model7);
		 JScrollPane scrollPane7 = new JScrollPane(table7);
		 panel.add(scrollPane7);
}}


