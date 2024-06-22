package alimentation;

public class Rangement {
private String RefRangement;
private String NomRangement;	
public Rangement(String ref, String nom)
{
	RefRangement=ref;
	NomRangement=nom;
}

public String getRefRangement() {
	return RefRangement;
}
public void setRefRangement(String refRangement) {
	RefRangement = refRangement;
}
public String getNomRangement() {
	return NomRangement;
}
public void setNomRangement(String nomRangement) {
	NomRangement = nomRangement;
}
}
