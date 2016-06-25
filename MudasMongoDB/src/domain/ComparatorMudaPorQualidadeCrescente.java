

package domain;

import java.util.Comparator;

/** Compara pela qualidade em ordem crescente.
 * @author Artur Schaefer
 *
 */
public class ComparatorMudaPorQualidadeCrescente implements Comparator<Muda> {
    public int compare(Muda o1, Muda o2) {       
        return o1.getQualidade().compareTo(o2.getQualidade());
    }

}
