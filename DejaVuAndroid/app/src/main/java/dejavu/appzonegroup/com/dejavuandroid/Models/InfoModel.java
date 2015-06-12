package dejavu.appzonegroup.com.dejavuandroid.Models;

/**
 * Created by CrowdStar on 2/26/2015.
 */
public class InfoModel {
    private String subject;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }
}
