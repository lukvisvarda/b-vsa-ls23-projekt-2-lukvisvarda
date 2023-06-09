/*
 * B-VSA Semestrálny Projekt 2
 * Špecifikácia REST webových služieb slúžia pre detailný opis požadovanej funkcionality pre Semestrálny Projekt 2  v rámci predmetu B-VSA akademický rok 2022/2023.  Pre dopyty na väčšinu služieb (okrem createStudent a createTeacher) je potrebné byť prihlásený. Služby pracujú s Basic autentifikáciou. Každý študent a učiteľ je používateľ systému, ktorý sa môže prihlásiť. Niektoré služby vyžadujú autorizáciu/oprávnenie pre ich dopyt. Služba, ktorá je obmedzená oprávneniami, to má uvedené v popise služby.  Ak služba má vrátiť jeden objekt a nie je nájdený namiesto hodnoty null vráti odpoveď 404. V prípade nečakanej chyby má byť odpoveď s kódom 500 a v správe dôvod vzniku chyby (správa zo vzniknutej Exception). 
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.CreateStudentRequest;
import org.openapitools.client.model.Message;
import org.openapitools.client.model.Student;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for StudentApi
 */
@Ignore
public class StudentApiTest {

    private final StudentApi api = new StudentApi();

    
    /**
     * Create new student
     *
     * Vytvorenie nového študenta. Po vytvorení je možné sa prihlásiť ako novo registrovaný študent. Služba je verejne dostupná bez prihlásenia. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createStudentTest() throws ApiException {
        CreateStudentRequest createStudentRequest = null;
        Student response = api.createStudent(createStudentRequest);

        // TODO: test validations
    }
    
    /**
     * Delete a student by id
     *
     * Vymazanie študenta podľa identifikátora.  Vymazať študenta môže iba učiteľ (t.j. prihlásený je učiteľ) alebo prihlásený študent s rovnakým identifikátorom ako v dopyte. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteStudentTest() throws ApiException {
        Long id = null;
        Student response = api.deleteStudent(id);

        // TODO: test validations
    }
    
    /**
     * Get all students
     *
     * Získanie zoznamu všetkých študentov.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAllStudentsTest() throws ApiException {
        List<Student> response = api.getAllStudents();

        // TODO: test validations
    }
    
    /**
     * Get a student by id
     *
     * Získanie študenta podľa identifikátora.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getStudentTest() throws ApiException {
        Long id = null;
        Student response = api.getStudent(id);

        // TODO: test validations
    }
    
}
