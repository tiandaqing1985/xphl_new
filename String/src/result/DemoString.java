package result;

/**
 * @program: String->DemoString
 * @author: gakki
 * @create: 2019-07-30 15:13
 **/

public class DemoString {

    private String name;
    private String status;

    public DemoString(String name, String status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DemoString{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
