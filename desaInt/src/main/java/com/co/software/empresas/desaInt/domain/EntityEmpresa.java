package com.co.software.empresas.desaInt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "empresa")
public class EntityEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "nit")
    private Long nit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    @JsonIgnore
    private Collection<EntityEmpleado> empleadoCollection;
}
