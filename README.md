MinIO Integrated Library for Spring Boot
-------
This library provides utilities that make it easy to integrate MinIO storage into a spring boot project

Feature List:
* [Upload Multipartfile] (#upload-multipartfile)

Quick start 
-------
* Just add the dependency to an existing Spring Boot project
```xml
<dependency>
    <groupId>com.atviettelsolutions</groupId>
    <artifactId>vts-kit-ms-minio-integrated</artifactId>
    <version>1.0.0</version>
</dependency>
```

* Then, add the following properties to your `application-*.yml` file.
```yaml
minio:
  server: http://<host>:<port>
  access-key: <access-key>
  secret-key: <secret-key>
  bucket: test-framework
  auto-create-bucket: true
```

* Finally, declare `MinioService` object
```java
@Autowired
private MinioService minioService;
```

Usage
-------
##### Upload MultipartFile
```java
ObjectWriteResponse rsUpload = minioService.uploadMultipartFile(file);
```

Build
-------
* Build with Unittest
```shell script
mvn clean install
```

* Build without Unittest
```shell script
mvn clean install -DskipTests
```

Contribute
-------
[Contributors Guide](CONTRIBUTING.md)

License
-------
This code is under the [MIT License](https://opensource.org/licenses/MIT).

See the [LICENSE](LICENSE) file for required notices and attributions.
