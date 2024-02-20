package sopt.org.seventhSeminar.external.client.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import sopt.org.seventhSeminar.exception.Error;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sopt.org.seventhSeminar.config.AWSConfig;
import sopt.org.seventhSeminar.exception.model.BadRequestException;
import sopt.org.seventhSeminar.exception.model.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;
    private final AWSConfig awsconfig;

    public AmazonS3Client amazonS3Client(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsconfig.getAccessKey(), awsconfig.getSecretKey());
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(awsconfig.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    // 파일 유효성 검사
    private String getFileExtension(String fileName){
        if(fileName.length()==0) {
            throw new NotFoundException(Error.NOT_FOUND_IMAGE_EXCEPTION, Error.NOT_FOUND_IMAGE_EXCEPTION.getMessage());
        }

        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new BadRequestException(Error.INVALID_MULTIPART_EXTENSION_EXCEPTION, Error.INVALID_MULTIPART_EXTENSION_EXCEPTION.getMessage());
        }
        return fileName.substring(fileName.lastIndexOf("."));

    }

    // 파일명 생성 -> 중복 방지
    private String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // 단일 이미지 업로드
    public String uploadImage(MultipartFile multiPartFile, String folder){
        String fileName = createFileName(multiPartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multiPartFile.getSize());
        objectMetadata.setContentType(multiPartFile.getContentType());

        try(InputStream inputStream = multiPartFile.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(awsconfig.getBucket()+"/"+folder+"/image", fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(awsconfig.getBucket()+"/"+folder+"/image",fileName).toString(); // 꺼내올 때는 링크만 전달
        } catch(IOException e){ // 저장이 제대로 되지 않았고 데이터가 존재하지 않을 경우
            throw new NotFoundException(Error.NOT_FOUND_SAVE_IMAGE_EXCEPTION, Error.NOT_FOUND_SAVE_IMAGE_EXCEPTION.getMessage());
        }
    }

    // 이미지 리스트 업로드
    public List<String> uploadImages(List<MultipartFile> multipartFileList, String folder){
        List<String> result = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFileList){
            result.add(uploadImage(multipartFile,folder));
        }
        return result;
    }

    // 이미지 삭제
    public void deleteFile(String imageUrl){
        String imageKey = imageUrl.substring(56);
        amazonS3.deleteObject(awsconfig.getBucket(), imageKey);
    }


}
