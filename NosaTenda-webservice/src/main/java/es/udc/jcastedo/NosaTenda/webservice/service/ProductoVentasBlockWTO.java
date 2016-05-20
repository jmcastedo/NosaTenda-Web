package es.udc.jcastedo.NosaTenda.webservice.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductoVentasBlockWTO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoVentasBlockWTO.class);

	private Long id;
	private Long version;
	private Calendar fecha_puesta_venta;
	private Calendar fecha_retirada;
	private Double precio;
	private Long stock_inicial;
	private Long ventas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getVentas() {
		return ventas;
	}
	public void setVentas(Long ventas) {
		this.ventas = ventas;
	}
	
	public String intervalo() {
		
		SimpleDateFormat date_format = new SimpleDateFormat("dd-MM");
		
		String titulo = new String();
		
		titulo = titulo + "'";
		
		titulo = titulo + date_format.format(this.fecha_puesta_venta.getTime());
		
		titulo = titulo + " - ";
		
		titulo = titulo + date_format.format(this.fecha_retirada.getTime());
		
		titulo = titulo + "'";
		
		logger.debug("titulo: " + titulo);
		
		return titulo;
	}
	
}
