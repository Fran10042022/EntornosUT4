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

    public double descuentoTarjetaFidelidad(double total) {
		if (tieneTarjetaFidelidad && saldoTarjeta > 0) {
			total -= saldoTarjeta;
		}
		return total;
	}

    public double calcularDescuentos(double total, boolean oferE, boolean esNavidad) {
		
		if (oferE) {
			total *= 0.9;
		}

		if (esNavidad) {
			total *= 0.85;
		}

		if (esMiembroVip) {
			total *= 0.8;
		}
		
		return total;
	}

    public double recargoMetodoPago(double total) {
		switch (metodoPago) {
	        case TARJETACREDITO: return total * 1.05;
	        case PAYPAL: return total * 1.02;
	        case EFECTIVO: return total;
	        default: throw new IllegalArgumentException("Metodo de Pago Invalido");
		}
	}
    
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo=" + tipo + '}';
    }
}
