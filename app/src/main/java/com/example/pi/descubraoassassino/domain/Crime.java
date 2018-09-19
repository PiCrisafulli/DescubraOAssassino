package com.example.pi.descubraoassassino.domain;

public class Crime {

/*Em português para melhor visualização de outros programadores para indenficação com
 *a api. Lembre-se, a recomendação é sempre fazer em uma só linguagem.
 */
        public String id;
        public String suspeito;
        public int numeroSuspeito;
        public String arma;
        public int numeroArma;
        public String local;
        public int numeroLocal;

        public Crime(){}

    public String toJsonString(){
        return  "{IdSuspeito: " + numeroSuspeito + ", IdArma: " + numeroArma + ", IdLocal: " + numeroLocal + ", IdMisterio: \"" + id + "\" }";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuspeito() {
        return suspeito;
    }

    public void setSuspeito(String suspeito) {
        this.suspeito = suspeito;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getNumeroSuspeito() {
        return numeroSuspeito;
    }

    public void setNumeroSuspeito(int numeroSuspeito) {
        this.numeroSuspeito = numeroSuspeito;
    }

    public int getNumeroArma() {
        return numeroArma;
    }

    public void setNumeroArma(int numeroArma) {
        this.numeroArma = numeroArma;
    }

    public int getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(int numeroLocal) {
        this.numeroLocal = numeroLocal;
    }
}