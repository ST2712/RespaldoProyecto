package com.co.software.empresas.desaInt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public EntityMovimientoDinero(EntityEmpleado empleado) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(Long montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }

    public String getConceptoMovimiento() {
        return conceptoMovimiento;
    }

    public void setConceptoMovimiento(String conceptoMovimiento) {
        this.conceptoMovimiento = conceptoMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EntityEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EntityEmpleado empleado) {
        this.empleado = empleado;
    }
    
}
