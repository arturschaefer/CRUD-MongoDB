package domain;

import java.util.Comparator;
/** Compara pelo tipo em ordem crescente.
 * @author Giovany Frossard Teixeira
 *
 */
public class ComparatorMudaPorTipoDecrescente implements Comparator<Muda> {
    public int compare(Muda o1, Muda o2) {
        return ((-1) * o1.getTipo().compareTo(o2.getTipo()));
    }

}
