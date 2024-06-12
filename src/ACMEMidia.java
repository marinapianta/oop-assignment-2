import java.io.*;
import java.util.*;

public class ACMEMidia {
    private Midiateca midiateca;

    public ACMEMidia() {
        this.midiateca = new Midiateca();
    }

    public void executa() {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {

            // Passo 1: Cadastrar vídeos
            while (true) {
                String codigoStr = reader.readLine();
                if (codigoStr.equals("-1")) break;
                String titulo = reader.readLine();
                String anoStr = reader.readLine();
                String categoriaStr = reader.readLine();
                String qualidadeStr = reader.readLine();

                try {
                    int codigo = Integer.parseInt(codigoStr);
                    int ano = Integer.parseInt(anoStr);
                    Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
                    int qualidade = Integer.parseInt(qualidadeStr);

                    if (midiateca.buscarPorCodigo(codigo) == null) {
                        Video video = new Video(codigo, titulo, ano, categoria, qualidade);
                        midiateca.adicionarMidia(video);
                        writer.println("1:" + codigo + "," + titulo + "," + ano + "," + categoria.getNome() + "," + qualidade);
                    } else {
                        writer.println("1:Erro-video com codigo repetido: " + codigo);
                    }
                } catch (Exception e) {
                    writer.println("1:Erro ao processar video: " + codigoStr + "," + titulo + "," + anoStr + "," + categoriaStr + "," + qualidadeStr);
                }
            }

            // Passo 2: Cadastrar músicas
            while (true) {
                String codigoStr = reader.readLine();
                if (codigoStr.equals("-1")) break;
                String titulo = reader.readLine();
                String anoStr = reader.readLine();
                String categoriaStr = reader.readLine();
                String duracaoStr = reader.readLine();

                try {
                    int codigo = Integer.parseInt(codigoStr);
                    int ano = Integer.parseInt(anoStr);
                    Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
                    double duracao = Double.parseDouble(duracaoStr);

                    if (midiateca.buscarPorCodigo(codigo) == null) {
                        Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
                        midiateca.adicionarMidia(musica);
                        writer.println("2:" + codigo + "," + titulo + "," + ano + "," + categoria.getNome() + "," + duracao);
                    } else {
                        writer.println("2:Erro-musica com codigo repetido: " + codigo);
                    }
                } catch (Exception e) {
                    writer.println("2:Erro ao processar musica: " + codigoStr + "," + titulo + "," + anoStr + "," + categoriaStr + "," + duracaoStr);
                }
            }

            // Passo 3: Mostrar dados de uma mídia
            try {
                int codigoMidia = Integer.parseInt(reader.readLine());
                Midia midia = midiateca.buscarPorCodigo(codigoMidia);
                if (midia == null) {
                    writer.println("3:Codigo inexistente.");
                } else {
                    writer.println("3:" + midia);
                }
            } catch (Exception e) {
                writer.println("3:Erro ao processar código de mídia.");
            }

            // Passo 4: Mostrar dados de mídias de uma categoria
            try {
                Categoria categoriaMidia = Categoria.valueOf(reader.readLine().toUpperCase());
                List<Midia> midiasPorCategoria = midiateca.buscarPorCategoria(categoriaMidia);
                if (midiasPorCategoria.isEmpty()) {
                    writer.println("4:Nenhuma midia encontrada.");
                } else {
                    for (Midia m : midiasPorCategoria) {
                        writer.println("4:" + m);
                    }
                }
            } catch (Exception e) {
                writer.println("4:Erro ao processar categoria de mídia.");
            }

            // Passo 5: Mostrar dados de vídeos de uma qualidade
            try {
                int qualidadeMidia = Integer.parseInt(reader.readLine());
                List<Video> videosPorQualidade = midiateca.buscarPorQualidade(qualidadeMidia);
                if (videosPorQualidade.isEmpty()) {
                    writer.println("5:Qualidade inexistente.");
                } else {
                    for (Video v : videosPorQualidade) {
                        writer.println("5:" + v);
                    }
                }
            } catch (Exception e) {
                writer.println("5:Erro ao processar qualidade de vídeo.");
            }

            // Passo 6: Mostrar dados da música de maior duração
            try {
                Musica maiorDuracaoMusica = midiateca.buscarMusicaMaiorDuracao();
                if (maiorDuracaoMusica == null) {
                    writer.println("6:Nenhuma música encontrada.");
                } else {
                    writer.println("6:" + maiorDuracaoMusica.getTitulo() + "," + maiorDuracaoMusica.getDuracao());
                }
            } catch (Exception e) {
                writer.println("6:Erro ao processar música de maior duração.");
            }

            // Passo 7: Remover uma mídia
            try {
                int codigoRemover = Integer.parseInt(reader.readLine());
                Midia midiaRemover = midiateca.buscarPorCodigo(codigoRemover);
                if (midiaRemover == null) {
                    writer.println("7:Codigo inexistente.");
                } else {
                    writer.println("7:" + midiaRemover);
                    midiateca.removerMidia(codigoRemover);
                }
            } catch (Exception e) {
                writer.println("7:Erro ao processar código de mídia para remoção.");
            }

            // Passo 8: Mostrar somatório de locações de todas as mídias
            try {
                double totalLocacoes = midiateca.calcularSomatorioLocacoes();
                if (midiateca.getMidias().isEmpty()) {
                    writer.println("8:Nenhuma midia encontrada.");
                } else {
                    writer.println("8:" + String.format("%.2f", totalLocacoes));
                }
            } catch (Exception e) {
                writer.println("8:Erro ao processar somatório de locações.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}