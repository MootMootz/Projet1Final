package fr.isika.cda24.TPAnnuaire.entitees;

public class User extends Employe {

	// on écrit les attributs propres aux utilisateurs c'est à dire le login et le
	// mot de passe

	private String login;
	private String mdp;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public User(String Nom, String Prenom, String login, String mdp) {
		super(Nom, Prenom);

		this.login = login;
		this.mdp = mdp;
	}
	
	
	

	@Override
	public void imprimer() {
		// TODO Auto-generated method stub

	}

	

	

	@Override
	public void rechercheAvancée() {
		// TODO Auto-generated method stub

	}

	



	@Override
	public void ajouterStagiaire() {
		 if (a == null)
	            return new Arbre(null, x, null);
	        if (x < a.contenu)
	            a.filsG = inserer(x, a.filsG);
	        else if (x > a.contenu)
	            a.filsD = inserer(x, a.filsD);
	        return a;

	}

	@Override
	static stagiaire rechercher() {
		if (a == null || x == a.contenu)
            return a;
        if (x < a.contenu)
            return chercher(x, a.filsG);
        return chercher(x, a.filsD);
	}

}
