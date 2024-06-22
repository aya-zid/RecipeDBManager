package alimentation;

public class Recette {
	private String RefRecette;
	private String NomRecette;
	private String DescriptifRecette;
	private int CaloriesRecette;
	private String Difficulte;
	private int TempsPreparation;
	private int TempsCuisson;
	private int NbPersonnes;
	public Recette(String ref, String nom,String desc,int cal,String diff,int tmpprep, int tmpcui,int nbpers)
	
	{
		setRefRecette(ref);
		setNomRecette(nom);
		setDescriptifRecette(desc);
		setCaloriesRecette(cal);
		setDifficulte(diff);
		setTempsPreparation(tmpprep);
		setTempsCuisson(tmpcui);
		setNbPersonnes(nbpers);
		
	}
	public String getRefRecette() {
		return RefRecette;
	}
	public void setRefRecette(String refRecette) {
		RefRecette = refRecette;
	}
	public String getNomRecette() {
		return NomRecette;
	}
	public void setNomRecette(String nomRecette) {
		NomRecette = nomRecette;
	}
	public String getDescriptifRecette() {
		return DescriptifRecette;
	}
	public void setDescriptifRecette(String descriptifRecette) {
		DescriptifRecette = descriptifRecette;
	}
	public int getCaloriesRecette() {
		return CaloriesRecette;
	}
	public void setCaloriesRecette(int caloriesRecette) {
		CaloriesRecette = caloriesRecette;
	}
	public String getDifficulte() {
		return Difficulte;
	}
	public void setDifficulte(String difficulte) {
		Difficulte = difficulte;
	}
	public int getTempsPreparation() {
		return TempsPreparation;
	}
	public void setTempsPreparation(int tempsPreparation) {
		TempsPreparation = tempsPreparation;
	}
	public int getTempsCuisson() {
		return TempsCuisson;
	}
	public void setTempsCuisson(int tempsCuisson) {
		TempsCuisson = tempsCuisson;
	}
	public int getNbPersonnes() {
		return NbPersonnes;
	}
	public void setNbPersonnes(int nbPersonnes) {
		NbPersonnes = nbPersonnes;
	}
}
