package com.episteme.datasource.postprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * RDF post-processor for episteme
 * 
 * @author Toni Prada <toniprada@gmail.com>
 */
public class PostProcessor {

	public static final String ECOS = Config.ECOS;

	public static void main(String[] args) throws Exception {
		System.out.println("Processing rdfs: input folder='" + Config.FOLDER_INPUT
				+ "' , output folder='" + Config.FOLDER_OUTPUT + "'");
		// Bing translation API. Client id and secret:
		// http://msdn.microsoft.com/en-us/library/hh454950.aspx
		// http://blogs.msdn.com/b/translation/p/gettingstarted1.aspx
		Translate.setClientId(BingAccount.CLIENT_ID);
		Translate.setClientSecret(BingAccount.CLIENT_SECRET);
		// Create folder for output
		File dir = new File(Config.FOLDER_OUTPUT);
		dir.mkdir();
		// Read RDFs in folder
		File folder = new File(Config.FOLDER_INPUT);
		File[] listOfFiles = folder.listFiles();
		String filename;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				filename = listOfFiles[i].getName();
				if (filename.toLowerCase().endsWith(".rdf")) {
					processRdf(filename);
				}
			}
		}
	}

	private static void processRdf(String filename) {
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(Config.FOLDER_INPUT + filename);
		if (in == null) {
			throw new IllegalArgumentException("File: " + filename + " not found");
		}
		// Read the RDF/XML file
		model.read(in, null);
		// Find the statements to process
		ArrayList<Statement> statements = new ArrayList<>();
		StmtIterator iter = model.listStatements();
		while (iter.hasNext()) {
			Statement s = iter.nextStatement();
			for (int i = 0; i < Config.NAMESPACES_TO_TRANSLATE.length; i++) {
				if (s.getPredicate().getNameSpace()
						.contentEquals(Config.NAMESPACES_TO_TRANSLATE[i])) {
					for (int j = 0; j < Config.LOCALNAMES_TO_TRANSLATE.length; j++) {
						if (s.getPredicate().getLocalName()
								.contentEquals(Config.LOCALNAMES_TO_TRANSLATE[j])) {
							statements.add(s);
						}
					}
				}
			}
		}
		// Process them
		for (Statement s : statements) {
			processStatement(model, s);
		}
		System.out.println(filename + " processed");

		// Write the output
		try {
			FileWriter writer = new FileWriter(Config.FOLDER_OUTPUT + filename);
			model.write(writer);
		} catch (IOException e) {
			System.out.println("Error writing " + filename + " to " + Config.FOLDER_OUTPUT + ": "
					+ e.getMessage());
		}
		// model.write(System.out, "TURTLE");
	}

	private static void processStatement(Model model, Statement s) {
		Resource r = s.getSubject();
		Literal l = (Literal) s.getObject();
		Property p = model.getProperty(s.getPredicate().toString());
		Literal lEs = model.createLiteral(l.toString(), "es");
		// delete original
		r.removeAll(p);
		// add original with the spanish tag
		r.addLiteral(p, lEs);
		// and translate
		try {
			String textEn = Translate.execute(l.toString(), Language.SPANISH, Language.ENGLISH);
			// if (!textEn.contains("TranslateApiException")) {
			Literal lEn = model.createLiteral(textEn, "en");
			r.addLiteral(p, lEn);
			// }
		} catch (Exception e) {
			System.out.println("Error translating" + l);
		}
	}

}
