package br.com.keystone.robo.vo;

import java.io.Serializable;

import java.util.List;

public class PaginatorVO<T> implements Serializable {
	
	private static final long serialVersionUID = -4056183374921868492L;

	private Integer page;
	
	private Integer pages;
	
	private List<T> lista;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}
}