# Anagrams solver with clients

An [anagram][1] is a word or sentence that can be rearranged to create another one, like **parliament** can be 
turn to **partial men**, or **Clint Eastwood** can turn into **Old West Action** ([Internet Anagram Server][2])

The goal is to implement a command line application that allows both finding the longest anagram and all existing 
anagrams in the dictionary used.

A dictionary will be a list of words where a line contains only a word or a sentence.

The running application reads all the text files from a given directory, as parameter, that would work as dictionaries and gives a 
prompt to search for anagrams as for example:

```bash
$ groovy -cp ... <path-to-dictionary-folder>
Dictionary folder dict/words/en/ will be loaded
Use <ctrl> + C to finish
* Write a word for searching anagrams: ...
```

## Environment

Groovy is used as programming language and Gradle (local installation) as build tool 

```bash
$ ./gradlew -v

------------------------------------------------------------
Gradle 5.2.1
------------------------------------------------------------

Build time:   2019-02-08 19:00:10 UTC
Revision:     f02764e074c32ee8851a4e1877dd1fea8ffb7183

Kotlin DSL:   1.1.3
Kotlin:       1.3.20
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.13 compiled on July 10 2018
JVM:          1.8.0_202 (Azul Systems, Inc. 25.202-b05)
OS:           Linux 4.19.32-1-MANJARO amd64

```

## Running at the project root folder

The test will be running with 

```bash
[project.path]$ ./gradlew test
```

Run the application executing 

```bash
[project.path]$ groovy -cp libs/anagrams-solver-0.1.jar app/src/main/groovy/Anagrammatist <dictionaries folder path>
```

when 'anagrams' subproject jar was generated.

```bash
[project.path]$ ./gradlew anagrams:jar
```

## Anagrammatist command line application

### Installation

Unpack the ZIP or TAR file in a suitable location

### Usage

```bash
$ cd anagrammatist-0.1
$ ./bin/anagrammatist <dictionary folder>
Dictionaries folder <dictionary folder> will be loaded
Use <ctrl> + C to finish
* Write a word for searching anagrams: anagrams
```

## Kotlin server
 
A minimal Kotlin Spring Boot server

Server would be running with
 
 ```bash
 [project.path]$ ./gradlew server:bootRun
> Task :server:compileKotlin
> Task :server:compileJava NO-SOURCE
> Task :server:processResources UP-TO-DATE
> Task :server:classes UP-TO-DATE

> Task :server:bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.5.RELEASE)

2019-05-29 18:00:08.382  INFO 26363 --- [           main] poc.anagrams.ApplicationKt               : Starting ApplicationKt on spectre with PID 26363 (/home/migupl/Develop/github.com/chal-yaas/server/build/classes/kotlin/main started by migupl in /home/migupl/Develop/github.com/chal-yaas/server)
2019-05-29 18:00:08.390  INFO 26363 --- [           main] poc.anagrams.ApplicationKt               : No active profile set, falling back to default profiles: default
2019-05-29 18:00:08.901  WARN 26363 --- [kground-preinit] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2019-05-29 18:00:10.570  WARN 26363 --- [           main] io.undertow.websockets.jsr               : UT026010: Buffer pool was not set on WebSocketDeploymentInfo, the default pool will be used
2019-05-29 18:00:10.614  INFO 26363 --- [           main] io.undertow.servlet                      : Initializing Spring embedded WebApplicationContext
2019-05-29 18:00:10.615  INFO 26363 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2013 ms
2019-05-29 18:00:11.539  INFO 26363 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-05-29 18:00:11.943  INFO 26363 --- [           main] org.xnio                                 : XNIO version 3.3.8.Final
2019-05-29 18:00:11.960  INFO 26363 --- [           main] org.xnio.nio                             : XNIO NIO Implementation Version 3.3.8.Final
2019-05-29 18:00:12.210  INFO 26363 --- [           main] o.s.b.w.e.u.UndertowServletWebServer     : Undertow started on port(s) 8080 (http) with context path ''
2019-05-29 18:00:12.214  INFO 26363 --- [           main] poc.anagrams.ApplicationKt               : Started ApplicationKt in 4.735 seconds (JVM running for 5.506)
2019-05-29 18:00:33.845  INFO 26363 --- [  XNIO-1 task-1] io.undertow.servlet                      : Initializing Spring DispatcherServlet 'dispatcherServlet'
2019-05-29 18:00:33.845  INFO 26363 --- [  XNIO-1 task-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2019-05-29 18:00:33.849  INFO 26363 --- [  XNIO-1 task-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
```
 
Test /language endpoint with curl
 
```bash
[project.path]$ curl -i -H "Accept: application/json" http://localhost:8080/language
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json;charset=UTF-8
Date: Thu, 30 May 2019 16:04:07 GMT

{"language":"en"}
```

### Docker image

You can create a Docker image without dictionary info executing
```bash
[project.path]$ ./gradlew server:bootJar
> Task :anagrams:compileJava NO-SOURCE
> Task :anagrams:compileGroovy UP-TO-DATE
> Task :anagrams:processResources UP-TO-DATE
> Task :anagrams:classes UP-TO-DATE
> Task :anagrams:jar
> Task :server:compileKotlin
> Task :server:compileJava NO-SOURCE
> Task :server:processResources UP-TO-DATE
> Task :server:classes UP-TO-DATE
> Task :server:bootJar

BUILD SUCCESSFUL in 5s
6 actionable tasks: 3 executed, 3 up-to-date
[project.path]$
[project.path]$ sudo docker build -t anagrams_server server/.
STEP 1: FROM openjdk:8-jdk-alpine
STEP 2: WORKDIR /app
--> Using cache 85ababea2157867a78bd970dca91bd88d1ada3df62eedbb91c018985eff35377
STEP 3: COPY build/libs/*.jar server.jar
--> dcf6577ea6f09a819af26ca0e7e45b0006c5d4f3472ea3fe8ee85de009ddbc3d
STEP 4: RUN unzip server.jar && rm server.jar
...
[project.path]$ 
```

Now, you can use any dictionary at running container as by example with Wordnet dictionary
```bash
[project.path]$ sudo docker run -it -p 8080:8080 -v $(pwd)/server/src/main/resources/wordnet:/app/wordnet -v $(pwd)/server/src/main/resources/application.yml:/app/application.yml anagrams_server
```

and using curl you can request by example
```bash
[project.path]$ curl -i -H "Accept: application/json" http://localhost:8080/anagrams/example
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json;charset=UTF-8
Date: Fri, 31 May 2019 08:22:50 GMT

[{"length":5,"words":["Eelam","ample","maple","expel"]},{"length":4,"words":["alee","male","Elam","lame","meal","pale","leap","peal","plea","axle","apex","lamp","palm","peel"]},{"length":3,"words":["ale","lea","ape","pea","axe","alp","lap","pal","lax","map","pax","eel","lee","elm"]},{"length":2,"words":["Ea","la","ma","ax","em","pe","LP"]}]
```

Use other dictionary as used in tests
```bash
[project.path]$ sudo docker run -it -p 8080:8080 -v $(pwd)/server/src/test/resources/dict:/app/dict -v $(pwd)/server/src/test/resources/application.yml:/app/application.yml anagrams_server
```

and doing the same request with curl
```bash
[project.path]$ curl -i -H "Accept: application/json" http://localhost:8080/anagrams/example
HTTP/1.1 200 OK
Connection: keep-alive
Transfer-Encoding: chunked
Content-Type: application/json;charset=UTF-8
Date: Fri, 31 May 2019 08:28:28 GMT

[]
```

gets empty response because the dictionary only contains a few words.

The important things are the volume definition (config and dictionary path) at instance start. 
You have to define:
- **application.yml**, defines path to dictionary and language of this
- **<folder>**, dictionary folder

and both must be mapped with same name to **/app** folder at running Docker instance.

But playing with multiples instances who contains distinct dictionaries is most funny. Remember to mapping every one 
to a different port.

---
## Fun facts

"Anagrams" itself can be anagrammatized as "Ars magna" (Latin, 'the great art').

English language:
- [Longest Words in the English Language][3]
- A word can be composed by only vowels (a, e, i, o, u) as the word [euouae][4]
- [I found the best anagram in English][5]

Playing with dictionaries of [English][6] (275k words), [Spanish][7] (250k words), [French][8] (210k words) ... 
[Wordnet][9] database for English ([MIT license][10] [citation][11]) or [Other laguages][12]

---
[1]: https://en.wikipedia.org/wiki/Anagram
[2]: https://wordsmith.org/anagram/index.html
[3]: https://grammar.yourdictionary.com/word-lists/longest-words-in-the-english-language.html
[4]: https://en.wikipedia.org/wiki/Euouae
[5]: https://blog.plover.com/lang/anagram-scoring.html
[6]: https://github.com/zeke/an-array-of-english-words
[7]: https://github.com/words/an-array-of-spanish-words
[8]: https://github.com/words/an-array-of-french-words
[9]: https://wordnet.princeton.edu/download/current-version
[10]: https://wordnet.princeton.edu/license-and-commercial-use
[11]: https://wordnet.princeton.edu/citing-wordnet
[12]: http://globalwordnet.org/resources/wordnets-in-the-world/
