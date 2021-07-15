public class Twist {
    private String twistOwner;
    private String content;

    public Twist(String twistOwner, String content) {
        this.twistOwner = twistOwner;
        this.content = content;
    }

    public String getTwistOwner() {
        return twistOwner;
    }

    public void setTwistOwner(String twistOwner) {
        this.twistOwner = twistOwner;
    }

    public String getContent() {
        return content;
    }
}
