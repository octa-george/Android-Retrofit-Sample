Retrofit Android Sample
=====================================


###Project Structure###
 
      Main Project
      ├── Api Lib
      │     ├── <some stuff from the lib>
      │     └── library
      │         ├── AndroidManifest.xml
      │         ├── res
      │         └── src // -> contains the Retrofit Java interfaces and Client, also information about our backend url
      │ 
      ├── Lib Commons
      │     └── <same structure as the lib 1>
      |         └──  // -> contains the app Models, simple POJOs for rest communication and not only
      │ 
      └── Sample App folder
            └── AndroidManifest.xml
            └── res
            └── src  // -> the sample app usage


###What is retrofit###
[Retrofit] library is a type-safe REST client for Android and Java created by [Square Open Source]. With this library you can request the webservices of a REST api with POST, GET and more. This library is awesome and very useful, but you need a good architecture and a good practice to use it as best as possible. This sample might offer you just that.

###Why should I use it?###
This part is the boring one. Often this is the part where you can read a big amount of theories and bechmarks on Retrofit, Volley, Asynctask… But that is not the purpose of this article. I want to show you how to use Retrofit in a smart way and not how to fell asleep quickly tonight ;) For those of you who were looking for an article about an alternative way to call a Rest API or why it’s forbidden to use Asynctask in your project, I advise you to read these articles, which are quite long but very interesting : [This article about Retrofit and Volley] and [this article is about Asynctask]. Now that you have read or skipped this part we can go further and see How to use Retrofit.

###How to use the sample?###

1 Inside your [application] you should create your SampleClient

```sh

       // this starts the client with the default values: logLevel NONE, linked to Production URL, app version 1.0
       client = RetrofitSampleClient.start();
       
       // if you want to customise the client and start it linked to Test environment you can do it so
       client = new RetrofitSampleClient.Builder().useTestServer().build();
       
       // other available methods
       .setLoggingLevel(RestAdapter.LogLevel.FULL) //-> NONE || BASIC || HEADERS || HEADERS_AND_ARGS
       .appVersion("0.0.3-debug")                  //-> is used for User-Agent Header 
       .useTestServer()                            //-> will use the QA URL from Api Constants
       ...

```


2 inside [ApiConstants] you can define your host URL


```sh

       public static final String PROD_URL = "https://api.sample.com/rest";
       public static final String TEST_URL = "http://qa.sample.com/rest";

```

   - if you wish to send requests to a different host, you can change the endpoint by calling:
```sh

       RetrofitSampleApplication.getInstance().setEndpoint("http://jsonplaceholder.typicode.com");

```
   - this will change the endpoint for all services, or if you wish to change the host for one service only:
```sh

       String url = "http://jsonplaceholder.typicode.com";
       SampleService sampleService = RetrofitSampleApplication.getInstance().getHostAdapter(url).create(SampleService.class);
       sampleService.doSomething();

```

3 Wherever you want to make a request you can do it like:
```sh

       RetrofitSampleApplication.getInstance().getXService().doSomething();

```


###Conclusion###

Now you have a great, sexy, powerful application with [Retrofit]. TA-DAH :D !



[Square Open Source]:http://square.github.io/
[application]:https://github.com/octa-george/Android-Retrofit-Sample/blob/master/app/src/main/java/ro/octa/retrofitsample/app/RetrofitSampleApplication.java
[ApiConstants]:https://github.com/octa-george/Android-Retrofit-Sample/blob/master/sample-api/src/main/java/ro/octa/retrofitsample/api/ApiConstants.java
[Retrofit]:http://square.github.io/retrofit/
[This article about Retrofit and Volley]:http://instructure.github.io/blog/2013/12/09/volley-vs-retrofit/
[this article is about Asynctask]:http://simonvt.net/2014/04/17/asynctask-is-bad-and-you-should-feel-bad/



