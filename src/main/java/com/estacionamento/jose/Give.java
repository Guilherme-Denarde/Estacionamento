//package com.estacionamento.jose;
//
//import com.estacionamento.jose.entity.Conductor;
//import com.estacionamento.jose.entity.Vehicle;
//import lombok.Getter;
//import lombok.Setter;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//public class Give {
//    @Getter
//    @Setter
//    private LocalDateTime enter;
//
//    @Getter @Setter
//    private LocalDateTime exit;
//
//    @Getter @Setter
//    private Conductor conductor;
//
//    @Getter @Setter
//    private Vehicle vehicle;
//
//    @Getter @Setter
//    private Long tempo;
//
//    @Getter @Setter
//    private Long tempoParaDesconto;
//
//    @Getter @Setter
//    private BigDecimal valorTotal;
//
//    @Getter @Setter
//    private Long desconto;
//
//    public Give(LocalDateTime entrada, LocalDateTime saida, Conductor conductor, Vehicle vehicle, Long tempo, LocalTime tempoParaDesconto, BigDecimal ValueTotal, Long desconto) {
//        this.enter = entrada;
//        this.exit = saida;
//        this.conductor = conductor;
//        this.vehicle = vehicle;
//        this.tempo = tempo;
//        this.tempoParaDesconto = tempoParaDesconto;
//        this.valorTotal = ValueTotal;
//        this.desconto = desconto;
//    }
//}
