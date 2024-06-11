import java.util.ArrayList;

public class Midiateca {
    private ArrayList<Midia> listaMidias;

    public Midiateca() {
        this.listaMidias = new ArrayList<>();
    }

    public boolean cadastraMidia(Midia midia) {
        if (consultaPorCodigo(midia.getCodigo()) != null) {
            return false;
        } else {
            listaMidias.add(midia);
            return true;
        }
    }

    public Midia consultaPorCodigo(int codigo) {
        for (Midia m : listaMidias) {
            if (m.getCodigo() == codigo)
                return m;
        }
        return null;
    }

    public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
        ArrayList<Midia> midiasPorCategoria = new ArrayList<>();
        for (Midia m : listaMidias) {
            if (m.getCategoria() == categoria)
                midiasPorCategoria.add(m);
        }
        return midiasPorCategoria;
    }

    public boolean removeMidia(int codigo) {
        Midia midia = consultaPorCodigo(codigo);
        if (midia != null) {
            listaMidias.remove(midia);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Video> consultaVideosPorQualidade(int qualidade) {
        ArrayList<Video> videosPorQualidade = new ArrayList<>();
        for (Midia m : listaMidias) {
            if (m instanceof Video) {
                Video video = (Video) m;
                if (video.getQualidade() == qualidade) {
                    videosPorQualidade.add(video);
                }
            }
        }
        return videosPorQualidade;
    }

    public Musica consultaMusicaMaiorDuracao() {
        Musica musicaMaiorDuracao = null;
        for (Midia m : listaMidias) {
            if (m instanceof Musica) {
                Musica musica = (Musica) m;
                if (musicaMaiorDuracao == null || musica.getDuracao() > musicaMaiorDuracao.getDuracao()) {
                    musicaMaiorDuracao = musica;
                }
            }
        }
        return musicaMaiorDuracao;
    }

    public double somatorioLocacoes() {
        double somatorio = 0.0;
        for (Midia m : listaMidias) {
            somatorio += m.calculaLocacao();
        }
        return somatorio;
    }
}
