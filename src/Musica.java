public class Musica extends Midia {
    private double duracao;
    private Categoria categoria;

    public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
        super(codigo, titulo, categoria, ano);
        this.duracao = duracao;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "duracao=" + duracao +
                ", categoria=" + categoria +
                "} " + super.toString();
    }

    @Override
    public double calculaLocacao() {
        double valorPorMinuto;
        switch (categoria) {
            case ACA:
                valorPorMinuto = 0.90;
                break;
            case DRA:
                valorPorMinuto = 0.70;
                break;
            case FIC:
                valorPorMinuto = 0.50;
                break;
            case ROM:
                valorPorMinuto = 0.30;
                break;
            default:
                valorPorMinuto = 0.0;
                break;
        }
        return valorPorMinuto * getDuracao();
    }
}
