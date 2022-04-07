package com.jio.ranshjmd.RequestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIConnection {
//
//    private static Retrofit retrofit = null;
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private static SSLContext getSSLConfig(Context context) throws CertificateException, IOException,
//            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//
//        // Loading CAs from an InputStream
//        CertificateFactory cf = null;
//        cf = CertificateFactory.getInstance("X.509");
//
//        Certificate ca;
//        // I'm using Java7. If you used Java6 close it manually with finally.
//        try (InputStream cert = context.getResources().openRawResource(R.raw.ssl)) {
//            ca = cf.generateCertificate(cert);
//        }
//
//        // Creating a KeyStore containing our trusted CAs
//        String keyStoreType = KeyStore.getDefaultType();
//        KeyStore keyStore   = KeyStore.getInstance(keyStoreType);
//        keyStore.load(null, null);
//        keyStore.setCertificateEntry("R-Ansh", ca);
//
//        // Creating a TrustManager that trusts the CAs in our KeyStore.
//        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//        tmf.init(keyStore);
//
//        // Creating an SSLSocketFactory that uses our TrustManager
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, tmf.getTrustManagers(), null);
//
//        return sslContext;
//
//    }
//
//    public static Retrofit getretrofitinstance(Context context) {
//
//        try {
//            String base_url="https://10.166.160.36:8443/ranshapi-0.0.1-SNAPSHOT/";
//            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(base_url);
//            OkHttpClient okHttp = new OkHttpClient.Builder()
//                    .sslSocketFactory(getSSLConfig(context).getSocketFactory())
//                    .build();
//
//
//            if (retrofit==null) {
//                retrofit = builder.client(okHttp)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//            }
//        } catch (KeyManagementException ex) {
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (NoSuchAlgorithmException ex) {
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (KeyStoreException ex) {
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (CertificateException ex) {
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (IOException ex) {
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//        return retrofit;
//    }

    private static Retrofit retrofit1;
    private static String base_url="http://10.166.160.36:8080/RAnsh_JMD/";
    public static Retrofit getretrofitinstance(){
        if (retrofit1 == null){
            retrofit1 = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }

}
