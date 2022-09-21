package com.co.software.empresas.desaInt.domain;

import com.co.software.empresas.desaInt.services.ServiceEmpresa;
import com.co.software.empresas.desaInt.util.EnumRol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "empleado")
public class EntityEmpleado {

    private Long idEmpresa;

    public EntityEmpleado(){

    }

    public EntityEmpleado(String nombre, String correo, EnumRol rol, EntityEmpresa empresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.empresa = empresa;
        this.idEmpresa = empresa.getId();
    }

    public EntityEmpleado(EntityEmpresa empresa) {
        this.empresa = empresa;
        this.idEmpresa = empresa.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private EnumRol rol;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    @JsonIgnore
    private Collection<EntityMovimientoDinero> movimientoDineroCollection;

    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntityEmpresa empresa;
}
