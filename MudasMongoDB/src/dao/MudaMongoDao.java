package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import domain.ComparatorMudaPorIdViveiroCrescente;
import domain.ComparatorMudaPorIdViveiroDecrescente;
import domain.ComparatorMudaPorDataCompraCrescente;
import domain.ComparatorMudaPorDataCompraDecrescente;
import domain.ComparatorMudaPorIdMongoCrescente;
import domain.ComparatorMudaPorIdMongoDecrescente;
import domain.ComparatorMudaPorQualidadeCrescente;
import domain.ComparatorMudaPorQualidadeDecrescente;
import domain.ComparatorMudaPorTipoCrescente;
import domain.ComparatorMudaPorTipoDecrescente;
import domain.ComparatorMudaPorPrecoCrescente;
import domain.ComparatorMudaPorPrecoDecrescente;
import domain.Muda;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/** Cria e testa a conexão com o banco de dados.
 *  Define tipos básicos do tipo Mongo.
 * 
 * @author artur
 */
public class MudaMongoDao {

    Mongo m;
    DB db;
    DBCollection colecaoMudas;

    /** Gera a conexão com o MongoDB, em localhost.
     *  Vale lembrar que se não houver o banco ou a collection, ele a criará em algum insert.
     *  Para autenticações de login isso não deve ser usado.
     * 
     */
    public MudaMongoDao() {
        
        try {
            m = new Mongo("localhost");
            db = m.getDB("MudasSchaefer2");
            if (!db.collectionExists("mudas")) {
                this.colecaoMudas = db.getCollection("mudas");
            }
        } catch (UnknownHostException ex) {
            System.out.println("Host Desconhecido");
        } catch (MongoException ex) {
            System.out.println("Erro no MongoDB");
        }

    }
    
    /** Gera um objeto com características de "Muda" necessárias para o BD.
     * 
     * @param muda
     * @return 
     */
    private BasicDBObject gerarObjetoMongo(Muda muda) {
        BasicDBObject mudaMongo = new BasicDBObject();
        mudaMongo.put("_id", muda.getId());
        mudaMongo.put("idViveiro", muda.getIdViveiro());
        mudaMongo.put("tipo", muda.getTipo());
        mudaMongo.put("qualidade", muda.getQualidade());
        mudaMongo.put("dataCompra", muda.getDataCompra());
        mudaMongo.put("preco", muda.getPrecoVenda());
        return mudaMongo;
    }

    /** Gera um tipo "Muda" a partir de um dado do BD.
     * 
     * @param mudaMongo
     * @return 
     */
    private Muda gerarMuda(BasicDBObject mudaMongo) {
        Muda muda = new Muda();
        muda.setId(mudaMongo.getString("_id"));
        muda.setIdViveiro(mudaMongo.getInt("idViveiro"));
        muda.setTipo(mudaMongo.getString("tipo"));
        muda.setQualidade(mudaMongo.getString("qualidade"));
        muda.setDataCompra(mudaMongo.getString("dataCompra"));
        muda.setPrecoVenda((float) mudaMongo.getDouble("preco"));
        return muda;
    }

    /** Insere um tipo "muda" no BD, fazendo a conversão do mesmo para um Objeto Mongo.
     * 
     * @param muda 
     */
    public void inserirMuda(Muda muda) {
        BasicDBObject mudaMongo;
        mudaMongo = this.gerarObjetoMongo(muda);
        colecaoMudas.insert(mudaMongo);
    }

    /** Realiza a busca de um Objeto no BD antes de ser alterado em um update ou delete.
     * 
     * @param muda
     * @return 
     */
    private BasicDBObject buscarMudaMongoAntigo(Muda muda) {
        BasicDBObject mudaMongoAntes;
        DBCursor cursor = colecaoMudas.find();
        while (cursor.hasNext()) {
            mudaMongoAntes = (BasicDBObject) cursor.next();
            if (mudaMongoAntes.getString("_id").equals(muda.getId())){
                return mudaMongoAntes;
            }
        }
        return null;

    }

    /** Altera uma informação no BD.
     * 
     * @param muda 
     */
    public void alterarMuda(Muda muda) {
        BasicDBObject mudaMongoModificado, mudaMongoAntigo;
        mudaMongoModificado = this.gerarObjetoMongo(muda);
        mudaMongoAntigo = this.buscarMudaMongoAntigo(muda);
        this.colecaoMudas.update(mudaMongoAntigo, mudaMongoModificado);
    }

    /** Exclui um documento no banco, a partir de uma "Muda" passada como paramêtro.
     * 
     * @param muda 
     */
    public void excluirMuda(Muda muda) {
        BasicDBObject mudaMongo;
        mudaMongo = this.gerarObjetoMongo(muda);
        colecaoMudas.remove(mudaMongo);
    }

    /** Salva um vetor de mudas em uma coleção "mudas", existente no BD.
     * 
     * @param mudas 
     */
    public void salvarMudas(Vector<Muda> mudas) {
        Muda muda;
        BasicDBObject mudaMongo;
        DBCollection colecaoMudas = db.getCollection("mudas");
        Iterator itMudas = mudas.iterator();
        while (itMudas.hasNext()) {
            muda = (Muda) itMudas.next();
            mudaMongo = this.gerarObjetoMongo(muda);
            colecaoMudas.insert(mudaMongo);
        }
    }

    /** Obtém um vetor de mudas do BD.
     * 
     * @return 
     */
    public Vector<Muda> obterMudas() {
        Muda muda;
        BasicDBObject mudaMongo;
        Vector<Muda> mudas = new Vector();
        this.colecaoMudas = db.getCollection("mudas");
        DBCursor cursor = colecaoMudas.find();
        while (cursor.hasNext()) {
            mudaMongo = (BasicDBObject) cursor.next();
            muda = this.gerarMuda(mudaMongo);
            mudas.add(muda);
        }
        return mudas;
    }

    /** Realiza a ordenação dos dados. Recebe o nome da coluna e se vai ser por ordem crescente ou decrescente.
     * 
     * @param coluna
     * @param crescente
     * @return
     * @throws ClassNotFoundException 
     */
    public Vector<Muda> obterMudas(String coluna, boolean crescente) throws ClassNotFoundException {
        Vector<Muda> mudas = this.obterMudas();
        if (coluna.equals("_id")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorIdMongoCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorIdMongoDecrescente());
            }
        }
        if (coluna.equals("idViveiro")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorIdViveiroCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorIdViveiroDecrescente());
            }
        } else if (coluna.equals("tipo")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorTipoCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorTipoDecrescente());
            }
        } else if (coluna.equals("qualidade")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorQualidadeCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorQualidadeDecrescente());
            }
        } else if (coluna.equals("dataCompra")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorDataCompraCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorDataCompraDecrescente());
            }
        }else if (coluna.equals("preco")) {
            if (crescente) {
                Collections.sort(mudas, new ComparatorMudaPorPrecoCrescente());
            } else {
                Collections.sort(mudas, new ComparatorMudaPorPrecoDecrescente());
            }
        }
        return mudas;
    }
}
