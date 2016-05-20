package es.udc.jcastedo.NosaTenda.webservice.service;

/**
 * Esta clase encapsula los parámetros de la petición POST para favoritos,
 * y es usada por jackson para parsearla
 * @author jmcastedo
 *
 */
public class FavoritoWTO {

	private Long clienteId;
	private Long tiendaId;
	
	public FavoritoWTO() {}
	
	public FavoritoWTO(Long clienteId, Long tiendaId) {
		this.clienteId = clienteId;
		this.tiendaId = tiendaId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getTiendaId() {
		return tiendaId;
	}

	public void setTiendaId(Long tiendaId) {
		this.tiendaId = tiendaId;
	}
}
