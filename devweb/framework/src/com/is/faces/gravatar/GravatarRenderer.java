package com.is.faces.gravatar;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.primefaces.renderkit.CoreRenderer;

import com.is.faces.util.Strings;

@FacesRenderer(componentFamily = Gravatar.COMPONENT_FAMILY, rendererType = GravatarRenderer.RENDERER_TYPE)
public class GravatarRenderer extends CoreRenderer{
	public static final String RENDERER_TYPE = "com.is.faces.GravatarRenderer";
	
	private static final MessageDigest md;
	
	static{
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		this.encodeMarkup(context, (Gravatar) component);
	}
	
	private void encodeMarkup(FacesContext context,	Gravatar gravatar) throws IOException {

		ResponseWriter writer = context.getResponseWriter();


		writer.startElement("img", gravatar);
		writer.writeAttribute("id", gravatar.getClientId(), null);
		writer.writeAttribute("style", gravatar.getStyle(), null);
		
		String url;
		try {
			url = this.generateURL(gravatar);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		writer.writeAttribute("src",url, null);
		writer.endElement("img");

	}

	private String generateURL(Gravatar gravatar) throws NoSuchAlgorithmException {
		String url = "http://www.gravatar.com/";
		
		boolean qrCode = gravatar.isQrCode();
		Integer size = gravatar.getSize();
		String notFound = gravatar.getNotFound();
		
		if(!qrCode){
			url += "avatar/";
		}
		
		url += generateMailHash(gravatar);
		
		url += qrCode ? ".qr" : ".jpg";
		
		List<String> params = new ArrayList<String>();
		
		if(size != null){
			params.add("s=" + size);
		}
		
		if(Strings.isNotEmpty(notFound) && !notFound.equals("default") && Gravatar.NOT_FOUND_VALUES.contains(notFound)){
			params.add("d=" + notFound);
		}
		
		if(params.size() > 0){
			url += "?" + Strings.join(params, "&");
		}
		
		return url;
	}

	private String generateMailHash(Gravatar gravatar)	throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(String.valueOf(gravatar.getValue()).getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}


}
