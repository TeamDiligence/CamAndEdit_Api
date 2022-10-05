package camandedit.server.user.domain;

import camandedit.server.global.common.BaseTimeJpaEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String password;

  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String name;

  private String userImage;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AuthProvider authProvider;

  @Builder
  public User(String email, String name, String userImage, AuthProvider authProvider, String password) {
    this.email = email;
    this.name = name;
    this.userImage = userImage;
    this.authProvider = authProvider;
    this.password = password;
  }


  public User updateProfile(String name, String image){
    this.name = name;
    this.userImage = image;
    return this;
  }

  public void encodePassword(String encodedPassword){
    this.password = encodedPassword;
  }
}
