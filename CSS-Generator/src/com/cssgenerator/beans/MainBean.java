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
import javax.faces.context.FacesContext;
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

	private CssStyle currentStyle = new CssStyle();
	private List<CssStyle> listOfCssStyles;
	private static final CssStyleDAO dao = new CssStyleDAO();
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");

	public String getMessage() {
		return "Hello World!";
	}

	public void resetCSS() {
		currentStyle = new CssStyle();
	}
	/*@PostConstruct
	public void init() {
	EntityManager entitymanager = emf.createEntityManager();
	listOfCssStyles = dao.loadAllStyles(entitymanager);
	}*/
	
	public String save() {
		EntityManager entitymanager = emf.createEntityManager();
		EntityTransaction transaction = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String css = null;
		
		if (request.getParameter("border-result-area") != null){
			css = request.getParameter("border-result-area");
			getCurrentStyle().setType("border-radius");
		}else if (request.getParameter("boxShadow-result-area") != null) {
			css = request.getParameter("border-result-area");
			getCurrentStyle().setType("border-radius");
		}else if (request.getParameter("boxShadow-result-area") != null) {
			css = request.getParameter("border-result-area");
			getCurrentStyle().setType("border-radius");
		}else if (request.getParameter("boxShadow-result-area") != null) {
			css = request.getParameter("border-result-area");
			getCurrentStyle().setType("border-radius");
		}

		
			getCurrentStyle().setCss(css);

			try {
				transaction = entitymanager.getTransaction();
				transaction.begin();
				dao.save(getCurrentStyle(), entitymanager);
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
				if (entitymanager.isOpen()) {
					entitymanager.close();
				}
				currentStyle = new CssStyle();

			}
		return null;
	}

	public void load() {

		EntityManager entitymanager = emf.createEntityManager();
		try {
			setListOfCssStyles(dao.loadAllStyles(entitymanager));
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entitymanager.close();
		}
	}
	
	public void loadByType(String type) {

		EntityManager entitymanager = emf.createEntityManager();
		try {
			setListOfCssStyles(dao.loadStyleByType(entitymanager, type));
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entitymanager.close();
		}
	}

	public void addFacesContextMessage(FacesMessage message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	public CssStyle getCurrentStyle() {
		return currentStyle;
	}

	public void setCurrentStyle(CssStyle currentStyle) {
		this.currentStyle = currentStyle;
	}

	public List<CssStyle> getListOfCssStyles() {
		return listOfCssStyles;
	}

	public void setListOfCssStyles(List<CssStyle> listOfCssStyles) {
		this.listOfCssStyles = listOfCssStyles;
	}


}
