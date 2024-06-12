public abstract class Midia {
    private int codigo;
    private String titulo;
    private int ano;
    private Categoria categoria;

    public Midia(int codigo, String titulo, Categoria categoria, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Midia{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", categoria=" + categoria +
                '}';
    }

    public abstract double calculaLocacao();
}
