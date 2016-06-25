package domain;

import java.util.Comparator;
/** Compara por data de compra em ordem decrescente.
 * 
 * @author artur
 */
public class ComparatorMudaPorDataCompraDecrescente implements Comparator<Muda>{
    public int compare(Muda o1, Muda o2) {
        return ((-1) * o1.getDataCompra().compareTo(o2.getDataCompra()));
    }

}
