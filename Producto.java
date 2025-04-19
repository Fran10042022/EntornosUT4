package prueba;

import java.util.Map;
import java.util.Set;

public class Producto {
	private String tipoProducto;
	private String categoriaProducto;
	private double precioBase;
	private double precioEnvio;
	private int cantidad;
	
	public Producto(String tipoProducto, String categoriaProducto, double precioBase, int cantidad, double precioEnvio) {
		this.tipoProducto = tipoProducto;
		this.categoriaProducto = categoriaProducto;
		this.precioBase = precioBase;
		this.cantidad = cantidad;
		this.precioEnvio = precioEnvio;
	}

	public double getPrecioEnvio() {
		return precioEnvio;
	}

	public void setPrecioEnvio(double precioEnvio) {
		this.precioEnvio = precioEnvio;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double calcularBase(final double descuento) {
		double total = precioBase * cantidad;
		
		if (descuento > 0) {
			total -= total * (descuento / 100);
		}
		return total;
		
	}
	
	public boolean validarProducto() {

		final Set<String> categoriasElec = Set.of("Smartphones");
		final Set<String> categoriasRopa = Set.of("Hombre", "Mujer");

		final Map<String, Set<String>> productValid = Map.of(
				"Electronico", categoriasElec,
				"Ropa",categoriasRopa);

		return productValid.containsKey(tipoProducto) && productValid.get(tipoProducto).contains(categoriaProducto);
    }

}
