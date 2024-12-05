package sg.edu.nus.iss.vttp_day18.model;

public class SessionData {
    private String name;
    private String dob;
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name + "," + dob;
    }
    public SessionData() {
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDob() {
        return dob;
    }
    public SessionData(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
}
