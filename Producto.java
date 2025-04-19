package prueba;

import java.util.Map;
import java.util.Set;

public class Producto {
	private static String tipoProducto;
	private static String categoriaProducto;
	private double precioBase;
	private double precioEnvio;
	private int cantidad;
	
	public Producto(String tipoProducto, String categoriaProducto, double precioBase, int cantidad, double precioEnvio) {
		Producto.tipoProducto = tipoProducto;
		Producto.categoriaProducto = categoriaProducto;
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
		Producto.tipoProducto = tipoProducto;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		Producto.categoriaProducto = categoriaProducto;
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

	public double calcularBase() {
		return precioBase * cantidad;
		
	}
	
	boolean validarProducto() {
		Set<String> categoriasElectronico = Set.of("Smartphones");
		Set<String> categoriasRopa = Set.of("Hombre", "Mujer");

		Map<String, Set<String>> productosValidos = Map.of(
				"Electronico", categoriasElectronico,
				"Ropa",categoriasRopa);

		return productosValidos.containsKey(tipoProducto) && productosValidos.get(tipoProducto).contains(categoriaProducto);
    }

}
