package ca.ulaval.glo4002.cart.application;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtils {

	public static Marshaller createMarshaller() {
		try {
			return createContext().createMarshaller();
		} catch (JAXBException e) {
			throw new PersistenceException(e);
		}
	}

	public static Unmarshaller createUnmarshaller() {
		try {
			return createContext().createUnmarshaller();
		} catch (JAXBException e) {
			throw new PersistenceException(e);
		}
	}

	private static JAXBContext createContext() throws JAXBException {
		return JAXBContext.newInstance(CartList.class, Shop.class);
	}

	public static File createStorageFile(String baseName) {
		try {
			File file = File.createTempFile(baseName, ".tmp");
			Logger.getGlobal().info("Using temporary file : " + file.getAbsolutePath());
			return file;
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}
}
