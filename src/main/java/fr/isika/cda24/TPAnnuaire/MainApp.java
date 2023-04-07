package fr.isika.cda24.TPAnnuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fr.isika.cda24.TPAnnuaire.PremiereFenetre;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

	PremiereFenetre root = new PremiereFenetre();
	
	Scene scene = new Scene(gridPane1);
	
	stage.setScene(scene);
	
	stage.setTitle("Bienvenue à l'annuaire - Isika");
	
	stage.sizeToScene();
	
	stage.show();
	
	}
}
