package com.bk.oauth2.sociallogin.data.repository;

import com.bk.oauth2.sociallogin.data.enums.SocialType;
import com.bk.oauth2.sociallogin.data.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findBySocialId(final String socialId);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}