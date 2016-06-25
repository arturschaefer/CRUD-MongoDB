package windows;

import control.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/** Configura as colunas com base na classe "Muda", definida no dominio do problema.
 *  Ainda define se os dados de ordenação lidos serão crescentes ou não.
 * 
 * @author Giovany Frossard Teixeira
 */
public class JanelaVisualizarMuda {

    protected ControladoraMudas controladora;
    protected String coluna;
    protected boolean crescente;
    private Vector colunas = null;

    protected Vector obterNomeColunasTabela() {
        if (colunas == null) {
            colunas = new Vector(10);
            colunas.addElement("_id");
            colunas.addElement("IdViveiro");
            colunas.addElement("Tipo");
            colunas.addElement("Qualidade");
            colunas.addElement("Data de Compra");
            colunas.addElement("Preco");        
        }

        return colunas;
    }

    protected void limparTabelaMudas() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    protected void preencherTabelaMudas() {
        try {
            Vector linhas;
            linhas = controladora.obterLinhasMudas(coluna, crescente);

            System.out.println("Legenda da ordem das colunas");
            for (int i = 0; i < colunas.size() - 1; i++) {
                System.out.print(colunas.get(i) + "   -   ");
            }
            System.out.print(colunas.get(colunas.size() - 1));
            System.out.println("");
            System.out.println("===========================================================================================================");

            int numLinhas = linhas.size();
            for (int i = 0; i < numLinhas; i++) {
                Vector linha = (Vector) linhas.get(i);
                for (int j = 0; j < colunas.size() - 1; j++) {
                    System.out.print(linha.get(j) + "   -   ");
                }
                
                String valor = linha.get(colunas.size() - 1).toString();
                
                if (valor.equals("")) {
                    System.out.print(" null ");
                } else {
                    System.out.print(valor);
                }
                System.out.println("");
                System.out.println("----------------------------------------------------------------------------------------------------------");

            }
            System.out.println("===========================================================================================================");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JanelaVisualizarMuda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JanelaVisualizarMuda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JanelaVisualizarMuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void montarCabecalho() {
        System.out.println(" Escolha uma das opções abaixo:");
        System.out.println(" 0) Sair");
        int desloque = 0;
        for (int i = 0; i < colunas.size(); i++) {
            System.out.println(" " + (i + 1 + desloque) + ") " + colunas.get(i) + " - Crescente");
            System.out.println(" " + (i + 2 + desloque) + ") " + colunas.get(i) + " - Decrescente");
            desloque++;
        }

    }

    protected void montarLayout() {
        // Montar cabeçalho
        System.out.println("===========================================================================================================");
        this.montarCabecalho();
        System.out.println("===========================================================================================================");
        // Preenchendo a Tabela de Mudas
        this.preencherTabelaMudas();
    }

    /** Basicamente um construtor de Visualizar Mudas.
     * 
     */
    public JanelaVisualizarMuda() {
        controladora = new ControladoraMudas();
        this.obterNomeColunasTabela();
        this.coluna = colunas.get(0).toString();
        this.crescente = true;
    }

    /** Executa as operações da classe. Faz a leitura das opções do programa.
     * 
     */
    public void executar() {
        Scanner leitorOpcao = new Scanner(System.in);
        do {
            this.montarLayout();

            int opcao = leitorOpcao.nextInt();
            leitorOpcao.close();
            limparTabelaMudas();
            // sair
            if (opcao == 0) {
                break;
            }
            this.coluna = (String) this.colunas.get((opcao - 1) / 2);
            if ((opcao % 2) == 1) {
                this.crescente = true;
            } else {
                this.crescente = false;
            }

        } while (true);
        leitorOpcao.close();
    }
}
