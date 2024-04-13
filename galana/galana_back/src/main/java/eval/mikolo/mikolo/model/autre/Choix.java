package eval.mikolo.mikolo.model.autre;

public class Choix {
    int idChoix;
    String nomChoix;
    public int getIdChoix() {
        return idChoix;
    }
    public void setIdChoix(int idChoix)
    throws Exception {
        if(idChoix<0) {
            throw new Exception("Veuillez entrer un id de choix valide");
        }
        this.idChoix = idChoix;
    }
    public String getNomChoix() {
        return nomChoix;
    }
    public void setNomChoix(String nomChoix)
    throws Exception {
        if(nomChoix==null||nomChoix.length()==0) {
            throw new Exception("Veuillez entrer un nom de choix");
        }
        this.nomChoix = nomChoix;
    }
}
