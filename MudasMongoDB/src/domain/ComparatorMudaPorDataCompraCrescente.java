package domain;

import java.util.Comparator;
/** Compara por data de compra em ordem crescente.
 * 
 * @author artur
 */
public class ComparatorMudaPorDataCompraCrescente implements Comparator<Muda>{
    /**
     * 
     * @param o1
     * @param o2
     * @return 
     */
    public int compare(Muda o1, Muda o2) {       
        return o1.getDataCompra().compareTo(o2.getDataCompra());
        
    }

}
