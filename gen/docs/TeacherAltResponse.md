

# TeacherAltResponse

Objekt študenta s identifikátormi záverečnej práce namiesto kolekcie objektov.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | Id učiteľa. V prípade ak je použité AIS ID ako identifikátor učiteľa tak je táto hodnota rovnaká ako v atribúte aisId. | 
**aisId** | **Long** | AIS ID učiteľa. Zadané pri vytvorení učiteľa. Musí byť unikátne v rámci všetkých učiteľov. | 
**name** | **String** |  |  [optional]
**email** | **String** |  | 
**password** | **String** |  |  [optional]
**institute** | **String** |  |  [optional]
**department** | **String** |  |  [optional]
**theses** | **List&lt;Long&gt;** | ID záverečných prác vytvorené učiteľom. Ak učiteľ nevytvoril žiadne záverečné práce je tento atribút vynechaný. |  [optional]



