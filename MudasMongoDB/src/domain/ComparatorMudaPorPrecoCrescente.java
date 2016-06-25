package domain;

import java.util.Comparator;

/** Compara pelo preço em ordem crescente.
 * @author Artur Schaefer
 *
 */
public class ComparatorMudaPorPrecoCrescente implements Comparator<Muda>{
    public int compare(Muda o1, Muda o2) {
        if(o1.getPrecoVenda() > o2.getPrecoVenda()){
            return 1;
        }else{
            return -1;
        }
        
    }

}
