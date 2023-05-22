# ThesisApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**assignThesis**](ThesisApi.md#assignThesis) | **POST** /api/theses/{id}/assign | Assign a thesis by id to a student
[**createThesis**](ThesisApi.md#createThesis) | **POST** /api/theses | Create new thesis
[**deleteThesis**](ThesisApi.md#deleteThesis) | **DELETE** /api/theses/{id} | Delete a thesis by id
[**getAllTheses**](ThesisApi.md#getAllTheses) | **GET** /api/theses | Get all theses
[**getThesis**](ThesisApi.md#getThesis) | **GET** /api/theses/{id} | Get a thesis by id
[**searchTheses**](ThesisApi.md#searchTheses) | **POST** /api/search/theses | Search theses by a teacher or a student
[**submitThesis**](ThesisApi.md#submitThesis) | **POST** /api/theses/{id}/submit | Submit a thesis by id assigned to a student


<a name="assignThesis"></a>
# **assignThesis**
> Thesis assignThesis(id, inlineObject)

Assign a thesis by id to a student

Priradenie záverečnej práce podľa identifikátora študentovi. Ak je prihlásený študent v dopyte je práca priradená jemu. Ak je prihlásený učiteľ je práca priradená študentovi podľa identifikátora v objekte tele dopytu. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    Long id = 56L; // Long | 
    InlineObject inlineObject = new InlineObject(); // InlineObject | 
    try {
      Thesis result = apiInstance.assignThesis(id, inlineObject);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#assignThesis");
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
 **inlineObject** | [**InlineObject**](InlineObject.md)|  | [optional]

### Return type

[**Thesis**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt záverečnej práce, ktorá bola priradená študentovi. Vrátený objekt má obsahovať aj objekt študenta priradeného k prácu. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**403** | Authorized user doesn&#39;t have permission for the request. Autentifikovaný používateľ nemá oprávnenie na dopyt. Kód v odpovedi je vždy 403.  |  -  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="createThesis"></a>
# **createThesis**
> CreateThesisRequest createThesis(thesis)

Create new thesis

Vytvorenie záverečnej práce. Vytvoriť záverečnú prácu môže iba učiteľ.  Vytvorená záverečná práca je vytvorená pod prihláseným učiteľom. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    Thesis thesis = new Thesis(); // Thesis | 
    try {
      CreateThesisRequest result = apiInstance.createThesis(thesis);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#createThesis");
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
 **thesis** | [**Thesis**](Thesis.md)|  |

### Return type

[**CreateThesisRequest**](CreateThesisRequest.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Objekt novo vytvorenej záverečnej práce. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**403** | Authorized user doesn&#39;t have permission for the request. Autentifikovaný používateľ nemá oprávnenie na dopyt. Kód v odpovedi je vždy 403.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="deleteThesis"></a>
# **deleteThesis**
> Thesis deleteThesis(id)

Delete a thesis by id

Vymazanie záverečnej práce podľa identifikátora. Vymazať prácu môže iba prihlásený učiteľ, ktorý prácu vypísal. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Thesis result = apiInstance.deleteThesis(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#deleteThesis");
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

[**Thesis**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt záverečnej práce, ktorý bola vymazaná. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**403** | Authorized user doesn&#39;t have permission for the request. Autentifikovaný používateľ nemá oprávnenie na dopyt. Kód v odpovedi je vždy 403.  |  -  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="getAllTheses"></a>
# **getAllTheses**
> List&lt;Thesis&gt; getAllTheses()

Get all theses

Získanie zoznamu záverečných prác.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    try {
      List<Thesis> result = apiInstance.getAllTheses();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#getAllTheses");
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

[**List&lt;Thesis&gt;**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Zoznam všetkých záverečných prác v systéme. Ak nie sú vypísané žiadne práce je vrátený prázdny zoznam. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="getThesis"></a>
# **getThesis**
> Thesis getThesis(id)

Get a thesis by id

Získanie záverečnej práce podľa identifikátora.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    Long id = 56L; // Long | 
    try {
      Thesis result = apiInstance.getThesis(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#getThesis");
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

[**Thesis**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt záverečnej práce so zadaným identifikátorom.  V prípade, že taká záverečná práca neexistuje, tak je vrátená odpoveď 404.  |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="searchTheses"></a>
# **searchTheses**
> List&lt;Thesis&gt; searchTheses(UNKNOWN_BASE_TYPE)

Search theses by a teacher or a student

Vyhľadanie záverečnej práce na základe kritérií tela dopytu.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    UNKNOWN_BASE_TYPE UNKNOWN_BASE_TYPE = new UNKNOWN_BASE_TYPE(); // UNKNOWN_BASE_TYPE | Telo dopytu je jeden z možných objektov. Buď sú práce vyhľadané podľa priradeného študenta,  alebo podľa učiteľa, ktorý vypísal záverečnú prácu. 
    try {
      List<Thesis> result = apiInstance.searchTheses(UNKNOWN_BASE_TYPE);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#searchTheses");
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
 **UNKNOWN_BASE_TYPE** | [**UNKNOWN_BASE_TYPE**](UNKNOWN_BASE_TYPE.md)| Telo dopytu je jeden z možných objektov. Buď sú práce vyhľadané podľa priradeného študenta,  alebo podľa učiteľa, ktorý vypísal záverečnú prácu.  | [optional]

### Return type

[**List&lt;Thesis&gt;**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Zoznam záverečných prác vyhovujúcich kritériu. Ak nie sú vypísané žiadne práce je vrátený prázdny zoznam. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

<a name="submitThesis"></a>
# **submitThesis**
> Thesis submitThesis(id, inlineObject1)

Submit a thesis by id assigned to a student

Odovzdanie záverečnej práce podľa identifikátora. Ak je prihlásený študent v dopyte musí sa zhodovať so študentom priradeného k práci. Ak je prihlásený učiteľ je práca odovzdaná študentom podľa identifikátora v objekte tele dopytu, ktorý sa musí zhodovať so študentom priradeného k prác. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ThesisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure HTTP basic authorization: basicAuth
    HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
    basicAuth.setUsername("YOUR USERNAME");
    basicAuth.setPassword("YOUR PASSWORD");

    ThesisApi apiInstance = new ThesisApi(defaultClient);
    Long id = 56L; // Long | 
    InlineObject1 inlineObject1 = new InlineObject1(); // InlineObject1 | 
    try {
      Thesis result = apiInstance.submitThesis(id, inlineObject1);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ThesisApi#submitThesis");
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
 **inlineObject1** | [**InlineObject1**](InlineObject1.md)|  | [optional]

### Return type

[**Thesis**](Thesis.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Objekt záverečnej práce, ktorá bola odovzdaná študentom. |  -  |
**401** | Unauthorized request.  Dopyt musí byt autorizovaný platnou autentifikačnou schémou. Kód v odpovedi je vždy 401.  |  * WWW_Authenticate -  <br>  |
**403** | Authorized user doesn&#39;t have permission for the request. Autentifikovaný používateľ nemá oprávnenie na dopyt. Kód v odpovedi je vždy 403.  |  -  |
**404** | Requested resource was not found. Objekt nebolo možné nájsť. Kód v odpovedi je vždy 404.  |  -  |
**500** | Internal server error. Nepredvídateľná chyba na servery pri spracovaní dopytu. Kód v odpovedi je vždy 500.  |  -  |

