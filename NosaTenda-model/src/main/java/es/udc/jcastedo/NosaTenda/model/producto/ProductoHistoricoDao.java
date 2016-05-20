package es.udc.jcastedo.NosaTenda.model.producto;

import java.util.List;

import es.udc.jcastedo.NosaTenda.model.productoService.ProductoBlock;
import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDao;

public interface ProductoHistoricoDao extends GenericDao<ProductoHistorico, Long> {

	public List<ProductoBlock> getFechasVentaByProducto(Long productoId);
	
	public List<ProductoHistorico> getHistoricos(Long productoId);
}
