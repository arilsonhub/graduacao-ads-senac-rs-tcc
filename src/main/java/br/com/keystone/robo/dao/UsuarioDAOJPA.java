package br.com.keystone.robo.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.repository.IUsuarioRepository;

public class UsuarioDAOJPA extends GenericDAO<Long, Usuario> implements IUsuarioRepository {
	
	@Inject
	public UsuarioDAOJPA(EntityManager entityManager) {
		super(entityManager);		
	}
	
	@Override
	public Usuario carregarUsuarioByLoginSenha(Usuario usuario) throws IntranetException {
		
		try{		
			StringBuilder query = new StringBuilder();
			query.append(" select usu ");
			query.append(" from Usuario usu ");
			query.append(" join fetch usu.perfil pef ");			
			query.append(" where usu.usu_usuario = :usuario and usu.usu_valido = 1 ");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());
			queryObject.setParameter("usuario", usuario.getUsu_usuario());
			queryObject.setMaxResults(1);
			Usuario usuarioLocalizado = (Usuario) queryObject.getSingleResult();
			
			if(usuarioLocalizado != null){				
				if(!usuarioLocalizado.getUsu_senha().equals(usuario.getUsu_senha())){
					throw new IntranetException("A senha informada é inválida");
				}				
			}
			
			return usuarioLocalizado;
			
		}catch(NoResultException nre){
			throw new IntranetException("Acesso negado");
		}
		catch(IntranetException ie){
			throw new IntranetException(ie.getMessage());
		}
		catch(Exception e){
			throw new IntranetException("Ocorreu uma falha ao acessar a base de dados");
		}
	}
	
	public Usuario carregarUsuarioById(Long usu_cod) throws IntranetException {
		try{		
			StringBuilder query = new StringBuilder();
			query.append(" select usu ");
			query.append(" from Usuario usu ");
			query.append(" join fetch usu.perfil pef ");			
			query.append(" where usu.usu_cod = :usu_cod and usu.usu_valido = 1 ");
			
			Query queryObject = super.getEntityManager().createQuery(query.toString());
			queryObject.setParameter("usu_cod", usu_cod);
			queryObject.setMaxResults(1);
			return (Usuario) queryObject.getSingleResult();			
		}catch(Exception e){
			throw new IntranetException("Falha ao buscar o usuário na base de dados");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Long> carregarPermissoesByUsuario(Usuario usuario) {
		StringBuilder query = new StringBuilder();
		query.append(" select peu.peu_fun_cod from permissaousuario peu where peu_usu_cod = :peu_usu_cod and peu_valido = 1 ");
		query.append(" UNION ");
		query.append(" select per.per_fun_cod from permissaoperfil per where per_pef_cod = :per_pef_cod and per_valido = 1 ");
		
		Query queryObject = super.getEntityManager().createNativeQuery(query.toString());
		queryObject.setParameter("peu_usu_cod", usuario.getUsu_cod());
		queryObject.setParameter("per_pef_cod", usuario.getPerfil().getPef_cod());
		
		List<Object> result = queryObject.getResultList();
		Set<Long> retorno = new HashSet<Long>();
		
		for(Object registro : result){		
			retorno.add(new Long(registro.toString()));
		}
		
		return retorno;
	}
}