# StudentApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createStudent**](StudentApi.md#createStudent) | **POST** /api/students | Create new student
[**deleteStudent**](StudentApi.md#deleteStudent) | **DELETE** /api/students/{id} | Delete a student by id
[**getAllStudents**](StudentApi.md#getAllStudents) | **GET** /api/students | Get all students
[**getStudent**](StudentApi.md#getStudent) | **GET** /api/students/{id} | Get a student by id


<a name="createStudent"></a>
# **createStudent**
> Student createStudent(createStudentRequest)

Create new student

Vytvorenie nového študenta. Po vytvorení je možné sa prihlásiť ako novo registrovaný študent. Služba je verejne dostupná bez prihlásenia. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StudentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StudentApi apiInstance = new StudentApi(defaultClient);
    CreateStudentRequest createStudentRequest = new CreateStudentRequest(); // CreateStudentRequest | 
    try {
      Student result = apiInstance.createStudent(createStudentRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StudentApi#createStudent");
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
 **createStudentRequest** | [**CreateStudentRequest**](CreateStudentRequest.md)|  |

### Return type

[**Student**](Student.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Objekt novo vytvoreného študenta. |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="deleteStudent"></a>
# **deleteStudent**
> Student deleteStudent(id)

Delete a student by id

Vymazanie študenta podľa identifikátora.  Vymazať študenta môže iba učiteľ (t.j. prihlásený je učiteľ) alebo prihlásený študent s rovnakým identifikátorom ako v dopyte. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StudentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    StudentApi apiInstance = new StudentApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Student result = apiInstance.deleteStudent(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StudentApi#deleteStudent");
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

[**Student**](Student.md)

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

<a name="getAllStudents"></a>
# **getAllStudents**
> List&lt;Student&gt; getAllStudents()

Get all students

Získanie zoznamu všetkých študentov.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StudentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    StudentApi apiInstance = new StudentApi(defaultClient);
    try {
      List<Student> result = apiInstance.getAllStudents();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StudentApi#getAllStudents");
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

[**List&lt;Student&gt;**](Student.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Zoznam všetkých študentov v systéme. Ak v systéme neexistujú žiadny študenti je vrátení prázdny zoznam. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="getStudent"></a>
# **getStudent**
> Student getStudent(id)

Get a student by id

Získanie študenta podľa identifikátora.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StudentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    StudentApi apiInstance = new StudentApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Student result = apiInstance.getStudent(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StudentApi#getStudent");
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

[**Student**](Student.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt študenta so zadaným identifikátorom. V prípade, že taký študent neexistuje tak je vrátená odpoveď 404. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

