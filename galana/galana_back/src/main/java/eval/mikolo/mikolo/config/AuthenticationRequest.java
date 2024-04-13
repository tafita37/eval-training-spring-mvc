package eval.mikolo.mikolo.config;

public class AuthenticationRequest {
    String mail;
    String mdp;
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
