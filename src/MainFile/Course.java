package MainFile;

public class Course {
    private String url;
    private String username;
    private String password;
    private String courseName;
    private String status;
    private String section;
    public MainController controller;

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

    public void creatingTracker(String url) {
        Thread t1 = new Thread(new Sniper(this,url));
        t1.start();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
