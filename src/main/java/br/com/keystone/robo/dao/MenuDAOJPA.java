package br.com.keystone.robo.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.keystone.robo.core.exceptions.IntranetException;
import br.com.keystone.robo.generic.GenericDAO;
import br.com.keystone.robo.model.Menu;
import br.com.keystone.robo.model.MenuCategoria;
import br.com.keystone.robo.model.Usuario;
import br.com.keystone.robo.repository.IMenuRepository;

public class MenuDAOJPA extends GenericDAO<Long, Menu> implements IMenuRepository {

	@Inject
	public MenuDAOJPA(EntityManager entityManager) {
		super(entityManager);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Long, HashMap<Long, Menu>> carregarMenuByCategoria(MenuCategoria menuCategoria) throws IntranetException {
	
		try{
				StringBuilder sql = new StringBuilder();
				sql.append(" select m.mem_cod,m.mem_action,m.mem_descricao,m.mem_img,m.mem_mct_cod,m.mem_valido, ");		
				sql.append(" (CASE WHEN m.mem_mem_cod is not null THEN m.mem_mem_cod ELSE 0 END) as mem_pai ");
				sql.append(" from menu m ");
				sql.append(" where m.mem_mct_cod = :mct_cod and m.mem_valido = 1  ");
				sql.append(" order by mem_pai ");		
				Query query = super.getEntityManager().createNativeQuery(sql.toString());
				query.setParameter("mct_cod", menuCategoria.getMct_cod());
				List<Object[]> result = query.getResultList();
				
				HashMap<Long, HashMap<Long, Menu>> retorno = new HashMap<Long, HashMap<Long,Menu>>();
				HashMap<Long,Menu> menuRegistro = new HashMap<Long, Menu>();
				Long idPaiAtual = 0L;
				
				for(Object[] registro : result){
					
					Long idPaiRecebido = new Long(registro[6].toString());
					
					Menu menu = new Menu();
					menu.setMem_cod(new Long(registro[0].toString()));
					menu.setMem_action((String)registro[1]);
					menu.setMem_descricao((String)registro[2]);
					menu.setMem_img((String)registro[3]);
					
					MenuCategoria menuCat = new MenuCategoria();
					menuCat.setMct_cod(new Long(registro[4].toString()));
					menu.setMenucategoria(menuCat);
					
					menu.setMem_valido(Integer.parseInt(registro[5].toString()));
					
					Menu menuPai = new Menu();
					menuPai.setMem_cod(new Long(registro[6].toString()));
					
					menu.setMenuParent(menuPai);			
					
					if(!idPaiRecebido.equals(idPaiAtual)){
						retorno.put(idPaiAtual, menuRegistro);
						idPaiAtual = idPaiRecebido;
						menuRegistro = new HashMap<Long, Menu>();
					}
					
					menuRegistro.put(menu.getMem_cod(), menu);
				}
				
				retorno.put(idPaiAtual, menuRegistro);
				return retorno;
		
		}catch(Exception e){
			throw new IntranetException("Ocorreu uma falha ao criar a sessão, tente novamente");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Long> carregarMenuCodByUsuario(Usuario usuario) {
		
		StringBuilder query = new StringBuilder();
		query.append(" select distinct mef_mem_cod from menufuncionalidade where mef_fun_cod in ( ");
		query.append(" select peu.peu_fun_cod from permissaousuario peu where peu_usu_cod = :peu_usu_cod and peu_valido = 1 ");
		query.append(" UNION ");
		query.append(" select per.per_fun_cod from permissaoperfil per where per_pef_cod = :per_pef_cod and per_valido = 1 ");
		query.append(" ) and mef_valido = 1 ");
		
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