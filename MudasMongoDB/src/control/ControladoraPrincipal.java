package control;

import windows.JanelaPrincipalMuda;
import windows.JanelaVisualizarMuda;

/** Inicia o programa, iniciliazando a janela.
 * 
 * @author artur
 */
public class ControladoraPrincipal {
    public static void main(String argsp[])
    {
        JanelaVisualizarMuda janela = new JanelaPrincipalMuda();
        janela.executar();
    }

}
