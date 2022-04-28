package com.viettel.vtskit.minio;

import com.viettel.vtskit.minio.configuration.ConstantConfiguration;
import com.viettel.vtskit.minio.configuration.MinioProperties;
import com.viettel.vtskit.minio.model.UploadResult;
import com.viettel.vtskit.minio.utils.StringUtils;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

public class MinioService {

    private MinioClient minioClient;
    private MinioProperties minioProperties;

    private UploadResult uploadBytes(String fileName, byte[] bytes, String contentType) throws Exception {
        ByteArrayInputStream is = null;
        try{
            is = new ByteArrayInputStream(bytes);
            PutObjectArgs.Builder uploadArgs = PutObjectArgs.builder();
            uploadArgs.contentType(contentType);
            uploadArgs.object(fileName);
            uploadArgs.bucket(minioProperties.getBucket());
            uploadArgs.stream(is, bytes.length, ConstantConfiguration.PART_SIZE);
            ObjectWriteResponse response = minioClient.putObject(uploadArgs.build());
            UploadResult result = new UploadResult();
            result.setEtag(response.etag());
            result.setFileName(fileName);
            result.setVersionId(response.versionId());
            return result;
        }catch (Exception ex){
            throw ex;
        }finally {
            if(is != null){
                is.close();
            }
        }
    }

    public UploadResult uploadMultipartFile(MultipartFile file) throws Exception {
        byte[] fileData = file.getBytes();
        return uploadBytes(file.getOriginalFilename(), fileData, file.getContentType());
    }

    public UploadResult uploadString(String content) throws Exception {

        String a = null;
        System.out.printf("" + a.length());

        byte[] fileData = content.getBytes(Charset.forName("UTF-8"));
        String randomName = String.format("%s.txt", StringUtils.randomString());
        return uploadBytes(randomName, fileData, "text/plain");
    }

    @Autowired
    public void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
}
