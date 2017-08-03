/**
* The Cayuga Dictionary project is to keep the language vibrant and alive 
* through immersion courses for adults and language daycare for children.
* 
* The project is Led by Dr. Carrie Dyck
* Faculty of Humanities and Social Sciences
* Memorial University of Newfoundland
*
* @author  Chen Zhang
* @version online 2.0
* @since   2016-12-01 
*/
package mun.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import mun.util.AnimationGenerator;
import mun.util.ImageEditor;
import mun.util.*;

public class AboutViewController implements Initializable{

	private final AnimationGenerator animationGenerator;
	private final ImageEditor imageEditor;
	//private final AdminViewController controller;
    @FXML
    protected ImageView portrait,book;
	
    public AboutViewController() {
    	//this.controller = controller;
        animationGenerator = new AnimationGenerator();
        imageEditor = new ImageEditor();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		imageEditor.setImageOn(portrait, "/buttons/dyck.jpg", 130, 130);
		imageEditor.setImageOn(book, "/buttons/book.jpg", 130, 130);
	}
}
