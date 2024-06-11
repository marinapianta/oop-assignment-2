import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ACMEMidia {
    private Map<Integer, Midia> midias;
    private ArrayList<String> saida;

    public ACMEMidia() {
        midias = new HashMap<>();
        saida = new ArrayList<>();
    }

    private void lerDados(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while (!(linha = br.readLine()).equals("-1")) {
                int codigo = Integer.parseInt(linha);
                String titulo = br.readLine();
                int ano = Integer.parseInt(br.readLine());
                Categoria categoria = Categoria.valueOf(br.readLine());
                int qualidade = Integer.parseInt(br.readLine());
                cadastrarVideo(codigo, titulo, ano, categoria, qualidade);
            }
            while (!(linha = br.readLine()).equals("-1")) {
                int codigo = Integer.parseInt(linha);
                String titulo = br.readLine();
                int ano = Integer.parseInt(br.readLine());
                Categoria categoria = Categoria.valueOf(br.readLine());
                double duracao = Double.parseDouble(br.readLine());
                cadastrarMusica(codigo, titulo, ano, categoria, duracao);
            }
            int codMidia = Integer.parseInt(br.readLine());
            mostrarDadosCodigo(codMidia);

            String catMidia = br.readLine();
            mostrarDadosCategoria(catMidia);

            int qualMidia = Integer.parseInt(br.readLine());
            mostrarDadosQualidade(qualMidia);

            int codRemove = Integer.parseInt(br.readLine());
            removerMidia(codRemove);

            mostrarSomatorioLocacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cadastrarVideo(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        if (midias.containsKey(codigo)) {
            saida.add("1:Erro-video com codigo repetido: " + codigo);
        } else {
            Video video = new Video(codigo, titulo, ano, categoria, qualidade);
            midias.put(codigo, video);
            saida.add("1:" + codigo + "," + titulo + "," + ano + "," + categoria + "," + qualidade);
        }
    }

    private void cadastrarMusica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
        if (midias.containsKey(codigo)) {
            saida.add("2:Erro-musica com codigo repetido: " + codigo);
        } else {
            Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
            midias.put(codigo, musica);
            saida.add("2:" + codigo + "," + titulo + "," + ano + "," + categoria + "," + String.format("%.2f", duracao));
        }
    }


    private void mostrarDadosCodigo(int codigo) {
        Midia midia = midias.get(codigo);
        if (midia == null) {
            saida.add("3:Codigo inexistente.");
        } else {
            saida.add("3:" + midia);
        }
    }

    private void mostrarDadosCategoria(String categoria) {
        boolean encontrado = false;
        for (Midia midia : midias.values()) {
            if (midia.getCategoria().equals(categoria)) {
                if (!encontrado) {
                    saida.add("4:" + midia);
                    encontrado = true;
                } else {
                    saida.add(midia.toString());
                }
            }
        }
        if (!encontrado) {
            saida.add("4:Nenhuma midia encontrada.");
        }
    }

    private void mostrarDadosQualidade(int qualidade) {
        boolean encontrado = false;
        for (Midia midia : midias.values()) {
            if (midia instanceof Video && ((Video) midia).getQualidade() == qualidade) {
                if (!encontrado) {
                    saida.add("5:" + midia);
                    encontrado = true;
                } else {
                    saida.add(midia.toString());
                }
            }
        }
        if (!encontrado) {
            saida.add("5:Qualidade inexistente.");
        }
    }

    private void mostrarMaiorDuracaoMusica() {
        Musica maiorDuracao = null;
        for (Midia midia : midias.values()) {
            if (midia instanceof Musica) {
                if (maiorDuracao == null || ((Musica) midia).getDuracao() > maiorDuracao.getDuracao()) {
                    maiorDuracao = (Musica) midia;
                }
            }
        }
        if (maiorDuracao == null) {
            saida.add("6:Nenhuma música encontrada.");
        } else {
            saida.add("6:" + maiorDuracao.getTitulo() + "," + String.format("%.2f", maiorDuracao.getDuracao()));
        }
    }

    private void removerMidia(int codigo) {
        Midia midia = midias.remove(codigo);
        if (midia == null) {
            saida.add("7:Codigo inexistente.");
        } else {
            saida.add("7:" + midia);
        }
    }

    private void mostrarSomatorioLocacoes() {
        double somatorio = 0;
        for (Midia midia : midias.values()) {
            somatorio += midia.calculaLocacao();
        }
        if (midias.isEmpty()) {
            saida.add("8:Nenhuma midia encontrada.");
        } else {
            saida.add("8:" + String.format("%.2f", somatorio));
        }
    }

    private void processarSaida(String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : saida) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

