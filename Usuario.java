/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

public class Usuario {
    private String nombre;
    private String email;
    private TipoUsuario tipo;
    private MetodoDePago metodoPago;
    private boolean tieneTarjetaFidelidad;
    private double saldoTarjeta;
    private boolean esMiembroVip;
 
    public Usuario(String nombre, String email, TipoUsuario tipo, MetodoDePago metodoPago, boolean tieneTarjetaFidelidad, double saldoTarjeta, boolean esMiembroVip) {
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
        this.metodoPago = metodoPago;
        this.tieneTarjetaFidelidad = tieneTarjetaFidelidad;
        this.saldoTarjeta = saldoTarjeta;
        this.esMiembroVip = esMiembroVip;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    
    public TipoUsuario getTipo() {
        return tipo;
    }
    
    public MetodoDePago getMetodoPago() {
        return metodoPago;
    }
    
    public boolean isTieneTarjetaFidelidad() {
		return tieneTarjetaFidelidad;
	}

	public void setTieneTarjetaFidelidad(boolean tieneTarjetaFidelidad) {
		this.tieneTarjetaFidelidad = tieneTarjetaFidelidad;
	}
	
	public double getSaldoTarjeta() {
		return saldoTarjeta;
	}

	public void setSaldoTarjeta(double saldoTarjeta) {
		this.saldoTarjeta = saldoTarjeta;
	}

    public boolean isEsMiembroVip() {
		return esMiembroVip;
	}

	public void setEsMiembroVip(boolean esMiembroVip) {
		this.esMiembroVip = esMiembroVip;
	}

    public double descuentoTarjetaFidelidad(final double total) {
		double resultado = total;
		
		if (tieneTarjetaFidelidad && saldoTarjeta > 0) {
			resultado -= saldoTarjeta;
		}
		return resultado;
	}

    public double calcularDescuentos(final double total,final boolean oferE,final boolean esNavidad) {
		
		double resultado = total;

	    if (oferE) {
	        resultado *= 0.9;
	    }

	    if (esNavidad) {
	        resultado *= 0.85;
	    }

	    if (esMiembroVip) {
	        resultado *= 0.8;
	    }

	    return resultado;
	}

    public double recargoMetodoPago(final double total) {
		final double descuento = switch (metodoPago) {
	        case TARJETACREDITO -> 0.7;
	        case PAYPAL -> 0.85;
	        case EFECTIVO -> 0.9;
		};
	
		return total * descuento;
	}

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo=" + tipo + '}';
    }
}
