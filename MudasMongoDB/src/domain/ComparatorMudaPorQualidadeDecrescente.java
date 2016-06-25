/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.Comparator;
/** Compara pela qualidade em ordem decrescente.
 * @author Artur Schaefer
 *
 */
public class ComparatorMudaPorQualidadeDecrescente implements Comparator<Muda> {
    public int compare(Muda o1, Muda o2) {
        return ((-1) * o1.getQualidade().compareTo(o2.getQualidade()));
    }

}
