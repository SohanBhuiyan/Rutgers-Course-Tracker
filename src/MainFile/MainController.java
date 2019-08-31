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
    private ObservableList<Course> items = FXCollections.observableArrayList();
    private ObservableList<String> semesterOptions = FXCollections.observableArrayList("FALL","SPRING");



    // snipe button action
    public void addNewCourseToTracker(ActionEvent event) {
        String semesterSelected = semester.getValue();
        String name_of_course_section = courseUrl.getText();
        String name_of_course = courseName.getText();


        Course course = new Course.Builder()
                .username(tf_username.getText())
                .password(tf_password.getText())
                .courseName(name_of_course)
                .section(name_of_course_section)
                .controller(this)
                .status("sniping....")
                .build();

        tableView.getItems().add(course);
        semesterSelected = semesterSelected.equals("FALL") ? "9" : "1";
        String year = academicYear.getText();
        String courseUrl = "http://sis.rutgers.edu/soc/#keyword?keyword="+name_of_course_section+"&semester="+semesterSelected+year+"&campus=NB&level=U";  //url takes you directly to the course page
        course.creatingTracker(courseUrl);
    }

    // method to declunk code in addNewCourseToTracker method.
    public void setUpColumns() {
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("section"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("status"));
    }
    // refresh the listview to show new added courses
    public void updateListView() {
        tableView.refresh();
        System.out.println("Done updating");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        sectionColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("section"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("courseName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("status"));
        tableView.setItems(items);
        semester.setItems(semesterOptions);

    }


}
