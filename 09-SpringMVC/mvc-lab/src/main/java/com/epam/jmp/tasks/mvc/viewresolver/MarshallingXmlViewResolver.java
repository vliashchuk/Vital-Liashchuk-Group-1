package com.epam.jmp.tasks.mvc.viewresolver;

import java.util.Locale;




import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

public class MarshallingXmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

	public MarshallingXmlViewResolver(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * Get the view to use.
	 * 
	 * @return Always returns an instance of {@link MappingJacksonJsonView}.
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		MarshallingView view = new MarshallingView();
		view.setMarshaller(marshaller);
		return view;
	}
}
