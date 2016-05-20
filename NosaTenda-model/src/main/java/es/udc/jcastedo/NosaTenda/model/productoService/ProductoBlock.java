package es.udc.jcastedo.NosaTenda.model.productoService;

import java.util.Calendar;

public class ProductoBlock {

	private Long version;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private Double precio;
	private Long stock_inicial;
	
	public ProductoBlock() {}

	public ProductoBlock(Long version, Calendar fecha_puesta_venta,
			Calendar fecha_retirada, Double precio, Long stock_inicial) {
		
		this.version = version;
		this.fecha_puesta_venta = fecha_puesta_venta;
		this.fecha_retirada = fecha_retirada;
		this.precio = precio;
		this.stock_inicial = stock_inicial;
	}



	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Calendar getFecha_puesta_venta() {
		return fecha_puesta_venta;
	}

	public void setFecha_puesta_venta(Calendar fecha_puesta_venta) {
		this.fecha_puesta_venta = fecha_puesta_venta;
	}

	public Calendar getFecha_retirada() {
		return fecha_retirada;
	}

	public void setFecha_retirada(Calendar fecha_retirada) {
		this.fecha_retirada = fecha_retirada;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getStock_inicial() {
		return stock_inicial;
	}

	public void setStock_inicial(Long stock_inicial) {
		this.stock_inicial = stock_inicial;
	}
	
	
}
