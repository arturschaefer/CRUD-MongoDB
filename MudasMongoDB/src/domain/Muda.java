package domain;

/** Classe básica do programa. Define o dominio do problema. 
 * Herda de um obejto do MongoDB.
 *
 * @author Artur Schaefer
 */
public class Muda extends MongoObject {
    private String tipo, qualidade, dataCompra, dataVenda;
    private int idViveiro;
    private float precoVenda;

    public int getIdViveiro() {
        return idViveiro;
    }

    public void setIdViveiro(int idViveiro) {
        this.idViveiro = idViveiro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    
    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
}
