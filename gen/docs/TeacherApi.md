# TeacherApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createTeacher**](TeacherApi.md#createTeacher) | **POST** /api/teachers | Create new teacher
[**deleteTeacher**](TeacherApi.md#deleteTeacher) | **DELETE** /api/teachers/{id} | Delete a teacher by id
[**getAllTeachers**](TeacherApi.md#getAllTeachers) | **GET** /api/teachers | Get all teachers
[**getTeacher**](TeacherApi.md#getTeacher) | **GET** /api/teachers/{id} | Get a teacher by id


<a name="createTeacher"></a>
# **createTeacher**
> Teacher createTeacher(createTeacherRequest)

Create new teacher

Vytvorenie nového učiteľa. Po vytvorení je možné sa prihlásiť ako novo registrovaný učiteľ. Služba je verejne dostupná bez prihlásenia. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TeacherApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TeacherApi apiInstance = new TeacherApi(defaultClient);
    CreateTeacherRequest createTeacherRequest = new CreateTeacherRequest(); // CreateTeacherRequest | 
    try {
      Teacher result = apiInstance.createTeacher(createTeacherRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TeacherApi#createTeacher");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createTeacherRequest** | [**CreateTeacherRequest**](CreateTeacherRequest.md)|  |

### Return type

[**Teacher**](Teacher.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Objekt novo vytvoreného učiteľa. |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="deleteTeacher"></a>
# **deleteTeacher**
> Teacher deleteTeacher(id)

Delete a teacher by id

Vymazanie učiteľa podľa identifikátora. Vymazať učiteľa môže iba prihlásený učiteľ, ktorého identifikátor sa zhoduje s poskytnutým identifikátorom v dopyte. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TeacherApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    TeacherApi apiInstance = new TeacherApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Teacher result = apiInstance.deleteTeacher(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TeacherApi#deleteTeacher");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**Teacher**](Teacher.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt študenta ktorý bol vymazaný. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**403** | Authorized user doesn&#39;t have permission for the request. Autentifikovaný používateľ nemá oprávnenie na dopyt. Kód v odpovedi je vždy 403.  |  -  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="getAllTeachers"></a>
# **getAllTeachers**
> List&lt;Teacher&gt; getAllTeachers()

Get all teachers

Získanie zoznamu všetkých učiteľov.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TeacherApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    TeacherApi apiInstance = new TeacherApi(defaultClient);
    try {
      List<Teacher> result = apiInstance.getAllTeachers();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TeacherApi#getAllTeachers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Teacher&gt;**](Teacher.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Zoznam všetkých učiteľov v systéme. Ak v systéme neexistujú žiadny učitelia je vrátený prázdny zoznam. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="getTeacher"></a>
# **getTeacher**
> Teacher getTeacher(id)

Get a teacher by id

Získanie učiteľa podľa identifikátora.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TeacherApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    TeacherApi apiInstance = new TeacherApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Teacher result = apiInstance.getTeacher(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TeacherApi#getTeacher");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**Teacher**](Teacher.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt učiteľa so zadaným identifikátorom. V prípade, že taký študent neexistuje tak je vrátená odpoveď 404. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

