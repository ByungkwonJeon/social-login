package com.bk.oauth2.sociallogin.data.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class Mutable extends Versioned {

  @Column(length = 16)
  @Id
  private UUID id = UUID.randomUUID();
}