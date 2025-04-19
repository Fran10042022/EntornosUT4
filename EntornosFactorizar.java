package prueba;

public class EntornosFactorizar {
    
    
    public double calculaDato(Producto producto, double descuento, double impuestos, boolean esOfertaEspecial, boolean esNavidad, int cuota, boolean esEnvioGratis, String codigoCupon, Usuario usuario) {
       
        double total = aplicarDescuentosYCargosGenerales(producto, descuento, impuestos, esOfertaEspecial, esNavidad, usuario);

        if (cuota>0) aplicarCuota(cuota, total);

		if (!esEnvioGratis) total += producto.getPrecioEnvio();

        if (codigoCupon != null && !codigoCupon.isBlank()) {
            total = aplicarCuponDescuento(total, codigoCupon);
        }

        if (!producto.validarProducto())
            throw new IllegalArgumentException("El producto no es vÃ¡lido para esta compra.");

      
		/*
		* Fran: aplica el descuento del usuario,
		* comprueba que no sea negativo,
		* devuelve el total con dos decimales
		*/
        return Math.round(((usuario != null ? aplicarDescuentoPorUsuario(usuario.getTipo(), Math.max(0, total)) : Math.max(0, total))) * 100.0) / 100.0;
    }
    

	    private static double aplicarCuota(int cuota, double total) {
    	switch (cuota) {
			case 3:
			total *= 1.1;
			break;
			case 6:
			total *= 1.2;
			break;
			case 12:
			total *= 1.3;
			break;
			
			default:
			System.out.println("Opcion invalida");
		}
    	return total;
    }
  
    private double aplicarCuponDescuento(final double total,final String codigoCupon) { //Elena: hacemos un switch y creamos una variable para optimizar el codigo

        final double descuento = switch (codigoCupon) {

            case "CUPOFF" -> 0.8;
            case "NAVIDAD2025" -> 0.75;
            default -> 1.0;

        };

        return total * descuento;

    }

    /*
    *David: Metodo que aplica el descuento segun el enum TipoUsuario 
    */
    private static double aplicarDescuentoPorUsuario(final TipoUsuario tipo, final double total) {
    	final double descuento = 
    	switch (tipo) {
	        case EMPLEADO -> 0.7;
	        case MIEMBRO_GOLD -> 0.85;
	        case MIEMBRO_SILVER -> 0.9;
	        case REGULAR -> 1.0;
	        default -> throw new IllegalArgumentException("Tipo de usuario no encontrado.");
    	};
        
    	return total * descuento;
    }
    
    /*
     * Metodo que devuelve el total base con los descuentos principales aplicados
     */
    private double aplicarDescuentosYCargosGenerales(final Producto producto, final double descuento,final double impuestos,final boolean oferE,final boolean esNavidad, final Usuario usuario) {
		double total = producto.getPrecioBase() * producto.getCantidad();
		
		if (descuento > 0) {
			total -= total * (descuento / 100);
		}

		if (usuario.isTieneTarjetaFidelidad() && usuario.getSaldoTarjeta() > 0) {
			total -= usuario.getSaldoTarjeta();
		}

		total += total * (impuestos / 100);

		if (oferE) {
			total *= 0.9;
		}

		if (esNavidad) {
			total *= 0.85;
		}

		if (usuario.isEsMiembroVip()) {
			total *= 0.8;
		}

		switch (usuario.getMetodoPago()) {
	        case TARJETACREDITO: return total * 1.05;
	        case PAYPAL: return total * 1.02;
	        case EFECTIVO: return total;
	        default: throw new IllegalArgumentException("Metodo de Pago Invalido");
		}
				
	}
}
    