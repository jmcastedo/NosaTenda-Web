package es.udc.jcastedo.NosaTenda.model.compra;

import java.util.Calendar;
import java.util.List;

import es.udc.jcastedo.NosaTenda.model.productoService.UnidadesBlock;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface CompraDao extends GenericDao<Compra, Long> {

	public List<Compra> getComprasByClienteId(Long clienteId);
	
	public List<Compra> getCompras();
	
	public List<Compra> getComprasByTiendaId(Long tiendaId);
	
	public List<UnidadesBlock> getVentas();
	
	public Long getVentasByProductoId(Long productoId);
	
	public Long getVentasByProductoIdByRango(Long productoId, Calendar fecha_inicio, Calendar fecha_fin);
	
	public List<UnidadesBlock> getVentasByTiendaIdDesglose(Long tiendaId);
	
	public Long getVentasByTiendaId(Long tiendaId);
	
	public List<UnidadesBlock> getVentasByCategoriaIdDesglose(Long categoriaId);
	
	public Long getVentasByCategoriaId(Long categoriaId);
	
	public List<UnidadesBlock> getVentasByCategoriaIdByTiendaIdDesglose(Long categoriaId, Long tiendaId);
	
	public Long getVentasByCategoriaIdByTiendaId(Long categoriaId, Long tiendaId);
}
