package pt.ubi.di.pdm.a44240_t0;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;


public class Mensagem {
    private String Saudacao, QuebraGelo, Corpo, Votos, Despedida, Assinatura, DataAtual;
    Calendar calendar;



    public Mensagem(String Saudacao, String QuebraGelo,String Corpo, String Votos, String Despedida,String Assinatura) {
        this.Saudacao = Saudacao;
        this.QuebraGelo = QuebraGelo;
        this.Corpo = Corpo;
        this.Votos = Votos;
        this.Despedida = Despedida;
        this.Assinatura = Assinatura;
        calendar = Calendar.getInstance();
        DataAtual = DateFormat.getDateInstance().format(calendar.getTime());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public String getSaudacao() {
        return Saudacao;
    }

    public String getQuebraGelo() {
        return QuebraGelo;
    }

    public String getCorpo() {
        return Corpo;
    }

    public String getVotos() {
        return Votos;
    }

    public String getDespedida() {
        return Despedida;
    }

    public String getAssinatura() {
        return Assinatura;
    }

    public String getDataAtual() {
        return DataAtual;
    }

    public void setSaudacao(String saudacao) {
        Saudacao = saudacao;
    }

    public void setQuebraGelo(String quebraGelo) {
        QuebraGelo = quebraGelo;
    }

    public void setCorpo(String corpo) {
        Corpo = corpo;
    }

    public void setVotos(String votos) {
        Votos = votos;
    }

    public void setDespedida(String despedida) {
        Despedida = despedida;
    }

    public void setAssinatura(String assinatura) {
        Assinatura = assinatura;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "Saudacao='" + Saudacao + '\'' +
                ", QuebraGelo='" + QuebraGelo + '\'' +
                ", Corpo='" + Corpo + '\'' +
                ", Votos='" + Votos + '\'' +
                ", Despedida='" + Despedida + '\'' +
                ", Assinatura='" + Assinatura + '\'' +
                ", Data='" + DataAtual + '\'' +
                '}';
    }


}
