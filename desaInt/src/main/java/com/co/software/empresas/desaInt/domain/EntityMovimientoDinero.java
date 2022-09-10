package com.co.software.empresas.desaInt.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movimiento_dinero")
public class EntityMovimientoDinero {

    public EntityMovimientoDinero(){

    }

    public EntityMovimientoDinero(Long montoMoviento, String conceptoMovimiento, EntityEmpleado empleado) {
        this.montoMoviento = montoMoviento;
        this.conceptoMovimiento = conceptoMovimiento;
        this.empleado = empleado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "monto_movimiento")
    private Long montoMoviento;

    @Column(name = "concepto_movimiento")
    private String conceptoMovimiento;


    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityEmpleado empleado;
}
