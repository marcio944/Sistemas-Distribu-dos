package servidor;

/**
 *
 * @author Casa
 */


public class Pessoa {
    
    private String nome;
    private String cpf;
    private float imc;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the imc
     */
    public float getImc() {
        return imc;
    }

    /**
     * @param imc the imc to set
     */
    public void setImc(float imc) {
        this.imc = imc;
    }
    
}
