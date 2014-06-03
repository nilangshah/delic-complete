package com.service;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import orchestrate.Orchestrator;

import jsonWrapper.JSONConverter;

import annotate.Annotator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;

@Path("/delic")
public class WebService {
	// This method is called if TEXT_PLAIN is request
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String sayPlainTextHello() {
	 * return "Hello Jersey"; }
	 */
	@Context
	ServletContext context;
	@POST
	@Consumes("text/plain")
	@Produces("aplication/json")
	public String post(String message) throws Exception {
		System.out.println(message);
		return delic(message);
	}

	public  String delic(String licenseText ) throws Exception {
		// String fileName = "lic";
		String conceptDirectory = "data/concepts";
		Document licenseDoc = new Document(licenseText);
		System.out.println("Text : " + licenseDoc.getDocText());
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		File conceptDir = new File(context.getRealPath(conceptDirectory));
		for(File conceptFile : conceptDir.listFiles()) {
			concepts.add(new Concept(conceptFile.getAbsolutePath()));
		}
		Annotator annon = new Annotator(concepts);
		ArrayList<AnnotatedSentence> annotatedSentences = annon.annotateDoc(licenseDoc);
		String jsonData = JSONConverter.getJSON(annotatedSentences);
		System.out.println(jsonData);
		return jsonData;
	}

	/*
	 * // This method is called if XML is request
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_XML) public String sayXMLHello() { return
	 * "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>"; }
	 * 
	 * // This method is called if HTML is request
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_HTML) public String sayHtmlHello() { return
	 * "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" +
	 * "Hello Jersey" + "</body></h1>" + "</html> "; }
	 */
	
}
