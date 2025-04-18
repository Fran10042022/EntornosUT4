package prueba;
public class EntornosFactorizar {
    
    
    public double calculaDato(double precioBase, int cantidad, double descuento, double impuestos, boolean tieneTarjetaFidelidad, double saldoTarjeta, boolean esOfertaEspecial, boolean esNavidad, boolean esMiembroVip, String metodoPago, boolean aplicarCuotas,final int cuota, boolean esEnvioGratis, double precioEnvio, String tipoProducto, String categoriaProducto, String codigoCupon, Usuario usuario) {
       
        double total = aplicarDescuentosGenerales(precioBase, cantidad, descuento, tieneTarjetaFidelidad, saldoTarjeta, impuestos, esOfertaEspecial, esNavidad, esMiembroVip);
        
        if (metodoPago.equals("TarjetaCredito")) {
            total *= 1.05;
        } else if (metodoPago.equals("PayPal")) {
            total *= 1.02;
        }

      

        //David: Uso switch para optimizar el if que habia anteriormente 
        // y asi lo simplifico
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
	        	total *= 1.00;
	        }


        if (!esEnvioGratis) {
            total += precioEnvio;
        }

        
        if (codigoCupon != null && !codigoCupon.isEmpty()) {
            total = aplicarCuponDescuento(total, codigoCupon);
        }

    
        if (!validarProducto(tipoProducto, categoriaProducto)) {
            throw new IllegalArgumentException("El producto no es válido para esta compra.");
        }

	/*
 	* Fran: aplica el descuento del usuario,
  	* comprueba que no sea negativo,
   	* devuelve el total con dos decimales
	*/
        return Math.round(((usuario != null ? aplicarDescuentoPorUsuario(usuario, Math.max(0, total)) : Math.max(0, total))) * 100.0) / 100.0;
      
    }
    
  
    private double aplicarCuponDescuento(final double total,final String codigoCupon) { //Elena: hacemos un switch y creamos una variable para optimizar el codigo

        final double descuento = switch (codigoCupon) {

            case "CUPOFF" -> 0.8;
            case "NAVIDAD2025" -> 0.75;
            default -> 1.0;

        };

        return total * descuento;

    }
    private boolean validarProducto(final String tipoProducto,final String categoriaProducto) {//Guille
		
	final Set<String> categoriasElec = Set.of("Smartphones");
	final Set<String> categoriasRopa = Set.of("Hombre", "Mujer");

	final Map<String, Set<String>> productosValidos = Map.of(
				"Electronico", categoriasElec,
				"Ropa",categoriasRopa);

		return productosValidos.containsKey(tipoProducto) && productosValidos.get(tipoProducto).contains(categoriaProducto);
	}

    private double aplicarDescuentoPorUsuario(Usuario usuario, double total) {
        if (usuario.esEmpleado()) {
            total *= 0.7; 
        } else if (usuario.esMiembroGold()) {
            total *= 0.85;  
        } else if (usuario.esMiembroSilver()) {
            total *= 0.9; 
        }
        return total;
    }
  
    /*
     * Metodo que devuelve el total base con los descuentos principales aplicados
     */
    private double aplicarDescuentosGenerales(final double precioBase,final int cantidad,final double descuento,final boolean tieneF,final double saldoTarjeta,final double impuestos,final boolean oferE,final boolean esNavidad,final boolean miembroV) {
		double total = precioBase * cantidad;
		
		if (descuento > 0) {
			total -= total * (descuento / 100);
		}

		if (tieneF && saldoTarjeta > 0) {
			total -= saldoTarjeta;
		}

		total += total * (impuestos / 100);

		if (oferE) {
			total *= 0.9;
		}

		if (esNavidad) {
			total *= 0.85;
		}

		if (miembroV) {
			total *= 0.8;
		}

		return total;
		
	}
}
