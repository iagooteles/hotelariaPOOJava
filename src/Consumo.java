public class Consumo {
    private Reserva reserva;
    private Produto produto;
    private int idConsumo;
    private double quantidade;

    public Consumo(Reserva reserva, Produto produto, int idConsumo, double quantidade) {
        this.reserva = reserva;
        this.produto = produto;
        this.idConsumo = idConsumo;
        this.quantidade = quantidade;
    }

}
