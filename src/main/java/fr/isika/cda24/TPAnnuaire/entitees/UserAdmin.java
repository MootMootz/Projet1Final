package fr.isika.cda24.TPAnnuaire.entitees;

public class UserAdmin extends User implements FonctionsAdmin {

	public UserAdmin(String Nom, String Prenom, String login, String mdp) {
		super(Nom, Prenom, login, mdp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mettreAjourStagiaire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerStagiaire() {
		  Arbre b = a;
	        while (a != null && x != a.contenu)
	            if (x < a.contenu)
	                a = a.filsG;
	            else
	                a = a.filsD;
	        if (a != null)
	            a = supprimerRacine(a);
	        return b;
		
	}

}
