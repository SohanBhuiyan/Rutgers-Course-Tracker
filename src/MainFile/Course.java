package MainFile;

public class Course {
    private String url;
    private String username;
    private String password;
    private String courseName;
    private String status;
    private String section;
    public MainController controller;

    // constructor
 /**   public Course(String user,String pass,MainController controller,String section, String course) {
        username = user;
        password = pass;
        this.section = section;
        // change this every semester so that the "&semester" is correct. 1 = SPRING 9 = FALL.
        this.url = "http://sis.rutgers.edu/soc/#keyword?keyword="+section+"&semester=12019&campus=NB&level=U";
        this.courseName = course;
        this.status = "sniping...";
        this.controller = controller;
        creatingTracker(url);
    }
**/

    private Course(Builder builder){
        this.url = builder.url;
        this.username = builder.username;
        this.password = builder.password;
        this.courseName = builder.courseName;
        this.status = builder.status;
        this.section = builder.section;
        this.controller = builder.controller;

    }
    //builder class for cleaner coding
    public static class Builder{
        private String url;
        private String username;
        private String password;
        private String courseName;
        private String status;
        private String section;
        public MainController controller;

        public Builder url(final String url){
            this.url = url;
            return this;
        }
        public Builder username(final String username){
            this.username = username;
            return this;
        }
        public Builder password(final String password){
            this.password = password;
            return this;
        }
        public Builder status(final String status){
            this.status = status;
            return this;
        }
        public Builder section(final String section){
            this.section = section;
            return this;
        }
        public Builder courseName(final String courseName){
            this.courseName = courseName;
            return this;
        }
        public Builder controller(final MainController controller){
            this.controller = controller;
            return this;
        }

        public Course build(){
            return new Course(this);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void creatingTracker(String url) {
        Thread t1 = new Thread(new Sniper(this,url));
        t1.start();
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getSection() {
        return section;
    }





}
