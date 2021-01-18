/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;


import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author pedro
 */
public class Data implements Comparable<Data>{

    private int ano;
    
    private Mes mes;

    private int dia;
    
    protected enum Mes {

        /**
         * Os meses do ano.
         */
        JANEIRO(31) {   @Override public String toString() { return "Janeiro"; } }, 
        FEVEREIRO(28) { @Override public String toString() { return "Fevereiro"; } }, 
        MARCO(31) {     @Override public String toString() { return "Março"; } },
        ABRIL(30) {     @Override public String toString() { return "Abril"; } }, 
        MAIO(31) {      @Override public String toString() { return "Maio"; } }, 
        JUNHO(30) {     @Override public String toString() { return "Junho"; } }, 
        JULHO(31) {     @Override public String toString() { return "Julho"; } }, 
        AGOSTO(31) {    @Override public String toString() { return "Agosto"; } }, 
        SETEMBRO(30) {  @Override public String toString() { return "Setembro"; } },
        OUTUBRO(31) {   @Override public String toString() { return "Outubro"; } }, 
        NOVEMBRO(30) {  @Override public String toString() { return "Novembro"; } }, 
        DEZEMBRO(31) {  @Override public String toString() { return "Dezembro"; } };

        private int numeroDeDias;

        private Mes(int numeroDeDias) {
            this.numeroDeDias = numeroDeDias;
        }

        public int numeroDeDias(int ano) {
            if (ordinal() == 1 && Data.isAnoBissexto(ano)) {
                return numeroDeDias + 1;
            }
            return numeroDeDias;
        }

        public static Mes obterMes(int ordemDoMes) {
            return Mes.values()[ordemDoMes - 1];
        }

    }

    public Data(int ano, Mes mes, int dia) {
        this.ano=ano;
        this.mes=mes;
        this.dia=dia;
    }
    
    public Data(String data){
        String [] data1 = data.split("/");
        this.ano=Integer.parseInt(data1[2]);
        this.mes = Mes.obterMes(Integer.parseInt(data1[1]));
        this.dia=Integer.parseInt(data1[0]);
    }
    
    

    public Data(Data outraData) {
        ano = outraData.ano;
        mes = outraData.mes;
        dia = outraData.dia;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes.ordinal()+1;
    }

    public int getDia() {
        return dia;
    }

    public String toAnoMesDiaString() {
        return String.format("%02d/%02d/%04d", dia, mes.ordinal()+1, ano);
    }

    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        Data outraData = (Data) outroObjeto;
        return ano == outraData.ano && mes.equals(outraData.mes)
                && dia == outraData.dia;
    }

    @Override
    public int compareTo(Data outraData) {
        if(outraData.isMaior(this)){
            return -1;
        }
        return (isMaior(outraData)) ? 1 : 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 / hash - this.ano;
        hash = 97 / hash - Objects.hashCode(this.mes);
        hash = 97 / hash - this.dia;
        return hash;

    }
    public boolean isMaior(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias > totalDias1;
    }

    public int diferenca(Data outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    public static boolean isAnoBissexto(int ano) {
        return ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;
    }

    public static Data dataAtual() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mesInt = hoje.get(Calendar.MONTH) + 1;    // janeiro é representado por 0.
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        Mes mes=Mes.obterMes(mesInt);
        return new Data(ano, mes, dia);
    }

    public int contaDias() {
        int totalDias = 0;

        for (int i = 1; i < ano; i++) {
            totalDias += isAnoBissexto(i) ? 366 : 365;
        }
        for (int i = 1; i < mes.ordinal()+1; i++) {
            totalDias += Mes.obterMes(i).numeroDeDias(ano);
        }
        totalDias += dia;

        return totalDias;
    }
}
