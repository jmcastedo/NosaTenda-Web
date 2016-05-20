package es.udc.jcastedo.NosaTenda.webservice.service;

/**
 * Esta clase encapsula los parámetros de la petición POST para hacer reservas,
 * y es usada por jackson para parsearla
 * @author jmcastedo
 *
 */
public class ReservaWTO {

	private Long unidades;
	private Double precio;
	private Long productoId;
	private Long clienteId;
	
	public ReservaWTO() {}
	
	public ReservaWTO(Long unidades, Double precio,
			Long productoId, Long clienteId) {
		this.unidades = unidades;
		this.precio = precio;
		this.productoId = productoId;
		this.clienteId = clienteId;
	}
	public Long getUnidades() {
		return unidades;
	}
	public void setUnidades(Long unidades) {
		this.unidades = unidades;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	
}
