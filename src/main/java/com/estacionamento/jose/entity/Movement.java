package com.estacionamento.jose.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "movement", schema = "public")
public class Movement extends AbstractEntity{

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "veiculo", nullable = true, unique = true)
    private Vehicle vehicleId;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "condutor", nullable = true)
    private Conductor conductorId;
    @Getter @Setter
    @Column(name = "entrada", nullable = true)
    private LocalDateTime enter;
    @Getter @Setter
    @Column(name = "saida")
    private LocalDateTime exit;
    @Getter @Setter
    @Column(name = "tempo")
    private Long tempo;
    @Getter @Setter
    @Column(name = "hora")
    private Integer hora;
    @Getter @Setter
    @Column(name = "minutos")
    private Integer minutos;
    @Getter @Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;
    @Getter @Setter
    @Column(name = "tempo_multa")
    private LocalTime tempoMulta;
    @Getter @Setter
    @Column(name = "valor_desconto")
    private Long valorDesconto;
    @Getter @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
    @Getter @Setter
    @Column(name = "valor_hora_multa")
    private BigDecimal valorHoraMulta;
    public BigDecimal calcularValorDesconto(Duration duracao) {
        long minutosUtilizados = duracao.toMinutes();
        if (minutosUtilizados >= 120) { // 2 horas em minutos
            return BigDecimal.valueOf(5); // 5 reais de desconto
        } else {
            return BigDecimal.ZERO;
        }
    }

    public Duration calcularTempoUtilizado() {
        if (exit.isBefore(enter)) {
            return Duration.ZERO; // Retorna duração zero se a data de saída for anterior à data de entrada
        }
        return Duration.between(enter, exit);
    }

    public BigDecimal calcularValorDesconto() {
        Duration duracao = calcularTempoUtilizado();
        long minutosUtilizados = duracao.toMinutes();
        if (minutosUtilizados >= 120) {
            return BigDecimal.valueOf(5);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal calcularValorTotal() {
        Duration duracao = calcularTempoUtilizado();
        long minutosUtilizados = duracao.toMinutes();
        BigDecimal valorPorMinuto = valorHora.divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP);
        BigDecimal valorCalculado = valorPorMinuto.multiply(BigDecimal.valueOf(minutosUtilizados));
        BigDecimal valorDesconto = calcularValorDesconto(duracao);
        BigDecimal valorFinal = valorCalculado.subtract(valorDesconto);

        return valorFinal.setScale(2, RoundingMode.HALF_UP);
    }
    public String gerarRelatorio() {
        Duration duracao = calcularTempoUtilizado();
        long minutosUtilizados = duracao.toMinutes();
        long horas = minutosUtilizados / 60;
        long minutosRestantes = minutosUtilizados % 60;

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Data e Hora de Entrada: ").append(enter).append("\n");
        relatorio.append("Data e Hora de Saída: ").append(exit).append("\n");
        relatorio.append("Condutor: ").append(conductorId.getName()).append("\n");
        relatorio.append("Veículo: ").append(vehicleId.getModelId().getName()).append("\n");
        relatorio.append("Placa: ").append(vehicleId.getPlate()).append("\n");
        relatorio.append("Tempo Utilizado: ").append(horas).append(" horas e ").append(minutosRestantes).append(" minutos\n");
        relatorio.append("Valor a Pagar: R$").append(calcularValorTotal()).append("\n");
        relatorio.append("Valor Desconto: R$").append(calcularValorDesconto(duracao));

        if (exit.isBefore(enter)) {
            return "Erro: A data e hora de saída não podem ser anteriores à data e hora de entrada.";
        }

        return relatorio.toString();
    }

}