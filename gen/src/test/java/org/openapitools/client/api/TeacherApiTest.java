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
import org.openapitools.client.model.CreateTeacherRequest;
import org.openapitools.client.model.Message;
import org.openapitools.client.model.Teacher;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for TeacherApi
 */
@Ignore
public class TeacherApiTest {

    private final TeacherApi api = new TeacherApi();

    
    /**
     * Create new teacher
     *
     * Vytvorenie nového učiteľa. Po vytvorení je možné sa prihlásiť ako novo registrovaný učiteľ. Služba je verejne dostupná bez prihlásenia. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTeacherTest() throws ApiException {
        CreateTeacherRequest createTeacherRequest = null;
        Teacher response = api.createTeacher(createTeacherRequest);

        // TODO: test validations
    }
    
    /**
     * Delete a teacher by id
     *
     * Vymazanie učiteľa podľa identifikátora. Vymazať učiteľa môže iba prihlásený učiteľ, ktorého identifikátor sa zhoduje s poskytnutým identifikátorom v dopyte. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTeacherTest() throws ApiException {
        Long id = null;
        Teacher response = api.deleteTeacher(id);

        // TODO: test validations
    }
    
    /**
     * Get all teachers
     *
     * Získanie zoznamu všetkých učiteľov.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getAllTeachersTest() throws ApiException {
        List<Teacher> response = api.getAllTeachers();

        // TODO: test validations
    }
    
    /**
     * Get a teacher by id
     *
     * Získanie učiteľa podľa identifikátora.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTeacherTest() throws ApiException {
        Long id = null;
        Teacher response = api.getTeacher(id);

        // TODO: test validations
    }
    
}
