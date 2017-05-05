package com.mm.web.bbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import com.mm.model.domain.Entity;
import com.mm.module.one.IEntityService;

/**
 * 
 * Entity Backed Bean
 * 
 * @author RS
 * @version 1.0.0
 *
 */

@Named("entityBBean")
@Scope("session")
public class EntityBBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IEntityService entityService;
	
	private int id;
	private String attribute;
	private List<Entity> entityList;
	
	
	@PostConstruct
	public  void init(){
		entityList = new ArrayList<Entity>();
		entityList.addAll(getEntityService().getEntitys());
	}
	public void addEntity() {
		Entity entity = new Entity();
		try {
			entity.setId(getId());
			entity.setAttribute(getAttribute());
			getEntityService().addEntity(entity);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));  
			entityList.add(entity);
		} catch (DataAccessException e) {
			entityList.remove(entity);
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	public void deleteAction(Entity value){
		getEntityService().deleteEntity(value);
		entityList.clear();
		entityList.addAll(getEntityService().getEntitys());
	}
	

	public void reset() {
		this.setId(0);
		this.setAttribute("");
	}

	public List<Entity> getEntityList() {
		
		return entityList;
	}

	public IEntityService getEntityService() {
		return entityService;
	}

	public void setEntityService(IEntityService entityService) {
		this.entityService = entityService;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public void onRowEdit(RowEditEvent event){
		System.out.println("<---------start edit-------->");
		Entity updateEntity=(((Entity) event.getObject()));
		System.out.println("<---------updateEntity-------->"+updateEntity.getAttribute());
		getEntityService().updateEntity(updateEntity);
	}
	
 }