package com.mediclick.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "insumos")
public class Insumos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinsumos")
    private Integer idinsumos;
    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha_entrada;
    @Column(name = "registro")
    private String registro;
    @Column(name = "codigo_insumo")
    private String codigo_insumo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private Integer cantidad;

    // Getters and setters
    public Insumos() {
        super();
    }

    public Integer getIdinsumos() {
        return idinsumos;
    }

    public void setIdinsumos(Integer idinsumos) {
        this.idinsumos = idinsumos;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getCodigo_insumo() {
        return codigo_insumo;
    }

    public void setCodigo_insumo(String codigo_insumo) {
        this.codigo_insumo = codigo_insumo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() { return cantidad;
    }

    public void setCantidad(Integer cantidad) { this.cantidad = cantidad;
    }
}
