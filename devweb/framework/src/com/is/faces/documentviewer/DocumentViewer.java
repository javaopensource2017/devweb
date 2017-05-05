package com.is.faces.documentviewer;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIGraphic;

@FacesComponent(value = DocumentViewer.COMPONENT_TYPE)
public class DocumentViewer extends UIGraphic {

	public static final String COMPONENT_TYPE = "com.is.faces.DocumentViewer";
	public static final String COMPONENT_FAMILY = "com.is.faces.components";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public Integer getWidth() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.width, null);
	}

	public void setWidth(Integer width) {
		this.getStateHelper().put(PropertyKeys.width, width);
	}

	public Integer getHeight() {
		return (Integer) this.getStateHelper().eval(PropertyKeys.height, null);
	}

	public void setHeight(Integer width) {
		this.getStateHelper().put(PropertyKeys.height, width);
	}

	public String getStyle() {
		return (String) this.getStateHelper().eval(PropertyKeys.style, null);
	}

	public void setStyle(String style) {
		this.getStateHelper().put(PropertyKeys.style, style);
	}

	public String getName() {
		return (String) getStateHelper().eval(PropertyKeys.name, null);
	}

	public void setName(String _name) {
		getStateHelper().put(PropertyKeys.name, _name);
	}

	public String getLibrary() {
		return (String) getStateHelper().eval(PropertyKeys.library, null);
	}

	public void setLibrary(String _library) {
		getStateHelper().put(PropertyKeys.library, _library);
	}

	public boolean isCache() {
		return (Boolean) getStateHelper().eval(PropertyKeys.cache, false);
	}

	public void setCache(boolean _cache) {
		getStateHelper().put(PropertyKeys.cache, _cache);
	}
	
	public boolean isUnmapped() {
		return (Boolean) getStateHelper().eval(PropertyKeys.unmapped, false);
	}

	public void setUnmapped(boolean unmapped) {
		getStateHelper().put(PropertyKeys.unmapped, unmapped);
	}

	public Integer getPage(){
		return (Integer) getStateHelper().eval(PropertyKeys.page);
	}
	
	public void setPage(Integer page){
		this.getStateHelper().put(PropertyKeys.page, page);
	}
	
	public java.lang.Object getLocale() {
		return (java.lang.Object) getStateHelper().eval(PropertyKeys.locale, null);
	}
	public void setLocale(java.lang.Object _locale) {
		getStateHelper().put(PropertyKeys.locale, _locale);
	}
	
	protected static enum PropertyKeys {
		width, 
		height, 
		style,
		name,
		library,
		cache,
		unmapped,
		page,
		locale;
	}

}
