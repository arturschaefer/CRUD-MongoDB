/**
 * Autor:           Artur Schaefer
 * Email:           artur.schaefer2@gmail.com
 * Data:            24/06/2016
 * Hora:            19:55:54
 * Codificação:     UTF-8
 * Disciplina:      Programação Orientada a Objetos I
 * Professor:       Giovany Frossard Teixeira
 * Instituição:     Instituto Federal de Educação, Ciência e Tecnologia do Espírito Santo - IFES
 *
 * Nome Original:       ComparatorMudaPorIdMongoCrescente
 * Pacote de Criação:   domain 
 */

package domain;

import java.util.Comparator;
/** Compara pelo _id do MongoDB em ordem crescente.
 * 
 * @author artur
 */
public class ComparatorMudaPorIdMongoCrescente implements Comparator<Muda> {
    public int compare(Muda o1, Muda o2) {
        return o1.getQualidade().compareTo(o2.getQualidade());        
    }
}

/*
 *  O programa é de uso exclusivo para a disciplina de Programação Orientada a Objetos I 
 *  Sua cópia é estritamente proibida!
 *  Na dúvida entre em contato: artur.schaefer2@gmail.com
 *  ©Artur_Schaefer
 */
