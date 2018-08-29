package administradorBienes;

import java.io.Serializable;

public class Recurso implements Serializable {

    private long id;
    private String serial;
    private String marca;
    private String tipo;
    private String proveedor;
    private long valorCOmercial;
    private String fechaDeCompra;
    private String estado;
    private String estadoAsignacion;


    public Recurso(long id, String serial, String marca, String tipo, String proveedor, long valorCOmercial, String fechaDeCompra, String estado, String estadoAsignacion) {
        this.id = id;
        this.serial = serial;
        this.marca = marca;
        this.tipo = tipo;
        this.proveedor = proveedor;
        this.valorCOmercial = valorCOmercial;
        this.fechaDeCompra = fechaDeCompra;
        this.estado = estado;
        this.estadoAsignacion=estadoAsignacion;

    }
/*
    public Recurso() {
        this.id = 0;
        this.serial = null;
        this.marca = null;
        this.tipo = 0;
        this.proveedor = null;
        this.valorCOmercial = 0;
        this.fechaDeCompra = null;
        this.estado = 0;

    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public long getValorCOmercial() {
        return valorCOmercial;
    }

    public void setValorCOmercial(long valorCOmercial) {
        this.valorCOmercial = valorCOmercial;
    }

    public String getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(String fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(String estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }


}
