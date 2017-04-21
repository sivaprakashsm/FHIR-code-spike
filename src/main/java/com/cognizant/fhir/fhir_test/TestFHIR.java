package com.cognizant.fhir.fhir_test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.ServerValidationModeEnum;

/**
 * Hello world!
 *
 */
public class TestFHIR 
{
    public static void main( String[] args )
    {
    	FhirContext ctx = FhirContext.forDstu2();
    	ctx.getRestfulClientFactory().setServerValidationMode(ServerValidationModeEnum.NEVER);
    	ctx.getRestfulClientFactory().setProxy("proxy.cognizant.com", 6050);
    	String serverBase = "http://fhirtest.uhn.ca/baseDstu2";
    	IGenericClient client = ctx.newRestfulGenericClient(serverBase);
    	TestFHIR testFHIR = new TestFHIR();
    	
    	testFHIR.getPatientDetails(client, "27944");
    }
    
    public Patient getPatientDetails(IGenericClient restClient, String patientId){
    	Patient patient = restClient.read(Patient.class, patientId);
    	System.out.println("====== patient details ======");
    	System.out.println("name == "+patient.getName().get(0).getNameAsSingleString());
    	System.out.println("family name == "+patient.getName().get(0).getFamilyAsSingleString());
    	System.out.println("given name == "+patient.getName().get(0).getGivenAsSingleString());
    	System.out.println("dob == "+patient.getBirthDate());
    	System.out.println("gender == "+patient.getGender());
    	return patient;
    }
}
