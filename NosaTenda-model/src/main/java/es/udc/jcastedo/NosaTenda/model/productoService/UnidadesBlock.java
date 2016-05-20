package es.udc.jcastedo.NosaTenda.model.productoService;

public class UnidadesBlock {

	private Long productoId;
	private Long unidadesVendidas;
	
	public UnidadesBlock() {}

	public UnidadesBlock(Long productoId, Long unidadesVendidas) {
		
		this.productoId = productoId;
		this.unidadesVendidas = unidadesVendidas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productoId == null) ? 0 : productoId.hashCode());
		result = prime
				* result
				+ ((unidadesVendidas == null) ? 0 : unidadesVendidas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadesBlock other = (UnidadesBlock) obj;
		if (productoId == null) {
			if (other.productoId != null)
				return false;
		} else if (!productoId.equals(other.productoId))
			return false;
		if (unidadesVendidas == null) {
			if (other.unidadesVendidas != null)
				return false;
		} else if (!unidadesVendidas.equals(other.unidadesVendidas))
			return false;
		return true;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Long getUnidadesVendidas() {
		return unidadesVendidas;
	}

	public void setUnidadesVendidas(Long unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}
	
	
}
