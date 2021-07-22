package br.com.brunoperdona.twister.entities;

public class Twist {
    private String twistOwner;
    private String content;
    private boolean adminTwist;

    public Twist(String twistOwner, String content) {
        this.twistOwner = twistOwner;
        this.content = content;
        this.adminTwist = false;
    }

    //construtor para twists de admins
    public Twist(String twistOwner, String content, boolean adminTwist ){
        this.twistOwner = twistOwner;
        this.content = content;
        if(adminTwist){
            this.adminTwist = true;
        }else{
            this.adminTwist = false;
        }

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

    public boolean getAdminTwist(){
        return this.adminTwist;
    }
}
