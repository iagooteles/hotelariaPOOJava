public class Reserva {
    private Quarto quarto;
    private Hospede hospede;
    private int idReserva;
    private double valor;
    private String dataEntrada;
    private String dataSaida;
    private boolean checkin;
    private boolean checkout;

    public Reserva(Quarto quarto, Hospede hospede, int idReserva, double valor, String dataEntrada, String dataSaida, boolean checkin, boolean checkout) {
        this.quarto = quarto;
        this.hospede = hospede;
        this.idReserva = idReserva;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.checkin = checkin;
        this.checkout = checkout;
    }


}
