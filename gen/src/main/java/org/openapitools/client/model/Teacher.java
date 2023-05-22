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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.client.model.Thesis;

/**
 * Objekt učiteľa.
 */
@ApiModel(description = "Objekt učiteľa.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-04-28T15:59:09.730931+02:00[Europe/Bratislava]")
public class Teacher {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_AIS_ID = "aisId";
  @SerializedName(SERIALIZED_NAME_AIS_ID)
  private Long aisId;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_INSTITUTE = "institute";
  @SerializedName(SERIALIZED_NAME_INSTITUTE)
  private String institute;

  public static final String SERIALIZED_NAME_DEPARTMENT = "department";
  @SerializedName(SERIALIZED_NAME_DEPARTMENT)
  private String department;

  public static final String SERIALIZED_NAME_THESES = "theses";
  @SerializedName(SERIALIZED_NAME_THESES)
  private List<Thesis> theses = null;


  public Teacher id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Id učiteľa. V prípade ak je použité AIS ID ako identifikátor učiteľa tak je táto hodnota rovnaká ako v atribúte aisId.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Id učiteľa. V prípade ak je použité AIS ID ako identifikátor učiteľa tak je táto hodnota rovnaká ako v atribúte aisId.")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public Teacher aisId(Long aisId) {
    
    this.aisId = aisId;
    return this;
  }

   /**
   * AIS ID učiteľa. Zadané pri vytvorení učiteľa. Musí byť unikátne v rámci všetkých učiteľov.
   * @return aisId
  **/
  @ApiModelProperty(required = true, value = "AIS ID učiteľa. Zadané pri vytvorení učiteľa. Musí byť unikátne v rámci všetkých učiteľov.")

  public Long getAisId() {
    return aisId;
  }


  public void setAisId(Long aisId) {
    this.aisId = aisId;
  }


  public Teacher name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Teacher email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public Teacher institute(String institute) {
    
    this.institute = institute;
    return this;
  }

   /**
   * Get institute
   * @return institute
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getInstitute() {
    return institute;
  }


  public void setInstitute(String institute) {
    this.institute = institute;
  }


  public Teacher department(String department) {
    
    this.department = department;
    return this;
  }

   /**
   * Get department
   * @return department
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDepartment() {
    return department;
  }


  public void setDepartment(String department) {
    this.department = department;
  }


  public Teacher theses(List<Thesis> theses) {
    
    this.theses = theses;
    return this;
  }

  public Teacher addThesesItem(Thesis thesesItem) {
    if (this.theses == null) {
      this.theses = new ArrayList<Thesis>();
    }
    this.theses.add(thesesItem);
    return this;
  }

   /**
   * Get theses
   * @return theses
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Thesis> getTheses() {
    return theses;
  }


  public void setTheses(List<Thesis> theses) {
    this.theses = theses;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Teacher teacher = (Teacher) o;
    return Objects.equals(this.id, teacher.id) &&
        Objects.equals(this.aisId, teacher.aisId) &&
        Objects.equals(this.name, teacher.name) &&
        Objects.equals(this.email, teacher.email) &&
        Objects.equals(this.institute, teacher.institute) &&
        Objects.equals(this.department, teacher.department) &&
        Objects.equals(this.theses, teacher.theses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, aisId, name, email, institute, department, theses);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Teacher {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    aisId: ").append(toIndentedString(aisId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    institute: ").append(toIndentedString(institute)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    theses: ").append(toIndentedString(theses)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
