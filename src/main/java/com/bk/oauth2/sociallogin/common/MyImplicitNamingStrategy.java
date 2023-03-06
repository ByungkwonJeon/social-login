package com.bk.oauth2.sociallogin.common;

import java.util.Locale;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class MyImplicitNamingStrategy extends ImplicitNamingStrategyComponentPathImpl {
  public static final MyImplicitNamingStrategy INSTANCE = new MyImplicitNamingStrategy();

  protected static String addUnderscores(String name) {
    final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
    for (int i = 1; i < buf.length() - 1; i++) {
      if (Character.isLowerCase(buf.charAt(i - 1))
          && Character.isUpperCase(buf.charAt(i))
          && Character.isLowerCase(buf.charAt(i + 1))) {
        buf.insert(i++, '_');
      }
    }
    return buf.toString().toLowerCase(Locale.ROOT);
  }

  @Override
  protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
    return super.toIdentifier(addUnderscores(stringForm), buildingContext);
  }
}