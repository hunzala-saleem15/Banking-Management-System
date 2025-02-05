public class Application {
    private int userId;
    private String applicationType;

    public Application(int userId, String applicationType) {
        this.userId = userId;
        this.applicationType = applicationType;
    }

    public int getUserId() {
        return userId;
    }

    public String getApplicationType() {
        return applicationType;
    }
}