package control;

import dao.*;
import domain.Muda;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/** Realiza a ligação dos dados com o pacote de Interface aos Dados.
 *
 * @author Artur Schaefer
 */
public class ControladoraMudas {
    private Vector<Muda> mudas;
    private int marcador;
    MudaMongoDao mudaDao;
    
    private String obterNomeColunaBanco(String coluna) {
        if (coluna.equals("_id"))
            return "_id";
        if (coluna.equals("Tipo"))
            return "tipo";
        if (coluna.equals("Qualidade"))
            return "qualidade";
        if (coluna.equals("Data de Compra"))
            return "dataCompra";
        if (coluna.equals("Preco"))
            return "preco";
        return "id";
    }

    /** Construir conexão com o banco.
     * 
     */
    public ControladoraMudas() {
        this.mudaDao = new MudaMongoDao();
    }
    
    /** Recebe um tipo muda e um vetor, onde coloca os dados em cada indice do vetor.
     * 
     * @param muda
     * @param linha 
     */
    private void atualizarMuda(Muda muda, Vector linha){
        muda.setId(linha.get(0).toString());
        muda.setIdViveiro(Integer.parseInt(linha.get(1).toString()));
        muda.setTipo(linha.get(2).toString());
        muda.setQualidade(linha.get(3).toString());
        muda.setDataCompra(linha.get(4).toString());
        muda.setPrecoVenda(Float.parseFloat(linha.get(5).toString())); 
    }

    /** Adiciona informações em um vetor, a partir de um tipo muda.
     * 
     * @param muda
     * @return 
     */
    private Vector criarLinhaMuda(Muda muda) {
        Vector linha = new Vector();
        linha.addElement(muda.getId());
        linha.addElement(muda.getIdViveiro());
        linha.addElement(muda.getTipo());
        linha.addElement(muda.getQualidade());
        linha.addElement(muda.getDataCompra());
        linha.addElement(muda.getPrecoVenda());
        return linha;
    }
     
    /** Adiciona as informações de um vetor dentro do BD.
     * 
     * @param linha
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void inserirNovoMuda(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException{
        Muda muda = new Muda();
        this.atualizarMuda(muda, linha);
        this.mudas.add(muda);
        mudaDao.inserirMuda(muda); 
    }
    
    /** Define um valor para o marcador, que posteriormente é utilizado para alterar algum dado no banco.
     *
     * @param marcador 
     */
    public void setMarcador(int marcador){
        this.marcador = marcador;
    }

    /** A partir de um marcador, atualiza um dado no BD.
     * 
     * @param linha
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void alterarMuda(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Muda muda = mudas.get(marcador);
        this.atualizarMuda(muda, linha);
        mudaDao.alterarMuda(muda); 
    }
    
    /** Exclui uma muda do BD.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void  excluirMuda() throws FileNotFoundException, IOException, ClassNotFoundException{
        mudaDao.excluirMuda(mudas.get(marcador)); 
        mudas.remove(marcador);
    }
    
    /** Obtem mudas a partir do BD, guardando as informações em um vetor.
     * 
     * @param coluna
     * @param crescente
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private Vector<Muda> obterMudas(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException{
        String nomeColunaBanco = this.obterNomeColunaBanco(coluna);
        mudas = mudaDao.obterMudas(nomeColunaBanco, crescente);
        return mudas; 
    }

    /** Adiciona o vetor obtido de mudas em um outro vetor, que representará as linhas na view.
     * 
     * @param coluna
     * @param crescente
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Vector obterLinhasMudas(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Muda> mudas = obterMudas(coluna, crescente);
        Vector linhas = new Vector();
        
        // Montando as linhas
        for(int i = 0; i < mudas.size(); i++){
            Muda muda = mudas.get(i);
            linhas.addElement(this.criarLinhaMuda(muda));
        }
        return linhas;
    }
}
