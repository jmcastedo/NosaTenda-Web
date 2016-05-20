package es.udc.jcastedo.NosaTenda.webservice.service;

import java.util.Calendar;

public class VentasBlockWTO {

	private Long id;
	//private Calendar fecha_puesta_venta;
	//private Calendar fecha_retirada;
	private Long ventas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Calendar getFecha_puesta_venta() {
//		return fecha_puesta_venta;
//	}
//	public void setFecha_puesta_venta(Calendar fecha_puesta_venta) {
//		this.fecha_puesta_venta = fecha_puesta_venta;
//	}
//	public Calendar getFecha_retirada() {
//		return fecha_retirada;
//	}
//	public void setFecha_retirada(Calendar fecha_retirada) {
//		this.fecha_retirada = fecha_retirada;
//	}
	public Long getVentas() {
		return ventas;
	}
	public void setVentas(Long ventas) {
		this.ventas = ventas;
	}
	
	
}