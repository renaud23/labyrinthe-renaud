package fr.insee.solr.service.impl;

public class SolrInseeException extends Exception{

	public SolrInseeException() {
		super();
	}

	public SolrInseeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SolrInseeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SolrInseeException(String message) {
		super(message);
	}

	public SolrInseeException(Throwable cause) {
		super(cause);
	}

}
