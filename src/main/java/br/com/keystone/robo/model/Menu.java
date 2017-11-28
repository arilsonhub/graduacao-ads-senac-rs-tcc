package br.com.keystone.robo.model;

import java.io.Serializable;

public class Menu implements Serializable {
	
	private static final long serialVersionUID = -6517278077807269957L;
	
	private Long mem_cod;
	
	private String mem_action;
	
	private String mem_descricao;
	
	private String mem_img;
	
	private MenuCategoria menucategoria;
	
	private Integer mem_valido;
	
    private Menu menuParent;
    
	public Long getMem_cod() {
		return mem_cod;
	}

	public void setMem_cod(Long mem_cod) {
		this.mem_cod = mem_cod;
	}

	public String getMem_action() {
		return mem_action;
	}

	public void setMem_action(String mem_action) {
		this.mem_action = mem_action;
	}

	public String getMem_descricao() {
		return mem_descricao;
	}

	public void setMem_descricao(String mem_descricao) {
		this.mem_descricao = mem_descricao;
	}

	public String getMem_img() {
		return mem_img;
	}

	public void setMem_img(String mem_img) {
		this.mem_img = mem_img;
	}

	public MenuCategoria getMenucategoria() {
		return menucategoria;
	}

	public void setMenucategoria(MenuCategoria menucategoria) {
		this.menucategoria = menucategoria;
	}

	public Integer getMem_valido() {
		return mem_valido;
	}

	public void setMem_valido(Integer mem_valido) {
		this.mem_valido = mem_valido;
	}
	
	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_cod == null) ? 0 : mem_cod.hashCode());
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
		Menu other = (Menu) obj;
		if (mem_cod == null) {
			if (other.mem_cod != null)
				return false;
		} else if (!mem_cod.equals(other.mem_cod))
			return false;
		return true;
	}
}