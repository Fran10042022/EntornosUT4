package prueba;

import java.util.Map;
import java.util.Set;

public class EntornosFactorizar {
    
    
    public double calculaDato(double precioBase, int cantidad, double descuento, double impuestos, boolean esOfertaEspecial, boolean esNavidad, String metodoPago, boolean aplicarCuotas,final int cuota, boolean esEnvioGratis, double precioEnvio, String tipoProducto, String categoriaProducto, String codigoCupon, Usuario usuario) {
       
        double total = aplicarDescuentosYCargosGenerales(precioBase, cantidad, descuento, impuestos, esOfertaEspecial, esNavidad,usuario);

        if (cuota>0) aplicarCuota(cuota, total);

		if (!esEnvioGratis) total += precioEnvio;

        if (codigoCupon != null && !codigoCupon.isBlank()) {
            total = aplicarCuponDescuento(total, codigoCupon);
        }

        if (!validarProducto(tipoProducto, categoriaProducto))
            throw new IllegalArgumentException("El producto no es vÃ¡lido para esta compra.");

      
		/*
		* Fran: aplica el descuento del usuario,
		* comprueba que no sea negativo,
		* devuelve el total con dos decimales
		*/
        return Math.round(((usuario != null ? aplicarDescuentoPorUsuario(usuario, Math.max(0, total)) : Math.max(0, total))) * 100.0) / 100.0;
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
    private boolean validarProducto(final String tipoProducto,final String categoriaProducto) {//Guille
		
	final Set<String> categoriasElec = Set.of("Smartphones");
	final Set<String> categoriasRopa = Set.of("Hombre", "Mujer");

	final Map<String, Set<String>> productosValidos = Map.of(
				"Electronico", categoriasElec,
				"Ropa",categoriasRopa);

		return productosValidos.containsKey(tipoProducto) && productosValidos.get(tipoProducto).contains(categoriaProducto);
	}

    /*
    *David: Metodo que aplica el descuento segun el enum TipoUsuario 
    */
    private static double aplicarDescuentoPorUsuario(Usuario usuario, double total) {
    	switch (usuario.getTipo()) {
	        case EMPLEADO: return total * 0.7;
	        case MIEMBRO_GOLD: return total * 0.85;
	        case MIEMBRO_SILVER: return total * 0.9;
	        default: return total;
    	}
        
    }
    
    /*
     * Metodo que devuelve el total base con los descuentos principales aplicados
     */
    private double aplicarDescuentosYCargosGenerales(final double precioBase,final int cantidad,final double descuento,final double impuestos,final boolean oferE,final boolean esNavidad, final Usuario usuario) {
		double total = precioBase * cantidad;
		
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
    