package windows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Janela principal do programa, faz a impressão das informações que podem ser vistas e editadas.
 *  Basicamente é um layout do programa. Com base nas colunas definidas em "JanelaVisualizarMuda" ele define padrão.
 * @see "Alteração na leitura para nextLine, que permite uma leitura com espaçamento."
 * @author Giovany Frossard Teixeira
 */
public class JanelaPrincipalMuda extends JanelaVisualizarMuda {

    @Override
    protected void montarCabecalho() {
        super.montarCabecalho();
        Vector colunas = this.obterNomeColunasTabela();
        int qtMenus = colunas.size() * 2;
        System.out.println(" " + (qtMenus + 1) + ") Incluir Novo Muda");
        System.out.println(" " + (qtMenus + 2) + ") Alterar Muda");
        System.out.println(" " + (qtMenus + 3) + ") Excluir Muda");
    }

    /** Faz a leitura dos dados, conforme a coluna especificada. 
     * 
     * @return 
     */
    private Vector lerDados() {
        System.out.println("===========================================================================================================");
        System.out.println("Digite os seguintes dados: ");
        Vector colunas = this.obterNomeColunasTabela();
        Scanner leitor = new Scanner(System.in);
        Vector dados = new Vector();
        String valor;
        for (int i = 0; i < colunas.size(); i++) {
            System.out.println(colunas.get(i));
            valor = leitor.nextLine(); //valor = leitor.next(); 
            dados.add(valor);
        }
        System.out.println("");
        System.out.println("===========================================================================================================");
        return dados;
    }



    /** Realiza a execução principal do programa: leitura dos dados e ligação com os controladores.
     *
     */
    @Override
    public void executar() {
        Vector colunas = this.obterNomeColunasTabela();
        int qtMenus = colunas.size() * 2;
        Scanner leitorOpcao = new Scanner(System.in);
        do {
            this.montarLayout();
            int opcao = leitorOpcao.nextInt();
            // sair
            if (opcao == 0) {
                break;
            } // ordenação
            else if (opcao <= qtMenus) {
                limparTabelaMudas();
                this.coluna = (String) colunas.get((opcao - 1) / 2);
                if ((opcao % 2) == 1) {
                    this.crescente = true;
                } else {
                    this.crescente = false;
                }
            } // incluir
            else if (opcao == qtMenus + 1) {
                limparTabelaMudas();
                Vector dados = lerDados();
                try {
                    this.controladora.inserirNovoMuda(dados);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                }

            } // alterar
            else if (opcao == qtMenus + 2) {
                System.out.println("Digite o número da linha que deseja alterar: ");
                this.controladora.setMarcador(leitorOpcao.nextInt());
                limparTabelaMudas();
                Vector dados = lerDados();
                try {
                    this.controladora.alterarMuda(dados);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                }

            } // excluir
            else if (opcao == qtMenus + 3) {
                System.out.println("Digite o número da linha que deseja excluir: ");
                this.controladora.setMarcador(leitorOpcao.nextInt());
                try {
                    this.controladora.excluirMuda();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipalMuda.class.getName()).log(Level.SEVERE, null, ex);
                }

                limparTabelaMudas();

            } else {
                System.out.println("Falha GERAL");
            }


        } while (true);
        leitorOpcao.close();

    }
}
