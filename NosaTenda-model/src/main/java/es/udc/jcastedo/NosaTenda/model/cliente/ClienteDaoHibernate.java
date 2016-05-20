package es.udc.jcastedo.NosaTenda.model.cliente;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import es.udc.jcastedo.NosaTenda.modelutil.dao.GenericDaoHibernate;
import es.udc.jcastedo.NosaTenda.modelutil.exceptions.InstanceNotFoundException;

@Repository("clienteDao")
public class ClienteDaoHibernate extends GenericDaoHibernate<Cliente, Long>
								implements ClienteDao {

	public Cliente find(String correo, String password) throws InstanceNotFoundException {
		
		try {
			
			Cliente cliente = (Cliente) getSession().createQuery("SELECT c FROM Cliente c " +
											"WHERE (c.correo LIKE :correo) " +
											"AND (c.password LIKE :password)")
											.setParameter("correo", correo)
											.setParameter("password", password)
											.uniqueResult();
			
			if (cliente == null) {
				throw new InstanceNotFoundException(correo, Cliente.class.getName());
			} else {
				return cliente;
			}
			
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}
	
	public Cliente findByCorreo(String correo) throws InstanceNotFoundException {
		
		try {
			
			Cliente cliente = (Cliente) getSession().createQuery("SELECT c FROM Cliente c " +
												"WHERE c.correo  = :correo")
												.setParameter("correo", correo)
												.uniqueResult();
			
			if (cliente == null) {
				throw new InstanceNotFoundException(cliente, ClienteDaoHibernate.class.getName());
			} else {
				return cliente;
			}
		} catch (HibernateException e) {
			throw convertHibernateAccessException(e);
		}
	}


	
}
