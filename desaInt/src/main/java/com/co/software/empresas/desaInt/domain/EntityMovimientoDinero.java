package com.co.software.empresas.desaInt.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "movimiento_dinero")
public class EntityMovimientoDinero {

    public EntityMovimientoDinero(){

    }

    public EntityMovimientoDinero(Long montoMoviento, String conceptoMovimiento, EntityEmpleado empleado) {
        this.montoMovimiento = montoMoviento;
        this.conceptoMovimiento = conceptoMovimiento;
        this.empleado = empleado;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.fecha = dtf.format(now);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "monto_movimiento")
    private Long montoMovimiento;

    @Column(name = "concepto_movimiento")
    private String conceptoMovimiento;

    @Column(name = "fecha")
    private String fecha;


    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityEmpleado empleado;
}
