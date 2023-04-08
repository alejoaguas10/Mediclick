package com.mediclick.entities;

import javax.persistence.*;

@Entity
@Table(name = "historiales")
public class Historiales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistoriales")
    private Integer idhistoriales;
    @Column(name = "num_documento")
    private Integer num_documento;
    @Column(name = "nombre_pac")
    private String nombre_pac;
    @Column(name = "apellido_pac")
    private String apellido_pac;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "motivo_consulta")
    private String motivo_consulta;
    @Column(name = "presion_arterial")
    private String presion_arterial;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "talla")
    private Float talla;

    // Getters and setters
    public Historiales() {
        super();
    }

    public Integer getIdhistoriales() {
        return idhistoriales;
    }

    public void setIdhistoriales(Integer idhistoriales) {
        this.idhistoriales = idhistoriales;
    }

    public Integer getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(Integer num_documento) {
        this.num_documento = num_documento;
    }

    public String getNombre_pac() {
        return nombre_pac;
    }

    public void setNombre_pac(String nombre_pac) {
        this.nombre_pac = nombre_pac;
    }

    public String getApellido_pac() {
        return apellido_pac;
    }

    public void setApellido_pac(String apellido_pac) {
        this.apellido_pac = apellido_pac;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public String getPresion_arterial() {
        return presion_arterial;
    }

    public void setPresion_arterial(String presion_arterial) {
        this.presion_arterial = presion_arterial;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Float getTalla() {
        return talla;
    }

    public void setTalla(Float talla) {
        this.talla = talla;
    }
}
