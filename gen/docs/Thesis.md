

# Thesis

Objekt záverečnej práce.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** |  | 
**registrationNumber** | **String** |  | 
**title** | **String** |  | 
**description** | **String** |  |  [optional]
**department** | **String** |  | 
**supervisor** | [**TeacherAltResponse**](TeacherAltResponse.md) |  | 
**author** | [**StudentAltResponse**](StudentAltResponse.md) |  |  [optional]
**publishedOn** | **LocalDate** |  |  [optional]
**deadline** | **LocalDate** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  | 
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]



## Enum: TypeEnum

Name | Value
---- | -----
BACHELOR | &quot;BACHELOR&quot;
MASTER | &quot;MASTER&quot;
DISSERTATION | &quot;DISSERTATION&quot;



## Enum: StatusEnum

Name | Value
---- | -----
FREE_TO_TAKE | &quot;FREE_TO_TAKE&quot;
IN_PROGRESS | &quot;IN_PROGRESS&quot;
SUBMITTED | &quot;SUBMITTED&quot;



