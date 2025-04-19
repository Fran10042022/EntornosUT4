package prueba;

public class EntornosFactorizar {
    
	/*
	* Fran:
	* Calcula el coste final de un producto segun los rangos
	* del Usuario aplicando asi los descuentos y cargos pertinentes
	*/
   public double calculaDato(final Producto producto, final double descuento,final double impuestos,final boolean esOfertaEspecial,final boolean esNavidad,final int cuota,final boolean esEnvioGratis,final String codigoCupon,final Usuario usuario) {//El pmd hace falso positivo con el comentario
       
        double total = aplicarDescuentosYCargosGenerales(producto, descuento, impuestos, esOfertaEspecial, esNavidad, usuario);

        if (cuota>0) { total =aplicarCuota(cuota, total);}

		    if (!esEnvioGratis) { total += producto.getPrecioEnvio();}

        if (codigoCupon != null && !codigoCupon.isBlank()) {
            total = aplicarCuponDescuento(total, codigoCupon);
        }

        if (!producto.validarProducto()) {
            throw new IllegalArgumentException("El producto no es vÃ¡lido para esta compra.");}

      
		/*
		* Fran: aplica el descuento del usuario,
		* comprueba que no sea negativo
		* 
		* @total con dos decimales
		*/
        return Math.round(((usuario != null ? aplicarDescuentoPorUsuario(usuario.getTipo(), Math.max(0, total)) : Math.max(0, total))) * 100.0) / 100.0;
    }

	private static double aplicarCuota(final int cuota,final double total) {
    	 double totalCuota =total;
        switch (cuota) {
            case 3:
            	totalCuota *= 1.1;
                break;
            case 6:
            	totalCuota *= 1.2;
                break;
            case 12:
            	totalCuota *= 1.3;
                break;
            default:
                throw new IllegalArgumentException("Cuota no válida: " + cuota);
        }
        return totalCuota;
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
	    * David: Metodo que aplica el descuento segun el enum TipoUsuario 
	    */
	    private static double aplicarDescuentoPorUsuario(final TipoUsuario tipo, final double total) {
	    	final double descuento = 
	    	switch (tipo) {
		        case EMPLEADO -> 0.7;
		        case MIEMBRO_GOLD -> 0.85;
		        case MIEMBRO_SILVER -> 0.9;
		        case REGULAR -> 1.0;
	    	};
	        
	    	return total * descuento;
	    }
	    
	    /*
	     * Fran: Devuelve el total base con los descuentos 
		 * y cargos principales aplicados
	     */
	    private double aplicarDescuentosYCargosGenerales(final Producto producto, final double descuento,final double impuestos,final boolean oferE,final boolean esNavidad, final Usuario usuario) {
			
			double total = producto.calcularBase(descuento);

			total = usuario.descuentoTarjetaFidelidad(total);

			total += total * (impuestos / 100);

			total = usuario.calcularDescuentos(total, oferE, esNavidad);

			return usuario.recargoMetodoPago(total);
					
	    }
}
    