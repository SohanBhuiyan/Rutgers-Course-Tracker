package MainFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TextField courseUrl;
    @FXML
    TextField courseName;
    @FXML TableColumn<Course,String> courseNameColumn;
    @FXML TableColumn<Course,String> statusColumn;
    @FXML TableColumn<Course, String> sectionColumn;
    @FXML TableView<Course> tableView;
    @FXML TextField tf_username;
    @FXML PasswordField tf_password;
    @FXML ComboBox<String> semester;
    @FXML TextField academicYear;
    ObservableList<Course> items = FXCollections.observableArrayList();
    ObservableList<String> semesterOptions = FXCollections.observableArrayList("FALL","SPRING");



    /** code is attached to the snipe button. Sets up variables from textfield that user fills out.
     * also updates the TableView content so it shows it correctly.
     * @param event
     */

    // Snipe Button Action
    public void addNewCourseToTracker(ActionEvent event) {
        String username;
        String password;
        String semesterSelected;

       // setUpColumns();
        username = tf_username.getText();
        password = tf_password.getText();
        semesterSelected = semester.getValue();
        String name_of_courseSection = courseUrl.getText();
        String name_of_course = courseName.getText();
        //old way
        //Course course = new Course(username,password,this,name_of_courseSection,name_of_course);

        Course course = new Course.Builder()
                .username(tf_username.getText())
                .password(tf_password.getText())
                .courseName(name_of_course)
                .section(name_of_courseSection)
                .controller(this)
                .build();
        items.add(course);
        tableView.setItems(items);

        semesterSelected = semesterSelected.equals("FALL") ? "9" : "1";
        String year = academicYear.getText();
        // this url will take chromium to where only the course provided is available, making it easier to determine if its open or not.
        String courseUrl = "http://sis.rutgers.edu/soc/#keyword?keyword="+name_of_courseSection+"&semester="+semesterSelected+year+"&campus=NB&level=U";
        course.creatingTracker(courseUrl);
    }

    // method to declunk code in addNewCourseToTracker method.
    public void setUpColumns() {
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("section"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("status"));
    }
    // called when a class has been successfully registered.
    public void updateListView() {
        tableView.refresh();
        System.out.println("Done updating");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("section"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("status"));
        //sectionColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("section"));
        tableView.setItems(items);
        semester.setItems(semesterOptions);

    }


}
