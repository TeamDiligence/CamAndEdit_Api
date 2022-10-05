package camandedit.server.global.config;

import camandedit.server.global.property.AwsS3Property;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

  private final AwsS3Property awsS3Property;

  @Bean
  public AmazonS3 amazonS3(){
    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
            awsS3Property.getAccessKey(), awsS3Property.getSecretKey())))
        .withRegion(Regions.AP_NORTHEAST_2)
        .build();
  }
}
