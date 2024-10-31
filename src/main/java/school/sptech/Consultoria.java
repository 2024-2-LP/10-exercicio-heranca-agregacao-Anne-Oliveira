package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
            return true;
        }
        return false;
    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack() && desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
            return true;
        }
        return false;
    }

    public Double getTotalSalarios() {
        Double totalSalarios = 0.0;
        for (Desenvolvedor desenvolvedorDaVez : desenvolvedores) {
            totalSalarios += desenvolvedorDaVez.calcularSalario();
        }
        return totalSalarios;
    }

    public Integer qtdDesenvolvedoresMobile() {
        return (int) desenvolvedores.stream()
                .filter(desenvolvedor -> desenvolvedor instanceof DesenvolvedorMobile)
                .count();
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> salarios = new ArrayList<>();
        for (Desenvolvedor desenvolvedorDaVez : desenvolvedores) {
            if (desenvolvedorDaVez.calcularSalario() >= salario) {
                salarios.add(desenvolvedorDaVez);
            }
        }
        return salarios;
    }

    public Desenvolvedor buscarMenorSalario() {
        return desenvolvedores.stream().min((desenvolvedorAtual, desenvolvedor) -> Double.compare(desenvolvedorAtual.calcularSalario(), desenvolvedor.calcularSalario())).orElse(null);
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> resultado = new ArrayList<>();
        for (Desenvolvedor desenvolvedorDaVez : desenvolvedores) {
            if (desenvolvedorDaVez instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb desenvolvedorWeb = (DesenvolvedorWeb) desenvolvedorDaVez;
                if (tecnologia.equals(desenvolvedorWeb.getBackend()) || tecnologia.equals(desenvolvedorWeb.getFrontend()) || tecnologia.equals(desenvolvedorWeb.getSgbd())) {
                    resultado.add(desenvolvedorDaVez);
                }
            } else if (desenvolvedorDaVez instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile desenvolvedorMobile = (DesenvolvedorMobile) desenvolvedorDaVez;
                if (tecnologia.equals(desenvolvedorMobile.getPlataforma()) || tecnologia.equals(desenvolvedorMobile.getLinguagem())) {
                    resultado.add(desenvolvedorDaVez);
                }
            }
        }
        return resultado;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> desenvolvedorPorTecnologia = buscarPorTecnologia(tecnologia);

        Double totalSalarios = 0.0;
        for (Desenvolvedor desenvolvedorDaVez : desenvolvedorPorTecnologia) {
            totalSalarios += desenvolvedorDaVez.calcularSalario();
        }
        return totalSalarios;
    }
}
