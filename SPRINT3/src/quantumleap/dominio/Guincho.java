package quantumleap.dominio;

public class Guincho {
    private String placa;
    private double preco;
    private double cargaMaxima;




    public Guincho(String placa, double preco, double cargaMaxima) {
        this.placa = placa;
        this.preco = preco;
        this.cargaMaxima = cargaMaxima;
    }

    public double getPreco(){
        return preco;
    }

}