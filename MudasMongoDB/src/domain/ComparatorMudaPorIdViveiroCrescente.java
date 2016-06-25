
package domain;

import java.util.Comparator;
/** Compara pelo IdViveiro em ordem crescente.
 *
 * @author Artur Schaefer
 * 
 */
public class ComparatorMudaPorIdViveiroCrescente implements Comparator<Muda> {
    /**
    * 
    * @param o1 Um id do Viveiro 01
    * @param o2 Um id do Viveiro 02
    * @return 1,-1,0
    */
    public int compare(Muda o1, Muda o2) {
        Integer v1 = o1.getIdViveiro();
        Integer v2 = o2.getIdViveiro();
        return v1 < v2 ? -1 : v1 > v2 ? +1 : 0;        
    }
}
