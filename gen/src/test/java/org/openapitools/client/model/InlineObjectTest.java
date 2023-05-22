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


package org.openapitools.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Model tests for InlineObject
 */
public class InlineObjectTest {
    private final InlineObject model = new InlineObject();

    /**
     * Model tests for InlineObject
     */
    @Test
    public void testInlineObject() {
        // TODO: test InlineObject
    }

    /**
     * Test the property 'studentId'
     */
    @Test
    public void studentIdTest() {
        // TODO: test studentId
    }

}
