package fr.isika.cda24.TPAnnuaire.entitees;

public abstract class Employe {

	// On définit les attributs de la classe abstraite employés. Ce sont ceux qui se
	// connecteront à l'outil

	protected String Nom;
	protected String Prenom;

	public Employe(String Nom, String Prenom) {
		this.Nom = Nom;
		this.Prenom = Prenom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public abstract void imprimer(); // methode pour imprimer

	public abstract void ajouterStagiaire(); // methode pour que l'utilisateur ajoute un stagiaire

	public abstract void rechercher(); // methode de recherche de stagiaire dans la liste

	public abstract void rechercheAvancée(); // methode de recherche avec les critères

	public String getNom() {
		return Nom + " " + Prenom;
	}
}
