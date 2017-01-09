package com.cssgenerator.beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.cssgenerator.dao.CssStyleDAO;
import com.cssgenerator.entities.CssStyle;
import com.sun.corba.se.spi.orbutil.fsm.Action;

@ManagedBean(name = "controller")
@ViewScoped
public class MainBean implements Serializable {

	private static final long serialVersionUID = -7182134510999986302L;
	
	private Long selectedId;
	private CssStyle currentStyle = new CssStyle(); 
	private List<CssStyle> listOfCssStyles;
	private static final CssStyleDAO dao = new CssStyleDAO();
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");
	private String currentType;
	private String preview;
	
	public String getMessage() {
		return "Hello World!";
	}

	public String resetCSS() {
		currentStyle = new CssStyle();
		return null;
	}
	/*
	 * @PostConstruct public void init() { EntityManager entitymanager =
	 * emf.createEntityManager(); listOfCssStyles =
	 * dao.loadAllStyles(entitymanager); }
	 */
	
	public void changeSelectedStyle (ValueChangeEvent e){
		EntityManager entityManager = emf.createEntityManager();
		CssStyle temp;
		Long id = (Long) e.getNewValue();
		temp = dao.findById(entityManager, id);
		preview = temp.getCss();
	}
	
	
	public void delete (){
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			dao.delete(entityManager, selectedId);
			transaction.commit();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Инфо:", "Успешно изтриване.");
			addFacesContextMessage(message);
		} catch (PersistenceException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Инфо:", e.getMessage());
			addFacesContextMessage(message);
		}finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
			loadByType(currentType);
			currentStyle = new CssStyle();
			preview = null;
			
		}
	}
	
	public String save() {
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		if (!request.getParameter("border-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("border-result-area"));
			getCurrentStyle().setType("border-radius");
		} else if (!request.getParameter("boxShadow-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("boxShadow-result-area"));
			getCurrentStyle().setType("box-shadow");
		} else if (!request.getParameter("text-shadow-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("text-shadow-result-area"));
			getCurrentStyle().setType("text-shadow");
		} else if (!request.getParameter("rgba-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("rgba-result-area"));
			getCurrentStyle().setType("rgba");
		} else if (!request.getParameter("multiple-columns-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("multiple-columns-result-area"));
			getCurrentStyle().setType("multiple-columns");
		} else if (!request.getParameter("box-resize-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("box-resize-result-area"));
			getCurrentStyle().setType("box-resize");
		} else if (!request.getParameter("outline-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("outline-result-area"));
			getCurrentStyle().setType("outline");
		} else if (!request.getParameter("transition-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("transition-result-area"));
			getCurrentStyle().setType("transition");
		} else if (!request.getParameter("transform-result-area").isEmpty()) {
			getCurrentStyle().setCss(request.getParameter("transform-result-area"));
			getCurrentStyle().setType("transform");
		}							
		
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			dao.save(getCurrentStyle(), entityManager);
			transaction.commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Инфо:", "Успешeн запис.");
			addFacesContextMessage(message);
		} catch (IllegalArgumentException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Инфо:", e.getMessage());
			addFacesContextMessage(message);
		} catch (PersistenceException e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Инфо:", "Неуспешен запис.");
			addFacesContextMessage(message);
			e.printStackTrace();

		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
			loadByType(currentType);
			currentStyle = new CssStyle();
		}
		
		return null;
	}

	public void loadByType(String type) {
		EntityManager entityManager = emf.createEntityManager();
		currentType = type;
		try {
			setListOfCssStyles(dao.loadStyleByType(entityManager, type));
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void addFacesContextMessage(FacesMessage message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	public List<CssStyle> getListOfCssStyles() {
		return listOfCssStyles;
	}

	public void setListOfCssStyles(List<CssStyle> listOfCssStyles) {
		this.listOfCssStyles = listOfCssStyles;
	}


	public CssStyle getCurrentStyle() {
		return currentStyle;
	}

	public void setCurrentStyle(CssStyle currentStyle) {
		this.currentStyle = currentStyle;
	}

	public String getCurrentType() {
		return currentType;
	}

	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

}
