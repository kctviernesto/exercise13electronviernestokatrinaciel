package exercise13electronviernestokatrinaciel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SubjectSearchEngineController implements Initializable {
    @FXML Text name, attributes, end, error;
    @FXML ImageView image;
    @FXML TextField searchInput;
    
    Subject displayedSubject;
    Image img;
    
    @FXML private void previousSubject() {
        int index = Subject.getSubjectIndex(displayedSubject);
        if(index > 0) {
          index--;
          displayedSubject = Subject.subjectList.get(index);
          resetDisplay(displayedSubject);
        }
        else {
          end.setText("This is the first subject of the list.");
        }
    }
    
    @FXML private void nextSubject() {
        int index = Subject.getSubjectIndex(displayedSubject);
        if(index < Subject.getListLength() - 1) {
          index++;
          displayedSubject = Subject.subjectList.get(index);
          resetDisplay(displayedSubject);
        }
        else {
          end.setText("This is the last subject of the list.");
        }
    }
    
    @FXML private void findSubject() {
        String input = searchInput.getText();
        try{
            displayedSubject = Subject.searchSubject(input);
            resetDisplay(displayedSubject);
        }
        catch(NullPointerException e){
            error.setText("That subject does not exist.");
        }
    }
    
    @FXML void resetDisplay(Subject s) {
        end.setText("");
        error.setText("");
        name.setText(s.getName());
        attributes.setText("Units: " + Double.toString(s.getUnits()) + " | Grade: " + Double.toString(s.getGrade()));
        img = new Image(getClass().getResourceAsStream(s.getImgFileName()));
        image.setImage(img);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayedSubject = Subject.subjectList.get(0);
        resetDisplay(displayedSubject);
    }    
    
}
