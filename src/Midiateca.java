import java.util.*;

public class Midiateca implements Iterador {
    private List<Midia> midias;
    private int currentIndex;

    public Midiateca() {
        this.midias = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void adicionarMidia(Midia midia) {
        midias.add(midia);
    }

    public boolean removerMidia(int codigo) {
        Iterator<Midia> it = midias.iterator();
        while (it.hasNext()) {
            Midia midia = it.next();
            if (midia.getCodigo() == codigo) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Midia buscarPorCodigo(int codigo) {
        for (Midia midia : midias) {
            if (midia.getCodigo() == codigo) {
                return midia;
            }
        }
        return null;
    }

    public List<Midia> buscarPorCategoria(Categoria categoria) {
        List<Midia> result = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia.getCategoria() == categoria) {
                result.add(midia);
            }
        }
        return result;
    }

    public List<Video> buscarPorQualidade(int qualidade) {
        List<Video> result = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Video && ((Video) midia).getQualidade() == qualidade) {
                result.add((Video) midia);
            }
        }
        return result;
    }

    public Musica buscarMusicaMaiorDuracao() {
        Musica maiorDuracaoMusica = null;
        for (Midia midia : midias) {
            if (midia instanceof Musica) {
                Musica musica = (Musica) midia;
                if (maiorDuracaoMusica == null || musica.getDuracao() > maiorDuracaoMusica.getDuracao()) {
                    maiorDuracaoMusica = musica;
                }
            }
        }
        return maiorDuracaoMusica;
    }

    public double calcularSomatorioLocacoes() {
        double total = 0;
        for (Midia midia : midias) {
            total += midia.calculaLocacao();
        }
        return total;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < midias.size();
    }

    @Override
    public Midia next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return midias.get(currentIndex++);
    }
}
