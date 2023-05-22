

# Student

Objekt študenta.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | Id študenta. V prípade ak je použité AIS ID ako identifikátor študenta tak je táto hodnota rovnaká ako v atribúte aisId. | 
**aisId** | **Long** | AIS ID študenta. Zadané pri vytvorení študenta. Musí byť unikátne v rámci všetkých študentov. | 
**name** | **String** |  |  [optional]
**email** | **String** |  | 
**year** | **Integer** |  |  [optional]
**term** | **Integer** |  |  [optional]
**programme** | **String** |  |  [optional]
**thesis** | [**Thesis**](Thesis.md) |  |  [optional]



