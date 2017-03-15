package mvc.view;

import java.util.Observable;

import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.control.CourbeController;
import mvc.model.CourbeModel;

public class CourbeVueConcret<X, Y> extends CourbeVue<X,Y> {
	private Text actionStatus;
	private static final String titleTxt = "Selection d'un fichier CSV";
	private Label label;
	private HBox labelHb;
	private Button btn1;
	private HBox buttonHb1;
	private VBox vbox;
	private Scene scene;
	public Stage primaryStage;
	private String chemin;
	

	public CourbeVueConcret(CourbeModel<X, Y> mod, CourbeController<X, Y> cont, Axis<X> xAx, Axis<Y> yAx, String t) {
		super(mod, cont, xAx, yAx, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
