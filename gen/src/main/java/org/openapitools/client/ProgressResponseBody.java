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


package org.openapitools.client;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {

    private final ResponseBody responseBody;
    private final ApiCallback callback;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, ApiCallback callback) {
        this.responseBody = responseBody;
        this.callback = callback;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                callback.onDownloadProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}


